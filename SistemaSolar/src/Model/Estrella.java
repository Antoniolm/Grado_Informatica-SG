/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;
import javax.media.j3d.Alpha;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.Light;
import javax.media.j3d.Material;
import javax.media.j3d.PointLight;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;

/**
 *
 * @author ANTONIO DAVID LÓPEZ MACHADO Y JAVIER MARTINEZ MONTILLA
 */
public class Estrella extends Astro{
    

    Light luzPuntual;
    RotationInterpolator rotator;
    public Estrella(String textura,float radi,float distanciaPadr,float tiempoRotPropi,float tiempoRotPadr){
        super(radi,distanciaPadr,tiempoRotPropi,tiempoRotPadr);
        
        Appearance appearance = new Appearance();
        
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
        sphere.setUserData(this);
        
        
        ////////////////////////////////////////////////////////////////////////////
        //ROTACION SOBRE SI MISMO
        ////////////////////////////////////////////////////////////////////////////
        TransformGroup nodorotacion = new TransformGroup();
        // Se le permite que se cambie en tiempo de ejecución
        nodorotacion.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        // Se crea la matriz de rotación
        Transform3D yAxis = new Transform3D();
        // Se crea un interpolador, un valor numérico que se ira modificando en tiempo de ejecución
        Alpha valor = new Alpha(-1, Alpha.INCREASING_ENABLE, 0, 0, (long) (4500*tiempoRotPropio), 0, 0, 0, 0, 0);
        // Se crea el interpolador de rotación, las figuras iran rotando
        rotator = new RotationInterpolator(valor, nodorotacion, yAxis,
                0.0f, (float) Math.PI * 2);  //Math.PI*2.0f es el valor que controla la velocidad de las vueltas
        // Se le pone el entorno de activación y se activa
        rotator.setSchedulingBounds(new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 300.0));
        rotator.setEnable(true);
        nodorotacion.addChild(rotator);
        
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
        
        nodorotacion.addChild(sphere);
        nodorotacion.addChild(luzPuntual);
        this.addChild(nodorotacion);
    }
    
    @Override
    public void add(Astro astro){
        this.addChild(astro);
    }
    @Override
    public void addAnillo(Anillo anillo) {
        
    }
    @Override
    public void onoffMovimiento() {
    }
    
    @Override
    public void addCamara(Camara cam){
        
   }
}

