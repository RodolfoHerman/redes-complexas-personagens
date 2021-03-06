package br.com.rodolfo.redes.complexas.config;

/**
 * Configuracoes
 */
public class Configuracoes {

    private String arquivoSalvarGrafoPython;
    private String arquivoSalvarGrafo;
    private String arquivoSalvarGephi;
    private String arquivoScript;
    private String arquivoPersonagens;
    private String arquivoApelidos;
    private boolean utilizarArquivoApelidos;
    private boolean falasPersonagem;

    public Configuracoes() {}

    /**
     * @return String return the arquivoSalvarGrafo
     */
    public String getArquivoSalvarGrafo() {
        return arquivoSalvarGrafo;
    }

    /**
     * @param arquivoSalvarGrafo the arquivoSalvarGrafo to set
     */
    public void setArquivoSalvarGrafo(String arquivoSalvarGrafo) {
        this.arquivoSalvarGrafo = arquivoSalvarGrafo;
    }

    public String getArquivoSalvarGephi() {
        return this.arquivoSalvarGephi;
    }

    public void setArquivoSalvarGephi(String arquivoSalvarGephi) {
        this.arquivoSalvarGephi = arquivoSalvarGephi;
    }

    /**
     * @return String return the arquivoScript
     */
    public String getArquivoScript() {
        return arquivoScript;
    }

    /**
     * @param arquivoScript the arquivoScript to set
     */
    public void setArquivoScript(String arquivoScript) {
        this.arquivoScript = arquivoScript;
    }

    /**
     * @return String return the arquivoPersonagens
     */
    public String getArquivoPersonagens() {
        return arquivoPersonagens;
    }

    /**
     * @param arquivoPersonagens the arquivoPersonagens to set
     */
    public void setArquivoPersonagens(String arquivoPersonagens) {
        this.arquivoPersonagens = arquivoPersonagens;
    }

    /**
     * @return String return the arquivoApelidos
     */
    public String getArquivoApelidos() {
        return arquivoApelidos;
    }

    /**
     * @param arquivoApelidos the arquivoApelidos to set
     */
    public void setArquivoApelidos(String arquivoApelidos) {
        this.arquivoApelidos = arquivoApelidos;
    }


    /**
     * @return String return the arquivoSalvarGrafoPython
     */
    public String getArquivoSalvarGrafoPython() {
        return arquivoSalvarGrafoPython;
    }

    /**
     * @param arquivoSalvarGrafoPython the arquivoSalvarGrafoPython to set
     */
    public void setArquivoSalvarGrafoPython(String arquivoSalvarGrafoPython) {
        this.arquivoSalvarGrafoPython = arquivoSalvarGrafoPython;
    }
    
    public boolean isUtilizarArquivoApelidos() {
        return utilizarArquivoApelidos;
    }

    public void setUtilizarArquivoApelidos(boolean utilizarArquivoApelidos) {
        this.utilizarArquivoApelidos = utilizarArquivoApelidos;
    }

    public boolean getFalasPersonagem() {
        return this.falasPersonagem;
    }

    public void setFalasPersonagem(boolean falasPersonagem) {
        this.falasPersonagem = falasPersonagem;
    }

}