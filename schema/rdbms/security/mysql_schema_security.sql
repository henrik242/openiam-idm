
CREATE TABLE ROLE(
       ROLE_ID              varchar(20) NOT NULL,
       SERVICE_ID           varchar(20) NOT NULL,
       ROLE_NAME            varchar(40) NULL,
       CREATE_DATE			DATETIME NULL,
       CREATED_BY			VARCHAR(20) NULL,
       DESCRIPTION          varchar(255) NULL,
       PROVISION_OBJ_NAME   varchar(80) NULL,
	   PARENT_ROLE_ID		VARCHAR(20) NULL,
	   TYPE_ID					VARCHAR(20) NULL,
      	 PRIMARY KEY (ROLE_ID, SERVICE_ID), 
	CONSTRAINT FK_ROLE_SERVICE
       FOREIGN KEY (SERVICE_ID) REFERENCES SECURITY_DOMAIN(DOMAIN_ID)
);



CREATE TABLE ROLE_ATTRIBUTE (
	   ROLE_ATTR_ID			VARCHAR(20) NOT NULL,
       NAME                 varchar(20) NULL,
       VALUE                varchar(255) NULL,
       METADATA_ID          varchar(20) NULL,
       ROLE_ID        	 	VARCHAR(20) NOT NULL,
	   SERVICE_ID           VARCHAR(20) NOT NULL,
	   ATTR_GROUP						VARCHAR(20) NOT NULL,
       PRIMARY KEY (ROLE_ATTR_ID),
	CONSTRAINT FK_ROLE_ROLE_ATTRIBUTE
       FOREIGN KEY (ROLE_ID,SERVICE_ID)
                             REFERENCES ROLE(ROLE_ID,SERVICE_ID)
);



CREATE TABLE GRP_ROLE(
	   SERVICE_ID           varchar(20) NOT NULL,
	   ROLE_ID              varchar(20) NOT NULL,
       GRP_ID               varchar(20) NOT NULL,
	PRIMARY KEY (SERVICE_ID, ROLE_ID, GRP_ID),
       CONSTRAINT FK_GRP_ROLE_ROLE
       FOREIGN KEY (ROLE_ID,SERVICE_ID)  REFERENCES ROLE(ROLE_ID,SERVICE_ID),
       CONSTRAINT FK_GRP_ROLE_GRP
       FOREIGN KEY (GRP_ID)  REFERENCES GRP(GRP_ID)
);


CREATE TABLE USER_ROLE (
	   SERVICE_ID			VARCHAR(20) NOT NULL,
	   ROLE_ID              VARCHAR(20) NOT NULL,
       USER_ID              varchar(20) NOT NULL,
       PRIMARY KEY (SERVICE_ID, ROLE_ID, USER_ID),
	   CONSTRAINT FK_USR_ROLE_ROLE
        FOREIGN KEY (ROLE_ID,SERVICE_ID)
                             REFERENCES ROLE(ROLE_ID,SERVICE_ID), 
       CONSTRAINT FK_USR_ROLE_USR
		FOREIGN KEY (USER_ID)
                             REFERENCES USERS(USER_ID)
);


CREATE TABLE RESOURCE_TYPE(
       RESOURCE_TYPE_ID     varchar(20) NOT NULL,
       DESCRIPTION          varchar(100) NULL,
       METADATA_TYPE_ID     varchar(20) NULL,
       PROVISION_RESOURCE   int NULL,
       PROCESS_NAME         varchar(80) NULL,
  PRIMARY KEY (RESOURCE_TYPE_ID)
);

CREATE TABLE RESOURCES(
       RESOURCE_ID          varchar(20) NOT NULL,
       RESOURCE_TYPE_ID     varchar(20) NULL,
       DESCRIPTION          varchar(100) NULL,
       RESOURCE_PARENT      varchar(20) NULL,
       BRANCH_ID            varchar(20) NULL,
       CATEGORY_ID	    varchar(20) NULL,
       DISPLAY_ORDER	    int NULL,
       NODE_LEVEL	    int NULL,
       SENSITIVE_APP	int null,
       PRIMARY KEY (RESOURCE_ID), 
	CONSTRAINT FK_RESOURCE_RESOURCE_TYPE
       FOREIGN KEY (RESOURCE_TYPE_ID)  REFERENCES RESOURCE_TYPE(RESOURCE_TYPE_ID),
	CONSTRAINT FK_RESOURCE_CATEGORY
       FOREIGN KEY (CATEGORY_ID) REFERENCES CATEGORY(CATEGORY_ID)
);


CREATE TABLE RESOURCE_PROP (
       RESOURCE_PROP_ID     varchar(20) NOT NULL,
       RESOURCE_ID          varchar(20) NULL,
       METADATA_ID          varchar(20) NULL,
       PROP_VALUE           varchar(100) NULL,
       PRIMARY KEY (RESOURCE_PROP_ID), 
	CONSTRAINT FK_RESOURCE_PROP_RESOURCE
       FOREIGN KEY (RESOURCE_ID) REFERENCES RESOURCES(RESOURCE_ID)
);
CREATE TABLE PRIVILEGE(
       PRIVILEGE_ID         varchar(20) NOT NULL,
       DESCRIPTION          varchar(40) NULL,
       PRIMARY KEY (PRIVILEGE_ID)
);
CREATE TABLE RESOURCE_USER(
       RESOURCE_ID          varchar(20) NOT NULL,
       USER_ID              varchar(20) NOT NULL,
       PRIVILEGE_ID         varchar(20) NOT NULL,
       PRIMARY KEY (RESOURCE_ID, USER_ID, PRIVILEGE_ID), 
       CONSTRAINT FK_RESOURCE_USER_PRIVILEGE
       FOREIGN KEY (PRIVILEGE_ID)
				 REFERENCES PRIVILEGE(PRIVILEGE_ID),
	CONSTRAINT FK_RESOURCE_USER_USERS
         FOREIGN KEY (USER_ID)
                             REFERENCES USERS(USER_ID), 
       FOREIGN KEY (RESOURCE_ID)
                             REFERENCES RESOURCES(RESOURCE_ID)
);

CREATE TABLE RESOURCE_ROLE(
       RESOURCE_ID          varchar(20) NOT NULL,
       ROLE_ID              varchar(20) NOT NULL,
       PRIVILEGE_ID         varchar(20) NOT NULL,
       PRIMARY KEY (RESOURCE_ID, ROLE_ID, PRIVILEGE_ID), 
	CONSTRAINT FK_RESOURCE_ROLE_PRIVILEGE
       FOREIGN KEY (PRIVILEGE_ID)
                             REFERENCES PRIVILEGE(PRIVILEGE_ID),
	CONSTRAINT FK_RESOURCE_ROLE_ROLE
       FOREIGN KEY (ROLE_ID)
                             REFERENCES ROLE(ROLE_ID), 
	CONSTRAINT FK_RESOURCE_ROLE_RESOURCE
       FOREIGN KEY (RESOURCE_ID)
                             REFERENCES RESOURCES(RESOURCE_ID)
);



CREATE TABLE USER_IND (
       USER_IND             varchar(20) NOT NULL,
       USER_DESCRIPTION     varchar(40) NULL,
       PRIMARY KEY (USER_IND)
);

CREATE TABLE LOGIN(
       SERVICE_ID           varchar(20) NOT NULL,
       LOGIN                varchar(50) NOT NULL,
	   MANAGED_SYS_ID 		VARCHAR(20) NOT NULL,
	   CANONICAL_NAME		VARCHAR(100) NULL,
       USER_ID              varchar(20) NULL,
       PASSWORD             varchar(80) NULL,
       PWD_EQUIVALENT_TOKEN varchar(80) NULL,
       PWD_CHANGED          datetime NULL,				/* last time the password was changed */
       PWD_EXP              datetime NULL,				/* date when the pswd expires */
       RESET_PWD	    	integer NOT NULL DEFAULT 0,		/* 1 - PASSWORD HAS BEEN RESET. FIRST TIME LOGIN*/
       IS_LOCKED	        integer NOT NULL DEFAULT 0,		/* IS THIS IDENTITY LOCKED  */
       STATUS		    	varchar(20) NULL,			/* STATUS OF THIS IDENTITY  */
       GRACE_PERIOD			DATETIME NULL, 				/* WHEN THE GRACE_PRD_EXP 	*/	
       CREATE_DATE			DATETIME NULL,
       CREATED_BY			VARCHAR(20) NULL,
       CURRENT_LOGIN_HOST	VARCHAR(40) NULL,		/* HOST FROM WHICH YOU ARE LOGGED IN*/	
       AUTH_FAIL_COUNT		INTEGER NULL,				/* NUMBER OF TIMES AUTH. HAS FAILED */
       LAST_AUTH_ATTEMPT	DATETIME NULL,	    	/* WHEN WAS THE LAST AUTH. ATTEMPT  */   
	   LAST_LOGIN			DATETIME NULL,			/* LAST TIME THE PERSON SUCCESSFULLY LOGGED IN */
	   IS_DEFAULT			INTEGER NULL DEFAULT 0,
       PRIMARY KEY (SERVICE_ID, LOGIN), 
	CONSTRAINT FK_LOGIN_USERS
       FOREIGN KEY (USER_ID)
                             REFERENCES USERS(USER_ID), 
       CONSTRAINT FK_LOGIN_SERVICE
       FOREIGN KEY (SERVICE_ID)
                             REFERENCES SECURITY_DOMAIN(DOMAIN_ID)
);

CREATE TABLE LOGIN_ATTRIBUTE (
	   LOGIN_ATTR_ID		VARCHAR(20) NOT NULL,
       LOGIN                varchar(30) NOT NULL,
       SERVICE_ID	    	varchar(20) NOT NULL,
       NAME                 varchar(20) NULL,
       VALUE                varchar(255) NULL,
       METADATA_ID          varchar(20) NULL,
	   ATTR_GROUP			VARCHAR(20) NULL,
       PRIMARY KEY (LOGIN_ATTR_ID), 
	CONSTRAINT FK_LOGIN_LOGIN_ATTRIBUTE
       FOREIGN KEY (SERVICE_ID, LOGIN)
                             REFERENCES LOGIN(SERVICE_ID, LOGIN)
);

CREATE TABLE AUTH_STATE (
       USER_ID              varchar(20) NOT NULL,
       AUTH_STATE           numeric(5,1) NULL,
       TOKEN                varchar(100) NULL,
       AA                   varchar(20) NULL,
       EXPIRATION           numeric(18,0) NULL,
       LAST_LOGIN	    datetime NULL,
       IP_ADDRESS	    varchar(20) NULL,
       PRIMARY KEY (USER_ID), 
	CONSTRAINT FK_AUTH_STATE_USERS FOREIGN KEY (USER_ID) REFERENCES USERS (USER_ID)
);



CREATE TABLE PWD_HISTORY (
       LOGIN                varchar(30) NOT NULL,
       SERVICE_ID	    	varchar(20) NOT NULL,
       DATE_CREATED         datetime NOT NULL,
       PASSWORD             varchar(80) NULL,
       PRIMARY KEY (LOGIN, SERVICE_ID, DATE_CREATED), 
	CONSTRAINT FK_PWD_HISTORY_LOGIN
       FOREIGN KEY (SERVICE_ID, LOGIN)
                             REFERENCES LOGIN(SERVICE_ID, LOGIN)
);

CREATE TABLE PWD_LOGIN_HISTORY (
       PWD_LOGIN_ID         varchar(20) NOT NULL,
       SERVICE_ID           varchar(20) NULL,
       LOGIN                varchar(30) NULL,
       PASSWORD             varchar(80) NULL,
       DATE_CREATED         datetime NULL,
       PRIMARY KEY (PWD_LOGIN_ID), 
	CONSTRAINT FK_PWD_LOGIN_HISTORY_LOGIN
       FOREIGN KEY (SERVICE_ID, LOGIN)
                             REFERENCES LOGIN(SERVICE_ID, LOGIN)
);
CREATE TABLE CREDENTIALS (
       USER_ID              varchar(20) NOT NULL,
       CREDENTIAL_TYPE      varchar(20) NOT NULL,
       VALUE                varchar(100) NULL,
       IS_PUBLIC            bit,
       PRIMARY KEY (USER_ID, CREDENTIAL_TYPE), 
	CONSTRAINT FK_CREDENTIALS_USERS
       FOREIGN KEY (USER_ID)
                             REFERENCES USERS(USER_ID)
);


CREATE TABLE IDENTITY_QUEST_GRP (
       IDENTITY_QUEST_GRP_ID varchar(20) NOT NULL,
       NAME                 varchar(60) NULL,
       STATUS               varchar(20) NULL,
       COMPANY_OWNER_ID     varchar(20) NULL,
       CREATE_DATE          datetime NULL,
       CREATED_BY           varchar(20) NULL,
       LAST_UPDATE          datetime NULL,
       LAST_UPDATED_BY      varchar(20) NULL,
       PRIMARY KEY (IDENTITY_QUEST_GRP_ID)
);

CREATE TABLE IDENTITY_QUESTION (
       IDENTITY_QUESTION_ID 	varchar(20) NOT NULL,
       IDENTITY_QUEST_GRP_ID 	varchar(20) NULL,
       QUESTION_TEXT        	varchar(255) NULL,
       REQUIRED             	int NULL,
 	USER_ID			VARCHAR(20),	
       PRIMARY KEY (IDENTITY_QUESTION_ID),
       CONSTRAINT FK_IDENTITY_QUESTION_IDENTITY_QUEST_GRP
              FOREIGN KEY (IDENTITY_QUEST_GRP_ID) REFERENCES IDENTITY_QUEST_GRP(IDENTITY_QUEST_GRP_ID)
);
CREATE TABLE USER_IDENTITY_ANS (
       IDENTITY_ANS_ID      varchar(20) NOT NULL,
       IDENTITY_QUESTION_ID varchar(20) NULL,
	   QUESTION_TEXT		varchar(255) null,
       USER_ID              varchar(20) NULL,
       QUESTION_ANSWER      varchar(255) NULL,
       PRIMARY KEY (IDENTITY_ANS_ID), 
       CONSTRAINT FK_USER_IDENTITY_ANS_IDENTITY_QUESTION
       		FOREIGN KEY (IDENTITY_QUESTION_ID)  REFERENCES IDENTITY_QUESTION(IDENTITY_QUESTION_ID), 
       CONSTRAINT FK_USER_IDENTITY_ANS_USERS
       		FOREIGN KEY (USER_ID) REFERENCES USERS(USER_ID)
);

CREATE TABLE POLICY_DEF (
       POLICY_DEF_ID        varchar(20) NOT NULL,
       NAME                 varchar(60) NULL,
       DESCRIPTION          varchar(255) NULL,
       POLICY_TYPE          varchar(20) NULL,
       LOCATION_TYPE        varchar(20) NULL,
       POLICY_RULE          varchar(500) NULL,
       POLICY_HANDLER       varchar(255) NULL,
	POLICY_ADVISE_HANDLER	VARCHAR(255) NULL,
       PRIMARY KEY (POLICY_DEF_ID)
);

CREATE TABLE POLICY (
       POLICY_ID            varchar(20) NOT NULL,
       POLICY_DEF_ID        varchar(20) NULL,
       NAME                 varchar(60) NULL,
       DESCRIPTION          varchar(255) NULL,
       ENABLEMENT           int NULL,
       CREATE_DATE          datetime NULL,
       CREATED_BY           varchar(20) NULL,
       LAST_UPDATE          datetime NULL,
       LAST_UPDATED_BY      varchar(20) NULL,
       PRIMARY KEY (POLICY_ID), 
       CONSTRAINT FK_POLICY_POLICY_DEF
       	FOREIGN KEY (POLICY_DEF_ID) REFERENCES POLICY_DEF(POLICY_DEF_ID)
);

CREATE TABLE POLICY_MEMBERSHIP (
       POLICY_MEM_ID        varchar(20) NOT NULL,
       POLICY_ID            varchar(20) NULL,
       SERVICE_ID           varchar(20) NULL,
       RESOURCE_TYPE        varchar(20) NULL,
       RESOURCE_OBJ_ID      varchar(20) NULL,
       PRIMARY KEY (POLICY_MEM_ID), 
       CONSTRAINT FK_POLICY_MEMBERSHIP_POLICY
       		FOREIGN KEY (POLICY_ID) REFERENCES POLICY(POLICY_ID)
);

CREATE TABLE POLICY_DEF_PARAM (
       DEF_PARAM_ID         varchar(20) NOT NULL,
       POLICY_DEF_ID        varchar(20) NULL,
       NAME                 varchar(60) NULL,
       DESCRIPTION          varchar(255) NULL,
       OPERATION            varchar(20) NULL,
       VALUE1               varchar(255) NULL,
       VALUE2               varchar(255) NULL,
       REPEATS               int NULL,
	POLICY_PARAM_HANDLER VARCHAR(255) NULL,
       PRIMARY KEY (DEF_PARAM_ID), 
       CONSTRAINT FK_POLICY_DEF_PARAM_POLICY_DEF
       		FOREIGN KEY (POLICY_DEF_ID) REFERENCES POLICY_DEF(POLICY_DEF_ID)
);

CREATE TABLE POLICY_ATTRIBUTE (
       POLICY_ATTR_ID       varchar(20) NOT NULL,
       DEF_PARAM_ID         varchar(20) NULL,
       POLICY_ID            varchar(20) NULL,
       NAME                 varchar(60) NULL,
       OPERATION            varchar(20) NULL,
       VALUE1               varchar(255) NULL,
       VALUE2               varchar(255) NULL,
       PRIMARY KEY (POLICY_ATTR_ID),
       CONSTRAINT FK_POLICY_ATTRIBUTE_POLICY_DEF_PARAM
       		FOREIGN KEY (DEF_PARAM_ID) REFERENCES POLICY_DEF_PARAM(DEF_PARAM_ID), 
       CONSTRAINT FK_POLICY_ATTRIBUTE_POLICY
       		FOREIGN KEY (POLICY_ID) REFERENCES POLICY(POLICY_ID)
);

CREATE TABLE AUTH_FLOW (
       AUTH_FLOW_ID         varchar(20) NOT NULL,
       CATEGORY_ID          varchar(20) NULL,
       NAME                 varchar(40) NULL,
       DESCRIPTION          varchar(255) NULL,
       CHALLENGE_METHOD     varchar(40) NULL,
       PRIMARY KEY (AUTH_FLOW_ID), 
	   CONSTRAINT FK_ATH_FLW_CAT
	       FOREIGN KEY (CATEGORY_ID)
                             REFERENCES CATEGORY (CATEGORY_ID)
);

CREATE TABLE AUTH_SEQUENCE (
       AUTH_SEQUENCE_ID     varchar(20) NOT NULL,
       AUTH_FLOW_ID         varchar(20) NULL,
       AUTH_METHOD          varchar(20) NULL,
       AUTH_SEQUENCE        int NULL,
       PRIMARY KEY (AUTH_SEQUENCE_ID), 
	   CONSTRAINT FK_ATH_SQ_ATH_FLW
       	FOREIGN KEY (AUTH_FLOW_ID)
                             REFERENCES AUTH_FLOW (AUTH_FLOW_ID)
);


CREATE TABLE AUTH_SEQUENCE_EVENT (
       SEQUENCE_EVENT_ID    varchar(20) NOT NULL,
       AUTH_SEQUENCE_ID     varchar(20) NULL,
       EVENT                varchar(20) NULL,
       INVOKE_URL           varchar(20) NULL,
       INVOKE_OBJ           varchar(20) NULL,
       PRIMARY KEY (SEQUENCE_EVENT_ID), 
	   CONSTRAINT FK_ATH_EV_ATH_SQ
       	FOREIGN KEY (AUTH_SEQUENCE_ID)
                             REFERENCES AUTH_SEQUENCE (AUTH_SEQUENCE_ID)
);

CREATE TABLE ENTITLEMENT (
       ENTITLEMENT_ID       varchar(20) NOT NULL,
       ENTITLEMENT_NAME     varchar(40) NULL,
       ENTITLEMENT_VALUE    varchar(80) NULL,
       DESCRIPTION          varchar(255) NULL,
       CREATE_DATE          datetime NULL,
       CREATED_BY           varchar(20) NULL,
       PRIMARY KEY (ENTITLEMENT_ID)
);

CREATE TABLE ROLE_ENTITLEMENT (
       ENTITLEMENT_ID       varchar(20) NOT NULL,
       ROLE_ID              varchar(20) NOT NULL,
       PRIMARY KEY (ENTITLEMENT_ID, ROLE_ID),
       CONSTRAINT RL_EN_RL
       	FOREIGN KEY (ROLE_ID) REFERENCES ROLE (ROLE_ID),
	   CONSTRAINT RL_EN_EN
       	FOREIGN KEY (ENTITLEMENT_ID) REFERENCES ENTITLEMENT(ENTITLEMENT_ID)
);



CREATE TABLE RESOURCE_POLICY (
       RESOURCE_POLICY_ID   varchar(20) NOT NULL,
       ROLE_ID              varchar(20) NULL,
       RESOURCE_ID          varchar(20) NULL,
       POLICY_START         datetime NULL,
       POLICY_END           datetime NULL,
       APPLY_TO_CHILDREN    int NULL,
       PRIMARY KEY (RESOURCE_POLICY_ID), 
       CONSTRAINT RS_PL_RL_RLID 
       		FOREIGN KEY (ROLE_ID)
                             REFERENCES ROLE (ROLE_ID), 
       CONSTRAINT RS_PL_RS_RSID 
       		FOREIGN KEY (RESOURCE_ID)
                             REFERENCES RESOURCES(RESOURCE_ID)
);





CREATE TABLE AUDIT_POLICY (
       AUDIT_POLICY_ID      varchar(20) NOT NULL,
       CATEGORY_ID          varchar(20) NULL,
       LOG_FORMAT           varchar(1000) NULL,
       LOG_PARAM            varchar(1000) NULL,
       LOG_EVENTS           varchar(1000) NULL,
       PRIMARY KEY (AUDIT_POLICY_ID), 
       CONSTRAINT AUDPL_CATID
              FOREIGN KEY (CATEGORY_ID)
                             REFERENCES CATEGORY(CATEGORY_ID)
);


CREATE TABLE TIME_POLICY (
       TIME_POLICY_ID       char(18) NOT NULL,
       RESOURCE_POLICY_ID   varchar(20) NULL,
       CATEGORY_ID          varchar(20) NULL,
       ACTION               char(18) NULL,
       START_TIME           char(18) NULL,
       END_TIME             char(18) NULL,
       PRIMARY KEY (TIME_POLICY_ID), 
       CONSTRAINT TM_PL_RS_PL
       		FOREIGN KEY (RESOURCE_POLICY_ID)
                             REFERENCES RESOURCE_POLICY(RESOURCE_POLICY_ID), 
       CONSTRAINT TM_PL_CATID
       		FOREIGN KEY (CATEGORY_ID)
                             REFERENCES CATEGORY(CATEGORY_ID)
);

CREATE TABLE IP_POLICY (
       IP_POLICY_ID         varchar(20) NOT NULL,
       RESOURCE_POLICY_ID   varchar(20) NULL,
       CATEGORY_ID          varchar(20) NULL,
       ACTION               varchar(20) NULL,
       IP_START             varchar(20) NULL,
       IP_END               varchar(20) NULL,
       PRIMARY KEY (IP_POLICY_ID), 
       CONSTRAINT IP_PL_RS_PL
      		 FOREIGN KEY (RESOURCE_POLICY_ID)
                             REFERENCES RESOURCE_POLICY(RESOURCE_POLICY_ID),
       CONSTRAINT IP_PL_CATID 
       		 FOREIGN KEY (CATEGORY_ID)
                             REFERENCES CATEGORY(CATEGORY_ID)
);


CREATE TABLE POLICY_SCRIPT (
       POLICY_SCRIPT_ID     varchar(20) NOT NULL,
       RESOURCE_POLICY_ID   varchar(20) NULL,
       CATEGORY_ID          varchar(20) NULL,
       SEQUENCE_ID          int NULL,
       RULE                 varchar(2000) NULL,
       PRIMARY KEY (POLICY_SCRIPT_ID), 
       CONSTRAINT PL_SC_RS_PL
       		FOREIGN KEY (RESOURCE_POLICY_ID)
                             REFERENCES RESOURCE_POLICY(RESOURCE_POLICY_ID), 
       CONSTRAINT PL_SC_CATID 
       FOREIGN KEY (CATEGORY_ID)
                             REFERENCES CATEGORY(CATEGORY_ID)
);


CREATE TABLE IDM_AUDIT_LOG (
      LOG_ID        	varchar(32) NOT NULL,
      OBJECT_TYPE_ID    VARCHAR(20) NOT NULL,            /* TYPE OF OBJECT THAT IS BEING AUDITED */
      ACTION_ID         VARCHAR(20) NOT NULL,            /* TYPE OF ACTION                              */
      ACTION_STATUS     VARCHAR(20) NULL,                /* STATUS OF THE ACTION                               */                                 
      REASON            VARCHAR(60) NULL,                /* REASON WHY THE STATUS IS WHAT IT IS. IF AUTH_FAILS, THEN WHY */
      REASON_DETAIL    	TEXT NULL,                       /* INCASE WE WANT TO CAPTURE SOME OUTPUT LIKE AN EXCEPTION LOG  */
      ACTION_DATETIME 	TIMESTAMP NULL,               /* DATE AND TIME THIS EVENT OCCURED.                                          */
      OBJECT_NAME      	VARCHAR(255) NULL,               /* OBJECT THAT WAS ACTED UPON                                                     */
      RESOURCE_NAME 	VARCHAR(255) NULL,               /* RESOURCE INVOLVED IN THIS TASK                                                */
      USER_ID              varchar(20) NULL,
      SERVICE_ID           varchar(20) NULL,
      LOGIN_ID             varchar(30) NULL,     
      HOST                 varchar(60) NULL,              /* HOST WHERE THE EVENT OCCURRED */
      CLIENT_ID            VARCHAR(20) NULL,              /* CLIENT, AGENT OR INTERFACE WHERE THIS EVENT OCCURRED      */
      REQ_URL              varchar(255) NULL,
      LINKED_LOG_ID		 	VARCHAR(32) NULL,			  /* INDICATES IF THIS EVENT IS LINKED TO ANOTHER EVENT 		*/
	  LINK_SEQUENCE			INT NULL,
	  ATTRIBUTES_CHANGES   	TEXT NULL,                     /* VALUES THAT WERE CHANGED DURING THIS OPERATION                 */
	  LOG_HASH   			VARCHAR(32) NULL,                     /* VALUES THAT WERE CHANGED DURING THIS OPERATION                 */
	  CUSTOM_ATTRNAME1          VARCHAR(255) NULL,        /* additional NAME fileds that can be used to have their own columns instead of in big blob. */
      CUSTOM_ATTRNAME2          VARCHAR(255) NULL,
      CUSTOM_ATTRNAME3          VARCHAR(255) NULL,
      CUSTOM_ATTRNAME4          VARCHAR(255) NULL,
      CUSTOM_ATTRNAME5          VARCHAR(255) NULL,
      CUSTOM_ATTRNAME6          VARCHAR(255) NULL,
      CUSTOM_ATTRNAME7          VARCHAR(255) NULL,
      CUSTOM_ATTRNAME8          VARCHAR(255) NULL,
      CUSTOM_ATTRNAME9          VARCHAR(255) NULL,
      CUSTOM_ATTRNAME10         VARCHAR(255) NULL,
      CUSTOM_ATTRVALUE1         VARCHAR(255) NULL,
      CUSTOM_ATTRVALUE2         VARCHAR(255) NULL,
      CUSTOM_ATTRVALUE3         VARCHAR(255) NULL,
      CUSTOM_ATTRVALUE4         VARCHAR(255) NULL,
      CUSTOM_ATTRVALUE5         VARCHAR(255) NULL,
      CUSTOM_ATTRVALUE6         VARCHAR(255) NULL,
      CUSTOM_ATTRVALUE7         VARCHAR(255) NULL,
      CUSTOM_ATTRVALUE8         VARCHAR(255) NULL,
      CUSTOM_ATTRVALUE9         VARCHAR(255) NULL,
      CUSTOM_ATTRVALUE10      	VARCHAR(255) NULL,
      CUSTOM_PARAMNAME1       	VARCHAR(255) NULL,           /* additional NAME fileds that can be used to have their own columns instead of in big blob. */
      CUSTOM_PARAMNAME2       	VARCHAR(255) NULL,
      CUSTOM_PARAMNAME3       	VARCHAR(255) NULL,
      CUSTOM_PARAMNAME4       	VARCHAR(255) NULL,
      CUSTOM_PARAMNAME5       	VARCHAR(255) NULL,
      CUSTOM_PARAMNAME6       	VARCHAR(255) NULL,
      CUSTOM_PARAMNAME7       	VARCHAR(255) NULL,
      CUSTOM_PARAMNAME8       	VARCHAR(255) NULL,
      CUSTOM_PARAMNAME9       	VARCHAR(255) NULL,
      CUSTOM_PARAMNAME10    	VARCHAR(255) NULL,
      CUSTOM_PARAMVALUE1    	VARCHAR(255) NULL,
      CUSTOM_PARAMVALUE2    	VARCHAR(255) NULL,
      CUSTOM_PARAMVALUE3    	VARCHAR(255) NULL,
      CUSTOM_PARAMVALUE4    	VARCHAR(255) NULL,
      CUSTOM_PARAMVALUE5    	VARCHAR(255) NULL,
      CUSTOM_PARAMVALUE6    	VARCHAR(255) NULL,
      CUSTOM_PARAMVALUE7    	VARCHAR(255) NULL,
      CUSTOM_PARAMVALUE8    	VARCHAR(255) NULL,
      CUSTOM_PARAMVALUE9    	VARCHAR(255) NULL,
      CUSTOM_PARAMVALUE10   	VARCHAR(255) NULL,
      PRIMARY KEY (LOG_ID)
);


CREATE TABLE PROVISION_CONNECTOR (
	CONNECTOR_ID 			varchar(32) NOT NULL,
	NAME		 			VARCHAR(40) NULL,
	METADATA_TYPE_ID		VARCHAR(20) NULL,
	STD_COMPLIANCE_LEVEL	VARCHAR(20) NULL,
	CLIENT_COMM_PROTOCOL	VARCHAR(20) NULL,
	SERVICE_URL				VARCHAR(60) NULL,
	CLASS_NAME				VARCHAR(60) NULL,
	PRIMARY KEY(CONNECTOR_ID)
);



CREATE TABLE MANAGED_SYS (
       MANAGED_SYS_ID           VARCHAR(32) NOT NULL,
       NAME         						VARCHAR(40) NULL,
 	   DESCRIPTION							VARCHAR(80) NULL,
       STATUS								VARCHAR(20) NULL,
	   CONNECTOR_ID							VARCHAR(20) NOT NULL,
	   DOMAIN_ID							VARCHAR(20) NOT NULL,
	   HOST_URL								VARCHAR(80) NULL,
	   PORT									INT NULL,
	   COMM_PROTOCOL						VARCHAR(20) NULL,
	   USER_ID								VARCHAR(40) NULL,
	   PSWD									VARCHAR(40) NULL,
	   START_DATE							DATE NULL,
	   END_DATE								DATE NULL,
       PRIMARY KEY (MANAGED_SYS_ID),
			 CONSTRAINT FK_MNG_SYS_PROV_CON
			 	FOREIGN KEY (CONNECTOR_ID) REFERENCES PROVISION_CONNECTOR(CONNECTOR_ID)
);

CREATE TABLE MNG_SYS_OBJECT_MATCH (
		OBJECT_SEARCH_ID	VARCHAR(32) NOT NULL,
		MANAGED_SYS_ID      VARCHAR(32) NOT NULL,
		/* USER, GROUP, ROLE, COMPUTER, RESOURCE, ETC */
		OBJECT_TYPE			VARCHAR(20) NULL DEFAULT 'USER',
		/* BASE_DN, SEARCH */ 
		MATCH_METHOD		VARCHAR(20) NULL DEFAULT 'BASE_DN',
		BASE_DN				VARCHAR(200) NULL,
		SEARCH_FILTER		VARCHAR(1000) NULL,
		KEY_FIELD			VARCHAR(40) NULL,
       PRIMARY KEY (OBJECT_SEARCH_ID),
			 CONSTRAINT FK_MNG_SYS_OBJ_MATC
			 	FOREIGN KEY (MANAGED_SYS_ID) REFERENCES MANAGED_SYS(MANAGED_SYS_ID)
);


CREATE TABLE MNG_SYS_GROUP(
    MNG_SYS_GROUP_ID    VARCHAR(32)    NOT NULL,
    MANAGED_SYS_ID      VARCHAR(32)    NOT NULL,
    PRIMARY KEY (MANAGED_SYS_ID, MNG_SYS_GROUP_ID), 
    CONSTRAINT Refmanaged_sys831 FOREIGN KEY (MANAGED_SYS_ID)
    REFERENCES MANAGED_SYS(MANAGED_SYS_ID)
);
COMMIT;

CREATE TABLE REQ_APPROVER(
    REQ_APPROVER_ID     VARCHAR(32)    NOT NULL,
    USER_ID             VARCHAR(20)	   NULL,
    APPROVER_LEVEL      VARCHAR(20)	   NULL,
    MNG_SYS_GROUP_ID    VARCHAR(32)    NOT NULL,
    MANAGED_SYS_ID      VARCHAR(32)    NOT NULL,
    PRIMARY KEY (REQ_APPROVER_ID)
);


CREATE TABLE PROV_REQUEST(
    REQUEST_ID        VARCHAR(32)     NOT NULL,
    REQUESTOR_ID      VARCHAR(20),
    REQUEST_DATE      DATETIME,
    STATUS            VARCHAR(20),
    STATUS_DATE       DATETIME,
    REQUEST_REASON    VARCHAR(500),
    PRIMARY KEY (REQUEST_ID)
);

CREATE TABLE APPROVAL_HISTORY(
    APPRV_HIST_ID      VARCHAR(32)    NOT NULL,
    ACTION_DATE        DATETIME,
    ACTION             VARCHAR(20)    NOT NULL,
    REQ_APPROVER_ID    VARCHAR(20)    NOT NULL,
    REQUEST_ID         VARCHAR(32)    NOT NULL,
    PRIMARY KEY (APPRV_HIST_ID), 
    CONSTRAINT RefREQ_APPROVER981 FOREIGN KEY (REQ_APPROVER_ID)
    REFERENCES REQ_APPROVER(REQ_APPROVER_ID),
    CONSTRAINT RefPROV_REQUEST991 FOREIGN KEY (REQUEST_ID)
    REFERENCES PROV_REQUEST(REQUEST_ID)
);

CREATE TABLE DATA_SOURCE(
    DATA_SOURCE_ID    VARCHAR(32)    NOT NULL,
    CLASS_NAME        VARCHAR(20),
    DRIVER_URL        VARCHAR(20),
    USER_NAME         VARCHAR(20),
    USER_PASSWORD     VARCHAR(20),
    PRIMARY KEY (DATA_SOURCE_ID)
);

CREATE TABLE PROV_FORM(
    FORM_ID                VARCHAR(32)     NOT NULL,
    NAME                   VARCHAR(50),
    DESCRIPTION            VARCHAR(100),
    MANAGED_SYS_ID         VARCHAR(32),
    MNG_SYS_GROUP_ID       VARCHAR(32),
    AUTO_GEN               INTEGER,
    CUSTOM_FORM_URL        VARCHAR(20),
    CUSTOM_FORM_HANDLER    VARCHAR(80),
    PROCESS_NAME           VARCHAR(20),
    SEND_MSG               INTEGER,
    DISPLAY_COLS           INT,
    PRIMARY KEY (FORM_ID)
);

CREATE TABLE PROV_QUESTION_BANK(
    PROV_QUEST_ID           VARCHAR(32)     NOT NULL,
    QUESTION_TEXT           VARCHAR(500),
    ANS_UI_OBJECT           VARCHAR(20),
    ANS_UI_OBJECT_SIZE      VARCHAR(20),
    ANS_LIST                VARCHAR(500),
    LIST_QUERY              VARCHAR(500),
    LIST_WEBSERVICE_URL     VARCHAR(80),
    WEBSERIVCE_OPERATION    VARCHAR(20),
    DATA_SOURCE_ID          VARCHAR(32)     NOT NULL,
    PRIMARY KEY (PROV_QUEST_ID), 
    CONSTRAINT RefDATA_SOURCE851 FOREIGN KEY (DATA_SOURCE_ID)
    REFERENCES DATA_SOURCE(DATA_SOURCE_ID)
);

CREATE TABLE FORM_QUESTION(
    FORM_ID          VARCHAR(32)    NOT NULL,
    PROV_QUEST_ID    VARCHAR(20)    NOT NULL,
    DISPLAY_ORDER    INT,
    PRIMARY KEY (FORM_ID, PROV_QUEST_ID), 
    CONSTRAINT RefPROV_FORM911 FOREIGN KEY (FORM_ID)
    REFERENCES PROV_FORM(FORM_ID),
    CONSTRAINT RefPROV_QUESTION_BANK921 FOREIGN KEY (PROV_QUEST_ID)
    REFERENCES PROV_QUESTION_BANK(PROV_QUEST_ID)
);


CREATE TABLE MNG_SYS_LIST(
    MANAGED_SYS_ID    VARCHAR(32)    NOT NULL,
    REQUEST_ID        VARCHAR(32)    NOT NULL,
    PRIMARY KEY (MANAGED_SYS_ID, REQUEST_ID), 
    CONSTRAINT Refmanaged_sys941 FOREIGN KEY (MANAGED_SYS_ID)
    REFERENCES managed_sys(MANAGED_SYS_ID),
    CONSTRAINT RefPROV_REQUEST951 FOREIGN KEY (REQUEST_ID)
    REFERENCES PROV_REQUEST(REQUEST_ID)
);

CREATE TABLE REQUEST_ATTACHMENT(
    REQUEST_ATTACHMENT_ID    VARCHAR(32)    NOT NULL,
    NAME                     VARCHAR(20),
    ATTACHMENT               VARCHAR(20),
    USER_ID                  VARCHAR(20),
    ATTACHMENT_DATE          VARCHAR(20),
    REQUEST_ID               VARCHAR(32)    NOT NULL,
    PRIMARY KEY (REQUEST_ATTACHMENT_ID), 
    CONSTRAINT RefPROV_REQUEST1001 FOREIGN KEY (REQUEST_ID)
    REFERENCES PROV_REQUEST(REQUEST_ID)
);

CREATE TABLE REQUEST_ATTRIBUTE(
    REQUEST_ATTR_ID    VARCHAR(32)    NOT NULL,
    NAME               VARCHAR(20),
    VALUE              VARCHAR(20),
    METADATA_ID        VARCHAR(20),
    ATTR_GROUP         VARCHAR(20),
    REQUEST_ID         VARCHAR(32)    NOT NULL,
    PRIMARY KEY (REQUEST_ATTR_ID), 
    CONSTRAINT RefPROV_REQUEST1011 FOREIGN KEY (REQUEST_ID)
    REFERENCES PROV_REQUEST(REQUEST_ID)
);

CREATE TABLE REQUEST_USER(
    REQ_USER_ID    VARCHAR(32)    NOT NULL,
    USER_ID             VARCHAR(20),
    FIRST_NAME          VARCHAR(20),
    LAST_NAME           VARCHAR(20),
    MIDDLE_INIT         VARCHAR(20),
    DEPT_CD             VARCHAR(20),
    DIVISION            VARCHAR(20),
    LOCATION_CD         VARCHAR(20),
    AFFILIATION         VARCHAR(20),
    REQUEST_ID          VARCHAR(32)    NOT NULL,
    PRIMARY KEY (REQ_USER_ID), 
    CONSTRAINT RefPROV_REQUEST931 FOREIGN KEY (REQUEST_ID)
    REFERENCES PROV_REQUEST(REQUEST_ID)
);


CREATE TABLE SYS_ATTRIBUTE_MAP (
       ATTRIBUTE_MAP_ID          		VARCHAR(32) NOT NULL,
			 MANAGED_SYS_ID           	VARCHAR(32) NOT NULL,
			 /* User, Group, OTHER 										*/
			 OBJECT_GROUP				VARCHAR(20) DEFAULT 'USER',
			 /* CN, DN, UID, ETC   */
			 TARGET_ATTRIBUTE_NAME		VARCHAR(40) NULL,
			 /* IS AUTHORITATIVE SRC. 0-FALSE, 1-TRUE */
			 AUTHORITATIVE_SRC			INT DEFAULT 0,
			 /* POLICY, IN-LINE 											*/
			 RULE_SOURCE				VARCHAR(20) NULL,
			 RULE						TEXT,
			 STATUS						VARCHAR(20) DEFAULT 'ACTIVE',
			 START_DATE					DATE,
			 END_DATE					DATE,
       PRIMARY KEY (ATTRIBUTE_MAP_ID),
			 CONSTRAINT FK_SYS_ATR_MNG_SYS
			 	FOREIGN KEY (MANAGED_SYS_ID) REFERENCES MANAGED_SYS(MANAGED_SYS_ID)
);


CREATE TABLE SECURITY_ACCESS_LOG (
      ACCESS_LOG_ID        varchar(32) NOT NULL,
      USER_ID              varchar(20) NOT NULL,
      LOG_TIME             datetime NOT NULL,
      SERVICE_ID           varchar(20) NULL,
      LOGIN_ID             varchar(30) NULL,
      ACTION               varchar(40) NULL,
      HOST_IP              varchar(60) NULL,
      REQ_URL              varchar(60) NULL,
      REQUEST_METHOD       VARCHAR(30) NULL,
      DESCRIPTION		   VARCHAR(500) NULL,
      PRIMARY KEY (ACCESS_LOG_ID)
);


ALTER TABLE STATUS
	ADD COLUMN  COMPANY_OWNER_ID		VARCHAR(20) NOT NULL,
  ADD COLUMN  SERVICE_ID           varchar(20) NOT NULL;

ALTER TABLE SERVICE
	ADD COLUMN  PARAM1		VARCHAR(20) NULL,
	ADD COLUMN  PARAM2		VARCHAR(20) NULL,
	ADD COLUMN  PARAM3		VARCHAR(20) NULL,
	ADD COLUMN  PARAM4		VARCHAR(20) NULL,
	ADD COLUMN  PARAM5		VARCHAR(20) NULL;


CREATE TABLE ADDRESS_USER(
       ADDRESS_ID           varchar(20) NOT NULL,
       USER_ID              varchar(20) NULL,
       COUNTRY              varchar(30) NULL,
       ADDRESS1             varchar(45) NULL,
       ADDRESS2             varchar(45) NULL,
       CITY                 varchar(30) NULL,
       STATE                varchar(15) NULL,
       POSTAL_CD            varchar(10) NULL,
       IS_DEFAULT           integer NULL,
       DESCRIPTION          varchar(25) NULL,
       ADDRESS_NBR          varchar(10) NULL,
       PRIMARY KEY (ADDRESS_ID),
	CONSTRAINT FK_ADDRESS_USER_USERS
       FOREIGN KEY (USER_ID)
                             REFERENCES USERS(USER_ID)
);

CREATE TABLE EMAIL_ADDRESS_USER (
       EMAIL_ID             varchar(20) NOT NULL,
       USER_ID              varchar(20) NULL,
       DESCRIPTION          varchar(25) NULL,
       EMAIL_ADDRESS        varchar(50) NULL,
       IS_DEFAULT           integer NULL,
       ADDRESS_ID           varchar(20) NULL,
       PRIMARY KEY (EMAIL_ID),
	CONSTRAINT FK_EMAIL_ADDR_USER_ADDR_USER
       FOREIGN KEY (ADDRESS_ID)
                             REFERENCES ADDRESS_USER(ADDRESS_ID)
);


CREATE TABLE PHONE_USER (
       PHONE_ID             varchar(20) NOT NULL,
       USER_ID              varchar(20) NULL,
       COUNTRY_CD           varchar(3) NULL,
       DESCRIPTION          varchar(25) NULL,
       PHONE_NBR            varchar(13) NULL,
       AREA_CD              varchar(3) NULL,
       IS_DEFAULT           integer NULL,
       ADDRESS_ID           varchar(20) NULL,
       PRIMARY KEY (PHONE_ID), 
	CONSTRAINT FK_PHONE_USER_USERS
       FOREIGN KEY (USER_ID)
                             REFERENCES USERS(USER_ID)
);

commit;