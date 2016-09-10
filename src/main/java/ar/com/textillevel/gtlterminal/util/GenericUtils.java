package ar.com.textillevel.gtlterminal.util;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.Timer;

import ar.com.textillevel.gtlterminal.AnchorTrick;
import ar.com.textillevel.gtlterminal.ui.SwingWorker;
import ar.com.textillevel.gtlterminal.ui.WaitDialog;

public class GenericUtils {

    private static final String REG_EXP_NUMERO = "-?[0-9]+([,|\\.][0-9]+)?";
    private static final Pattern pattern = Pattern.compile(REG_EXP_NUMERO);

    public static Image scale(Image img, int width, int height, boolean proportional) {
        Image scaledImg = null;
        if (proportional) {
            final float scale = Math.min((float) width / img.getWidth(null), (float) height / img.getHeight(null));
            final int swidth = Math.round(scale * img.getWidth(null));
            final int sheight = Math.round(scale * img.getHeight(null));
            scaledImg = img.getScaledInstance(swidth, sheight, Image.SCALE_DEFAULT);
        } else {
            scaledImg = img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        }
        return scaledImg;
    }

    public static Image iconToImage(Icon icon) {
        if (icon instanceof ImageIcon) {
            return ((ImageIcon) icon).getImage();
        } else {
            final int w = icon.getIconWidth();
            final int h = icon.getIconHeight();
            final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            final GraphicsDevice gd = ge.getDefaultScreenDevice();
            final GraphicsConfiguration gc = gd.getDefaultConfiguration();
            final BufferedImage image = gc.createCompatibleImage(w, h);
            final Graphics2D g = image.createGraphics();
            icon.paintIcon(null, g, 0, 0);
            g.dispose();
            return image;
        }
    }

    public static Icon loadIcon(String path) {
        return new ImageIcon(getResource(path));
    }

    public static URL getResource(String path) {
        final ClassLoader cl = new AnchorTrick().getClass().getClassLoader();
        return cl.getResource(path);
    }

    public static void centrar(Window window) {
        final int x = ((Toolkit.getDefaultToolkit().getScreenSize().width - window.getWidth()) / 2);
        final int y = ((Toolkit.getDefaultToolkit().getScreenSize().height - window.getHeight()) / 2);
        window.setLocation(x, y);
    }

    public static boolean esNumerico(String text) {
        if (text == null) {
            return false;
        }
        text = text.replaceAll(",", "").replaceAll("\\.", "");
        final Matcher matcher = pattern.matcher(text.trim());
        return matcher.matches();
    }

    public static boolean isUnix() {
        final String os = System.getProperty("os.name").toLowerCase();
        return (os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0);
    }

    public static boolean isWindows() {
        final String os = System.getProperty("os.name").toLowerCase();
        return (os.indexOf("win") >= 0);
    }

    public static GridBagConstraints createGridBagConstraints(int x, int y, int posicion, int fill, Insets insets,
                    int cantX, int cantY, double weightx, double weighty) {
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = posicion;
        gbc.fill = fill;
        gbc.insets = insets;
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = cantX;
        gbc.gridheight = cantY;
        gbc.weightx = weightx;
        gbc.weighty = weighty;
        return gbc;
    }

    public static String convertirNumeroATexto(Double numero) {
        if (numero < 0) {
            return "";
        }
        if (Math.floor(numero.doubleValue()) - Math.abs(numero.doubleValue()) == 0) {
            return Num2Text.getInstance().convertirLetras(numero.intValue());
        } else {
            final String strNro = String.valueOf(numero);
            final String pe = strNro.substring(0, strNro.indexOf('.'));
            String pd = strNro.substring(strNro.indexOf('.') + 1, strNro.length());
            if (pd.length() >= 2) {
                pd = pd.substring(0, 2);
            }
            pd = (pd.length() == 1 ? pd + "0" : pd);
            return Num2Text.getInstance().convertirLetras(Integer.parseInt(pe)) + " con "
                            + Num2Text.getInstance().convertirLetras(Integer.parseInt(pd));
        }
    }

    public static <T> Collection<T> restaConjuntosOrdenada(Collection<T> izq, Collection<T> der) {
        final ArrayList<T> aux = new ArrayList<>();
        for (final T obj : izq) {
            if (!der.contains(obj)) {
                aux.add(obj);
            }
        }
        return aux;
    }

    public static void showTemporaryDialog(final int waitTime, final String titulo, final JOptionPane pane) {
        final JDialog dialog = pane.createDialog(titulo);
        setEstadoPanel(pane, false);
        final Timer timer = new Timer(waitTime, a -> dialog.dispose());
        timer.start();
        dialog.setVisible(true);
        dialog.dispose();
    }

    public static void setEstadoPanel(JComponent panel, boolean estado) {
        final Component componentes[] = panel.getComponents();
        for (final Component componente : componentes) {
            final JComponent component = (JComponent) componente;
            if (component instanceof JPanel || component instanceof JScrollPane || component instanceof JViewport) {
                setEstadoPanel(component, estado);
            } else if (component instanceof JTable || component instanceof JList || !(component instanceof JLabel)) {
                component.setEnabled(estado);
            }
        }
        panel.setEnabled(estado);
    }

    public interface BackgroundTask {
        public void perform();
    }

    public static void realizarOperacionConDialogoDeEspera(String textoEspera, final BackgroundTask task) {
        final SwingWorker sw = new SwingWorker() {
            @Override
            public Object construct() {
                task.perform();
                WaitDialog.stopWait();
                return 0;
            }
        };
        sw.start();
        WaitDialog.startWait(textoEspera);
    }
}
