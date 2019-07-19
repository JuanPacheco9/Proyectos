package juego2;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

import javax.swing.JPanel;

public class Juego extends JPanel {

    //Sonido del Juego
    URL direccionSonidoSalto, direccionSonidoChoque;
    AudioClip sonidoChoque, sonidoSalto;
    //Creacion de objetos de las clases
    Fondo fondo = new Fondo(this);
    Personaje auto = new Personaje(this);
    Obstaculo obstaculo = new Obstaculo(this);
    //Variables para puntaje
    public boolean juegoFinalizado = false;
    public int puntos = 0;

    public Juego() {

        direccionSonidoChoque = getClass().getResource("/multimedia/choque.wav");
        sonidoChoque = Applet.newAudioClip(direccionSonidoChoque);

        direccionSonidoSalto = getClass().getResource("/multimedia/salto.wav");
        sonidoSalto = Applet.newAudioClip(direccionSonidoSalto);

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                sonidoSalto.play();
                auto.keyPressed(e);

            }
        });
        setFocusable(true);
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        dibujar(g2);
        dibujarPuntaje(g2);
    }

    public void dibujar(Graphics2D g) {

        fondo.paint(g);
        auto.paint(g);
        obstaculo.paint(g);
        mover();
    }

    public void dibujarPuntaje(Graphics2D g) {

        Graphics2D g1 = g, g2 = g;
        Font score = new Font("Arial", Font.BOLD, 30);
        g.setFont(score);
        g.setColor(Color.blue);
        g1.drawString("Puntaje:  " + puntos, 1000, 50);

        if (juegoFinalizado) {
            g2.setColor(Color.red);
            g2.drawString("¡¡¡ Haz Perdido !!!", 530, 120);
        }
    }

    public void mover() {
        //fondo.mover();
        obstaculo.mover();
        auto.mover();
    }

    public void finJuego() {
        sonidoChoque.play();
        juegoFinalizado = true;
    }
}
