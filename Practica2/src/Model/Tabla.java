/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Primitive;
import java.awt.Color;
import java.util.ArrayList;
import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.PolygonAttributes;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3f;

/**
 *
 * @author LENOVO
 */
public class Tabla extends BranchGroup{
    
    public Tabla(){
        
        ////Campo horizontal
        ////Translación
        Appearance ap=new Appearance();
        ap.setPolygonAttributes(new PolygonAttributes(PolygonAttributes.POLYGON_FILL, PolygonAttributes.CULL_BACK, 0.0f));
        Color3f color2=new Color3f(Color.red);
        ColoringAttributes color=new ColoringAttributes(color2 ,ColoringAttributes.SHADE_FLAT);
        ap.setColoringAttributes(color);
        Box box = new Box(11.0f, 2.0f, 11.0f,
            Primitive.GENERATE_NORMALS | 
            Primitive.GENERATE_TEXTURE_COORDS |
            Primitive.ENABLE_APPEARANCE_MODIFY, ap);
        
        bloque aux;
        ArrayList<ArrayList< bloque>> matrizbloques=new ArrayList<ArrayList<bloque>>();
        ArrayList<bloque> auxarray;
        Vector3f vector=new Vector3f(-9f,2.0f,-9f);
        for(int i=0;i<10;i++){
            //vector=new Vector3f(-9f,2.0f,-9f);
            vector.x=-9f;
            auxarray=new ArrayList<bloque>();
            for(int j=0;j<10;j++){
                aux=new bloque((Vector3f) vector.clone());
                this.addChild(aux);
                auxarray.add(new bloque((Vector3f) vector.clone()));
                vector.x+=2f;
           }
            matrizbloques.add(auxarray);
           vector.z+=2;
        }
        this.addChild(box);
        
        
        //// Campo vertical
        /////ROTACION Z
       ///// Translacion
        
        
        
        
    }
}
