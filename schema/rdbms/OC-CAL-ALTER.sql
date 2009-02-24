ALTER TABLE openiam.METADATA_TYPE
 ADD ACTIVE INT AFTER DESCRIPTION,
 ADD SYNC_MANAGED_SYS INT;

ALTER TABLE openiam.METADATA_ELEMENT
 ADD SELF_EDITABLE INT AFTER REQUIRED,
 ADD SELF_VIEWABLE INT;

ALTER TABLE openiam.METADATA_ELEMENT
 ADD UI_TYPE INT AFTER SELF_VIEWABLE,
 ADD UI_OBJECT_SIZE VARCHAR(40) NULL AFTER UI_TYPE,
 ADD VALUE_SRC INT;


ALTER TABLE email_address
 ADD NAME VARCHAR(40) AFTER PARENT_TYPE;
ALTER TABLE openiam.phone
 ADD NAME VARCHAR(40) AFTER PARENT_TYPE;
ALTER TABLE openiam.address
 ADD NAME VARCHAR(40) AFTER ADDRESS7;

ALTER TABLE USERS
 DROP FOREIGN KEY FK_USERS_METADATA_TYPE,
 CHANGE DEPT DEPT_CD VARCHAR(50),
 CHANGE LOCATION_ID LOCATION_CD VARCHAR(50),
 ADD MAIL_CODE VARCHAR(50) AFTER LAST_DATE,
 ADD DEPT_NAME VARCHAR(100),
 ADD LOCATION_NAME VARCHAR(100);

tony.lucich@ceoit.ocgov.com


 ALTER TABLE occal.users
 ADD DEPT VARCHAR(20) AFTER LOCATION_NAME;

 update USERS
 SET TITLE = 'CISO / Enterprise Architect' ,
 	DEPT_NAME = 'CEO',
	LOCATION_NAME = '10 210A',
	MANAGER_ID = 'Patel, Mahesh'
 where USER_ID = '3007';

 COMMIT;

insert into PHONE (PHONE_ID, AREA_CD, NAME, PHONE_NBR, PARENT_ID, PARENT_TYPE) VALUES ('2100','714','Desk Phone', '834-2040', '3007', 'USER');
insert into PHONE (PHONE_ID, AREA_CD, NAME, PHONE_NBR, PARENT_ID, PARENT_TYPE) VALUES ('2101','714','Alt. Cell Phone', '834-7000', '3007', 'USER');
insert into PHONE (PHONE_ID, AREA_CD, NAME, PHONE_NBR, PARENT_ID, PARENT_TYPE) VALUES ('2102','714','Personal Cell Phone', '240-1291', '3007', 'USER');
insert into PHONE (PHONE_ID, AREA_CD, NAME, PHONE_NBR, PARENT_ID, PARENT_TYPE) VALUES ('2103','714','Fax', '834-7015', '3007', 'USER');

INSERT INTO USER_ATTRIBUTES(ID, USER_ID, METADATA_ID, NAME, VALUE) VALUES(200, '3007', '101', 'APPS_NAME', '');
INSERT INTO USER_ATTRIBUTES(ID, USER_ID, METADATA_ID, NAME, VALUE) VALUES(201, '3007', '104', 'APP_URL', '');
INSERT INTO USER_ATTRIBUTES(ID, USER_ID, METADATA_ID, NAME, VALUE) VALUES(202, '3007', '105', 'APPS_UID', 'Lucich.Tony01');
INSERT INTO USER_ATTRIBUTES(ID, USER_ID, METADATA_ID, NAME, VALUE) VALUES(203, '3007', '106', 'APPS_PASSWORD', 'TLucich33904');
INSERT INTO USER_ATTRIBUTES(ID, USER_ID, METADATA_ID, NAME, VALUE) VALUES(204, '3007', '107', 'CAPS_ENABLED', '');

commit;

commit;


ALTER TABLE USERS
 ADD CONSTRAINT FK_USERS_METADATA_TYPE FOREIGN KEY (TYPE_ID) REFERENCES openiam.metadata_type (TYPE_ID) ON UPDATE RESTRICT ON DELETE RESTRICT;



insert into category (category_id, parent_id, category_name, show_list) values ('ORG_TYPE', 'ROOT', 'Org Types',0);
insert into METADATA_TYPE(TYPE_ID, DESCRIPTION) values('Agency','Agency');
insert into category_type (category_id, type_id) values('ORG_TYPE','Agency');


insert into COMPANY(COMPANY_ID, COMPANY_NAME, STATUS,TYPE_ID) VALUES ('003','AUDITOR-CONTROLLER','ACTIVE', 'AGENCY');
insert into COMPANY(COMPANY_ID, COMPANY_NAME, STATUS, TYPE_ID) VALUES ('005','BOARD OF SUPERVISORS','ACTIVE', 'AGENCY');
insert into COMPANY(COMPANY_ID, COMPANY_NAME, STATUS, TYPE_ID) VALUES ('014','CAPS PROGRAM','ACTIVE', 'AGENCY');
insert into COMPANY(COMPANY_ID, COMPANY_NAME, STATUS, TYPE_ID) VALUES ('26','DISTRICT ATTORNEY','ACTIVE', 'AGENCY');

COMMIT;

insert into USERS (user_id,first_name, last_name, STATUS, COMPANY_ID  ) values('3100','Trey','Parker','APPROVED','100');
insert into USERS (user_id,first_name, last_name, STATUS, COMPANY_ID  ) values('3101','Matt','Stone','APPROVED','100');
insert into USERS (user_id,first_name, last_name, STATUS, COMPANY_ID  ) values('3102','Isaac','Hayes','APPROVED','100');
insert into USERS (user_id,first_name, last_name, STATUS, COMPANY_ID  ) values('3103','Eliza','Smith','APPROVED','100');
insert into USERS (user_id,first_name, last_name, STATUS, COMPANY_ID  ) values('3104','Mona','Marshall','APPROVED','100');

insert into USER_GRP (grp_id, user_id) 	values('END_USER_GRP','3100');
insert into USER_GRP (grp_id, user_id) 	values('END_USER_GRP','3101');
insert into USER_GRP (grp_id, user_id) 	values('END_USER_GRP','3102');
insert into USER_GRP (grp_id, user_id) 	values('END_USER_GRP','3103');
insert into USER_GRP (grp_id, user_id) 	values('END_USER_GRP','3104');

insert into LOGIN(SERVICE_ID, LOGIN, MANAGED_SYS_ID, USER_ID, PASSWORD,RESET_PWD, IS_LOCKED, AUTH_FAIL_COUNT) VALUES('USR_SEC_DOMAIN','trey@ceoit.ocgov.com','USR_SEC_DOMAIN','3100','b83a81d1b3f5f209a70ec02c3d7f7fc5',0,0,0);
insert into LOGIN(SERVICE_ID, LOGIN, MANAGED_SYS_ID, USER_ID, PASSWORD,RESET_PWD, IS_LOCKED, AUTH_FAIL_COUNT) VALUES('USR_SEC_DOMAIN','matt@ceoit.ocgov.com','USR_SEC_DOMAIN','3101','b83a81d1b3f5f209a70ec02c3d7f7fc5',0,0,0);
insert into LOGIN(SERVICE_ID, LOGIN, MANAGED_SYS_ID, USER_ID, PASSWORD,RESET_PWD, IS_LOCKED, AUTH_FAIL_COUNT) VALUES('USR_SEC_DOMAIN','issac@ceoit.ocgov.com','USR_SEC_DOMAIN','3102','b83a81d1b3f5f209a70ec02c3d7f7fc5',0,0,0);
insert into LOGIN(SERVICE_ID, LOGIN, MANAGED_SYS_ID, USER_ID, PASSWORD,RESET_PWD, IS_LOCKED, AUTH_FAIL_COUNT) VALUES('USR_SEC_DOMAIN','eliza@ceoit.ocgov.com','USR_SEC_DOMAIN','3103','b83a81d1b3f5f209a70ec02c3d7f7fc5',0,0,0);
insert into LOGIN(SERVICE_ID, LOGIN, MANAGED_SYS_ID, USER_ID, PASSWORD,RESET_PWD, IS_LOCKED, AUTH_FAIL_COUNT) VALUES('USR_SEC_DOMAIN','mona@ceoit.ocgov.com','USR_SEC_DOMAIN','3104','b83a81d1b3f5f209a70ec02c3d7f7fc5',0,0,0);


update LOGIN set reset_pwd = 0, is_locked = 0;


INSERT INTO AUTH_STATE(USER_ID, TOKEN, AUTH_STATE, AA, EXPIRATION) values('3100', NULL,0,'OPENIAM',0);
INSERT INTO AUTH_STATE(USER_ID, TOKEN, AUTH_STATE, AA, EXPIRATION) values('3101', NULL,0,'OPENIAM',0);
INSERT INTO AUTH_STATE(USER_ID, TOKEN, AUTH_STATE, AA, EXPIRATION) values('3102', NULL,0,'OPENIAM',0);
INSERT INTO AUTH_STATE(USER_ID, TOKEN, AUTH_STATE, AA, EXPIRATION) values('3102', NULL,0,'OPENIAM',0);
INSERT INTO AUTH_STATE(USER_ID, TOKEN, AUTH_STATE, AA, EXPIRATION) values('3103', NULL,0,'OPENIAM',0);
INSERT INTO AUTH_STATE(USER_ID, TOKEN, AUTH_STATE, AA, EXPIRATION) values('3104', NULL,0,'OPENIAM',0);


/* 1-31-2009 */
ALTER TABLE occal.users
 ADD MAIDEN_NAME VARCHAR(40) AFTER DEPT,
 ADD NICKNAME VARCHAR(40);

ALTER TABLE occal.users
 CHANGE MIDDLE_INIT MIDDLE_INIT VARCHAR(40);

ALTER TABLE openiam.users
 DROP FOREIGN KEY FK_USERS_METADATA_TYPE,
 CHANGE JOB_CODE JOB_CODE VARCHAR(50);
ALTER TABLE openiam.users
 ADD CONSTRAINT FK_USERS_METADATA_TYPE FOREIGN KEY (TYPE_ID) REFERENCES openiam.metadata_type (TYPE_ID) ON UPDATE RESTRICT ON DELETE RESTRICT;



ALTER TABLE occal.users
	ADD PASSWORD_THEME VARCHAR(20) AFTER DEPT;


/* Data for application */ 

insert into ROLE(ROLE_ID, SERVICE_ID, ROLE_NAME) VALUES ('LSA', 'USR_SEC_DOMAIN', 'LSA');
insert into ROLE(ROLE_ID, SERVICE_ID, ROLE_NAME) VALUES ('MANAGER', 'USR_SEC_DOMAIN', 'MANAGER');
insert into ROLE(ROLE_ID, SERVICE_ID, ROLE_NAME) VALUES ('USER', 'USR_SEC_DOMAIN', 'USER');
insert into ROLE(ROLE_ID, SERVICE_ID, ROLE_NAME) VALUES ('CISO', 'USR_SEC_DOMAIN', 'CISO');

insert into ROLE(ROLE_ID, SERVICE_ID, ROLE_NAME) VALUES ('SECURITY', 'USR_SEC_DOMAIN', 'SECURITY');
insert into ROLE(ROLE_ID, SERVICE_ID, ROLE_NAME) VALUES ('HR', 'USR_SEC_DOMAIN', 'HR');

commit;

/* 2/6 ALTER SCHEMA  */

ALTER TABLE ROLE
 DROP FOREIGN KEY FK_ROLE_SERVICE,
 ADD TYPE_ID VARCHAR(20) AFTER PARENT_ROLE_ID;
ALTER TABLE ROLE
 ADD CONSTRAINT FK_ROLE_SERVICE FOREIGN KEY (SERVICE_ID) REFERENCES openiam.security_domain (DOMAIN_ID) ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE openiam.menu
 CHANGE MENU_NAME MENU_NAME VARCHAR(40);



insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, display_order) values('ENT_APPS', 'ROOT' ,'Enterprise Applications','Enterprise Applications','', 'en',0);


insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, display_order) values('CAPS+', 'ENT_APPS' ,'CAPS+','CAPS+','', 'en',1);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, display_order) values('CLS', 'ENT_APPS' ,'CAPS Learning Center (CLC)','CAPS Learning Center (CLC)','http://teamdevts.ocgov.com/caps_learning_center/', 'en',2);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, display_order) values('PTMS-PROTO', 'ENT_APPS' ,'PTMS-Prototype','PTMS-Prototype','http://192.168.52.130/prototype', 'en',3);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, display_order) values('PTMS-FRAME', 'ENT_APPS' ,'PTMS-Framework','PTMS-Framework','http://192.168.52.130/framework', 'en',4);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, display_order) values('PTMS-EFRAME', 'ENT_APPS' ,'PTMS-eFramework','PTMS-eFramework','http://192.168.52.130/eframework', 'en',5);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, display_order) values('EGOV', 'ENT_APPS' ,'eGov','eGov','', 'en',6);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, display_order) values('VTI', 'ENT_APPS' ,'VTI','VTI','http://192.168.124.186/vti/Default.asp?Direct=1', 'en',8);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, display_order) values('CLARITY', 'ENT_APPS' ,'Clarity','Clarity','http://clarity.ocgov.com/niku/app?action=homeActionId', 'en',9); 
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, display_order) values('ONBASE', 'ENT_APPS' ,'On Base','On Base','', 'en',10); 
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, display_order) values('TRAINPARTNERS', 'ENT_APPS' ,'Training Partners','Training Partners','http://www.ocgov.com/hr/trainingpartner/', 'en',11); 
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, display_order) values('SCORECARD', 'ENT_APPS' ,'Balanced Score Card','Balanced Score Card','', 'en',12); 
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, display_order) values('EMPPORTAL', 'ENT_APPS' ,'Employee Portal','Employee Portal','', 'en',13); 
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, display_order) values('AUDREPORTS', 'ENT_APPS' ,'Audit Reports','Audit Reports','', 'en',14); 

commit;


ALTER TABLE openiam.provision_connector
 CHANGE CONNECTOR_ID CONNECTOR_ID VARCHAR(32) NOT NULL;
