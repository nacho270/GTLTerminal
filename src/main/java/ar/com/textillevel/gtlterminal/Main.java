package ar.com.textillevel.gtlterminal;

import ar.com.textillevel.gtlterminal.ui.Lector;

public class Main {

    public static void main(String[] args) {
        if (System.getProperty("textillevel.server.ip") == null) {
            System.setProperty("textillevel.server.ip", "192.168.1.119:8080");
        }
        new Lector().setVisible(true);
    }
}
