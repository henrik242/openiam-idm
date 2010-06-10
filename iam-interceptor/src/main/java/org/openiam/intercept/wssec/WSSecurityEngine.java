/*
 * Copyright  2003-2005 The Apache Software Foundation.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package org.openiam.intercept.wssec;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ws.security.SOAPConstants;
import org.apache.ws.security.WSDocInfo;
import org.apache.ws.security.WSSecurityException;
import org.apache.ws.security.components.crypto.Crypto;
import org.apache.ws.security.conversation.ConversationConstants;
import org.apache.ws.security.message.token.UsernameToken;
import org.apache.ws.security.processor.Processor;
import org.apache.ws.security.util.WSSecurityUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.security.auth.callback.CallbackHandler;
import javax.xml.namespace.QName;

import java.util.Vector;


public class WSSecurityEngine {
    public static final String VALUE_TYPE = "ValueType";
    
    private static final Log log= LogFactory.getLog(WSSecurityEngine.class);
    

    private static WSSecurityEngine engine = null;
    
    /**
     * The WSSConfig instance used by this SecurityEngine to
     * find Processors for processing security headers
     */
    private WSSConfig wssConfig = null;
    private boolean doDebug = false;
    /**
     * <code>wsse:BinarySecurityToken</code> as defined by WS Security specification
     */
    public static final QName binaryToken = 
        new QName(WSConstants.WSSE_NS, WSConstants.BINARY_TOKEN_LN);
    /**
     * <code>wsse:UsernameToken</code> as defined by WS Security specification
     */
    public static final QName usernameToken = 
        new QName(WSConstants.WSSE_NS, WSConstants.USERNAME_TOKEN_LN);
    /**
     * <code>wsu:Timestamp</code> as defined by OASIS WS Security specification,
     */
    public static final QName timeStamp = 
        new QName(WSConstants.WSU_NS, WSConstants.TIMESTAMP_TOKEN_LN);
    /**
     * <code>wsse11:signatureConfirmation</code> as defined by OASIS WS Security specification,
     */
    public static final QName signatureConfirmation = 
        new QName(WSConstants.WSSE11_NS, WSConstants.SIGNATURE_CONFIRMATION_LN);
    /**
     * <code>ds:Signature</code> as defined by XML Signature specification,
     * enhanced by WS Security specification
     */
    public static final QName SIGNATURE = 
        new QName(WSConstants.SIG_NS, WSConstants.SIG_LN);
    /**
     * <code>xenc:EncryptedKey</code> as defined by XML Encryption specification,
     * enhanced by WS Security specification
     */
    public static final QName ENCRYPTED_KEY = 
        new QName(WSConstants.ENC_NS, WSConstants.ENC_KEY_LN);
    /**
     * <code>xenc:EncryptedData</code> as defined by XML Encryption specification,
     * enhanced by WS Security specification
     */
    public static final QName ENCRYPTED_DATA = 
        new QName(WSConstants.ENC_NS, WSConstants.ENC_DATA_LN);
    /**
     * <code>xenc:ReferenceList</code> as defined by XML Encryption specification,
     */
    public static final QName REFERENCE_LIST = 
        new QName(WSConstants.ENC_NS, WSConstants.REF_LIST_LN);
    /**
     * <code>saml:Assertion</code> as defined by SAML specification
     */
    public static final QName SAML_TOKEN = 
        new QName(WSConstants.SAML_NS, WSConstants.ASSERTION_LN);

    /**
     * <code>wsc:DerivedKeyToken</code> as defined by WS-SecureConversation specification
     */
    public static final QName DERIVED_KEY_TOKEN_05_02 = 
        new QName(ConversationConstants.WSC_NS_05_02, ConversationConstants.DERIVED_KEY_TOKEN_LN);

    /**
     * <code>wsc:SecurityContextToken</code> as defined by WS-SecureConversation specification
     */
    public static final QName SECURITY_CONTEXT_TOKEN_05_02 = 
        new QName(ConversationConstants.WSC_NS_05_02, ConversationConstants.SECURITY_CONTEXT_TOKEN_LN);

    /**
     * <code>wsc:DerivedKeyToken</code> as defined by WS-SecureConversation specification in WS-SX
     */
    public static final QName DERIVED_KEY_TOKEN_05_12 = 
        new QName(ConversationConstants.WSC_NS_05_12, ConversationConstants.DERIVED_KEY_TOKEN_LN);

    /**
     * <code>wsc:SecurityContextToken</code> as defined by WS-SecureConversation specification in 
     * WS-SX
     */
    public static final QName SECURITY_CONTEXT_TOKEN_05_12 = 
        new QName(ConversationConstants.WSC_NS_05_12, ConversationConstants.SECURITY_CONTEXT_TOKEN_LN);
    
    public WSSecurityEngine() {
    }

    /**
     * Get a singleton instance of security engine.
     * <p/>
     *
     * @return ws-security engine.
     */
    public synchronized static WSSecurityEngine getInstance() {
        if (engine == null) {
            engine = new WSSecurityEngine();
        }
        return engine;
    }
    
    /**
     * @return      the WSSConfig object set on this instance, or
     *              the statically defined one, if the instance-level
     *              config object is null.
     */
    public final WSSConfig
    getWssConfig() {
        return (wssConfig == null) ? WSSConfig.getDefaultWSConfig() : wssConfig;
    }
    
    /**
     * @param cfg   the WSSConfig instance for this WSSecurityEngine to use
     *
     * @return      the WSSConfig instance previously set on this 
     *              WSSecurityEngine instance
     */
    public final WSSConfig
    setWssConfig(WSSConfig cfg) {
        WSSConfig ret = wssConfig;
        wssConfig = cfg;
        return ret;
    }
    

    
    
    /**
     * Process the security header given the soap envelope as W3C document.
     * <p/>
     * This is the main entry point to verify or decrypt a SOAP envelope.
     * First check if a <code>wsse:Security</code> is available with the
     * defined actor.
     *
     * @param doc    the SOAP envelope as {@link Document}
     * @param actor  the engine works on behalf of this <code>actor</code>. Refer
     *               to the SOAP specification about <code>actor</code> or <code>role
     *               </code>
     * @param cb     a callback hander to the caller to resolve passwords during
     *               encryption and {@link UsernameToken} handling
     * @param crypto the object that implements the access to the keystore and the
     *               handling of certificates.
     * @return a result vector
     * @throws WSSecurityException
     * @see WSSecurityEngine#processSecurityHeader(Element securityHeader, CallbackHandler cb,
     * Crypto sigCrypto, Crypto decCrypto)
     */
    public Vector processSecurityHeader(Document doc,
                                        String actor,
                                        CallbackHandler cb,
                                        Crypto crypto)
            throws WSSecurityException {
        return processSecurityHeader(doc, actor, cb, crypto, crypto);
    }

    /**
     * Process the security header given the soap envelope as W3C document.
     * <p/>
     * This is the main entry point to verify or decrypt a SOAP envelope.
     * First check if a <code>wsse:Security</code> is available with the
     * defined actor.
     *
     * @param doc       the SOAP envelope as {@link Document}
     * @param actor     the engine works on behalf of this <code>actor</code>. Refer
     *                  to the SOAP specification about <code>actor</code> or <code>role
     *                  </code>
     * @param cb        a callback hander to the caller to resolve passwords during
     *                  encryption and {@link UsernameToken} handling
     * @param sigCrypto the object that implements the access to the keystore and the
     *                  handling of certificates for Signature
     * @param decCrypto the object that implements the access to the keystore and the
     *                  handling of certificates for Decryption
     * @return a result vector
     * @throws WSSecurityException
     * @see WSSecurityEngine#processSecurityHeader(
     * Element securityHeader, CallbackHandler cb, Crypto sigCrypto, Crypto decCrypto)
     */
    public Vector processSecurityHeader(Document doc,
                                        String actor,
                                        CallbackHandler cb,
                                        Crypto sigCrypto,
                                        Crypto decCrypto)
            throws WSSecurityException {

    	log.info("processSecurityHeader with doc called.");

        if (actor == null) {
            actor = "";
        }
        Vector wsResult = null;
        SOAPConstants sc = SecurityUtil.getSOAPConstants(doc.getDocumentElement());
        Element elem = SecurityUtil.getSecurityHeader(doc, actor, sc);
        
        if (elem == null) {
        	return null;
        }
        
        log.info("Element in securityheader=" + elem);
        log.info("Processing WS-Security header for '" + actor + "' actor.");
        
        if (elem != null) {
  
            wsResult = processSecurityHeader(elem, cb, sigCrypto, decCrypto);
        }
        return wsResult;
    }

    /**
     * Process the security header given the <code>wsse:Security</code> DOM
     * Element. 
     * 
     * This function loops over all direct child elements of the
     * <code>wsse:Security</code> header. If it finds a known element, it
     * transfers control to the appropriate handling function. The method
     * processes the known child elements in the same order as they appear in
     * the <code>wsse:Security</code> element. This is in accordance to the WS
     * Security specification. <p/>
     * 
     * Currently the functions can handle the following child elements:
     * 
     * <ul>
     * <li>{@link #SIGNATURE <code>ds:Signature</code>}</li>
     * <li>{@link #ENCRYPTED_KEY <code>xenc:EncryptedKey</code>}</li>
     * <li>{@link #REFERENCE_LIST <code>xenc:ReferenceList</code>}</li>
     * <li>{@link #usernameToken <code>wsse:UsernameToken</code>}</li>
     * <li>{@link #timeStamp <code>wsu:Timestamp</code>}</li>
     * </ul>
     *
     * Note that additional child elements can be processed if appropriate
     * Processors have been registered with the WSSCondig instance set
     * on this class.
     *
     * @param securityHeader the <code>wsse:Security</code> header element
     * @param cb             a callback hander to the caller to resolve passwords during
     *                       encryption and {@link UsernameToken}handling
     * @param sigCrypto      the object that implements the access to the keystore and the
     *                       handling of certificates used for Signature
     * @param decCrypto      the object that implements the access to the keystore and the
     *                       handling of certificates used for Decryption
     * @return a Vector of {@link WSSecurityEngineResult}. Each element in the
     *         the Vector represents the result of a security action. The elements
     *         are ordered according to the sequence of the security actions in the
     *         wsse:Signature header. The Vector maybe empty if no security processing
     *         was performed.
     * @throws WSSecurityException
     */
    protected Vector processSecurityHeader(Element securityHeader,
                                           CallbackHandler cb,
                                           Crypto sigCrypto,
                                           Crypto decCrypto) throws WSSecurityException {

        log.info("processSecurityHeader called.");

        /*
         * Gather some info about the document to process and store
         * it for retrieval. Store the implementation of signature crypto
         * (no need for encryption --- yet)
         */
        WSDocInfo wsDocInfo = new WSDocInfo(securityHeader.getOwnerDocument());
        wsDocInfo.setCrypto(sigCrypto);

        NodeList list = securityHeader.getChildNodes();
        log.info("SecurityHeader nodeList" + list);
        
        int len = list.getLength();
        Node elem;
  
        Vector returnResults = new Vector();

        for (int i = 0; i < len; i++) {
            elem = list.item(i);
            if (elem.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            log.info("element localname=" + elem.getLocalName());
            QName el = new QName(elem.getNamespaceURI(), elem.getLocalName());
            final WSSConfig cfg = getWssConfig();
            Processor p = cfg.getProcessor(el);
            /*
             * Call the processor for this token. After the processor returns, 
             * store it for later retrieval. The token processor may store some
             * information about the processed token
             */
            if (p != null) {
                log.info("Call to handle token. need to figure out which class????");
            	// p.handleToken((Element) elem, sigCrypto, decCrypto, cb, wsDocInfo, returnResults, cfg);
                wsDocInfo.setProcessor(p);
            } else {
                /*
                 * Add check for a BinarySecurityToken, add info to WSDocInfo. If BST is
                 * found before a Signature token this would speed up (at least a little
                 * bit) the processing of STR Transform.
                 */
            	log.info( "Unknown Element: " + elem.getLocalName() + " " + elem.getNamespaceURI()                );
            }
        }

        return returnResults;
    }
}
