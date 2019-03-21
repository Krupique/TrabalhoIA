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
import trabalhoia.estrutura.Fila;
import trabalhoia.utilidades.Estados;

/**
 *
 * @author Henrique K. Secchi
 */
public class BestFirstThread implements Runnable{
    private TelaPrincipalController tela;
    private Algoritmos algoritmos;
    private ArrayList<Integer> movimentos;
    private int mov;
            
    public BestFirstThread(TelaPrincipalController tela, Algoritmos algoritmos) {
        this.tela = tela;
        this.algoritmos = algoritmos;
        movimentos = new ArrayList<>();
        
    }
    
    @Override
    public void run() {
        try
        {
            buscaHeuristica();
            System.out.println("Movimentos: " + mov);
        }catch(Exception er)
        {
            System.out.println("Erro: " + er.getMessage());
        }
        
        tela.exibirProgress(false);
        
        Platform.runLater(() -> {
            tela.exibirBuscaHeuristica(mov);
        });
    }
    
    /*Best First - Busca Heurística*/
    public boolean buscaHeuristica()
    {
        mov = 1;
        algoritmos.setVisitados(new Estados());
        Fila fila = new Fila();
        int[] estado = algoritmos.getEstado();
        boolean res = bf(fila);
        algoritmos.setEstado(estado);
        return res;
    }
    
    public boolean bf(Fila fila) //Best First
    {
        if((!fila.isEmpty() && algoritmos.getVisitados().vizinhoVisitado(algoritmos.getEstado(), fila.getPrimeiroFila())))
        {
            return false;
        }
        else
        {
            if(algoritmos.validarPecas()) //Retorna se posição for válida.
                return true;
            else
            {
                if(!fila.isEmpty()) //Retira o primeiro elemento da fila, salvo a primeira vez que entrar no algoritmo.
                {
                    Object[] faux = fila.dequeue();
                    algoritmos.setEstado((int[])faux[1]);
                    algoritmos.mov((int)faux[0]);
                    
                    algoritmos.getVisitados().removerVizinho((int[])faux[1], (int)faux[0]);
                    mov++;
                }
                
                int[] atual = algoritmos.getEstado(); //Pega o estado atual e adiciona aos visitados.
                Fila faux1 = new Fila();
                Fila faux2 = new Fila();
                faux1 = algoritmos.pegarVizinhos(faux1, algoritmos.getBandeira()); //Adiciona todas as possibilidades de movimentos.
                faux2 = algoritmos.pegarVizinhos(faux2, algoritmos.getBandeira()); //Adiciona todas as possibilidades de movimentos.
                algoritmos.getVisitados().addEstado(atual, faux2);
                
                Object[] obj;
                while(!faux1.isEmpty())
                {
                    obj = faux1.dequeue();
                    algoritmos.setEstado((int[])obj[1]);
                    algoritmos.mov((int)obj[0]);
                    int pecasFora = algoritmos.getPecasForaDoLugar();
                    int dist = algoritmos.getDistanciaTotal();
                    
                    fila.queue((int)obj[0], (int[])obj[1], pecasFora, dist);
                }
                fila.ordenar();
                
                algoritmos.setEstado(fila.getPrimeiroEstado());
                algoritmos.mov(fila.getPrimeiroFila());
                
                
                return bf(fila);
            }
        }
    }
}
