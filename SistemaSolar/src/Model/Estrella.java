/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Light;
import javax.media.j3d.Material;
import javax.media.j3d.PointLight;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

/**
 *
 * @author LENOVO
 */
public class Estrella extends Astro{
    

    Light luzPuntual;
    
    public Estrella(String textura,float radi,float distanciaPadr,float tiempoRotPropi,float tiempoRotPadr){
        super(radi,distanciaPadr,tiempoRotPropi,tiempoRotPadr);
        
        // Vamos a hacer el grafo de escena, una esfera
        // Se necesita una geometría y un aspecto
        Appearance appearance = new Appearance();
        //appearance.setPolygonAttributes(new PolygonAttributes (PolygonAttributes.POLYGON_FILL, PolygonAttributes.CULL_BACK, 0.0f)); 

        
        ///////////////////////////////
        
        Texture aTexture = new TextureLoader (textura, null).getTexture();
        appearance.setTexture (aTexture);
        appearance.setMaterial (new Material (
            new Color3f (0.20f, 0.20f, 0.20f),   // Color ambiental
            new Color3f (1.00f, 1.00f, 1.00f),   // Color emisivo
            new Color3f (0.50f, 0.50f, 0.50f),   // Color difuso
            new Color3f (0.70f, 0.70f, 0.70f),   // Color especular
            17.0f ));                            // Brillo
        TextureAttributes ta = new TextureAttributes();
        ta.setTextureMode(TextureAttributes.MODULATE);
        appearance.setTextureAttributes(ta);   
        
        Sphere sphere = new Sphere (super.radio, 
        Primitive.GENERATE_NORMALS | 
        Primitive.GENERATE_TEXTURE_COORDS |
        Primitive.ENABLE_APPEARANCE_MODIFY, 64, 
        appearance);
        
        //////////////////////////////////
        
        
        BoundingSphere worldBounds = new BoundingSphere(new Point3d(0.0, 0.0,
        0.0), // Center
        1000.0); // Extent
        
        Color3f white = new Color3f (1.0f, 1.0f, 1.0f);;
        Point3f posicion=new Point3f (0.0f, 0.0f, 0.0f);
        Point3f atenuacion=new Point3f (1.0f, 0.0f, 0.0f);
        luzPuntual = new PointLight(white,posicion,atenuacion);
        luzPuntual.setCapability(PointLight.ALLOW_STATE_WRITE);
        luzPuntual.setCapability(PointLight.ALLOW_COLOR_WRITE);
        luzPuntual.setCapability(PointLight.ALLOW_POSITION_WRITE);
        luzPuntual.setCapability(PointLight.ALLOW_ATTENUATION_WRITE);
        luzPuntual.setInfluencingBounds(worldBounds);
        luzPuntual.setEnable(true);
        
        this.addChild(sphere);
        this.addChild(luzPuntual);
        
    
    }
    @Override
    public void add(Astro astro){
        this.addChild(astro);
    }

    @Override
    public void onoffMovimiento() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

