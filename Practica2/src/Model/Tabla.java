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
    
    private ArrayList<ArrayList<bloque>> matrizbloques;
    
    public Tabla(Color3f colorp,boolean pickeable){
        
        ////Campo horizontal
        ////Translación
        Appearance ap=new Appearance();
        ap.setPolygonAttributes(new PolygonAttributes(PolygonAttributes.POLYGON_FILL, PolygonAttributes.CULL_BACK, 0.0f));
        ColoringAttributes color=new ColoringAttributes(colorp ,ColoringAttributes.SHADE_FLAT);
        ap.setColoringAttributes(color);
        Box box = new Box(11.0f, 1.0f, 11.0f, Primitive.ENABLE_APPEARANCE_MODIFY, ap);
        box.setPickable(false);
        matrizbloques=new ArrayList<ArrayList<bloque>>();
        ArrayList<bloque> auxArray;
        Vector3f vector=new Vector3f(-9f,1.0f,-9f);
        for(int i=0;i<10;i++){
            vector.x=-9f;
            auxArray=new ArrayList<bloque>();
            for(int j=0;j<10;j++){
                auxArray.add(new bloque(vector,pickeable));
                this.addChild(auxArray.get(j));
                vector.x+=2f;
           }
            matrizbloques.add(auxArray);
           vector.z+=2;
        }
        this.addChild(box);
          
    }
    public void añadirNaves(ArrayList<String> naves){
        for(int i =0;i<naves.size();i++){
            for(int j=0;j<naves.get(i).length();j++){
                if(naves.get(i).charAt(j)!='0'){
                    matrizbloques.get(i).get(j).activarAcierto();
                }
            
            }
        }
    
    }
}
