
/*Data for authentication tests */
insert into USERS (user_id,first_name, last_name, STATUS, COMPANY_ID ) values('4000','test1','','PENDING_INITIAL_LOGIN','100');
insert into LOGIN(SERVICE_ID, LOGIN, MANAGED_SYS_ID, USER_ID, PASSWORD,RESET_PWD, IS_LOCKED, AUTH_FAIL_COUNT) VALUES('IDM','test1','0','4000','b83a81d1b3f5f209a70ec02c3d7f7fc5',0,0,0);

insert into USERS (user_id,first_name, last_name, STATUS, COMPANY_ID ) values('4001','test2','','INACTIVE','100');
insert into LOGIN(SERVICE_ID, LOGIN, MANAGED_SYS_ID, USER_ID, PASSWORD,RESET_PWD, IS_LOCKED, AUTH_FAIL_COUNT) VALUES('IDM','test2','0','4001','b83a81d1b3f5f209a70ec02c3d7f7fc5',0,0,0);

insert into USERS (user_id,first_name, last_name, STATUS,SECONDARY_STATUS, COMPANY_ID ) values('4002','test3','','ACTIVE','LOCKED','100');
insert into LOGIN(SERVICE_ID, LOGIN, MANAGED_SYS_ID, USER_ID, PASSWORD,RESET_PWD, IS_LOCKED, AUTH_FAIL_COUNT) VALUES('IDM','test3','0','4002','b83a81d1b3f5f209a70ec02c3d7f7fc5',0,0,0);

insert into USERS (user_id,first_name, last_name, STATUS, COMPANY_ID, START_DATE ) values('4004','test5','','PENDING_START_DATE','100', DATE_ADD(CURDATE(), INTERVAL 10 DAY));
insert into LOGIN(SERVICE_ID, LOGIN, MANAGED_SYS_ID, USER_ID, PASSWORD,RESET_PWD, IS_LOCKED, AUTH_FAIL_COUNT) VALUES('IDM','test5','0','4004','b83a81d1b3f5f209a70ec02c3d7f7fc5',0,0,0);


update LOGIN set reset_pwd = 0, is_locked = 0;


commit;