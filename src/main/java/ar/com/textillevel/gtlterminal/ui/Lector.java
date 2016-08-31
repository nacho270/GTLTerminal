package ar.com.textillevel.gtlterminal.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.FocusTraversalPolicy;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import ar.com.textillevel.gtlterminal.integration.TerminalServiceClient;
import ar.com.textillevel.gtlterminal.util.GenericUtils;

public class Lector extends JFrame {

    private static final long serialVersionUID = 6430878999956547684L;

    private final JTextField txtIngreso = new JTextField();
    private Modo modo = Modo.SALIDA;

    public Lector() {
        setupScreen();
        setupComponentes();
    }

    private void setupScreen() {
        setTitle("GTL - TERMINAL");
        setSize(new Dimension(450, 250));
        // setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        GenericUtils.centrar(this);
        setIconImage(GenericUtils.iconToImage(GenericUtils.loadIcon("logo.jpg")));
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent e) {
                System.exit(0);
            }
        });
    }

    private void setupComponentes() {
        final JPanel panelNorte = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        final JLabel lblEstado = new JLabel("ESPERANDO......");
        lblEstado.setFont(lblEstado.getFont().deriveFont(Font.BOLD).deriveFont(20f));
        panelNorte.add(lblEstado);

        final JPanel panelSur = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        final ButtonGroup group = new ButtonGroup();
        final JToggleButton tgbSalida = new JToggleButton("SALIDA", true);
        final JToggleButton tgbReingreso = new JToggleButton("REINGRESO");
        tgbSalida.addActionListener(new ButtonListener(Modo.SALIDA));
        tgbSalida.setPreferredSize(new Dimension(170, 70));
        tgbReingreso.addActionListener(new ButtonListener(Modo.REINGRESO));
        tgbReingreso.setPreferredSize(new Dimension(170, 70));
        group.add(tgbSalida);
        panelSur.add(tgbSalida);
        group.add(tgbReingreso);
        panelSur.add(tgbReingreso);

        txtIngreso.setFont(txtIngreso.getFont().deriveFont(Font.BOLD).deriveFont(50f));
        txtIngreso.setBorder(null);
        txtIngreso.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(final KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    lblEstado.setText("FIN LECTURA");
                    finLectura();
                    return;
                }
                lblEstado.setText("LEYENDO......");
            }

            private void finLectura() {
                final String msg = "ENVIANDO " + modo.toString().toUpperCase();
                GenericUtils.realizarOperacionConDialogoDeEspera(msg, () -> {
                    try {
                        if (modo == Modo.SALIDA) {
                            TerminalServiceClient.marcarEntregado(txtIngreso.getText());
                        } else {
                            TerminalServiceClient.reingresar(txtIngreso.getText());
                        }
                    } catch (final RemoteException re) {
                        JOptionPane.showMessageDialog(Lector.this,
                                        "Se ha producido un error al comunicarse con el servidor", "Error",
                                        JOptionPane.ERROR_MESSAGE);
                        re.printStackTrace();
                    }
                    reset();
                });
            }

            private void reset() {
                lblEstado.setText("ESPERANDO......");
                txtIngreso.setText("");
                txtIngreso.requestFocus();
                txtIngreso.requestFocusInWindow();
            }
        });
        add(panelNorte, BorderLayout.NORTH);
        add(txtIngreso, BorderLayout.CENTER);
        add(panelSur, BorderLayout.SOUTH);
        setFocusTraversalPolicy(crearPoliticaFocus());
    }

    private FocusTraversalPolicy crearPoliticaFocus() {
        return new FocusTraversalPolicy() {

            @Override
            public Component getLastComponent(final Container aContainer) {
                return txtIngreso;
            }

            @Override
            public Component getFirstComponent(final Container aContainer) {
                return txtIngreso;
            }

            @Override
            public Component getDefaultComponent(final Container aContainer) {
                return txtIngreso;
            }

            @Override
            public Component getComponentBefore(final Container aContainer, final Component aComponent) {
                return txtIngreso;
            }

            @Override
            public Component getComponentAfter(final Container aContainer, final Component aComponent) {
                return txtIngreso;
            }
        };
    }

    private enum Modo {
        SALIDA, REINGRESO;
    }

    private class ButtonListener implements ActionListener {

        private final Modo modo;

        public ButtonListener(final Modo modo) {
            this.modo = modo;
        }

        @Override
        public void actionPerformed(final ActionEvent e) {
            Lector.this.modo = modo;
            txtIngreso.requestFocus();
            txtIngreso.requestFocusInWindow();
        }
    }
}
