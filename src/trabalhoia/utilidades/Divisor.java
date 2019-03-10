/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhoia.utilidades;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

/**
 *
 * @author Henrique K. Secchi
 */
public class Divisor {
    
    private static Pixel[][][][] dividir(Image img)
    {
        int[] pixel = {0, 0, 0, 0};
        Pixel[][][][] mataux;
        BufferedImage bimg;
        bimg = SwingFXUtils.fromFXImage(img, null);
        WritableRaster raster = bimg.getRaster();
        int larg = (int)(img.getWidth() / 3);
        int alt = (int)(img.getHeight()/ 3);
        int auxalt = alt;
        int auxlarg = larg;
        
        mataux = new Pixel[3][3][larg][alt];
        
        for (int i = 0, l = 0, m = 0, c = 0, w = 0; i < img.getHeight(); i++, l++) 
        {
            for (int j = 0; j < img.getWidth(); j++) 
            {
                if(alt == i)
                {
                    l = 0;
                    alt += auxalt;
                    m++;
                }
                if(j < larg)
                {
                    c = 0;
                    w = j;
                }
                else if(j < larg * 2)
                {
                    c = 1;
                    w = j - larg;
                }
                else
                {
                    c = 2;
                    w = j - larg * 2;
                }
                
                raster.getPixel(i, j, pixel);
                mataux[m][c][w][l] = new Pixel(pixel[0], pixel[1], pixel[2]);
            }
        }
        
        return mataux;
    }
    
    public static Image[] converter(Image img)
    {
        
        Pixel[][][][] mat = dividir(img);
        int[] pixel = new int[4];
        BufferedImage[] bimg = new BufferedImage[9];
        WritableRaster[] raster = new WritableRaster[9];
        Image[] imgs = new Image[9];
        
        for (int i = 0; i < 9; i++) {
            bimg[i] = new BufferedImage(mat[0][0].length, mat[0][0][0].length, BufferedImage.TYPE_INT_RGB);
            raster[i] = bimg[i].getRaster();
            imgs[i] = SwingFXUtils.toFXImage(bimg[i], null);
        }
        
        
        for (int i = 0; i < imgs[0].getHeight(); i++) {
            for (int j = 0; j < imgs[0].getWidth(); j++) {
                
                pixel = mat[0][0][j][i].getXYZ();
                raster[0].setPixel(i, j, pixel);
                
                pixel = mat[0][1][j][i].getXYZ();
                raster[1].setPixel(i, j, pixel);
                
                pixel = mat[0][2][j][i].getXYZ();
                raster[2].setPixel(i, j, pixel);
                
                pixel = mat[1][0][j][i].getXYZ();
                raster[3].setPixel(i, j, pixel);
                
                pixel = mat[1][1][j][i].getXYZ();
                raster[4].setPixel(i, j, pixel);
                
                pixel = mat[1][2][j][i].getXYZ();
                raster[5].setPixel(i, j, pixel);
                
                pixel = mat[2][0][j][i].getXYZ();
                raster[6].setPixel(i, j, pixel);
                
                pixel = mat[2][1][j][i].getXYZ();
                raster[7].setPixel(i, j, pixel);
                
                pixel = mat[2][2][j][i].getXYZ();
                raster[8].setPixel(i, j, pixel);
            }
        }
        imgs[0] = SwingFXUtils.toFXImage(bimg[0], null);
        imgs[1] = SwingFXUtils.toFXImage(bimg[1], null);
        imgs[2] = SwingFXUtils.toFXImage(bimg[2], null);
        imgs[3] = SwingFXUtils.toFXImage(bimg[3], null);
        imgs[4] = SwingFXUtils.toFXImage(bimg[4], null);
        imgs[5] = SwingFXUtils.toFXImage(bimg[5], null);
        imgs[6] = SwingFXUtils.toFXImage(bimg[6], null);
        imgs[7] = SwingFXUtils.toFXImage(bimg[7], null);
        imgs[8] = SwingFXUtils.toFXImage(bimg[8], null);
        
        return imgs;
    }
}
