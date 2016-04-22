/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import Model.Camara;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author fvelasco
 */
public class Control extends JFrame {
 
  /**
   * Creates new form Control
   */
    ArrayList<Camara> camaras;
  public Control(ArrayList<Camara> camara) {
    initComponents();
    // Atributos de referencia
    camaras=camara;
    // Atributos de la ventana
    setTitle ("Control Window");
    setLocation (820, 700);
    
    // Se construye y se abre la ventana de visualizacion
    
    // Cuando se cierra esta ventana se llama al método encargado del cierre
    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        exit (0);
      }
    });
    
    repaint();
  }

  /// El método encargado del cierre de la aplicación es único
  public void exit (int status) {
    System.exit (status);
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jb_exit = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        camper = new javax.swing.JButton();
        camluna = new javax.swing.JButton();
        camnave = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jb_exit.setMnemonic('X');
        jb_exit.setText("Exit");
        jb_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_exitActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Camaras"));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        camper.setText("Camara perspectiva");
        camper.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                camperActionPerformed(evt);
            }
        });
        jPanel2.add(camper, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 26, -1, -1));

        camluna.setText("Camara luna");
        camluna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                camlunaActionPerformed(evt);
            }
        });
        jPanel2.add(camluna, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 69, 147, -1));

        camnave.setText("Camara nave");
        camnave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                camnaveActionPerformed(evt);
            }
        });
        jPanel2.add(camnave, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 112, 147, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jb_exit)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jb_exit)
                .addContainerGap())
        );

        jPanel2.getAccessibleContext().setAccessibleName("Vistas");

        pack();
    }// </editor-fold>//GEN-END:initComponents

  private void jb_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_exitActionPerformed
    exit(0);
  }//GEN-LAST:event_jb_exitActionPerformed

    private void camperActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_camperActionPerformed
        // TODO add your handling code here:
        if(!camaras.get(0).isActive()){
            if(camaras.get(1).isActive()){
                camaras.get(1).removeCanvas();
            }
            if(camaras.get(2).isActive()){
                camaras.get(2).removeCanvas();
            }
            camaras.get(0).addCanvas();
        
        }
            
    }//GEN-LAST:event_camperActionPerformed

    private void camlunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_camlunaActionPerformed
        // TODO add your handling code here:
        if(!camaras.get(1).isActive()){
            if(camaras.get(0).isActive()){
                camaras.get(0).removeCanvas();
            }
            if(camaras.get(2).isActive()){
                camaras.get(2).removeCanvas();
            }
            camaras.get(1).addCanvas();
        
        }
    }//GEN-LAST:event_camlunaActionPerformed

    private void camnaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_camnaveActionPerformed
        // TODO add your handling code here:
         if(!camaras.get(2).isActive()){
            if(camaras.get(1).isActive()){
                camaras.get(1).removeCanvas();
            }
            if(camaras.get(0).isActive()){
                camaras.get(0).removeCanvas();
            }
            camaras.get(2).addCanvas();
        
        }
    }//GEN-LAST:event_camnaveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton camluna;
    private javax.swing.JButton camnave;
    private javax.swing.JButton camper;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton jb_exit;
    // End of variables declaration//GEN-END:variables
}
