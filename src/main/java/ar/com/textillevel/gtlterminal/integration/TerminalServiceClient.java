package ar.com.textillevel.gtlterminal.integration;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

public class TerminalServiceClient {

    private static TerminalService service;
    static {
        final TerminalServiceServiceLocator locator = new TerminalServiceServiceLocator();
        try {
            service = locator.getTerminalServicePort(
                            new URL("http://192.168.1.119:8080/GTL-gtlback-server/TerminalService"));
        } catch (final MalformedURLException e) {
            e.printStackTrace();
        } catch (final ServiceException e) {
            e.printStackTrace();
        }
    }

    public static void marcarEntregado(final String codigo) throws RemoteException {
        service.marcarEntregado(codigo);
    }

    public static void reingresar(final String codigo) throws RemoteException {
        service.reingresar(codigo);
    }
}
