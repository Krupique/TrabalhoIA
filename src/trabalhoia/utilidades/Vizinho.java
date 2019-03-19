/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhoia.utilidades;

import java.util.ArrayList;
import trabalhoia.estrutura.Fila;

/**
 *
 * @author Henrique K. Secchi
 */
public class Vizinho {
    private int[] estado;
    private Fila vizinhos;

    public Vizinho(int[] estado) {
        this.estado = estado;
        vizinhos = new Fila();
    }
    
    public Vizinho(int[] estado, Fila vizinhos) {
        this.estado = estado;
        this.vizinhos = new Fila();
        this.vizinhos = vizinhos;
    }

    public int[] getEstado() {
        return estado;
    }

    public Fila getVizinhos() {
        return vizinhos;
    }

    public void setVizinhos(Fila vizinhos) {
        //Fica o adendo aqui, pode ser que de pau ao atribuir direto
        this.vizinhos = vizinhos;
    }
    
    public int dequeueVizinho()
    {
        //return vizinhos.dequeue();
        return 1;
    }
    
    public boolean isEmptyVizinhos()
    {
        return vizinhos.isEmpty();
    }
    
    public int getTopVizinho()
    {
        //return vizinhos.getPrimeiro();
        return 1;
    }
   
}
