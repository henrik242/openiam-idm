
/* Reporting MENU options */
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD) values('PROVREPORT','REPORT','Provisioning Reports','Provisioning Information Reports','', 'en');
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD) values('ACCESSREPORT','REPORT','Access Reports','Access Control Reports','', 'en');
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD) values('OTHERREPORT','REPORT','Misc Reports','Misc Reports','', 'en');


insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, display_order) values('REQUESTAPPRV', 'SELFSERVICE' ,'Request-Approval','Request-Approval','priv/requestIndex.do', 'en',3);


/* Customer portal Request Approval  MENU */

insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, display_order) values('MYWORK', 'REQUESTAPPRV' ,'My Work','My Work','menunav.do', 'en',1);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, display_order) values('TEAMWORK', 'REQUESTAPPRV' ,'Team Work','Team Work','menunav.do', 'en',2);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, display_order) values('MYSETTINGS', 'REQUESTAPPRV' ,'Settings','My Options','menunav.do', 'en',3);

insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, display_order) values('MYTASKS', 'MYWORK' ,'Manage Requests','Manage Requests','requestList.selfserve', 'en',1);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, display_order) values('CREATEREQ', 'MYWORK','New Request','New Request','priv/prov/createRequest.do?method=newRequest', 'en',2);

insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, display_order) values('TEAMTASKS', 'TEAMWORK' ,'Tasks','Tasks','priv/prov/myTeamTasks.do?method=teamview', 'en',3);

insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, display_order) values('DELEGATE', 'MYSETTINGS','Delegate','Delegate','priv/prov/delegateIdmIndex.do?method=view', 'en',2);
insert into MENU (menu_id, menu_group, menu_name,menu_desc,url,LANGUAGE_CD, display_order) values('AVAILABILITY', 'MYSETTINGS','Availability','Availability','priv/prov/availability.do?method=view', 'en',3);



/* service admin role */


INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID) VALUES('REQUESTAPPRV','END_USER');


INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID) VALUES('MYWORK','END_USER');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID) VALUES('TEAMWORK','END_USER');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID) VALUES('MYSETTINGS','END_USER');

INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID) VALUES('MYTASKS','END_USER');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID) VALUES('CREATEREQ','END_USER');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID) VALUES('TEAMTASKS','END_USER');
INSERT INTO PERMISSIONS(MENU_ID,ROLE_ID) VALUES('TEAMCREATEREQ','END_USER');

insert into PROVISION_CONNECTOR(CONNECTOR_ID, NAME, METADATA_TYPE_ID, SERVICE_URL) VALUES ('52','RDBMS CONNECTOR', 'RDBMS_Connector','HTTP://localhost:8080/idm-rdbms-con-ws/' );
insert into PROVISION_CONNECTOR(CONNECTOR_ID, NAME, METADATA_TYPE_ID, SERVICE_URL) VALUES ('53','SOAP CONNECTOR', 'SOAP_Connector','HTTP://localhost:8080/idm-soap-con-ws/' );


commit;