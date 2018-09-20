package br.com.rodolfo.redes.complexas;

import br.com.rodolfo.redes.complexas.controle.Controle;

/**
 * Hello world!
 */
public final class App {

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        
        Controle controle = new Controle();
        controle.executar();

    }
}
