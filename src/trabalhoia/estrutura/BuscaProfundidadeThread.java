/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhoia.estrutura;

import java.util.ArrayList;
import trabalhoia.TelaPrincipalController;

/**
 *
 * @author Henrique K. Secchi
 */
public class BuscaProfundidadeThread implements Runnable{
    private TelaPrincipalController tela;
    private Algoritmos algoritmos;
    private ArrayList<Integer> movimentos;
    private int profundidade;

    public BuscaProfundidadeThread(TelaPrincipalController tela, Algoritmos algoritmos) {
        this.tela = tela;
        this.algoritmos = algoritmos;
        profundidade = 0;
        movimentos = new ArrayList<>();
    }
    
    @Override
    public void run() {
        
        try
        {
            if(buscaProfundidade())
            {
                System.out.println("Conseguiu");
                algoritmos.setBandeira(algoritmos.getBandeira());
                
                for (int i = 0; i < movimentos.size(); i--) { //A ideia é pegar todos os movimentos no array de movimentos e exibir só os movimentos utilizados para completar o desafio.
                    try
                    {
                        tela.print();
                        algoritmos.mov(movimentos.get(i));
                        Thread.sleep(300);
                    }catch(Exception er){}
                }
                tela.print();
            }
            else
                System.out.println("Nao conseguiu!");
            
            
        }catch(Exception er)
        {
            System.out.println("Erro: " + er.getMessage());
        }
            tela.exibirProgress(false);
    }
    
    public boolean buscaProfundidade()
    {
        /*
            A idéia desta busca é pegar o primeiro nó na sequência e ir buscando.
            Se chegar no final e não for o desejado, retrocede uma etapa (backtracking)
            e pega o segundo caminho. Vai retrocedendo até achar o resultado procurado.
        */
        
        profundidade = 0;
        
        //movimentos = new ArrayList<>();
        int[] estadoAtual = algoritmos.getEstado(); //Pega o estado atual das peças.
        boolean res = dfs(algoritmos.getBandeira(), profundidade);
        algoritmos.setEstado(estadoAtual); //Volta ao estado antes de entrar no algoritmo. Para ir fazendo passo a passo.
        
        return res;
    }
    
    private boolean dfs(int bandeira, int profundidade)
    {   
        //System.out.println("Profundidade: " + profundidade);
        if(profundidade > 20)
            return false;
        else
        {
            if(algoritmos.validarPecas())
                return true;

            Pilha possib = new Pilha();
            possib = algoritmos.pegarVizinhos(possib, bandeira);

            while(!possib.isEmpty())
            {
                int rota = possib.pop();
                int[] atual = algoritmos.getEstado();

                algoritmos.mov(rota);
                if (dfs(rota, ++profundidade))
                {
                    //movimentos.add(rota);
                    return true;
                }
                else
                {
                    //movimentos.remove(movimentos.size() - 1);
                    algoritmos.setEstado(atual);
                }
            }
            
            System.out.println("Chegou aqui");
            return false;
        }
        
    }
}
