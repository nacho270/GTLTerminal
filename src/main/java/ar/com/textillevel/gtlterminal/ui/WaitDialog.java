package ar.com.textillevel.gtlterminal.ui;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;

import ar.com.textillevel.gtlterminal.util.GenericUtils;

public class WaitDialog extends JDialog {

  private static final long serialVersionUID = 7333760082366921670L;

  private static final String PATH = "ajax-loader.gif";
  private static final WaitDialog instance = new WaitDialog();

  private static JLabel lblTexto;
  private JLabel lblWaitingAnimation;

  private WaitDialog() {
    add(getLblTexto(), BorderLayout.NORTH);
    add(getLblWaitingAnimation(), BorderLayout.CENTER);
    pack();
    GenericUtils.centrar(this);
  }

  public static void startWait(String texto) {
    getLblTexto().setText(texto);
    instance.setVisible(true);
  }

  public static void stopWait() {
    instance.setVisible(false);
  }

  public static JLabel getLblTexto() {
    if (lblTexto == null) {
      lblTexto = new JLabel(" ", JLabel.CENTER);
    }
    return lblTexto;
  }

  public JLabel getLblWaitingAnimation() {
    if (lblWaitingAnimation == null) {
      lblWaitingAnimation = new JLabel(GenericUtils.loadIcon(PATH));
    }
    return lblWaitingAnimation;
  }
}
