package br.com.rodolfo.redes.complexas.servicos.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.rodolfo.redes.complexas.servicos.ArquivoService;

/**
 * ArquivoServiceImpl
 */
public class ArquivoServiceImpl implements ArquivoService {

    private static final Logger log = LoggerFactory.getLogger(ArquivoServiceImpl.class);
    private static final File raiz = new File("");
    
    @Override
    public void salvarArquivoGrafo(String caminho, int[][] grafo) {

        log.info("Slavando o grafo no caminho : {}", caminho);

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(raiz.getAbsolutePath().concat(caminho)))){

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
            System.exit(1);
        }
    }

    @Override
    public void salvarArquivoGrafoPython(String caminho, List<String> edges) {

        log.info("Slavando o grafo para python no caminho : {}", caminho);

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(raiz.getAbsolutePath().concat(caminho)))){

            for (String edge : edges) {
                
                escritor.write(edge);
                escritor.newLine();
            }

            escritor.flush();

        } catch (Exception e) {
            
            log.error("Erro : {}", e.getMessage());
            System.exit(1);
        }
    }

    @Override
    public void salvarArquivoGephi(String caminho, String grafoGephi) {

        log.info("Slavando o grafo para GEPHI no caminho : {}", caminho);

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(raiz.getAbsolutePath().concat(caminho)))){

            escritor.write(grafoGephi);
            escritor.flush();

        } catch (Exception e) {
            
            log.error("Erro : {}", e.getMessage());
            System.exit(1);
        }

    }

    @Override
    public List<String> buscarConteudoScript(String caminho) {
        
        log.info("Buscando script do filme no caminho : {}", caminho);
        
        List<String> script = new ArrayList<>();

        try (BufferedReader leitor = new BufferedReader(new FileReader(raiz.getAbsolutePath().concat(caminho)))){

            String leitura;

            while((leitura = leitor.readLine()) != null ) {

                script.add(StringUtils.stripAccents(leitura.toLowerCase()));
            }

        } catch (Exception e) {
            
            log.error("Erro : {}", e.getMessage());
            System.exit(1);
        }
        
        return script;
    }

    @Override
    public List<String> buscarListaPersonagens(String caminho) {
        
        log.info("Buscando a lista de personagens do filme no caminho : {}", caminho);
        
        Set<String> personagens = new HashSet<>();
        
        try (BufferedReader leitor = new BufferedReader(new FileReader(raiz.getAbsolutePath().concat(caminho)))){
            
            String leitura;

            while((leitura = leitor.readLine()) != null ) {

                personagens.add(StringUtils.stripAccents(leitura.toLowerCase()));
            }

        } catch (Exception e) {
            
            log.error("Erro : {}", e.getMessage());
            System.exit(1);
        }

        List<String> ordenada = new ArrayList<>(personagens);
        Collections.sort(ordenada);
        
        return ordenada;
    }

    @Override
    public List<String> buscarConteudoScriptSemApelido(String caminho_1, String caminho_2) {
        
        log.info("Buscando script do filme no caminho : {}, arquivo substituir apelidos no caminho : {}", caminho_1, caminho_2);
        
        List<String> script = new ArrayList<>();
        List<String[]> substituicao = new ArrayList<>();
        
        try(BufferedReader leitor = new BufferedReader(new FileReader(raiz.getAbsolutePath().concat(caminho_2)))) {
            
            String leitura;
            
            while((leitura= leitor.readLine()) != null) {
                
                String[] aux = leitura.split("~");

                substituicao.add(new String[]{
                    StringUtils.stripAccents(aux[0].toLowerCase()),
                    StringUtils.stripAccents(aux[1].toLowerCase())
                });
            }
            
        } catch (Exception e) {
            
            log.error("Erro : {}", e.getMessage());
            System.exit(1);
        }
        
        try (BufferedReader leitor = new BufferedReader(new FileReader(raiz.getAbsolutePath().concat(caminho_1)))){

            String leitura;

            while((leitura = leitor.readLine()) != null ) {

                for(String[] termos : substituicao) {
                
                    leitura = leitura.replaceAll("(?i)" + termos[0], termos[1]);
                }
                
                script.add(StringUtils.stripAccents(leitura.toLowerCase()));
            }

        } catch (Exception e) {
            
            log.error("Erro : {}", e.getMessage());
            System.exit(1);
        }
        
        return script;
    }
    
    
}