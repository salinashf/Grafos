package com.grafos.objetos;

import java.awt.Color;
import java.util.ArrayList;

public class Grafo {

    private ArrayList<Nodo> listaNodo;

    public Grafo() {
        this.listaNodo = new ArrayList();
    }

    public boolean adjuntarNodo(Nodo nodo) {
        Nodo nodoTemp = buscarNodo(nodo.getDato());
        if (nodoTemp == null) {
            this.listaNodo.add(nodo);
            return true;
        }
        return false;
    }

    public void crearEnlacesDirigido(Nodo padre, Nodo adyacente, Arista arista) {
        if ((padre != null) && (adyacente != null)) {
            padre.addNodoAdyacente(arista, adyacente);
        }
    }

    public void crearEnlacesNoDirigido(Nodo padre, Nodo adyacente, Arista arista) {
        crearEnlacesDirigido(padre, adyacente, arista);
        crearEnlacesDirigido(adyacente, padre, arista);
    }

    public Nodo buscarNodo(Object dato) {
        Nodo temp = null;
        if (dato != null) {
            for (Nodo nodo : this.listaNodo) {
                if (dato.equals(nodo.getDato())) {
                    temp = nodo;
                }
            }
        }
        return temp;
    }

    public Nodo buscarNodo(int x, int y) {
        Nodo nodoAuxiliar = null;
        for (int i = 0; i < this.listaNodo.size(); i++) {
            int xNodo = ((Nodo) this.listaNodo.get(i)).getCirculo().getX();
            int yNodo = ((Nodo) this.listaNodo.get(i)).getCirculo().getY();
            if ((x > xNodo) && (x < xNodo + ((Nodo) this.listaNodo.get(i)).getCirculo().getDiametro())
                    && (y > yNodo) && (y < yNodo + ((Nodo) this.listaNodo.get(i)).getCirculo().getDiametro())) {
                nodoAuxiliar = (Nodo) this.listaNodo.get(i);
                break;
            }
        }

        return nodoAuxiliar;
    }

    public ArrayList<Nodo> getAdyacentes(Object dato) {
        ArrayList<Nodo> lista = null;
        Nodo principal = buscarNodo(dato);
        ArrayList<Enlace> aristas = principal.getListaNodoAdyacente();
        if (aristas != null) {
            for (int i = 0; i < aristas.size(); i++) {
                lista.add(((Enlace) aristas.get(i)).getNodo());
            }
        }
        return lista;
    }

    public ArrayList<Nodo> getListaNodos() {
        return this.listaNodo;
    }

    public boolean isAdyacente(Nodo n1, Nodo n2) {
        boolean aux = false;
        ArrayList<Enlace> listaAristas = n1.getListaNodoAdyacente();
        if (listaAristas != null) {
            for (int i = 0; i < listaAristas.size(); i++) {
                if (((Enlace) listaAristas.get(i)).getNodo() == n2) {
                    aux = true;
                }
            }
        }
        return aux;
    }

    public boolean isAdyacente(Object datoNodoInicio, Object datoNodoDestino) {
        boolean aux = false;
        Nodo n1 = buscarNodo(datoNodoInicio);
        Nodo n2 = buscarNodo(datoNodoDestino);
        ArrayList<Enlace> listaAristas = n1.getListaNodoAdyacente();
        if (listaAristas != null) {
            for (int i = 0; i < listaAristas.size(); i++) {
                if (((Enlace) listaAristas.get(i)).getNodo() == n2) {
                    aux = true;
                }
            }
        }
        return aux;
    }

    public Arista getArista(Object datoNodo1, Object datoNodo2) {
        return getArista(buscarNodo(datoNodo1), buscarNodo(datoNodo2));
    }

    public Arista getArista(String nombreVia) {
        Arista aux = null;
        if (nombreVia != null) {
            ArrayList<Nodo> listaN = this.listaNodo;
            for (Nodo nd : listaN) {
                ArrayList<Enlace> lA = nd.getListaNodoAdyacente();
                for (Enlace enlace : lA) {
                    if (enlace.getArista().getNombreArista().equals(nombreVia)) {
                        aux = enlace.getArista();
                    }
                }
            }
        }
        return aux;
    }

    public Arista getArista(Nodo n1, Nodo n2) {
        Arista aux = null;
        if (isAdyacente(n1, n2)) {
            ArrayList<Enlace> listaAristas = n1.getListaNodoAdyacente();
            for (int i = 0; i < listaAristas.size(); i++) {
                if (((Enlace) listaAristas.get(i)).getNodo() == n2) {
                    aux = ((Enlace) listaAristas.get(i)).getArista();
                }
            }
        } else if (isAdyacente(n2, n1)) {
            aux = getArista(n2, n1);
        }
        return aux;
    }

    public Enlace getEnlace(Object datoNodo1, Object datoNodo2) {
        Enlace aux = null;
        if (isAdyacente(datoNodo1, datoNodo2)) {
            Nodo n1 = buscarNodo(datoNodo1);
            Nodo n2 = buscarNodo(datoNodo2);
            ArrayList<Enlace> listaAristas = n1.getListaNodoAdyacente();
            for (int i = 0; i < listaAristas.size(); i++) {
                if (((Enlace) listaAristas.get(i)).getNodo() == n2) {
                    aux = (Enlace) listaAristas.get(i);
                }
            }
        } else if (isAdyacente(datoNodo2, datoNodo1)) {
            aux = getEnlace(datoNodo2, datoNodo1);
        }
        return aux;
    }

    public void reiniciarGrafoParaDisjktra() {
        for (Nodo n : this.listaNodo) {
            n.setMarca(false);
            n.setNodoAntecesorDisjktra(null);
            n.setLongitudCamino(-1);
        }
    }

    public boolean eliminarNodo(Nodo nodo) {
        boolean retornado = false;
        if (nodo != null) {
            retornado = this.listaNodo.remove(nodo);
        }
        return retornado;
    }

    public void reiniciarColores() {
        if (this.listaNodo != null) {
            for (Nodo nodo : this.listaNodo) {
                nodo.getCirculo().setColor(Color.yellow);
                ArrayList<Enlace> la = nodo.getListaNodoAdyacente();
                if (la != null) {
                    for (Enlace enlace : la) {
                        if (enlace.getArista().isHabilitado()) {
                            enlace.getArista().getLineaQuebrada().setColor(Color.black);
                            enlace.getArista().getLineaQuebrada().setGrosorLinea(1.0F);
                        }
                    }
                }
            }
        }
    }

    public ArrayList<Arista> aristasEntrante(Nodo nodo) {
        ArrayList<Arista> listaArista = null;
        for (Nodo n : this.listaNodo) {
            ArrayList<Enlace> enlaces = n.getListaNodoAdyacente();
            if (enlaces != null) {
                listaArista = new ArrayList();
                for (Enlace e : enlaces) {
                    if (e.getNodo() == nodo) {
                        listaArista.add(e.getArista());
                    }
                }
            }
        }
        return listaArista;
    }

    public ArrayList<Arista> aristasSaliente(Nodo nodo) {
        ArrayList<Arista> listaArista = null;
        if ((nodo != null)
                && (this.listaNodo.contains(nodo))) {
            ArrayList<Enlace> listaEnlace = nodo.getListaNodoAdyacente();
            if (listaArista != null) {
                listaArista = new ArrayList();
                for (Enlace e : listaEnlace) {
                    listaArista.add(e.getArista());
                }
            }
        }

        return listaArista;
    }

    private void eliminarAristas(Nodo nodo) {
        ArrayList<Arista> aristas = aristasSaliente(nodo);
        for (Arista a : aristas) {
            a = null;
        }
    }

    public void eliminarAristasSalientes(Nodo nodo) {
        ArrayList<Arista> aristas = aristasSaliente(nodo);
        eliminarAristas(nodo);
    }

    public void eliminarAristasEntrante(Nodo nodo) {
        ArrayList<Arista> aristas = aristasEntrante(nodo);
        eliminarAristas(nodo);
    }
}
