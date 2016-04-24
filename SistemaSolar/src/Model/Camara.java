/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.sun.j3d.utils.behaviors.mouse.MouseBehavior;
import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;
import com.sun.j3d.utils.behaviors.mouse.MouseWheelZoom;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.PhysicalBody;
import javax.media.j3d.PhysicalEnvironment;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.View;
import javax.media.j3d.ViewPlatform;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

/**
 *
 * @author ANTONIO DAVID LÓPEZ MACHADO Y JAVIER MARTINEZ MONTILLA
 */
public class Camara extends BranchGroup{
    private View vista;
    private Canvas3D canvas;
    private boolean activada;
    private TransformGroup tg;
    
    public Camara(Canvas3D canva,float distance,float planodelantero,float planotrasero,float screenScl,boolean planta,float angulo,Point3d posicion,Point3d direccion,Vector3d vup,boolean move){    
      canvas=canva;
      activada=true;
      ViewPlatform viewplat=new ViewPlatform();
      viewplat.setActivationRadius(40.0f);
      
      PhysicalBody pbody=new PhysicalBody();
      PhysicalEnvironment penvi=new PhysicalEnvironment();
      
      vista=new View();
      
      if(!planta) {
           vista.setProjectionPolicy(View.PERSPECTIVE_PROJECTION);
           vista.setFieldOfView(Math.toRadians(angulo));
       } else {
           vista.setProjectionPolicy(View.PARALLEL_PROJECTION);
           vista.setScreenScalePolicy(View.SCALE_EXPLICIT);
           vista.setScreenScale(screenScl);
       }
      
      vista.setFrontClipDistance(planodelantero);
      vista.setBackClipDistance(planotrasero);
      vista.addCanvas3D(canvas);
      vista.setPhysicalBody(pbody);
      vista.setPhysicalEnvironment(penvi);
      vista.attachViewPlatform(viewplat);
      
      Transform3D posicionVista= new Transform3D ( ) ;
      posicionVista.lookAt( posicion , direccion , vup ) ;
      posicionVista.invert();
      
      TransformGroup transformgr = new TransformGroup(posicionVista);
      
      if(move){ 
            //Damos permiso para modificar el transformGroup una vez vivo
            transformgr.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
            
            //MouseRotate permite rotar figuras por sus ejes X e Y arrastrando el botón izquierdo
            MouseRotate myMouseRotate = new MouseRotate(MouseBehavior.INVERT_INPUT);
            myMouseRotate.setSchedulingBounds(new BoundingSphere(new Point3d(), 300.0));
            myMouseRotate.setFactor(0.005);
            myMouseRotate.setTransformGroup(transformgr);
            
            //MouseTranslate Permite trasladar figuras en las direcciones X e Y arrastrando el botón derecho
            MouseTranslate myMouseTranslate = new MouseTranslate(MouseBehavior.INVERT_INPUT);
            myMouseTranslate.setSchedulingBounds(new BoundingSphere(new Point3d(), 300.0));
            myMouseTranslate.setFactor(0.1);
            myMouseTranslate.setTransformGroup(transformgr);

            //MouseWheelZoom permite trasladar figuras en la dirección Z moviendo la rueda
            MouseWheelZoom myMouseZoom = new MouseWheelZoom(MouseBehavior.INVERT_INPUT);
            myMouseZoom.setSchedulingBounds(new BoundingSphere(new Point3d(), 300.0));
            myMouseZoom.setFactor(2.0);
            myMouseZoom.setTransformGroup(transformgr);
            
            transformgr.addChild(myMouseRotate);
            transformgr.addChild(myMouseTranslate);
            transformgr.addChild(myMouseZoom);
        }

      transformgr.addChild(viewplat);
      addChild(transformgr);    
    }
    
    public void removeCanvas(){
        vista.removeCanvas3D(canvas);
        activada=false;
    }
    
    public void addCanvas(){
        vista.addCanvas3D(this.canvas);
        activada=true;
    }
    
    public boolean isActive(){
        return activada;
    }
}
