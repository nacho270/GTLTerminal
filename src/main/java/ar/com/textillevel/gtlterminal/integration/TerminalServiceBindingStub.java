package ar.com.textillevel.gtlterminal.integration;

@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
public class TerminalServiceBindingStub extends org.apache.axis.client.Stub
                implements ar.com.textillevel.gtlterminal.integration.TerminalService {
    private final java.util.Vector cachedSerClasses = new java.util.Vector();
    private final java.util.Vector cachedSerQNames = new java.util.Vector();
    private final java.util.Vector cachedSerFactories = new java.util.Vector();
    private final java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc[] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[2];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1() {
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("marcarEntregado");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg0"),
                        org.apache.axis.description.ParameterDesc.IN,
                        new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"),
                        java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg1"),
                        org.apache.axis.description.ParameterDesc.IN,
                        new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"),
                        java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://impl.terminal.webservices.textillevel.com.ar/",
                        "terminalServiceResponse"));
        oper.setReturnClass(ar.com.textillevel.gtlterminal.integration.TerminalServiceResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("reingresar");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg0"),
                        org.apache.axis.description.ParameterDesc.IN,
                        new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"),
                        java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg1"),
                        org.apache.axis.description.ParameterDesc.IN,
                        new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"),
                        java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://impl.terminal.webservices.textillevel.com.ar/",
                        "terminalServiceResponse"));
        oper.setReturnClass(ar.com.textillevel.gtlterminal.integration.TerminalServiceResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

    }

    public TerminalServiceBindingStub() throws org.apache.axis.AxisFault {
        this(null);
    }

    public TerminalServiceBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service)
                    throws org.apache.axis.AxisFault {
        this(service);
        super.cachedEndpoint = endpointURL;
    }

    public TerminalServiceBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service) super.service).setTypeMappingVersion("1.2");
        java.lang.Class cls;
        javax.xml.namespace.QName qName;
        final javax.xml.namespace.QName qName2;
        final java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
        final java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
        final java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
        final java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
        final java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
        final java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
        final java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
        final java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
        final java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
        final java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
        qName = new javax.xml.namespace.QName("http://impl.terminal.webservices.textillevel.com.ar/",
                        "terminalServiceResponse");
        cachedSerQNames.add(qName);
        cls = ar.com.textillevel.gtlterminal.integration.TerminalServiceResponse.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            final org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            final java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                final java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        final java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        final javax.xml.namespace.QName qName = (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        final java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            final java.lang.Class sf = (java.lang.Class) cachedSerFactories.get(i);
                            final java.lang.Class df = (java.lang.Class) cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        } else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            final org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory) cachedSerFactories
                                            .get(i);
                            final org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory) cachedDeserFactories
                                            .get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        } catch (final java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    @Override
    public ar.com.textillevel.gtlterminal.integration.TerminalServiceResponse marcarEntregado(java.lang.String arg0,
                    java.lang.String arg1) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        final org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://impl.terminal.webservices.textillevel.com.ar/",
                        "marcarEntregado"));

        setRequestHeaders(_call);
        setAttachments(_call);
        try {
            final java.lang.Object _resp = _call.invoke(new java.lang.Object[] { arg0, arg1 });

            if (_resp instanceof java.rmi.RemoteException) {
                throw (java.rmi.RemoteException) _resp;
            } else {
                extractAttachments(_call);
                try {
                    return (ar.com.textillevel.gtlterminal.integration.TerminalServiceResponse) _resp;
                } catch (final java.lang.Exception _exception) {
                    return (ar.com.textillevel.gtlterminal.integration.TerminalServiceResponse) org.apache.axis.utils.JavaUtils
                                    .convert(_resp, ar.com.textillevel.gtlterminal.integration.TerminalServiceResponse.class);
                }
            }
        } catch (final org.apache.axis.AxisFault axisFaultException) {
            throw axisFaultException;
        }
    }

    @Override
    public ar.com.textillevel.gtlterminal.integration.TerminalServiceResponse reingresar(java.lang.String arg0,
                    java.lang.String arg1) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        final org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://impl.terminal.webservices.textillevel.com.ar/",
                        "reingresar"));

        setRequestHeaders(_call);
        setAttachments(_call);
        try {
            final java.lang.Object _resp = _call.invoke(new java.lang.Object[] { arg0, arg1 });

            if (_resp instanceof java.rmi.RemoteException) {
                throw (java.rmi.RemoteException) _resp;
            } else {
                extractAttachments(_call);
                try {
                    return (ar.com.textillevel.gtlterminal.integration.TerminalServiceResponse) _resp;
                } catch (final java.lang.Exception _exception) {
                    return (ar.com.textillevel.gtlterminal.integration.TerminalServiceResponse) org.apache.axis.utils.JavaUtils
                                    .convert(_resp, ar.com.textillevel.gtlterminal.integration.TerminalServiceResponse.class);
                }
            }
        } catch (final org.apache.axis.AxisFault axisFaultException) {
            throw axisFaultException;
        }
    }

}
