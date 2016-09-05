package ar.com.textillevel.gtlterminal.integration;

public interface TerminalServiceService extends javax.xml.rpc.Service {
    public java.lang.String getTerminalServicePortAddress();

    public ar.com.textillevel.gtlterminal.integration.TerminalService getTerminalServicePort()
                    throws javax.xml.rpc.ServiceException;

    public ar.com.textillevel.gtlterminal.integration.TerminalService getTerminalServicePort(java.net.URL portAddress)
                    throws javax.xml.rpc.ServiceException;
}
