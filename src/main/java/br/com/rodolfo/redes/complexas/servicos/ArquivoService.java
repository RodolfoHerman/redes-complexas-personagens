package br.com.rodolfo.redes.complexas.servicos;

import java.util.List;

/**
 * ArquivoService
 */
public interface ArquivoService {

    /**
     * Salva arquivo de dados do grafo
     * 
     * @param caminho
     * @param dados
     */
    void salvarArquivoGrafo(String caminho, int[][] grafo);

    /**
     * Salva arquivo de dados do grafo para o python
     * 
     * @param caminho
     * @param edges
     */
    void salvarArquivoGrafoPython(String caminho, List<String> edges);

    /**
     * Criar o arquivo xml para o programa GEPHI (formato .gexf)
     * 
     * @param caminho
     * @param edges
     * @param listaPersonagens
     */
    void salvarArquivoGephi(String caminho, List<String> edges, List<String> listaPersonagens);

    /**
     * Retorna o script do filme em uma lista de strings
     * 
     * @param caminho
     * @return List<String>
     */
    List<String> buscarConteudoScript(String caminho);

    /**
     * Retorna a lista de personagens do filme
     * 
     * @param caminho
     * @return List<String>
     */
    List<String> buscarListaPersonagens(String caminho);

    /**
     * Retorna lista de personagens trocando os apelidos pelo nome do personagem
     * 
     * @param caminho_1
     * @param caminho_2
     * @return List<String>
     */
    List<String> buscarConteudoScriptSemApelido(String caminho_1, String caminho_2);

}