package br.com.rodolfo.redes.complexas.controle;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.rodolfo.redes.complexas.algoritmo.Algoritmo;
import br.com.rodolfo.redes.complexas.config.Configuracoes;
import br.com.rodolfo.redes.complexas.servicos.ArquivoService;
import br.com.rodolfo.redes.complexas.servicos.impl.ArquivoServiceImpl;

/**
 * Controle
 */
public class Controle {

    private ArquivoService arquivoService;
    private Configuracoes configuracoes;
    private String PROPERTIES = "config.properties";

    private static final Logger log = LoggerFactory.getLogger(Controle.class);

    public Controle() {

        log.info("Inicializando a aplicação.");

        arquivoService    = new ArquivoServiceImpl();
        configuracoes     = new Configuracoes();
        Properties prop   = new Properties();

        try (InputStream input = Controle.class.getClassLoader().getResourceAsStream(PROPERTIES)){
            
            prop.load(input);

            this.configuracoes.setArquivoPersonagens(prop.getProperty("arquivo.lista.personagens"));
            this.configuracoes.setArquivoSalvarGrafoPython(prop.getProperty("arquivo.salvar.grafo.python"));
            this.configuracoes.setArquivoSalvarGrafo(prop.getProperty("arquivo.salvar.grafo"));
            this.configuracoes.setArquivoScript(prop.getProperty("arquivo.script"));
            this.configuracoes.setArquivoApelidos(prop.getProperty("arquivo.apelidos"));

        } catch (Exception e) {
            
            log.error("Erro : {}", e.getMessage());
        }
    }

    public void executar() {
        
        List<String> script = arquivoService.buscarConteudoScript(configuracoes.getArquivoScript());
        List<String> personagens = arquivoService.buscarListaPersonagens(configuracoes.getArquivoPersonagens());

        log.info("Executando algoritmo.");

        Algoritmo algoritmo = new Algoritmo(script, personagens);
        algoritmo.realizarParse();
        algoritmo.extrairGrafo();

        arquivoService.salvarArquivoGrafo(configuracoes.getArquivoSalvarGrafo(), algoritmo.getGrafo());
        arquivoService.salvarArquivoGrafoPython(configuracoes.getArquivoSalvarGrafoPython(), algoritmo.getGrafoPython());
    }
    
}