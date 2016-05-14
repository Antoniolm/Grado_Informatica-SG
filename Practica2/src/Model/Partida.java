package Model;

import GUI.Control;
import GUI.Visualization;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.io.IOException;
import java.util.ArrayList;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Locale;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.VirtualUniverse;
import javax.vecmath.AxisAngle4f;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;

/**
 *
 * @author ANTONIO DAVID LÓPEZ MACHADO Y JAVIER MARTINEZ MONTILLA
 */
public class Partida {
    private Fondo background;
    
    public Partida() throws IOException{
     // Se obtiene la configuración gráfica del sistema y se crean los Canvas3D que va a mostrar la imagen
    Canvas3D canvas = new Canvas3D (SimpleUniverse.getPreferredConfiguration());
    canvas.setSize(800, 600);
    Canvas3D canvas2 = new Canvas3D (SimpleUniverse.getPreferredConfiguration());
    canvas2.setSize(1000, 800);
    
    // Se construyen las ventanas de visualización
     Visualization visualizationWindow = new Visualization (canvas,650,580,0,0);
     Visualization visualizationWindow2 = new Visualization (canvas2,1000,800,750,0);
     
    // Se crea el universo
    VirtualUniverse universe = new VirtualUniverse();
    Locale local=new Locale(universe);
    
    //Se crean las camaras, (planta, perspectiva, luna y nave)
    Camara camaraplanta=new Camara(canvas, 60.0f, 0.02f, 40.0f,0.01f,true,0,new Point3d(0.0,140.0,0.0), new Point3d(0.0,0.0,0.0), new Vector3d(0,0,-1),false);

    Camara camataque=new Camara(canvas2, 60.0f, 0.02f, 40.0f,0.01f,false,45,new Point3d(0.0,14.0,33.0), new Point3d(0.0,14.0,0.0), new Vector3d(0,1,0),true);
    camataque.removeCanvas();
    Camara camaranaves=new Camara(canvas2, 60.0f, 0.02f, 40.0f,0.01f,false,100,new Point3d (0,10,25), new Point3d (0,0,15), new Vector3d (0,1,0),false);
    camaranaves.removeCanvas();
    
    //Compilamos todas las camaras
    camaraplanta.compile();
    camataque.compile();
    camaranaves.compile();
    
    //Array de camaras variables
    ArrayList<Camara> camaras=new ArrayList<Camara>();
    camaras.add(camataque);
    camaras.add(camaranaves);
    
    
    //Ventana de control
    Control nuevocontrol=new Control(camaras);
    camataque.addCanvas();
    
    local.addBranchGraph(camaraplanta);
    local.addBranchGraph(camataque);
    local.addBranchGraph(camaranaves);
    
    //Se crea la luz ambiental y la compilamos
    Luz aLight= new Luz();
    aLight.compile();
    
    // Se crea y se añade el fondo y la compilamos
    background = new Fondo();
    background.compile();
    
   //Añadimos la nave al locale
   
    
   Color3f color=new Color3f(0.0f, 0.9f, 1.0f);
    Color3f color3=new  Color3f(1.0f,0.4f,0.4f);
   Tablero tabla=new Tablero(color,color,"plantillas/fichero.txt");
   local.addBranchGraph(tabla);
   
        TransformGroup rotacion3 = new TransformGroup();
        Transform3D rotacionx = new Transform3D();
        rotacionx.rotY(Math.PI);
        rotacion3.setTransform(rotacionx);
   
    Tablero tabla2=new Tablero(color3,color3,"plantillas/fichero.txt");
    BranchGroup bg=new BranchGroup();
    bg.addChild(rotacion3);
    rotacion3.addChild(tabla2);
    local.addBranchGraph(bg);
    
    //Añadimos al locale los branchgraph, luz ambiental y fondo
    local.addBranchGraph(aLight);
    local.addBranchGraph(background);
    
    // Se muestra la ventana
    visualizationWindow.setVisible(true);
    visualizationWindow2.setVisible(true);
    nuevocontrol.setVisible(true);
    }
}
