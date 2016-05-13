package Model;

import GUI.Control;
import GUI.Visualization;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.util.ArrayList;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Locale;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.VirtualUniverse;
import javax.vecmath.AxisAngle4f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;

/**
 *
 * @author ANTONIO DAVID LÓPEZ MACHADO Y JAVIER MARTINEZ MONTILLA
 */
public class Partida {
    private Fondo background;
    
    public Partida(){
     // Se obtiene la configuración gráfica del sistema y se crean los Canvas3D que va a mostrar la imagen
    Canvas3D canvas = new Canvas3D (SimpleUniverse.getPreferredConfiguration());
    canvas.setSize(800, 600);
    Canvas3D canvas2 = new Canvas3D (SimpleUniverse.getPreferredConfiguration());
    canvas2.setSize(800, 600);
    
    // Se construyen las ventanas de visualización
     Visualization visualizationWindow = new Visualization (canvas,650,580,0,0);
     Visualization visualizationWindow2 = new Visualization (canvas2,650,580,750,0);
     
    // Se crea el universo
    VirtualUniverse universe = new VirtualUniverse();
    Locale local=new Locale(universe);
    
    //Se crean las camaras, (planta, perspectiva, luna y nave)
    Camara camaraplanta=new Camara(canvas, 60.0f, 0.02f, 40.0f,0.01f,true,0,new Point3d(0.0,140.0,0.0), new Point3d(0.0,0.0,0.0), new Vector3d(0,0,-1),false);

    Camara camarapers=new Camara(canvas2, 60.0f, 0.02f, 40.0f,0.01f,false,45.0f,new Point3d(50.0,50.0,50.0), new Point3d(0.0,0.0,0.0), new Vector3d(0,1,0),true);
    camarapers.removeCanvas(); 
    Camara camaraluna=new Camara(canvas2, 60.0f, 0.02f, 40.0f,0.01f,false,100.0f,new Point3d (0,0.5,0), new Point3d (-1,-0.25,0), new Vector3d (1,1,0),false);
    camaraluna.removeCanvas();
    Camara camaranave=new Camara(canvas2, 60.0f, 0.02f, 40.0f,0.01f,false,45.0f,new Point3d (0,0.5,-0.25), new Point3d (0,0,1), new Vector3d(0,1,0),false);
    camaranave.removeCanvas();
    
    //Compilamos todas las camaras
    camaraplanta.compile();
    camarapers.compile();
    camaraluna.compile();
    camaranave.compile();
    
    //Array de camaras variables
    ArrayList<Camara> camaras=new ArrayList<Camara>();
    camaras.add(camarapers);
    camaras.add(camaraluna);
    camaras.add(camaranave);
    
    //Ventana de control
    Control nuevocontrol=new Control(camaras);
    camarapers.addCanvas();
    
    local.addBranchGraph(camaraplanta);
    local.addBranchGraph(camarapers);
    
    //Se crea la luz ambiental y la compilamos
    Luz aLight= new Luz();
    aLight.compile();
    
    // Se crea y se añade el fondo y la compilamos
    background = new Fondo();
    background.compile();
    
    //Se crea la nave con su movimiento
    Nave transformer= new Nave("IronHide/RB-IronHide.obj",5000,
                        new Point3f[]{
                            new Point3f(5f, 5f, -10f), new Point3f(5f, 10f, 0f),
                            new Point3f(5f, 15f, 10f), new Point3f(5f, 10f, 10f),
                            new Point3f(5f, 5f, 10f), new Point3f(-5f, 5f, 10f),
                            new Point3f(-5f, 5f, -10f), new Point3f(5f, 5f, -10f)
                        },
                        new AxisAngle4f[] {             // angulos
                            new AxisAngle4f(0.0f, 1.0f, 0.0f, (float) Math.toRadians(0)),
                            new AxisAngle4f(1.0f, 0.0f, 0.0f, (float) Math.toRadians(315)),
                            new AxisAngle4f(0.0f, 1.0f, 0.0f, (float) Math.toRadians(0)),
                            new AxisAngle4f(1.0f, 0.0f, 0.0f, (float) Math.toRadians(45)),
                            new AxisAngle4f(0.0f, 1.0f, 0.0f, (float) Math.toRadians(270)),
                            new AxisAngle4f(0.0f, 1.0f, 0.0f, (float) Math.toRadians(180)),
                            new AxisAngle4f(0.0f, 1.0f, 0.0f, (float) Math.toRadians(90)),
                            new AxisAngle4f(0.0f, 1.0f, 0.0f, (float) Math.toRadians(360))
                        },
                        new float[]{
                            0f, 0.14f, 0.28f, 0.42f, 0.56f, 0.7f, 0.84f, 1f
                        });
    
   //Añadimos la nave al locale
   transformer.addCamara(camaranave);
   local.addBranchGraph(transformer);
   
    
   Tablero tabla=new Tablero();
   local.addBranchGraph(tabla);
   
        TransformGroup rotacion3 = new TransformGroup();
        Transform3D rotacionx = new Transform3D();
        rotacionx.rotY(Math.PI);
        rotacion3.setTransform(rotacionx);
   
    Tablero tabla2=new Tablero();
    BranchGroup bg=new BranchGroup();
    bg.addChild(rotacion3);
    rotacion3.addChild(tabla2);
    local.addBranchGraph(bg);
    //Agregamos el picking
    Picking picar=new Picking(canvas2);
    picar.setSchedulingBounds(new BoundingSphere(new Point3d(0,0,0),300.0f));
    //picar.setStatus(sol);
    BranchGroup bgpicking=new BranchGroup();
    bgpicking.addChild(picar);
    //sol.addChild(bgpicking);
    
    //Compilamos el branchgroup del sol del cual
    //ramifica todo el sistema solar
    //sol.compile();
    
    //Añadimos al locale los branchgraph, luz ambiental y fondo
    //local.addBranchGraph(sol);
    local.addBranchGraph(aLight);
    local.addBranchGraph(background);
    
    // Se muestra la ventana
    visualizationWindow.setVisible(true);
    visualizationWindow2.setVisible(true);
    nuevocontrol.setVisible(true);
    }
}
