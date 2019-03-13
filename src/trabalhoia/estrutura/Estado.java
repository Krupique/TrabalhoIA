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
public class Estado {
    private int[] estado;
    private ArrayList<Integer> visitados;
    
    public Estado(int[] estado)
    {
        this.estado = estado;
        visitados = new ArrayList<Integer>();
    }
    
    public void addVisitado(int pos)
    {
        visitados.add(pos);
    }
    
    public boolean foiVisitado(int pos)
    {
        int i = 0;
        while(i < visitados.size() && pos != visitados.get(i))
            i++;
        
        if(i < visitados.size())
            return true;
        return false;
    }
    
    public int[] getEstado()
    {
        return estado;
    }
}
