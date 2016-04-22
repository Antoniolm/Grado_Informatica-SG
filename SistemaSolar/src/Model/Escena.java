/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import GUI.Control;
import GUI.Visualization;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.Viewer;
import com.sun.j3d.utils.universe.ViewingPlatform;
import java.util.ArrayList;
import javax.media.j3d.Alpha;
import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Light;
import javax.media.j3d.Locale;
import javax.media.j3d.PhysicalBody;
import javax.media.j3d.PhysicalEnvironment;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Switch;
import javax.media.j3d.Texture;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.View;
import javax.media.j3d.ViewPlatform;
import javax.media.j3d.VirtualUniverse;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

/**
 *
 * @author LENOVO
 */
public class Escena {
    private Fondo background;
    
    public Escena(){
     // Se obtiene la configuración gráfica del sistema y se crea el Canvas3D que va a mostrar la imagen
    Canvas3D canvas = new Canvas3D (SimpleUniverse.getPreferredConfiguration());
    canvas.setSize(800, 600);
    Canvas3D canvas2 = new Canvas3D (SimpleUniverse.getPreferredConfiguration());
    canvas2.setSize(800, 600);
    // Se construye la ventana de visualización
     Visualization visualizationWindow = new Visualization (canvas,800,600,0,0);
     Visualization visualizationWindow2 = new Visualization (canvas2,800,600,800,0);
    // Se crea el universo y la rama de la vista con ese canvas
    VirtualUniverse universe = new VirtualUniverse();
    Locale local=new Locale(universe);
    //Camaras
    Camara camaraplanta=new Camara(canvas, 60.0f, 0.02f, 40.0f,0.01f,true,0,new Point3d(0.0,140.0,0.0), new Point3d(0.0,0.0,0.0), new Vector3d(0,0,-1));
    //Camara camaraplanta=new Camara(canvas, 60.0f, 0.02f, 40.0f,0.01f,false,100.0f,new Point3d(5.0,5.0,2.0), new Point3d(0.0,5.0,0.0), new Vector3d(0,1,0));
    Camara camarapers=new Camara(canvas2, 60.0f, 0.02f, 40.0f,0.01f,false,45.0f,new Point3d(50.0,50.0,50.0), new Point3d(0.0,0.0,0.0), new Vector3d(0,1,0));
    camarapers.removeCanvas();
    Camara camaraluna=new Camara(canvas2, 60.0f, 0.02f, 40.0f,0.01f,false,100.0f,new Point3d (0,0.5,0), new Point3d (-1,-0.25,0), new Vector3d (1,1,0));
    camaraluna.removeCanvas();
    Camara camaranave=new Camara(canvas2, 60.0f, 0.02f, 40.0f,0.01f,false,45.0f,new Point3d(0.0,0.5,0.0), new Point3d(-1.0,-0.25,0.0), new Vector3d(1,1,0));
    camaranave.removeCanvas();
    
    ArrayList<Camara> camaras=new ArrayList<Camara>();
    camaras.add(camarapers);
    camaras.add(camaraluna);
    camaras.add(camaranave);
    
    Control nuevocontrol=new Control(camaras);
    camarapers.addCanvas();
    local.addBranchGraph(camaraplanta);
    local.addBranchGraph(camarapers);
    
    
   // universe.getViewingPlatform().setNominalViewingTransform();
   
    Luz aLight= new Luz();
    
    // Se crea y se añade el fondo
    background = new Fondo();
    ////////////////////////////////////////////////
    //NAVE
    ///////////////////////////////////////////////
    //Raptor/FA-22_Raptor.obj
    //naveFuturama\\low_poly_express_ship.obj
    //naveFuturama\\low_poly_express_ship.obj
    
    //public Nave(String textur, long duracion, Point3f[] recorrido, Quat4f[] angulos, float[] alphas){
   Nave transformer= new Nave("IronHide\\RB-IronHide.obj",5000,
                        new Point3f[]{
                            new Point3f(-5f,5f,0f),new Point3f(-7f,5f,2f),
                            new Point3f(-3f,5f,2f),new Point3f(0f,5f,0f)
                        },
                        new Quat4f[]{
                            new Quat4f(0.0f,1.0f,0.0f,(float)Math.toRadians(0)),
                            new Quat4f(1.0f,0.0f,0.0f,(float)Math.toRadians(315)),
                            new Quat4f(0.0f,1.0f,0.0f,(float)Math.toRadians(0)),
                            new Quat4f(1.0f,0.0f,0.0f,(float)Math.toRadians(45)),
                        },
                        new float[]{
                            0.0f,0.33f,0.67f,1.0f
                        });
   
   transformer.addCamara(camaranave);
   local.addBranchGraph(transformer);
   
    // Como raíz se usa un BrachGroup
    Astro sol=new Estrella("imgs/sol.jpg",4.0f,0.0f,2.0f,4.0f);
    
    Astro mercurio= new Planeta("imgs/mercurio.jpg",1.2f,6.0f,1.7f,2.3f);
    
    Astro venus= new Planeta("imgs/venus.jpg",1.7f,10.0f,2.0f,2.5f);
    
    Astro tierra=new Planeta("imgs/tierra.jpg",0.7f,14.0f,1.0f,2.7f);
        Astro luna=new Satelite("imgs/luna.jpg",0.3f,1.50f,2.0f,3.0f);
        
    Astro marte= new Planeta("imgs/marte.jpg",1.5f,17.5f,1.0f,2.9f);
        Astro fobos=new Satelite("imgs/fobos.jpg",0.5f,2.3f,2.0f,3.0f);
        Astro deimos=new Satelite("imgs/deimos.jpg",0.3f,3.5f,2.0f,5.0f);
        
    
    Astro jupiter= new Planeta("imgs/jupiter.jpg",3.0f,25.0f,0.3f,3.0f);
        Astro io=new Satelite("imgs/io.jpg",0.2f,3.50f,2.0f,3.7f);
        Astro europa=new Satelite("imgs/europa.png",0.2f,4.2f,2.0f,5.5f);
        Astro calisto=new Satelite("imgs/calisto.jpg",0.3f,4.9f,2.0f,8.0f);
       
    Astro saturno= new Planeta("imgs/saturno.jpg",2.7f,35.0f,0.3f,3.3f);
    
    Astro urano= new Planeta("imgs/urano.jpg",2.25f,42.0f,0.5f,3.4f);
        Astro miranda=new Satelite("imgs/miranda.jpg",0.1f,3.0f,2.0f,3.4f);
        Astro ariel=new Satelite("imgs/ariel.jpg",0.2f,3.70f,2.0f,4.5f);
        Astro titania=new Satelite("imgs/titania.jpg",0.2f,4.30f,2.0f,8.0f);
    Astro neptuno= new Planeta("imgs/neptuno.jpg",2.0f,48.0f,1.5f,3.8f);
        Astro triton=new Satelite("imgs/triton.png",0.3f,2.60f,2.0f,-5.0f);
    Astro pluton= new Planeta("imgs/pluton.jpg",0.7f,52.0f,0.4f,4.0f);
    
    tierra.add(luna);
    sol.add(mercurio);
    sol.add(venus);
    sol.add(tierra);
    sol.add(marte);
        marte.add(fobos);
        marte.add(deimos);
    sol.add(jupiter);
        jupiter.add(io);
        jupiter.add(europa);
        jupiter.add(calisto);
    sol.add(saturno);
        
    sol.add(urano);
        urano.add(titania);
        urano.add(ariel);
        urano.add(miranda);
    sol.add(neptuno);
        neptuno.add(triton);
    sol.add(pluton);
    
    luna.addCamara(camaraluna);

    
    //Agregamos el picking
    Picking picar=new Picking(canvas2);
    picar.setSchedulingBounds(new BoundingSphere(new Point3d(0,0,0),300.0f));
    picar.setStatus(sol);
    BranchGroup bgpicking=new BranchGroup();
    
    bgpicking.addChild(picar);
    sol.addChild(bgpicking);
    //local.addBranchGraph(prueba);
    
    
    
    local.addBranchGraph(sol);
    local.addBranchGraph(aLight);
    local.addBranchGraph(background);
    
    
    
    // Se muestra la ventana
    visualizationWindow.setVisible(true);
    visualizationWindow2.setVisible(true);
    nuevocontrol.setVisible(true);
    }
     
  private BranchGroup createViews (Canvas3D canvas,float distance) {
      BranchGroup vistagroup=new BranchGroup();
    
      ViewPlatform viewplat=new ViewPlatform();
      viewplat.setActivationRadius(40.0f);
      
      PhysicalBody pbody=new PhysicalBody();
      PhysicalEnvironment penvi=new PhysicalEnvironment();
      
      
      View vista=new View();
      vista.setFrontClipDistance(0.02);
      vista.setBackClipDistance(40);
      vista.addCanvas3D(canvas);
      vista.setPhysicalBody(pbody);
      vista.setPhysicalEnvironment(penvi);
      vista.attachViewPlatform(viewplat);

      Transform3D transform = new Transform3D();
      transform.setTranslation(new Vector3f(0.0f, 0.0f, distance));
      TransformGroup transformgr = new TransformGroup(transform);
      transformgr.addChild(viewplat);
      vistagroup.addChild(transformgr);

    // Se construye y devuelve el Universo con los parametros definidos
    return vistagroup;
  }
}
