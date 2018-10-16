package br.com.rodolfo.redes.complexas.algoritmo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.com.rodolfo.redes.complexas.modelos.Cena;
import br.com.rodolfo.redes.complexas.modelos.Grafo;
import br.com.rodolfo.redes.complexas.util.Util;

/**
 * Algoritmo
 */
public class Algoritmo {

    private final List<String> script;
    private final List<String> personagens;
    private final List<Cena> cenas;
    private int[][] grafoFinal;

    public Algoritmo(List<String> script, List<String> personagens) {

        this.script = script;
        this.personagens = personagens;
        this.cenas = new ArrayList<>();
    }

    public void realizarParse() {

        String scriptTrecho;
        int contador = 0;
        int numCena  = 1;

        for(int x = 0; x < script.size(); x++) {

            scriptTrecho = this.script.get(contador++);

            if(palavraChave(scriptTrecho)) {

                Set<String> aux = new HashSet<>();
                scriptTrecho = script.get(contador);

                do {
                    
                    aux.addAll(extrairPersonagens(scriptTrecho));
                    scriptTrecho = script.get(++contador);

                } while (contador < (script.size()-1) && !palavraChave(scriptTrecho));

                cenas.add(new Cena(numCena, new ArrayList<>(aux)));
                numCena++;
                //contador--;
                x = contador;
            }
        }

        extrairGrafo();
    }

    private void extrairGrafo() {
        
        List<int[][]> grafos = new ArrayList<>();
        Map<String, Integer> mapa = Util.transformarListaParaMapa(personagens);

        for (Cena cena : cenas) {

            grafos.add(cena.criarGrafo(personagens.size(), mapa));
        }

        Grafo grafo = new Grafo(personagens.size(), grafos);
        grafo.construirGrafo();

        this.grafoFinal = grafo.getGrafo();
    }

    private Set<String> extrairPersonagens(String scriptTrecho) {

        Set<String> resp = new HashSet<>();

        for (String personagem : personagens) {
            
            if(Util.contemPalavra(scriptTrecho, personagem)) {

                resp.add(personagem);
            }
        }

        return resp;
    }

    private boolean palavraChave(String scriptTrecho) {
        return Util.contemPalavra(scriptTrecho, "EXT") || Util.contemPalavra(scriptTrecho, "INT");
    }

    public int[][] getGrafo() {
        return this.grafoFinal;
    }

    public List<String> getGrafoPython() {
        return Util.converterGrafoParaPython(this.grafoFinal, personagens);
    }

    public List<String> getPersonagens() {
        return this.personagens;
    }

}