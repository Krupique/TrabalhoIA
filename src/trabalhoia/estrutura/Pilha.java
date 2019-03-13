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
public class Pilha {
    private ArrayList<Integer> pilha;
    private int tl;
    
    public Pilha()
    {
        tl = -1;
        pilha = new ArrayList<Integer>();
    }
    
    public int getTopoLista()
    {
        if(!isEmpty())
            return pilha.get(tl);
        return -9999;
    }
    
    public int pop()
    {
        if(!isEmpty())
        {
            int num = pilha.get(tl);
            pilha.remove(tl--);
            return num;
        }
        return -9999;
    }
    
    public void push(int num)
    {
        ++tl;
        pilha.add(num);
    }
    
    public int getSize()
    {
        return tl;
    }
    
    public boolean isEmpty()
    {
        return tl == -1;
    }
}
