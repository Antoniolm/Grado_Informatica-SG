/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import GUI.Visualization;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.Viewer;
import com.sun.j3d.utils.universe.ViewingPlatform;
import javax.media.j3d.Alpha;
import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Light;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Texture;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.View;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
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
    // Se construye la ventana de visualización
    Visualization visualizationWindow = new Visualization (canvas);
    
    // Se crea el universo y la rama de la vista con ese canvas
    SimpleUniverse universe = createUniverse(canvas);
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
   Nave planetExpress= new Nave("IronHide\\RB-IronHide.obj");
   universe.addBranchGraph(planetExpress);
   
   
    // Como raíz se usa un BrachGroup
    Astro sol=new Estrella("imgs/sol.jpg",4.0f,0.0f,2.0f,2.0f);
    
    Astro mercurio= new Planeta("imgs/mercurio.jpg",1.2f,6.0f,1.7f,0.3f);
    
    Astro venus= new Planeta("imgs/venus.jpg",1.7f,10.0f,2.0f,0.5f);
    
    Astro tierra=new Planeta("imgs/tierra.jpg",0.7f,14.0f,1.0f,0.7f);
        Astro luna=new Satelite("imgs/luna.jpg",0.3f,1.50f,2.0f,1.0f);
        
    Astro marte= new Planeta("imgs/marte.jpg",1.5f,17.5f,1.0f,0.9f);
        Astro fobos=new Satelite("imgs/fobos.jpg",0.5f,2.3f,2.0f,1.0f);
        Astro deimos=new Satelite("imgs/deimos.jpg",0.3f,3.5f,2.0f,3.0f);
        
    
    Astro jupiter= new Planeta("imgs/jupiter.jpg",3.0f,25.0f,0.3f,1.0f);
        Astro io=new Satelite("imgs/io.jpg",0.2f,3.50f,2.0f,1.7f);
        Astro europa=new Satelite("imgs/europa.png",0.2f,4.2f,2.0f,3.5f);
        Astro calisto=new Satelite("imgs/calisto.jpg",0.3f,4.9f,2.0f,6.0f);
       
    Astro saturno= new Planeta("imgs/saturno.jpg",2.7f,35.0f,0.3f,1.3f);
    
    Astro urano= new Planeta("imgs/urano.jpg",2.25f,42.0f,0.5f,1.4f);
        Astro miranda=new Satelite("imgs/miranda.jpg",0.1f,3.0f,2.0f,1.4f);
        Astro ariel=new Satelite("imgs/ariel.jpg",0.2f,3.70f,2.0f,2.5f);
        Astro titania=new Satelite("imgs/titania.jpg",0.2f,4.30f,2.0f,6.0f);
    Astro neptuno= new Planeta("imgs/neptuno.jpg",2.0f,48.0f,1.5f,1.8f);
        Astro triton=new Satelite("imgs/triton.png",0.3f,2.60f,2.0f,-3.0f);
    Astro pluton= new Planeta("imgs/pluton.jpg",0.7f,52.0f,0.4f,2.0f);
    
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
    
    
    universe.addBranchGraph(sol);
    universe.addBranchGraph(aLight);
    universe.addBranchGraph(background);
    
    //Agregamos el picking
    Picking picar=new Picking(canvas);
    picar.setStatus(sol);
    
    // Se muestra la ventana
    visualizationWindow.setVisible(true);
    }
     
  private SimpleUniverse createUniverse (Canvas3D canvas) {
    // Se crea manualmente un ViewingPlatform para poder personalizarlo y asignárselo al universo
    ViewingPlatform viewingPlatform = new ViewingPlatform();
    
    // La transformación de vista, dónde se está, a dónde se mira, Vup
    TransformGroup viewTransformGroup = viewingPlatform.getViewPlatformTransform();
    Transform3D viewTransform3D = new Transform3D();
    viewTransform3D.lookAt (new Point3d (20,20,20), new Point3d (0,0,0), new Vector3d (0,1,0));
    viewTransform3D.invert();
    viewTransformGroup.setTransform (viewTransform3D);

    // El comportamiento, para mover la camara con el raton
    OrbitBehavior orbit = new OrbitBehavior(canvas, OrbitBehavior.REVERSE_ALL);
    orbit.setSchedulingBounds(new BoundingSphere(new Point3d (0.0f, 0.0f, 0.0f), 300.0f));
    orbit.setZoomFactor (2.0f);
    viewingPlatform.setViewPlatformBehavior(orbit);
    
    // Se establece el angulo de vision a 45 grados y el plano de recorte trasero
    Viewer viewer = new Viewer (canvas);
    View view = viewer.getView();
    view.setFieldOfView(Math.toRadians(45));
    view.setBackClipDistance(300.0);

    // Se construye y devuelve el Universo con los parametros definidos
    return new SimpleUniverse (viewingPlatform, viewer);
  }
}
