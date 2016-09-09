package ar.com.textillevel.gtlterminal.integration;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import ar.com.textillevel.gtlterminal.Environment;

public class TerminalServiceClient {

    private static TerminalService service;
    private static TerminalService service2;

    static {
        final TerminalServiceServiceLocator locator = new TerminalServiceServiceLocator();
        try {
            service = locator.getTerminalServicePort(new URL("http://" + System.getProperty(Environment.KEY_IP_SERVER)
                            + "/GTL-gtlback-server/TerminalService"));
            service2 = locator.getTerminalServicePort(new URL("http://" + System.getProperty(Environment.KEY_IP_SERVER2)
                            + "/GTL-gtlback-server/TerminalService"));
        } catch (final MalformedURLException e) {
            e.printStackTrace();
        } catch (final ServiceException e) {
            e.printStackTrace();
        }
    }

    public static TerminalServiceResponse marcarEntregado(final String codigo) throws RemoteException {
        if (codigo.endsWith("0")) {
            return service.marcarEntregado(codigo, System.getProperty(Environment.KEY_TERMINAL_NAME));
        }
        return service2.marcarEntregado(codigo, System.getProperty(Environment.KEY_TERMINAL_NAME));
    }

    public static TerminalServiceResponse reingresar(final String codigo) throws RemoteException {
        if (codigo.endsWith("0")) {
            return service.reingresar(codigo, System.getProperty(Environment.KEY_TERMINAL_NAME));
        }
        return service2.reingresar(codigo, System.getProperty(Environment.KEY_TERMINAL_NAME));

    }
}
