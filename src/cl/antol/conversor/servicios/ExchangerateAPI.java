package cl.antol.conversor.servicios;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExchangerateAPI {

    private static final String API_KEY = "066ff9e47233625b39a9633c";
    private static final String BASE_API_URL = "https://v6.exchangerate-api.com/v6/";

    public JsonObject obtenterDatosDeApi(String base) {
        String apiUrl = BASE_API_URL + API_KEY + "/latest/" + base;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();
            JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();

            if (jsonObject.has("result") && jsonObject.get("result").getAsString().equals("error")) {
                throw new RuntimeException("Código de moneda inválido: " + base);
            }

            return jsonObject;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error al obtener datos de la API: " + e.getMessage());
        }
    }

    public double obtenerTasaDeCambio(String base, String objetivo) {
        JsonObject datos = obtenterDatosDeApi(base);
        if (!datos.has("conversion_rates")) {
            throw new RuntimeException("No se encontraron tasas de cambio");
        }

        JsonObject tasas = datos.getAsJsonObject("conversion_rates");
        return tasas.has(objetivo) ? tasas.get(objetivo).getAsDouble() : 0.0;
    }
}