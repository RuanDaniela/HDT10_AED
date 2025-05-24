package com.daniela.proyecto;

import static org.junit.Assert.*;
import org.junit.Test;

public class GrafoTest {
    @Test
    public void testAgregarCiudad() {
        Grafo grafo = new Grafo();
        grafo.agregarCiudad("CiudadX");
        assertTrue(grafo.getCiudades().contains("CiudadX"));
    }

    @Test
    public void testAgregarConexion() {
        Grafo grafo = new Grafo();
        grafo.agregarConexion("A", "B", 10);
        int[][] matriz = grafo.getMatriz();
        assertEquals(10, matriz[grafo.getCiudades().indexOf("A")][grafo.getCiudades().indexOf("B")]);
    }

    @Test
    public void testEliminarConexion() {
        Grafo grafo = new Grafo();
        grafo.agregarConexion("A", "B", 5);
        grafo.eliminarConexion("A", "B");
        assertEquals(Integer.MAX_VALUE, grafo.getMatriz()[grafo.getCiudades().indexOf("A")][grafo.getCiudades().indexOf("B")]);
    }
}
