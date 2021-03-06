USE openiam;

insert into SECURITY_DOMAIN (DOMAIN_ID, NAME, STATUS, LOGIN_MODULE, AUTH_SYS_ID) values('IDM','IDM','ON-LINE', null,'0');
insert into SECURITY_DOMAIN (DOMAIN_ID, NAME, STATUS, LOGIN_MODULE, AUTH_SYS_ID) values('USR_SEC_DOMAIN','DEFAULT DOMAIN','ON-LINE', null,'0');

update SECURITY_DOMAIN
 SET AUTHENTICATION_POLICY='4001',
     PASSWORD_POLICY='4000',
	 AUDIT_POLICY='4002';

insert into SERVICE (SERVICE_ID, SERVICE_NAME, STATUS) values('USR_SEC_DOMAIN','USER SECURITY DOMAIN','ON-LINE');

insert into LANGUAGE (LANGUAGE_CD, LANGUAGE) VALUES ('en','English');
insert into LANGUAGE (LANGUAGE_CD, LANGUAGE) VALUES ('fr','French');
insert into LANGUAGE (LANGUAGE_CD, LANGUAGE) VALUES ('es','Spanish');
insert into LANGUAGE (LANGUAGE_CD, LANGUAGE) VALUES ('de','German');
insert into LANGUAGE (LANGUAGE_CD, LANGUAGE) VALUES ('it','Italian');
insert into LANGUAGE (LANGUAGE_CD, LANGUAGE) VALUES ('nl','Dutch');
insert into LANGUAGE (LANGUAGE_CD, LANGUAGE) VALUES ('pt','Portugese');

insert into METADATA_TYPE(TYPE_ID, DESCRIPTION) values('OrgOpenIAM','OpenIAM');
insert into METADATA_TYPE(TYPE_ID, DESCRIPTION, SYNC_MANAGED_SYS) values('divisionType','Division', 0);
insert into METADATA_TYPE(TYPE_ID, DESCRIPTION, SYNC_MANAGED_SYS) values('departmentType','Department', 0);
insert into METADATA_TYPE(TYPE_ID, DESCRIPTION, SYNC_MANAGED_SYS) values('MANAGED_SYS','Managed System', 0);
insert into METADATA_TYPE(TYPE_ID, DESCRIPTION, SYNC_MANAGED_SYS) values('SYS_ACTION','System Actions', 0);
insert into METADATA_TYPE(TYPE_ID, DESCRIPTION, SYNC_MANAGED_SYS) values('AUTH_REPO','Authentication Repository', 0);
insert into METADATA_TYPE(TYPE_ID, DESCRIPTION, SYNC_MANAGED_SYS) values('WEBSERVICE','Web Service', 0);
insert into METADATA_TYPE(TYPE_ID, DESCRIPTION, SYNC_MANAGED_SYS) values('WEBSERVICE_OP','Web Service Operation', 0);
insert into METADATA_TYPE(TYPE_ID, DESCRIPTION, SYNC_MANAGED_SYS) values('NO-PROVISION-APP','Un-Provisionable Apps', 0);


insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name) values ('401','MANAGED_SYS','SUBMIT_USER_TO_CONNECTOR');
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name) values ('402','MANAGED_SYS','INCLUDE_IN_PASSWORD_SYNC');
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name) values ('406','MANAGED_SYS','TABLE_NAME');
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name) values ('405','MANAGED_SYS','INCLUDE_IN_SYNC');

insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name) values ('411','AUTH_REPO','HOST_URL');
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name) values ('412','AUTH_REPO','BASE_DN');
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name) values ('413','AUTH_REPO','HOST_LOGIN ID');
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name) values ('421','AUTH_REPO','PASSWORD');
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name) values ('415','AUTH_REPO','COMMUNICATION_PROTOCOL');
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name) values ('416','AUTH_REPO','OBJECT_CLASS');
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name) values ('418','AUTH_REPO','SEARCH_ATTRIBUTE');
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name) values ('419','AUTH_REPO','MANAGED_SYS_ID');
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name) values ('420','AUTH_REPO','DN_ATTRIBUTE');

insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name) values ('430','WEBSERVICE','END_POINT');
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name) values ('440','WEBSERVICE_OP','OPERATION_NAME');

insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name) values ('450','NO-PROVISION-APP','URL');


insert into CATEGORY (category_id, parent_id, category_name, show_list) values ('ROLE_TYPE', 'ROOT', 'ROLE Types',0);
insert into CATEGORY (category_id, parent_id, category_name, show_list) values ('GROUP_TYPE', 'ROOT', 'GROUP Types',0);
insert into CATEGORY (category_id, parent_id, category_name, show_list) values ('ORG_TYPE', 'ROOT', 'ORGANIZATION Types',0);
insert into CATEGORY_TYPE (category_id, type_id) values('ORG_TYPE','divisionType');
insert into CATEGORY_TYPE (category_id, type_id) values('ORG_TYPE','departmentType');

insert into METADATA_TYPE(TYPE_ID, DESCRIPTION,SYNC_MANAGED_SYS) values('SystemAccount','System Account type',1);
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

insert into METADATA_TYPE(TYPE_ID, DESCRIPTION,SYNC_MANAGED_SYS) values('LdapOrgPerson','LdapOrgPerson User type',1);
insert into METADATA_TYPE(TYPE_ID, DESCRIPTION,SYNC_MANAGED_SYS) values('ADUser','AD User type',1);
insert into METADATA_TYPE(TYPE_ID, DESCRIPTION,SYNC_MANAGED_SYS) values('ADGroup','AD User type',1);

insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,DESCRIPTION,MULTI_VALUE,REQUIRED) values ('300','LdapOrgPerson', 'name','',0,0);
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,DESCRIPTION,MULTI_VALUE,REQUIRED) values ('301','LdapOrgPerson', 'distinguishedName','',0,0);
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,DESCRIPTION,MULTI_VALUE,REQUIRED) values ('302','LdapOrgPerson', 'objectclass','',1,1);
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,DESCRIPTION,MULTI_VALUE,REQUIRED) values ('303','LdapOrgPerson', 'aliasedObjectName','',0,0);
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,DESCRIPTION,MULTI_VALUE,REQUIRED) values ('304','LdapOrgPerson', 'cn','',0,1);
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,DESCRIPTION,MULTI_VALUE,REQUIRED) values ('305','LdapOrgPerson', 'sn','',0,0);
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,DESCRIPTION,MULTI_VALUE,REQUIRED) values ('306','LdapOrgPerson', 'serialNumber','',0,0);
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,DESCRIPTION,MULTI_VALUE,REQUIRED) values ('307','LdapOrgPerson', 'c','',0,0);
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,DESCRIPTION,MULTI_VALUE,REQUIRED) values ('308','LdapOrgPerson', 'l','',0,0);
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,DESCRIPTION,MULTI_VALUE,REQUIRED) values ('309','LdapOrgPerson', 'st','',0,0);
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,DESCRIPTION,MULTI_VALUE,REQUIRED) values ('310','LdapOrgPerson', 'street','',0,0);
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,DESCRIPTION,MULTI_VALUE,REQUIRED) values ('311','LdapOrgPerson', 'o','',0,1);
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,DESCRIPTION,MULTI_VALUE,REQUIRED) values ('312','LdapOrgPerson', 'ou','',0,0);
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,DESCRIPTION,MULTI_VALUE,REQUIRED) values ('313','LdapOrgPerson', 'title','',0,0);
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,DESCRIPTION,MULTI_VALUE,REQUIRED) values ('314','LdapOrgPerson', 'description','',0,0);
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,DESCRIPTION,MULTI_VALUE,REQUIRED) values ('341','LdapOrgPerson', 'givenName','',0,0);
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,DESCRIPTION,MULTI_VALUE,REQUIRED) values ('342','LdapOrgPerson', 'initials','',0,0);
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,DESCRIPTION,MULTI_VALUE,REQUIRED) values ('348','LdapOrgPerson', 'uid','',0,0);
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,DESCRIPTION,MULTI_VALUE,REQUIRED) values ('349','LdapOrgPerson', 'mail','',0,0);

insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,DESCRIPTION,MULTI_VALUE,REQUIRED) values ('315','LdapOrgPerson', 'businessCategory','',0,0);
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,DESCRIPTION,MULTI_VALUE,REQUIRED) values ('316','LdapOrgPerson', 'postalAddress','',0,0);
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,DESCRIPTION,MULTI_VALUE,REQUIRED) values ('317','LdapOrgPerson', 'postalCode','',0,0);
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,DESCRIPTION,MULTI_VALUE,REQUIRED) values ('318','LdapOrgPerson', 'postOfficeBox','',0,0);
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,DESCRIPTION,MULTI_VALUE,REQUIRED) values ('319','LdapOrgPerson', 'physicalDeliveryOfficeName','',0,0);
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,DESCRIPTION,MULTI_VALUE,REQUIRED) values ('320','LdapOrgPerson', 'telephoneNumber','',0,0);
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,DESCRIPTION,MULTI_VALUE,REQUIRED) values ('321','LdapOrgPerson', 'telexNumber','',0,0);
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,DESCRIPTION,MULTI_VALUE,REQUIRED) values ('322','LdapOrgPerson', 'teletexTerminalIdentifier','',0,0);
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,DESCRIPTION,MULTI_VALUE,REQUIRED) values ('323','LdapOrgPerson', 'facsimileTelephoneNumber','',0,0);
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,DESCRIPTION,MULTI_VALUE,REQUIRED) values ('324','LdapOrgPerson', 'x121Address','',0,0);
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,DESCRIPTION,MULTI_VALUE,REQUIRED) values ('325','LdapOrgPerson', 'internationaliSDNNumber','',0,0);
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,DESCRIPTION,MULTI_VALUE,REQUIRED) values ('326','LdapOrgPerson', 'registeredAddress','',0,0);
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,DESCRIPTION,MULTI_VALUE,REQUIRED) values ('327','LdapOrgPerson', 'destinationIndicator','',0,0);
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,DESCRIPTION,MULTI_VALUE,REQUIRED) values ('328','LdapOrgPerson', 'preferredDeliveryMethod','',0,0);
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,DESCRIPTION,MULTI_VALUE,REQUIRED) values ('329','LdapOrgPerson', 'presentationAddress','',0,0);
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,DESCRIPTION,MULTI_VALUE,REQUIRED) values ('330','LdapOrgPerson', 'supportedApplicationContext','',0,0);
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,DESCRIPTION,MULTI_VALUE,REQUIRED) values ('331','LdapOrgPerson', 'member','',0,0);
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,DESCRIPTION,MULTI_VALUE,REQUIRED) values ('332','LdapOrgPerson', 'owner','',0,0);
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,DESCRIPTION,MULTI_VALUE,REQUIRED) values ('333','LdapOrgPerson', 'roleOccupant','',0,0);
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,DESCRIPTION,MULTI_VALUE,REQUIRED) values ('334','LdapOrgPerson', 'seeAlso','',0,0);
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,DESCRIPTION,MULTI_VALUE,REQUIRED) values ('335','LdapOrgPerson', 'userPassword','',0,0);
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,DESCRIPTION,MULTI_VALUE,REQUIRED) values ('336','LdapOrgPerson', 'userCertificate','',0,0);
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,DESCRIPTION,MULTI_VALUE,REQUIRED) values ('337','LdapOrgPerson', 'cACertificate','',0,0);
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,DESCRIPTION,MULTI_VALUE,REQUIRED) values ('338','LdapOrgPerson', 'authorityRevocationList','',0,0);
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,DESCRIPTION,MULTI_VALUE,REQUIRED) values ('339','LdapOrgPerson', 'certificateRevocationList','',0,0);
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,DESCRIPTION,MULTI_VALUE,REQUIRED) values ('340','LdapOrgPerson', 'crossCertificatePair','',0,0);
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,DESCRIPTION,MULTI_VALUE,REQUIRED) values ('343','LdapOrgPerson', 'generationQualifier','',0,0);
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,DESCRIPTION,MULTI_VALUE,REQUIRED) values ('344','LdapOrgPerson', 'x500UniqueIdentifier','',0,0);
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,DESCRIPTION,MULTI_VALUE,REQUIRED) values ('346','LdapOrgPerson', 'uniqueMember','',0,0);
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,DESCRIPTION,MULTI_VALUE,REQUIRED) values ('347','LdapOrgPerson', 'houseIdentifier','',0,0);
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,DESCRIPTION,MULTI_VALUE,REQUIRED) values ('350','LdapOrgPerson', 'ref','',0,0);
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,DESCRIPTION,MULTI_VALUE,REQUIRED) values ('351','LdapOrgPerson', 'referral','',0,0);
insert into METADATA_ELEMENT(metadata_id, type_id, attribute_name,DESCRIPTION,MULTI_VALUE,REQUIRED) values ('352','LdapOrgPerson', 'krbName','',0,0);




insert into METADATA_TYPE(TYPE_ID, DESCRIPTION) values('AD_Connector','Active Directory Connector');
insert into METADATA_TYPE(TYPE_ID, DESCRIPTION) values('LDAP_Connector','LDAP Connector');
insert into METADATA_TYPE(TYPE_ID, DESCRIPTION) values('RDBMS_Connector','RDBMS Connector');

insert into METADATA_TYPE(TYPE_ID, DESCRIPTION) values('SOAP_Connector','SOAP Connector');
insert into METADATA_TYPE(TYPE_ID, DESCRIPTION) values('SCRIPT_Connector','Script Connector');
insert into METADATA_TYPE(TYPE_ID, DESCRIPTION) values('GOOGLE_Connector','GOOGLE APPS Connector');

UPDATE METADATA_TYPE
SET ACTIVE = 1, SYNC_MANAGED_SYS = 1;

insert into COMPANY(company_id, company_name, status, type_ID, DOMAIN_NAME, CLASSIFICATION) values('100','OpenIAM', 'ACTIVE', 'OrgOpenIAM','openiam.com', 'ORGANIZATION');
insert into COMPANY(company_id, company_name, status, type_ID, CLASSIFICATION, PARENT_ID ) values('101','Sales', 'ACTIVE', 'departmentType', 'DEPARTMENT', '100');
insert into COMPANY(company_id, company_name, status, type_ID, CLASSIFICATION, PARENT_ID) values('102','Finance', 'ACTIVE', 'departmentType', 'DEPARTMENT', '100');
insert into COMPANY(company_id, company_name, status, type_ID, CLASSIFICATION, PARENT_ID) values('103','Customer Service', 'ACTIVE', 'departmentType', 'DEPARTMENT', '100');
insert into COMPANY(company_id, company_name, status, type_ID, CLASSIFICATION, PARENT_ID) values('104','IT', 'ACTIVE', 'departmentType', 'DEPARTMENT', '100');



insert into CATEGORY(CATEGORY_ID, PARENT_ID, CATEGORY_NAME, SHOW_LIST) VALUES('ROOT','', 'ROOT',0);
insert into CATEGORY(CATEGORY_ID, PARENT_ID, CATEGORY_NAME, SHOW_LIST) VALUES('ACL','', 'ACL Root',0);
insert into CATEGORY(CATEGORY_ID, PARENT_ID, CATEGORY_NAME, SHOW_LIST) VALUES('WebSite','', 'ACL',0);
insert into CATEGORY(CATEGORY_ID, PARENT_ID, CATEGORY_NAME, SHOW_LIST) VALUES('Application','', 'ACL',0);
insert into CATEGORY (category_id, parent_id, category_name, show_list) values ('USER_TYPE', 'ROOT', 'User Types',0);
insert into CATEGORY (category_id, parent_id, category_name, show_list,  DISPLAY_ORDER) values ('MANAGED_SYS_TYPE', 'ROOT', 'Managed System Types',0,0);

insert into CATEGORY (category_id, parent_id, category_name, show_list, DISPLAY_ORDER) values ('CONNECTOR_TYPE', 'ROOT', 'Provisioning Connectors',0,0);


insert into CATEGORY_TYPE (category_id, type_ID) values('USER_TYPE','InetOrgPerson');
insert into CATEGORY_TYPE (category_id, type_ID) values('USER_TYPE','Contractor');
insert into CATEGORY_TYPE (category_id, type_ID) values('USER_TYPE','SystemAccount');

insert into CATEGORY_TYPE (category_id, type_ID) values('CONNECTOR_TYPE','AD_Connector');
insert into CATEGORY_TYPE (category_id, type_ID) values('CONNECTOR_TYPE','LDAP_Connector');
insert into CATEGORY_TYPE (category_id, type_ID) values('CONNECTOR_TYPE','RDBMS_Connector');
insert into CATEGORY_TYPE (category_id, type_ID) values('CONNECTOR_TYPE','SOAP_Connector');
insert into CATEGORY_TYPE (category_id, type_ID) values('CONNECTOR_TYPE','SCRIPT_Connector');

insert into CATEGORY_TYPE (category_id, type_ID) values('ACL','DIRECTORY');
insert into CATEGORY_TYPE (category_id, type_id) values('ACL','FILE');
insert into CATEGORY_TYPE (category_id, type_id) values('ACL','URL');

insert into CATEGORY_TYPE (category_id, type_ID) values('MANAGED_SYS_TYPE','LdapOrgPerson');
insert into CATEGORY_TYPE (category_id, type_ID) values('MANAGED_SYS_TYPE','ADUser');
insert into CATEGORY_TYPE (category_id, type_ID) values('MANAGED_SYS_TYPE','ADGroup');


update CATEGORY
SET DISPLAY_ORDER = 0, SHOW_LIST = 0
WHERE DISPLAY_ORDER IS NULL OR SHOW_LIST IS NULL;


INSERT INTO ROLE (ROLE_ID,SERVICE_ID,ROLE_NAME) VALUES ('SUPER_SEC_ADMIN','IDM','Super Security Admin');
INSERT INTO ROLE (ROLE_ID,SERVICE_ID,ROLE_NAME) VALUES ('SEC_ADMIN','IDM','Security Admin');
INSERT INTO ROLE (ROLE_ID,SERVICE_ID,ROLE_NAME) VALUES ('END_USER','USR_SEC_DOMAIN','End User');


INSERT INTO ROLE (ROLE_ID,SERVICE_ID,ROLE_NAME) VALUES ('HR','USR_SEC_DOMAIN','Human Resource');
INSERT INTO ROLE (ROLE_ID,SERVICE_ID,ROLE_NAME) VALUES ('MANAGER','USR_SEC_DOMAIN','Manager');
INSERT INTO ROLE (ROLE_ID,SERVICE_ID,ROLE_NAME) VALUES ('SECURITY_MANAGER','USR_SEC_DOMAIN','Security Manager');
INSERT INTO ROLE (ROLE_ID,SERVICE_ID,ROLE_NAME) VALUES ('SEC_ADMIN','USR_SEC_DOMAIN','Security Admin');

insert into GRP (GRP_id, grp_name)   values('SUPER_SEC_ADMIN_GRP','Super Admin Group');
insert into GRP (grp_id, grp_name)   values('SEC_ADMIN_GRP','Sec Admin Group');
insert into GRP (grp_id, grp_name)   values('END_USER_GRP','End User Group');

insert into GRP (grp_id, grp_name)   values('HR_GRP','HR Group');
insert into GRP (grp_id, grp_name)   values('MNGR_GRP','Manager Group');
insert into GRP (grp_id, grp_name)   values('SECURITY_GRP','Security Group');

insert into USERS (user_id,first_name, last_name, STATUS, COMPANY_ID ) values('3000','sys','','ACTIVE','100');
insert into USERS (user_id,first_name, last_name, STATUS, COMPANY_ID ) values('3001','sys2','','ACTIVE','100');
insert into USERS (user_id,first_name, last_name, STATUS, COMPANY_ID  ) values('3006','Scott','Nelson','ACTIVE','100');

insert into USERS (user_id,first_name, last_name, STATUS, COMPANY_ID  ) values('3007','HR','User','ACTIVE','100');
insert into USERS (user_id,first_name, last_name, STATUS, COMPANY_ID  ) values('3008','Hiring','Manager','ACTIVE','100');
insert into USERS (user_id,first_name, last_name, STATUS, COMPANY_ID  ) values('3009','Security','Manager','ACTIVE','100');



insert into USER_GRP (USER_GRP_ID, grp_id, user_id) 	values('1000','SUPER_SEC_ADMIN_GRP','3000');
insert into USER_GRP (USER_GRP_ID,grp_id, user_id) 	values('1001','SUPER_SEC_ADMIN_GRP','3001');
insert into USER_GRP (USER_GRP_ID,grp_id, user_id) 	values('1002','END_USER_GRP','3006');

insert into USER_GRP (USER_GRP_ID,grp_id, user_id) 	values('1003','HR_GRP','3007');
insert into USER_GRP (USER_GRP_ID,grp_id, user_id) 	values('1004','MNGR_GRP','3008');
insert into USER_GRP (USER_GRP_ID,grp_id, user_id) 	values('1005','SECURITY_GRP','3009');

INSERT INTO GRP_ROLE(ROLE_ID,GRP_ID, SERVICE_ID) VALUES ('SUPER_SEC_ADMIN','SUPER_SEC_ADMIN_GRP', 'IDM');
INSERT INTO GRP_ROLE(ROLE_ID,GRP_ID, SERVICE_ID) VALUES ('END_USER','END_USER_GRP', 'USR_SEC_DOMAIN');

INSERT INTO GRP_ROLE(ROLE_ID,GRP_ID, SERVICE_ID) VALUES ('HR','HR_GRP', 'USR_SEC_DOMAIN');
INSERT INTO GRP_ROLE(ROLE_ID,GRP_ID, SERVICE_ID) VALUES ('MANAGER','MNGR_GRP', 'USR_SEC_DOMAIN');
INSERT INTO GRP_ROLE(ROLE_ID,GRP_ID, SERVICE_ID) VALUES ('SECURITY_MANAGER','SECURITY_GRP', 'USR_SEC_DOMAIN');


insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, DISPLAY_ORDER) values('ROOT', NULL ,'Root','Root', null, 'en',0);

insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, DISPLAY_ORDER) values('IDM','ROOT','Identity','IDENTITY MANAGER','security/index.jsp', 'en',1);

insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, DISPLAY_ORDER) values('IDMAN','IDM','User Admin','User Admin','idman/index.jsp', 'en',1);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, DISPLAY_ORDER) values('ACC_CONTROL','IDM','Access Control','Access Control','access/accessIndex.do', 'en',2);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, active, display_order) values('PROVISIONING','IDM','Provisioning','Provisioning','prov/provIndex.do', 'en',1,3);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, active, display_order) values('SECURITY_POLICY','IDM','Policy','Policy','security/policy.do?method=init&nav=reset', 'en',1,4);

insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, DISPLAY_ORDER) values('ADMIN','IDM','Administration','Administration','admin/index.jsp', 'en',6);

insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, DISPLAY_ORDER) values('USER','IDMAN','User','User','menunav.do', 'en',1);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, DISPLAY_ORDER) values('ORG','IDMAN','Organization','Organization','orglist.cnt', 'en',2);



insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, DISPLAY_ORDER) values('QUERYUSER','USER','Query','Query User','idman/userSearch.do?action=view', 'en',1);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, DISPLAY_ORDER) values('ADDUSER','USER','Add','Add User','newUser.cnt', 'en',2);

insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, DISPLAY_ORDER) values('USERSUMMARY','QUERYUSER','User Details','User Details','editUser.cnt', 'en',1);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, DISPLAY_ORDER) values('USERATTR','QUERYUSER','Ext Attributes','Ext Attributes','userAttr.cnt', 'en',2);

insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, DISPLAY_ORDER) values('USERIDENTITY','QUERYUSER','Identities','User Identities','userIdentity.cnt', 'en',3);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, DISPLAY_ORDER) values('USERGROUP','QUERYUSER','Group','User Groups','userGroup.cnt', 'en',4);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, DISPLAY_ORDER) values('USERROLE','QUERYUSER','Role','User Role','userRole.cnt', 'en',5);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, DISPLAY_ORDER) values('USERHISTORY','QUERYUSER','History','User History','userHistory.cnt', 'en',7);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, DISPLAY_ORDER) values('USERPSWDRESET','QUERYUSER','Password Reset','Password Reset','resetPassword.cnt', 'en',8);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, DISPLAY_ORDER) values('DELG_FILTER','QUERYUSER','Delegation Filter','Delegation Filter','userDelegationFilter.cnt', 'en',8);



insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, active, display_order) values('SECURITY_GROUP','ACC_CONTROL','Group','Group','grouplist.cnt', 'en',1,1);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, active, display_order) values('SECURITY_ROLE','ACC_CONTROL','Role','Role','rolelist.cnt', 'en',1,2);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, active, display_order) values('SECURITY_RES','ACC_CONTROL','Resource','Resource','resourcelist.cnt', 'en',1,3);


insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, DISPLAY_ORDER) values('RESSUMMARY','SECURITY_RES','Resource Details','Resource Details','resourceDetail.cnt', 'en',1);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, DISPLAY_ORDER) values('RESPOLICYMAP','SECURITY_RES','Policy Map','Policy Map','resourceMap.cnt', 'en',2);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, DISPLAY_ORDER) values('RESAPPROVER','SECURITY_RES','Approval Flow','Approval Flow','resApprovalFlow.cnt', 'en',3);

/* ROLE MENU */

insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, DISPLAY_ORDER) values('ROLE_SUMMARY','SECURITY_ROLE','Detail','Role Details','roleDetail.cnt', 'en',1);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, DISPLAY_ORDER) values('ROLE_RESMAP','SECURITY_ROLE','Resource Map','Resource Map','roleResource.cnt', 'en',2);

	
/* Provisioning MENU options */

insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, DISPLAY_ORDER) values('PROVCONNECT','PROVISIONING','Connectors','Provisioning Connectors','connectorList.cnt', 'en',3);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, DISPLAY_ORDER) values('MNGSYS','PROVISIONING','Managed Resource','Managed Connections','managedSysList.cnt', 'en',4);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, DISPLAY_ORDER) values('SYNCUSER','PROVISIONING','Sychronization','Synchronization','syncUser.cnt', 'en',3);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, DISPLAY_ORDER) values('SYNCLOG','PROVISIONING','LOG Viewer','LOG Viewer','syncExecLog.cnt', 'en',4);


insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, DISPLAY_ORDER) values('SYNCDETAIL','SYNCUSER','Configuration','Configuration','syncConfiguration.cnt', 'en',1);


/* Admin MENU options */
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, DISPLAY_ORDER) values('SECDOMAIN','ADMIN','Security Domain','Security Domain','secDomainList.cnt', 'en',1);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, DISPLAY_ORDER) values('LOCATION','ADMIN','Location','Location','locationList.cnt', 'en',3);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, DISPLAY_ORDER) values('CHALLENGE','ADMIN','Challenge Quest','Challenge Quest','challengeQuestList.cnt', 'en',6);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, DISPLAY_ORDER) values('BATCH_PROC','ADMIN','Batch Processes','Batch Processes','batchList.cnt', 'en',6);

insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, DISPLAY_ORDER) values('REPORT','IDM','Report','Report','security/reportIndex.do', 'en',5);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, DISPLAY_ORDER) values('AUDITREPORT','REPORT','Audit Reports','Audit Information Reports','', 'en',2);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, DISPLAY_ORDER) values('AUDIT_RPT','AUDITREPORT','Audit Report','Audit Report','auditReport.cnt', 'en',1);

/* Self Service MENU options */
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, display_order) values('SELFSERVICE', 'ROOT' ,'SELF SERVICE','SELF SERVICE','', 'en',0);

insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, display_order, PUBLIC_URL) values('ACCESSCENTER','SELFSERVICE', 'Access Management Center', 'Access Management Center', null, 'en', '1',0);

insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, display_order, PUBLIC_URL) values('REQINBOX', 'ACCESSCENTER' , 'In-Box','In-Box','myPendingRequest.selfserve', 'en','3',0);

insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, display_order, PUBLIC_URL) values('MANAGEREQ', 'ACCESSCENTER' , 'Request History','Request History','requestList.selfserve', 'en','4',0);

insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, display_order, PUBLIC_URL) values('NEWUSER','ACCESSCENTER','New User', 'New User', 'newHire.selfserve', 'en', '6',0);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, display_order, PUBLIC_URL) values('NEWUSER-NOAPPRV','ACCESSCENTER','New User-NO Approver', 'New User-No Approver', 'newUserNoApp.selfserve', 'en', '7',0);

insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, display_order, PUBLIC_URL) values('SELFCENTER','SELFSERVICE','Self Service Center', 'Self Service Center', null, 'en', '2',0);

insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, display_order, PUBLIC_URL) values('DIRECTORY','SELFCENTER','Directory Lookup', 'Directory Lookup', 'pub/directory.do?method=view', 'en', '1',1);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, display_order, PUBLIC_URL) values('SELF_REGISTER','SELFCENTER','Self Registration', 'Self Registration', 'pub/selfRegister.selfserve', 'en', '2',1);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, display_order, PUBLIC_URL) values('CHNGPSWD','SELFCENTER', 'Change Password', 'Change Password', 'passwordChange.selfserve', 'en', '3',0);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, display_order, PUBLIC_URL) values('IDQUEST','SELFCENTER', 'Challenge Response', 'Challenge Response', 'identityQuestion.selfserve', 'en', '4',0);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, display_order, PUBLIC_URL) values('FORGOTPSWD','SELFCENTER', 'Forgot Password', 'Forgot Password', 'pub/unlockUser.selfserve', 'en', '5',1);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, display_order, PUBLIC_URL) values('PROFILE','SELFCENTER', 'Edit Your Profile', 'Edit Your Profile', 'profile.selfserve', 'en', '6',0);




INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID, SERVICE_ID) VALUES('BATCH_PROC','SUPER_SEC_ADMIN','IDM');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID,SERVICE_ID) VALUES('REPORT','SUPER_SEC_ADMIN','IDM');



/* service admin role */

INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID,SERVICE_ID) VALUES('IDMAN','SUPER_SEC_ADMIN','IDM');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID,SERVICE_ID) VALUES('SECURITY_POLICY','SUPER_SEC_ADMIN','IDM');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID,SERVICE_ID) VALUES('ACC_CONTROL','SUPER_SEC_ADMIN','IDM');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID,SERVICE_ID) VALUES('PROVISIONING','SUPER_SEC_ADMIN','IDM');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID,SERVICE_ID) VALUES('ADMIN','SUPER_SEC_ADMIN','IDM');



INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID,SERVICE_ID) VALUES('SECDOMAIN','SUPER_SEC_ADMIN','IDM');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID,SERVICE_ID) VALUES('METADATA','SUPER_SEC_ADMIN','IDM');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID,SERVICE_ID) VALUES('LOCATION','SUPER_SEC_ADMIN','IDM');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID,SERVICE_ID) VALUES('ORGPOLICY','SUPER_SEC_ADMIN','IDM');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID,SERVICE_ID) VALUES('SYSNOTIFICATION','SUPER_SEC_ADMIN','IDM');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID,SERVICE_ID) VALUES('CHALLENGE','SUPER_SEC_ADMIN','IDM');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID,SERVICE_ID) VALUES('REFDATA','SUPER_SEC_ADMIN','IDM');


INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID,SERVICE_ID) VALUES('ORG','SUPER_SEC_ADMIN','IDM');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID,SERVICE_ID) VALUES('USER','SUPER_SEC_ADMIN','IDM');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID,SERVICE_ID) VALUES('SYNCUSER','SUPER_SEC_ADMIN','IDM');

INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID,SERVICE_ID) VALUES('SYNCDETAIL','SUPER_SEC_ADMIN','IDM');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID,SERVICE_ID) VALUES('SYNCLOG','SUPER_SEC_ADMIN','IDM');


INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID,SERVICE_ID) VALUES('QUERYUSER','SUPER_SEC_ADMIN','IDM');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID,SERVICE_ID) VALUES('ADDUSER','SUPER_SEC_ADMIN','IDM');


INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID,SERVICE_ID) VALUES('USERSUMMARY','SUPER_SEC_ADMIN','IDM');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID,SERVICE_ID) VALUES('USERIDENTITY','SUPER_SEC_ADMIN','IDM');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID,SERVICE_ID) VALUES('USERGROUP','SUPER_SEC_ADMIN','IDM');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID,SERVICE_ID) VALUES('USERROLE','SUPER_SEC_ADMIN','IDM');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID,SERVICE_ID) VALUES('USERHISTORY','SUPER_SEC_ADMIN','IDM');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID,SERVICE_ID) VALUES('USERPSWDRESET','SUPER_SEC_ADMIN','IDM');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID,SERVICE_ID) VALUES('DELG_FILTER','SUPER_SEC_ADMIN','IDM');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID,SERVICE_ID) VALUES('USERATTR','SUPER_SEC_ADMIN','IDM');

INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID,SERVICE_ID) VALUES('RESSUMMARY','SUPER_SEC_ADMIN','IDM');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID,SERVICE_ID) VALUES('RESPOLICYMAP','SUPER_SEC_ADMIN','IDM');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID,SERVICE_ID) VALUES('RESAPPROVER','SUPER_SEC_ADMIN','IDM');


INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID,SERVICE_ID) VALUES('ROLE_SUMMARY','SUPER_SEC_ADMIN','IDM');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID,SERVICE_ID) VALUES('ROLE_RESMAP','SUPER_SEC_ADMIN','IDM');


INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID,SERVICE_ID) VALUES('SECURITY_GROUP','SUPER_SEC_ADMIN','IDM');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID,SERVICE_ID) VALUES('SECURITY_ROLE','SUPER_SEC_ADMIN','IDM');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID,SERVICE_ID) VALUES('SECURITY_RES','SUPER_SEC_ADMIN','IDM');

INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID,SERVICE_ID) VALUES('PROVCONNECT','SUPER_SEC_ADMIN','IDM');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID,SERVICE_ID) VALUES('MNGSYS','SUPER_SEC_ADMIN','IDM');


INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID,SERVICE_ID) VALUES('IDSYNC','SUPER_SEC_ADMIN','IDM');

INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID, SERVICE_ID) VALUES('SELFSERVICE','END_USER','USR_SEC_DOMAIN');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID, SERVICE_ID) VALUES('ACCESSCENTER','END_USER','USR_SEC_DOMAIN');


INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID, SERVICE_ID) VALUES('SELF_REGISTER','END_USER','USR_SEC_DOMAIN');

INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID, SERVICE_ID) VALUES('REQINBOX','END_USER','USR_SEC_DOMAIN');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID, SERVICE_ID) VALUES('MANAGEREQ','END_USER','USR_SEC_DOMAIN');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID, SERVICE_ID) VALUES('SELFCENTER','END_USER','USR_SEC_DOMAIN');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID, SERVICE_ID) VALUES('DIRECTORY','END_USER','USR_SEC_DOMAIN');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID, SERVICE_ID) VALUES('CHNGPSWD','END_USER','USR_SEC_DOMAIN');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID, SERVICE_ID) VALUES('IDQUEST','END_USER','USR_SEC_DOMAIN');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID, SERVICE_ID) VALUES('FORGOTPSWD','END_USER','USR_SEC_DOMAIN');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID, SERVICE_ID) VALUES('PROFILE','END_USER','USR_SEC_DOMAIN');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID, SERVICE_ID) VALUES('REPORTINC','END_USER','USR_SEC_DOMAIN');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID, SERVICE_ID) VALUES('CONTADMIN','END_USER','USR_SEC_DOMAIN');

INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID, SERVICE_ID) VALUES('NEWUSER','END_USER','USR_SEC_DOMAIN');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID, SERVICE_ID) VALUES('NEWUSER-NOAPPRV','END_USER','USR_SEC_DOMAIN');



INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID, SERVICE_ID) VALUES('REQINBOX','SUPER_SEC_ADMIN','USR_SEC_DOMAIN');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID, SERVICE_ID) VALUES('MANAGEREQ','SUPER_SEC_ADMIN','USR_SEC_DOMAIN');


insert into LOGIN(SERVICE_ID, LOGIN, MANAGED_SYS_ID, USER_ID, PASSWORD,RESET_PWD, IS_LOCKED, AUTH_FAIL_COUNT) VALUES('IDM','sysadmin','0','3000','b83a81d1b3f5f209a70ec02c3d7f7fc5',0,0,0);
insert into LOGIN(SERVICE_ID, LOGIN, MANAGED_SYS_ID, USER_ID, PASSWORD,RESET_PWD, IS_LOCKED, AUTH_FAIL_COUNT) VALUES('USR_SEC_DOMAIN','sysadmin','0','3000','b83a81d1b3f5f209a70ec02c3d7f7fc5',0,0,0);

insert into LOGIN(SERVICE_ID, LOGIN, MANAGED_SYS_ID, USER_ID, PASSWORD,RESET_PWD, IS_LOCKED, AUTH_FAIL_COUNT) VALUES('IDM','sysadmin2','0','3001','b83a81d1b3f5f209a70ec02c3d7f7fc5',0,0,0);
insert into LOGIN(SERVICE_ID, LOGIN, MANAGED_SYS_ID, USER_ID, PASSWORD,RESET_PWD, IS_LOCKED, AUTH_FAIL_COUNT) VALUES('USR_SEC_DOMAIN','snelson','0','3006','b83a81d1b3f5f209a70ec02c3d7f7fc5',0,0,0);

insert into LOGIN(SERVICE_ID, LOGIN, MANAGED_SYS_ID, USER_ID, PASSWORD,RESET_PWD, IS_LOCKED, AUTH_FAIL_COUNT) VALUES('USR_SEC_DOMAIN','hrmanager','0','3007','b83a81d1b3f5f209a70ec02c3d7f7fc5',0,0,0);
insert into LOGIN(SERVICE_ID, LOGIN, MANAGED_SYS_ID, USER_ID, PASSWORD,RESET_PWD, IS_LOCKED, AUTH_FAIL_COUNT) VALUES('USR_SEC_DOMAIN','manager','0','3008','b83a81d1b3f5f209a70ec02c3d7f7fc5',0,0,0);
insert into LOGIN(SERVICE_ID, LOGIN, MANAGED_SYS_ID, USER_ID, PASSWORD,RESET_PWD, IS_LOCKED, AUTH_FAIL_COUNT) VALUES('USR_SEC_DOMAIN','secmanager','0','3009','b83a81d1b3f5f209a70ec02c3d7f7fc5',0,0,0);

update LOGIN set reset_pwd = 0, is_locked = 0;


INSERT INTO AUTH_STATE(USER_ID, TOKEN, AUTH_STATE, AA, EXPIRATION) values('3000', NULL,0,'OPENIAM',0);
INSERT INTO AUTH_STATE(USER_ID, TOKEN, AUTH_STATE, AA, EXPIRATION) values('3001', NULL,0,'OPENIAM',0);
INSERT INTO AUTH_STATE(USER_ID, TOKEN, AUTH_STATE, AA, EXPIRATION) values('3006', NULL,0,'OPENIAM',0);

INSERT INTO AUTH_STATE(USER_ID, TOKEN, AUTH_STATE, AA, EXPIRATION) values('3007', NULL,0,'OPENIAM',0);
INSERT INTO AUTH_STATE(USER_ID, TOKEN, AUTH_STATE, AA, EXPIRATION) values('3008', NULL,0,'OPENIAM',0);
INSERT INTO AUTH_STATE(USER_ID, TOKEN, AUTH_STATE, AA, EXPIRATION) values('3009', NULL,0,'OPENIAM',0);

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
insert into SEQUENCE_GEN (attribute, next_id) values('DOMAIN_ID','1000');

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



insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'USER', 'PENDING_START_DATE','en','String','PENDING_START_DATE','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'USER', 'PENDING_APPROVAL','en','String','PENDING_APPROVAL','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'USER', 'ACTIVE','en','String','ACTIVE','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'USER', 'INACTIVE','en','String','INACTIVE','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'USER', 'DELETED','en','String','DELETED','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'USER', 'APPROVAL_DECLINED','en','String','APPROVAL_DECLINED','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'USER', 'PENDING_USER_VALIDATION','en','String','PENDING_USER_VALIDATION','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'USER', 'PENDING_INITIAL_LOGIN','en','String','PENDING_INITIAL_LOGIN','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'USER', 'LEAVE','en','String','LEAVE','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'USER', 'TERMINATE','en','String','TERMINATE','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'USER', 'RETIRED','en','String','RETIRED','100', 'IDM');


insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'USER_2ND_STATUS', 'LOCKED','en','String','LOCKED','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'USER_2ND_STATUS', 'LOCKED_ADMIN','en','String','LOCKED_ADMIN','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'USER_2ND_STATUS', 'DISABLED','en','String','DISABLED','100', 'IDM');

insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'OPERATION', 'DL','en','String','DELETE','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'OPERATION', 'RJ','en','String','REJECT','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'OPERATION', 'BL','en','String','DISABLE','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'OPERATION', 'UB','en','String','UN-DISABLE','100', 'IDM');


insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'SERVICE_STATUS', 'READY','en','String','READY','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'SERVICE_STATUS', 'OFF-LINE','en','String','OFF-LINE','100', 'IDM');



insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'SERVICE_TYPE', 'AD','en','String','ACTIVE DIRECTORY','100', 'IDM');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'SERVICE_TYPE', 'LDAP','en','String','LDAP','100', 'IDM');

/* JOB CODES*/

insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'JOB_CODE', 'MANAGER','en','String','MANAGER','100', 'USR_SEC_DOMAIN');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'JOB_CODE', 'SECURITY MANAGER','en','String','SECURITY MANAGER','100', 'USR_SEC_DOMAIN');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'JOB_CODE', 'SERVICE REP','en','String','SERVICE REP','100', 'USR_SEC_DOMAIN');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'JOB_CODE', 'SALES REP','en','String','SALES REP','100', 'USR_SEC_DOMAIN');

/* USER EMPLOYMENT TYPE*/
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'USER_TYPE', 'PERM FULL TIME','en','String','PERMANENT FULL TIME','100', 'USR_SEC_DOMAIN');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'USER_TYPE', 'PERM ART TIME','en','String','PERMANENT PART TIME','100', 'USR_SEC_DOMAIN');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'USER_TYPE', 'CONTRACTOR','en','String','CONTRACTOR','100', 'USR_SEC_DOMAIN');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'USER_TYPE', 'VENDOR','en','String','VENDOR','100', 'USR_SEC_DOMAIN');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'USER_TYPE', 'AFFILIATE','en','String','AFFILIATE','100', 'USR_SEC_DOMAIN');
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'USER_TYPE', 'SYS ACCOUNT','en','String','SYSTEM ACCOUNT','100', 'USR_SEC_DOMAIN');


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
insert into STATUS ( CODE_GROUP, status_cd, LANGUAGE_CD, status_type, description, COMPANY_OWNER_ID, SERVICE_ID) values( 'COUNTRY_CD', 'NOR','en','String','Norway','100', 'IDM');
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


/* locations */
insert into LOCATION(LOCATION_ID, NAME, COUNTRY, BLDG_NUM, ADDRESS1, CITY, STATE, POSTAL_CD) VALUES ('100', 'HQ', 'US', '111' ,'MAIN ST', 'MY TOWN', 'NY', '12345');
insert into LOCATION(LOCATION_ID, NAME, COUNTRY, BLDG_NUM, ADDRESS1, CITY, STATE, POSTAL_CD) VALUES ('101', 'BRANCH', 'US', '112' ,'Next ST', 'MY TOWN', 'CT', '67890');


/* POLICY ENTRIES */

insert into POLICY_DEF(POLICY_DEF_ID, NAME, DESCRIPTION, POLICY_TYPE, LOCATION_TYPE) VALUES ('100','PASSWORD POLICY','Out of the box Password Policy', '2','DB' );

INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION, OPERATION, POLICY_PARAM_HANDLER,PARAM_GROUP) VALUES('110','100','PWD_HIST_VER','Password history versions', null,'org.openiam.idm.srvc.pswd.rule.PasswordHistoryRule', 'PSWD_COMPOSITION');
INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION, OPERATION, POLICY_PARAM_HANDLER,PARAM_GROUP) VALUES('111','100','PWD_EXPIRATION','Password expiration', null, '', '');
INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION, OPERATION, POLICY_PARAM_HANDLER,PARAM_GROUP) VALUES('141','100','PWD_EXPIRATION_ON_RESET','Password expiration time on reset', null, '', '');
INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION, OPERATION, POLICY_PARAM_HANDLER,PARAM_GROUP) VALUES('133','100','PWD_EXP_GRACE','Password expiration grace period', null, '',null);
INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION, OPERATION, POLICY_PARAM_HANDLER,PARAM_GROUP) VALUES('134','100','CHNG_PSWD_ON_RESET','Change Password after reset', null, '', null);
INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION, OPERATION, POLICY_PARAM_HANDLER,PARAM_GROUP) VALUES('135','100','INITIAL_PSWD_FIXED','Is the initial password rule based?', null, '' ,null);
INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION, OPERATION, POLICY_PARAM_HANDLER,PARAM_GROUP) VALUES('136','100','INITIAL_PSWD_VALUE','Value of the initial password', null, '' ,null);
INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION,OPERATION, POLICY_PARAM_HANDLER,PARAM_GROUP)  VALUES('112','100','PWD_LEN','Password length','RANGE', 'org.openiam.idm.srvc.pswd.rule.PasswordLengthRule', 'PSWD_COMPOSITION');
INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION, OPERATION, POLICY_PARAM_HANDLER,PARAM_GROUP) VALUES('113','100','NUMERIC_CHARS','Numeric characters(Min-Max)','RANGE','org.openiam.idm.srvc.pswd.rule.NumericCharRule', 'PSWD_COMPOSITION');
INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION, OPERATION, POLICY_PARAM_HANDLER,PARAM_GROUP) VALUES('114','100','UPPERCASE_CHARS','Uppercase characters(Min-Max)','RANGE','org.openiam.idm.srvc.pswd.rule.UpperCaseRule', 'PSWD_COMPOSITION');
INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION, OPERATION, POLICY_PARAM_HANDLER,PARAM_GROUP) VALUES('115','100','LOWERCASE_CHARS','Lowercase characters(Min-Max)','RANGE','org.openiam.idm.srvc.pswd.rule.LowerCaseRule', 'PSWD_COMPOSITION');
INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION, OPERATION, POLICY_PARAM_HANDLER,PARAM_GROUP) VALUES('116','100','NON_ALPHA_CHARS','Non-alpha numeric symbols(Min-Max)','RANGE','org.openiam.idm.srvc.pswd.rule.NonAlphaNumericRule', 'PSWD_COMPOSITION');
INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION, OPERATION, POLICY_PARAM_HANDLER,PARAM_GROUP) VALUES('117','100','ALPHA_CHARS','Alpha character(Min-Max)','RANGE','org.openiam.idm.srvc.pswd.rule.AlphaCharRule', 'PSWD_COMPOSITION');
INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION, OPERATION, POLICY_PARAM_HANDLER,PARAM_GROUP) VALUES('120','100','DICTIONARY_CHECK','Dictionary Check','boolean','', '');
INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION, OPERATION, POLICY_PARAM_HANDLER,PARAM_GROUP) VALUES('121','100','PWD_LOGIN','Reject password = Login Id','boolean','org.openiam.idm.srvc.pswd.rule.PasswordNotPrincipalRule', 'PSWD_COMPOSITION');
INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION, OPERATION, POLICY_PARAM_HANDLER,PARAM_GROUP) VALUES('122','100','PWD_NAME','Reject password = First / Last name','boolean','org.openiam.idm.srvc.pswd.rule.PasswordNotNameRule', 'PSWD_COMPOSITION');
INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION, OPERATION, POLICY_PARAM_HANDLER,PARAM_GROUP) VALUES('123','100','VOWELS_IN_PWD','Reject Password containing vowels ','boolean','', 'PSWD_COMPOSITION');
INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION, OPERATION, POLICY_PARAM_HANDLER,PARAM_GROUP) VALUES('124','100','REJECT_NUM_START','Reject passwords that begin or end with a numeric character','boolean', '', 'PSWD_COMPOSITION');
INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION, OPERATION, POLICY_PARAM_HANDLER,PARAM_GROUP) VALUES('125','100','HAS_NUMERIC_AT','Password to contain numeric chars at following positions', null,'', 'PSWD_COMPOSITION');
INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION, OPERATION, POLICY_PARAM_HANDLER,PARAM_GROUP) VALUES('126','100','HAS_ALPHA_NUM_AT','Password to contain alpha numeric chars at following positions', null,'', 'PSWD_COMPOSITION');
INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION, OPERATION, POLICY_PARAM_HANDLER,PARAM_GROUP) VALUES('128','100','FORCE_NON_ALPHA_NUM_AT','Force password to contain non-alpha numeric chars at following positions', null,'', 'PSWD_COMPOSITION');
INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION, OPERATION, POLICY_PARAM_HANDLER,PARAM_GROUP) VALUES('129','100','PWD_EXP_WARN','Days to password expiration warning', null,null, 'MISC');
INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION, OPERATION, POLICY_PARAM_HANDLER,PARAM_GROUP) VALUES('130','100','QUEST_COUNT','Number of questions to display', null, null, null);
INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION, OPERATION, POLICY_PARAM_HANDLER,PARAM_GROUP) VALUES('131','100','QUEST_SRC','Source of questions', null,null, 'SELFSERVE');
INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION, OPERATION, POLICY_PARAM_HANDLER,PARAM_GROUP) VALUES('132','100','QUEST_LIST','Question list', null,null, 'SELFSERVE');

INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION, OPERATION, POLICY_PARAM_HANDLER,PARAM_GROUP) VALUES('138','100','PWD_EQ_PWD','RejectPassword Equals password', null,'org.openiam.idm.srvc.pswd.rule.PasswordNotPasswordRule', 'PSWD_COMPOSITION');
INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION, OPERATION, POLICY_PARAM_HANDLER,PARAM_GROUP) VALUES('139','100','RESET_BY_USER','Reject reset by user', null,'org.openiam.idm.srvc.pswd.rule.ChangePasswordByUserRule', 'PSWD_COMPOSITION');
INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION, OPERATION, POLICY_PARAM_HANDLER,PARAM_GROUP) VALUES('140','100','RESET_PER_TIME','Reset allowed per time unit', null,'org.openiam.idm.srvc.pswd.rule.PasswordChangesFrequencyRule', 'PSWD_COMPOSITION');
INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION, OPERATION, POLICY_PARAM_HANDLER,PARAM_GROUP) VALUES('142','100','PASSWORD_CHANGE_ALLOWED','Determines if a you are allowed to change the password', null,'org.openiam.idm.srvc.pswd.rule.PasswordChangeAllowedRule', 'PSWD_COMPOSITION');
INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION, OPERATION, POLICY_PARAM_HANDLER,PARAM_GROUP) VALUES('170','100','REJECT_CHARS_IN_PSWD','Characters not allowed in a password', null,'org.openiam.idm.srvc.pswd.rule.RejectCharactersRule', 'PSWD_COMPOSITION');
INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION, OPERATION, POLICY_PARAM_HANDLER,PARAM_GROUP) VALUES('171','100','QUEST_ANSWER_CORRECT','Number of answers that are required to be correct', null, null, null);


/* Authentication Policy */
insert into POLICY_DEF(POLICY_DEF_ID, NAME, DESCRIPTION, POLICY_TYPE, LOCATION_TYPE) VALUES ('103','AUTHENTICATION POLICY','Out of the box Authentication Policy', '4','DB' );
INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION) VALUES('157','103','FAILED_AUTH_COUNT','Failed Authentication Attempts');
INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION) VALUES('151','103','AUTO_UNLOCK_TIME','UnLock account in n minutes');
INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION) VALUES('152','103','TOKEN_TYPE','SSO Token Type');
INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION) VALUES('159','103','TOKEN_LIFE','SSO Token Type Life');
INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION) VALUES('153','103','LOGIN_MODULE_SEL_POLCY','Policy to select the login module');
INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION) VALUES('154','103','SUCCESS_URL','URL to forward on authn success');
INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION) VALUES('155','103','FAIL_URL','URL to forward on authn fail');
INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION) VALUES('158','103','DEFAULT_LOGIN_MOD','Default Login Module');
INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION) VALUES('162','103','TOKEN_ISSUER','Issuer of the SSO Token');
INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION) VALUES('163','103','LOGIN_MOD_TYPE','Type of authentication module');
INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION) VALUES('164','103','AUTH_REPOSITORY','Authentication Repository');

INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION) VALUES('179','103','HOST_URL','URL of the host system');
INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION) VALUES('172','103','HOST_LOGIN','Login to the host');
INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION) VALUES('173','103','HOST_PASSWORD','Password to the host');
INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION) VALUES('174','103','BASEDN','Type of authentication module');
INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION) VALUES('175','103','PROTOCOL','Protocol');
INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION) VALUES('176','103','KEY_ATTRIBUTE','Name of the Primary Key Attribute');
INSERT INTO POLICY_DEF_PARAM (DEF_PARAM_ID, POLICY_DEF_ID, NAME, DESCRIPTION) VALUES('178','103','MANAGED_SYS_ID','Managed system Id');


insert into POLICY_DEF(POLICY_DEF_ID, NAME, DESCRIPTION, POLICY_TYPE, LOCATION_TYPE) VALUES ('104','ATTRIBUTE POLICY','Attribute value policies.', '5','DB' );


update POLICY_DEF_PARAM
	set repeats = 0
where repeats is null;

insert into POLICY (POLICY_ID, POLICY_DEF_ID, NAME, DESCRIPTION, STATUS, CREATE_DATE, CREATED_BY) VALUES ('4000','100', 'Default Pswd Policy', 'Default Password Policy', 1,curdate(), '3000');

insert into POLICY (POLICY_ID, POLICY_DEF_ID, NAME, DESCRIPTION, STATUS, CREATE_DATE, CREATED_BY) VALUES ('4001','103', 'Default Authn Policy', 'Default Authentication Policy', 1,curdate(), '3000');


/* Default Authn policy param */
insert into POLICY_ATTRIBUTE (POLICY_ATTR_ID, DEF_PARAM_ID, POLICY_ID, NAME, OPERATION,VALUE1)VALUES ('4101','151', '4001', 'AUTO_UNLOCK_TIME', '','30');
insert into POLICY_ATTRIBUTE (POLICY_ATTR_ID, DEF_PARAM_ID, POLICY_ID, NAME, OPERATION,VALUE1)VALUES ('4102','152', '4001', 'TOKEN_TYPE', '','OPENIAM_TOKEN');
insert into POLICY_ATTRIBUTE (POLICY_ATTR_ID, DEF_PARAM_ID, POLICY_ID, NAME, OPERATION,VALUE1)VALUES ('4103','153', '4001', 'LOGIN_MODULE_SEL_POLCY', '','');
insert into POLICY_ATTRIBUTE (POLICY_ATTR_ID, DEF_PARAM_ID, POLICY_ID, NAME, OPERATION,VALUE1)VALUES ('4104','154', '4001', 'SUCCESS_URL', '','');
insert into POLICY_ATTRIBUTE (POLICY_ATTR_ID, DEF_PARAM_ID, POLICY_ID, NAME, OPERATION,VALUE1)VALUES ('4105','155', '4001', 'FAIL_URL', '','');
insert into POLICY_ATTRIBUTE (POLICY_ATTR_ID, DEF_PARAM_ID, POLICY_ID, NAME, OPERATION,VALUE1)VALUES ('4107','157', '4001', 'FAILED_AUTH_COUNT', '','3');
insert into POLICY_ATTRIBUTE (POLICY_ATTR_ID, DEF_PARAM_ID, POLICY_ID, NAME, OPERATION,VALUE1)VALUES ('4108','158', '4001', 'DEFAULT_LOGIN_MOD', '','org.openiam.idm.srvc.auth.spi.DefaultLoginModule');
insert into POLICY_ATTRIBUTE (POLICY_ATTR_ID, DEF_PARAM_ID, POLICY_ID, NAME, OPERATION,VALUE1)VALUES ('4109','159', '4001', 'TOKEN_LIFE', '','30');
insert into POLICY_ATTRIBUTE (POLICY_ATTR_ID, DEF_PARAM_ID, POLICY_ID, NAME, OPERATION,VALUE1)VALUES ('4110','162', '4001', 'TOKEN_ISSUER', '','openiam');
insert into POLICY_ATTRIBUTE (POLICY_ATTR_ID, DEF_PARAM_ID, POLICY_ID, NAME, OPERATION,VALUE1)VALUES ('4111','163', '4001', 'LOGIN_MOD_TYPE', '','1');
insert into POLICY_ATTRIBUTE (POLICY_ATTR_ID, DEF_PARAM_ID, POLICY_ID, NAME, OPERATION,VALUE1)VALUES ('4112','164', '4001', 'AUTH_REPOSITORY', '',null);

/* Default Password policy param */
insert into POLICY_ATTRIBUTE (POLICY_ATTR_ID, DEF_PARAM_ID, POLICY_ID, NAME, OPERATION,VALUE1)VALUES ('4000','116', '4000', 'NON_ALPHA_CHARS', 'RANGE',null);
insert into POLICY_ATTRIBUTE (POLICY_ATTR_ID, DEF_PARAM_ID, POLICY_ID, NAME, OPERATION,VALUE1)VALUES ('4001','131', '4000', 'QUEST_SRC', '','USER');
insert into POLICY_ATTRIBUTE (POLICY_ATTR_ID, DEF_PARAM_ID, POLICY_ID, NAME, OPERATION,VALUE1)VALUES ('4004','117', '4000', 'ALPHA_CHARS', 'RANGE','');
insert into POLICY_ATTRIBUTE (POLICY_ATTR_ID, DEF_PARAM_ID, POLICY_ID, NAME, OPERATION, VALUE1) VALUES ('4007', '114', '4000', 'UPPERCASE_CHARS', 'RANGE', null);
insert into POLICY_ATTRIBUTE (POLICY_ATTR_ID, DEF_PARAM_ID, POLICY_ID, NAME, OPERATION, VALUE1) VALUES ('4008', '113', '4000', 'NUMERIC_CHARS', 'RANGE', 1);
insert into POLICY_ATTRIBUTE (POLICY_ATTR_ID, DEF_PARAM_ID, POLICY_ID, NAME, OPERATION, VALUE1) VALUES ('4010', '122', '4000', 'PWD_NAME', 'boolean', null);
insert into POLICY_ATTRIBUTE (POLICY_ATTR_ID, DEF_PARAM_ID, POLICY_ID, NAME, OPERATION, VALUE1,VALUE2) VALUES ('4011', '112', '4000', 'PWD_LEN', 'RANGE', 8,12);
insert into POLICY_ATTRIBUTE (POLICY_ATTR_ID, DEF_PARAM_ID, POLICY_ID, NAME, OPERATION, VALUE1) VALUES ('4012', '120', '4000', 'DICTIONARY_CHECK', 'boolean', null);
insert into POLICY_ATTRIBUTE (POLICY_ATTR_ID, DEF_PARAM_ID, POLICY_ID, NAME, OPERATION, VALUE1) VALUES ('4013', '110', '4000', 'PWD_HIST_VER', null, 6);
insert into POLICY_ATTRIBUTE (POLICY_ATTR_ID, DEF_PARAM_ID, POLICY_ID, NAME, OPERATION, VALUE1) VALUES ('4015', '132', '4000', 'QUEST_LIST', null, null);
insert into POLICY_ATTRIBUTE (POLICY_ATTR_ID, DEF_PARAM_ID, POLICY_ID, NAME, OPERATION, VALUE1) VALUES ('4016', '130', '4000', 'QUEST_COUNT', null, 3);
insert into POLICY_ATTRIBUTE (POLICY_ATTR_ID, DEF_PARAM_ID, POLICY_ID, NAME, OPERATION, VALUE1) VALUES ('4017', '121', '4000', 'PWD_LOGIN', 'boolean', null);
insert into POLICY_ATTRIBUTE (POLICY_ATTR_ID, DEF_PARAM_ID, POLICY_ID, NAME, OPERATION, VALUE1) VALUES ('4019', '115', '4000', 'LOWERCASE_CHARS', 'RANGE', null);
insert into POLICY_ATTRIBUTE (POLICY_ATTR_ID, DEF_PARAM_ID, POLICY_ID, NAME, OPERATION, VALUE1) VALUES ('4020', '129', '4000', 'PWD_EXP_WARN', null, 7);
insert into POLICY_ATTRIBUTE (POLICY_ATTR_ID, DEF_PARAM_ID, POLICY_ID, NAME, OPERATION, VALUE1) VALUES ('4023', '111', '4000', 'PWD_EXPIRATION', null, 90);
insert into POLICY_ATTRIBUTE (POLICY_ATTR_ID, DEF_PARAM_ID, POLICY_ID, NAME, OPERATION, VALUE1) VALUES ('4054', '141', '4000', 'PWD_EXPIRATION_ON_RESET', null, 2);
insert into POLICY_ATTRIBUTE (POLICY_ATTR_ID, DEF_PARAM_ID, POLICY_ID, NAME, OPERATION, VALUE1) VALUES ('4050', '133', '4000', 'PWD_EXP_GRACE', null, 5);
insert into POLICY_ATTRIBUTE (POLICY_ATTR_ID, DEF_PARAM_ID, POLICY_ID, NAME, OPERATION, VALUE1) VALUES ('4051', '134', '4000', 'CHNG_PSWD_ON_RESET', null, 1);

insert into POLICY_ATTRIBUTE (POLICY_ATTR_ID, DEF_PARAM_ID, POLICY_ID, NAME, OPERATION, VALUE1) VALUES ('4052', '135', '4000', 'INITIAL_PSWD_FIXED', null, 1);
insert into POLICY_ATTRIBUTE (POLICY_ATTR_ID, DEF_PARAM_ID, POLICY_ID, NAME, OPERATION, VALUE1) VALUES ('4053', '136', '4000', 'INITIAL_PSWD_VALUE', null, 'passwd00');
insert into POLICY_ATTRIBUTE (POLICY_ATTR_ID, DEF_PARAM_ID, POLICY_ID, NAME, OPERATION, VALUE1) VALUES ('4055', '142', '4000', 'PASSWORD_CHANGE_ALLOWED', null, 1);
insert into POLICY_ATTRIBUTE (POLICY_ATTR_ID, DEF_PARAM_ID, POLICY_ID, NAME, OPERATION, VALUE1) VALUES ('4056', '170', '4000', 'REJECT_CHARS_IN_PSWD', null, '<>');
insert into POLICY_ATTRIBUTE (POLICY_ATTR_ID, DEF_PARAM_ID, POLICY_ID, NAME, OPERATION, VALUE1) VALUES ('4057', '171', '4000', 'QUEST_ANSWER_CORRECT', null, 3);

INSERT INTO POLICY_OBJECT_ASSOC (POLICY_OBJECT_ID, POLICY_ID, ASSOCIATION_LEVEL, ASSOCIATION_VALUE) VALUES ('1100', '4000', 'GLOBAL', 'GLOBAL');



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
INSERT INTO IDENTITY_QUESTION(IDENTITY_QUESTION_ID, IDENTITY_QUEST_GRP_ID, QUESTION_TEXT, REQUIRED) VALUES('212','GLOBAL','What is the name of your favorite movie, play or song?',0);
INSERT INTO IDENTITY_QUESTION(IDENTITY_QUESTION_ID, IDENTITY_QUEST_GRP_ID, QUESTION_TEXT, REQUIRED) VALUES('213','GLOBAL','What is the title of your favorite book?',0);
INSERT INTO IDENTITY_QUESTION(IDENTITY_QUESTION_ID, IDENTITY_QUEST_GRP_ID, QUESTION_TEXT, REQUIRED) VALUES('214','GLOBAL','What is the name of your first boy or girl friend?',0);
INSERT INTO IDENTITY_QUESTION(IDENTITY_QUESTION_ID, IDENTITY_QUEST_GRP_ID, QUESTION_TEXT, REQUIRED) VALUES('215','GLOBAL','What is the name of your favorite school teacher?',0);
INSERT INTO IDENTITY_QUESTION(IDENTITY_QUESTION_ID, IDENTITY_QUEST_GRP_ID, QUESTION_TEXT, REQUIRED) VALUES('216','GLOBAL','What is the year and nick name of your first car (Year-Name)?',0);
INSERT INTO IDENTITY_QUESTION(IDENTITY_QUESTION_ID, IDENTITY_QUEST_GRP_ID, QUESTION_TEXT, REQUIRED) VALUES('217','GLOBAL','Where did you meet you significant other?',0);
INSERT INTO IDENTITY_QUESTION(IDENTITY_QUESTION_ID, IDENTITY_QUEST_GRP_ID, QUESTION_TEXT, REQUIRED) VALUES('218','GLOBAL','What is the type and name of your first pet (Type-Name)?',0);

insert into PROVISION_CONNECTOR(CONNECTOR_ID, NAME, METADATA_TYPE_ID, SERVICE_URL,SERVICE_NAMESPACE, SERVICE_PORT) VALUES ('51','LDAP CONNECTOR', 'LDAP_Connector','localhost:8080/openiam-idm-esb/idmsrvc/LDAPConnectorService','http://www.openiam.org/service/connector', 'LDAPConnectorServicePort' );
insert into PROVISION_CONNECTOR(CONNECTOR_ID, NAME, METADATA_TYPE_ID, SERVICE_URL,SERVICE_NAMESPACE, SERVICE_PORT) VALUES ('54','Google Apps CONNECTOR', 'GOOGLE_Connector','localhost:8080/openiam-idm-esb/idmsrvc/GoogleAppsConnectorService','http://www.openiam.org/service/connector', 'GoogleAppsConnectorServicePort' );
insert into PROVISION_CONNECTOR(CONNECTOR_ID, NAME, METADATA_TYPE_ID, SERVICE_URL,SERVICE_NAMESPACE, SERVICE_PORT) VALUES ('61','Application Tables CONNECTOR', 'DB_Connector','localhost:8080/openiam-idm-esb/idmsrvc/ApplicationTablesConnector','http://www.openiam.org/service/connector', 'NA' );



INSERT INTO MANAGED_SYS (MANAGED_SYS_ID, NAME, DESCRIPTION, STATUS, CONNECTOR_ID, DOMAIN_ID, HOST_URL, PORT, COMM_PROTOCOL, RESOURCE_ID) VALUES('0','OPENIAM', 'Primary IDENTITY', 'ACTIVE', null, 'USR_SEC_DOMAIN', null, null, null, '0'   );
INSERT INTO MANAGED_SYS (MANAGED_SYS_ID, NAME, DESCRIPTION, STATUS, CONNECTOR_ID, DOMAIN_ID, HOST_URL, PORT, COMM_PROTOCOL, RESOURCE_ID) VALUES('101','OPENIAM_LDAP', 'Primary Directory', 'ACTIVE', '51', 'USR_SEC_DOMAIN', 'ldap://localhost','389', 'SSL', '101'   );
INSERT INTO MANAGED_SYS (MANAGED_SYS_ID, NAME, DESCRIPTION, STATUS, CONNECTOR_ID, DOMAIN_ID, HOST_URL, PORT, COMM_PROTOCOL, RESOURCE_ID) VALUES('103','Google Apps Connector', 'Google Apps Connector', 'ACTIVE', '54', 'USR_SEC_DOMAIN', '', null,'CLEAR', '103'   );



INSERT INTO MANAGED_SYS (MANAGED_SYS_ID, NAME, DESCRIPTION, STATUS, CONNECTOR_ID, DOMAIN_ID,   RESOURCE_ID) VALUES('200','SELFSERVICE_NEWHIRE', 'New Hire Form', 'ACTIVE', null, null,  '200'   );
INSERT INTO MANAGED_SYS (MANAGED_SYS_ID, NAME, DESCRIPTION, STATUS, CONNECTOR_ID, DOMAIN_ID,   RESOURCE_ID) VALUES('201','SELFSERVICE_CREATEREQUEST', 'Create Request Form', 'ACTIVE', null, null,  '201'   );
INSERT INTO MANAGED_SYS (MANAGED_SYS_ID, NAME, DESCRIPTION, STATUS, CONNECTOR_ID, DOMAIN_ID,   RESOURCE_ID) VALUES('202','SELFSERVICE_PROFILE', 'Profile Form', 'ACTIVE', null, null,  '202'   );


INSERT INTO RESOURCE_TYPE (RESOURCE_TYPE_ID, DESCRIPTION, METADATA_TYPE_ID, PROVISION_RESOURCE) VALUES('AUTH_REPO', 'Authentication Repository', 'AUTH_REPO', null);
INSERT INTO RESOURCE_TYPE (RESOURCE_TYPE_ID, DESCRIPTION, METADATA_TYPE_ID, PROVISION_RESOURCE) VALUES('MANAGED_SYS', 'Managed Systems', 'MANAGED_SYSTEM', 1);
INSERT INTO RESOURCE_TYPE (RESOURCE_TYPE_ID, DESCRIPTION, METADATA_TYPE_ID, PROVISION_RESOURCE) VALUES('SYS_ACTION', 'System Action', 'SYS_ACTION', 0);
INSERT INTO RESOURCE_TYPE (RESOURCE_TYPE_ID, DESCRIPTION, METADATA_TYPE_ID, PROVISION_RESOURCE) VALUES('OTHER_NONPROV', 'Non-Provisionable Resource', 'MANAGED_SYSTEM', 0);
INSERT INTO RESOURCE_TYPE (RESOURCE_TYPE_ID, DESCRIPTION, METADATA_TYPE_ID, PROVISION_RESOURCE) VALUES('WEBSERVICE', 'Web Services', 'WEBSERVICE', 0);
INSERT INTO RESOURCE_TYPE (RESOURCE_TYPE_ID, DESCRIPTION, METADATA_TYPE_ID, PROVISION_RESOURCE) VALUES('NO-PROVISION-APP', 'Un-provisionable Apps', 'NO-PROVISION-APP', 0);

insert into RES (RESOURCE_ID, RESOURCE_TYPE_ID,NAME, DISPLAY_ORDER) VALUES ('0', 'MANAGED_SYS', 'OPENIAM', 1);
insert into RES (RESOURCE_ID, RESOURCE_TYPE_ID,NAME, DISPLAY_ORDER, MANAGED_SYS_ID ) VALUES ('101', 'MANAGED_SYS', 'LDAP', 2, '101');
insert into RES (RESOURCE_ID, RESOURCE_TYPE_ID,NAME, DISPLAY_ORDER, MANAGED_SYS_ID ) VALUES ('103', 'MANAGED_SYS', 'Google App Con', 3, '103');


insert into RES (RESOURCE_ID, RESOURCE_TYPE_ID,NAME, DISPLAY_ORDER, DESCRIPTION) VALUES ('252', 'SYS_ACTION', 'SELFSERVICE_TERMINATE', 3,'TERMINATE USER');
insert into RES (RESOURCE_ID, RESOURCE_TYPE_ID,NAME, DISPLAY_ORDER, DESCRIPTION) VALUES ('253', 'SYS_ACTION', 'SELFSERVICE_CHANGEROLE', 4,'CHANGE USER ROLE');
insert into RES (RESOURCE_ID, RESOURCE_TYPE_ID,NAME, DISPLAY_ORDER, DESCRIPTION) VALUES ('254', 'SYS_ACTION', 'SELFSERVICE_NEWUSER', 5, 'NEW USER');
insert into RES (RESOURCE_ID, RESOURCE_TYPE_ID,NAME, DISPLAY_ORDER, DESCRIPTION) VALUES ('255', 'SYS_ACTION', 'SELFSERVICE_SELFREGISTER', 5, 'SELF REGISTRATION');


/* Indicates that the user object should not be sent to the AD and LDAP connectors */
insert into RESOURCE_PROP(RESOURCE_PROP_ID, RESOURCE_ID, NAME, PROP_VALUE) VALUES ('200', '101','INCLUDE_USER_OBJECT', 'N');


insert into RESOURCE_PROP(RESOURCE_PROP_ID, RESOURCE_ID, NAME, PROP_VALUE) VALUES ('202', '101','PRINCIPAL_NAME', 'uid');
insert into RESOURCE_PROP(RESOURCE_PROP_ID, RESOURCE_ID, NAME, PROP_VALUE) VALUES ('203', '101','PRINCIPAL_PASSWORD', 'userPassword');
insert into RESOURCE_PROP(RESOURCE_PROP_ID, RESOURCE_ID, NAME, PROP_VALUE) VALUES ('204', '101','INCLUDE_IN_PASSWORD_SYNC', 'Y');



INSERT INTO APPROVER_ASSOC(APPROVER_ASSOC_ID, REQUEST_TYPE, APPROVER_LEVEL, ASSOCIATION_TYPE)  VALUES ('100', 'NEW_HIRE', 1, 'SUPERVISOR');
INSERT INTO APPROVER_ASSOC(APPROVER_ASSOC_ID, REQUEST_TYPE, APPROVER_LEVEL, ASSOCIATION_TYPE, APPROVER_USER_ID)  VALUES ('101', '254', 1, 'USER','3000');


insert into MNG_SYS_OBJECT_MATCH(OBJECT_SEARCH_ID, MANAGED_SYS_ID, OBJECT_TYPE, MATCH_METHOD, SEARCH_FILTER, BASE_DN, SEARCH_BASE_DN ,KEY_FIELD) VALUES('100', '101', 'USER', 'BASE_DN', '(&(objectclass=inetOrgPerson)(?))','ou=people,dc=openiam,dc=org','dc=openiam,dc=org','uid');

/* MAP FOR openiam repository */
INSERT INTO ATTRIBUTE_MAP(ATTRIBUTE_MAP_ID, MANAGED_SYS_ID, RESOURCE_ID, MAP_FOR_OBJECT_TYPE, ATTRIBUTE_NAME, ATTRIBUTE_POLICY_ID) VALUES ('230', '0', '0','PRINCIPAL', 'PRINCIPAL', '4562');
INSERT INTO ATTRIBUTE_MAP(ATTRIBUTE_MAP_ID, MANAGED_SYS_ID, RESOURCE_ID, MAP_FOR_OBJECT_TYPE, ATTRIBUTE_NAME, ATTRIBUTE_POLICY_ID) VALUES ('231', '0', '0','PRINCIPAL', 'PASSWORD', '4563');
INSERT INTO ATTRIBUTE_MAP(ATTRIBUTE_MAP_ID, MANAGED_SYS_ID, RESOURCE_ID, MAP_FOR_OBJECT_TYPE, ATTRIBUTE_NAME, ATTRIBUTE_POLICY_ID) VALUES ('232', '0', '0','EMAIL', 'EMAIL', '4564');
INSERT INTO ATTRIBUTE_MAP(ATTRIBUTE_MAP_ID, MANAGED_SYS_ID, RESOURCE_ID, MAP_FOR_OBJECT_TYPE, ATTRIBUTE_NAME, ATTRIBUTE_POLICY_ID) VALUES ('233', '0', '0','PRINCIPAL', 'DOMAIN', '4565');

/* MAP FOR LDAP */

INSERT INTO ATTRIBUTE_MAP(ATTRIBUTE_MAP_ID, MANAGED_SYS_ID, RESOURCE_ID, MAP_FOR_OBJECT_TYPE, ATTRIBUTE_NAME, ATTRIBUTE_POLICY_ID) VALUES ('201', '101', '101','USER', 'cn', '4501');
INSERT INTO ATTRIBUTE_MAP(ATTRIBUTE_MAP_ID, MANAGED_SYS_ID, RESOURCE_ID, MAP_FOR_OBJECT_TYPE, ATTRIBUTE_NAME, ATTRIBUTE_POLICY_ID) VALUES ('202', '101', '101','USER', 'mail', '4502');
INSERT INTO ATTRIBUTE_MAP(ATTRIBUTE_MAP_ID, MANAGED_SYS_ID, RESOURCE_ID, MAP_FOR_OBJECT_TYPE, ATTRIBUTE_NAME, ATTRIBUTE_POLICY_ID) VALUES ('203', '101', '101','USER', 'o', '4503');
INSERT INTO ATTRIBUTE_MAP(ATTRIBUTE_MAP_ID, MANAGED_SYS_ID, RESOURCE_ID, MAP_FOR_OBJECT_TYPE, ATTRIBUTE_NAME, ATTRIBUTE_POLICY_ID) VALUES ('204', '101', '101','USER', 'ou', '4504');
INSERT INTO ATTRIBUTE_MAP(ATTRIBUTE_MAP_ID, MANAGED_SYS_ID, RESOURCE_ID, MAP_FOR_OBJECT_TYPE, ATTRIBUTE_NAME, ATTRIBUTE_POLICY_ID) VALUES ('205', '101', '101','USER', 'postalCode', '4505');
INSERT INTO ATTRIBUTE_MAP(ATTRIBUTE_MAP_ID, MANAGED_SYS_ID, RESOURCE_ID, MAP_FOR_OBJECT_TYPE, ATTRIBUTE_NAME, ATTRIBUTE_POLICY_ID) VALUES ('206', '101', '101','USER', 'sn', '4506');
INSERT INTO ATTRIBUTE_MAP(ATTRIBUTE_MAP_ID, MANAGED_SYS_ID, RESOURCE_ID, MAP_FOR_OBJECT_TYPE, ATTRIBUTE_NAME, ATTRIBUTE_POLICY_ID) VALUES ('207', '101', '101','USER', 'st', '4507');
INSERT INTO ATTRIBUTE_MAP(ATTRIBUTE_MAP_ID, MANAGED_SYS_ID, RESOURCE_ID, MAP_FOR_OBJECT_TYPE, ATTRIBUTE_NAME, ATTRIBUTE_POLICY_ID) VALUES ('208', '101', '101','USER', 'street', '4508');
INSERT INTO ATTRIBUTE_MAP(ATTRIBUTE_MAP_ID, MANAGED_SYS_ID, RESOURCE_ID, MAP_FOR_OBJECT_TYPE, ATTRIBUTE_NAME, ATTRIBUTE_POLICY_ID) VALUES ('209', '101', '101','USER', 'userPassword', '4509');
INSERT INTO ATTRIBUTE_MAP(ATTRIBUTE_MAP_ID, MANAGED_SYS_ID, RESOURCE_ID, MAP_FOR_OBJECT_TYPE, ATTRIBUTE_NAME, ATTRIBUTE_POLICY_ID) VALUES ('210', '101', '101','USER', 'postalAddress', '4510');
INSERT INTO ATTRIBUTE_MAP(ATTRIBUTE_MAP_ID, MANAGED_SYS_ID, RESOURCE_ID, MAP_FOR_OBJECT_TYPE, ATTRIBUTE_NAME, ATTRIBUTE_POLICY_ID) VALUES ('211', '101', '101','USER', 'telephoneNumber', '4511');
INSERT INTO ATTRIBUTE_MAP(ATTRIBUTE_MAP_ID, MANAGED_SYS_ID, RESOURCE_ID, MAP_FOR_OBJECT_TYPE, ATTRIBUTE_NAME, ATTRIBUTE_POLICY_ID) VALUES ('212', '101', '101','USER', 'facsimileTelephoneNumber', '4512');
INSERT INTO ATTRIBUTE_MAP(ATTRIBUTE_MAP_ID, MANAGED_SYS_ID, RESOURCE_ID, MAP_FOR_OBJECT_TYPE, ATTRIBUTE_NAME, ATTRIBUTE_POLICY_ID) VALUES ('213', '101', '101','USER', 'mobileTelephoneNumber', '4513');
INSERT INTO ATTRIBUTE_MAP(ATTRIBUTE_MAP_ID, MANAGED_SYS_ID, RESOURCE_ID, MAP_FOR_OBJECT_TYPE, ATTRIBUTE_NAME, ATTRIBUTE_POLICY_ID) VALUES ('215', '101', '101','PRINCIPAL', 'uid', '4515');
INSERT INTO ATTRIBUTE_MAP(ATTRIBUTE_MAP_ID, MANAGED_SYS_ID, RESOURCE_ID, MAP_FOR_OBJECT_TYPE, ATTRIBUTE_NAME, ATTRIBUTE_POLICY_ID) VALUES ('216', '101', '101','USER', 'departmentNumber', '4516');
INSERT INTO ATTRIBUTE_MAP(ATTRIBUTE_MAP_ID, MANAGED_SYS_ID, RESOURCE_ID, MAP_FOR_OBJECT_TYPE, ATTRIBUTE_NAME, ATTRIBUTE_POLICY_ID) VALUES ('217', '101', '101','USER', 'displayName', '4517');
INSERT INTO ATTRIBUTE_MAP(ATTRIBUTE_MAP_ID, MANAGED_SYS_ID, RESOURCE_ID, MAP_FOR_OBJECT_TYPE, ATTRIBUTE_NAME, ATTRIBUTE_POLICY_ID) VALUES ('218', '101', '101','USER', 'employeeType', '4518');
INSERT INTO ATTRIBUTE_MAP(ATTRIBUTE_MAP_ID, MANAGED_SYS_ID, RESOURCE_ID, MAP_FOR_OBJECT_TYPE, ATTRIBUTE_NAME, ATTRIBUTE_POLICY_ID) VALUES ('221', '101', '101','USER', 'objectclass', '4521');
INSERT INTO ATTRIBUTE_MAP(ATTRIBUTE_MAP_ID, MANAGED_SYS_ID, RESOURCE_ID, MAP_FOR_OBJECT_TYPE, ATTRIBUTE_NAME, ATTRIBUTE_POLICY_ID) VALUES ('222', '101', '101','USER', 'title', '4522');

/* MAP FOR GOOGLE APPS*/
INSERT INTO ATTRIBUTE_MAP(ATTRIBUTE_MAP_ID, MANAGED_SYS_ID, RESOURCE_ID, MAP_FOR_OBJECT_TYPE, ATTRIBUTE_NAME, ATTRIBUTE_POLICY_ID) VALUES ('400', '103', '103','PRINCIPAL', 'userName', '4600');
INSERT INTO ATTRIBUTE_MAP(ATTRIBUTE_MAP_ID, MANAGED_SYS_ID, RESOURCE_ID, MAP_FOR_OBJECT_TYPE, ATTRIBUTE_NAME, ATTRIBUTE_POLICY_ID) VALUES ('401', '103', '103','USER', 'firstName', '4601');
INSERT INTO ATTRIBUTE_MAP(ATTRIBUTE_MAP_ID, MANAGED_SYS_ID, RESOURCE_ID, MAP_FOR_OBJECT_TYPE, ATTRIBUTE_NAME, ATTRIBUTE_POLICY_ID) VALUES ('402', '103', '103','USER', 'lastName', '4602');
INSERT INTO ATTRIBUTE_MAP(ATTRIBUTE_MAP_ID, MANAGED_SYS_ID, RESOURCE_ID, MAP_FOR_OBJECT_TYPE, ATTRIBUTE_NAME, ATTRIBUTE_POLICY_ID) VALUES ('403', '103', '103','USER', 'password', '4603');



/* Batch Tasks */
insert into BATCH_CONFIG(TASK_ID, TASK_NAME, FREQUENCY_UNIT_OF_MEASURE, ENABLED, TASK_URL, EXECUTION_ORDER) VALUES('100','ACCOUNT_LOCKED_NOTIFICATION', 'MINUTE','0','batch/accountLockedNotify.groovy','2');
insert into BATCH_CONFIG(TASK_ID, TASK_NAME, FREQUENCY_UNIT_OF_MEASURE, ENABLED, TASK_URL, EXECUTION_ORDER) VALUES('101','INACTIVE_USER', 'NIGHTLY','0','batch/inactiveUser.groovy','1');
insert into BATCH_CONFIG(TASK_ID, TASK_NAME, FREQUENCY_UNIT_OF_MEASURE, ENABLED, TASK_URL, EXECUTION_ORDER) VALUES('102','PASSWORD_NEAR_EXP', 'NIGHTLY','0','batch/passwordExpirationNotification.groovy','2');
insert into BATCH_CONFIG(TASK_ID, TASK_NAME, FREQUENCY_UNIT_OF_MEASURE, ENABLED, TASK_URL, EXECUTION_ORDER) VALUES('103','ACCOUNT_TERMINATED', 'NIGHTLY','0','batch/terminateOnExpiration.groovy','3');
insert into BATCH_CONFIG(TASK_ID, TASK_NAME, FREQUENCY_UNIT_OF_MEASURE, ENABLED, TASK_URL, EXECUTION_ORDER) VALUES('104','AUTO_UNLOCK', 'MINUTE','0','batch/autoUnlock.groovy','1');
insert into BATCH_CONFIG(TASK_ID, TASK_NAME, FREQUENCY_UNIT_OF_MEASURE, ENABLED, TASK_URL, EXECUTION_ORDER) VALUES('105','PASSWORD_EXPIRED', 'NIGHTLY','0','batch/pswdExpired.groovy','1');


commit;