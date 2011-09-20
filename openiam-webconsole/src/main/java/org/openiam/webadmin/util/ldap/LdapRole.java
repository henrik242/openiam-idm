package org.openiam.webadmin.util.ldap;

import org.openiam.idm.srvc.mngsys.dto.ManagedSys;

import javax.naming.Context;
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
public class LdapRole {
    String objectClass="groupOfUniqueNames";
    String baseDN = "";


     public String add(ManagedSys sys, Map<String, org.openiam.idm.srvc.synch.dto.Attribute> rowAttr) {

         String name = rowAttr.get("role name").getValue();
         String category = rowAttr.get("role category").getValue();
         String roleDescription = rowAttr.get("role description").getValue();
         String roleType = rowAttr.get("role type").getValue();
         String ldapName = null;

         try {
             LdapContext ctx = LdapConnection.connect(sys);

             BasicAttributes basicAttr =  new BasicAttributes();

             basicAttr.put(new BasicAttribute("objectclass", objectClass));
             basicAttr.put(new BasicAttribute("businessCategory", category));
             basicAttr.put(new BasicAttribute("description",roleDescription));
             //basicAttr.put(new BasicAttribute("uniqueMember","uid=jyeung,ou=users,dc=gtawestdir,dc=com"));
             //basicAttr.put(new BasicAttribute("st",state));
             //basicAttr.put(new BasicAttribute("l",city));


             if (roleType.equalsIgnoreCase("roles")) {

              ldapName = "cn=" + name + ",ou=roles,dc=gtawestdir,dc=com";
             }else {
              ldapName = "cn=" + name + ",ou=affiliations,dc=gtawestdir,dc=com";
             }

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
