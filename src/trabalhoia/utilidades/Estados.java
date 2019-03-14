/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhoia.utilidades;

import java.util.ArrayList;

/**
 *
 * @author Henrique K. Secchi
 */
public class Estados {
    
    private ArrayList<Estado> estados;
    
    public Estados()
    {
        estados = new ArrayList<Estado>();
    }
    
    public void addEstado(int[] estado)
    {
        estados.add(new Estado(estado));
    }
    
    public void addVisitado(int[] estado, int pos)
    {
        int flag = estaContido(estado);
        estados.get(flag).addVisitado(pos);
    }
    
    private int estaContido(int[] estado)
    {
        int[] aux;
        for (int i = 0; i < estados.size(); i++) {
            aux = estados.get(i).getEstado();
            
            int j = 0;
            while(j < estado.length && estado[j] == aux[j])
                j++;
            
            if(!(j < estado.length))
                return i;
        }
        return -1;
    }
    
    public boolean foiVisitado(int[] estado, int pos)
    {
        int flag = estaContido(estado);
        if(flag == -1)
            return false;
        else
            return estados.get(flag).foiVisitado(pos);
    }
    
    public boolean contem(int[] estado)
    {
        return estaContido(estado) != -1;
    }
}
