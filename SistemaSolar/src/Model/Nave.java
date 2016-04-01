/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.sun.j3d.loaders.IncorrectFormatException;
import com.sun.j3d.loaders.ParsingErrorException;
import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Texture;
import javax.vecmath.Point3f;

/**
 *
 * @author LENOVO
 */
public class Nave extends BranchGroup{
    private ArrayList<Point3f> recorrido;
    //ArrayList<Point3f> recorrid,
    public Nave(String textur){
        Scene escena=null;
        ObjectFile planetExpress =new ObjectFile(ObjectFile.RESIZE | ObjectFile.STRIPIFY | ObjectFile.TRIANGULATE,(float) ( Math.PI/4 ) ) ;
        try {
            escena=planetExpress.load(textur);
        } catch (FileNotFoundException | ParsingErrorException | IncorrectFormatException e) {
            System.err.println(e);
            System.exit(1);
        }
        this.addChild(escena.getSceneGroup());
    }
}
