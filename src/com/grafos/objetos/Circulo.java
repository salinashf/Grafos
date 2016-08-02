package com.grafos.objetos;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;


public class Circulo {

    private Coordenadas coordenadas;
    private Color color;
    private int diametro;
    private String etiqueta;
    private Font fuente;
    private int izquierda;
    private int grosorBorde;

    public Circulo(Coordenadas coordenadas) {
        this.coordenadas = coordenadas;
        this.color = Color.yellow;
        this.diametro = 20;
        this.etiqueta = "";
        this.fuente = null;
        this.izquierda = 0;
        this.grosorBorde = 2;
    }

    public void dibujarCirculo(Graphics g) {
        if (this.coordenadas.size() > 0) {
            ((Graphics2D) g).setColor(this.color);

            ((Graphics2D) g).setStroke(new BasicStroke(getGrosorBorde()));

            ((Graphics2D) g).fillOval(((int[]) this.coordenadas.get(0))[0], ((int[]) this.coordenadas.get(0))[1], this.diametro, this.diametro);
            ((Graphics2D) g).setColor(Color.black);
            ((Graphics2D) g).drawOval(((int[]) this.coordenadas.get(0))[0], ((int[]) this.coordenadas.get(0))[1], this.diametro, this.diametro);
            if (!"".equals(this.etiqueta)) {
                if (this.fuente != null) {
                    g.setFont(this.fuente);
                }

                ((Graphics2D) g).drawString(this.etiqueta, ((int[]) this.coordenadas.get(0))[0] - this.izquierda, ((int[]) this.coordenadas.get(0))[1]);

                ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            }
        } else {
            System.out.println("No hay coordenadas para el circulo");
        }
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    public void setDiametro(int diametro) {
        this.diametro = diametro;
    }

    public int getDiametro() {
        return this.diametro;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public void setEtiqueta(String etiqueta, boolean izquierda) {
        this.etiqueta = etiqueta;
    }

    public String getEtiqueta() {
        return this.etiqueta;
    }

    public int getX() {
        if (this.coordenadas.size() > 0) {
            return ((int[]) this.coordenadas.get(0))[0];
        }
        return -1;
    }

    public int getY() {
        if (this.coordenadas.size() > 0) {
            return ((int[]) this.coordenadas.get(0))[1];
        }
        return -1;
    }

    public Font getFuente() {
        return this.fuente;
    }

    public void setFuente(Font fuente) {
        this.fuente = fuente;
    }

    public int isIzquierda() {
        return this.izquierda;
    }

    public void setIzquierda(int izquierda) {
        this.izquierda = izquierda;
    }

    public int getGrosorBorde() {
        return this.grosorBorde;
    }

    public void setGrosorBorde(int grosorBorde) {
        this.grosorBorde = grosorBorde;
    }
}
