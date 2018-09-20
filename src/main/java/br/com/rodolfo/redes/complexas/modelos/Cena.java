package br.com.rodolfo.redes.complexas.modelos;

import java.util.List;
import java.util.Map;

/**
 * Cena
 */
public class Cena {

    private final int cena;
    private final List<String> personagens;

    public Cena(int cena, List<String> personagens) {

        this.cena = cena;
        this.personagens = personagens;
    }

    public int[][] criarGrafo(int dimensao, Map<String, Integer> personagensPosicao) {

        int[][] grafo = new int[dimensao][dimensao];

        for (String personagem_1 : personagens) {
            
            int posicao_x = personagensPosicao.get(personagem_1);

            for (String personagem_2 : personagens) {
                
                int posicao_y = personagensPosicao.get(personagem_2);

                if(posicao_x != posicao_y) {

                    grafo[posicao_x][posicao_y] = 1;
                }
            }
        }

        return grafo;
    }

    public int getCena() {
        return this.cena;
    }

    @Override
    public String toString() {
        return "Cena : [cena = " + cena + ", personagens = " + personagens + "]";
    }
}