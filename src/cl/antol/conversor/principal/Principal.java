package cl.antol.conversor.principal;

import cl.antol.conversor.servicios.ExchangerateAPI;
import cl.antol.conversor.utilidades.GeneradorDeArchivo;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExchangerateAPI consulta = new ExchangerateAPI();
        GeneradorDeArchivo generadorDeArchivo = new GeneradorDeArchivo();
        String[] monedas = {"USD", "CLP", "ARS", "EUR", "GBP", "JPY"}; // Agrega más monedas según lo necesites

        System.out.println("Seleccione el tipo de Moneda:");
        for (int i = 0; i < monedas.length; i++) {
            System.out.println((i + 1) + ") " + monedas[i]);
        }
        int monedaOrigenIndex = scanner.nextInt() - 1;
        String monedaOrigen = monedas[monedaOrigenIndex];

        System.out.println("Ingrese la cantidad en " + monedaOrigen + ":");
        double cantidad = scanner.nextDouble();

        System.out.println("Indique a que tipo desea convertir:");
        for (int i = 0; i < monedas.length; i++) {
            System.out.println((i + 1) + ") " + monedas[i]);
        }
        int monedaDestinoIndex = scanner.nextInt() - 1;
        String monedaDestino = monedas[monedaDestinoIndex];

        try {
            double tasaDeCambio = consulta.obtenerTasaDeCambio(monedaOrigen, monedaDestino);
            double cantidadConvertida = cantidad * tasaDeCambio;
            System.out.println("Cantidad en " + monedaDestino + ": " + cantidadConvertida);

            // Guardar el resultado en un archivo
            String resultado = "Cantidad en " + monedaDestino + ": " + cantidadConvertida;
            String nombreArchivo = "resultado_conversion.txt";
            generadorDeArchivo.escribirEnArchivo(nombreArchivo, resultado);

            System.out.println("Los datos se han guardado en " + nombreArchivo);
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }
    }
}