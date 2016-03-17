/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Texture;

/**
 *
 * @author LENOVO
 */
public abstract class Astro extends Shape3D{
    Texture textura;
    double radio;
    double distanciaPadre;
    double tiempoRotPropio;
    double tiempoRotPadre;
    Astro(double radi,double distanciaPadr,double tiempoRotPropi,double tiempoRotPadr){
        radio=radi;
        distanciaPadre=distanciaPadr;
        tiempoRotPropio=tiempoRotPropi;
        tiempoRotPadre=tiempoRotPadr;
        
    }
    public void paraMovimiento(){
    
    }
    public void reanudarMovimiento(){
    
    }
    abstract public BranchGroup add(Astro planet);
    
    
}
