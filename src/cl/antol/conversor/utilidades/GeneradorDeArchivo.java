package cl.antol.conversor.utilidades;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class GeneradorDeArchivo {

    // Método para escribir en el archivo (añadir contenido)
    public void escribirEnArchivo(String nombreArchivo, String contenido, boolean append) {
        try (FileWriter fileWriter = new FileWriter(nombreArchivo, append);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.println(contenido);
        } catch (IOException e) {
            System.err.println("Error escribiendo en el archivo: " + e.getMessage());
        }
    }
}
