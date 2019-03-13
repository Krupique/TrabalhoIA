/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhoia.estrutura;

import java.util.ArrayList;
import java.util.Collections;
import javafx.scene.image.Image;
import trabalhoia.utilidades.Divisor;

/**
 * @author Henrique K. Secchi
 */
public class Algoritmos {
    private Image imgPrincipal;
    private Imagem[] imgs; //Vetor com todas as imagens (imagem, id, pos correta, flag)
    private int bandeira; //Indica qual é a posição nula (sem imagem).
    private Pilha pilha;
    private ArrayList<Integer> array;
    private Estados estados;
    
    public Algoritmos(Image img, int flag)
    {
        imgPrincipal = img;
        imgs = new Imagem[9];
        
        Image[] aux = Divisor.converter(img);
        //Adicionando as imagens cruas na classe Imagem(Contem mais detalhes sobre a imagem).
        imgs[0] = new Imagem(aux[0], 0, true);
        imgs[1] = new Imagem(aux[3], 1, true);
        imgs[2] = new Imagem(aux[6], 2, true);
        imgs[3] = new Imagem(aux[1], 3, true);
        imgs[4] = new Imagem(aux[4], 4, true);
        imgs[5] = new Imagem(aux[7], 5, true);
        imgs[6] = new Imagem(aux[2], 6, true);
        imgs[7] = new Imagem(aux[5], 7, true);
        imgs[8] = new Imagem(aux[8], 8, true);
        
        bandeira = flag - 1;
        imgs[bandeira].setFlag(false);
        imgs[bandeira].setImg(null);
    }

    public Image getImgPrincipal() {
        return imgPrincipal;
    }

    public void setImgPrincipal(Image img) {
        this.imgPrincipal = img;
    }

    public Image[] getImgs() {
        Image[] vet = new Image[9];
        for (int i = 0; i < 9; i++)
            vet[i] = imgs[i].getImg();
        
        return vet;
    }
    
    public Image getImgPos(int pos)
    {
        //Lembrar que os imageview estão numerados de 1..9
        //E o vetor está de 0..8
        return imgs[pos].getImg();
    }
    
    //Embaralhar do jeito que o parkour mencionou.
    public Image[] embaralhar()
    {
        ArrayList<Imagem> array = new ArrayList<Imagem>();
        for (int i = 0; i < 9; i++)
            array.add(imgs[i]);
        
        Collections.shuffle(array);
        
        Image[] vet = new Image[9];
        for (int i = 0; i < 9; i++)
        {
            imgs[i] = array.get(i);
            vet[i] = imgs[i].getImg();
            
            if(!imgs[i].getFlag())
                bandeira = i;
        }
        
        System.out.println("BANDEIRA: " + bandeira);
        
        return vet;
    }
    
    public Image[] ordenar()
    {
        ArrayList<Imagem> temp = new ArrayList<Imagem>();
        for (int i = 0; i < 9; i++)
            temp.add(imgs[i]);
        
        Image[] vet = new Image[9];
        for (int i = 0; i < 9; i++)
        {
            imgs[temp.get(i).getId()] = temp.get(i);
            vet[i] = imgs[i].getImg();
            
            if(!imgs[i].getFlag())
                bandeira = i;
        }
        
        return vet;
    }
    
    public int getBandeira()
    {
        int i = 0;
        while(i < 9 && imgs[i].getFlag())
            i++;
        return i;
    }
    
    public boolean movimentar(int pos)
    {
        return mov(--pos);
    }
    
    private boolean mov(int pos)
    {
        if(isPossible(pos))
        {
            Imagem temp = imgs[bandeira];
            imgs[bandeira] = imgs[pos];
            imgs[pos] = temp;
            bandeira = pos;
            return true;
        }
        else 
            return false;
    }
    
    private boolean isPossible(int pos)
    {
        boolean flag = false;
        switch(pos)
        {
            case 0:
                flag = bandeira == 1 || bandeira == 3 ? true : false;
            break;
            
            case 1:
                flag = bandeira == 0 || bandeira == 2 || bandeira == 4 ? true : false;
            break;
            
            case 2:
                flag = bandeira == 1 || bandeira == 5 ? true : false;
            break;
            
            case 3:
                flag = bandeira == 0 || bandeira == 4 || bandeira == 6 ? true: false;
            break;
            
            case 4:
                flag = bandeira == 1 || bandeira == 3 || bandeira == 5 || bandeira == 7 ? true : false;
            break;
            
            case 5:
                flag = bandeira == 2 || bandeira == 4 || bandeira == 8 ? true : false;
            break;
            
            case 6:
                flag = bandeira == 3 || bandeira == 7 ? true : false;
            break;
            
            case 7:
                flag = bandeira == 4 || bandeira == 6 || bandeira == 8 ? true : false;
            break;
            
            case 8:
                flag = bandeira == 5 || bandeira == 7 ? true : false;
            break;
            
            default:
            break;
        }
        return flag;
    }
    
    //Ta um saco essa função, eu sei. Mas eu não to com vontade de pensar em uma solução para calcular a distância de maneira automática.
    private int calcularDistancia(int id, int posAtual)
    {
        int dist = -1;
        switch(id)
        {
            case 0:
                dist =  posAtual == 0 ? 0 :
                        posAtual == 1 || posAtual == 3 ? 1 : 
                        posAtual == 2 || posAtual == 4 || posAtual == 6 ? 2 :
                        posAtual == 5 || posAtual == 7 ? 3 : 4; /*8*/
            break;
            
            case 1:
                dist =  posAtual == 1 ? 0 :
                        posAtual == 0 || posAtual == 2 || posAtual == 4 ? 1 : 
                        posAtual == 3 || posAtual == 5 || posAtual == 7 ? 2 : 3; /*6 ou 7*/
            break;
            
            case 2:
                dist =  posAtual == 2 ? 0 :
                        posAtual == 1 || posAtual == 5? 1 : 
                        posAtual == 0 || posAtual == 4 || posAtual == 8 ? 2 :
                        posAtual == 3 || posAtual == 7 ? 3 : 4; /*6*/
            break;
            
            case 3:
                dist =  posAtual == 3 ? 0 :
                        posAtual == 0 || posAtual == 4 || posAtual == 6 ? 1 : 
                        posAtual == 1 || posAtual == 5 || posAtual == 7 ? 2 : 3; /*2 ou 8*/
            break;
            
            case 4:
                dist =  posAtual == 4 ? 0 :
                        posAtual == 1 || posAtual == 3 || posAtual == 5 || posAtual == 7 ? 1 : 2; /*0, 2, 6 ou 8*/
            break;
            
            case 5:
                dist =  posAtual == 5 ? 0 :
                        posAtual == 2 || posAtual == 4 || posAtual == 8 ? 1 : 
                        posAtual == 1 || posAtual == 3 || posAtual == 7 ? 2 : 3; /*0 ou 6*/
            break;
            
            case 6:
                dist =  posAtual == 6 ? 0 :
                        posAtual == 3 || posAtual == 7 ? 1 : 
                        posAtual == 0 || posAtual == 4 || posAtual == 8 ? 2 :
                        posAtual == 1 || posAtual == 5 ? 3 : 4; /*2*/
            break;
            
            case 7:
                dist =  posAtual == 7 ? 0 :
                        posAtual == 4 || posAtual == 6 || posAtual == 8 ? 1 : 
                        posAtual == 1 || posAtual == 3 || posAtual == 5 ? 2 : 3; /*0 ou 2*/
            break;
            
            case 8:
                dist =  posAtual == 8 ? 0 :
                        posAtual == 5 || posAtual == 7 ? 1 : 
                        posAtual == 2 || posAtual == 4 || posAtual == 6 ? 2 :
                        posAtual == 1 || posAtual == 3 ? 3 : 4; /*0*/
            break;
            
            default:
            break;
        }
        return dist;
    }
    
    //Adiciona na pilha de vizinhos todos os vizinhos da posição desejada
    public Pilha pegarVizinhos(Pilha p, int pos)
    {
        switch(pos)
        {
            case 0:
                p.push(1); p.push(3);
            break;
            
            case 1:
                p.push(0); p.push(2); p.push(4);
            break;
            
            case 2:
                p.push(1); p.push(5);
            break;
            
            case 3:
                p.push(0); p.push(4); p.push(6);
            break;
            
            case 4:
                p.push(1); p.push(3);
                p.push(5); p.push(7);
            break;
            
            case 5:
                p.push(2); p.push(4); p.push(8);
            break;
            
            case 6:
                p.push(3); p.push(7);
            break;
            
            case 7:
                p.push(4); p.push(6); p.push(8);
            break;
            
            case 8:
                p.push(5); p.push(7);
            break;
        }
        return p;
    }
    
    //Verifica se todas as peças estão no lugar correto.
    public boolean validarPecas()
    {
        int i = 0;
        while(i < 9 && imgs[i].getId() == i)
            i++;
        return i == 9;
    }
    
    public boolean buscaProfundidade()
    {
        /*
            A idéia desta busca é pegar o primeiro nó na sequência e ir buscando.
            Se chegar no final e não for o desejado, retrocede uma etapa (backtracking)
            e pega o segundo caminho. Vai retrocedendo até achar o resultado procurado.
        */
        
        pilha = new Pilha();
        int bandeira = this.bandeira;
        estados = new Estados();
        
        Pilha partida = new Pilha();
        partida = pegarVizinhos(partida, bandeira);
        
        boolean res = dfs(partida.pop());
        this.bandeira = getBandeira();
        return res;
    }

    private int[] getEstado()
    {
        int[] aux = new int[9];
        for (int i = 0; i < 9; i++)
            aux[i] = imgs[i].getId();
        
        return aux;
    }
    
    private void setEstado(int[] estado)
    {
        Imagem temp;
        for (int i = 0; i < 8; i++) {
            for (int j = 1; j < 9; j++) {
                if(estado[i] != imgs[j].getId())
                {
                    temp = imgs[j];
                    imgs[j] = imgs[i];
                    imgs[i] = temp;
                }
            }
        }
    }
    
    
    //Não to conseguindo fazer a busca em profundidade dessa porra.
    //Ta perdendo as pregas, digo referências.
    private boolean dfs(int rota) {
        if(validarPecas())
            return true;
        
        int[] atual = getEstado();
        Pilha p;
        if(estados.contem(atual))
        {
            if(estados.foiVisitado(atual, rota))
                return false;
            else
            {
                estados.addVisitado(atual, rota);
                mov(rota);
                p = new Pilha();
                p = pegarVizinhos(p, rota);
                while(!p.isEmpty())
                {
                    int aux = p.pop();
                    boolean flag = dfs(aux);
                    if(flag)
                        return true;
                }
            }
        }
        else
        {
            estados.addEstado(atual);
            return dfs(rota);
        }
        return false;
    }
    
}
