/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.sun.j3d.utils.geometry.Sphere;
import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.Texture;

/**
 *
 * @author LENOVO
 */
public class Estrella extends Astro{
    public Estrella(float radi,float distanciaPadr,float tiempoRotPropi,float tiempoRotPadr){
        super(radi,distanciaPadr,tiempoRotPropi,tiempoRotPadr);
        // Vamos a hacer el grafo de escena, una esfera
        // Se necesita una geometría y un aspecto
        Appearance appearance = new Appearance();
        appearance.setPolygonAttributes(new PolygonAttributes (PolygonAttributes.POLYGON_FILL, PolygonAttributes.CULL_BACK, 0.0f)); 

        Sphere sphere = new Sphere((float) super.radio, appearance);
        this.addChild(sphere);
    
    }
    @Override
    public void add(Astro astro){
        this.addChild(astro);
    }
}

