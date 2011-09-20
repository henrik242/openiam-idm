package org.openiam.webadmin.sync.notuser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.base.ws.ResponseCode;
import org.openiam.base.ws.ResponseStatus;
import org.openiam.idm.srvc.audit.service.AuditHelper;
import org.openiam.idm.srvc.auth.login.LoginDataService;
import org.openiam.idm.srvc.mngsys.dto.ManagedSys;
import org.openiam.idm.srvc.mngsys.service.ManagedSystemDataService;
import org.openiam.idm.srvc.org.dto.OrgClassificationEnum;
import org.openiam.idm.srvc.org.dto.Organization;
import org.openiam.idm.srvc.org.service.OrganizationDataService;
import org.openiam.idm.srvc.role.service.RoleDataService;
import org.openiam.idm.srvc.synch.dto.Attribute;
import org.openiam.idm.srvc.synch.dto.LineObject;
import org.openiam.idm.srvc.synch.dto.SyncResponse;
import org.openiam.idm.srvc.synch.dto.SynchConfig;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVStrategy;
import org.openiam.idm.srvc.synch.service.TransformScript;
import org.openiam.idm.srvc.synch.service.ValidationScript;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.dto.UserStatusEnum;
import org.openiam.idm.srvc.user.service.UserDataService;
import org.openiam.provision.dto.ProvisionUser;
import org.openiam.webadmin.util.ldap.LdapOrganization;
import org.springframework.context.ApplicationContext;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.List;



/**
 * Created by IntelliJ IDEA.
 * User: suneetshah
 * Date: 8/31/11
 * Time: 3:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class SynchOrg {

    private static final Log log = LogFactory.getLog(SynchOrg.class);
    protected LineObject rowHeader = new LineObject();

protected ManagedSystemDataService managedSysService;
protected OrganizationDataService orgDataService;
protected LdapOrganization ldapOrganization;


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

                        String name = rowAttr.get("org name").getValue();
                        String description = rowAttr.get("business category").getValue();
                        if (name != null) {
                            List<Organization> orgList =  orgDataService.search(name, null, null, null);
                            if (orgList == null || orgList.isEmpty()) {
                                // create a new org
                                Organization org = new Organization();
                                org.setOrganizationName(name);
                                org.setDescription(description);
                                org.setClassification(OrgClassificationEnum.ORGANIZATION);
                                org.setStatus("ACTIVE");
                                orgDataService.addOrganization(org);
                            }

                            // save in ldap
                        }


                        // add object to ldap
                        ldapOrganization.add(sys,rowAttr);


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

    public OrganizationDataService getOrgDataService() {
        return orgDataService;
    }

    public void setOrgDataService(OrganizationDataService orgDataService) {
        this.orgDataService = orgDataService;
    }

    public LdapOrganization getLdapOrganization() {
        return ldapOrganization;
    }

    public void setLdapOrganization(LdapOrganization ldapOrganization) {
        this.ldapOrganization = ldapOrganization;
    }
}
