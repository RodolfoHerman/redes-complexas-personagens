package br.com.rodolfo.redes.complexas.modelos;

import java.util.List;

/**
 * Grafo
 */
public class Grafo {

    private int[][] grafo;
    private final List<int[][]> grafos;
    private final int dimensao;

    public Grafo(int dimensao, List<int[][]> grafos) {

        this.grafos = grafos;
        this.dimensao = dimensao;
        this.grafo = new int[dimensao][dimensao];
    }

    public void construirGrafo() {

        for (int[][] g : grafos) {
            
            for(int x = 0; x < dimensao; x++) {

                for(int y = 0; y < dimensao; y++) {

                    this.grafo[x][y] += g[x][y];
                }
            }
        }
    }

    public int[][] getGrafo() {
        return this.grafo;
    }

}