
package ar.com.textillevel.gtlterminal;

import ar.com.textillevel.gtlterminal.ui.Lector;
import ar.com.textillevel.gtlterminal.util.GenericUtils;

public class Main {

    public static void main(String[] args) {
        GenericUtils.setPropertyIfNull(Environment.KEY_IP_SERVER, "192.168.1.119:8080");
        GenericUtils.setPropertyIfNull(Environment.KEY_IP_SERVER2, "192.168.1.119:8080");
        GenericUtils.setPropertyIfNull(Environment.KEY_TERMINAL_NAME, "Terminal local");
        new Lector().setVisible(true);
    }
}
