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
import javax.naming.directory.SearchControls;
import javax.naming.NamingEnumeration;
import javax.naming.directory.Attributes;
import javax.naming.ldap.*;
import javax.naming.directory.SearchResult;
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

    public static  void getMembership() {
        System.out.println("GetMembership...");
         try {
            LdapContext ctx = connect();

             //Create the search controls
			SearchControls searchCtls = new SearchControls();

			//Specify the search scope
			searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);

			//specify the LDAP search filter
			String searchFilter = "(&(objectClass=inetOrgPerson)(uid=Suneet.GTAUser6))" ;

			//Specify the Base for the search
			String searchBase = "ou=users,DC=gtawestdir,DC=com";

			//initialize counter to total the group members
			int totalResults = 0;

			//Specify the attributes to return
			String returnedAtts[]={"memberOf", "isMemberOf"};
			searchCtls.setReturningAttributes(returnedAtts);

			//Search for objects using the filter
			NamingEnumeration answer = ctx.search(searchBase, searchFilter, searchCtls);

			//Loop through the search results
			while (answer.hasMoreElements()) {
				SearchResult sr = (SearchResult)answer.next();

				System.out.println(">>>" + sr.getName());

				//Print out the groups

				Attributes attrs = sr.getAttributes();
				if (attrs != null) {

					try {
						for (NamingEnumeration ae = attrs.getAll();ae.hasMore();) {
							Attribute attr = (Attribute)ae.next();
							System.out.println("Attribute: " + attr.getID());
							for (NamingEnumeration e = attr.getAll();e.hasMore();totalResults++) {

								System.out.println(" " +  totalResults + ". " +  e.next());
							}

						}

					}
					catch (NamingException e)	{
						System.err.println("Problem listing membership: " + e);
					}

				}
			}



         }catch(Exception e) {

             e.printStackTrace();
         }

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

        LdapTest.getMembership();

    }

}
