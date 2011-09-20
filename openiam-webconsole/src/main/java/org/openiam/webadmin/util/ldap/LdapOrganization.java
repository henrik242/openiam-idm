package org.openiam.webadmin.util.ldap;

import org.openiam.idm.srvc.mngsys.dto.ManagedSys;
import org.openiam.idm.srvc.org.dto.Organization;

import javax.naming.Context;
import javax.naming.directory.Attribute;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.ldap.LdapContext;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: suneetshah
 * Date: 8/31/11
 * Time: 12:24 AM
 * To change this template use File | Settings | File Templates.
 */
public class LdapOrganization {
    String objectClass="organizationalUnit";
    String baseDN = "";


     public String add(ManagedSys sys, Map<String, org.openiam.idm.srvc.synch.dto.Attribute> rowAttr) {

         String name = rowAttr.get("org name").getValue();
         String category = rowAttr.get("business category").getValue();
         String phoneNumber = rowAttr.get("phone").getValue();
         String faxNumber = rowAttr.get("fax").getValue();
         String postalAddress = rowAttr.get("address").getValue();
         String postalCode = rowAttr.get("postal code").getValue();
         String description = null;
         if (rowAttr.get("description") != null) {
           description = rowAttr.get("description").getValue();
         }


         try {
             LdapContext ctx = LdapConnection.connect(sys);

             BasicAttributes basicAttr =  new BasicAttributes();

             basicAttr.put(new BasicAttribute("objectclass", objectClass));
             basicAttr.put(new BasicAttribute("businessCategory", category));
             basicAttr.put(new BasicAttribute("telephoneNumber",phoneNumber));
             if (description != null) {
                 basicAttr.put(new BasicAttribute("description",description));
             }
             basicAttr.put(new BasicAttribute("facsimileTelephoneNumber",faxNumber));
             basicAttr.put(new BasicAttribute("postalAddress",postalAddress));
             basicAttr.put(new BasicAttribute("postalCode",postalCode));
             //basicAttr.put(new BasicAttribute("st",state));
             //basicAttr.put(new BasicAttribute("l",city));



             String ldapName = "ou=" + name + ",ou=organizations,dc=gtawestdir,dc=com";

             Context result = ctx.createSubcontext(ldapName, basicAttr);
             return ldapName;

         }catch(Exception e) {
             System.out.println(e.toString());
           // e.printStackTrace();

             return null;
         }


    }

    public String update() {
        return null;

    }

    public void delete() {



    }

    public String getObjectClass() {
        return objectClass;
    }

    public String getBaseDN() {
        return baseDN;
    }
}
