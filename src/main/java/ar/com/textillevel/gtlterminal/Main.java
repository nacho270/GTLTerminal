package ar.com.textillevel.gtlterminal;

import ar.com.textillevel.gtlterminal.ui.Lector;
import ar.com.textillevel.gtlterminal.util.GenericUtils;

public class Main {

    public static void main(String[] args) {
        GenericUtils.setPropertyIfNull("textillevel.server.ip", "192.168.1.119:8080");
        GenericUtils.setPropertyIfNull("textillevel.terminal.name", "Terminal local");
        new Lector().setVisible(true);
    }
}
