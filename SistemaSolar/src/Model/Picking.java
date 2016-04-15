/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.media.j3d.SceneGraphPath;
import javax.media.j3d.Shape3D;
import javax.media.j3d.WakeupCondition;
import javax.media.j3d.WakeupOnAWTEvent;
import javax.media.j3d.WakeupOr;

/**
 *
 * @author LENOVO
 */
public class Picking extends Behavior{
    
    private WakeupOnAWTEvent condition;
    //private WakeupCondition condition ;
    private PickCanvas pickCanvas ;
    private Canvas3D canvas ;
    public Picking (Canvas3D aCanvas ) {
        canvas = aCanvas ;
        condition = new WakeupOnAWTEvent (MouseEvent.MOUSE_CLICKED) ;
    }
    public void setStatus( BranchGroup bg) {
        pickCanvas = new PickCanvas(canvas, bg);
        pickCanvas.setTolerance((float) 0.0f ) ;
        pickCanvas.setMode(PickInfo.PICK_GEOMETRY);
        pickCanvas.setFlags(PickInfo.NODE | PickInfo.CLOSEST_GEOM_INFO);
        setEnable(true);
}
    @Override
    public void initialize() {
        setEnable(false);
        wakeupOn(condition);
    }

    @Override
    public void processStimulus(Enumeration cond) {
        System.out.println("esta entrando2");
        WakeupOnAWTEvent c = (WakeupOnAWTEvent) cond.nextElement();
        AWTEvent[] e = c.getAWTEvent();
        MouseEvent mouse = (MouseEvent) e[0];
        pickCanvas.setShapeLocation(mouse);
        PickInfo pi=pickCanvas.pickClosest();
        if(pi!=null){
            
            Node p=pi.getNode();
            Primitive padre=(Primitive)p.getParent();
            Astro objeto= (Astro)padre.getUserData();
            
            //SceneGraphPath sgp= pi.getSceneGraphPath();
            //Astro a=(Astro) sgp.getNode(sgp.nodeCount());
            objeto.onoffMovimiento();
        }
        wakeupOn(condition);
    }    
}
