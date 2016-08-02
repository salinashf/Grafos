package com.grafos.objetos;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class LineaQuebrada {

    private Coordenadas coordenadas;
    private Color color;
    private float grosorLinea;
    private String etiqueta;
    private int longitud;
    private boolean mostrarEtiqueta;

    public LineaQuebrada(Coordenadas coordenadas) {
        this.coordenadas = coordenadas;
        this.grosorLinea = 1.0F;
        this.color = Color.black;
        this.mostrarEtiqueta = true;
    }

    public void dibujarLineaQuebrada(Graphics g) {
        ((Graphics2D) g).setColor(getColor());
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        BasicStroke stroke = new BasicStroke(this.grosorLinea);
        ((Graphics2D) g).setStroke(stroke);
        if (this.coordenadas != null) {
            int[] aux = new int[0];
            for (int i = 0; i < this.coordenadas.size(); i++) {
                aux = (int[]) this.coordenadas.get(i);
                if (i == 0) {
                    ((Graphics2D) g).drawLine(aux[0], aux[1], aux[0], aux[1]);
                } else {
                    ((Graphics2D) g).drawLine(((int[]) this.coordenadas.get(i - 1))[0], ((int[]) this.coordenadas.get(i - 1))[1], aux[0], aux[1]);
                }
            }

            if (this.mostrarEtiqueta) {
                ((Graphics2D) g).setColor(Color.blue);
                Font fuente = new Font("Monospaced", 1, 12);
                g.setFont(fuente);

                if (this.coordenadas.size() == 2) {
                    int xMenor = menor(((int[]) this.coordenadas.get(0))[0], ((int[]) this.coordenadas.get(1))[0]);
                    int yMenor = menor(((int[]) this.coordenadas.get(0))[1], ((int[]) this.coordenadas.get(1))[1]);

                    int xMayor = mayor(((int[]) this.coordenadas.get(0))[0], ((int[]) this.coordenadas.get(1))[0]);
                    int yMayor = mayor(((int[]) this.coordenadas.get(0))[1], ((int[]) this.coordenadas.get(1))[1]);

                    int distanciaVertical = yMayor - yMenor;
                    int distanciaHorizontal = xMayor - xMenor;
                    ((Graphics2D) g).drawString(this.longitud + "", distanciaHorizontal / 2 + xMenor, distanciaVertical / 2 + yMenor);
                } else {
                    int pos = this.coordenadas.size() / 2;

                    ((Graphics2D) g).drawString(this.longitud + "", ((int[]) this.coordenadas.get(pos))[0] + 3, ((int[]) this.coordenadas.get(pos))[1] - 8);
                }
            }
        }

        stroke = new BasicStroke(1.0F);
        ((Graphics2D) g).setStroke(stroke);
    }

    private int mayor(int n1, int n2) {
        return n1 > n2 ? n1 : n2;
    }

    private int menor(int n1, int n2) {
        return n1 < n2 ? n1 : n2;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    public void setGrosorLinea(float grosorLinea) {
        this.grosorLinea = grosorLinea;
    }

    public float getGrosorLinea() {
        return this.grosorLinea;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getEtiqueta() {
        return this.etiqueta;
    }

    public int getLongitud() {
        return this.longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    public Coordenadas getCoordenadas() {
        return this.coordenadas;
    }

    public void setCoordenadas(Coordenadas coordenadas) {
        this.coordenadas = coordenadas;
    }

    public boolean isMostrarEtiqueta() {
        return this.mostrarEtiqueta;
    }

    public void setMostrarEtiqueta(boolean mostrarEtiqueta) {
        this.mostrarEtiqueta = mostrarEtiqueta;
    }
}
