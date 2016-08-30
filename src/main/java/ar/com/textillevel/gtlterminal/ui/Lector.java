package ar.com.textillevel.gtlterminal.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ar.com.textillevel.gtlterminal.util.GenericUtils;
import ar.com.textillevel.gtlterminal.util.GenericUtils.BackgroundTask;

public class Lector extends JFrame {

  private static final long serialVersionUID = 6430878999956547684L;

  public Lector() {
    setupScreen();
    setupComponentes();
  }

  private void setupComponentes() {
    setTitle("LECTOR");
    setSize(new Dimension(400, 200));
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    GenericUtils.centrar(this);
    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
  }

  private void setupScreen() {
    JPanel panelNorte = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
    final JLabel lblEstado = new JLabel("ESPERANDO......");
    lblEstado.setFont(lblEstado.getFont().deriveFont(Font.BOLD).deriveFont(20f));
    panelNorte.add(lblEstado);

    final JLabel lblLectura = new JLabel();
    lblLectura.setFont(lblLectura.getFont().deriveFont(Font.BOLD).deriveFont(30f));

    this.addKeyListener(new KeyAdapter() {
      @Override
      public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          finLectura();
          return;
        }
        lblEstado.setText("LEYENDO......");
        String txtLabel = lblLectura.getText();
        String textoLeido = String.valueOf(e.getKeyChar());
        lblLectura.setText(txtLabel + textoLeido);
      }

      private void finLectura() {
        GenericUtils.realizarOperacionConDialogoDeEspera("Enviando", new BackgroundTask() {
          @Override
          public void perform() {
            lblEstado.setText("FIN LECTURA");
            try {
              Thread.sleep(3000);
            } catch (InterruptedException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            }
            lblEstado.setText("ESPERANDO......");
            lblLectura.setText("");
          }
        });
      }
    });
    add(panelNorte, BorderLayout.NORTH);
    add(lblLectura, BorderLayout.CENTER);
  }
}
