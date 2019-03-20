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
    private ArrayList<Integer> prioridade;
    private ArrayList<Integer> distancia;
    
    public Fila()
    {
        fila = new ArrayList<Integer>();
        estado = new ArrayList<int[]>();
        prioridade = new ArrayList<Integer>();
        distancia = new ArrayList<Integer>();
        tl = 0;
    }
    
    public void queue(int num, int[] estado)
    {
        fila.add(num);
        prioridade.add(0);
        distancia.add(0);
        
        int[] aux = new int[estado.length];
        for (int i = 0; i < estado.length; i++)
            aux[i] = estado[i];
        
        this.estado.add(aux);
        tl++;
    }
    
    public void queue(int num, int[] estado, int priori, int dist)
    {
        fila.add(num);
        prioridade.add(priori);
        distancia.add(dist);
        
        int[] aux = new int[estado.length];
        for (int i = 0; i < estado.length; i++)
            aux[i] = estado[i];
        
        this.estado.add(aux);
        tl++;
    }
    
    public Object[] dequeue()
    {
        tl--;
        Object[] obj = new Object[4];
        
        obj[0] = fila.get(0);
        obj[1]  = estado.get(0);
        obj[2] = prioridade.get(0);
        obj[3] = distancia.get(0);
        fila.remove(0);
        estado.remove(0);
        prioridade.remove(0);
        distancia.remove(0);
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
    
    public int getPrimeiroPrioridade()
    {
        return prioridade.get(0);
    }
    
    public int getPrioridade(int pos)
    {
        return prioridade.get(pos);
    }
    
    public void setPrioridade(int pos, int valor)
    {
        prioridade.set(pos, valor);
    }
    
    private void ordenarPrioridade()
    {   
        int[] vet = new int[tl];
        int temp;
        
        for (int i = 0; i < prioridade.size(); i++)
            vet[i] = prioridade.get(i);
        
        for (int i = prioridade.size(); i > 1; i--) {
            for (int j = 0; j < prioridade.size() - 1; j++) {
                if(vet[j] > vet[j + 1])
                {
                    swap(j, j + 1);
                    temp = vet[j];
                    vet[j] = vet[j + 1];
                    vet[j + 1] = temp;
                }
            }
        }
        
    }
    
    public void ordenar()
    {
        ordenarDistancia();
        ordenarPrioridade();
    }
    
    private void ordenarDistancia()
    {
        int[] vet = new int[tl];
        int temp;
        
        for (int i = 0; i < tl; i++) {
            vet[i] = distancia.get(i);
        }
        
        for (int i = tl; i > 1; i--) {
            for (int j = 0; j < tl - 1; j++) {
                if(vet[j] > vet[j + 1])
                {
                    swap(j, j + 1);
                    temp = vet[j];
                    vet[j] = vet[j + 1];
                    vet[j + 1] = temp;
                }
            }
        }
    }
    
    private void swap(int pos1, int pos2)
    {
        int tempPriori = prioridade.get(pos1);
        int tempFila = fila.get(pos1);
        int tempDist = distancia.get(pos1);
        int[] tempEstado = pegarVetor(pos1);
        
        prioridade.set(pos1, prioridade.get(pos2));
        fila.set(pos1, fila.get(pos2));
        distancia.set(pos1, distancia.get(pos2));
        estado.set(pos1, pegarVetor(pos2));
        
        prioridade.set(pos2, tempPriori);
        fila.set(pos2, tempFila);
        distancia.set(pos2, tempDist);
        estado.set(pos2, tempEstado);
    }
    
    private int[] pegarVetor(int pos)
    {
        int[] res = new int[estado.get(pos).length];
        for (int i = 0; i < res.length; i++)
            res[i] = estado.get(pos)[i];
        
        return res;
    }
    
    //Ta um saco essa função, eu sei. Mas eu não to com vontade de pensar em uma solução para calcular a distância de maneira automática.
    
}
