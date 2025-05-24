package com.daniela.proyecto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grafo {
    private List<String> ciudades = new ArrayList<>();
    private Map<String, Integer> indices = new HashMap<>();
    private int[][] matriz;
    private final int INF = Integer.MAX_VALUE;

    public Grafo() {
        matriz = new int[0][0];
    }

    public void agregarCiudad(String ciudad) {
        if (!indices.containsKey(ciudad)) {
            ciudades.add(ciudad);
            indices.put(ciudad, ciudades.size() - 1);
            expandirMatriz();
        }
    }

    private void expandirMatriz() {
        int n = ciudades.size();
        int[][] nueva = new int[n][n];
        for (int[] fila : nueva) Arrays.fill(fila, INF);

        for (int i = 0; i < matriz.length; i++)
            for (int j = 0; j < matriz.length; j++)
                nueva[i][j] = matriz[i][j];

        matriz = nueva;
    }

    public void agregarConexion(String origen, String destino, int tiempo) {
        agregarCiudad(origen);
        agregarCiudad(destino);
        int i = indices.get(origen);
        int j = indices.get(destino);
        matriz[i][j] = tiempo;
    }

    public void eliminarConexion(String origen, String destino) {
        if (indices.containsKey(origen) && indices.containsKey(destino)) {
            int i = indices.get(origen);
            int j = indices.get(destino);
            matriz[i][j] = INF;
        }
    }

    public void reiniciar() {
        ciudades.clear();
        indices.clear();
        matriz = new int[0][0];
    }

    public int[][] getMatriz() {
        return matriz;
    }

    public List<String> getCiudades() {
        return ciudades;
    }
}
