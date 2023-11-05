package com.youtube.algoritmos.dijkstra;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class DijkstraExample {
	private static Logger log = Logger.getLogger(DijkstraExample.class);
	
    static final int V = 5; // Número de nodos en el grafo

    int minDistance(int dist[], Boolean sptSet[]) {
        int min = Integer.MAX_VALUE, min_index = -1; // Inicialización de valores mínimos

        for (int v = 0; v < V; v++)
            if (!sptSet[v] && dist[v] <= min) { // Verifica nodos no incluidos y distancias mínimas
                min = dist[v];
                min_index = v; // Actualiza el índice del mínimo
            }

        return min_index; // Retorna el índice del nodo con la mínima distancia
    }

    void printSolution(int dist[]) {
        // Mapeo de nodos con sus índices para imprimir resultados
        Map<Integer, Character> nodes = new HashMap<>();
        nodes.put(0, 'A');
        nodes.put(1, 'B');
        nodes.put(2, 'C');
        nodes.put(3, 'D');
        nodes.put(4, 'E');

        log.debug("Distancias mínimas desde el nodo fuente:");
        for (int i = 0; i < V; i++)
        	  log.debug("Nodo " + nodes.get(i) + ": " + dist[i]);
    }

    void dijkstra(int graph[][], int src) {
        int dist[] = new int[V]; // Arreglo para almacenar las distancias mínimas
        Boolean sptSet[] = new Boolean[V]; // Conjunto de nodos con caminos más cortos

        Arrays.fill(dist, Integer.MAX_VALUE); // Inicializa todas las distancias en infinito
        Arrays.fill(sptSet, false); // Ningún nodo está en el conjunto de caminos más cortos

        dist[src] = 0; // La distancia al nodo fuente es 0

        for (int count = 0; count < V - 1; count++) {
            int u = minDistance(dist, sptSet); // Encuentra el nodo con la mínima distancia
            sptSet[u] = true; // Marca el nodo como visitado en el conjunto de caminos más cortos

            for (int v = 0; v < V; v++)
                if (!sptSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v])
                    dist[v] = dist[u] + graph[u][v]; // Actualiza la distancia si se encuentra un camino más corto
        }

        printSolution(dist); // Imprime las distancias mínimas
    }

    public static void main(String[] args) {
    	int graph[][] = new int[][] {
    	    // Matriz de adyacencia que representa el grafo
    	    // Nodo A (0) está conectado con el nodo B (1) con peso 1 y nodo E (4) con peso 4
    	    {0, 1, 0, 0, 4},
    	    // Nodo B (1) está conectado con el nodo A (0) con peso 1, nodo C (2) con peso 2
    	    {1, 0, 2, 0, 0},
    	    // Nodo C (2) está conectado con el nodo B (1) con peso 2, nodo D (3) con peso 3
    	    {0, 2, 0, 3, 0},
    	    // Nodo D (3) está conectado con el nodo C (2) con peso 3, nodo E (4) con peso 2
    	    {0, 0, 3, 0, 2},
    	    // Nodo E (4) está conectado con el nodo A (0) con peso 4, nodo D (3) con peso 2
    	    {4, 0, 0, 2, 0}
    	};
        DijkstraExample dijkstraExample = new DijkstraExample(); // Instancia de la clase
        dijkstraExample.dijkstra(graph, 0); // Ejecución del algoritmo de Dijkstra desde el nodo 0
    }
}
