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
    private int id; //Posição correta da imagem
    private boolean flag; //Se é a imagem que está visível ou não.
    private int pos;

    public Imagem(Image img, int id, boolean flag) {
        this.img = img;
        this.id = id;
        this.flag = flag;
        this.pos = id;
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

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }
}
