package cl.antol.conversor.principal;
import cl.antol.conversor.servicios.ExchangerateAPI;
import cl.antol.conversor.utilidades.GeneradorDeArchivo;
import cl.antol.conversor.utilidades.Titulos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Crear e inicializar el Map con códigos de moneda y descripciones, esta opcion de codigo te permite agregar
        // Dinamicamente mas monedas que sean validas dentro del JSON que trae la API

        HashMap<String, String> monedas = new HashMap<>();
        monedas.put("USD", "Dólar EEUU");
        monedas.put("CLP", "Peso Chileno");
        monedas.put("ARS", "Peso Argentino");
        monedas.put("EUR", "Euro");
        monedas.put("GBP", "Libra Esterlina");
        monedas.put("JPY", "Yen Japonés");
        monedas.put("BRL", "Real Brasil");
        monedas.put("INR", "Rupia India");


        // Instanciar el generador de archivos
        GeneradorDeArchivo generadorDeArchivo = new GeneradorDeArchivo();
        String nombreArchivo = "resultado_conversion.txt";

        Titulos titulos = new Titulos();
        titulos.mostrarTitulo("Conversor de Monedas");



        // Ciclo principal

        while (true) {
            // Mostrar menú con descripciones detalladas y opción de salir
            System.out.println("\nSeleccione el tipo de Moneda:\n");
            int index = 1;
            for (String codigo : monedas.keySet()) {
                System.out.println(index + ") " + codigo + " - " + monedas.get(codigo));
                index++;
            }
            System.out.println(index + ") Salir");

            // Capturar la opción del usuario
            int opcion = scanner.nextInt();
            if (opcion == index) {
                // Opción de salir
                System.out.println("Saliendo...");
                break;
            }
            if (opcion <= 0 || opcion > monedas.size()) {
                // Opción no válida
                System.out.println("Opción no válida, intente nuevamente.");
                continue;
            }

            // Seleccionar moneda de origen
            String monedaOrigen = (String) monedas.keySet().toArray()[opcion - 1];
            System.out.println("Ingrese la cantidad en " + monedas.get(monedaOrigen) + ":");
            double cantidad = scanner.nextDouble();

            // Mostrar menú para moneda destino y opción de salir
            System.out.println("Indique a qué tipo desea convertir: ");
            index = 1;
            for (String codigo : monedas.keySet()) {
                System.out.println(index + ") " + codigo + " - " + monedas.get(codigo));
                index++;
            }
            System.out.println(index + ") Salir");

            // Capturar la opción del usuario
            opcion = scanner.nextInt();
            if (opcion == index) {
                // Opción de salir
                System.out.println("Saliendo...");
                break;
            }
            if (opcion <= 0 || opcion > monedas.size()) {
                // Opción no válida
                System.out.println("Opción no válida, intente nuevamente.");
                continue;
            }

            // Seleccionar moneda de destino
            String monedaDestino = (String) monedas.keySet().toArray()[opcion - 1];

// Lógica de conversión

            ExchangerateAPI consulta = new ExchangerateAPI();
            try {
                double tasaDeCambio = consulta.obtenerTasaDeCambio(monedaOrigen, monedaDestino);
                double cantidadConvertida = cantidad * tasaDeCambio;
                System.out.println("Cantidad en " + monedas.get(monedaDestino) + ": " + cantidadConvertida);

                // Obtener la fecha y hora actual
                LocalDateTime fechaHoraActual = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String fechaHoraFormateada = fechaHoraActual.format(formatter);

                // Formatear el resultado con fecha y hora
                String resultado = String.format("Registro: %s\nConvertido %.2f %s a %.2f %s\n",
                        fechaHoraFormateada, cantidad, monedaOrigen, cantidadConvertida, monedaDestino);

                // Guardar el resultado en un archivo (añadiendo contenido)
                generadorDeArchivo.escribirEnArchivo(nombreArchivo, resultado, true);

                System.out.println("Los datos se han guardado en " + nombreArchivo);
            } catch (RuntimeException e) {
                System.err.println(e.getMessage());
            }
        }

        scanner.close();
    }
}