/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhoia.estrutura;

import java.util.ArrayList;
import java.util.Collections;
import javafx.collections.FXCollections;
import javafx.scene.image.Image;
import trabalhoia.utilidades.Divisor;

/**
 * @author Henrique K. Secchi
 */
public class Algoritmos {
    private Image imgPrincipal;
    private Imagem[] imgs;
    
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
        
        imgs[flag - 1].setFlag(false);
        imgs[flag - 1].setImg(null);
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
        
        return vet;
    }
}
