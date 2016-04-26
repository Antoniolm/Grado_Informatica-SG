package Model;

import com.sun.j3d.utils.image.TextureLoader;
import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Material;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.vecmath.Color3f;

/**
 *
 * @author ANTONIO DAVID LÓPEZ MACHADO Y JAVIER MARTINEZ MONTILLA
 */
public class Anillo extends BranchGroup{
    private double radioInterior;
    private double radioExterior;
    private double tiemposRotPropio;
    public Anillo(String textura,double radioInt,double radioExt/*,double tiempoRotPro*/){
         Appearance appearance = new Appearance();
        Texture aTexture = new TextureLoader (textura, null).getTexture();
        appearance.setTexture (aTexture);
        appearance.setMaterial (new Material (
            new Color3f (0.20f, 0.20f, 0.20f),   // Color ambiental
            new Color3f (0.00f, 0.00f, 0.00f),   // Color emisivo
            new Color3f (0.50f, 0.50f, 0.50f),   // Color difuso
            new Color3f (0.70f, 0.70f, 0.70f),   // Color especular
            17.0f ));                            // Brillo
        TextureAttributes ta = new TextureAttributes();
        ta.setTextureMode(TextureAttributes.MODULATE);
        appearance.setTextureAttributes(ta);
        
        
        radioExterior=radioExt;
        radioInterior=radioInt;
        Torus anill=new Torus((float)radioExt,(float)radioInt,40,2,appearance);
        this.setPickable(false);
        this.addChild(anill);
    }
}
