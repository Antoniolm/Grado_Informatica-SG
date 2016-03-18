/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.sun.j3d.utils.geometry.Sphere;
import javax.media.j3d.Alpha;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Texture;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

/**
 *
 * @author LENOVO
 */
public class Planeta extends Astro {
    private TransformGroup nodorotacionSatelite;
    public Planeta(float radi,float distanciaPadr,float tiempoRotPropi,float tiempoRotPadr){
        super(radi,distanciaPadr,tiempoRotPropi,tiempoRotPadr);
        
        
        Appearance appearance = new Appearance();
        appearance.setPolygonAttributes(new PolygonAttributes (PolygonAttributes.POLYGON_FILL, PolygonAttributes.CULL_BACK, 0.0f)); 

        TransformGroup translacion = new TransformGroup();
        Vector3f vector=new Vector3f(distanciaPadre,0.0f,0.0f);
        Transform3D transformtranslation = new Transform3D();
        transformtranslation.setTranslation(vector);
        translacion.setTransform(transformtranslation); 
        
        Sphere sphere = new Sphere((float) super.radio, appearance);
        
        /////////////////////////////////////////////////////////////////////////////
        //ROTACION SOBRE SI MISMO
        ////////////////////////////////////////////////////////////////////////////
        nodorotacionSatelite = new TransformGroup();
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
        rotator.setSchedulingBounds(new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0));
        rotator.setEnable(true);
        nodorotacionSatelite.addChild(rotator);
        
        /////////////////////////////////////////////////////////////////////////////
        //ROTACION SOBRE LA ESTRELLA
        ////////////////////////////////////////////////////////////////////////////
        TransformGroup rotacionestrella = new TransformGroup();
        // Se le permite que se cambie en tiempo de ejecución
        rotacionestrella.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        // Se crea la matriz de rotación
        Transform3D yAxiestrella = new Transform3D();
        // Se crea un interpolador, un valor numérico que se ira modificando en tiempo de ejecución
        Alpha valueestrella = new Alpha(-1, Alpha.INCREASING_ENABLE, 0, 0,
                4000, 0, 0, 0, 0, 0);
        // Se crea el interpolador de rotación, las figuras iran rotando
        RotationInterpolator rotatorestrella = new RotationInterpolator(valueestrella, rotacionestrella, yAxiestrella,
                0.0f, (float) Math.PI * tiempoRotPadre);  //Math.PI*2.0f es el valor que controla la velocidad de las vueltas
        // Se le pone el entorno de activación y se activa
        rotatorestrella.setSchedulingBounds(new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0));
        rotatorestrella.setEnable(true);
        rotacionestrella.addChild(rotatorestrella);
        
        
        /////////////////////////////////////////////////////////////////////////////
        //REALIZAMOS LOS ENLACES
        ////////////////////////////////////////////////////////////////////////////
        translacion.addChild(nodorotacionSatelite);
        nodorotacionSatelite.addChild(sphere);
        rotacionestrella.addChild(translacion);
        
        this.addChild(rotacionestrella);
        
    }
    public void add(Anillo anillo){

    }

    public void add(Astro astro) {
        nodorotacionSatelite.addChild(astro);
    }
}
