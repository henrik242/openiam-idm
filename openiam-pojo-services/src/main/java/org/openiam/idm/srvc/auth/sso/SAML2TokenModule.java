/*
 * Copyright 2009, OpenIAM LLC 
 * This file is part of the OpenIAM Identity and Access Management Suite
 *
 *   OpenIAM Identity and Access Management Suite is free software: 
 *   you can redistribute it and/or modify
 *   it under the terms of the Lesser GNU General Public License 
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
package org.openiam.idm.srvc.auth.sso;

import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.openiam.base.id.UUIDGen;
import org.openiam.idm.srvc.auth.dto.SSOToken;
import org.opensaml.Configuration;
import org.opensaml.common.SAMLObjectBuilder;
import org.opensaml.common.SAMLVersion;
import org.opensaml.saml1.core.NameIdentifier;
import org.opensaml.saml2.core.Assertion;
import org.opensaml.saml2.core.AuthnContext;
import org.opensaml.saml2.core.AuthnContextClassRef;
import org.opensaml.saml2.core.AuthnStatement;
import org.opensaml.saml2.core.Conditions;
import org.opensaml.saml2.core.NameID;
import org.opensaml.saml2.core.NameIDType;
import org.opensaml.saml2.ecp.Request;
import org.opensaml.saml2.core.Subject;
import org.opensaml.xml.ConfigurationException;
import org.opensaml.xml.Namespace;
import org.opensaml.xml.XMLObjectBuilderFactory;
import org.opensaml.xml.io.Marshaller;
import org.opensaml.xml.io.MarshallerFactory;
import org.opensaml.xml.io.UnmarshallerFactory;
import org.opensaml.xml.io.Unmarshaller;
import org.opensaml.xml.parse.BasicParserPool;
import org.opensaml.xml.util.XMLHelper;
import org.w3c.dom.Element;
import org.w3c.dom.Document;


import org.opensaml.saml2.core.Issuer;
import org.opensaml.saml2.core.impl.AuthnContextBuilder;
import org.opensaml.saml2.core.impl.AuthnContextClassRefBuilder;
import org.opensaml.saml2.core.impl.AuthnStatementBuilder;
import org.opensaml.saml2.core.impl.ConditionsBuilder;
import org.opensaml.saml2.core.impl.IssuerBuilder;
import org.opensaml.saml2.core.impl.NameIDBuilder;
import org.opensaml.saml2.core.impl.SubjectBuilder;

/**
 * Generates SAML s compliant assertions for use with SSO
 * @author suneet
 *
 */
public class SAML2TokenModule implements SSOTokenModule {

	private static final Log log = LogFactory.getLog(SAML2TokenModule.class);
	
	static boolean initialized = false;
	
	private QName qname;
	
    /** Parser manager used to parse XML. */
    protected static BasicParserPool parser;
    
    /** XMLObject builder factory. */
    protected static XMLObjectBuilderFactory builderFactory;

    /** XMLObject marshaller factory. */
    protected static MarshallerFactory marshallerFactory;

    /** XMLObject unmarshaller factory. */
    protected static UnmarshallerFactory unmarshallerFactory;
    
    
    public SAML2TokenModule() {
        super();
        
        parser = new BasicParserPool();
        parser.setNamespaceAware(true);
        builderFactory = Configuration.getBuilderFactory();
        marshallerFactory = Configuration.getMarshallerFactory();
        unmarshallerFactory = Configuration.getUnmarshallerFactory();
        init();
    }
    
    private void init() {
        try{
        	if (!initialized) {
	            SAMLBootstrap.bootstrap();
	            initialized = true;
        	}
        }catch(ConfigurationException e){
            e.printStackTrace();
        }
    }
    
	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.auth.sso.SSOTokenModule#createToken(java.util.Map)
	 */
	public SSOToken createToken(Map tokenParam) {
	      String expectedID = "ident";
	       DateTime expectedIssueInstant = new DateTime(System.currentTimeMillis());
	       DateTime notAfterTime = new DateTime(System.currentTimeMillis());
	       
	       String tokenIssuer = (String)tokenParam.get("TOKEN_ISSUER");
	       String tokenLife = (String)tokenParam.get("TOKEN_LIFE");
	       int idleTime = Integer.parseInt(tokenLife.trim());
	       
	       notAfterTime = notAfterTime.plusMinutes(idleTime);

	       String userId = (String)tokenParam.get("USER_ID");
	       String principal = (String)tokenParam.get("PRINCIPAL");
	       
	       int expectedMinorVersion = 1;      
	       qname = Request.DEFAULT_ELEMENT_NAME;
		       
			XMLObjectBuilderFactory builderFactory = Configuration.getBuilderFactory();
		
		       // Get the assertion builder based on the assertion element name
		       SAMLObjectBuilder<Assertion> builder = (SAMLObjectBuilder<Assertion>)builderFactory.getBuilder(Assertion.DEFAULT_ELEMENT_NAME);

		       if(builder == null){
		           System.out.println("Unable to retrieve builder for object QName = " + qname);
		       }
		       
		       // issuer builder
		       IssuerBuilder ib = new IssuerBuilder();
		       Issuer issuer = ib.buildObject();
		       issuer.setValue(tokenIssuer);    
		       
		       // Create the assertion
		       Assertion assertion = builder.buildObject();
		       assertion.setVersion(SAMLVersion.VERSION_20);
		       assertion.setIssuer(issuer);
		       assertion.setID(UUIDGen.getUUID());
		       assertion.setIssueInstant(expectedIssueInstant);
		       assertion.setSubject(this.buildSubject(userId, principal, tokenIssuer));
		       assertion.getAuthnStatements().add(buildAuthenticateStatement(expectedIssueInstant,userId));
		       assertion.setConditions(this.buildConditions(expectedIssueInstant, notAfterTime));     
		       
		       MarshallerFactory marshallerFactory = Configuration.getMarshallerFactory();

		       // Get the Subject marshaller
		       Marshaller marshaller = marshallerFactory.getMarshaller(assertion);

		       // Marshall the Subject
		       try {
		    	  
		           Element generatedDOM = marshaller.marshall(assertion, parser.newDocument());
		           SSOToken ssoTkn = new SSOToken();
		           
		           	String saml = XMLHelper.nodeToString(generatedDOM);
		   			int index = saml.indexOf(">");
		   			String str = saml.substring(index+1);
		           
		           ssoTkn.setToken( str);
		           
		           ssoTkn.setExpirationTime(notAfterTime.toDate());
		           return ssoTkn;
		   	   
		       }catch(Exception e) {
		    	   e.printStackTrace();
		       }
		       return null;

	}
	
	private Namespace buildNameSpace(String issuer) {
		Namespace nb = new Namespace();
		nb.setNamespaceURI(issuer);
		return nb;
	}
	
	private Conditions buildConditions(DateTime expectedIssueInstant, DateTime notAfterTime) {
	       ConditionsBuilder cb = new ConditionsBuilder();
	       Conditions conditions = cb.buildObject();

	       
	       conditions.setNotBefore(expectedIssueInstant);
	       conditions.setNotOnOrAfter(notAfterTime);	
	       return conditions;
	}

	private AuthnStatement buildAuthenticateStatement(DateTime expectedIssueInstant,String userId) {
		AuthnStatementBuilder asb = new AuthnStatementBuilder();
        AuthnStatement authnStatement = asb.buildObject();
        authnStatement.setAuthnInstant(expectedIssueInstant);

        AuthnContextBuilder acb = new AuthnContextBuilder();
        AuthnContext myACI = acb.buildObject();
        AuthnContextClassRefBuilder accrb = new AuthnContextClassRefBuilder();
        AuthnContextClassRef accr = accrb.buildObject();
        accr.setAuthnContextClassRef(AuthnContext.PASSWORD_AUTHN_CTX);
        myACI.setAuthnContextClassRef(accr);
        authnStatement.setAuthnContext(myACI);

        return authnStatement;
		


	}

private Subject buildSubject(String userId, String principal, String issuer) {

		SubjectBuilder sb = new SubjectBuilder();
		Subject sub = sb.buildObject();
		
		NameIDBuilder nb = new NameIDBuilder();
        NameID nameId = nb.buildObject();
        nameId.setNameQualifier(issuer);
        nameId.setValue(principal);
        nameId.setFormat(NameIDType.X509_SUBJECT);
        sub.setNameID(nameId);

         
 

	       return sub;

	}

	

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.auth.sso.SSOTokenModule#isTokenValid(java.lang.String, java.lang.String)
	 */
	public boolean isTokenValid(String userId, String principal, String token) {
		
		StringReader reader = new StringReader(token.trim());
		
		System.out.println("token in validate=" + reader.toString());
		
		try {
			BasicParserPool ppMgr = new BasicParserPool();
			ppMgr.setNamespaceAware(true);
			Document inCommonMDDoc = ppMgr.parse(reader);
			Element metadataRoot = inCommonMDDoc.getDocumentElement();
	
			
			UnmarshallerFactory unmarshallerFactory  = Configuration.getUnmarshallerFactory();
			Unmarshaller unmarshaller = unmarshallerFactory.getUnmarshaller(metadataRoot);
			Assertion samlAssertion = (Assertion)unmarshaller.unmarshall(metadataRoot); 
			
			// carry out some basic validation
			List<AuthnStatement> authnStmtList = samlAssertion.getAuthnStatements();
			if (authnStmtList == null || authnStmtList.size() == 0) {
				return false;
			}
			
			
			Subject sub = samlAssertion.getSubject();
			if (sub == null) {
				return false;
			}
			if (sub.getNameID() == null || sub.getNameID().getValue() == null) {
				return false;
			}
			log.info("Token NameId=" + sub.getNameID().getValue());
			if (!sub.getNameID().getValue().equalsIgnoreCase(principal) ) {
				return false;
			}
			
			
			Conditions cond = samlAssertion.getConditions();
			if (cond == null) {
				return false;
			}
			DateTime curTime = new DateTime(System.currentTimeMillis());
			if (curTime.isBefore(cond.getNotBefore().getMillis())) {
				return false;
			}
			if (curTime.isAfter(cond.getNotOnOrAfter().getMillis())) {
				return false;
			}
			return true;
		}catch(Exception e) {
			log.error("Error during token validation: " + e);
			return false;
		}

	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.auth.sso.SSOTokenModule#refreshToken(java.lang.String, java.lang.String)
	 */
	public SSOToken refreshToken( Map tokenParam) {
		return this.createToken(tokenParam);
	}
	


}
