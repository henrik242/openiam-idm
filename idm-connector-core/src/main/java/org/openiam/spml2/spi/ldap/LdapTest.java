package org.openiam.spml2.spi.ldap;

import org.openiam.idm.srvc.mngsys.dto.ManagedSys;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import javax.naming.ldap.LdapName;
import java.util.Hashtable;

/**
 * Created by IntelliJ IDEA.
 * User: suneetshah
 * Date: 8/30/11
 * Time: 4:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class LdapTest {

    public LdapTest() {
    }

    public static LdapContext connect()  throws NamingException {

		//LdapContext ctxLdap = null;
		Hashtable<String, String> envDC = new Hashtable();


		envDC.put(Context.PROVIDER_URL,"ldap://localhost:1389");
		envDC.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
		envDC.put(Context.SECURITY_AUTHENTICATION, "simple" ); // simple
		envDC.put(Context.SECURITY_PRINCIPAL,"cn=Directory Manager");  //"administrator@diamelle.local"
		envDC.put(Context.SECURITY_CREDENTIALS, "openiam");


		return (new InitialLdapContext(envDC,null));


	}

    public static void createOrg() {
        try {
            LdapContext ctx = connect();

            BasicAttributes basicAttr =  new BasicAttributes();

            Attribute a = new BasicAttribute("objectclass","organizationalUnit");
            basicAttr.put(a);
            basicAttr.put(new BasicAttribute("businessCategory","Hospital"));
            basicAttr.put(new BasicAttribute("telephoneNumber","917-371-0126"));
             basicAttr.put(new BasicAttribute("description","My description"));
             basicAttr.put(new BasicAttribute("facsimileTelephoneNumber","917-371-0126"));
            basicAttr.put(new BasicAttribute("postalAddress","49 whortleberry rd"));
            basicAttr.put(new BasicAttribute("postalCode","06896"));
             basicAttr.put(new BasicAttribute("st","ct"));
             basicAttr.put(new BasicAttribute("l","redding"));

            String ldapName = "ou=myhospital,ou=organizations,dc=gtawestdir,dc=com";

            Context result = ctx.createSubcontext(ldapName, basicAttr);

        }catch(Exception e) {
           e.printStackTrace();
        }


    }

    public static void createRole() {
        try {
            LdapContext ctx = connect();

            BasicAttributes basicAttr =  new BasicAttributes();

            Attribute a = new BasicAttribute("objectclass","groupOfUniqueNames");
            basicAttr.put(a);
            basicAttr.put(new BasicAttribute("businessCategory","Hospital"));
            basicAttr.put(new BasicAttribute("description","My description"));

            //String ldapName = "cn=myrole,ou=roles,dc=gtawestdir,dc=com";

            String ldapName = "cn=myrole,ou=affiliations,dc=gtawestdir,dc=com";

            Context result = ctx.createSubcontext(ldapName, basicAttr);

        }catch(Exception e) {
           e.printStackTrace();
        }


    }


    public static void main(String[] args) {
        System.out.println("LdapTest called....");

        LdapTest.createRole();

    }

}
