package com.grafos.objetos;

public class Arista {

    private int idArista;

    private String nombreArista;

    private int peso;

    private LineaQuebrada lineaQuebrada;

    private boolean habilitado;

    public Arista() {
        this(-1, "");
    }

    public Arista(int idArista) {
        this(-1, "");
    }

    public Arista(int idArista, String nombreArista) {
        this(-1, "", 1);
    }

    public Arista(int idArista, String nombreArista, int peso) {
        this.idArista = idArista;
        this.nombreArista = nombreArista;
        this.peso = peso;
        this.lineaQuebrada = null;
        this.habilitado = true;
    }

    public void setIdArista(int idArista) {
        this.idArista = idArista;
    }

    public int getIdArista() {
        return this.idArista;
    }

    public void setNombreArista(String nombreVia) {
        this.nombreArista = nombreVia;
    }

    public String getNombreArista() {
        return this.nombreArista;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getPeso() {
        return this.peso;
    }

    public void setLineaQuebrada(LineaQuebrada lineaQuebrada) {
        this.lineaQuebrada = lineaQuebrada;
        if (lineaQuebrada != null) {
            this.lineaQuebrada.setLongitud(this.peso);
        }
    }

    public LineaQuebrada getLineaQuebrada() {
        return this.lineaQuebrada;
    }

    public boolean isHabilitado() {
        return this.habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }
}
