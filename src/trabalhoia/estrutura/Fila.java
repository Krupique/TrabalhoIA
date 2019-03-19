/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhoia.estrutura;

import java.util.ArrayList;

/**
 *
 * @author Henrique K. Secchi
 */
public class Fila {
    private ArrayList<Integer> fila;
    private ArrayList<int[]> estado;
    private int tl;
    
    public Fila()
    {
        fila = new ArrayList<Integer>();
        estado = new ArrayList<int[]>();
        tl = 0;
    }
    
    public void queue(int num, int[] estado)
    {
        fila.add(num);
        int[] aux = new int[estado.length];
        for (int i = 0; i < estado.length; i++)
            aux[i] = estado[i];
        
        this.estado.add(aux);
        tl++;
    }
    
    public Object[] dequeue()
    {
        tl--;
        Object[] obj = new Object[2];
        
        obj[0] = fila.get(0);
        obj[1]  = estado.get(0);
        fila.remove(0);
        estado.remove(0);
        return obj;
    }
    
    public int getPrimeiroFila()
    {
        return fila.get(0);
    }
    
    public int[] getPrimeiroEstado()
    {
        return estado.get(0);
    }
    
    public int getTL()
    {
        return tl;
    }
    
    public boolean isEmpty()
    {
        return tl == 0;
    }
}
