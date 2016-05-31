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
    boolean terminada;
    static int cont;
    static boolean camAtaque;
    public Partida() throws IOException{

    Canvas3D canvas = new Canvas3D (SimpleUniverse.getPreferredConfiguration());
    canvas.setSize(1000, 800);
    hayganador="";
    terminada=false;
    camAtaque=false;
    cont=0;
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
    //Camaras jugador Rojo
    Camara ataqueRojo=new Camara(canvas, 60.0f, 0.02f, 40.0f,0.01f,45,new Point3d(0.0,14.0,-33.0), new Point3d(0.0,13.25,0.0), new Vector3d(0,1,0));
    ataqueRojo.removeCanvas();
    Camara generalRojo=new Camara(canvas, 60.0f, 0.02f, 40.0f,0.01f,100,new Point3d (0,10,-25), new Point3d (0,0,-15), new Vector3d (0,1,0));
    generalRojo.removeCanvas();
    //Camara ganador
    Camara camaraGanador=new Camara(canvas, 60.0f, 0.02f, 40.0f,0.01f,45,new Point3d(0.0,35.0,33.0), new Point3d(0.0,34.25,0.0), new Vector3d(0,1,0));
    camaraGanador.removeCanvas();
    
    //Compilamos todas las camaras
    ataqueAzul.compile();
    generalAzul.compile();
    ataqueRojo.compile();
    generalRojo.compile();
    camaraGanador.compile();
    
    //Array de camaras variables
    ArrayList<Camara> camaras=new ArrayList<Camara>();
    camaras.add(generalAzul);
    camaras.add(ataqueAzul);
    camaras.add(generalRojo);
    camaras.add(ataqueRojo);
    camaras.add(camaraGanador);
    
    
    picar=new Picking(canvas,this);
    //Ventana de control
    nuevocontrol=new Control(camaras,this);
    generalAzul.addCanvas();
    
    local.addBranchGraph(ataqueAzul);
    local.addBranchGraph(generalAzul);
    local.addBranchGraph(ataqueRojo);
    local.addBranchGraph(generalRojo);
    local.addBranchGraph(camaraGanador);

    
    //Se crea la luz ambiental y la compilamos
    Luz aLight= new Luz();
    aLight.compile();
    
    // Se crea y se añade el fondo y la compilamos
    background = new Fondo();
    background.compile();
   
    //Creamos el cartel para el ganador
    Cartel ganador=new Cartel("imgs/ganador.png");
    ganador.compile();
    
    local.addBranchGraph(ganador);
    
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
    
    
    public boolean realizarAtaque(int x,int y){//Hacemos el cambio de camaras tmb
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
    
    
    public String getResultadoAtaque(int x, int y){
        String resultado;
        boolean turnoAzul=nuevocontrol.getTurno();
        boolean hundido = true;
        boolean ctrlizq=true,ctrldch=true,ctrlup=true,ctrldown=true;
        if(turnoAzul){
            hundido=tablaRoja.comprobarEstadoNave(x, y);
        }
        else{
            hundido=tablaAzul.comprobarEstadoNave(x, y);
        }
        if(hundido)
            return "hundido";
        else
            return "tocado";
    }
    
    
     public void setCont(int valor){
        cont=valor;
    }
     
     
    public void setCamAtaque(boolean valor){
        camAtaque=valor;
    }
    
    
    public int getCont(){
        return cont;
    }
    
    
    public void procesarAccion(Bloque blqactual){
        if (!blqactual.getActivado() && cont == 0 && camAtaque && !terminada) {
            //objeto.activarFallo();
            int x = blqactual.getX();
            int y = blqactual.getY();
            boolean estado = realizarAtaque(x, y);
            //NUEVO2
            String resultAtaque = "vacío";
            //FIN NUEVO2
            if (estado) {
                blqactual.activarAcierto();
                if (!getGanador().isEmpty()) {
                    nuevocontrol.setAreaMensajes("Ganador " + getGanador());
                    nuevocontrol.selectCamGanador();
                    terminada = true;
                    
                } //NUEVO2
                else {
                    resultAtaque = getResultadoAtaque(x, y);
                    if (resultAtaque != "vacío") {
                        switch (resultAtaque) {
                            case "tocado":
                                nuevocontrol.setAreaMensajes("¡Tocado!");
                                break;
                            case "hundido":
                                nuevocontrol.setAreaMensajes("¡¡Hundido!!");
                                break;
                        }
                    }
                }
                //FIN NUEVO2
                cont = 0;
                nuevocontrol.desactivarCambioTurno();
            } else {
                blqactual.activarFallo();
                nuevocontrol.setAreaMensajes("¡Has fallado!");
                cont++;
                nuevocontrol.activarCambioTurno();
            }
        }
    }
}
