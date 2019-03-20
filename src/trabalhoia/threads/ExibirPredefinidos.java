/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhoia.threads;

import java.util.ArrayList;
import javafx.application.Platform;
import trabalhoia.TelaPrincipalController;
import trabalhoia.estrutura.Algoritmos;

/**
 *
 * @author Henrique K. Secchi
 */
public class ExibirPredefinidos implements Runnable{
    
    private TelaPrincipalController tela;
    private Algoritmos algoritmos;
    private ArrayList<Integer> movimentos;

    public ExibirPredefinidos(TelaPrincipalController tela, Algoritmos algoritmos) {
        this.tela = tela;
        this.algoritmos = algoritmos;
        movimentos = tela.getListMovimentos();
    }
    
    @Override
    public void run() {
        try
        {
            for (int i = 0; i < movimentos.size(); i++) {
                algoritmos.mov(movimentos.get(i));
                tela.print();
                Thread.sleep(500);
            }
        }catch(Exception er)
        {
            System.out.println("Erro: " + er.getMessage());
        }
        tela.exibirProgress(false);
        Platform.runLater(() -> {
            tela.exibirResultados(213380, 3347, 7);
        });
    }
    
}
