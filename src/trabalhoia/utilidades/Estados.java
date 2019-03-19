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
    
    private ArrayList<int[]> estados;
    
    public Estados()
    {
        estados = new ArrayList<int[]>();
    }
    
    public void addEstado(int[] estado)
    {
        estados.add(estado);
    }
    
    public boolean foiVisitado(int[] estado)
    {
        int i = 0;
        boolean flag = true;
        boolean achou = false;
        while(i < estados.size() && !achou)
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
                achou = true;
            
            i++;
        }
        
        return achou;
    }
    
    
    public int[] getEstado(int pos)
    {
        return estados.get(pos);
    }
}
