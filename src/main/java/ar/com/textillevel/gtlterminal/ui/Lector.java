package ar.com.textillevel.gtlterminal.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.FocusTraversalPolicy;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
import ar.com.textillevel.gtlterminal.integration.TerminalServiceResponse;
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
        // setSize(new Dimension(450, 270));
        setExtendedState(MAXIMIZED_BOTH);
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

        final JPanel panelSur = new JPanel(new GridBagLayout());
        final ButtonGroup group = new ButtonGroup();
        final JToggleButton tgbSalida = new JToggleButton("SALIDA", true);
        final JToggleButton tgbReingreso = new JToggleButton("REINGRESO");
        tgbSalida.addActionListener(new ButtonListener(Modo.SALIDA));
        tgbSalida.setPreferredSize(new Dimension(170, 70));
        tgbReingreso.addActionListener(new ButtonListener(Modo.REINGRESO));
        tgbReingreso.setPreferredSize(new Dimension(170, 70));
        group.add(tgbSalida);
        panelSur.add(new JLabel("IP Server: " + System.getProperty("textillevel.server.ip")),
                        GenericUtils.createGridBagConstraints(0, 1, GridBagConstraints.WEST, GridBagConstraints.NONE,
                                        new Insets(5, 5, 5, 5), 1, 1, 0, 0));
        panelSur.add(tgbSalida, GenericUtils.createGridBagConstraints(0, 0, GridBagConstraints.CENTER,
                        GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 1, 1, 1, 1));
        group.add(tgbReingreso);
        panelSur.add(tgbReingreso, GenericUtils.createGridBagConstraints(1, 0, GridBagConstraints.CENTER,
                        GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 1, 1, 1, 1));

        final JPanel panelCentro = new JPanel(new GridBagLayout());
        txtIngreso.setFont(txtIngreso.getFont().deriveFont(Font.BOLD).deriveFont(45f));
        txtIngreso.setBorder(null);
        panelCentro.add(txtIngreso, GenericUtils.createGridBagConstraints(0, 0, GridBagConstraints.CENTER,
                        GridBagConstraints.BOTH, new Insets(5, 10, 5, 10), 1, 1, 1, 1));
        txtIngreso.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(final KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (txtIngreso.getText().trim().length() == 0) {
                        GenericUtils.showTemporaryDialog(3000, "Error",
                                        new JOptionPane("No se ha leido el codigo", JOptionPane.ERROR_MESSAGE));
                        return;
                    }
                    lblEstado.setText("FIN LECTURA");
                    finLectura();
                    return;
                }
                lblEstado.setText("LEYENDO......");
            }

            private void finLectura() {
                final String msg = "DANDO " + modo.toString().toUpperCase();
                GenericUtils.realizarOperacionConDialogoDeEspera(msg, () -> {
                    try {
                        TerminalServiceResponse resp;
                        if (modo == Modo.SALIDA) {
                            resp = TerminalServiceClient.marcarEntregado(txtIngreso.getText());
                        } else {
                            resp = TerminalServiceClient.reingresar(txtIngreso.getText());
                        }
                        if (resp.isError()) {
                            GenericUtils.showTemporaryDialog(3000, "Error",
                                            new JOptionPane(resp.getCodigoError() + " - " + resp.getMensajeError(),
                                                            JOptionPane.ERROR_MESSAGE));
                        } else {
                            GenericUtils.showTemporaryDialog(1000, "Error",
                                            new JOptionPane("Operacion exitosa", JOptionPane.INFORMATION_MESSAGE));
                        }
                    } catch (final RemoteException re) {
                        GenericUtils.showTemporaryDialog(3000, "Error",
                                        new JOptionPane("Se ha producido un error al comunicarse con el servidor"));
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
        add(panelCentro, BorderLayout.CENTER);
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
