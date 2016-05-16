package Model;

import com.sun.j3d.loaders.IncorrectFormatException;
import com.sun.j3d.loaders.ParsingErrorException;
import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import java.io.FileNotFoundException;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;

/**
 *
 * @author ANTONIO DAVID LÓPEZ MACHADO Y JAVIER MARTINEZ MONTILLA
 */
public class Nave extends BranchGroup{
    //NUEVO
    Nave(String ruta, int tipo){
        this.setPickable(false);
        
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
        switch(tipo){
            case 1:
                this.addChild(modelo.getSceneGroup());
                break;
            case 2:
                TransformGroup escala2 = crece(1.75f);
                escala2.addChild(modelo.getSceneGroup());
                this.addChild(escala2);
                break;
            case 3:
                Transform3D rot1 = new Transform3D();
                rot1.rotX(-90.0);
                Transform3D rot2 = new Transform3D();
                rot2.rotZ(-165.0);
                TransformGroup t1 = new TransformGroup(rot1);
                TransformGroup t2 = new TransformGroup(rot2);
                TransformGroup escala3 = crece(2.25f);
                
                escala3.addChild(modelo.getSceneGroup());
                t2.addChild(escala3);
                t1.addChild(t2);
                this.addChild(t1);
                break;
            case 4:
                Transform3D r1 = new Transform3D();
                r1.rotX(-89.5);
                Transform3D r2 = new Transform3D();
                r2.rotZ(-89.5);
                TransformGroup tr1 = new TransformGroup(r1);
                TransformGroup tr2 = new TransformGroup(r2);
                TransformGroup escala4 = crece(2.5f);
                
                escala4.addChild(modelo.getSceneGroup());
                tr2.addChild(escala4);
                tr1.addChild(tr2);
                this.addChild(tr1);
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
