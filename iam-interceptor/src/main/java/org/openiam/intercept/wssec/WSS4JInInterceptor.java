
package org.openiam.intercept.wssec;

import java.io.IOException;
import java.security.Principal;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;


import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.dom.DOMSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.binding.soap.SoapFault;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.SoapVersion;
import org.apache.cxf.binding.soap.saaj.SAAJInInterceptor;
import org.apache.cxf.common.classloader.ClassLoaderUtils;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.helpers.CastUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.security.SecurityContext;
import org.apache.cxf.staxutils.StaxUtils;
import org.apache.cxf.ws.security.SecurityConstants;
import org.apache.cxf.ws.security.tokenstore.SecurityToken;
import org.apache.cxf.ws.security.tokenstore.TokenStore;
import org.apache.cxf.ws.security.wss4j.AbstractWSS4JInterceptor;
import org.apache.ws.security.WSConstants;
import org.apache.ws.security.WSPasswordCallback;
//import org.apache.ws.security.WSSConfig;
//import org.apache.ws.security.WSSecurityEngine;
import org.apache.ws.security.WSSecurityEngineResult;
import org.apache.ws.security.WSSecurityException;
import org.apache.ws.security.WSUsernameTokenPrincipal;
import org.apache.ws.security.handler.RequestData;
import org.apache.ws.security.handler.WSHandlerConstants;
import org.apache.ws.security.handler.WSHandlerResult;
import org.apache.ws.security.message.WSSecHeader;
import org.apache.ws.security.message.token.SecurityTokenReference;
import org.apache.ws.security.message.token.Timestamp;
import org.apache.ws.security.processor.Processor;
import org.apache.ws.security.util.WSSecurityUtil;


/**
 * Performs WS-Security inbound actions.
 * 
 */
public class WSS4JInInterceptor extends AbstractWSS4JInterceptor {

	static protected ResourceBundle res = ResourceBundle.getBundle("securityconf");

	
    public static final String TIMESTAMP_RESULT = "wss4j.timestamp.result";
    public static final String SIGNATURE_RESULT = "wss4j.signature.result";
    public static final String PRINCIPAL_RESULT = "wss4j.principal.result";
    public static final String PROCESSOR_MAP = "wss4j.processor.map";

    public static final String SECURITY_PROCESSED = WSS4JInInterceptor.class.getName() + ".DONE";
    
	private static final Log LOG = LogFactory.getLog(WSS4JInInterceptor.class);
    

    private SAAJInInterceptor saajIn = new SAAJInInterceptor();
    private boolean ignoreActions;

    /**
     *
     */
    private WSSecurityEngine secEngineOverride;
    
    public WSS4JInInterceptor() {
        super();

        setPhase(Phase.PRE_PROTOCOL);
        getAfter().add(SAAJInInterceptor.class.getName());
    }
    public WSS4JInInterceptor(boolean ignore) {
        this();
        ignoreActions = ignore;
    }

    public WSS4JInInterceptor(Map<String, Object> properties) {
        this();
        
        LOG.info("OpenIAM WSS4JInterceptor Map constructor called.");
        
        setProperties(properties);
        final Map<QName, Object> map = CastUtils.cast(
            (Map)properties.get(PROCESSOR_MAP));
        if (map != null) {
            secEngineOverride = createSecurityEngine(map);
        }
    }

    public void setIgnoreActions(boolean i) {
        ignoreActions = i;
    }
    private SOAPMessage getSOAPMessage(SoapMessage msg) {
        SOAPMessage doc = msg.getContent(SOAPMessage.class);
        if (doc == null) {
            saajIn.handleMessage(msg);
            doc = msg.getContent(SOAPMessage.class);
        }
        return doc;
    }
    
    @Override
    public Object getProperty(Object msgContext, String key) {
        // use the superclass first
        Object result = super.getProperty(msgContext, key);
        
        // handle the special case of the SEND_SIGV
        if (result == null 
            && key == WSHandlerConstants.SEND_SIGV
            && this.isRequestor((SoapMessage)msgContext)) {
            result = ((SoapMessage)msgContext).getExchange().getOutMessage().get(key);
        }               
        return result;
    }
    
    public void handleMessage(SoapMessage msg) throws Fault {
    	LOG.info("INTERCEPTOR handleMessage() called");
    	
    	String enabled = res.getString("WS_SECURITY_ENABLED");
    	if (enabled.equalsIgnoreCase("false")) {
    		return;
    	}
    	
    	if (msg.containsKey(SECURITY_PROCESSED)) {
            return;
        }
        msg.put(SECURITY_PROCESSED, Boolean.TRUE);
        WSSConfig config = (WSSConfig)msg.getContextualProperty(WSSConfig.class.getName()); 
        WSSecurityEngine engine = null;
        if (config != null) {
            engine = new WSSecurityEngine();
            engine.setWssConfig(config);
        } else {
            engine = getSecurityEngine();
        }
        
        SOAPMessage doc = getSOAPMessage(msg);
        


        SoapVersion version = msg.getVersion();

        RequestData reqData = new RequestData();
        /*
         * The overall try, just to have a finally at the end to perform some
         * housekeeping.
         */
        try {
            reqData.setMsgContext(msg);
            computeAction(msg, reqData);
            Vector actions = new Vector();
            String action = getAction(msg, version);

            int doAction = SecurityUtil.decodeAction(action, actions);
            
            LOG.info("Action = " + doAction);
            

            String actor = (String)getOption(WSHandlerConstants.ACTOR);
            LOG.info("Actor =" + actor);
            
            
            CallbackHandler cbHandler = getCallback(reqData, doAction);
            LOG.info("Callback handler=" + cbHandler);
            
            /*
             * Get and check the Signature specific parameters first because
             * they may be used for encryption too.
             */
            //doReceiverAction(doAction, reqData);
            
            Vector wsResult = null;

            
            wsResult = engine.processSecurityHeader(
                doc.getSOAPPart(), 
                actor, 
                cbHandler, 
                reqData.getSigCrypto(), 
                reqData.getDecCrypto()
            );
           
            if (wsResult == null) {
            	 throw new WSSecurityException(WSSecurityException.INVALID_SECURITY);
            }
            // validate the saml assertion
            if (! SecurityUtil.isSamlAssertionValid(doc.getSOAPPart()) ) {
            	LOG.info("IsSamlAssertion failed");
            	throw new WSSecurityException(WSSecurityException.INVALID_SECURITY_TOKEN);
            }
            
           
           // if (reqData.getWssConfig().isEnableSignatureConfirmation()) {
           //     checkSignatureConfirmation(reqData, wsResult);
           // }

            //checkSignatures(msg, reqData, wsResult);
            //checkTimestamps(msg, reqData, wsResult);
            //checkActions(msg, reqData, wsResult, actions);
            
            doResults(msg, actor, doc, wsResult);

 

        } catch (WSSecurityException e) {
            SoapFault fault = createSoapFault(version, e);
            throw fault;
        } catch (XMLStreamException e) {
            throw new SoapFault("STAX_EX", e, version.getSender());
        } catch (SOAPException e) {
            throw new SoapFault("SAAJ_EX", e, version.getSender());
        } finally {
            reqData.clear();
            reqData = null;
        }
    }

    private void checkActions(SoapMessage msg, RequestData reqData, Vector wsResult, Vector actions) 
        throws WSSecurityException {
        /*
         * now check the security actions: do they match, in any order?
         */
        if (!ignoreActions && !checkReceiverResultsAnyOrder(wsResult, actions)) {
            LOG.info("Security processing failed (actions mismatch)");
            throw new WSSecurityException(WSSecurityException.INVALID_SECURITY);
        }
    }
    private void checkSignatures(SoapMessage msg, RequestData reqData, Vector wsResult) 
        throws WSSecurityException {
        /*
         * Now we can check the certificate used to sign the message. In the
         * following implementation the certificate is only trusted if
         * either it itself or the certificate of the issuer is installed in
         * the keystore. Note: the method verifyTrust(X509Certificate)
         * allows custom implementations with other validation algorithms
         * for subclasses.
         */

        // Extract the signature action result from the action vector
        Vector signatureResults = new Vector();
        signatureResults = 
            WSSecurityUtil.fetchAllActionResults(wsResult, WSConstants.SIGN, signatureResults);

        if (!signatureResults.isEmpty()) {
            for (int i = 0; i < signatureResults.size(); i++) {
                WSSecurityEngineResult result = 
                    (WSSecurityEngineResult) signatureResults.get(i);
                
                X509Certificate returnCert = (X509Certificate)result
                    .get(WSSecurityEngineResult.TAG_X509_CERTIFICATE);

                if (returnCert != null && !verifyTrust(returnCert, reqData)) {
                    LOG.info("The certificate used for the signature is not trusted");
                    throw new WSSecurityException(WSSecurityException.FAILED_CHECK);
                }
                msg.put(SIGNATURE_RESULT, result);
            }
        }
    }
    
    protected void checkTimestamps(SoapMessage msg, RequestData reqData, Vector wsResult) 
        throws WSSecurityException {
        /*
         * Perform further checks on the timestamp that was transmitted in
         * the header. In the following implementation the timestamp is
         * valid if it was created after (now-ttl), where ttl is set on
         * server side, not by the client. Note: the method
         * verifyTimestamp(Timestamp) allows custom implementations with
         * other validation algorithms for subclasses.
         */
        // Extract the timestamp action result from the action vector
        Vector timestampResults = new Vector();
        timestampResults = 
            WSSecurityUtil.fetchAllActionResults(wsResult, WSConstants.TS, timestampResults);

        if (!timestampResults.isEmpty()) {
            for (int i = 0; i < timestampResults.size(); i++) {
                WSSecurityEngineResult result = 
                    (WSSecurityEngineResult) timestampResults.get(i);
                Timestamp timestamp = (Timestamp)result.get(WSSecurityEngineResult.TAG_TIMESTAMP);

                if (timestamp != null && !verifyTimestamp(timestamp, decodeTimeToLive(reqData))) {
                    LOG.info("The timestamp could not be validated");
                    throw new WSSecurityException(WSSecurityException.MESSAGE_EXPIRED);
                }
                msg.put(TIMESTAMP_RESULT, result);
            }
        }
    }
    
    /**
     * Do whatever is necessary to determine the action for the incoming message and 
     * do whatever other setup work is necessary.
     * 
     * @param msg
     * @param reqData
     */
    protected void computeAction(SoapMessage msg, RequestData reqData) {
        
    }
    protected void doResults(SoapMessage msg, String actor, SOAPMessage doc, Vector wsResult)
        throws SOAPException, XMLStreamException, WSSecurityException {
        /*
         * All ok up to this point. Now construct and setup the security result
         * structure. The service may fetch this and check it.
         */
        List<Object> results = CastUtils.cast((List)msg.get(WSHandlerConstants.RECV_RESULTS));
        if (results == null) {
            results = new Vector<Object>();
            msg.put(WSHandlerConstants.RECV_RESULTS, results);
        }
        WSHandlerResult rResult = new WSHandlerResult(actor, wsResult);
        results.add(0, rResult);

        SOAPBody body = doc.getSOAPBody();

        XMLStreamReader reader = StaxUtils.createXMLStreamReader(new DOMSource(body));
        // advance just past body
        int evt = reader.next();
        int i = 0;
        while (reader.hasNext() && i < 1
               && (evt != XMLStreamConstants.END_ELEMENT || evt != XMLStreamConstants.START_ELEMENT)) {
            reader.next();
            i++;
        }
        msg.setContent(XMLStreamReader.class, reader);
        String pwType = (String)getProperty(msg, "passwordType");
        if ("PasswordDigest".equals(pwType)) {
            //CXF-2150 - we need to check the UsernameTokens
            for (WSSecurityEngineResult o : CastUtils.cast(wsResult, WSSecurityEngineResult.class)) {
                Integer actInt = (Integer)o.get(WSSecurityEngineResult.TAG_ACTION);
                if (actInt == WSConstants.UT) {
                    WSUsernameTokenPrincipal princ 
                        = (WSUsernameTokenPrincipal)o.get(WSSecurityEngineResult.TAG_PRINCIPAL);
                    if (!princ.isPasswordDigest()) {
                        LOG.info("Non-digest UsernameToken found, but digest required");
                        throw new WSSecurityException(WSSecurityException.INVALID_SECURITY);
                    }
                }
            }            
        }
        if (wsResult != null) {
        for (WSSecurityEngineResult o : CastUtils.cast(wsResult, WSSecurityEngineResult.class)) {
            final Principal p = (Principal)o.get(WSSecurityEngineResult.TAG_PRINCIPAL);
            if (p != null) {
                msg.put(PRINCIPAL_RESULT, p);                   
                SecurityContext sc = msg.get(SecurityContext.class);
                if (sc == null || sc.getUserPrincipal() == null) {
                    SecurityContext c = new SecurityContext() {
                        public Principal getUserPrincipal() {
                            return p;
                        }
                        public boolean isUserInRole(String role) {
                            return false;
                        }
                    };
                    msg.put(SecurityContext.class, c);
                    break;
                }
            }            
        }
        }
    }

    private String getAction(SoapMessage msg, SoapVersion version) {
    	LOG.info("INTERCEPTOR getAction called.");
        String action = (String)getOption(WSHandlerConstants.ACTION);
        if (action == null) {
            action = (String)msg.get(WSHandlerConstants.ACTION);
        }
        if (action == null) {
            LOG.info("No security action was defined!");
            throw new SoapFault("No security action was defined!", version.getReceiver());
        }
        return action;
    }
    
    private class TokenStoreCallbackHandler implements CallbackHandler {
        private CallbackHandler internal;
        private TokenStore store;
        public TokenStoreCallbackHandler(CallbackHandler in,
                                         TokenStore st) {
            internal = in;
            store = st;
        }
        
        public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
            for (int i = 0; i < callbacks.length; i++) {
                WSPasswordCallback pc = (WSPasswordCallback)callbacks[i];
                
                String id = pc.getIdentifier();
                
                if (SecurityTokenReference.ENC_KEY_SHA1_URI.equals(pc.getKeyType())) {
                    for (SecurityToken token : store.getValidTokens()) {
                        if (id.equals(token.getSHA1())) {
                            pc.setKey(token.getSecret());
                            return;
                        }
                    }                    
                } else { 
                    SecurityToken tok = store.getToken(id);
                    if (tok != null) {
                        pc.setKey(tok.getSecret());
                        pc.setCustomToken(tok.getToken());
                        return;
                    }
                }
            }
            if (internal != null) {
                internal.handle(callbacks);
            }
        }
        
    }

    private CallbackHandler getCallback(RequestData reqData, int doAction) throws WSSecurityException {
        /*
         * To check a UsernameToken or to decrypt an encrypted message we need a
         * password.
         */
        CallbackHandler cbHandler = null;
        if ((doAction & (WSConstants.ENCR | WSConstants.UT)) != 0) {
            Object o = ((SoapMessage)reqData.getMsgContext())
                .getContextualProperty(SecurityConstants.CALLBACK_HANDLER);
            if (o instanceof String) {
                try {
                    o = ClassLoaderUtils.loadClass((String)o, this.getClass()).newInstance();
                } catch (Exception e) {
                    throw new WSSecurityException(e.getMessage(), e);
                }
            }            
            if (o instanceof CallbackHandler) {
                cbHandler = (CallbackHandler)o;
            }
            if (cbHandler == null) {
                try {
                    cbHandler = getPasswordCB(reqData);
                } catch (WSSecurityException sec) {
                    Endpoint ep = ((SoapMessage)reqData.getMsgContext()).getExchange().get(Endpoint.class);
                    if (ep != null && ep.getEndpointInfo() != null) {
                        TokenStore store = (TokenStore)ep.getEndpointInfo()
                            .getProperty(TokenStore.class.getName());
                        if (store != null) {
                            return new TokenStoreCallbackHandler(cbHandler, store);
                        }
                    }                    
                    throw sec;
                }
            }
        }
        Endpoint ep = ((SoapMessage)reqData.getMsgContext()).getExchange().get(Endpoint.class);
        if (ep != null && ep.getEndpointInfo() != null) {
            TokenStore store = (TokenStore)ep.getEndpointInfo().getProperty(TokenStore.class.getName());
            if (store != null) {
                return new TokenStoreCallbackHandler(cbHandler, store);
            }
        }
        return cbHandler;
    }


    
    /**
     * @return      the WSSecurityEngine in use by this interceptor.
     *              This engine is defined to be the secEngineOverride
     *              instance, if defined in this class (and supplied through
     *              construction); otherwise, it is taken to be the default
     *              WSSecEngine instance (currently defined in the WSHandler
     *              base class).
     *
     * TODO the WSHandler base class defines secEngine to be static, which
     * is really bad, because the engine has mutable state on it.
     */
    private WSSecurityEngine  getSecurityEngine() {
        if (secEngineOverride != null) {
            return secEngineOverride;
        }
        WSSecurityEngine s = WSSecurityEngine.getInstance();
        
        //check if secEngine has anything in it
        LOG.info("secEngine=" + this.secEngine);
        LOG.info("secEngine config=" + this.secEngine.getWssConfig());
                
        return s;
        
        //s.setWssConfig(secEngine.getWssConfig());
   
        
        //this.secEngine.getWssConfig()
        //return secEngine;
    }
   

    /**
     * @return      a freshly minted WSSecurityEngine instance, using the
     *              (non-null) processor map, to be used to initialize the
     *              WSSecurityEngine instance.
     *
     * TODO The WSS4J APIs leave something to be desired here, but hopefully
     * we'll clean all this up in WSS4J-2.0
     */
    private static WSSecurityEngine  createSecurityEngine( final Map<QName, Object> map ) {
        assert map != null;
        final WSSConfig config = WSSConfig.getNewInstance();
        for (Map.Entry<QName, Object> entry : map.entrySet()) {
            final QName key = entry.getKey();
            Object val = entry.getValue();
            
            if (val instanceof String) {
                String valStr = ((String)val).trim();
                if ("null".equals(valStr) || valStr.length() == 0) {
                    valStr = null;
                }
                config.setProcessor(key, valStr);
            } else if (val instanceof Processor) {
                config.setProcessor(key, (Processor)val);
            } else if (val == null) {
                config.setProcessor(key, (String)val);
            }
        }
        final WSSecurityEngine ret = new WSSecurityEngine();
        ret.setWssConfig(config);
        return ret;
    }
    
    
    /**
     * Create a SoapFault from a WSSecurityException, following the SOAP Message Security
     * 1.1 specification, chapter 12 "Error Handling".
     * 
     * When the Soap version is 1.1 then set the Fault/Code/Value from the fault code
     * specified in the WSSecurityException (if it exists).
     * 
     * Otherwise set the Fault/Code/Value to env:Sender and the Fault/Code/Subcode/Value
     * as the fault code from the WSSecurityException.
     */
    private SoapFault createSoapFault(SoapVersion version, WSSecurityException e) {
        SoapFault fault;
        javax.xml.namespace.QName faultCode = e.getFaultCode();
        if (version.getVersion() == 1.1 && faultCode != null) {
            fault = new SoapFault(e.getMessage(), e, faultCode);
        } else {
            fault = new SoapFault(e.getMessage(), e, version.getSender());
            if (version.getVersion() != 1.1 && faultCode != null) {
                fault.setSubCode(faultCode);
            }
        }
        return fault;
    }
    
}
