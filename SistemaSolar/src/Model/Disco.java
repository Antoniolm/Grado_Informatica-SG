/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;
import com.sun.j3d.utils.geometry.Stripifier;
import java.util.ArrayList;
import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.IndexedTriangleStripArray;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;

/**
 *
 * @author LENOVO
 */
public class Disco extends Shape3D{
        float radioExterior,radioInterior;
        int resolucion;
        Appearance appe;
        public Disco(float radioExt,float radioIn,int res,Appearance app){
            radioExterior=radioExt;
            radioInterior=radioIn;
            resolucion=res;
            appe=app;
        GeometryArray geometria=createGeometryInfo();//createIndex();//createGeometryInfo();
        this.setGeometry(geometria);
        this.setAppearance(app);
    }
    private GeometryArray createGeometryInfo(){
         int[] contornospoligono={2};
         int[] verticescontorno={(resolucion),(resolucion)};
   
        ArrayList<Point3d> mispuntosext=new ArrayList<Point3d>();
        ArrayList<Point3d> mispuntosin=new ArrayList<Point3d>();
        //Creamos el primer vertice el cual indica el radio exterior
        Point3d puntoaux=new Point3d(radioExterior,0,0);
        //Creamos el primer vertice el cual indica el radio interior
        Point3d puntoinAux=new Point3d(radioInterior,0,0);
        
        mispuntosext.add(new Point3d(puntoaux));
        mispuntosin.add(new Point3d(puntoinAux));
   
        float alf = (float) (2 * 3.1415 / resolucion);
        //Creamos la transformación
        Transform3D rotacionz = new Transform3D();
        rotacionz.rotZ(alf);
        
        //Aplicamos la transformacion en los puntos
        for (int i = 0; i < resolucion-1; i++) {
            rotacionz.transform(puntoaux);
            mispuntosext.add(new Point3d(puntoaux));
            
            rotacionz.transform(puntoinAux);
            mispuntosin.add(new Point3d(puntoinAux));
        }

        //Creamos las caras del objeto en un ArrayList
        ArrayList<Point3d> cjtovert=new ArrayList<Point3d>();
        for(int i=0;i<(mispuntosext.size());i++){
            cjtovert.add(mispuntosext.get(i));
        }
            
        for(int i=0;i<(mispuntosin.size());i++){   
            cjtovert.add(mispuntosin.get(i));
        }
        
        //Las convertimos a Array
        Point3d[] vertices = new Point3d[cjtovert.size()];
        cjtovert.toArray(vertices);
        
       //creamos el objeto geometryinfo 
        GeometryInfo gi = new GeometryInfo(GeometryInfo.POLYGON_ARRAY);
        gi.setCoordinates(vertices);
        gi.setContourCounts(contornospoligono);
        gi.setStripCounts(verticescontorno);

        NormalGenerator normGen = new NormalGenerator(Math.toRadians(30));
        normGen.generateNormals(gi);
        Stripifier cadenas = new Stripifier();
        cadenas.stripify(gi);
        GeometryArray geometria = gi.getGeometryArray();
        
        return geometria;
    }
    private GeometryArray createIndex(){
        ArrayList<Point3d> mispuntos=new ArrayList<Point3d>();
        //Creamos el primer vertice el cual indica el radio exterior
        Point3d puntoaux=new Point3d(radioExterior,0,0);
        //Creamos el primer vertice el cual indica el radio interior
        Point3d puntoinAux=new Point3d(radioInterior,0,0);
        
        mispuntos.add(new Point3d(puntoinAux));
        mispuntos.add(new Point3d(puntoaux));

   
        float alf = (float) (2 * 3.1415 / resolucion);
        //Creamos la transformación
        Transform3D rotacionz = new Transform3D();
        rotacionz.rotZ(alf);

        //Aplicamos la transformacion en los puntos
        for (int i = 0; i < resolucion; i++) {
            rotacionz.transform(puntoinAux);
            mispuntos.add(new Point3d(puntoinAux));
            
            rotacionz.transform(puntoaux);
            mispuntos.add(new Point3d(puntoaux));
            

        }
        
       int[] indices = new int[resolucion*6];
        //Creamos las caras
       int k=0;
        for(int i=0;i<(resolucion*6)-1;i=i+6){
            //Primera cara con el mismo verticeorigen
            indices[i]=k;
            indices[i+1]=k+1;
            indices[i+2]=k+3;        
            //Segunda cara con el mismo vertice origen
            indices[i+3]=k;
            indices[i+4]=k+3;
            indices[i+5]=k+2;
            k=k+2;
        }
        //Numero de cadenas 
        Point3d[] vertices = new Point3d[mispuntos.size()];
        mispuntos.toArray(vertices);
        int[] cadenas = { indices.length };
        IndexedTriangleStripArray geometri=new IndexedTriangleStripArray(vertices.length, GeometryArray.COORDINATES, indices.length, cadenas);
        geometri.setCoordinates(0,vertices);
        geometri.setCoordinateIndices(0,indices);
        return (GeometryArray)geometri;
        
    }
}
