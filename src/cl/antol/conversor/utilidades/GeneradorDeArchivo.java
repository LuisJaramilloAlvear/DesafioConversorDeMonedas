package cl.antol.conversor.utilidades;

import java.io.FileWriter;
import java.io.IOException;

public class GeneradorDeArchivo {
    public void escribirEnArchivo(String nombreArchivo, String contenido) {
        try (FileWriter fileWriter = new FileWriter(nombreArchivo)) {
            fileWriter.write(contenido);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
