/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.image.TextureLoader;
import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.Material;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3f;

/**
 *
 * @author LENOVO
 */
public class Cartel extends BranchGroup {
    public Cartel(String textura){
        TransformGroup translacion = new TransformGroup();
        Vector3f vector = new Vector3f(0.0f, 35.0f, 1.0f);
        Transform3D trans = new Transform3D();
        trans.setTranslation(vector);
        translacion.setTransform(trans);
        
        Appearance appearance = new Appearance();
        
        Texture aTexture = new TextureLoader (textura, null).getTexture();
        appearance.setTexture (aTexture);
        appearance.setMaterial (new Material (
            new Color3f (1.00f, 1.00f, 1.00f),   // Color ambiental
            new Color3f (1.00f, 1.00f, 1.00f),   // Color emisivo
            new Color3f (0.50f, 0.50f, 0.50f),   // Color difuso
            new Color3f (0.70f, 0.70f, 0.70f),   // Color especular
            17.0f ));                            // Brillo
        TextureAttributes ta = new TextureAttributes();
        ta.setTextureMode(TextureAttributes.MODULATE);
        appearance.setTextureAttributes(ta); 
        
        
        
        Box box = new Box(14.0f, 11.0f, 1.0f, Primitive.GENERATE_NORMALS | 
        Primitive.GENERATE_TEXTURE_COORDS |
        Primitive.ENABLE_APPEARANCE_MODIFY,appearance);
        translacion.addChild(box);
        this.addChild(translacion);
    
    }
}
