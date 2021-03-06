package br.com.rodolfo.redes.complexas.algoritmo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

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
    private final boolean falasPersongam;
    private int[][] grafoFinal;

    public Algoritmo(List<String> script, List<String> personagens, boolean falasPersongam) {

        this.script = script;
        this.personagens = personagens;
        this.falasPersongam = falasPersongam;
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
                    
                    if(falasPersongam) {

                        aux.addAll(extrairPersonagens(scriptTrecho));

                    } else {

                        //Por ser script bem estruturado, quando é linha de nome do personagem podem conter até 5 palavras
                        String[] trecho = scriptTrecho.trim().split(" ");
                        //Por ser script bem estruturado, quando é linha de nome pode começar com 20 até 23 espaços
                        int ocorrencia  = StringUtils.countMatches(scriptTrecho, " ");

                        // if(trecho.length <= 5 && ocorrencia >= 20) {
                        if(trecho.length <= 5) {

                            aux.addAll(extrairPersonagens(scriptTrecho));
                        }
                    }

                    scriptTrecho = script.get(++contador);

                } while (contador < (script.size()-1) && !palavraChave(scriptTrecho));

                cenas.add(new Cena(numCena++, new ArrayList<>(aux)));

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