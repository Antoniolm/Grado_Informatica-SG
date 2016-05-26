package Model;

import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.pickfast.PickCanvas;
import java.awt.AWTEvent;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import javax.media.j3d.Behavior;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Node;
import javax.media.j3d.PickInfo;
import javax.media.j3d.WakeupOnAWTEvent;

/**
 *
 * @author ANTONIO DAVID LÓPEZ MACHADO, JAVIER MARTINEZ MONTILLA, MANUEL ALBERTO LAFUENTE ARANDA
 */
public class Picking extends Behavior{
    private WakeupOnAWTEvent condition;
    private PickCanvas pickCanvas ;
    private Canvas3D canvas ;
    private Partida partida;
    static int cont;
    static boolean camAtaque;
    public boolean terminada;
    
    public Picking (Canvas3D aCanvas,Partida part) {
        partida=part;
        canvas = aCanvas ;
        condition = new WakeupOnAWTEvent (MouseEvent.MOUSE_CLICKED) ;
        cont=0;
        camAtaque=false;
        terminada=false;
    }
    
    public void setStatus( BranchGroup bg) {
        //Realizamos la configuracion de nuestra pickcanvas
        pickCanvas = new PickCanvas(canvas, bg);
        //pickCanvas.setTolerance((float) 15.0f ) ;
        pickCanvas.setTolerance((float) 2.0f ) ;
        pickCanvas.setMode(PickInfo.PICK_GEOMETRY);
        pickCanvas.setFlags(PickInfo.NODE | PickInfo.CLOSEST_GEOM_INFO);
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
    @Override
    public void initialize() {
        //Activamos nuestro behavior
        setEnable(true);
        wakeupOn(condition);
    }

    @Override
    public void processStimulus(Enumeration cond) {
        WakeupOnAWTEvent c = (WakeupOnAWTEvent) cond.nextElement();
        AWTEvent[] e = c.getAWTEvent();
        MouseEvent mouse = (MouseEvent) e[0];
        pickCanvas.setShapeLocation(mouse);
        PickInfo pi=pickCanvas.pickClosest();
        System.out.println("Contador:"+cont);
        if(pi!=null){
            Node p=pi.getNode();
            Primitive padre = (Primitive) p.getParent();
            //System.out.println("Cont"+padre.getUserData().getClass());
            if(padre.getUserData().getClass().toString().contains("Bloque")){
                    Bloque objeto = (Bloque) padre.getUserData();
                    if(!objeto.getActivado() && cont==0 && camAtaque && !terminada){ 
                        //objeto.activarFallo();
                        int x=objeto.getX();
                        int y=objeto.getY();
                        boolean estado=partida.cambiarTurno(x,y);
                        if(estado){ 
                            objeto.activarAcierto();
                            if(!partida.getGanador().isEmpty()){
                                partida.nuevocontrol.setAreaMensajes("Ganador "+ partida.getGanador());
                                terminada=true;
                              }
                            cont=0;
                            partida.nuevocontrol.desactivarCambioTurno();
                        }else{ 
                            objeto.activarFallo();
                            cont++;
                            partida.nuevocontrol.activarCambioTurno();
                        }
                    }
            }
            //Partida.actualizarmapa(x,y);
        }
        
        wakeupOn(condition);
    }    

}
