package br.com.rodolfo.redes.complexas.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Util
 */
public class Util {

    private static final String _REGEX_ = "(?i).*?\\b%s\\b.*?";

    public static Map<String, Integer> transformarListaParaMapa(List<String> personagens) {
        
        Map<String, Integer> mapa = new HashMap<>();
        int posicao = 0;
        
        for (String personagem : personagens) {
            
            mapa.put(personagem, posicao++);
        }

        return mapa;
    }

    public static boolean contemPalavra(String texto, String palavra) {
        String regex = String.format(_REGEX_, Pattern.quote(palavra));
        return texto.matches(regex);
    }

    public static void imprimirGrafo(int[][] grafo) {
        
        int dimensao = grafo[0].length;

        for(int x = 0; x < dimensao; x++) {
            for(int y = 0; y < dimensao; y++) {

                System.out.print(grafo[x][y] + "\t");
            }
            System.out.println();
        }

    }

    public static List<String> normalizarNomesPersonagens(List<String> personagens) {
        
        List<String> novosNomes = new ArrayList<>();

        for (String personagem : personagens) {
            
            novosNomes.add(personagem.replace(" ", "-"));
        }

        return novosNomes;
    }

    public static List<String> converterGrafoParaPython(int[][] grafo, List<String> personagens) {
        
        int dimensao = grafo[0].length;
        List<String> grafoPython = new ArrayList<>();

        for(int x = 0; x < dimensao; x++) {
            
            String personagem_1 = personagens.get(x).replace(" ", "-");
            
            for(int y = (x+1); y < dimensao; y++) {

                String personagem_2 = personagens.get(y).replace(" ", "-");

                if(grafo[x][y] != 0) {
                    
                    String aux = personagem_1.concat(",").concat(personagem_2).concat(",").concat("" + grafo[x][y]);
                    grafoPython.add(aux);
                }
            }
        }

        return grafoPython;
    }

    private Util() {}

}