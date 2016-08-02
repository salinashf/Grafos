package com.grafos.objetos;

import java.util.ArrayList;

public class Nodo {

    private Object dato;
    private ArrayList<Enlace> listaNodoAdyacente;
    private boolean visitado = false;
    private Circulo circulo;
    private int izquierda;
    private int longitudCamino;
    private Nodo nodoAntecesorDisjktra;
    private boolean marca;

    public Nodo() {
        this.dato = new Object();
        this.circulo = null;
        this.izquierda = 0;
        inicializarParaDisjktra();
    }

    private void inicializarParaDisjktra() {
        this.longitudCamino = -1;
        this.nodoAntecesorDisjktra = null;
        this.marca = false;
    }

    public Nodo(Object dato, Coordenadas coordenada) {
        this.dato = dato;
        this.listaNodoAdyacente = new ArrayList();
        this.circulo = new Circulo(coordenada);
        this.circulo.setIzquierda(this.izquierda);
        inicializarParaDisjktra();
    }

    public void setDato(Object dato) {
        this.dato = dato;
    }

    public Object getDato() {
        return this.dato;
    }

    public ArrayList<Enlace> getListaNodoAdyacente() {
        ArrayList<Enlace> listAristaAux = null;
        if (!this.listaNodoAdyacente.isEmpty()) {
            listAristaAux = new ArrayList();
            for (Enlace enlace : this.listaNodoAdyacente) {
                if (enlace.getArista().isHabilitado()) {
                    listAristaAux.add(enlace);
                }
            }
        }
        return listAristaAux;
    }

    public void addNodoAdyacente(Enlace arista) {
        this.listaNodoAdyacente.add(arista);
    }

    public void addNodoAdyacente(Arista via, Nodo nodo) {
        addNodoAdyacente(new Enlace(via, nodo));
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }

    public boolean isVisitado() {
        return this.visitado;
    }

    public Circulo getCirculo() {
        return this.circulo;
    }

    public void setCirculo(Circulo circulo) {
        this.circulo = circulo;
    }

    public int getLongitudCamino() {
        return this.longitudCamino;
    }

    public void setLongitudCamino(int longitudCamino) {
        this.longitudCamino = longitudCamino;
    }

    public boolean isMarca() {
        return this.marca;
    }

    public void setMarca(boolean marca) {
        this.marca = marca;
    }

    public Nodo getNodoAntecesorDisjktra() {
        return this.nodoAntecesorDisjktra;
    }

    public void setNodoAntecesorDisjktra(Nodo nodoAntecesorDisjktra) {
        this.nodoAntecesorDisjktra = nodoAntecesorDisjktra;
    }
}
