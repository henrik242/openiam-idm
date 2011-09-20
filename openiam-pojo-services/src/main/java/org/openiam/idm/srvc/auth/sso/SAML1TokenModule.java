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

import org.openiam.base.id.UUIDGen;
import org.openiam.idm.srvc.auth.dto.SSOToken;
import org.openiam.util.encrypt.Cryptor;

import javax.xml.namespace.QName;
import org.w3c.dom.Element;
import org.w3c.dom.Document;

import org.opensaml.ws.transport.http.HttpServletResponseAdapter;
import org.opensaml.xml.parse.BasicParserPool;
import org.opensaml.xml.util.XMLHelper;
import org.opensaml.Configuration;
import org.opensaml.xml.ConfigurationException;
import org.opensaml.xml.XMLObjectBuilderFactory;
import org.opensaml.xml.XMLObjectBuilder;
import org.opensaml.common.*;
import org.opensaml.xml.io.Marshaller;
import org.opensaml.xml.io.MarshallerFactory;
import org.opensaml.xml.io.Unmarshaller;
import org.opensaml.xml.io.UnmarshallerFactory;
import org.opensaml.xml.io.UnmarshallingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.joda.time.chrono.ISOChronology;

import org.opensaml.saml1.core.Assertion;
import org.opensaml.saml1.core.AuthenticationStatement;
import org.opensaml.saml1.core.Conditions;
import org.opensaml.saml1.core.NameIdentifier;
import org.opensaml.saml1.core.Request;
import org.opensaml.saml1.core.Subject;

/**
 * Generates SAML 1 compliant assertions for use with SSO
 * @author suneet
 *
 */
public class SAML1TokenModule implements SSOTokenModule {
	
	private static final Log log = LogFactory.getLog(SAML1TokenModule.class);
	
	protected Cryptor cryptor;
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

    public SAML1TokenModule() {
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
       
       String tokenIssuer = (String)tokenParam.get("TOKEN_ISSUER");
       String tokenLife = (String)tokenParam.get("TOKEN_LIFE");
       int idleTime = Integer.parseInt(tokenLife.trim());
       
       
       DateTime expectedIssueInstant = new DateTime(System.currentTimeMillis());
       DateTime notAfterTime = new DateTime(System.currentTimeMillis());
       notAfterTime = notAfterTime.plusMinutes(idleTime);
       
       String userId = (String)tokenParam.get("USER_ID");
       
       int expectedMinorVersion = 1;      
       qname = Request.DEFAULT_ELEMENT_NAME;
	       
		XMLObjectBuilderFactory builderFactory = Configuration.getBuilderFactory();
	
	       // Get the assertion builder based on the assertion element name
	       SAMLObjectBuilder<Assertion> builder = (SAMLObjectBuilder<Assertion>)builderFactory.getBuilder(Assertion.DEFAULT_ELEMENT_NAME);

	       if(builder == null){
	           System.out.println("Unable to retrieve builder for object QName = " + qname);
	       }
	           
	       // Create the assertion
	       Assertion assertion = builder.buildObject();
	       assertion.setIssuer(tokenIssuer);
	       assertion.setVersion(SAMLVersion.VERSION_10);
	       assertion.setID(UUIDGen.getUUID());
	       assertion.setIssueInstant(expectedIssueInstant);

	       // conditions
	       Conditions conditions = buildConditions(expectedIssueInstant, notAfterTime);           
	       assertion.setConditions(conditions);

	       // authentication statement

	       List<AuthenticationStatement> authStatements = assertion.getAuthenticationStatements();
	       authStatements.add(buildAuthenticateStatement(expectedIssueInstant, userId, tokenIssuer));

	       
	      
	       
	       MarshallerFactory marshallerFactory = Configuration.getMarshallerFactory();

	       // Get the Subject marshaller
	       Marshaller marshaller = marshallerFactory.getMarshaller(assertion);

	       // Marshall the Subject
	       try {
	    	  
	           Element generatedDOM = marshaller.marshall(assertion, parser.newDocument());
	           SSOToken ssoTkn = new SSOToken();
	           ssoTkn.setToken( XMLHelper.nodeToString(generatedDOM));
	           ssoTkn.setExpirationTime(notAfterTime.toDate());
	           return ssoTkn;
	   	   
	       }catch(Exception e) {
	    	   e.printStackTrace();
	       }
	       return null;
		
	}

	private Conditions buildConditions(DateTime expectedIssueInstant, DateTime notAfterTime) {
	       SAMLObjectBuilder<Conditions> conditionBuilder = (SAMLObjectBuilder<Conditions>)builderFactory.getBuilder(Conditions.DEFAULT_ELEMENT_NAME);
	       if(conditionBuilder == null){
	           System.out.println("Unable to retrieve builder for object = " + Conditions.DEFAULT_ELEMENT_NAME);
	       }
	       Conditions conditions = conditionBuilder.buildObject();
	       conditions.setNotBefore(expectedIssueInstant);
	       conditions.setNotOnOrAfter(notAfterTime);	
	       return conditions;
	}

	private AuthenticationStatement buildAuthenticateStatement(DateTime expectedIssueInstant,String userId, String issuer) {
	       SAMLObjectBuilder<AuthenticationStatement> authStatementBuilder = (SAMLObjectBuilder<AuthenticationStatement>)builderFactory.getBuilder(AuthenticationStatement.DEFAULT_ELEMENT_NAME);
	       if(authStatementBuilder == null){
	           System.out.println("Unable to retrieve builder for object = " + AuthenticationStatement.DEFAULT_ELEMENT_NAME);
	       }
	       AuthenticationStatement authStatement = authStatementBuilder.buildObject();
	       authStatement.setAuthenticationInstant(expectedIssueInstant);
	       authStatement.setAuthenticationMethod("urn:oasis:names:tc:SAML:1.0:am:password");
	       // set the subject
	       authStatement.setSubject(buildSubject(userId, issuer));

	       return authStatement;

	}

	private Subject buildSubject(String userId, String issuer) {
	       SAMLObjectBuilder<Subject> subjectBuilder = (SAMLObjectBuilder<Subject>)builderFactory.getBuilder(Subject.DEFAULT_ELEMENT_NAME);
	       if(subjectBuilder == null){
	           System.out.println("Unable to retrieve builder for object = " + Subject.DEFAULT_ELEMENT_NAME);
	       }
	       Subject subject = subjectBuilder.buildObject();
	       
	       // build the name identifier
	       SAMLObjectBuilder<NameIdentifier> nameIdentifierBuilder = (SAMLObjectBuilder<NameIdentifier>)builderFactory.getBuilder(NameIdentifier.DEFAULT_ELEMENT_NAME);
	       if(nameIdentifierBuilder == null){
	           System.out.println("Unable to retrieve builder for object = " + NameIdentifier.DEFAULT_ELEMENT_NAME);
	       }
	       NameIdentifier nameIdentifier = nameIdentifierBuilder.buildObject();
	       nameIdentifier.setNameIdentifier(userId);
	       nameIdentifier.setNameQualifier(issuer);
	       
	       subject.setNameIdentifier(nameIdentifier);
	       

	       return subject;

	}
	
	
	
	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.auth.sso.SSOTokenModule#isTokenValid(java.lang.String, java.lang.String)
	 */
	public boolean isTokenValid(String userId,String principal, String token) {
		
		StringReader reader = new StringReader(token);
		try {
			BasicParserPool ppMgr = new BasicParserPool();
			ppMgr.setNamespaceAware(true);
			Document inCommonMDDoc = ppMgr.parse(reader);
			Element metadataRoot = inCommonMDDoc.getDocumentElement();
	
			
			UnmarshallerFactory unmarshallerFactory  = Configuration.getUnmarshallerFactory();
			Unmarshaller unmarshaller = unmarshallerFactory.getUnmarshaller(metadataRoot);
			Assertion samlAssertion = (Assertion)unmarshaller.unmarshall(metadataRoot); 
			samlAssertion.validate(true);
			return true;
		}catch(Exception e) {
			log.error("Error during token validation: " + e);
			return false;
		}

	}
	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.auth.sso.SSOTokenModule#refreshToken(java.lang.String, java.lang.String)
	 */
	public SSOToken refreshToken(Map tokenParam) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public static void main(String [] args) {
		System.out.println("Test SAML 1");
		
		Map tokenParam = new HashMap();
		
		SAML1TokenModule token = new SAML1TokenModule();
		SSOToken tk = token.createToken(tokenParam);
		System.out.println("token = " + tk.getToken() + "\n\n");
		
		SAML1TokenModule token2 = new SAML1TokenModule();
		SSOToken tk2 = token2.createToken(tokenParam);
		System.out.println("token = " + tk2.getToken());
	
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.auth.sso.SSOTokenModule#setCryptor(org.openiam.util.encrypt.Cryptor)
	 */
	public void setCryptor(Cryptor cryptor) {
		// TODO Auto-generated method stub
		
	}
	
	public void setTokenLife(int tokenLife) {
		
	}

}
