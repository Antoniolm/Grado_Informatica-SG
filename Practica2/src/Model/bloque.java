/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Primitive;
import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3f;

/**
 *
 * @author LENOVO
 */
public class bloque extends BranchGroup {
    
    private int id;
    private Box box;
    public bloque(Vector3f vector){
        
        Appearance ap=new Appearance();
        ap.setPolygonAttributes(new PolygonAttributes(PolygonAttributes.POLYGON_FILL, PolygonAttributes.CULL_BACK, 0.0f));
        box = new Box(0.7f, 1.0f, 0.7f,
            Primitive.GENERATE_NORMALS | 
            Primitive.GENERATE_TEXTURE_COORDS |
            Primitive.ENABLE_APPEARANCE_MODIFY, ap);
        
        TransformGroup translacion = new TransformGroup();
        Transform3D transformtranslation = new Transform3D();
        transformtranslation.setTranslation(vector);
        translacion.setTransform(transformtranslation); 
        
       translacion.addChild(box);
       this.addChild(translacion);
    }
    public int getId(){
        return id;
        
    }
    public Box getBox(){
        return box;
    
    }
    public void activarFallo(){
    
    }
    public void activarAcierto(){
    
    }
}
