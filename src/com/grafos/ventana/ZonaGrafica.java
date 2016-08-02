/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grafos.ventana;

import com.grafos.objetos.Arista;
import com.grafos.objetos.Coordenadas;
import com.grafos.objetos.Grafo;
import com.grafos.objetos.Nodo;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class ZonaGrafica  extends  JPanel  
{
    Grafo grafo = new Grafo();
    Nodo nodoInicio = null;
    Nodo nodoFin = null;
    public void dibujarGrafo() {
        this.paint(this.getGraphics());
        dibujarArista();
        dibujarNodos();
    }

    public void dibujarNodos() {
        java.util.ArrayList<Nodo> listaNodo = this.grafo.getListaNodos();
        for (Nodo nodo : listaNodo) {
            nodo.getCirculo().dibujarCirculo(this.getGraphics());
        }
    }

    public void dibujarArista() {
        java.util.ArrayList<Nodo> listaNodo = this.grafo.getListaNodos();
        for (Nodo nodo : listaNodo) {
            java.util.ArrayList<com.grafos.objetos.Enlace> listaEnlace = nodo.getListaNodoAdyacente();
            if (listaEnlace != null) {
                for (com.grafos.objetos.Enlace enlace : listaEnlace) {
                    enlace.getArista().getLineaQuebrada().dibujarLineaQuebrada(this.getGraphics());
                }
            }
        }
    }

    private int ingresarPeso() {
        String peso = javax.swing.JOptionPane.showInputDialog("Digite el peso.");
        int intPeso = 0;
        try {
            intPeso = Integer.parseInt(peso);
        } catch (Exception ex) {
            intPeso = ingresarPeso();
        }
        return intPeso;
    }

    private void eliminarNodo(int x, int y) {
        if (this.grafo.buscarNodo(x, y) != null) {
            Nodo temp = this.grafo.buscarNodo(x, y);
            this.grafo.eliminarAristasEntrante(temp);
            if (this.grafo.eliminarNodo(temp)) {
                javax.swing.JOptionPane.showMessageDialog(null, "Eliminado");
                dibujarGrafo();
            }
        }
    }

    private void jPanel1MouseClicked(MouseEvent evt) {
        int x = evt.getX();
        int y = evt.getY();
        if (evt.isMetaDown()) {

            if (this.grafo.buscarNodo(x, y) != null) {
                if (this.nodoInicio == null) {
                    this.grafo.reiniciarGrafoParaDisjktra();
                    this.grafo.reiniciarColores();
                    this.nodoInicio = this.grafo.buscarNodo(x, y);
                    this.nodoInicio.getCirculo().setColor(java.awt.Color.red);
                } else {
                    this.nodoFin = this.grafo.buscarNodo(x, y);
                    com.grafos.operaciones.Disjktra disjktra = new com.grafos.operaciones.Disjktra(this.grafo);
                    disjktra.ejecutar(this.nodoInicio);
                    disjktra.marcarRutaCorta(this.nodoFin, java.awt.Color.red);

                    this.nodoInicio = null;
                    this.nodoFin = null;
                }

            }

        } else if (this.grafo.buscarNodo(x, y) != null) {
            if (this.nodoInicio == null) {
                this.nodoInicio = this.grafo.buscarNodo(x, y);
                this.nodoInicio.getCirculo().setColor(java.awt.Color.red);
            } else {
                this.nodoFin = this.grafo.buscarNodo(x, y);
                crearArista();

                this.nodoInicio.getCirculo().setColor(java.awt.Color.yellow);

                this.nodoInicio = null;
                this.nodoFin = null;
            }
        } else {
            crearNodo(x, y);
        }

        dibujarGrafo();
    }

    private void crearArista() {
        int intPeso = ingresarPeso();
        Arista arista = new Arista();
        arista.setPeso(intPeso);
        Coordenadas c = new Coordenadas(100000, 100000);
        c.addCoordenada(this.nodoInicio.getCirculo().getX() + this.nodoInicio.getCirculo().getDiametro() / 2, this.nodoInicio.getCirculo().getY() + this.nodoInicio.getCirculo().getDiametro() / 2);
        c.addCoordenada(this.nodoFin.getCirculo().getX() + this.nodoInicio.getCirculo().getDiametro() / 2, this.nodoFin.getCirculo().getY() + this.nodoInicio.getCirculo().getDiametro() / 2);
        com.grafos.objetos.LineaQuebrada lq = new com.grafos.objetos.LineaQuebrada(c);
        arista.setLineaQuebrada(lq);
        this.grafo.crearEnlacesNoDirigido(this.nodoInicio, this.nodoFin, arista);
    }

    private void crearNodo(int x, int y) {
        Coordenadas c = new Coordenadas(100000, 100000, x, y);
        String dato = javax.swing.JOptionPane.showInputDialog("Digite un dato");
        if (dato != null) {
            dato = dato.toUpperCase();
            Nodo nodo = new Nodo(dato, c);
            nodo.getCirculo().setDiametro(12);
            nodo.getCirculo().setEtiqueta(nodo.getDato() + "");
            if (this.grafo.adjuntarNodo(nodo)) {
                nodo.getCirculo().dibujarCirculo(this.getGraphics());
            }

            this.nodoInicio = null;
            this.nodoFin = null;
        }
    }

 
}
