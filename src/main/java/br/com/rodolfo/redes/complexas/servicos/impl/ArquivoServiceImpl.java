package br.com.rodolfo.redes.complexas.servicos.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.rodolfo.redes.complexas.servicos.ArquivoService;

/**
 * ArquivoServiceImpl
 */
public class ArquivoServiceImpl implements ArquivoService {

    private static final Logger log = LoggerFactory.getLogger(ArquivoServiceImpl.class);
    
    @Override
    public void salvarArquivoGrafo(String caminho, int[][] grafo) {

        log.info("Slavando o grafo no caminho : {}", caminho);

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(caminho))){
            
            int dimensao = grafo[0].length;

            for(int x = 0; x < dimensao; x++) {
                for(int y = 0; y < dimensao; y++) {

                    escritor.write(grafo[x][y] + "\t");
                }
                escritor.newLine();
            }
        
            escritor.flush();
        
        } catch (Exception e) {
            
            log.error("Erro : {}", e.getMessage());
        }
    }

    @Override
    public void salvarArquivoGrafoPython(String caminho, List<String> edges) {

        log.info("Slavando o grafo para python no caminho : {}", caminho);

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(caminho))){

            for (String edge : edges) {
                
                escritor.write(edge);
                escritor.newLine();
            }

            escritor.flush();

        } catch (Exception e) {
            
            log.error("Erro : {}", e.getMessage());
        }
    }

    @Override
    public List<String> buscarConteudoScript(String caminho) {
        
        log.info("Buscando script do filme no caminho : {}", caminho);
        
        List<String> script = new ArrayList<>();

        try (BufferedReader leitor = new BufferedReader(new FileReader(caminho))){

            String leitura;

            while((leitura = leitor.readLine()) != null ) {

                script.add(leitura);
            }

        } catch (Exception e) {
            
            log.error("Erro : {}", e.getMessage());
        }
        
        return script;
    }

    @Override
    public List<String> buscarListaPersonagens(String caminho) {
        
        log.info("Buscando a lista de personagens do filme no caminho : {}", caminho);
        
        Set<String> personagens = new HashSet<>();

        try (BufferedReader leitor = new BufferedReader(new FileReader(caminho))){

            String leitura;

            while((leitura = leitor.readLine()) != null ) {

                personagens.add(leitura.trim().toLowerCase());
            }

        } catch (Exception e) {
            
            log.error("Erro : {}", e.getMessage());
        }

        List<String> ordenada = new ArrayList<>(personagens);
        Collections.sort(ordenada);
        
        return ordenada;
    }

    @Override
    public List<String> buscarListaPersonagensSemApelido(String caminho_1, String caminho_2) {
		return null;
	}
    
}