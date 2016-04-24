/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import javax.media.j3d.Appearance;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.IndexedGeometryArray;
import javax.media.j3d.IndexedTriangleArray;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.vecmath.Point3d;
import javax.vecmath.TexCoord2f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

/**
 *
 * @author ANTONIO DAVID LÓPEZ MACHADO Y JAVIER MARTINEZ MONTILLA
 */
public class Torus extends Shape3D{
        int resolucion,resolucion2;
        float radioExterior,radioInterior;
        public Torus(float radioExt,float radioInt,int res,int res2,Appearance app){
            int[] indices;
        resolucion=res;
        resolucion2=res2;
        radioExterior=radioExt;
        radioInterior=radioInt;
             
       //Realizamos el caculo de los puntos
       Point3d[] vertices=calcularVertices(radioExterior, radioInterior, res, res2);
        indices=calcularIndicesVertices(resolucion, resolucion2);
        TexCoord2f[] texCoords= cal_coordText();
        
       //creamos el objeto IndexedTriangleArray
        IndexedGeometryArray geometria= new IndexedTriangleArray(texCoords.length, GeometryArray.COORDINATES | GeometryArray.NORMALS |  GeometryArray.TEXTURE_COORDINATE_2, indices.length);       
        geometria.setCoordinates (0 , vertices ) ;
        geometria. setCoordinateIndices(0,indices) ;
        geometria.setNormals(0, calnormal_v());
        geometria.setNormalIndices(0, indices);
        geometria.setTextureCoordinates(0, 0, texCoords);
        geometria.setTextureCoordinateIndices(0, 0, calcularIndicesTexCoords(resolucion, resolucion2));
        this.setGeometry(geometria);
        this.setAppearance(app);
    }    
    private Point3d[] calcularVertices(float rad1, float rad2, int res1, int res2) {
        ArrayList<Point3d> mispuntos=new ArrayList<Point3d>();
        //Creamos el primer vertice el cual indica el radio exterior
        Point3d puntoaux=new Point3d((radioExterior-radioInterior)/2,0,0);
        
        float alf2 = (float) (2 * 3.1415 / res2);
        float alf1 = (float) (2 * 3.1415 / res1);
        //Creamos la transformación
        Transform3D rotacionz = new Transform3D();
        rotacionz.rotZ(alf2);
        Transform3D rotaciony = new Transform3D();
        rotaciony.rotY(alf1);
        
        //Creamos la translación inicial para hacer la rotacion en z
        Vector3d vectortrans=new Vector3d(radioExterior-((radioExterior-radioInterior)/2),0.0f,0.0f);
        Transform3D translacion=new Transform3D();
        translacion.setTranslation(vectortrans);
        
        
        //Aplicamos la transformacion en los puntos
        for (int i = 0; i < res1; i++){
            rotaciony.rotY(alf1*i);
            for(int j=0;j<res2;j++){
                puntoaux=new Point3d((radioExterior-radioInterior)/2,0,0);
                rotacionz.rotZ(alf2*j);
                rotacionz.transform(puntoaux);
                translacion.transform(puntoaux);
                rotaciony.transform(puntoaux);
                mispuntos.add(new Point3d(puntoaux));
            }
        }
         Point3d[] vertices = new Point3d[mispuntos.size()];
        mispuntos.toArray(vertices);
        return vertices;
    }
    
    
    private int[] calcularIndicesVertices(int res, int res2) {
        int[] indices = new int[res*res2*6];
        int k=0;
        int cont=0;
        int puntoActual,puntoNextRes,puntoActualSiguiente,puntoNextResSig;
        for(int i=0; i<res; i++) {
            for(int j=0; j<res2; j++) {
                puntoActual=k;
                puntoNextRes=res2+k; //el punto de la siguiente resolucion de vertices
                puntoActualSiguiente=k+1;
                puntoNextResSig=puntoNextRes+1;
                
                if(i==res-1) { //if para el final del primer bucle
                    puntoNextRes=j;
                    puntoNextResSig = j+1;
                }
                if(j==res2-1) { // if para el final del segundo bucle
                    puntoActualSiguiente -= res2;
                    puntoNextResSig -= res2;
                }
                
                indices[cont]=puntoActual%(res*res2);
                indices[cont+1]=(puntoNextResSig)%(res*res2);
                indices[cont+2]=(puntoActualSiguiente)%(res*res2);

                indices[cont+3]=puntoActual%(res*res2);
                indices[cont+4]=(puntoNextRes)%(res*res2);
                indices[cont+5]=(puntoNextResSig)%(res*res2);
                cont+=6;
                k++;
            }
            
        } 
        
        return indices;
    }
    public Vector3f[] calnormal_v(){
	ArrayList<Vector3f> mispuntos=new ArrayList<Vector3f>();;
        Vector3f[] salida;
        //Creamos el primer vertice el cual indica el radio exterior
        Vector3f vectnormal;
        
        float alf2 = (float) (2 * 3.1415 / resolucion2);
        float alf1 = (float) (2 * 3.1415 / resolucion);
        
        //Creamos la transformación
        Transform3D rotacionz = new Transform3D();
        Transform3D rotaciony = new Transform3D();

        //Aplicamos la transformacion en los puntos
        for (int i = 0; i < resolucion; i++){
            rotaciony.rotY(alf1*i);
            for(int j=0;j<resolucion2;j++){
                //Realizamos las diferentes trans al vector normal
                //para obtener bien normalizado cada vertice
                vectnormal=new Vector3f(1.0f,0.0f,0.0f);
                rotacionz.rotZ(alf2*j);
                rotacionz.transform(vectnormal);
                rotaciony.transform(vectnormal);
                vectnormal.normalize();
                mispuntos.add(new Vector3f(vectnormal));
            }
        }
        salida = new Vector3f[mispuntos.size()];
        mispuntos.toArray(salida);
        return salida;
    }
    public TexCoord2f[] cal_coordText(){
        ArrayList<TexCoord2f> coords=new ArrayList<TexCoord2f>();
        TexCoord2f[] salida;
        float num_filas=1.0f/(resolucion+1.0f);
        float num_columnas=1.0f/(resolucion2+1.0f);
        float s, t;
        for(int i=0; i<resolucion+1; i++) {
            s = (float)num_columnas*i;
            for(int j=0; j<resolucion2+1; j++) {
                t = (float)num_filas*j;
                coords.add(new TexCoord2f(s,t));
            }
        }        
        salida=new TexCoord2f[coords.size()];
        coords.toArray(salida);
        return salida;
    } 
    
    private int[] calcularIndicesTexCoords(int res1, int res2) {
        int[] indicesTexCoords = new int[res1*res2*6];
        int cnt = 0, puntoActual, puntoNextRes, puntoActualSiguiente, puntoNextResSig;
        for(int i=0; i<res1; i++) {
            for(int j=0; j<res2; j++) {
                puntoActual = i*(res2+1)+j; //Supuesto puesto actual
                puntoNextRes = puntoActual+(res2+1); //Punto de la siguiente franja
                puntoActualSiguiente = puntoActual+1; //Punto siguiente al actual
                puntoNextResSig = puntoActualSiguiente+(res2+1); // punto siguiente de la otra franja
                
                //Primer triangulo de textura
                indicesTexCoords[cnt] = puntoActual;
                indicesTexCoords[cnt+1] = puntoNextResSig;
                indicesTexCoords[cnt+2] = puntoActualSiguiente;
                
                //Segundo triangulo de textura
                indicesTexCoords[cnt+3] = puntoActual;
                indicesTexCoords[cnt+4] = puntoNextRes;
                indicesTexCoords[cnt+5] = puntoNextResSig;
                cnt+=6;
            }
        }
        
        return indicesTexCoords;
    }
}
