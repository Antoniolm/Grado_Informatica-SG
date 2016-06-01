package Model;

import GUI.Control;
import GUI.Visualization;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
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
    TransformGroup transAlGanar;
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
   
    //TransformGroup que utilizaremos para cambiar de posicion las dos camaras de ataque
    //asi cuando un jugador gane la partida solo hace falta añadirle la translación a este
    //transformGroup y se vera el cartel de ganador
    transAlGanar=new TransformGroup();
    transAlGanar.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    //añadimos las dos camaras afectadas
    transAlGanar.addChild(ataqueAzul);
    transAlGanar.addChild(ataqueRojo);
    //creamos un bg para poder añadir esas dos camaras que tendran
    //ese cambio de posicion al terminar la partida
    BranchGroup camarasFlex=new BranchGroup();
    camarasFlex.addChild(transAlGanar);
    
    //Compilamos todas las camaras
    camarasFlex.compile();
    generalAzul.compile();
    generalRojo.compile();
    
    //Array de camaras variables
    ArrayList<Camara> camaras=new ArrayList<Camara>();
    camaras.add(generalAzul);
    camaras.add(ataqueAzul);
    camaras.add(generalRojo);
    camaras.add(ataqueRojo);
    
    //Ventana de control
    nuevocontrol=new Control(camaras,this);
    generalAzul.addCanvas();
    
    //Se crea la luz ambiental y la compilamos
    Luz aLight= new Luz();
    aLight.compile();
    
    // Se crea y se añade el fondo y la compilamos
    background = new Fondo();
    background.compile();
   
    //Creamos el cartel para el ganador
    Cartel cartel=new Cartel("imgs/ganador.png");
    cartel.compile();

    //Color de ese tablero
   Color3f color=new Color3f(0.0f, 0.9f, 1.0f);
   tablaAzul=new Tablero(color,color,selecPlantAleatoria());
   
   //Creamos la segunda parte del tablero
   //El cual se vera afectada por una rotacion en el eje Y
    TransformGroup rotacion3 = new TransformGroup();
    Transform3D rotacionx = new Transform3D();
    rotacionx.rotY(Math.PI);
    rotacion3.setTransform(rotacionx);
   
    //Color de ese tablero
    Color3f color3=new  Color3f(1.0f,0.4f,0.4f);
    tablaRoja=new Tablero(color3,color3,selecPlantAleatoria());
    BranchGroup bg=new BranchGroup();
    
    rotacion3.addChild(tablaRoja);
    bg.addChild(rotacion3);
    bg.addChild(tablaAzul);
    

    
    //Agregamos el picking
    picar=new Picking(canvas,this);
    picar.setSchedulingBounds(new BoundingSphere(new Point3d(0,0,0),300.0f));
    picar.setStatus(bg);
    bg.addChild(picar);
    
    //Compilamos los tableros
    bg.compile();
    
    //Añadimos nuestros tableros al locale
    local.addBranchGraph(bg);
    //Añadimos al locale los branchgraph, luz ambiental fondo y cartel
    local.addBranchGraph(aLight);
    local.addBranchGraph(background);
    local.addBranchGraph(cartel);
    //Añadimos nuestras camaras al locale
    local.addBranchGraph(camarasFlex);
    local.addBranchGraph(generalAzul);
    local.addBranchGraph(generalRojo);

    // Se muestra la ventana
    visualizationWindow2.setVisible(true);
    nuevocontrol.setVisible(true);
    }
    
    /**
    *  Realiza la accion de ataque, envia la posicion x,y del bloque que se ha atacado
    *  al tablero enemigo(según el turno actual) y nos devuelve si ha acertado o no
    *  tambíen nos realiza la comprobación de si hay ya un ganador o no.
    */
    public boolean realizarAtaque(int x,int y){
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
    
    /**
    *  Comprueba si se ha realizado un hundimiento/tocado de un nave 
    *  según el turno se realiza en un tablero o en otro.
    */
    public String getResultadoAtaque(int x, int y){
        boolean turnoAzul=nuevocontrol.getTurno();
        boolean hundido = true;
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
     /**
     *  Método que se encarga de gestionar el ataque de un jugador(si falla o acierta),
     *  cuando se ha tocado/hundido un nave y cuando un jugador ha ganado.
     */
    public void procesarAccion(Bloque blqActual){
        if (!blqActual.getActivado() && cont == 0 && camAtaque && !terminada) {
            //objeto.activarFallo();
            int x = blqActual.getX();
            int y = blqActual.getY();
            boolean estado = realizarAtaque(x, y);
            
            String resultAtaque = "vacío";
         
            if (estado) {
                blqActual.activarAcierto();
                if (!getGanador().isEmpty()) {
                    nuevocontrol.setAreaMensajes("Ganador " + getGanador());
                    //Subimos la camara a la posición donde esta nuestro cartel
                    //de ganador
                    Transform3D translacion=new Transform3D();
                    translacion.setTranslation(new Vector3f(0.0f,26.0f,0.0f));
                    transAlGanar.setTransform(translacion);
                    terminada = true;
                    
                } 
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
                
                cont = 0;
                nuevocontrol.desactivarCambioTurno();
            } else {
                blqActual.activarFallo();
                nuevocontrol.setAreaMensajes("¡Has fallado!");
                cont++;
                nuevocontrol.activarCambioTurno();
            }
        }
    }
    
    
     /**
     *  Seleccionamos de forma aleatoria la plantilla de naves que cargara cada jugador
     */
    public String selecPlantAleatoria(){
        String salida="";
        Random rand = new Random();
        int randomNum = rand.nextInt((2 - 0) + 1);
        switch(randomNum){
            case 0:
                 salida="plantillas/fichero0.txt";
                 break;
            case 1:
                 salida="plantillas/fichero1.txt";
                 break;
            case 2:
                 salida="plantillas/fichero2.txt";
                 break;
        
        }
        return salida;   
    }
}
