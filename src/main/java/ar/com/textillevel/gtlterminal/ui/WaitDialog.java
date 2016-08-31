package ar.com.textillevel.gtlterminal.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.Icon;
import javax.swing.ImageIcon;
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
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        add(getLblTexto(), BorderLayout.NORTH);
        add(getLblWaitingAnimation(), BorderLayout.CENTER);
        setSize(new Dimension(250, 120));
        GenericUtils.centrar(this);
        setUndecorated(true);
    }

    public static void startWait(String texto) {
        getLblTexto().setText("\n" + texto);
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
            final Icon icon = GenericUtils.loadIcon(PATH);
            lblWaitingAnimation = new JLabel(
                            new ImageIcon(GenericUtils.scale(GenericUtils.iconToImage(icon), 50, 50, true)));
        }
        return lblWaitingAnimation;
    }
}
