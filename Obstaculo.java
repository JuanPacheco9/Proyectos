package juego2;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Area;
import javax.swing.ImageIcon;

public class Obstaculo {

    //Variables que delimitan el area del objeto
    Area cabeza, cuerpo, vaca;
    //Variables de tamaño de personaje
    int ancho_obstaculo = 70;
    int alto_obstaculo = 70;
    //Coordenadas iniciales donde se ubica el objeto
    int x_inicial = 1300;
    int y_inicial = 270;
    //Coordenadas para manipular al objeto
    int x_auxiliar = -7;
    //objeto de la clase juego
    private Juego jueguito;

    public Obstaculo(Juego jueguito) {
        this.jueguito = jueguito;
    }

    public void mover() {

        if (x_inicial <= -100) {
            jueguito.puntos++;
            x_inicial = 1300;
        } else {

            if (colision()) {
                jueguito.finJuego();
            } else {
                x_inicial = x_inicial + x_auxiliar;
            }
        }
    }

    public void paint(Graphics2D g) {

        ImageIcon animal = new ImageIcon(getClass().getResource("/multimedia/vaquita.png"));
        g.drawImage(animal.getImage(), x_inicial, y_inicial, ancho_obstaculo, alto_obstaculo, null);

    }

    public boolean colision() {
        Area areaA = new Area(jueguito.auto.getBounds());
        areaA.intersect(getBounds());
        return !areaA.isEmpty();
    }

    public Area getBounds() {

        Rectangle forma1 = new Rectangle(x_inicial, y_inicial, 38, 40);
        Rectangle forma2 = new Rectangle(x_inicial + 19, y_inicial + 16, 50, 53);
        cabeza = new Area(forma1);
        cuerpo = new Area(forma2);

        vaca = cabeza;
        vaca.add(cabeza);
        vaca.add(cuerpo);

        return vaca;

    }
}
