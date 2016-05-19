package Practica2;

import Model.Partida;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ANTONIO DAVID LÓPEZ MACHADO, JAVIER MARTINEZ MONTILLA, MANUEL ALBERTO LAFUENTE ARANDA
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //NUEVO
        try {
            // TODO code application logic here
            Partida partida= new Partida();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        //FIN NUEVO
    }
    
}
