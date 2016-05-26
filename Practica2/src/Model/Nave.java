package Model;

import com.sun.j3d.loaders.IncorrectFormatException;
import com.sun.j3d.loaders.ParsingErrorException;
import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import java.io.FileNotFoundException;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3f;

/**
 *
 * @author ANTONIO DAVID LÓPEZ MACHADO, JAVIER MARTINEZ MONTILLA, MANUEL ALBERTO LAFUENTE ARANDA
 */
public class Nave extends BranchGroup{
    //NUEVO
    boolean horizontal;
    Nave(String ruta, int tipo,boolean horizon,Vector3f posicion){
        this.setPickable(false);
        horizontal=horizon;
        //Adición del modelo
        Scene modelo = null;
        ObjectFile nave = new ObjectFile(ObjectFile.RESIZE | ObjectFile.STRIPIFY |
            ObjectFile.TRIANGULATE);

        try{
            modelo = nave.load(ruta);
        }catch(FileNotFoundException e){
            System.err.println(e);
            System.exit(1);
        }catch (ParsingErrorException e) {
            System.err.println (e);
            System.exit(1);
        } catch (IncorrectFormatException e) {
            System.err.println (e);
            System.exit(1);
        }
        
        Transform3D trasn1 = new Transform3D();
        trasn1.setTranslation(posicion);
        TransformGroup posn = new TransformGroup(trasn1);
        Transform3D rotx,roty,rotz;
        switch(tipo){
            case 1:
                posn.addChild(modelo.getSceneGroup());
                this.addChild(posn);
                break;
            case 2:
                TransformGroup escala2 = crece(1.75f);
                escala2.addChild(modelo.getSceneGroup());
                if(horizontal){
                    roty = new Transform3D();
                    roty.rotY(Math.PI/2);
                    TransformGroup t1 = new TransformGroup(roty);
                    t1.addChild(escala2);
                    posn.addChild(t1);
                }
                else{
                    posn.addChild(escala2);
                }
                
                this.addChild(posn);
                break;
            case 3:
                rotx = new Transform3D();
                rotx.rotX(-Math.PI/2);
                rotz = new Transform3D();
                rotz.rotZ(Math.toRadians(-180.0));
                TransformGroup t1 = new TransformGroup(rotx);
                TransformGroup t2 = new TransformGroup(rotz);
                TransformGroup escala3 = crece(3.00f);
                
                escala3.addChild(modelo.getSceneGroup());
                t2.addChild(escala3);
                t1.addChild(t2);
                
                if(horizontal){
                    roty = new Transform3D();
                    roty.rotY(Math.PI/2);
                    TransformGroup t4 = new TransformGroup(roty);
                    t4.addChild(t1);
                    posn.addChild(t4);
                }
                else{
                    posn.addChild(t1);
                }
                this.addChild(posn);
                
                break;
            case 4:
                
                TransformGroup escala4 = crece(4.0f);
                escala4.addChild(modelo.getSceneGroup());
                if(horizontal){
                    roty = new Transform3D();
                    roty.rotY(Math.PI/2);
                    TransformGroup t4 = new TransformGroup(roty);
                    t4.addChild(escala4);
                    posn.addChild(t4);
                }
                else{
                    posn.addChild(escala4);
                }
                this.addChild(posn);
                break;
        }
    }
    
    private TransformGroup crece(float crecimiento){
        TransformGroup esc = new TransformGroup();
        Transform3D tr = new Transform3D();
        tr.setScale(crecimiento);
        esc.setTransform(tr);
        
        return esc;
    }
    //FIN NUEVO
}
