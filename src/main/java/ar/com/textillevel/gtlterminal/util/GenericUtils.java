package ar.com.textillevel.gtlterminal.util;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Window;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import ar.com.textillevel.gtlterminal.AnchorTrick;
import ar.com.textillevel.gtlterminal.ui.SwingWorker;
import ar.com.textillevel.gtlterminal.ui.WaitDialog;

public class GenericUtils {

  private static final String REG_EXP_NUMERO = "-?[0-9]+([,|\\.][0-9]+)?";
  private static final Pattern pattern = Pattern.compile(REG_EXP_NUMERO);

  public static Icon loadIcon(String path) {
    return new ImageIcon(getResource(path));
  }

  public static URL getResource(String path) {
    ClassLoader cl = new AnchorTrick().getClass().getClassLoader();
    return cl.getResource(path);
  }

  public static void centrar(Window window) {
    int x = ((Toolkit.getDefaultToolkit().getScreenSize().width - window.getWidth()) / 2);
    int y = ((Toolkit.getDefaultToolkit().getScreenSize().height - window.getHeight()) / 2);
    window.setLocation(x, y);
  }

  public static boolean esNumerico(String text) {
    if (text == null) {
      return false;
    }
    text = text.replaceAll(",", "").replaceAll("\\.", "");
    Matcher matcher = pattern.matcher(text.trim());
    return matcher.matches();
  }

  public static boolean isUnix() {
    String os = System.getProperty("os.name").toLowerCase();
    return (os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0);
  }

  public static boolean isWindows() {
    String os = System.getProperty("os.name").toLowerCase();
    return (os.indexOf("win") >= 0);
  }

  public static GridBagConstraints createGridBagConstraints(int x, int y, int posicion, int fill, Insets insets,
      int cantX, int cantY, double weightx, double weighty) {
    GridBagConstraints gbc = new GridBagConstraints();
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
      String strNro = String.valueOf(numero);
      String pe = strNro.substring(0, strNro.indexOf('.'));
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
    ArrayList<T> aux = new ArrayList<T>();
    for (T obj : izq) {
      if (!der.contains(obj)) {
        aux.add(obj);
      }
    }
    return aux;
  }

  public interface BackgroundTask {
    public void perform();
  }

  public static void realizarOperacionConDialogoDeEspera(String textoEspera, final BackgroundTask task) {
    SwingWorker sw = new SwingWorker() {
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
