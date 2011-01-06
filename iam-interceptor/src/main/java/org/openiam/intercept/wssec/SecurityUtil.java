/*
 * Copyright 2009, OpenIAM LLC 
 * This file is part of the OpenIAM Identity and Access Management Suite
 *
 *   OpenIAM Identity and Access Management Suite is free software: 
 *   you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License 
 *   version 3 as published by the Free Software Foundation.
 *
 *   OpenIAM is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   Lesser GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenIAM.  If not, see <http://www.gnu.org/licenses/>. *
 */

/**
 * 
 */
package org.openiam.intercept.wssec;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ws.security.SOAP11Constants;
import org.apache.ws.security.SOAP12Constants;
import org.apache.ws.security.SOAPConstants;
import org.apache.ws.security.WSSecurityException;
import org.apache.ws.security.util.StringUtil;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
/**
 * Utilities used by the WS-Security interceptor
 * @author suneet
 *
 */
public class SecurityUtil {
	
	private static final Log log= LogFactory.getLog(SecurityUtil.class);

    public static int decodeAction(String action, Vector actions) throws WSSecurityException {

        int doAction = 0;
        if (action == null) {
            return doAction;
        }
        String single[] = StringUtil.split(action, ' ');
        for (int i = 0; i < single.length; i++) {
            if (single[i].equals(WSHandlerConstants.NO_SECURITY)) {
                doAction = WSConstants.NO_SECURITY;
                return doAction;
            } else if (single[i].equals(WSHandlerConstants.USERNAME_TOKEN)) {
                doAction |= WSConstants.UT;
                actions.add(new Integer(WSConstants.UT));
            } else if (single[i].equals(WSHandlerConstants.SIGNATURE)) {
                doAction |= WSConstants.SIGN;
                actions.add(new Integer(WSConstants.SIGN));
            } else if (single[i].equals(WSHandlerConstants.ENCRYPT)) {
                doAction |= WSConstants.ENCR;
                actions.add(new Integer(WSConstants.ENCR));
            } else if (single[i].equals(WSHandlerConstants.SAML2_TOKEN_UNSIGNED)) {
                    doAction |= WSConstants.ST_UNSIGNED;
                    actions.add(new Integer(WSConstants.ST_UNSIGNED));
            } else if (single[i].equals(WSHandlerConstants.SAML_TOKEN_UNSIGNED)) {
                doAction |= WSConstants.ST_UNSIGNED;
                actions.add(new Integer(WSConstants.ST_UNSIGNED));
            } else if (single[i].equals(WSHandlerConstants.SAML_TOKEN_SIGNED)) {
                doAction |= WSConstants.ST_SIGNED;
                actions.add(new Integer(WSConstants.ST_SIGNED));
            } else if (single[i].equals(WSHandlerConstants.TIMESTAMP)) {
                doAction |= WSConstants.TS;
                actions.add(new Integer(WSConstants.TS));
            } else if (single[i].equals(WSHandlerConstants.NO_SERIALIZATION)) {
                doAction |= WSConstants.NO_SERIALIZE;
                actions.add(new Integer(WSConstants.NO_SERIALIZE));
            } else if (single[i].equals(WSHandlerConstants.SIGN_WITH_UT_KEY)) {
                doAction |= WSConstants.UT_SIGN;
                actions.add(new Integer(WSConstants.UT_SIGN));
            } else if (single[i].equals(WSHandlerConstants.ENABLE_SIGNATURE_CONFIRMATION)) {
                doAction |= WSConstants.SC;
                actions.add(new Integer(WSConstants.SC));
            } else {
                throw new WSSecurityException(
                    "Unknown action defined: " + single[i]
                );
            }
        }
        return doAction;
    }
    
    public static boolean  isSamlAssertionValid(Document doc) {
    	SOAPConstants sc = SecurityUtil.getSOAPConstants(doc.getDocumentElement());
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S'Z'");

        Element soapHeaderElement = 
            (Element) getDirectChild(
                doc.getDocumentElement(), 
                sc.getHeaderQName().getLocalPart(), 
                sc.getEnvelopeURI()
            );
        if (soapHeaderElement == null) { // no SOAP header at all
        	log.info("SoapHeaderElement is null");
            return false;
        }

        NodeList list = 
            soapHeaderElement.getElementsByTagNameNS("urn:oasis:names:tc:SAML:2.0:assertion", "Assertion");
        if (list == null) {
        	log.info("Assertion block is missing");
            return false;
        }
        
        list = 
            soapHeaderElement.getElementsByTagNameNS("urn:oasis:names:tc:SAML:2.0:assertion", "Subject");
        if (list == null) {
        	log.info("Subject block is missing");
            return false;
        }

        // check if the conditions statement is there
        list = 
            soapHeaderElement.getElementsByTagNameNS("urn:oasis:names:tc:SAML:2.0:assertion", "Conditions");
        if (list == null) {
        	log.info("Conditions block is missing");
            return false;
        }
        // check the time on the conditions
        for (int i = 0; i < list.getLength(); i++) {
            Element elem = (Element) list.item(i);
            log.info("Element=" + elem.getNodeName());
            log.info("NotBefore=" + elem.getAttribute("NotBefore"));
            log.info("NotAfter=" + elem.getAttribute("NotOnOrAfter"));
            try { 
            	Date notBefore =  df.parse(elem.getAttribute("NotBefore"));
            	Date notAfter =  df.parse(elem.getAttribute("NotOnOrAfter"));
            	
        		Date curTime = new Date(System.currentTimeMillis());
        		if ( dateDiff(curTime, notBefore) < 0 ) {
        			return false;
        		}
        		if ( dateDiff(curTime, notAfter) > 0) {
        			return false;
        		}
        		

            }catch(ParseException pe) {
            	pe.printStackTrace();
            	log.error(pe);
            }

        }
        
        // check if the authentication statement is there.
        
        list = 
            soapHeaderElement.getElementsByTagNameNS("urn:oasis:names:tc:SAML:2.0:assertion", "AuthnStatement");
        if (list == null) {
        	log.info("AuthnStatement block is missing");
            return false;
        }

        return true;
        
    }

	static public  long  dateDiff(Date curDate, Date targetDate) {

		
		long curTime = curDate.getTime();
		long targetTime = targetDate.getTime();
		
		return curTime - targetTime;
		

		
	}
    
    public static Element getSecurityHeader(Document doc, String actor, SOAPConstants sc) {
        Element soapHeaderElement = 
            (Element) getDirectChild(
                doc.getDocumentElement(), 
                sc.getHeaderQName().getLocalPart(), 
                sc.getEnvelopeURI()
            );

        log.info("getSecurityHeader: actor=" + actor);
        
        if (soapHeaderElement == null) { // no SOAP header at all
        	log.info("SoapHeaderElement is null");
            return null;
        }

        // get all wsse:Security nodes
        NodeList list = 
            soapHeaderElement.getElementsByTagNameNS(WSConstants.WSSE_NS, WSConstants.WSSE_LN);
        if (list == null) {
            return null;
        }
        log.info("getSecurityHeader: nodeList is not null");
        log.info("getSecurityHeader: nodeList lenght is: " + list.getLength());
        for (int i = 0; i < list.getLength(); i++) {
            Element elem = (Element) list.item(i);
            Attr attr = 
                elem.getAttributeNodeNS(
                    sc.getEnvelopeURI(), sc.getRoleAttributeQName().getLocalPart()
                );
            String hActor = (attr != null) ? attr.getValue() : null;
            
            log.info("hActor=" + hActor);
            return elem;
            
            //if (SecurityUtil.isActorEqual(actor, hActor)) {
            //??    return elem;
           // }
        }
        return null;
    }
    
    public static Node getDirectChild(
            Node fNode, 
            String localName,
            String namespace
        ) {
            for (
                Node currentChild = fNode.getFirstChild(); 
                currentChild != null; 
                currentChild = currentChild.getNextSibling()
            ) {
                if (localName.equals(currentChild.getLocalName())
                    && namespace.equals(currentChild.getNamespaceURI())) {
                    return currentChild;
                }
            }
            return null;
        }

    public static boolean isActorEqual(String actor, String hActor) {
        
        if (((hActor == null) || (hActor.length() == 0)) 
            && ((actor == null) || (actor.length() == 0))) {
            return true;
        }
        
        if ((hActor != null) && (actor != null) && hActor.equalsIgnoreCase(actor)) {
            return true;
        }
        
        return false;
    }
    
    public static SOAPConstants getSOAPConstants(Element startElement) {
        Document doc = startElement.getOwnerDocument();
        String ns = doc.getDocumentElement().getNamespaceURI();
        if (WSConstants.URI_SOAP12_ENV.equals(ns)) {
            return new SOAP12Constants();
        }
        return new SOAP11Constants();
    }
	
}
