package ar.com.textillevel.gtlterminal;

public final class Environment {

    public static final String KEY_IP_SERVER = "textillevel.server.ip";
    public static final String KEY_IP_SERVER2 = "textillevel.server.ip2";
    public static final String KEY_TERMINAL_NAME = "textillevel.terminal.name";

    public static void setPropertyIfNull(final String property, final String defaultValue) {
        if (System.getProperty(property) == null) {
            System.setProperty(property, defaultValue);
        }
    }
}
