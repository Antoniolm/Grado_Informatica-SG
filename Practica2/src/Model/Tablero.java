package Model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3f;

/**
 *
 * @author ANTONIO DAVID LÓPEZ MACHADO, JAVIER MARTINEZ MONTILLA, MANUEL ALBERTO LAFUENTE ARANDA
 */
public class Tablero extends BranchGroup{
        ArrayList<String> matrizNaves;
        Tabla vertical,horizontal;
        int contadornaves;
        boolean perdedor;
        
    public Tablero(Color3f color,Color3f color2,String fichero) throws IOException{
         vertical=new Tabla(color,true);
         horizontal=new Tabla(color2,false);
         
         //Cargamos las naves y las introducimos en el tablero
         cargarNaves(fichero);
         contadornaves=2;
         perdedor=false;
         horizontal.añadirNaves(matrizNaves);
         
         TransformGroup translacionverti=new TransformGroup();
         Vector3f vector=new Vector3f(0.0f,13.0f,1.0f);
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
    public boolean posicionAtaque(int x,int y){
        boolean salida=false;
        if(matrizNaves.get(y).charAt(x)!='0'){
            salida=true;
            horizontal.setFallo(x, y);
            //System.out.println("Acierto -> x:"+x+" y:"+y);
            //String s=matrizNaves.get(y).substring(0,x)+'0'+matrizNaves.get(y).substring(x+1);
            contadornaves--;
            //matrizNaves.set(y,s);
            
            /*IMPRIMIR MATRIZNAVES*/
            /*for(String i:matrizNaves){
                System.out.println(i);
            }*/
            //en otro metodo comprobamos si no esta a cero aun para saber el ganador
            //comprobarGanador();
        }
        else{
            horizontal.setAgua(x, y);
            System.out.println("Fallo -> x:"+x+" y:"+y);
        }
        return salida;
    }

    public boolean comprobarGanador() {
        //int cont=0;
        /*for(String i:matrizNaves){
            for(int j=0;j<i.length();j++){
                if(j=='0')
                    cont++;
            }
        }*/
        if(contadornaves == 0)
            perdedor=true;
        return perdedor;
    }
}
