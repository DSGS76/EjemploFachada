package com.modelos.subsystem;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;

public class FileEncrypter {

    private static final byte[] claveFija = new byte[]{
        (byte) 0x7A, (byte) 0x3B, (byte) 0x91, (byte) 0xC4,
        (byte) 0x56, (byte) 0xAD, (byte) 0xFF, (byte) 0x12,
        (byte) 0x89, (byte) 0xE3, (byte) 0x0A, (byte) 0x6D,
        (byte) 0xB7, (byte) 0x5E, (byte) 0x39, (byte) 0x02,
        (byte) 0xA6, (byte) 0xD4, (byte) 0x78, (byte) 0x1C,
        (byte) 0xF0, (byte) 0x99, (byte) 0x43, (byte) 0x20,
        (byte) 0xCE, (byte) 0x11, (byte) 0x3F, (byte) 0x65,
        (byte) 0xBA, (byte) 0xD8, (byte) 0x0E, (byte) 0x74
    };

    private SecretKey clave;

    public FileEncrypter() {
        this.clave = new SecretKeySpec(claveFija, "AES");
    }

    // Método general para procesar el archivo (cifrar o descifrar)
    private void procesarArchivo(String rutaArchivo, int modo) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(modo, clave);

        File archivo = new File(rutaArchivo);
        File archivoTemporal = new File(rutaArchivo + ".temp");


        try (FileInputStream fis = new FileInputStream(archivo);
             FileOutputStream fos = new FileOutputStream(archivoTemporal);
             CipherOutputStream cos = new CipherOutputStream(fos, cipher)) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                cos.write(buffer, 0, bytesRead);
            }
        }

        // Asegúra que el archivo temporal se creó correctamente antes de reemplazar el original
        if (archivoTemporal.exists()) {
            archivo.delete(); // Elimina el archivo original
            archivoTemporal.renameTo(archivo); // Renombra el temporal al original
        } else {
            System.err.println("Error al procesar el archivo: el archivo temporal no se creó.");
        }
    }

    // Método para cifrar un archivo
    public void encriptar(String rutaArchivo) throws Exception {
        procesarArchivo(rutaArchivo, Cipher.ENCRYPT_MODE);
    }

    // Método para descifrar un archivo
    public void desencriptar(String rutaArchivo) throws Exception {
        procesarArchivo(rutaArchivo, Cipher.DECRYPT_MODE);
    }
}
