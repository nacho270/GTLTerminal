package ar.com.textillevel.gtlterminal.integration;

public interface TerminalService extends java.rmi.Remote {
    public ar.com.textillevel.gtlterminal.integration.TerminalServiceResponse marcarEntregado(java.lang.String arg0,
                    java.lang.String arg1) throws java.rmi.RemoteException;

    public ar.com.textillevel.gtlterminal.integration.TerminalServiceResponse reingresar(java.lang.String arg0,
                    java.lang.String arg1) throws java.rmi.RemoteException;
}
