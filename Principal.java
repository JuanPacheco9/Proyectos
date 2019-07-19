package juego2;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class Principal {
       
    public static void main(String[] args) {
        m1();
    }
  public static void m1(){
            
        JFrame ventana = new JFrame("Jueguito");
        Juego jueguito = new Juego();
        ventana.add(jueguito);
	ventana.setSize(1300,400);
	ventana.setLocation(70, 200);
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
        while (!jueguito.juegoFinalizado) {
            jueguito.repaint();
            try {
                Thread.sleep(5);
            } catch (InterruptedException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        jueguito.repaint();
    
  }
}
