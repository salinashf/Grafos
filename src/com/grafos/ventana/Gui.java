package com.grafos.ventana;

import java.awt.event.MouseEvent;
import javax.swing.GroupLayout;
import javax.swing.JPanel;
import com.grafos.objetos.Arista;
import com.grafos.objetos.Coordenadas;
import com.grafos.objetos.Grafo;
import com.grafos.objetos.Nodo;

public class Gui extends javax.swing.JFrame {

    Grafo grafo = new Grafo();
    Nodo nodoInicio = null;
    Nodo nodoFin = null;
    private JPanel jPanel1;
    private JPanel jPanel2;

    public Gui() {
        initComponents();
    }

    private void initComponents() {
        this.jPanel2 = new JPanel();
        this.jPanel1 = new JPanel();

        setDefaultCloseOperation(3);

        this.jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Grafo"));

        this.jPanel1.setBorder(null);
        this.jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                Gui.this.jPanel1MouseClicked(evt);
            }

        });
        GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
        this.jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 570, 32767));

        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 261, 32767));

        GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
        this.jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -1, -1, 32767).addContainerGap()));

        jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addComponent(this.jPanel1, -1, -1, 32767).addContainerGap()));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jPanel2, -1, -1, 32767));

        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jPanel2, -1, -1, 32767));

        pack();
    }

    public void dibujarGrafo() {
        this.jPanel1.paint(this.jPanel1.getGraphics());
        dibujarArista();
        dibujarNodos();
    }

    public void dibujarNodos() {
        java.util.ArrayList<Nodo> listaNodo = this.grafo.getListaNodos();
        for (Nodo nodo : listaNodo) {
            nodo.getCirculo().dibujarCirculo(this.jPanel1.getGraphics());
        }
    }

    public void dibujarArista() {
        java.util.ArrayList<Nodo> listaNodo = this.grafo.getListaNodos();
        for (Nodo nodo : listaNodo) {
            java.util.ArrayList<com.grafos.objetos.Enlace> listaEnlace = nodo.getListaNodoAdyacente();
            if (listaEnlace != null) {
                for (com.grafos.objetos.Enlace enlace : listaEnlace) {
                    enlace.getArista().getLineaQuebrada().dibujarLineaQuebrada(this.jPanel1.getGraphics());
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
                nodo.getCirculo().dibujarCirculo(this.jPanel1.getGraphics());
            }

            this.nodoInicio = null;
            this.nodoFin = null;
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Gui().setVisible(true);
            }
        });
    }
}
