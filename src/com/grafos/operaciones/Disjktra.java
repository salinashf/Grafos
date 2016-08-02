 package com.grafos.operaciones;
 
 import java.awt.Color;
 import java.util.ArrayList;
 import com.grafos.objetos.Arista;
 import com.grafos.objetos.Enlace;
 import com.grafos.objetos.Grafo;
 import com.grafos.objetos.Nodo;
 
 
 
 
 
 
 
 public class Disjktra
 {
   Grafo grafo;
   ListaNodo listaNodosAdyacentes;
   ArrayList<Arista> aux = new ArrayList();
   
   public Disjktra(Grafo grafo) { this.grafo = grafo;
     this.listaNodosAdyacentes = new ListaNodo();
   }
   
   private void llenarConAdyacentes(Nodo nodo) { if (nodo != null) {
       ArrayList<Enlace> listaAux = nodo.getListaNodoAdyacente();
       if (listaAux != null) {
         for (Enlace enlace : listaAux) {
           Nodo aux2 = enlace.getNodo();
           if (!aux2.isMarca())
           {
             if (this.listaNodosAdyacentes.isContenido(aux2)) {
               int longitud = nodo.getLongitudCamino() + enlace.getArista().getPeso();
               if (aux2.getLongitudCamino() > longitud) {
                 aux2.setLongitudCamino(longitud);
                 aux2.setNodoAntecesorDisjktra(nodo);
               }
             } else {
               aux2.setLongitudCamino(nodo.getLongitudCamino() + enlace.getArista().getPeso());
               aux2.setNodoAntecesorDisjktra(nodo);
               this.listaNodosAdyacentes.add(aux2);
             }
           }
         }
       }
     }
   }
   
   public void ejecutar(Nodo nodoInicio) {
     nodoInicio.setLongitudCamino(0);
     if (nodoInicio != null) {
       this.listaNodosAdyacentes = new ListaNodo();
       this.listaNodosAdyacentes.add(nodoInicio);
       while (!this.listaNodosAdyacentes.isEmpty()) {
         Nodo menor = this.listaNodosAdyacentes.buscarMenor();
         menor.setMarca(true);
         this.listaNodosAdyacentes.remove(menor);
         llenarConAdyacentes(menor);
       }
     }
   }
   
   private void rutaCorta(Nodo nodoFinal) {
     this.aux.clear();
     Nodo nAux = nodoFinal;
     while (nAux.getNodoAntecesorDisjktra() != null)
     {
 
       this.aux.add(this.grafo.getArista(nAux, nAux.getNodoAntecesorDisjktra()));
       
       nAux = nAux.getNodoAntecesorDisjktra();
     }
   }
   
   public void marcarRutaCorta(Nodo nodoFinal, Color color) {
     if (nodoFinal != null) {
       rutaCorta(nodoFinal);
       for (int i = 0; i < this.aux.size(); i++) {
         if (!this.aux.isEmpty()) {
           ((Arista)this.aux.get(i)).getLineaQuebrada().setColor(color);
           ((Arista)this.aux.get(i)).getLineaQuebrada().setGrosorLinea(4.0F);
         }
       }
     }
   }
 }


