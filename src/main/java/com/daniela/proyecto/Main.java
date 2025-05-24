package com.daniela.proyecto;


import java.util.List;
import java.util.Scanner;


public class Main {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        Grafo grafo = new Grafo();
        ArchivoLogistica lector = new ArchivoLogistica();

        System.out.print("Ingrese tipo de clima (normal, lluvia, nieve, tormenta): ");
        String clima = input.nextLine();

        try {
            lector.leerArchivoDesdeTexto("logistica.txt", grafo, clima);
        } catch (Exception e) {
            System.out.println("Error al leer archivo: " + e.getMessage());
            return;
        }

        mostrarMatriz(grafo);

        boolean activo = true;
        while (activo) {
            System.out.println("""
            --- MENÚ ---
            1. Mostrar ruta más corta entre dos ciudades
            2. Mostrar centro del grafo
            3. Modificar grafo
            4. Salir
            """);

            String opcion = input.nextLine();
            switch (opcion) {
                case "1" -> mostrarRutaInteractiva(grafo);
                case "2" -> mostrarCentro(grafo);
                case "3" -> modificarGrafo(grafo, lector);
                case "4" -> activo = false;
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    public static void mostrarRutaInteractiva(Grafo grafo) {
        Floyd.Resultado resultado = Floyd.calcular(grafo.getMatriz());
        System.out.print("Ciudad origen: ");
        String origen = input.nextLine();
        System.out.print("Ciudad destino: ");
        String destino = input.nextLine();

        int i = grafo.getCiudades().indexOf(origen);
        int j = grafo.getCiudades().indexOf(destino);

        if (i == -1 || j == -1) {
            System.out.println("Ciudad no encontrada.");
            return;
        }

        RutaUtil.mostrarRuta(i, j, resultado, grafo);
    }

    public static void mostrarCentro(Grafo grafo) {
        Floyd.Resultado resultado = Floyd.calcular(grafo.getMatriz());
        String centro = RutaUtil.calcularCentro(grafo, resultado.dist);
        System.out.println("Centro del grafo: " + centro);
    }

    public static void modificarGrafo(Grafo grafo, ArchivoLogistica lector) {
        System.out.println("1. Eliminar conexión\n2. Agregar conexión\n3. Cambiar clima (releer archivo)");
        String opcion = input.nextLine();

        switch (opcion) {
            case "1" -> {
                System.out.print("Ciudad origen: ");
                String origen = input.nextLine();
                System.out.print("Ciudad destino: ");
                String destino = input.nextLine();
                grafo.eliminarConexion(origen, destino);
            }
            case "2" -> {
                System.out.print("Ciudad origen: ");
                String origen = input.nextLine();
                System.out.print("Ciudad destino: ");
                String destino = input.nextLine();
                System.out.print("Tiempo normal: ");
                int tiempo = Integer.parseInt(input.nextLine());
                grafo.agregarConexion(origen, destino, tiempo);
            }
            case "3" -> {
                System.out.print("Nuevo clima: ");
                String clima = input.nextLine();
                grafo.reiniciar();
                try {
                    lector.leerArchivoDesdeTexto("logistica.txt", grafo, clima);
                } catch (Exception e) {
                    System.out.println("Error al leer archivo: " + e.getMessage());
                }
            }
        }
    }

    public static void mostrarMatriz(Grafo grafo) {
        System.out.println("Matriz de adyacencia:");
        List<String> ciudades = grafo.getCiudades();
        int[][] m = grafo.getMatriz();

        System.out.print("            ");
        for (String c : ciudades)
            System.out.printf("%12s", c);
        System.out.println();

        for (int i = 0; i < m.length; i++) {
            System.out.printf("%12s", ciudades.get(i));
            for (int j = 0; j < m.length; j++) {
                System.out.printf("%12s", (m[i][j] == Integer.MAX_VALUE) ? "INF" : m[i][j]);
            }
            System.out.println();
        }
    }
}
