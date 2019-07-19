package juego2;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import javax.swing.ImageIcon;

public class Personaje {

    //Variables que delimitan el area del objeto
    Area llantaDelantera, llantaTrasera, carroceria, tractor;
    //Variables de tamaño de personaje
    int ancho_personaje = 112;
    int alto_personaje = 78;
    //Coordenadas iniciales donde se ubica el personaje
    int x_inicial = 50;
    int y_inicial = 270;
    //Coordenadas para manipular al personaje
    int x_auxiliar = 0;
    int y_auxiliar = 0;
    //objeto de la clase juego
    private Juego jueguito;
    //variables que nos ayudan a saber si el personaje esta brincando o no
    boolean saltando = false;
    boolean sube = false;
    boolean baja = false;

    public Personaje(Juego jueguito) {
        this.jueguito = jueguito;
    }

    public void mover() {
        if (x_inicial + x_auxiliar > 0 && x_inicial + x_auxiliar < jueguito.getWidth() - ancho_personaje) {
            x_inicial = x_inicial + x_auxiliar;
        }

        if (saltando) {
            if (y_inicial == 270)//si el personaje esta en el suelo
            {
                sube = true;
                y_auxiliar = -2;
                baja = false;
            }
            if (y_inicial == 100)//si el personale llego al limite del salto
            {
                baja = true;
                y_auxiliar = 2;
                sube = false;
            }

            if (sube) {
                y_inicial = y_inicial + y_auxiliar;
            }
            if (baja) {

                y_inicial = y_inicial + y_auxiliar;
                if (y_inicial == 270)//si el personaje esta en el suelo
                {
                    saltando = false;
                }
            }
            x_inicial += x_auxiliar;
        }
    }

    public void paint(Graphics2D g) {

        ImageIcon auto = new ImageIcon(getClass().getResource("/multimedia/tractor.png"));
        g.drawImage(auto.getImage(), x_inicial, y_inicial, ancho_personaje, alto_personaje, null);
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_SPACE) {
            saltando = true;
        }
        if (key == KeyEvent.VK_LEFT) {
            x_auxiliar = -1;
        }
        if (key == KeyEvent.VK_RIGHT) {

            x_auxiliar = +1;

        }

    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            x_auxiliar = 0;
        }
        if (key == KeyEvent.VK_RIGHT) {

            x_auxiliar = 0;

        }

    }

    public Area getBounds() {

        Rectangle forma1 = new Rectangle(x_inicial, y_inicial, 95, 62);
        carroceria = new Area(forma1);
        Ellipse2D forma2 = new Ellipse2D.Double(x_inicial, y_inicial + 28, 48, 48);
        llantaTrasera = new Area(forma2);
        Ellipse2D forma3 = new Ellipse2D.Double(x_inicial + 73, y_inicial + 39, 38, 38);
        llantaDelantera = new Area(forma3);

        tractor = carroceria;
        tractor.add(carroceria);
        tractor.add(llantaTrasera);
        tractor.add(llantaDelantera);

        return tractor;
    }

}
