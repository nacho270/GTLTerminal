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
                    new URL("http://" + System.getProperty("textillevel.server.ip") + "/GTL-gtlback-server/TerminalService"));
        } catch (final MalformedURLException e) {
            e.printStackTrace();
        } catch (final ServiceException e) {
            e.printStackTrace();
        }
    }

    public static TerminalServiceResponse marcarEntregado(final String codigo) throws RemoteException {
        return service.marcarEntregado(codigo);
    }

    public static TerminalServiceResponse reingresar(final String codigo) throws RemoteException {
        return service.reingresar(codigo);
    }
}
