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
public class Estados {
    
    private ArrayList<int[]> estados;
    private ArrayList<int[]> visitados;
    
    public Estados()
    {
        estados = new ArrayList<int[]>();
        visitados = new ArrayList<int[]>();
    }
    
    public void addEstado(int[] estado, Fila vizinhos)
    {
        estados.add(estado);
        int i = 0;
        
        int[] vet = new int[vizinhos.getTL()];
        while(!vizinhos.isEmpty())
            vet[i++] = (int)vizinhos.dequeue()[0];
        
        visitados.add(vet);
            
    }
    
    public int foiVisitado(int[] estado)
    {
        int i = 0;
        boolean flag = true;
        int achou = -1;
        while(i < estados.size() && achou == -1)
        {
            int j = 0;
            flag = true;
            while(j < estado.length && flag)
            {
                if(estado[j] != estados.get(i)[j])
                    flag = false;
                j++;
            }
            
            if(flag)
                achou = i;
            
            i++;
        }
        
        return achou;
    }
    
    public boolean vizinhoVisitado(int[] estado, int vizi)
    {
        int pos = foiVisitado(estado);
        if(pos != -1)
        {  
            int[] f = visitados.get(pos);
            int i = 0;
            while(i < f.length && vizi != f[i])
                i++;

            if(i < f.length)
                return true;
        }
        return false;
    }
    
    public int getTLVizinhos(int[] estado)
    {
        int pos = foiVisitado(estado);
        if(pos != -1)
            return visitados.get(pos).length;
        return -1;
    }
    
    public void removerVizinho(int[] estado, int vizi)
    {
        int pos = foiVisitado(estado);
        if(pos != -1)
        {
            int[] f = visitados.get(pos);

            int i = 0;
            while(i < f.length && vizi != f[i])
                i++;

            if(i < f.length)
            {
                f = remaneja(f, i);
                visitados.set(pos, f);
            }
        }
        
    }
    
    private int[] remaneja(int[] f, int i)
    {
        int[] vet = new int[f.length - 1];
        int j = 0, k = 0;
        while(j < vet.length)
        {
            vet[j++] = j == i ? f[k] : f[++k];
        }
        return vet;
    }
    
    public int[] getEstado(int pos)
    {
        return estados.get(pos);
    }
}
