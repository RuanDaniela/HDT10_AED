package com.daniela.proyecto;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class ArchivoLogistica {
    public void leerArchivoDesdeTexto(String ruta, Grafo grafo, String clima) throws IOException {
        try (Scanner sc = new Scanner(new FileInputStream(ruta))) {
            while (sc.hasNextLine()) {
                String[] datos = sc.nextLine().split(" ");
                if (datos.length < 6) continue;

                String origen = datos[0];
                String destino = datos[1];
                int tiempo;

                switch (clima.toLowerCase()) {
                    case "lluvia" -> tiempo = Integer.parseInt(datos[3]);
                    case "nieve" -> tiempo = Integer.parseInt(datos[4]);
                    case "tormenta" -> tiempo = Integer.parseInt(datos[5]);
                    default -> tiempo = Integer.parseInt(datos[2]);
                }

                grafo.agregarConexion(origen, destino, tiempo);
            }
        }
    }
}
