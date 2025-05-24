package com.daniela.proyecto;

import java.util.ArrayList;
import java.util.List;

public class RutaUtil {
    public static void mostrarRuta(int origen, int destino, Floyd.Resultado resultado, Grafo grafo) {
        if (resultado.dist[origen][destino] == Integer.MAX_VALUE) {
            System.out.println("No hay ruta disponible.");
            return;
        }

        List<String> ruta = new ArrayList<>();
        reconstruirRuta(origen, destino, resultado.camino, ruta, grafo);
        ruta.add(grafo.getCiudades().get(destino));

        System.out.println("Ruta más corta:");
        for (int i = 0; i < ruta.size(); i++) {
            System.out.print(ruta.get(i));
            if (i != ruta.size() - 1) System.out.print(" -> ");
        }
        System.out.println("\nTiempo total: " + resultado.dist[origen][destino] + " hrs");
    }

    public static void reconstruirRuta(int i, int j, int[][] camino, List<String> ruta, Grafo grafo) {
        if (camino[i][j] == -1 || camino[i][j] == i) {
            ruta.add(grafo.getCiudades().get(i));
            return;
        }
        reconstruirRuta(i, camino[i][j], camino, ruta, grafo);
        ruta.add(grafo.getCiudades().get(camino[i][j]));
    }

    public static String calcularCentro(Grafo grafo, int[][] distancias) {
        int n = distancias.length, minEx = Integer.MAX_VALUE, centro = -1;
        for (int i = 0; i < n; i++) {
            int max = 0;
            for (int j = 0; j < n; j++)
                if (distancias[i][j] != Integer.MAX_VALUE)
                    max = Math.max(max, distancias[i][j]);
            if (max < minEx) {
                minEx = max;
                centro = i;
            }
        }
        return grafo.getCiudades().get(centro);
    }
}
