package com.modelos.subsystem;

import com.sun.tools.javac.Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    @SuppressWarnings("unchecked")
    public List<String> leer() {
        List<String> textos = new ArrayList<>();
        File file = new File("src/main/java/com/modelos/test.txt");

        // Verificar si el archivo existe y tiene contenido antes de intentar leerlo
        if (!file.exists() || file.length() == 0) {
            System.out.println("El archivo está vacío o no existe.");
            return textos;
        }

        // Leer el archivo y almacenar cada línea en la lista
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(file))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                textos.add(linea); // Añadir cada línea a la lista
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        return textos;
    }


}
