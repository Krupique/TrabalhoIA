/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhoia.estrutura;

import javafx.scene.image.Image;

/**
 *
 * @author Henrique K. Secchi
 */
public class Imagem {
    private Image img;
    private int id;

    public Imagem(Image img, int id) {
        this.img = img;
        this.id = id;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
