/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhoia.utilidades;

/**
 *
 * @author Henrique K. Secchi
 */
public class Pixel {
    private double x; 
    private double y; 
    private double z;

    public Pixel(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public Pixel()
    {
        
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }
    
    public int[] getXYZ()
    {
        int[] px = new int[3];
        px[0] = (int)x;
        px[1] = (int)y;
        px[2] = (int)z;
        
        return px;
    }
}
