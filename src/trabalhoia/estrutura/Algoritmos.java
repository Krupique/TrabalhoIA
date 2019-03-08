/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhoia.estrutura;

import javafx.scene.image.Image;

/**
 * @author Henrique K. Secchi
 */
public class Algoritmos {
    private Image imgPrincipal;
    private Imagem[][] imgs;
    
    public Algoritmos(Image img)
    {
        imgPrincipal = img;
        imgs = new Imagem[3][3];
        
        //Funcao dividir imagens.
    }

    public Image getImgPrincipal() {
        return imgPrincipal;
    }

    public void setImgPrincipal(Image img) {
        this.imgPrincipal = img;
    }

    public Imagem[][] getImgs() {
        return imgs;
    }

    public void setImgs(Imagem[][] imgs) {
        this.imgs = imgs;
    }
}
