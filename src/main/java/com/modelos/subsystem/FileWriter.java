package com.modelos.subsystem;


import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

public class FileWriter {

	public void escribir(String texto) {

        File file = new File("src/main/java/com/modelos/test.txt");

        // Escribir la lista actualizada en el archivo
        try (BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(file, true))) {
            writer.write(texto);
            writer.newLine(); // Agregar una nueva línea después del texto
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    
    }

}
