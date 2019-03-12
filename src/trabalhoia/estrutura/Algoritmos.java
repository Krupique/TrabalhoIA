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
    
    public Algoritmos(Image img, int flag)
    {
        imgPrincipal = img;
        imgs = new Imagem[9];
        
        Image[] aux = Divisor.converter(img);
        //Adicionando as imagens cruas na classe Imagem(Contem mais detalhes sobre a imagem).
        imgs[0] = new Imagem(aux[0], 1, true);
        imgs[1] = new Imagem(aux[3], 2, true);
        imgs[2] = new Imagem(aux[6], 3, true);
        imgs[3] = new Imagem(aux[1], 4, true);
        imgs[4] = new Imagem(aux[4], 5, true);
        imgs[5] = new Imagem(aux[7], 6, true);
        imgs[6] = new Imagem(aux[2], 7, true);
        imgs[7] = new Imagem(aux[5], 8, true);
        imgs[8] = new Imagem(aux[8], 9, true);
        
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
        }
        
        int i = 0;
        if(i < 9 && imgs[i].getFlag())
            i++;
        
        bandeira = i;
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
            imgs[temp.get(i).getId() - 1] = temp.get(i);
            vet[i] = imgs[i].getImg();
        }
        
        int i = 0;
        if(i < 9 && imgs[i].getFlag())
            i++;
        
        bandeira = i;
        
        return vet;
    }
    
    public int getBandeira()
    {
        return bandeira;
    }
    
    public boolean movimentar(int pos)
    {
        System.out.println("Bandeira: " + bandeira + " Pos: " + pos);
        if(isPossible(--pos))
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
}
