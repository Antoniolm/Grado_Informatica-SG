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
 * @author LENOVO
 */
public class Planeta extends Astro {
    public Planeta(double radi,double distanciaPadr,double tiempoRotPropi,double tiempoRotPadr){
        super(radi,distanciaPadr,tiempoRotPropi,tiempoRotPadr);
    }
    public void add(Anillo anillo){
    
    }

    @Override
    public BranchGroup add(Astro planet) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
