package Model;

import GUI.Control;
import GUI.Visualization;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.io.IOException;
import java.util.ArrayList;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Locale;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.VirtualUniverse;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

/**
 *
 * @author ANTONIO DAVID LÓPEZ MACHADO, JAVIER MARTINEZ MONTILLA, MANUEL ALBERTO LAFUENTE ARANDA
 */
public class Partida {
    private Fondo background;
    Tablero tablaAzul,tablaRoja;
    String hayganador;
    Control nuevocontrol;
    Picking picar;
    
    public Partida() throws IOException{

    Canvas3D canvas = new Canvas3D (SimpleUniverse.getPreferredConfiguration());
    canvas.setSize(1000, 800);
    hayganador="";
    // Se construyen las ventanas de visualización
     Visualization visualizationWindow2 = new Visualization (canvas,1000,800,750,0);
     
    // Se crea el universo
    VirtualUniverse universe = new VirtualUniverse();
    Locale local=new Locale(universe);
    
    //Camaras jugador Azul
    Camara ataqueAzul=new Camara(canvas, 60.0f, 0.02f, 40.0f,0.01f,45,new Point3d(0.0,14.0,33.0), new Point3d(0.0,13.25,0.0), new Vector3d(0,1,0));
    ataqueAzul.removeCanvas();
    Camara generalAzul=new Camara(canvas, 60.0f, 0.02f, 40.0f,0.01f,100,new Point3d (0,10,25), new Point3d (0,0,15), new Vector3d (0,1,0));
    generalAzul.removeCanvas();
    
    Camara ataqueRojo=new Camara(canvas, 60.0f, 0.02f, 40.0f,0.01f,45,new Point3d(0.0,14.0,-33.0), new Point3d(0.0,13.25,0.0), new Vector3d(0,1,0));
    ataqueRojo.removeCanvas();
    Camara generalRojo=new Camara(canvas, 60.0f, 0.02f, 40.0f,0.01f,100,new Point3d (0,10,-25), new Point3d (0,0,-15), new Vector3d (0,1,0));
    generalRojo.removeCanvas();
    
    
    //Compilamos todas las camaras
    ataqueAzul.compile();
    generalAzul.compile();
    ataqueRojo.compile();
    generalRojo.compile();
    
    //Array de camaras variables
    ArrayList<Camara> camaras=new ArrayList<Camara>();
    camaras.add(generalAzul);
    camaras.add(ataqueAzul);
    camaras.add(generalRojo);
    camaras.add(ataqueRojo);
    
    
    picar=new Picking(canvas,this);
    //Ventana de control
    nuevocontrol=new Control(camaras,picar);
    generalAzul.addCanvas();
    
    local.addBranchGraph(ataqueAzul);
    local.addBranchGraph(generalAzul);
    local.addBranchGraph(ataqueRojo);
    local.addBranchGraph(generalRojo);

    
    //Se crea la luz ambiental y la compilamos
    Luz aLight= new Luz();
    aLight.compile();
    
    // Se crea y se añade el fondo y la compilamos
    background = new Fondo();
    background.compile();
    
   //Añadimos las naves al locale
   //NUEVO
   /*Nave nave1 = new Nave("naves/E-TIE-I/E-TIE-I.obj", 1,false,new Vector3f(5, 3, 4.3f));
   Transform3D trasn1 = new Transform3D();
   trasn1.setTranslation(new Vector3f(5, 3, 4.3f));
   TransformGroup posn1 = new TransformGroup(trasn1);
   posn1.addChild(nave1);
   BranchGroup n1 = new BranchGroup();
   n1.addChild(posn1);
   local.addBranchGraph(n1);*/
   
   /*Nave nave2 = new Nave("naves/naveEspacial/naveEspacial.obj", 2,false,new Vector3f(5, 3, 4.3f));
   Transform3D trasn2 = new Transform3D();
   trasn2.setTranslation(new Vector3f(-5, 3, 5));
   TransformGroup posn2 = new TransformGroup(trasn2);
   posn2.addChild(nave2);
   BranchGroup n2 = new BranchGroup();
   n2.addChild(posn2);
   local.addBranchGraph(n2);
   /*
   Nave nave3 = new Nave("naves/FA-22_Raptor/FA-22_Raptor.obj", 3,false,new Vector3f(5, 3, 4.3f));
   Transform3D trasn3 = new Transform3D();
   trasn3.setTranslation(new Vector3f(5, 3, 14));
   TransformGroup posn3 = new TransformGroup(trasn3);
   posn3.addChild(nave3);
   BranchGroup n3 = new BranchGroup();
   n3.addChild(posn3);
   local.addBranchGraph(n3);
   
   Nave nave4 = new Nave("naves/IronHide/RB-IronHide.obj", 4,false,new Vector3f(5, 3, 4.3f));
   Transform3D trasn4 = new Transform3D();
   trasn4.setTranslation(new Vector3f(-4f, 4f, 15f));
   TransformGroup posn4 = new TransformGroup(trasn4);
   posn4.addChild(nave4);
   BranchGroup n4 = new BranchGroup();
   n4.addChild(posn4);
   local.addBranchGraph(n4);*/
   //FIN NUEVO
    //Color de ese tablero
   Color3f color=new Color3f(0.0f, 0.9f, 1.0f);
   tablaAzul=new Tablero(color,color,"plantillas/fichero2.txt");
   
   //Creamos la segunda parte del tablero
    TransformGroup rotacion3 = new TransformGroup();
    Transform3D rotacionx = new Transform3D();
    rotacionx.rotY(Math.PI);
    rotacion3.setTransform(rotacionx);
   
    //Color de ese tablero
    Color3f color3=new  Color3f(1.0f,0.4f,0.4f);
    tablaRoja=new Tablero(color3,color3,"plantillas/fichero3.txt");
    BranchGroup bg=new BranchGroup();
    
    rotacion3.addChild(tablaRoja);
    bg.addChild(rotacion3);
    bg.addChild(tablaAzul);
    
    //Agregamos el picking
    picar=new Picking(canvas,this);
    picar.setSchedulingBounds(new BoundingSphere(new Point3d(0,0,0),300.0f));
    picar.setStatus(bg);
    bg.addChild(picar);
    
    
    local.addBranchGraph(bg);
    
    //Añadimos al locale los branchgraph, luz ambiental y fondo
    local.addBranchGraph(aLight);
    local.addBranchGraph(background);
    
    // Se muestra la ventana
    visualizationWindow2.setVisible(true);
    nuevocontrol.setVisible(true);
    }
    public boolean cambiarTurno(int x,int y){//Hacemos el cambio de camaras tmb
        boolean salida=false;
        boolean turnoAzul=nuevocontrol.getTurno();
        if(turnoAzul){
            salida=tablaRoja.posicionAtaque(x,y);
            if(tablaRoja.comprobarGanador())
                hayganador="Jugador Azul";
        }
        else{
            salida=tablaAzul.posicionAtaque(x,y);
            if(tablaAzul.comprobarGanador()){
                hayganador="Jugador Rojo";
            }
        }
        return salida;
    }
    public String getGanador(){
        return hayganador;
    }
    //NUEVO2
    public String getResultadoAtaque(int x, int y){
        String resultado;
        boolean turnoAzul=nuevocontrol.getTurno();
        boolean hundido = true;
        boolean ctrlizq=true,ctrldch=true,ctrlup=true,ctrldown=true;
        if(turnoAzul){
            char naveActual= tablaRoja.getPosValor(x, y);
            tablaRoja.setPosValor(x, y, '5');
            int rango = Integer.parseInt(naveActual+"") -1;
            int i = rango;
            while(hundido && i!=0){
                for(int j=1; j<=rango; j++, i--){
                    //Comprobación lado izquierdo
                    if(ctrlizq && (x-j)>=0 ){
                        if(tablaRoja.getPosValor(x-j, y)==naveActual)
                            hundido = false;
                        else 
                           if(tablaRoja.getPosValor(x-j, y)=='0')
                            ctrlizq=false;    
                    }    
                    //Comprobación lado derecho
                    if(ctrldch && (x+j)<tablaRoja.getsizex()){
                        if( tablaRoja.getPosValor(x+j, y)==naveActual)
                            hundido = false;
                        else if(tablaRoja.getPosValor(x+j, y)=='0')
                            ctrldch=false;
                    }
                    //Comprobación lado superior
                    if(ctrlup && (y-j)>=0 ){
                        if(tablaRoja.getPosValor(x, y-j)==naveActual)
                            hundido = false;
                        else if(tablaRoja.getPosValor(x, y-j)=='0')
                            ctrlup=false;
                    }
                    //Comprobación lado inferior
                    if(ctrldown && (y+j)<tablaRoja.getsizey()){
                        if(tablaRoja.getPosValor(x, y+j)==naveActual)
                            hundido = false;
                        if(tablaRoja.getPosValor(x, y+j)=='0')
                            ctrldown=false;
                    }
                }                
            }
        }
        else{
            char naveActual= tablaAzul.getPosValor(x, y);
            tablaAzul.setPosValor(x, y, '5');
            int rango = Integer.parseInt(naveActual+"") -1;
            int i = rango;
            while(hundido && i!=0){
                for(int j=1; j<=rango; j++, i--){
                    //Comprobación lado izquierdo
                    if((x-j)>=0 && tablaAzul.getPosValor(x-j, y)==naveActual)
                        hundido = false;
                    //Comprobación lado derecho
                    if((x+j)<tablaAzul.getsizex() &&  tablaAzul.getPosValor(x+j, y)==naveActual)
                        hundido = false;
                    //Comprobación lado superior
                    if((y-j)>=0 && tablaAzul.getPosValor(x, y-j)==naveActual)
                        hundido = false;
                    //Comprobación lado inferior
                    if((y+j)<tablaAzul.getsizey() &&  tablaAzul.getPosValor(x, y+j)==naveActual)
                        hundido = false;
                }                
            }
        }
        if(hundido)
            return "hundido";
        else
            return "tocado";
    }
    //FIN NUEVO2
    
    //public boolean getTurno(){
    //    
    //}
}
