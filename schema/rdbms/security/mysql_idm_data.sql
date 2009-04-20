insert into SECURITY_DOMAIN (DOMAIN_ID, NAME, STATUS) values('IDM','IDM','ON-LINE');
insert into SECURITY_DOMAIN (DOMAIN_ID, NAME, STATUS) values('USR_SEC_DOMAIN','USER SECURITY DOMAIN','ON-LINE');

insert into SERVICE (SERVICE_ID, SERVICE_NAME, STATUS) values('USR_SEC_DOMAIN','USER SECURITY DOMAIN','ON-LINE');

insert into LANGUAGE (LANGUAGE_CD, LANGUAGE) VALUES ('en','English');
insert into LANGUAGE (LANGUAGE_CD, LANGUAGE) VALUES ('fr','French');
insert into LANGUAGE (LANGUAGE_CD, LANGUAGE) VALUES ('es','Spanish');
insert into LANGUAGE (LANGUAGE_CD, LANGUAGE) VALUES ('de','German');
insert into LANGUAGE (LANGUAGE_CD, LANGUAGE) VALUES ('it','Italian');
insert into LANGUAGE (LANGUAGE_CD, LANGUAGE) VALUES ('nl','Dutch');
insert into LANGUAGE (LANGUAGE_CD, LANGUAGE) VALUES ('pt','Portugese');

insert into METADATA_TYPE(TYPE_ID, DESCRIPTION) values('OrgOpenIAM','OpenIAM');

insert into METADATA_TYPE(TYPE_ID, DESCRIPTION,SYNC_MANAGED_SYS) values('InetOrgPerson','InetOrgPerson user type',1);
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,SELF_EDITABLE, SELF_VIEWABLE, UI_TYPE,UI_OBJECT_SIZE) values ('101','InetOrgPerson', 'Display Name',1,1,'TEXT','size=20');
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,SELF_EDITABLE, SELF_VIEWABLE, UI_TYPE,UI_OBJECT_SIZE) values ('104','InetOrgPerson', 'Preferred Language',1,1,'TEXT','size=20');
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,SELF_EDITABLE, SELF_VIEWABLE, UI_TYPE,UI_OBJECT_SIZE) values ('105','InetOrgPerson', 'VehicleLicense',1,1,'TEXT','size=20');
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,SELF_EDITABLE, SELF_VIEWABLE, UI_TYPE,UI_OBJECT_SIZE) values ('106','InetOrgPerson', 'Given Name',1,1,'TEXT','size=20');
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,SELF_EDITABLE, SELF_VIEWABLE, UI_TYPE,UI_OBJECT_SIZE) values ('107','InetOrgPerson', 'LabeledURI',1,1,'TEXT','size=20');
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,SELF_EDITABLE, SELF_VIEWABLE, UI_TYPE,UI_OBJECT_SIZE) values ('108','InetOrgPerson', 'Initials',1,1,'TEXT','size=20');
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,SELF_EDITABLE, SELF_VIEWABLE, UI_TYPE,UI_OBJECT_SIZE) values ('110','InetOrgPerson', 'BusinessCategory',1,1,'TEXT','size=20');

insert into METADATA_TYPE(TYPE_ID, DESCRIPTION,SYNC_MANAGED_SYS) values('Contractor','Contractor user type',1);
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,SELF_EDITABLE, SELF_VIEWABLE, UI_TYPE,UI_OBJECT_SIZE) values ('131','Contractor', 'Display Name',1,1,'TEXT','size=20');
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,SELF_EDITABLE, SELF_VIEWABLE, UI_TYPE,UI_OBJECT_SIZE) values ('134','Contractor', 'Preferred Language',1,1,'TEXT','size=20');
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,SELF_EDITABLE, SELF_VIEWABLE, UI_TYPE,UI_OBJECT_SIZE) values ('135','Contractor', 'VehicleLicense',1,1,'TEXT','size=20');
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,SELF_EDITABLE, SELF_VIEWABLE, UI_TYPE,UI_OBJECT_SIZE) values ('136','Contractor', 'Given Name',1,1,'TEXT','size=20');
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,SELF_EDITABLE, SELF_VIEWABLE, UI_TYPE,UI_OBJECT_SIZE) values ('137','Contractor', 'LabeledURI',1,1,'TEXT','size=20');
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,SELF_EDITABLE, SELF_VIEWABLE, UI_TYPE,UI_OBJECT_SIZE) values ('138','Contractor', 'Initials',1,1,'TEXT','size=20');
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,SELF_EDITABLE, SELF_VIEWABLE, UI_TYPE,UI_OBJECT_SIZE) values ('260','Contractor', 'BusinessCategory',1,1,'TEXT','size=20');
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,SELF_EDITABLE, SELF_VIEWABLE, UI_TYPE,UI_OBJECT_SIZE) values ('261','Contractor', 'StartDate',1,1,'TEXT','size=20');
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,SELF_EDITABLE, SELF_VIEWABLE, UI_TYPE,UI_OBJECT_SIZE) values ('262','Contractor', 'EndDate',1,1,'TEXT','size=20');


insert into METADATA_TYPE(TYPE_ID, DESCRIPTION) values('DIRECTORY','Directory');
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name) values ('140','DIRECTORY', 'Display Name');


insert into METADATA_TYPE(TYPE_ID, DESCRIPTION) values('FILE','File');
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name) values ('150','FILE', 'Display Name');


insert into METADATA_TYPE(TYPE_ID, DESCRIPTION) values('URL','URL');
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name) values ('160','URL', 'Display Name');


insert into METADATA_TYPE(TYPE_ID, DESCRIPTION) values('AD_Connector','Active Directory Connector');
insert into METADATA_TYPE(TYPE_ID, DESCRIPTION) values('LDAP_Connector','LDAP Connector');
insert into METADATA_TYPE(TYPE_ID, DESCRIPTION) values('RDBMS_Connector','RDBMS Connector');

insert into METADATA_TYPE(TYPE_ID, DESCRIPTION) values('SOAP_Connector','SOAP Connector');

UPDATE METADATA_TYPE 
SET ACTIVE = 1, SYNC_MANAGED_SYS = 1;

insert into COMPANY(company_id, company_name, status, type_ID) values('100','OpenIAM', 'ACTIVE', 'OrgOpenIAM');


insert into CATEGORY(CATEGORY_ID, PARENT_ID, CATEGORY_NAME, SHOW_LIST) VALUES('ROOT','', 'ROOT',0);
insert into CATEGORY(CATEGORY_ID, PARENT_ID, CATEGORY_NAME, SHOW_LIST) VALUES('ACL','', 'ACL Root',0);
insert into CATEGORY(CATEGORY_ID, PARENT_ID, CATEGORY_NAME, SHOW_LIST) VALUES('WebSite','', 'ACL',0);
insert into CATEGORY(CATEGORY_ID, PARENT_ID, CATEGORY_NAME, SHOW_LIST) VALUES('Application','', 'ACL',0);
insert into CATEGORY (category_id, parent_id, category_name, show_list) values ('USER_TYPE', 'ROOT', 'User Types',0);

insert into CATEGORY (category_id, parent_id, category_name, show_list) values ('CONNECTOR_TYPE', 'ROOT', 'Provisioning Connectors',0);


insert into CATEGORY_TYPE (category_id, type_ID) values('USER_TYPE','InetOrgPerson');
insert into CATEGORY_TYPE (category_id, type_ID) values('USER_TYPE','Contractor');

insert into CATEGORY_TYPE (category_id, type_ID) values('CONNECTOR_TYPE','AD_Connector');
insert into CATEGORY_TYPE (category_id, type_ID) values('CONNECTOR_TYPE','LDAP_Connector');
insert into CATEGORY_TYPE (category_id, type_ID) values('CONNECTOR_TYPE','RDBMS_Connector');
insert into CATEGORY_TYPE (category_id, type_ID) values('CONNECTOR_TYPE','SOAP_Connector');

insert into CATEGORY_TYPE (category_id, type_ID) values('ACL','DIRECTORY');
insert into CATEGORY_TYPE (category_id, type_id) values('ACL','FILE');
insert into CATEGORY_TYPE (category_id, type_id) values('ACL','URL');

update CATEGORY
SET DISPLAY_ORDER = 0, SHOW_LIST = 0
WHERE DISPLAY_ORDER IS NULL OR SHOW_LIST IS NULL;


INSERT INTO ROLE (ROLE_ID,SERVICE_ID,ROLE_NAME) VALUES ('SUPER_SEC_ADMIN','IDM','Super Security Admin');
INSERT INTO ROLE (ROLE_ID,SERVICE_ID,ROLE_NAME) VALUES ('SEC_ADMIN','IDM','Security Admin');
INSERT INTO ROLE (ROLE_ID,SERVICE_ID,ROLE_NAME) VALUES ('END_USER','USR_SEC_DOMAIN','End User');


insert into GRP (GRP_id, grp_name)   values('SUPER_SEC_ADMIN_GRP','Super Admin Group');
insert into GRP (grp_id, grp_name)   values('SEC_ADMIN_GRP','Sec Admin Group');
insert into GRP (grp_id, grp_name)   values('END_USER_GRP','End User Group');

insert into USERS (user_id,first_name, last_name, STATUS, COMPANY_ID ) values('3000','sys','','APPROVED','100');
insert into USERS (user_id,first_name, last_name, STATUS, COMPANY_ID ) values('3001','sys2','','APPROVED','100');
insert into USERS (user_id,first_name, last_name, STATUS, COMPANY_ID  ) values('3006','Scott','Nelson','APPROVED','100');


insert into USER_GRP (grp_id, user_id) 	values('SUPER_SEC_ADMIN_GRP','3000');
insert into USER_GRP (grp_id, user_id) 	values('SUPER_SEC_ADMIN_GRP','3001');
insert into USER_GRP (grp_id, user_id) 	values('END_USER_GRP','3006');


INSERT INTO GRP_ROLE(ROLE_ID,GRP_ID, SERVICE_ID) VALUES ('SUPER_SEC_ADMIN','SUPER_SEC_ADMIN_GRP', 'IDM');
INSERT INTO GRP_ROLE(ROLE_ID,GRP_ID, SERVICE_ID) VALUES ('END_USER','END_USER_GRP', 'USR_SEC_DOMAIN');

insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, DISPLAY_ORDER) values('ROOT', NULL ,'Root','Root', null, 'en',0);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, DISPLAY_ORDER) values('SECURITY','ROOT','Access Control','Access Control','security/index.jsp', 'en',1);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, DISPLAY_ORDER) values('IDMAN','ROOT','User Admin','User Admin','idman/index.jsp', 'en',2);

insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, active, display_order) values('SECURITY_POLICY','ROOT','Policy','Policy','security/policy.do?method=init&nav=reset', 'en',1,12);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, DISPLAY_ORDER) values('ADMIN','ROOT','Administration','Administration','admin/index.jsp', 'en',20);


insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, DISPLAY_ORDER) values('USER','IDMAN','User','User','menunav.do', 'en',1);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, DISPLAY_ORDER) values('ORG','IDMAN','Organization','Organization','menunav.do', 'en',2);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, DISPLAY_ORDER) values('APPRVUSER','IDMAN','Approve','Approve User','idman/approveUser.do?method=viewUserList', 'en',5);


insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, DISPLAY_ORDER) values('QUERYUSER','USER','Query','Query User','idman/userSearch.do?action=view', 'en',1);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, DISPLAY_ORDER) values('ADDUSER','USER','Add','Add User','idman/user.do?method=userForm', 'en',2);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, DISPLAY_ORDER) values('EDITUSER','USER','Edit','Edit User','idman/user.do?method=identities&mode=EDIT', 'en',3);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, DISPLAY_ORDER) values('DELUSER','USER','Del','Del User','idman/user.do?method=deleteUser&mode=DEL', 'en',4);

insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, DISPLAY_ORDER) values('QUERYORG','ORG','Query','Query Org','idman/orgSearch.do?action=view', 'en',2);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, DISPLAY_ORDER) values('ADDORG','ORG','Add','Add Org','idman/org.do?method=addOrg', 'en',3);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, DISPLAY_ORDER) values('EDITORG','ORG','Edit','Edit Org','idman/org.do?method=editOrg&mode=EDIT', 'en',4);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, DISPLAY_ORDER) values('DELORG','ORG','Del','Del Org','idman/org.do?method=delOrg&mode=DEL', 'en',5);

insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, active, display_order) values('SECURITY_ACL','SECURITY','Resources','Resources','security/resourceTree.do?method=init&categoryId=ACL&menuId=SECURITY_ACL', 'en',1,6);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, active, display_order) values('SECURITY_GROUP','SECURITY','Group','Group','security/group.do?method=init&nav=reset', 'en',1,4);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, active, display_order) values('SECURITY_MENU','SECURITY','Menu','Menu','security/menu.do?method=init&nav=reset', 'en',1,8);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, active, display_order) values('SECURITY_ROLE','SECURITY','Role','Role','security/role.do?method=viewRoles&nav=reset', 'en',1,2);


insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, active, display_order) values('SECURITY_RTYPE','SECURITY_ACL','Resource Type','Resource Type','security/resourcetype.do?method=init&menuId=SECURITY_ACL', 'en',1,4);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, active, display_order) values('SECURITY_TREE','SECURITY_ACL','Resource Tree','Resource Tree','security/resourceTree.do?method=init&categoryId=ACL&menuId=SECURITY_ACL', 'en',1,2);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, active, display_order) values('SECURITY_PRIVILEGE','SECURITY_ACL','Privileges','Privileges','security/privilege.do?method=init&menuId=SECURITY_ACL', 'en',1,6);



/* Reporting MENU options */
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, DISPLAY_ORDER) values('REPORT','ROOT','Report','Report','security/reportIndex.do', 'en',16);

insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD) values('USERREPORT','REPORT','User Reports','User Information Reports','', 'en');
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD) values('AUDITREPORT','REPORT','Audit Reports','Audit Information Reports','', 'en');
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD) values('PASSWORDREPORT','REPORT','Password Reports','Password Reports','', 'en');


insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD) values('PSWDCHANGE_RPT','PASSWORDREPORT','Password Change','Password Change Report','home.jsp?bodyjsp=ui/report/passwordRpt.html', 'en');
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD) values('AUDIT_RPT','AUDITREPORT','Audit Report','Audit Report','home.jsp?bodyjsp=ui/report/audit.jsp', 'en');
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD) values('ALLROLERPT','ACCESSREPORT','All Roles','All Roles Report','allRolesReport.report?method=allRole', 'en');

/* Admin MENU options */
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, active, display_order) values('SECURITY_AUDITLOG','ADMIN','Audit Log','Audit Log','home.jsp?bodyjsp=/log/searchlog.jsp', 'en',1,14);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, DISPLAY_ORDER) values('SECDOMAIN','ADMIN','Security Domain','Security Domain','home.jsp?bodyjsp=ui/domain/domain.html', 'en',1);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, DISPLAY_ORDER) values('METADATA','ADMIN','Metadata','Metadata','admin/index.jsp', 'en',2);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, DISPLAY_ORDER) values('PROVCONNECT','ADMIN','Connectors','Provisioning Connectors','connectorList.cnt', 'en',3);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, DISPLAY_ORDER) values('MNGSYS','ADMIN','Managed Systems','Managed Systems','managedSysList.cnt', 'en',4);



insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, display_order) values('ENT_APPS', 'ROOT' ,'Enterprise Applications','Enterprise Applications','', 'en',0);


/* Self Service MENU options */
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, display_order) values('SELFSERVICE', 'ROOT' ,'SELF SERVICE','SELF SERVICE','', 'en',0);

insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, display_order, PUBLIC_URL) values('ACCESSCENTER','SELFSERVICE', 'Access Management Center', 'Access Management Center', null, 'en', '1',0);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, display_order, PUBLIC_URL) values('NEWHIRE','ACCESSCENTER','New Hire', 'New Hire', 'newHire.selfserve','en', '1' ,0);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, display_order, PUBLIC_URL) values('MANAGEREQ', 'ACCESSCENTER' , 'Manage Requests','Manage Requests','requestList.selfserve', 'en','2',0);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, display_order, PUBLIC_URL) values('CREATEREQ','ACCESSCENTER','Create Request', 'Create Request', 'createRequest.selfserve', 'en', '3',0);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, display_order, PUBLIC_URL) values('CHNGACCESS','ACCESSCENTER','Change Access', 'Change Access', 'changeAccess.selfserve', 'en', '4',0);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, display_order, PUBLIC_URL) values('TERMINATEACCESS','ACCESSCENTER','Terminate', 'Terminate', 'terminateAccess.selfserv', 'en', '5',0);

insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, display_order, PUBLIC_URL) values('SELFCENTER','SELFSERVICE','Self Service Center', 'Self Service Center', null, 'en', '2',0);

insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, display_order, PUBLIC_URL) values('DIRECTORY','SELFCENTER','Directory Lookup', 'Directory Lookup', 'pub/directory.do?method=view', 'en', '1',1);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, display_order, PUBLIC_URL) values('CHNGPSWD','SELFCENTER', 'Change Password', 'Change Password', 'priv/password.do?method=view', 'en', '2',0);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, display_order, PUBLIC_URL) values('IDQUEST','SELFCENTER', 'Challenge Response', 'Challenge Response', 'priv/idquest.do?method=view', 'en', '3',0);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, display_order, PUBLIC_URL) values('FORGOTPSWD','SELFCENTER', 'Forgot Password', 'Forgot Password', 'pub/unLockUser.do?method=view', 'en', '4',1);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, display_order, PUBLIC_URL) values('PROFILE','SELFCENTER', 'Edit Your Profile', 'Edit Your Profile', 'priv/customProfile.do?method=view', 'en', '5',0);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, display_order, PUBLIC_URL) values('REPORTINC','SELFCENTER', 'Report Security Incident', 'Report Security Incident', 'pub/reportIncident.selfserve', 'en', '6',1);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, display_order, PUBLIC_URL) values('CONTADMIN','SELFCENTER', 'Contact Admin', 'Contact Admin', 'pub/contactAdmin.selfserve', 'en', '7',1);



/* service admin role */


INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID) VALUES('IDMAN','SUPER_SEC_ADMIN');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID) VALUES('SECURITY','SUPER_SEC_ADMIN');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID) VALUES('AUDITLOG','SUPER_SEC_ADMIN');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID) VALUES('REPORT','SUPER_SEC_ADMIN');



INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID) VALUES('APPRVUSER','SUPER_SEC_ADMIN');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID) VALUES('ORG','SUPER_SEC_ADMIN');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID) VALUES('USER','SUPER_SEC_ADMIN');

INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID) VALUES('QUERYUSER','SUPER_SEC_ADMIN');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID) VALUES('ADDUSER','SUPER_SEC_ADMIN');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID) VALUES('EDITUSER','SUPER_SEC_ADMIN');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID) VALUES('DELUSER','SUPER_SEC_ADMIN');

INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID) VALUES('QUERYORG','SUPER_SEC_ADMIN');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID) VALUES('ADDORG','SUPER_SEC_ADMIN');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID) VALUES('EDITORG','SUPER_SEC_ADMIN');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID) VALUES('DELORG','SUPER_SEC_ADMIN');


INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID) VALUES('SECURITY_ACL','SUPER_SEC_ADMIN');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID) VALUES('SECURITY_AUDITLOG','SUPER_SEC_ADMIN');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID) VALUES('SECURITY_GROUP','SUPER_SEC_ADMIN');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID) VALUES('SECURITY_MENU','SUPER_SEC_ADMIN');

INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID) VALUES('SECURITY_POLICY','SUPER_SEC_ADMIN');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID) VALUES('SECURITY_ROLE','SUPER_SEC_ADMIN');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID) VALUES('SECURITY_RTYPE','SUPER_SEC_ADMIN');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID) VALUES('SECURITY_TREE','SUPER_SEC_ADMIN');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID) VALUES('SECURITY_PRIVILEGE','SUPER_SEC_ADMIN');

INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID) VALUES('SELFSERVICE','END_USER');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID) VALUES('ACCESSCENTER','END_USER');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID) VALUES('NEWHIRE','END_USER');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID) VALUES('CHNGACCESS','END_USER');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID) VALUES('MANAGEREQ','END_USER');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID) VALUES('SELFCENTER','END_USER');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID) VALUES('DIRECTORY','END_USER');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID) VALUES('CHNGPSWD','END_USER');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID) VALUES('IDQUEST','END_USER');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID) VALUES('FORGOTPSWD','END_USER');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID) VALUES('PROFILE','END_USER');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID) VALUES('REPORTINC','END_USER');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID) VALUES('CONTADMIN','END_USER');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID) VALUES('CREATEREQ','END_USER');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID) VALUES('TERMINATEACCESS','END_USER');

INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID) VALUES('ADMIN','SUPER_SEC_ADMIN');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID) VALUES('SECDOMAIN','SUPER_SEC_ADMIN');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID) VALUES('METADATA','SUPER_SEC_ADMIN');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID) VALUES('PROVCONNECT','SUPER_SEC_ADMIN');

INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID) VALUES('MNGSYS','SUPER_SEC_ADMIN');




insert into LOGIN(SERVICE_ID, LOGIN, MANAGED_SYS_ID, USER_ID, PASSWORD,RESET_PWD, IS_LOCKED, AUTH_FAIL_COUNT) VALUES('IDM','sysadmin','IDM','3000','b83a81d1b3f5f209a70ec02c3d7f7fc5',0,0,0);
insert into LOGIN(SERVICE_ID, LOGIN, MANAGED_SYS_ID, USER_ID, PASSWORD,RESET_PWD, IS_LOCKED, AUTH_FAIL_COUNT) VALUES('IDM','sysadmin2','IDM','3001','b83a81d1b3f5f209a70ec02c3d7f7fc5',0,0,0);
insert into LOGIN(SERVICE_ID, LOGIN, MANAGED_SYS_ID, USER_ID, PASSWORD,RESET_PWD, IS_LOCKED, AUTH_FAIL_COUNT) VALUES('USR_SEC_DOMAIN','snelson','USR_SEC_DOMAIN','3006','b83a81d1b3f5f209a70ec02c3d7f7fc5',0,0,0);

update LOGIN set reset_pwd = 0, is_locked = 0;


INSERT INTO AUTH_STATE(USER_ID, TOKEN, AUTH_STATE, AA, EXPIRATION) values('3000', NULL,0,'OPENIAM',0);
INSERT INTO AUTH_STATE(USER_ID, TOKEN, AUTH_STATE, AA, EXPIRATION) values('3001', NULL,0,'OPENIAM',0);
INSERT INTO AUTH_STATE(USER_ID, TOKEN, AUTH_STATE, AA, EXPIRATION) values('3006', NULL,0,'OPENIAM',0);


/* Sequence Gen*/
insert into SEQUENCE_GEN (ATTRIBUTE, NEXT_ID) VALUES('USER_ID', '4000');
insert into SEQUENCE_GEN (ATTRIBUTE, NEXT_ID) VALUES('PHONE_ID', '2000');
insert into SEQUENCE_GEN (ATTRIBUTE, NEXT_ID) VALUES('ADDRESS_ID', '2000');
insert into SEQUENCE_GEN (ATTRIBUTE, NEXT_ID) VALUES('EMAIL_ID', '2000');
insert into SEQUENCE_GEN (ATTRIBUTE, NEXT_ID) VALUES('USER_ATTR_ID', '2000');
insert into SEQUENCE_GEN (ATTRIBUTE, NEXT_ID) VALUES('PWD_LOGIN_ID', '2000');
insert into SEQUENCE_GEN (attribute, next_id)	values('RELATION_SET_ID','1000');
insert into SEQUENCE_GEN (attribute, next_id)	values('RELATIONSHIP_ID','1000');

insert into SEQUENCE_GEN (attribute, next_id)	values('METADATA_ID','3000');
insert into SEQUENCE_GEN (attribute, next_id)	values('METADATA_VALUE_ID','2000');
insert into SEQUENCE_GEN (attribute, next_id)	values('METADATA_ELEMENT_ID','2000');

insert into SEQUENCE_GEN (attribute, next_id)	values('CATEGORY_ID','3000');

insert into SEQUENCE_GEN (attribute, next_id)	values('COMPANY_ID','2000');
insert into SEQUENCE_GEN (attribute, next_id)	values('COMPANY_ATTR_ID','2000');
insert into SEQUENCE_GEN (attribute, next_id)	values('ATTACHMENT_ID','2000');

insert into SEQUENCE_GEN (attribute, next_id)	values('IMAGE_ID','2000');
insert into SEQUENCE_GEN (attribute, next_id)	values('ROLE_ID','1003');
insert into SEQUENCE_GEN (attribute, next_id)	values('GRP_ID','4105');
insert into SEQUENCE_GEN (attribute, next_id)	values('GRP_ATTR_ID','1000');
insert into SEQUENCE_GEN (attribute, next_id)	values('MENU_ID','5000');
insert into SEQUENCE_GEN (attribute, next_id)	values('POLICY_ID','5000');
insert into SEQUENCE_GEN (attribute, next_id)	values('POLICY_ATTR_ID','6004');
insert into SEQUENCE_GEN (attribute, next_id)	values('POLICY_MEMBER_ID',6000);

INSERT INTO SEQUENCE_GEN (ATTRIBUTE,NEXT_ID) 	VALUES('TYPE_ID','1013');
insert into SEQUENCE_GEN (attribute, next_id)	values('PARENT_ID','1018');
insert into SEQUENCE_GEN (attribute, next_id)	values('ENTITLEMENT_ID','1003');
insert into SEQUENCE_GEN (attribute,next_id)  values('BOARD_PROPERTY_ID','2004');
insert into SEQUENCE_GEN (attribute,next_id)  values('RESOURCE_ID','2004');
insert into SEQUENCE_GEN (attribute, next_id)	values('ITEM_ID','1000');
insert into SEQUENCE_GEN (attribute, next_id) values('COMPONENT_ID','1000');
insert into SEQUENCE_GEN (attribute, next_id) values('ACCOUNT_ID','1000');
insert into SEQUENCE_GEN (attribute, next_id) values ('RESOURCE_TYPE_ID', '1001');
insert into SEQUENCE_GEN (attribute, next_id) values ('RESOURCE_PROP_ID', '1001');
insert into SEQUENCE_GEN (attribute, next_id) values ('PRIVILEGE_ID', '1001');
insert into SEQUENCE_GEN (attribute, next_id) values('SERVICE_ID','1000');

insert into SEQUENCE_GEN (attribute, next_id) values ('ACCESS_LOG_ID', '1001');
insert into SEQUENCE_GEN (attribute, next_id) values ('NOTE_ID', '1001');

insert into SEQUENCE_GEN (attribute, next_id) values ('QUESTION_ID', '1001');
insert into SEQUENCE_GEN (attribute, next_id) values ('ANSWER_ID', '1001');
insert into SEQUENCE_GEN (attribute, next_id) values ('GRP_ROLE_ID','200');

insert into SEQUENCE_GEN (attribute, next_id) values ('ORG_STRUCTURE_ID','200');
insert into SEQUENCE_GEN (attribute, next_id) values ('LOGIN_ATTR_ID','200');
insert into SEQUENCE_GEN (attribute, next_id) values ('USER_ROLE_ID','200');
insert into SEQUENCE_GEN (attribute, next_id) values ('USER_GRP_ID','200');
insert into SEQUENCE_GEN (attribute, next_id) values ('CONNECTOR_ID','100');
insert into SEQUENCE_GEN (attribute, next_id) values ('MANAGED_SYS_ID','100');

insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'USER', 'PENDING','en','String','PENDING','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'USER', 'APPROVED','en','String','APPROVED','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'USER', 'ACTIVE','en','String','ACTIVE','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'USER', 'LOCKED','en','String','LOCKED','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'USER', 'DELETED','en','String','DELETED','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'USER', 'INACTIVE','en','String','INACTIVE','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'USER', 'BLACK LISTED','en','String','BLACKLISTED','100', 'IDM');


insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'TOKEN', 'PENDING','en','String','PENDING','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'TOKEN', 'UN-ASSIGNED','en','String','UN-ASSIGNED','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'TOKEN', 'ASSIGNED-UNLOCKED','en','String','ASSIGNED-UNLOCKED','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'TOKEN', 'ASSIGNED-LOCKED','en','String','ASSIGNED-LOCKED','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'TOKEN', 'ACTIVE','en','String','ACTIVE','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'TOKEN', 'LOST','en','String','LOST','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'TOKEN', 'DISABLED','en','String','DISABLED','100', 'IDM');



insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'OPERATION', 'DL','en','String','DELETE','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'OPERATION', 'RJ','en','String','REJECT','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'OPERATION', 'BL','en','String','BLACK LIST','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'OPERATION', 'ST','en','String','STOP TOKEN','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'OPERATION', 'FT','en','String','FORGOT TOKEN','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'OPERATION', 'UB','en','String','UN-BLACK LIST','100', 'IDM');


insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'SERVICE_STATUS', 'READY','en','String','READY','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'SERVICE_STATUS', 'OFF-LINE','en','String','OFF-LINE','100', 'IDM');



insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'SERVICE_TYPE', 'AD','en','String','ACTIVE DIRECTORY','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'SERVICE_TYPE', 'LDAP','en','String','LDAP','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'SERVICE_TYPE', 'SQLSERVER','en','String','MS SQL Server','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'SERVICE_TYPE', 'ORACLE DB','en','String','ORACLE DB','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'SERVICE_TYPE', 'IIS','en','String','MS IIS Agent','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'SERVICE_TYPE', 'APACHE','en','String','APACHE AGENT','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'SERVICE_TYPE', 'JBOSS4','en','String','JBOSS 4.x AGENT','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'SERVICE_TYPE', 'WEBLOGIC','en','String','BEA WEBLOGIC AGENT','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'SERVICE_TYPE', 'WEBSPHERE','en','String','IBM WEBPSHERE AGENT','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'SERVICE_TYPE', 'CUSTOMAPP','en','String','CUSTOM APPLICATION','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'SERVICE_TYPE', 'LIFERAY','en','String','LIFE RAY PORTAL','100', 'IDM');


/* Country codes */

insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'AFG','en','String','Afghanistan','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'ALB','en','String','Albania','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'DZA','en','String','Algeria','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'ALL','en','String','All Countries','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'ASM','en','String','American Samoa','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'AND','en','String','Andorra','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'AGO','en','String','Angola','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'ATA','en','String','Antarctica','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'ARG','en','String','Argentina','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'ARM','en','String','Armenia','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'ABW','en','String','Aruba','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', '1','en','String','Ascension Island','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'AUS','en','String','Australia','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'AUT','en','String','Austria','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'AZE','en','String','Azerbaijan','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'BHR','en','String','Bahrain','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'BGD','en','String','Bangladesh','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'BLR','en','String','Belarus','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'BEL','en','String','Belgium','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'BLZ','en','String','Belize','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'BEN','en','String','Benin','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'BTN','en','String','Bhutan','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'BOL','en','String','Bolivia','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'BWA','en','String','Botswana','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'BRA','en','String','Brazil','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', '2','en','String','Brunei','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'BGR','en','String','Bulgaria','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'BFA','en','String','Burkina Faso','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'BDI','en','String','Burundi','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'KHM','en','String','Cambodia','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'CMR','en','String','Cameroon','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'CAN','en','String','Canada','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', '3','en','String','Cape Verde Islands','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'CAF','en','String','Central African Republic','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'TCD','en','String','Chad','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'NZL','en','String','Chatham Island (New Zealand)','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'CHL','en','String','Chile','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'CHN','en','String','China (PRC)','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'CCK','en','String','Cocos-Keeling Islands','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'COL','en','String','Colombia','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'COM','en','String','Comoros','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'COG','en','String','Congo','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'COD','en','String','Congo, Dem. Rep. of (former Zaire)','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'COK','en','String','Cook Islands','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'CRI','en','String','Costa Rica','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'HRV','en','String','Croatia','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'CUB','en','String','Cuba','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', '4','en','String','Curaao','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'CYP','en','String','Cyprus','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'CZE','en','String','Czech Republic','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'DNK','en','String','Denmark','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', '5','en','String','Diego Garcia','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'DJI','en','String','Djibouti','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'TMP','en','String','East Timor','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', '6','en','String','Easter Island','100', 'IDM') ;
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'ECU','en','String','Ecuador','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'EGY','en','String','Egypt','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'SLV','en','String','El Salvador','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'GNQ','en','String','Equatorial Guinea','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'ERI','en','String','Eritrea','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'EST','en','String','Estonia','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'ETH','en','String','Ethiopia','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'FRO','en','String','Faeroe Islands','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'FLK','en','String','Falkland Islands','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'FJI','en','String','Fiji Islands','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'FIN','en','String','Finland','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'FRA','en','String','France','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', '7','en','String','French Antilles','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'GUF','en','String','French Guiana','100', 'IDM') ;
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'PYF','en','String','French Polynesia','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'GAB','en','String','Gabon','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'GMB','en','String','Gambia','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'GEO','en','String','Georgia','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'DEU','en','String','Germany','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'GHA','en','String','Ghana','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'GIB','en','String','Gibraltar','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'GRC','en','String','Greece','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'GRL','en','String','Greenland','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'GLP','en','String','Guadeloupe','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'GTM','en','String','Guatemala','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'GIN','en','String','Guinea (PRP)','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'GNB','en','String','Guinea-Bissau','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'GUY','en','String','Guyana','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'HTI','en','String','Haiti','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'HND','en','String','Honduras','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'HKG','en','String','Hong Kong','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'HUN','en','String','Hungary','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'ISL','en','String','Iceland','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'IND','en','String','India','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'IRN','en','String','Iran','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'IRQ','en','String','Iraq','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'IRL','en','String','Ireland','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'ISR','en','String','Israel','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'ITA','en','String','Italy','100', 'IDM') ;
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'CIV','en','String','Ivory Coast (Cte dIvoire)','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'JPN','en','String','Japan','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'JOR','en','String','Jordan','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'KAZ','en','String','Kazakhstan','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'KEN','en','String','Kenya','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'KIR','en','String','Kiribati','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'PRK','en','String','Korea (North)','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'KOR','en','String','Korea (South)','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'KWT','en','String','Kuwait','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'KGZ','en','String','Kyrgyz Republic','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'LAO','en','String','Laos','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'LVA','en','String','Latvia','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'LBN','en','String','Lebanon','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'LSO','en','String','Lesotho','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'LBR','en','String','Liberia','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'LYB','en','String','Libya','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'LIE','en','String','Liechtenstein','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'LTU','en','String','Lithuania','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'LUX','en','String','Luxembourg','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'MAC','en','String','Macau','100', 'IDM');
	
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'MDG','en','String','Madagascar','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'MWI','en','String','Malawi','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'MYS','en','String','Malaysia','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'MDV','en','String','Maldives','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'MLI','en','String','Mali Republic','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'MLT','en','String','Malta','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'MHL','en','String','Marshall Islands','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'MTQ','en','String','Martinique','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'MRT','en','String','Mauritania','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'MUS','en','String','Mauritius','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'MYT','en','String','Mayotte Island','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'MEX','en','String','Mexico','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'FSM','en','String','Micronesia','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'MDA','en','String','Moldova','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'MCO','en','String','Monaco','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'MNG','en','String','Mongolia','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'MAR','en','String','Morocco','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'MOZ','en','String','Mozambique','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'MMR','en','String','Myanmar','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'NAM','en','String','Namibia','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'NRU','en','String','Nauru','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'NPL','en','String','Nepal','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'NLD','en','String','Netherlands','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'ANT','en','String','Netherlands Antilles','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'NCL','en','String','New Caledonia','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'NIC','en','String','Nicaragua','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'NER','en','String','Niger','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'NGA','en','String','Nigeria','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'NIU','en','String','Niue','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'NFK','en','String','Norfolk Island','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'PAK','en','String','PAKISTAN','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'PLW','en','String','Palau','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', '8','en','String','Palestine','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'PAN','en','String','Panama','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'PNG','en','String','Papua New Guinea','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'PRY','en','String','Paraguay','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'PER','en','String','Peru','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'PHL','en','String','Philippines','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'POL','en','String','Poland','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'PRT','en','String','Portugal','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'QAT','en','String','Qatar','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'REU','en','String','Runion Island','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'ROM','en','String','Romania','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'RUS','en','String','Russia','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'RWA','en','String','Rwanda','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'SMR','en','String','San Marino','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'STP','en','String','So Tom and Principe','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'SAU','en','String','Saudi Arabia','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'SEN','en','String','Senegal','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', '9','en','String','Serbia','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'SYC','en','String','Seychelles Islands','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'SLE','en','String','Sierra Leone','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'SGP','en','String','Singapore','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'SVK','en','String','Slovak Republic','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'SVN','en','String','Slovenia','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'SLB','en','String','Solomon Islands','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'SOM','en','String','Somalia','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'ZAF','en','String','South Africa','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'ESP','en','String','Spain','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'LKA','en','String','Sri Lanka','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'SHN','en','String','St. Helena','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'SDN','en','String','Sudan','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'SUR','en','String','Suriname','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'SWZ','en','String','Swaziland','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'SWE','en','String','Sweden','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'CHE','en','String','Switzerland','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'SYR','en','String','Syria','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'TWN','en','String','Taiwan','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'TJK','en','String','Tajikistan','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'TZA','en','String','Tanzania','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'THA','en','String','Thailand','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'TGO','en','String','Togo','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'TKL','en','String','Tokelau','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'TON','en','String','Tonga Islands','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'TUN','en','String','Tunisia','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'TUR','en','String','Turkey','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'TKM','en','String','Turkmenistan','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'TUV','en','String','Tuvalu','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'UGA','en','String','Uganda','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'UKR','en','String','Ukraine','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'ARE','en','String','United Arab Emirates','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'GBR','en','String','United Kingdom','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'USA','en','String','United States of America','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'URY','en','String','Uruguay','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'UZB','en','String','Uzbekistan','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'VUT','en','String','Vanuatu','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', '10','en','String','Vatican City','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'VEN','en','String','Venezuela','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'VNM','en','String','Vietnam','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', '11','en','String','Wake Island','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'WLF','en','String','Wallis and Futuna Islands','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', '12','en','String','Western Samoa','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'YEM','en','String','Yemen','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'YUG','en','String','Yugoslavia','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'ZMB','en','String','Zambia','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', '13','en','String','Zanzibar','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'ZWE','en','String','Zimbabwe','100', 'IDM');

insert into STATUS (CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values('OBJECT_TYPE', 'ACCNT','en','String','Account','100', 'IDM');
insert into STATUS (CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values('OBJECT_TYPE', 'ADMIN','en','String','Administrator','100', 'IDM');
insert into STATUS (CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values('OBJECT_TYPE', 'ADMGP','en','String','Admin Group','100', 'IDM');
insert into STATUS (CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values('OBJECT_TYPE', 'ATTR','en','String','Attribute','100', 'IDM');

/* PRIVLEGE       */
INSERT INTO PRIVILEGE(PRIVILEGE_ID, DESCRIPTION) VALUES('CREATE','Create');
INSERT INTO PRIVILEGE(PRIVILEGE_ID, DESCRIPTION) VALUES('READ','Read');
INSERT INTO PRIVILEGE(PRIVILEGE_ID, DESCRIPTION) VALUES('EXECUTE','Execute');
INSERT INTO PRIVILEGE(PRIVILEGE_ID, DESCRIPTION) VALUES('UPDATE','Update');
INSERT INTO PRIVILEGE(PRIVILEGE_ID, DESCRIPTION) VALUES('DELETE','Delete');



/* POLICY ENTRIES */

insert into POLICY_DEF(POLICY_DEF_ID, NAME, DESCRIPTION, POLICY_TYPE, LOCATION_TYPE)
VALUES ('100','PASSWORD POLICY','Out of the box Password Policy', '2','DB' );

INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION, OPERATION, POLICY_PARAM_HANDLER) VALUES('110','100','PWD_HIST_VER','Password history versions', null,'diamelle.security.pwd.policy.HistoryRule');

INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION, OPERATION, POLICY_PARAM_HANDLER) VALUES('111','100','PWD_EXPIRATION','Password expiration', null, 'diamelle.security.pwd.policy.ExpirationRule');

INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION,OPERATION, POLICY_PARAM_HANDLER)  VALUES('112','100','PWD_LEN','Password length','RANGE', 'diamelle.security.pwd.policy.PasswordLenghtRule');

INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION, OPERATION) VALUES('113','100','NUMERIC_CHARS','Numeric characters(Min-Max)','RANGE');

INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION, OPERATION) VALUES('114','100','UPPERCASE_CHARS','Uppercase characters(Min-Max)','RANGE');

INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION, OPERATION) VALUES('115','100','LOWERCASE_CHARS','Lowercase characters(Min-Max)','RANGE');

INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION, OPERATION) VALUES('116','100','NON_ALPHA_CHARS','Non-alpha numeric characters(Min-Max)','RANGE');

INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION, OPERATION) VALUES('117','100','ALPHA_CHARS','Alpha numeric symbols(Min-Max','RANGE');

INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION, OPERATION) VALUES('119','100','CONSECUTIVE_CHARS','Number of consecutive characters allowed', null);

INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION, OPERATION) VALUES('120','100','DICTIONARY_CHECK','Dictionary Check','boolean');

INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION, OPERATION) VALUES('121','100','PWD_LOGIN','Reject password = Login Id','boolean');

INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION, OPERATION) VALUES('122','100','PWD_NAME','Reject password = First / Last name','boolean');

INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION, OPERATION) VALUES('123','100','VOWELS_IN_PWD','Reject Password containing vowels ','boolean');

INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION, OPERATION) VALUES('124','100','REJECT_NUM_START','Reject passwords that begin or end with a numeric character','boolean');

INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION, OPERATION) VALUES('125','100','HAS_NUMERIC_AT','Password to contain numeric chars at following positions', null);

INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION, OPERATION) VALUES('126','100','HAS_ALPHA_NUM_AT','Password to contain alpha numeric chars at following positions', null);

INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION, OPERATION) VALUES('128','100','FORCE_NON_ALPHA_NUM_AT','Force password to contain non-alpha numeric chars at following positions', null);

INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION, OPERATION) VALUES('129','100','PWD_EXP_WARN','Days to password expiration warning', null);

INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION, OPERATION) VALUES('130','100','QUEST_COUNT','Number of questions to display', null);

INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION, OPERATION) VALUES('131','100','QUEST_SRC','Source of questions', null);

INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION, OPERATION) VALUES('132','100','QUEST_LIST','Question list', null);

update POLICY_DEF_PARAM set POLICY_PARAM_HANDLER = 'diamelle.security.pwd.policy.PasswordLenghtRule' where DEF_PARAM_ID = '112';
update POLICY_DEF_PARAM set POLICY_PARAM_HANDLER = 'diamelle.security.pwd.policy.ExpirationRule' where DEF_PARAM_ID = '111';
update POLICY_DEF_PARAM set POLICY_PARAM_HANDLER = 'diamelle.security.pwd.policy.HistoryRule' where DEF_PARAM_ID = '110';

/* Access Policy */
insert into POLICY_DEF(POLICY_DEF_ID, NAME, DESCRIPTION, POLICY_TYPE, LOCATION_TYPE) VALUES ('101','ACCESS POLICY','Out of the box Access Policy', '1','DB' );
INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME,OPERATION) VALUES('100','101','SERVICE_ID','IN');

/* Audit Policy */
insert into POLICY_DEF(POLICY_DEF_ID, NAME, DESCRIPTION, POLICY_TYPE, LOCATION_TYPE)  VALUES ('102','AUDIT POLICY','Out of the box Audit Policy', '3','DB' );
INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION) VALUES('104','102','LOGON_EVENT','Failed Authentication Attempts');
INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION) VALUES('106','102','OBJ_ACCESS_MANAGEMENT','Object Access');
INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION) VALUES('107','102','PWD_CHANGE','Password Change');
INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION) VALUES('108','102','POLICY_CHANGE','Policy Change');
INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION) VALUES('199','102','ACCOUNT_MANAGEMENT','Policy Change');



/* Account Policy */
insert into POLICY_DEF(POLICY_DEF_ID, NAME, DESCRIPTION, POLICY_TYPE, LOCATION_TYPE) 
VALUES ('103','ACCOUNT POLICY','Out of the box Account Policy', '4','DB' );
INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION)
VALUES('102','103','FAILED_AUTH_COUNT','Failed Authentication Attempts');
INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION)
VALUES('103','103','LOCK_DURATION','Lock Duration');

insert into POLICY_DEF(POLICY_DEF_ID, NAME, DESCRIPTION, POLICY_TYPE, LOCATION_TYPE) VALUES ('104','ATTRIBUTE POLICY','Attribute value policies.', '5','DB' );


update POLICY_DEF_PARAM
	set repeats = 0
where repeats is null;

insert into POLICY (POLICY_ID, POLICY_DEF_ID, NAME, DESCRIPTION, STATUS, CREATE_DATE, CREATED_BY)
VALUES ('4000','100', 'Default Pswd Policy', 'Default Password Policy', 1,curdate(), '3000');

insert into POLICY (POLICY_ID, POLICY_DEF_ID, NAME, DESCRIPTION, STATUS, CREATE_DATE, CREATED_BY)
VALUES ('4001','103', 'Default Account Policy', 'Default Account Policy', 1,curdate(), '3000');

insert into POLICY (POLICY_ID, POLICY_DEF_ID, NAME, DESCRIPTION, STATUS, CREATE_DATE, CREATED_BY)
VALUES ('4002','103', 'Audit Policy', 'Audit Policy', 1,curdate(), '3000');

insert into POLICY_ATTRIBUTE (POLICY_ATTR_ID, DEF_PARAM_ID, POLICY_ID, NAME, OPERATION,VALUE1)VALUES ('4000','116', '4000', 'NON_ALPHA_CHARS', 'RANGE',1);
insert into POLICY_ATTRIBUTE (POLICY_ATTR_ID, DEF_PARAM_ID, POLICY_ID, NAME, OPERATION,VALUE1)VALUES ('4001','131', '4000', 'QUEST_SRC', '','USER');
insert into POLICY_ATTRIBUTE (POLICY_ATTR_ID, DEF_PARAM_ID, POLICY_ID, NAME, OPERATION,VALUE1)VALUES ('4002','100', '4000', 'SERVICE_ID', 'IN','');
insert into POLICY_ATTRIBUTE (POLICY_ATTR_ID, DEF_PARAM_ID, POLICY_ID, NAME, OPERATION,VALUE1)VALUES ('4003','123', '4000', 'VOWELS_IN_PWD', '','boolean');
insert into POLICY_ATTRIBUTE (POLICY_ATTR_ID, DEF_PARAM_ID, POLICY_ID, NAME, OPERATION,VALUE1)VALUES ('4004','117', '4000', 'ALPHA_CHARS', 'RANGE','');
insert into POLICY_ATTRIBUTE (POLICY_ATTR_ID, DEF_PARAM_ID, POLICY_ID, NAME, OPERATION,VALUE1)VALUES ('4005','103', '4001', 'LOCK_DURATION', '','');
insert into POLICY_ATTRIBUTE (POLICY_ATTR_ID, DEF_PARAM_ID, POLICY_ID, NAME, OPERATION,VALUE1)VALUES ('4006','102', '4001', 'FAILED_AUTH_COUNT', '','3');
insert into POLICY_ATTRIBUTE (POLICY_ATTR_ID, DEF_PARAM_ID, POLICY_ID, NAME, OPERATION, VALUE1) VALUES ('4007', '114', '4000', 'UPPERCASE_CHARS', 'RANGE', null);
insert into POLICY_ATTRIBUTE (POLICY_ATTR_ID, DEF_PARAM_ID, POLICY_ID, NAME, OPERATION, VALUE1) VALUES ('4008', '113', '4000', 'NUMERIC_CHARS', 'RANGE', 1);
insert into POLICY_ATTRIBUTE (POLICY_ATTR_ID, DEF_PARAM_ID, POLICY_ID, NAME, OPERATION, VALUE1) VALUES ('4010', '122', '4000', 'PWD_NAME', 'boolean', null);
insert into POLICY_ATTRIBUTE (POLICY_ATTR_ID, DEF_PARAM_ID, POLICY_ID, NAME, OPERATION, VALUE1,VALUE2) VALUES ('4011', '112', '4000', 'PWD_LEN', 'RANGE', 8,12);
insert into POLICY_ATTRIBUTE (POLICY_ATTR_ID, DEF_PARAM_ID, POLICY_ID, NAME, OPERATION, VALUE1) VALUES ('4012', '120', '4000', 'DICTIONARY_CHECK', 'boolean', null);
insert into POLICY_ATTRIBUTE (POLICY_ATTR_ID, DEF_PARAM_ID, POLICY_ID, NAME, OPERATION, VALUE1) VALUES ('4013', '110', '4000', 'PWD_HIST_VER', null, 6);
insert into POLICY_ATTRIBUTE (POLICY_ATTR_ID, DEF_PARAM_ID, POLICY_ID, NAME, OPERATION, VALUE1) VALUES ('4014', '126', '4000', 'HAS_ALPHA_NUM_AT', null, 1);
insert into POLICY_ATTRIBUTE (POLICY_ATTR_ID, DEF_PARAM_ID, POLICY_ID, NAME, OPERATION, VALUE1) VALUES ('4015', '132', '4000', 'QUEST_LIST', null, null);
insert into POLICY_ATTRIBUTE (POLICY_ATTR_ID, DEF_PARAM_ID, POLICY_ID, NAME, OPERATION, VALUE1) VALUES ('4016', '130', '4000', 'QUEST_COUNT', null, 3);
insert into POLICY_ATTRIBUTE (POLICY_ATTR_ID, DEF_PARAM_ID, POLICY_ID, NAME, OPERATION, VALUE1) VALUES ('4024', '128', '4000', 'FORCE_NON_ALPHA_NUM_AT', null, null);
insert into POLICY_ATTRIBUTE (POLICY_ATTR_ID, DEF_PARAM_ID, POLICY_ID, NAME, OPERATION, VALUE1) VALUES ('4017', '121', '4000', 'PWD_LOGIN', 'boolean', null);
insert into POLICY_ATTRIBUTE (POLICY_ATTR_ID, DEF_PARAM_ID, POLICY_ID, NAME, OPERATION, VALUE1) VALUES ('4018', '125', '4000', 'HAS_NUMERIC_AT', null, null);
insert into POLICY_ATTRIBUTE (POLICY_ATTR_ID, DEF_PARAM_ID, POLICY_ID, NAME, OPERATION, VALUE1) VALUES ('4019', '115', '4000', 'LOWERCASE_CHARS', 'RANGE', null);
insert into POLICY_ATTRIBUTE (POLICY_ATTR_ID, DEF_PARAM_ID, POLICY_ID, NAME, OPERATION, VALUE1) VALUES ('4020', '129', '4000', 'PWD_EXP_WARN', null, 7);
insert into POLICY_ATTRIBUTE (POLICY_ATTR_ID, DEF_PARAM_ID, POLICY_ID, NAME, OPERATION, VALUE1) VALUES ('4021', '124', '4000', 'REJECT_NUM_START', 'boolean', null);
insert into POLICY_ATTRIBUTE (POLICY_ATTR_ID, DEF_PARAM_ID, POLICY_ID, NAME, OPERATION, VALUE1) VALUES ('4022', '119', '4000', 'CONSECUTIVE_CHARS', null, null);
insert into POLICY_ATTRIBUTE (POLICY_ATTR_ID, DEF_PARAM_ID, POLICY_ID, NAME, OPERATION, VALUE1) VALUES ('4023', '111', '4000', 'PWD_EXPIRATION', null, 90);



/* USED AS LOGICAL GROUPING FOR RESOURCES */
INSERT INTO CATEGORY(CATEGORY_ID,CATEGORY_NAME) VALUES('SECURITY','SECURITY');

insert into IDENTITY_QUEST_GRP(IDENTITY_QUEST_GRP_ID, NAME, STATUS, COMPANY_OWNER_ID, CREATE_DATE) VALUES ('GLOBAL','GLOBAL IDENTITY QUESTIONS', 'ACTIVE', 'GLOBAL', curdate());

INSERT INTO IDENTITY_QUESTION(IDENTITY_QUESTION_ID, IDENTITY_QUEST_GRP_ID, QUESTION_TEXT, REQUIRED) VALUES('200','GLOBAL','What are the last four digits of your social security number?',1);

INSERT INTO IDENTITY_QUESTION(IDENTITY_QUESTION_ID, IDENTITY_QUEST_GRP_ID, QUESTION_TEXT, REQUIRED) VALUES('209','GLOBAL','What are the last four digits of your drivers license?',1);

INSERT INTO IDENTITY_QUESTION(IDENTITY_QUESTION_ID, IDENTITY_QUEST_GRP_ID, QUESTION_TEXT, REQUIRED) VALUES('201','GLOBAL','What is your mothers maiden name?',1);


INSERT INTO IDENTITY_QUESTION(IDENTITY_QUESTION_ID, IDENTITY_QUEST_GRP_ID, QUESTION_TEXT, REQUIRED) VALUES('202','GLOBAL','Where did you go to school?',1);

INSERT INTO IDENTITY_QUESTION(IDENTITY_QUESTION_ID, IDENTITY_QUEST_GRP_ID, QUESTION_TEXT, REQUIRED) VALUES('203','GLOBAL','What is your pets name?',0);

INSERT INTO IDENTITY_QUESTION(IDENTITY_QUESTION_ID, IDENTITY_QUEST_GRP_ID, QUESTION_TEXT, REQUIRED) VALUES('204','GLOBAL','What is your favorite food?',0);

INSERT INTO IDENTITY_QUESTION(IDENTITY_QUESTION_ID, IDENTITY_QUEST_GRP_ID, QUESTION_TEXT, REQUIRED) VALUES('205','GLOBAL','What is your favorite color?',0);

INSERT INTO IDENTITY_QUESTION(IDENTITY_QUESTION_ID, IDENTITY_QUEST_GRP_ID, QUESTION_TEXT, REQUIRED) VALUES('206','GLOBAL','Which city were you born in?',0);

INSERT INTO IDENTITY_QUESTION(IDENTITY_QUESTION_ID, IDENTITY_QUEST_GRP_ID, QUESTION_TEXT, REQUIRED) VALUES('207','GLOBAL','What is your favorite sport?',0);

INSERT INTO IDENTITY_QUESTION(IDENTITY_QUESTION_ID, IDENTITY_QUEST_GRP_ID, QUESTION_TEXT, REQUIRED) VALUES('210','GLOBAL','What is the name of your favorite school?',0);
INSERT INTO IDENTITY_QUESTION(IDENTITY_QUESTION_ID, IDENTITY_QUEST_GRP_ID, QUESTION_TEXT, REQUIRED) VALUES('211','GLOBAL','What is the name of your first pet',0);
INSERT INTO IDENTITY_QUESTION(IDENTITY_QUESTION_ID, IDENTITY_QUEST_GRP_ID, QUESTION_TEXT, REQUIRED) VALUES('212','GLOBAL','What is the name of your favorite movie?',0);
INSERT INTO IDENTITY_QUESTION(IDENTITY_QUESTION_ID, IDENTITY_QUEST_GRP_ID, QUESTION_TEXT, REQUIRED) VALUES('213','GLOBAL','What is the title of your favorite book?',0);



insert into PROVISION_CONNECTOR(CONNECTOR_ID, NAME, METADATA_TYPE_ID, SERVICE_URL) VALUES ('50','AD CONNECTOR', 'AD_Connector','HTTP://localhost:8080/idm-ad-con-ws/' );
insert into PROVISION_CONNECTOR(CONNECTOR_ID, NAME, METADATA_TYPE_ID, SERVICE_URL) VALUES ('51','LDAP CONNECTOR', 'LDAP_Connector','HTTP://localhost:8080/idm-ldap-con-ws/' );

INSERT INTO MANAGED_SYS (MANAGED_SYS_ID, NAME, DESCRIPTION, STATUS, CONNECTOR_ID, DOMAIN_ID, HOST_URL, PORT, COMM_PROTOCOL, USER_ID, PSWD) VALUES('100','OPENIAM_LDAP', 'Primary Directory', 'ACTIVE', '51', 'USR_SEC_DOMAIN', 'ldap://96.56.80.245','1389', 'ss,','cn=Directory Manager','scorpio18'   );

insert into MNG_SYS_OBJECT_MATCH(OBJECT_SEARCH_ID, MANAGED_SYS_ID, OBJECT_TYPE, MATCH_METHOD, SEARCH_FILTER, BASE_DN,KEY_FIELD) 
VALUES('100', '100', 'USER', 'BASE_DN', '(&(objectClass=user)(uid=?))','ou=people,dc=openiam,dc=org\\','uid');





commit;