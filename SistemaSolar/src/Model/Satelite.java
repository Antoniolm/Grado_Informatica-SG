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
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Material;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

/**
 *
 * @author LENOVO
 */
public class Satelite extends Astro{
    public Satelite(String textura, float radi,float distanciaPadr,float tiempoRotPropi,float tiempoRotPadr){
        super(radi,distanciaPadr,tiempoRotPropi,tiempoRotPadr);
         Appearance appearance = new Appearance();
        //appearance.setPolygonAttributes(new PolygonAttributes (PolygonAttributes.POLYGON_FILL, PolygonAttributes.CULL_BACK, 0.0f)); 

        //Sphere sphere = new Sphere((float) super.radio, appearance);
        
        Texture aTexture = new TextureLoader (textura, null).getTexture();
        appearance.setTexture (aTexture);
        appearance.setMaterial (new Material (
            new Color3f (0.20f, 0.20f, 0.20f),   // Color ambiental
            new Color3f (0.00f, 0.00f, 0.00f),   // Color emisivo
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

        /////////////////////////////////////////////////////////////////////////////
        //ROTACION SOBRE LA ESTRELLA
        ////////////////////////////////////////////////////////////////////////////
        TransformGroup rotacionpadre = new TransformGroup();
        // Se le permite que se cambie en tiempo de ejecución
        rotacionpadre.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        // Se crea la matriz de rotación
        Transform3D yAxipadre = new Transform3D();
        // Se crea un interpolador, un valor numérico que se ira modificando en tiempo de ejecución
        Alpha valuepadre = new Alpha(-1, Alpha.INCREASING_ENABLE, 0, 0, (long) (4500*tiempoRotPadre), 0, 0, 0, 0, 0);
        // Se crea el interpolador de rotación, las figuras iran rotando
        RotationInterpolator rotatorpadre = new RotationInterpolator(valuepadre, rotacionpadre, yAxipadre,
                0.0f, (float) Math.PI * 2);  //Math.PI*2.0f es el valor que controla la velocidad de las vueltas
        // Se le pone el entorno de activación y se activa
        rotatorpadre.setSchedulingBounds(new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 300.0));
        rotatorpadre.setEnable(true);
        rotacionpadre.addChild(rotatorpadre);

        /////////////////////////////////////////////////////////////////////////////
        //ROTACION SOBRE SI MISMO
        ////////////////////////////////////////////////////////////////////////////
        TransformGroup nodorotacionSatelite = new TransformGroup();
        // Se le permite que se cambie en tiempo de ejecución
        nodorotacionSatelite.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        // Se crea la matriz de rotación
        Transform3D yAxis = new Transform3D();
        // Se crea un interpolador, un valor numérico que se ira modificando en tiempo de ejecución
        Alpha value = new Alpha(-1, Alpha.INCREASING_ENABLE, 0, 0,
                4000, 0, 0, 0, 0, 0);
        // Se crea el interpolador de rotación, las figuras iran rotando
        RotationInterpolator rotator = new RotationInterpolator(value, nodorotacionSatelite, yAxis,
                0.0f, (float) Math.PI * tiempoRotPropio);  //Math.PI*2.0f es el valor que controla la velocidad de las vueltas
        // Se le pone el entorno de activación y se activa
        rotator.setSchedulingBounds(new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 300.0));
        rotator.setEnable(true);
        nodorotacionSatelite.addChild(rotator);
        
        
        
        /////////////////////////////////////////////////////////////////////////////
        //TRANSLACION
        ////////////////////////////////////////////////////////////////////////////
        TransformGroup translacion = new TransformGroup();
        Vector3f vector=new Vector3f(distanciaPadre,0.0f,0.0f);
        Transform3D transformtranslation = new Transform3D();
        transformtranslation.setTranslation(vector);
        translacion.setTransform(transformtranslation); 
        
        ////////////////////////////////////////////////////////////////////////////
        //ENLACES
        ////////////////////////////////////////////////////////////////////////////
        nodorotacionSatelite.addChild(sphere);
        translacion.addChild(nodorotacionSatelite);
        rotacionpadre.addChild(translacion);
        this.addChild(rotacionpadre);
    }

    public void add(Astro planet) {
        
    }
    
}
