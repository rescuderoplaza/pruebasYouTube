package com.youtube.algoritmos.dijkstra;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class DijkstraExample {
	private static Logger log = Logger.getLogger(DijkstraExample.class);
	
    static final int V = 5; // N�mero de nodos en el grafo

    int minDistance(int dist[], Boolean sptSet[]) {
        int min = Integer.MAX_VALUE, min_index = -1; // Inicializaci�n de valores m�nimos

        for (int v = 0; v < V; v++)
            if (!sptSet[v] && dist[v] <= min) { // Verifica nodos no incluidos y distancias m�nimas
                min = dist[v];
                min_index = v; // Actualiza el �ndice del m�nimo
            }

        return min_index; // Retorna el �ndice del nodo con la m�nima distancia
    }

    void printSolution(int dist[]) {
        // Mapeo de nodos con sus �ndices para imprimir resultados
        Map<Integer, Character> nodes = new HashMap<>();
        nodes.put(0, 'A');
        nodes.put(1, 'B');
        nodes.put(2, 'C');
        nodes.put(3, 'D');
        nodes.put(4, 'E');

        log.debug("Distancias m�nimas desde el nodo fuente:");
        for (int i = 0; i < V; i++)
        	  log.debug("Nodo " + nodes.get(i) + ": " + dist[i]);
    }

    void dijkstra(int graph[][], int src) {
        int dist[] = new int[V]; // Arreglo para almacenar las distancias m�nimas
        Boolean sptSet[] = new Boolean[V]; // Conjunto de nodos con caminos m�s cortos

        Arrays.fill(dist, Integer.MAX_VALUE); // Inicializa todas las distancias en infinito
        Arrays.fill(sptSet, false); // Ning�n nodo est� en el conjunto de caminos m�s cortos

        dist[src] = 0; // La distancia al nodo fuente es 0

        for (int count = 0; count < V - 1; count++) {
            int u = minDistance(dist, sptSet); // Encuentra el nodo con la m�nima distancia
            sptSet[u] = true; // Marca el nodo como visitado en el conjunto de caminos m�s cortos

            for (int v = 0; v < V; v++)
                if (!sptSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v])
                    dist[v] = dist[u] + graph[u][v]; // Actualiza la distancia si se encuentra un camino m�s corto
        }

        printSolution(dist); // Imprime las distancias m�nimas
    }

    public static void main(String[] args) {
    	int graph[][] = new int[][] {
    	    // Matriz de adyacencia que representa el grafo
    	    // Nodo A (0) est� conectado con el nodo B (1) con peso 1 y nodo E (4) con peso 4
    	    {0, 1, 0, 0, 4},
    	    // Nodo B (1) est� conectado con el nodo A (0) con peso 1, nodo C (2) con peso 2
    	    {1, 0, 2, 0, 0},
    	    // Nodo C (2) est� conectado con el nodo B (1) con peso 2, nodo D (3) con peso 3
    	    {0, 2, 0, 3, 0},
    	    // Nodo D (3) est� conectado con el nodo C (2) con peso 3, nodo E (4) con peso 2
    	    {0, 0, 3, 0, 2},
    	    // Nodo E (4) est� conectado con el nodo A (0) con peso 4, nodo D (3) con peso 2
    	    {4, 0, 0, 2, 0}
    	};
        DijkstraExample dijkstraExample = new DijkstraExample(); // Instancia de la clase
        dijkstraExample.dijkstra(graph, 0); // Ejecuci�n del algoritmo de Dijkstra desde el nodo 0
    }
}
