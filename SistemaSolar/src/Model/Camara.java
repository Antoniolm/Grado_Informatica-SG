/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

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
import javax.vecmath.Vector3f;

/**
 *
 * @author LENOVO
 */
public class Camara extends BranchGroup{
    private View vista;
    private Canvas3D canvas;
    private boolean activada;
    public Camara(Canvas3D canva,float distance,float planodelantero,float planotrasero,float screenScl,boolean planta,float angulo,Point3d posicion,Point3d direccion,Vector3d vup){    
      canvas=canva;
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
      transformgr.addChild(viewplat);
      addChild(transformgr);    
    }
    public void removeCanvas(){
        vista.removeCanvas3D(canvas);
        activada=false;
    }
    public void addCanvas(Canvas3D canv){
        canvas=canv;
        vista.addCanvas3D(canvas);
        activada=true;
    }
    public boolean isActive(){
        return activada;
    }
}
