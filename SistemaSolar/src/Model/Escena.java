/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import GUI.Visualization;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.universe.SimpleUniverse;
import javax.media.j3d.Alpha;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
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
public class Escena {
    public Escena(){
     // Se obtiene la configuración gráfica del sistema y se crea el Canvas3D que va a mostrar la imagen
    Canvas3D canvas = new Canvas3D (SimpleUniverse.getPreferredConfiguration());
    canvas.setSize(800, 600);
    // Se construye la ventana de visualización
    Visualization visualizationWindow = new Visualization (canvas);
    
    // Se crea el universo y la rama de la vista con ese canvas
    SimpleUniverse universe = new SimpleUniverse (canvas);
    universe.getViewingPlatform().setNominalViewingTransform();
    // Como raíz se usa un BrachGroup
    Astro sol=new Estrella(0.7f,0.0f,0.0f,0.0f);
    Astro tierra=new Planeta(0.7f,0.0f,0.0f,0.0f);
    BranchGroup root = new BranchGroup();
    root.addChild (sol);
    BranchGroup planetaTierra = new BranchGroup();

    // Se cuelga la raiz del grafo al universo
    universe.addBranchGraph(root);
    universe.addBranchGraph(sol.add(tierra));
    
    
    // Se muestra la ventana
    visualizationWindow.setVisible(true);
    }
}
