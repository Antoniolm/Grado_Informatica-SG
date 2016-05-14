/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

/**
 *
 * @author LENOVO
 */
public class Tablero extends BranchGroup{
        ArrayList<String> matrizNaves;
    public Tablero(Color3f color,Color3f color2,String fichero) throws IOException{
         Tabla vertical=new Tabla(color,true);
         Tabla horizontal=new Tabla(color2,false);
         
         //Cargamos las naves y las introducimos en el tablero
         cargarNaves(fichero);
         horizontal.añadirNaves(matrizNaves);
         
         TransformGroup translacionverti=new TransformGroup();
         Vector3f vector=new Vector3f(0.0f,14.0f,1.0f);
         Transform3D trans=new Transform3D();
         trans.setTranslation(vector);
         translacionverti.setTransform(trans);
         
         TransformGroup rotacion=new TransformGroup();
         Transform3D rotacionx=new Transform3D();
         rotacionx.rotX(Math.PI/2);
         rotacion.setTransform(rotacionx);
         
         
         TransformGroup translacion=new TransformGroup();
         vector=new Vector3f(0.0f,0.0f,13.0f);
         trans=new Transform3D();
         trans.setTranslation(vector);
         translacion.setTransform(trans);
         
         rotacion.addChild(vertical);
         translacionverti.addChild(rotacion);     
         
         translacion.addChild(horizontal);
         this.addChild(translacion);
         this.addChild(translacionverti);
         
    }
    private void cargarNaves(String fichero) throws FileNotFoundException, IOException{
      String cadena;
      matrizNaves=new ArrayList<String>();
      FileReader f = new FileReader(fichero);
      BufferedReader b = new BufferedReader(f);
      while((cadena = b.readLine())!=null) {
          matrizNaves.add(new String(cadena));
      }
      b.close();
    
    }
    
}
