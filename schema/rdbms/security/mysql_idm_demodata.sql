
insert into METADATA_TYPE(TYPE_ID, DESCRIPTION) values('OrgProvider','Provider');
insert into METADATA_TYPE(TYPE_ID, DESCRIPTION) values('OrgBillServ','Billing Service');

insert into category (category_id, parent_id, category_name, show_list) values ('ORG_TYPE', 'ROOT', 'Org Types',0);
insert into category_type (category_id, type_id) values('ORG_TYPE','OrgOpenIAM');
insert into category_type (category_id, type_id) values('ORG_TYPE','OrgProvider');
insert into category_type (category_id, type_id) values('ORG_TYPE','OrgBillServ');

insert into menu (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, DISPLAY_ORDER) values('CLAIMS','ROOT','Claims','Claims','', 'en',5);
insert into menu (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, DISPLAY_ORDER) values('CLM_CREATE','CLAIMS','New Claim','New Claim','menunav.do', 'en',1);
insert into menu (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, DISPLAY_ORDER) values('CLM_SEARCH','CLAIMS','Find Claim','Find Claim','menunav.do', 'en',2);

insert into users (user_id,first_name, last_name, STATUS, COMPANY_ID ) values('3100','John','Smith','APPROVED','100');
insert into users (user_id,first_name, last_name, STATUS, COMPANY_ID ) values('3101','Mary','Blyth','APPROVED','100');
insert into users (user_id,first_name, last_name, STATUS, COMPANY_ID ) values('3102','William','Wallace','APPROVED','100');


insert into users (user_id,first_name, last_name, STATUS, COMPANY_ID ) values('3103','Mary','Jane','APPROVED','100');
insert into users (user_id,first_name, last_name, STATUS, COMPANY_ID ) values('3104','Lucy','Sky','APPROVED','100');


insert into LOGIN(SERVICE_ID, LOGIN, USER_ID, PASSWORD,RESET_PWD, IS_LOCKED, AUTH_FAIL_COUNT, Managed_sys_id) VALUES('USR_SEC_DOMAIN','john.smith','3100','b83a81d1b3f5f209a70ec02c3d7f7fc5',0,0,0,'0');
insert into LOGIN(SERVICE_ID, LOGIN, USER_ID, PASSWORD,RESET_PWD, IS_LOCKED, AUTH_FAIL_COUNT, Managed_sys_id) VALUES('USR_SEC_DOMAIN','mary.blyth','3101','b83a81d1b3f5f209a70ec02c3d7f7fc5',0,0,0,'0');
insert into LOGIN(SERVICE_ID, LOGIN, USER_ID, PASSWORD,RESET_PWD, IS_LOCKED, AUTH_FAIL_COUNT, Managed_sys_id) VALUES('USR_SEC_DOMAIN','william.wallace','3102','b83a81d1b3f5f209a70ec02c3d7f7fc5',0,0,0,'0');

insert into LOGIN(SERVICE_ID, LOGIN, USER_ID, PASSWORD,RESET_PWD, IS_LOCKED, AUTH_FAIL_COUNT, Managed_sys_id) VALUES('USR_SEC_DOMAIN','mary.jane','3103','b83a81d1b3f5f209a70ec02c3d7f7fc5',0,0,0,'0');
insert into LOGIN(SERVICE_ID, LOGIN, USER_ID, PASSWORD,RESET_PWD, IS_LOCKED, AUTH_FAIL_COUNT, Managed_sys_id) VALUES('USR_SEC_DOMAIN','lucy.sky','3104','b83a81d1b3f5f209a70ec02c3d7f7fc5',0,0,0,'0');

insert into grp (grp_id, grp_name)   values('FEE_NEGOTIATION','Fee Negotiation');
insert into grp (grp_id, grp_name)   values('REPRICING','Repricing');
insert into grp (grp_id, grp_name)   values('CUSTOMER_SERV','Customer Service');
insert into grp (grp_id, grp_name)   values('LEGAL','LEGAL');

INSERT INTO ROLE (ROLE_ID,SERVICE_ID,ROLE_NAME) VALUES ('CLAIMS_REPRICER','USR_SEC_DOMAIN','Claims Repricer');
INSERT INTO ROLE (ROLE_ID,SERVICE_ID,ROLE_NAME) VALUES ('FAX_QUE_ROUTING','USR_SEC_DOMAIN','FAX Queue Routing');

INSERT INTO ROLE (ROLE_ID,SERVICE_ID,ROLE_NAME) VALUES ('SERVICE_MGR','USR_SEC_DOMAIN','Service Manager');
INSERT INTO ROLE (ROLE_ID,SERVICE_ID,ROLE_NAME) VALUES ('ELIGIBILITY_VERIF','USR_SEC_DOMAIN','Eligibility Verifier');
INSERT INTO ROLE (ROLE_ID,SERVICE_ID,ROLE_NAME) VALUES ('BILL_SERVICE_MGR','USR_SEC_DOMAIN','Billing Service Manager');
INSERT INTO ROLE (ROLE_ID,SERVICE_ID,ROLE_NAME) VALUES ('CLAIMS_REPRICE_SEN','USR_SEC_DOMAIN','Claims Repricing Senior');
INSERT INTO ROLE (ROLE_ID,SERVICE_ID,ROLE_NAME) VALUES ('CLAIMS_REPRICE_MGR','USR_SEC_DOMAIN','Claims Repricing Manager');
INSERT INTO ROLE (ROLE_ID,SERVICE_ID,ROLE_NAME) VALUES ('CLAIMS_REPRICE_RES','USR_SEC_DOMAIN','Claims Repricing Researcher');

insert into user_grp (grp_id, user_id)     values('CUSTOMER_SERV','3101');
insert into user_grp (grp_id, user_id) 		 values('REPRICING','3102');

INSERT INTO GRP_ROLE(ROLE_ID,GRP_ID, SERVICE_ID) VALUES ('SERVICE_MGR','CUSTOMER_SERV', 'USR_SEC_DOMAIN');
INSERT INTO GRP_ROLE(ROLE_ID,GRP_ID, SERVICE_ID) VALUES ('CLAIMS_REPRICE_MGR','REPRICING', 'USR_SEC_DOMAIN');


insert into CATEGORY(CATEGORY_ID, PARENT_ID, CATEGORY_NAME, SHOW_LIST) VALUES('ACL_SAVILITY','ACL', 'SEC DOMAIN',0);

insert into METADATA_TYPE(TYPE_ID, DESCRIPTION) values('PROXY_URL_TYPE','Proxy URL Type');
insert into metadata_element(metadata_id, type_id, attribute_name) values ('PROXY_URL','PROXY_URL_TYPE', 'PROXY_URL');
insert into metadata_element(metadata_id, type_id, attribute_name) values ('TARGET_URL','PROXY_URL_TYPE', 'TARGET_URL');

insert into category_type (category_id, type_id) values('ACL_SAVILITY','PROXY_URL_TYPE');

insert into METADATA_TYPE(TYPE_ID, DESCRIPTION) values('APPL_TYPE','Application Type');
insert into metadata_element(metadata_id, type_id, attribute_name) values ('APP_NAME','APPL_TYPE', 'APPL_NAME');
insert into metadata_element(metadata_id, type_id, attribute_name) values ('APP_DESC','APPL_TYPE', 'APPL_DESC');
insert into metadata_element(metadata_id, type_id, attribute_name) values ('PRXY_URL','APPL_TYPE', 'PROXY_URL');
insert into metadata_element(metadata_id, type_id, attribute_name) values ('HOST_BASE_URL','APPL_TYPE', 'TARGET_URL');
insert into metadata_element(metadata_id, type_id, attribute_name) values ('APP_TYPE','APPL_TYPE', 'APP_TYPE');

insert into category_type (category_id, type_id) values('ACL_SAVILITY','APPL_TYPE');

insert into METADATA_TYPE(TYPE_ID, DESCRIPTION) values('FIELD_TYPE','JSP FIELD Type');
insert into metadata_element(metadata_id, type_id, attribute_name) values ('FIELD_NAME','FIELD_TYPE', 'FIELD_NAME');
insert into metadata_element(metadata_id, type_id, attribute_name) values ('TAG_TYPE','FIELD_TYPE', 'TAG_TYPE');
insert into metadata_element(metadata_id, type_id, attribute_name) values ('CONTAINER_NAME','FIELD_TYPE', 'CONTAINER_NAME');

insert into category_type (category_id, type_id) values('ACL_SAVILITY','FIELD_TYPE');

insert into RESOURCE_TYPE (RESOURCE_TYPE_ID, DESCRIPTION, METADATA_TYPE_ID) VALUES ('URL','URL Resource','PROXY_URL_TYPE');
insert into RESOURCE_TYPE (RESOURCE_TYPE_ID, DESCRIPTION, METADATA_TYPE_ID) VALUES ('APPLICATION','Application Resource','APPL_TYPE');
insert into RESOURCE_TYPE (RESOURCE_TYPE_ID, DESCRIPTION, METADATA_TYPE_ID) VALUES ('FIELD','UI FIELD','FIELD_TYPE');

insert into RESOURCES(RESOURCE_ID,   RESOURCE_TYPE_ID,  DESCRIPTION, RESOURCE_PARENT, CATEGORY_ID, BRANCH_ID  ) VALUES ('1101','APPLICATION', 'Spring MVC Example',null,'ACL_SAVILITY','1101');
INSERT INTO RESOURCE_PROP(RESOURCE_PROP_ID, RESOURCE_ID, METADATA_ID, PROP_VALUE)  VALUES ('1500','1101','PRXY_URL' ,'/example-spring/*');
INSERT INTO RESOURCE_PROP(RESOURCE_PROP_ID, RESOURCE_ID, METADATA_ID, PROP_VALUE)  VALUES ('1501','1101','HOST_BASE_URL' ,'http://localhost:8080/example-spring/');
INSERT INTO RESOURCE_PROP(RESOURCE_PROP_ID, RESOURCE_ID, METADATA_ID, PROP_VALUE)  VALUES ('1502','1101','APP_TYPE' ,'SPRING MVC');
INSERT INTO RESOURCE_PROP(RESOURCE_PROP_ID, RESOURCE_ID, METADATA_ID, PROP_VALUE)  VALUES ('1503','1101','APP_NAME' ,'Spring MVC Example');
INSERT INTO RESOURCE_PROP(RESOURCE_PROP_ID, RESOURCE_ID, METADATA_ID, PROP_VALUE)  VALUES ('1504','1101','APP_DESC' ,'Sample Web Application');

insert into RESOURCES(RESOURCE_ID,   RESOURCE_TYPE_ID,  DESCRIPTION, RESOURCE_PARENT, CATEGORY_ID, BRANCH_ID  ) VALUES ('1102','URL', 'Claims','1101','ACL_SAVILITY','');
INSERT INTO RESOURCE_PROP(RESOURCE_PROP_ID, RESOURCE_ID, METADATA_ID, PROP_VALUE)  VALUES ('1505','1102','PR0XY_URL' ,'/example-spring/claims/*');
INSERT INTO RESOURCE_PROP(RESOURCE_PROP_ID, RESOURCE_ID, METADATA_ID, PROP_VALUE)  VALUES ('1506','1102','TARGET_URL' ,'http://localhost:8080/example-spring/claims/*');

insert into RESOURCES(RESOURCE_ID,   RESOURCE_TYPE_ID,  DESCRIPTION, RESOURCE_PARENT, CATEGORY_ID, BRANCH_ID  ) VALUES ('1103','URL', 'Profile','1101','ACL_SAVILITY','');
INSERT INTO RESOURCE_PROP(RESOURCE_PROP_ID, RESOURCE_ID, METADATA_ID, PROP_VALUE)  VALUES ('1507','1103','PR0XY_URL' ,'/example-spring/profile/*');
INSERT INTO RESOURCE_PROP(RESOURCE_PROP_ID, RESOURCE_ID, METADATA_ID, PROP_VALUE)  VALUES ('1508','1103','TARGET_URL' ,'http://localhost:8080/example-spring/profile/*');

insert into RESOURCES(RESOURCE_ID,   RESOURCE_TYPE_ID,  DESCRIPTION, RESOURCE_PARENT, CATEGORY_ID, BRANCH_ID  ) VALUES ('1104','URL', 'Search','1101','ACL_SAVILITY','');
INSERT INTO RESOURCE_PROP(RESOURCE_PROP_ID, RESOURCE_ID, METADATA_ID, PROP_VALUE)  VALUES ('1509','1103','PR0XY_URL' ,'/example-spring/search/*');
INSERT INTO RESOURCE_PROP(RESOURCE_PROP_ID, RESOURCE_ID, METADATA_ID, PROP_VALUE)  VALUES ('15010','1103','TARGET_URL' ,'http://localhost:8080/example-spring/reprice/*');

insert into RESOURCES(RESOURCE_ID,   RESOURCE_TYPE_ID,  DESCRIPTION, RESOURCE_PARENT, CATEGORY_ID, BRANCH_ID  ) VALUES ('1105','URL', 'Re-pricer','1101','ACL_SAVILITY','');
INSERT INTO RESOURCE_PROP(RESOURCE_PROP_ID, RESOURCE_ID, METADATA_ID, PROP_VALUE)  VALUES ('15011','1103','PR0XY_URL' ,'/example-spring/reprice/*');
INSERT INTO RESOURCE_PROP(RESOURCE_PROP_ID, RESOURCE_ID, METADATA_ID, PROP_VALUE)  VALUES ('15012','1103','TARGET_URL' ,'http://localhost:8080/example-spring/reprice/*');


update resources
 set display_order = 0, node_level=0;


insert into company(COMPANY_ID,COMPANY_NAME,TYPE_ID) values ('150','General Hospital','OrgProvider');
insert into company(COMPANY_ID,COMPANY_NAME,TYPE_ID) values ('151','St. Marys Hopital','OrgProvider');
insert into company(COMPANY_ID,COMPANY_NAME,TYPE_ID) values ('152','Foundation Pediatrics','OrgProvider');
insert into company(COMPANY_ID,COMPANY_NAME,TYPE_ID) values ('153','Fallon Clinic','OrgProvider');



commit;



insert into METADATA_TYPE(TYPE_ID, DESCRIPTION) values('SERVICE_URL_TYPE','Service URL Type');
insert into metadata_element(metadata_id, type_id, attribute_name) values ('SRV_PROXY_URL','SERVICE_URL_TYPE', 'PROXY_URL');
insert into metadata_element(metadata_id, type_id, attribute_name) values ('SRV_TARGET_URL','SERVICE_URL_TYPE', 'TARGET_URL');


insert into METADATA_TYPE(TYPE_ID, DESCRIPTION) values('SERVICE_OPER_TYPE','Service Operation Type');
insert into metadata_element(metadata_id, type_id, attribute_name) values ('SERV_NAME','SERVICE_OPER_TYPE', 'PROXY_URL');
insert into metadata_element(metadata_id, type_id, attribute_name) values ('OPER_NAME','SERVICE_OPER_TYPE', 'TARGET_URL');

insert into category_type (category_id, type_id) values('ACL_SAVILITY','SERVICE_URL_TYPE');
insert into category_type (category_id, type_id) values('ACL_SAVILITY','SERVICE_OPER_TYPE');


insert into RESOURCE_TYPE (RESOURCE_TYPE_ID, DESCRIPTION, METADATA_TYPE_ID) VALUES ('SERVICE','URL Resource','SERVICE_URL_TYPE');
insert into RESOURCE_TYPE (RESOURCE_TYPE_ID, DESCRIPTION, METADATA_TYPE_ID) VALUES ('OPERATION','Application Resource','SERVICE_OPER_TYPE');

insert into RESOURCES(RESOURCE_ID,   RESOURCE_TYPE_ID,  DESCRIPTION, RESOURCE_PARENT, CATEGORY_ID, BRANCH_ID  ) VALUES ('2101','APPLICATION', 'Business Services',null,'ACL_SAVILITY','2101');
INSERT INTO RESOURCE_PROP(RESOURCE_PROP_ID, RESOURCE_ID, METADATA_ID, PROP_VALUE)  VALUES ('2500','2101','SRV_PROXY_URL' ,'/bus-services/*');
INSERT INTO RESOURCE_PROP(RESOURCE_PROP_ID, RESOURCE_ID, METADATA_ID, PROP_VALUE)  VALUES ('2501','2101','SRV_TARGET_URL' ,'http://10.100.0.25:8080/bus-services/');


insert into RESOURCES(RESOURCE_ID,   RESOURCE_TYPE_ID,  DESCRIPTION, RESOURCE_PARENT, CATEGORY_ID, BRANCH_ID  ) VALUES ('2102','SERVICE', 'User Service','2101','ACL_SAVILITY','');
INSERT INTO RESOURCE_PROP(RESOURCE_PROP_ID, RESOURCE_ID, METADATA_ID, PROP_VALUE)  VALUES ('2505','2102','SRV_PROXY_URL' ,'/bus-services/userMgr/*');
INSERT INTO RESOURCE_PROP(RESOURCE_PROP_ID, RESOURCE_ID, METADATA_ID, PROP_VALUE)  VALUES ('2506','2102','SRV_TARGET_URL' ,'http://10.100.0.25:8080/bus-services/userMgr/*');

insert into RESOURCES(RESOURCE_ID,   RESOURCE_TYPE_ID,  DESCRIPTION, RESOURCE_PARENT, CATEGORY_ID, BRANCH_ID  ) VALUES ('2103','SERVICE', 'Pricing Service','2101','ACL_SAVILITY','');
INSERT INTO RESOURCE_PROP(RESOURCE_PROP_ID, RESOURCE_ID, METADATA_ID, PROP_VALUE)  VALUES ('2507','2103','SRV_PROXY_URL' ,'/bus-services/priceMgr/*');
INSERT INTO RESOURCE_PROP(RESOURCE_PROP_ID, RESOURCE_ID, METADATA_ID, PROP_VALUE)  VALUES ('2508','2103','SRV_TARGET_URL' ,'http://10.100.0.25:8080/bus-services/priceMgr/*');

insert into RESOURCES(RESOURCE_ID,   RESOURCE_TYPE_ID,  DESCRIPTION, RESOURCE_PARENT, CATEGORY_ID, BRANCH_ID  ) VALUES ('2104','OPERATION', 'calculatePrice','2103','ACL_SAVILITY','');
INSERT INTO RESOURCE_PROP(RESOURCE_PROP_ID, RESOURCE_ID, METADATA_ID, PROP_VALUE)  VALUES ('2509','2103','SRV_PROXY_URL' ,'/bus-services/priceMgr/calculatePrice(*)');
INSERT INTO RESOURCE_PROP(RESOURCE_PROP_ID, RESOURCE_ID, METADATA_ID, PROP_VALUE)  VALUES ('2510','2103','SRV_TARGET_URL' ,'http://10.100.0.25:8080/bus-services/priceMgr/calculatePrice(*)');



