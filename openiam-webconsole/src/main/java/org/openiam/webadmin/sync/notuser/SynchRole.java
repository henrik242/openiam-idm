package org.openiam.webadmin.sync.notuser;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVStrategy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.idm.srvc.mngsys.dto.ManagedSys;
import org.openiam.idm.srvc.mngsys.service.ManagedSystemDataService;
import org.openiam.idm.srvc.org.dto.Organization;
import org.openiam.idm.srvc.org.service.OrganizationDataService;
import org.openiam.idm.srvc.role.dto.RoleId;
import org.openiam.idm.srvc.role.ws.RoleDataWebService;
import org.openiam.idm.srvc.role.dto.Role;
import org.openiam.idm.srvc.role.ws.RoleResponse;
import org.openiam.idm.srvc.synch.dto.Attribute;
import org.openiam.idm.srvc.synch.dto.LineObject;
import org.openiam.idm.srvc.synch.dto.SynchConfig;
import org.openiam.webadmin.util.ldap.LdapRole;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by IntelliJ IDEA.
 * User: suneetshah
 * Date: 8/31/11
 * Time: 3:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class SynchRole {

    private static final Log log = LogFactory.getLog(SynchRole.class);
    protected LineObject rowHeader = new LineObject();

protected ManagedSystemDataService managedSysService;
protected RoleDataWebService roleDataService;
protected LdapRole ldapRole;


   public void synch(SynchConfig config) {
       System.out.println("SynchOrg called.");

       Reader reader = null;
       String fileName = config.getFileName();

       		File file = new File(config.getFileName());
		try {
			reader = new FileReader(file);
		}catch(FileNotFoundException fe) {

			fe.printStackTrace();
            return;

		}



		CSVParser parser = new CSVParser(reader, CSVStrategy.EXCEL_STRATEGY);
		try {




			int ctr = 0;
			String[][] fileContentAry =   parser.getAllValues();
			int size = fileContentAry.length;

            System.out.println("Size=" + size);

            ManagedSys sys =  managedSysService.getManagedSys("101");


			for (String[] lineAry : fileContentAry) {
				System.out.println("File Row #= " + lineAry[0]);
				if (ctr == 0) {
					populateTemplate(lineAry);
					ctr++;
				}else {

					LineObject rowObj = rowHeader.copy();
					populateRowObject(rowObj, lineAry);

                    System.out.println("LineObject=" + rowObj);

					try {


						// check if the user exists or not
						Map<String, Attribute> rowAttr = rowObj.getColumnMap();
						//
                        // add object to the organization service

                        String name = rowAttr.get("role name").getValue();
                         String roleType = rowAttr.get("role type").getValue();
                        String category = rowAttr.get("role category").getValue();
                        String description = rowAttr.get("role description").getValue();

                        if (name != null) {
                           RoleResponse resp =  roleDataService.getRole("USR_SEC_DOMAIN", name);
                           if (resp.getRole() == null) {


                                // create a new org
                               Role rl = new Role();
                               RoleId rlId = new RoleId("USR_SEC_DOMAIN", name);
                               rl.setDescription(description);
                               rl.setRoleName(name);
                               rl.setId(rlId);
                               rl.setStatus("ACTIVE");
                               roleDataService.addRole(rl);
                            }

                            // save in ldap
                        }


                        // add object to ldap
                        ldapRole.add(sys,rowAttr);


					}catch(Exception e) {
						e.printStackTrace();

                        return;
					}

				}

			}

		}catch(Exception io) {
            io.printStackTrace();
            return;



		}
       return;
	}

	private void populateTemplate(String[] lineAry) {
		Map<String,Attribute> columnMap = new HashMap<String, Attribute>();

		int ctr =0;
		for (String s  :lineAry) {
			Attribute a = new Attribute(s, null);
			a.setType("STRING");
			a.setColumnNbr(ctr);
			columnMap.put(a.getName(),a);
			ctr++;
		}
		rowHeader.setColumnMap(columnMap);

        System.out.println("LineAry in template=" + lineAry);
	}



	private void populateRowObject(LineObject rowObj ,String[] lineAry) {
		DateFormat df =  new SimpleDateFormat("MM-dd-yyyy");
		Map<String, Attribute> attrMap =  rowObj.getColumnMap();
		Set<String> keySet = attrMap.keySet();
		Iterator<String> it  = keySet.iterator();

		while ( it.hasNext()) {
			String key  = it.next();
			Attribute attr =  rowObj.get(key);
			int colNbr = attr.getColumnNbr();
			String colValue = lineAry[colNbr];


			attr.setValue(colValue);
		}

	}


    public ManagedSystemDataService getManagedSysService() {
        return managedSysService;
    }

    public void setManagedSysService(ManagedSystemDataService managedSysService) {
        this.managedSysService = managedSysService;
    }

    public RoleDataWebService getRoleDataService() {
        return roleDataService;
    }

    public void setRoleDataService(RoleDataWebService roleDataService) {
        this.roleDataService = roleDataService;
    }

    public LdapRole getLdapRole() {
        return ldapRole;
    }

    public void setLdapRole(LdapRole ldapRole) {
        this.ldapRole = ldapRole;
    }
}
