/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Texture;

/**
 *
 * @author ANTONIO DAVID LÓPEZ MACHADO Y JAVIER MARTINEZ MONTILLA
 */
public abstract class Astro extends BranchGroup{
    Texture textura;
    float radio;
    float distanciaPadre;
    float tiempoRotPropio;
    float tiempoRotPadre;
    boolean movimiento;
    
    Astro(float radi,float distanciaPadr,float tiempoRotPropi,float tiempoRotPadr){
        radio=radi;
        distanciaPadre=distanciaPadr;
        tiempoRotPropio=tiempoRotPropi;
        tiempoRotPadre=tiempoRotPadr;
        movimiento=true;
    }
    
    public abstract void onoffMovimiento();
    public abstract void add(Astro astro);
    public abstract void addAnillo(Anillo anillo);
    public abstract void addCamara(Camara cam);
}
