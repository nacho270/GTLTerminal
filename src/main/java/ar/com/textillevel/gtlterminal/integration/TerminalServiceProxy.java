package ar.com.textillevel.gtlterminal.integration;

public class TerminalServiceProxy implements ar.com.textillevel.gtlterminal.integration.TerminalService {
  private String _endpoint = null;
  private ar.com.textillevel.gtlterminal.integration.TerminalService terminalService = null;
  
  public TerminalServiceProxy() {
    _initTerminalServiceProxy();
  }
  
  public TerminalServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initTerminalServiceProxy();
  }
  
  private void _initTerminalServiceProxy() {
    try {
      terminalService = (new ar.com.textillevel.gtlterminal.integration.TerminalServiceServiceLocator()).getTerminalServicePort();
      if (terminalService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)terminalService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)terminalService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (terminalService != null)
      ((javax.xml.rpc.Stub)terminalService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public ar.com.textillevel.gtlterminal.integration.TerminalService getTerminalService() {
    if (terminalService == null)
      _initTerminalServiceProxy();
    return terminalService;
  }
  
  public ar.com.textillevel.gtlterminal.integration.TerminalServiceResponse marcarEntregado(java.lang.String arg0) throws java.rmi.RemoteException{
    if (terminalService == null)
      _initTerminalServiceProxy();
    return terminalService.marcarEntregado(arg0);
  }
  
  public ar.com.textillevel.gtlterminal.integration.TerminalServiceResponse reingresar(java.lang.String arg0) throws java.rmi.RemoteException{
    if (terminalService == null)
      _initTerminalServiceProxy();
    return terminalService.reingresar(arg0);
  }
  
  
}