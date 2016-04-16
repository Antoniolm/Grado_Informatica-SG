/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.sun.j3d.loaders.IncorrectFormatException;
import com.sun.j3d.loaders.ParsingErrorException;
import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.j3d.Alpha;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.RotPosPathInterpolator;
import javax.media.j3d.Texture;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.AxisAngle4f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

/**
 *
 * @author LENOVO
 */
public class Nave extends BranchGroup{
    private Point3f[] recorrido;
    private long velocidad;
    private float[] alphas;
    private Quat4f[] angulos;
    private RotPosPathInterpolator interpolator;
    private TransformGroup transform;
    TransformGroup translacion;
    
    public Nave(String textur, long duracion, Point3f[] recorrido, Quat4f[] angulos, float[] alphas){
        this.setPickable(false);
        this.velocidad = velocidad;
        this.recorrido = new Point3f[recorrido.length];
        this.angulos = new Quat4f[recorrido.length];
        this.alphas = new float[recorrido.length];
        
        for(int i=0; i<recorrido.length; i++) { // Se crean los puntos ángulos y alphas
            this.recorrido[i] = recorrido[i];
            this.angulos[i] = new Quat4f();
            this.angulos[i].set(angulos[i]);
            this.alphas[i] = alphas[i];
        }
        
        translacion = new TransformGroup();
        Vector3f vector=new Vector3f(0.0f,5.0f,0.0f);
        Transform3D transformtranslation = new Transform3D();
        transformtranslation.setTranslation(vector);
        translacion.setTransform(transformtranslation); 
        
        TransformGroup rotacion = new TransformGroup();
        Transform3D trotacion = new Transform3D();
        trotacion.rotZ(Math.toRadians(90));
        rotacion.setTransform(trotacion);
        
        transform = new TransformGroup();
        transform.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        Transform3D t3d= new Transform3D();
        Alpha valor = new Alpha(-1, Alpha.INCREASING_ENABLE, 0, 0,duracion, 0, 0, 0, 0, 0);
        interpolator= new RotPosPathInterpolator(valor,transform,t3d,alphas,angulos,recorrido);
        interpolator.setSchedulingBounds(new BoundingSphere(new Point3d(0.0,0.0,0.0),200));
        interpolator.setEnable(true);
        transform.addChild(interpolator);
                
        ////TEXTURA
        Scene escena=null;
        ObjectFile planetExpress =new ObjectFile(ObjectFile.RESIZE | ObjectFile.STRIPIFY | ObjectFile.TRIANGULATE,(float) ( Math.PI/4 ) ) ;
        try {
            escena=planetExpress.load(textur);
        } catch (FileNotFoundException | ParsingErrorException | IncorrectFormatException e) {
            System.err.println(e);
            System.exit(1);
        }

        BranchGroup objeto= new BranchGroup();
        objeto.addChild(escena.getSceneGroup());
        rotacion.addChild(objeto);
        translacion.addChild(rotacion);
        transform.addChild(translacion);
        
        this.addChild(transform);
        
    }
    
    void addCamara(Camara cam){
        transform.addChild(cam);
    }
}
