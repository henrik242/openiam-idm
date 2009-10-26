CREATE TABLE SECURITY_DOMAIN (
       DOMAIN_ID            varchar(20) NOT NULL,
       NAME         		varchar(40) NULL,
       STATUS				VARCHAR(20) NULL,
       /* Refers to which managed system to use for authentication */
	   AUTH_SYS_ID			VARCHAR(20) NULL,
	   LOGIN_MODULE			VARCHAR(100) NULL,
       /* Default policies for the domain */
       PASSWORD_POLICY	    	VARCHAR(20) NULL,
       AUTHENTICATION_POLICY	VARCHAR(20) NULL,
       AUDIT_POLICY	    		VARCHAR(20) NULL,
	PRIMARY KEY (DOMAIN_ID)
) ENGINE=InnoDB;


CREATE TABLE LANGUAGE (
       LANGUAGE_CD          varchar(5) NOT NULL,
       LANGUAGE             varchar(20) NULL,
	   LOCALE				INT NULL DEFAULT 0,
       PRIMARY KEY (LANGUAGE_CD)
)ENGINE=InnoDB;

CREATE TABLE BATCH_CONFIG (
	   TASK_ID 						VARCHAR(32) NOT NULL,
	   TASK_NAME 					VARCHAR(50) NULL,
       FREQUENCY					INT NULL,
	   FREQUENCY_UNIT_OF_MEASURE	VARCHAR(10) NULL,
	   LAST_EXEC_TIME				DATETIME NULL,
       PRIMARY KEY (TASK_ID)
) ENGINE=InnoDB;



CREATE TABLE EXCLUDE_WORD_LIST (
       WORD                 varchar(30) NOT NULL,
       LANGUAGE_CD          varchar(5) NOT NULL,
       PRIMARY KEY (WORD, LANGUAGE_CD), 
	CONSTRAINT FK_EXCLUDE_WORD_LIST_LANGUAGE
       FOREIGN KEY (LANGUAGE_CD)
                             REFERENCES LANGUAGE(LANGUAGE_CD)
) ENGINE=InnoDB;

CREATE TABLE CATEGORY(
       CATEGORY_ID          varchar(20) NOT NULL,
       PARENT_ID            varchar(20) NULL,
       CATEGORY_NAME        varchar(40) NULL,
	   	 CATEGORY_DESC        	varchar(80) NULL,
       CREATE_DATE			DATETIME NULL,
       CREATED_BY			VARCHAR(20) NULL,
       SHOW_LIST            int NULL DEFAULT 0,
       DISPLAY_ORDER        int NULL DEFAULT 0,
       PRIMARY KEY (CATEGORY_ID)
) ENGINE=InnoDB;

CREATE TABLE CATEGORY_LANGUAGE (
       CATEGORY_ID          varchar(20) NOT NULL,
       LANGUAGE_CD          varchar(5) NOT NULL,
       CATEGORY_NAME        varchar(40) NULL,
       PRIMARY KEY (CATEGORY_ID, LANGUAGE_CD), 
	 CONSTRAINT FK_CATEGORY_LANGUAGE_LANGUAGE
       FOREIGN KEY (LANGUAGE_CD)
                             REFERENCES LANGUAGE(LANGUAGE_CD), 
	 CONSTRAINT FK_CATEGORY_LANGUAGE_CATEGORY
       FOREIGN KEY (CATEGORY_ID)
                             REFERENCES CATEGORY(CATEGORY_ID) ON DELETE CASCADE
) ENGINE=InnoDB;



CREATE TABLE METADATA_TYPE (
       TYPE_ID              varchar(20) NOT NULL,
       DESCRIPTION          varchar(40) NULL,
	   ACTIVE				INTEGER NULL DEFAULT 0,
	   SYNC_MANAGED_SYS		INTEGER NULL DEFAULT 0,
       PRIMARY KEY (TYPE_ID)
)  ENGINE=InnoDB;


CREATE TABLE METADATA_ELEMENT (
       METADATA_ID          varchar(20) NOT NULL,
       TYPE_ID              varchar(20) NULL,
       ATTRIBUTE_NAME       varchar(50) NULL,
       DESCRIPTION          varchar(40) NULL,
	   MIN_LEN				INT DEFAULT 0,
	   MAX_LEN				INT NULL,	   
		/* UPPER OR LOWER 		*/
	   TEXT_CASE					VARCHAR(10) NULL,
	   /* STRING, INT, FLOAT 	*/
	   DATA_TYPE			VARCHAR(20) NULL,
	   MIN_VALUE			NUMERIC NULL,
		MAX_VALUE			NUMERIC NULL,
		DEFAULT_VALUE		VARCHAR(100) NULL,
		VALUE_LIST			VARCHAR(1000),
		LABEL				VARCHAR(100),
		MULTI_VALUE			INT NULL,
		AUDITABLE			INT NULL,
		REQUIRED			INT NULL,
		/* DETERMINES IF THE USER WHO OWN A RECORD AND SEE AND EDIT THEIR RECORD */
		SELF_EDITABLE		INT NULL,
		SELF_VIEWABLE		INT NULL,
		/* TEXT, PASSWORD, TEXT_AREA, CHECKBOX, SELECT, LIST */
		UI_TYPE				VARCHAR(20) NULL,
		UI_OBJECT_SIZE		VARCHAR(40) NULL,
		VALUE_SRC			VARCHAR(1000) NULL,
       PRIMARY KEY (METADATA_ID), 
	 CONSTRAINT FK_METADATA_ELEMENT_METADATA_TYPE     
	 FOREIGN KEY (TYPE_ID)
                 REFERENCES METADATA_TYPE(TYPE_ID)
) ENGINE=InnoDB;





CREATE TABLE COMPANY (
       COMPANY_ID           varchar(32) NOT NULL,
       COMPANY_NAME         VARCHAR(60) NULL,
       LST_UPDATE           datetime NULL,
       LST_UPDATED_BY		VARCHAR(20) NULL,
       PARENT_ID            varchar(32) NULL,
       STATUS			    VARCHAR(20) NULL,
       TYPE_ID              varchar(20) NULL,
	   CREATE_DATE			datetime NULL,
	   CREATED_BY			VARCHAR(20) NULL,
	   ALIAS				VARCHAR(40) NULL,
	   DESCRIPTION			VARCHAR(40) NULL,
	   DOMAIN_NAME			VARCHAR(40) NULL,
	   LDAP_STR				VARCHAR(255) NULL,	
	   CLASSIFICATION		VARCHAR(40) NULL,	
	   INTERNAL_COMPANY_ID	VARCHAR(32) NULL,
 	   PRIMARY KEY (COMPANY_ID), 
	CONSTRAINT FK_COMPANY_METADATA_TYPE
       FOREIGN KEY (TYPE_ID)
                             REFERENCES METADATA_TYPE(TYPE_ID)
) ENGINE=InnoDB;



CREATE TABLE COMPANY_ATTACHMENT_REF (
       COMPANY_ATTACH_REF_ID varchar(20) NOT NULL,
       COMPANY_ID           varchar(20) NULL,
       NAME                 varchar(20) NULL,
       VALUE                varchar(50) NULL,
       URL                  varchar(50) NULL,
       PRIMARY KEY (COMPANY_ATTACH_REF_ID), 
	CONSTRAINT FK_COMPANY_ATTACHMENT_REF_COMPANY
       FOREIGN KEY (COMPANY_ID)
                             REFERENCES COMPANY(COMPANY_ID)
) ENGINE=InnoDB;


CREATE TABLE COMPANY_ATTRIBUTE (
       COMPANY_ATTR_ID      varchar(32) NOT NULL,
       COMPANY_ID           varchar(32) NULL,
       NAME                 varchar(20) NULL,
       VALUE                varchar(255) NULL,
       METADATA_ID          varchar(20) NULL,
       PRIMARY KEY (COMPANY_ATTR_ID), 
	CONSTRAINT FK_COMPANY_ATTRIBUTE_COMPANY
       FOREIGN KEY (COMPANY_ID)
                             REFERENCES COMPANY(COMPANY_ID),
	CONSTRAINT FK_COMPANY_METADATA_ELEMENT
	 
       FOREIGN KEY (METADATA_ID)
                             REFERENCES METADATA_ELEMENT(METADATA_ID)
)ENGINE=InnoDB;


CREATE TABLE USERS(
       USER_ID              varchar(32) NOT NULL,
       FIRST_NAME           varchar(40) NULL,
       LAST_NAME            varchar(40) NULL,
       MIDDLE_INIT          VARCHAR(40) NULL,
       TYPE_ID              varchar(20) NULL,
	   CLASSIFICATION		VARCHAR(20) NULL,
       TITLE                varchar(30) NULL,
	   DEPT_CD				VARCHAR(50) NULL, /* Dept - can POINT TO AN OU in a directory */
	   DEPT					VARCHAR(50) NULL, 
	   DEPT_NAME			VARCHAR(100) NULL,
	   MAIL_CODE			VARCHAR(50) NULL,	   
       DIVISION				VARCHAR(50) NULL,
		COST_CENTER			VARCHAR(20) NULL,
	    STATUS              VARCHAR(40) NULL,
	   SECONDARY_STATUS		VARCHAR(40) NULL,
       BIRTHDATE            datetime NULL,
       SEX                  char(1) NULL,
	   CREATE_DATE			DATEtime NULL,
 	   CREATED_BY			VARCHAR(32) NULL,
	   LAST_UPDATE			DATETIME NULL,
	   LAST_UPDATED_BY		VARCHAR(20) NULL,
       PREFIX               VARCHAR(4) NULL,
       SUFFIX               VARCHAR(20) NULL,
       USER_TYPE_IND		VARCHAR(20) NULL,
       EMPLOYEE_ID			VARCHAR(32) NULL,
       EMPLOYEE_TYPE		VARCHAR(20) NULL,	/* TEMP, FULL-TIME, INTERN, PART-TIME, CONTRACTOR,  */			
       LOCATION_CD			VARCHAR(50) NULL,
	   LOCATION_NAME		VARCHAR(100) NULL,
       COMPANY_ID        	VARCHAR(32) NULL,
       COMPANY_OWNER_ID		VARCHAR(32) NULL,
       JOB_CODE				VARCHAR(50) NULL,
       MANAGER_ID			VARCHAR(32) NULL,
	   START_DATE			DATE NULL,
       LAST_DATE			DATE NULL,
 	   MAIDEN_NAME 			VARCHAR(40) NULL,
 	   NICKNAME 			VARCHAR(40) NULL,
	   PASSWORD_THEME		VARCHAR(20) NULL,
	   COUNTRY              varchar(30) NULL,
	   BLDG_NUM				VARCHAR(10) NULL,
	   STREET_DIRECTION		VARCHAR(20) NULL,
       ADDRESS1             varchar(45) NULL,
       ADDRESS2             varchar(45) NULL,
	   ADDRESS3             varchar(45) NULL,
	   ADDRESS4             varchar(45) NULL,
	   ADDRESS5             varchar(45) NULL,
	   ADDRESS6             varchar(45) NULL,
	   ADDRESS7             varchar(45) NULL,
       CITY                 varchar(30) NULL,
       STATE                varchar(15) NULL,
       POSTAL_CD            varchar(10) NULL,
	   EMAIL_ADDRESS        varchar(320) NULL,
	   AREA_CD              varchar(10) NULL,
       COUNTRY_CD           varchar(3) NULL,
       PHONE_NBR            varchar(50) NULL,
       PHONE_EXT			VARCHAR(20) NULL,
	   SHOW_IN_SEARCH		INT NULL,
       PRIMARY KEY (USER_ID), 
	CONSTRAINT FK_USERS_METADATA_TYPE
         FOREIGN KEY (TYPE_ID)
                             	REFERENCES METADATA_TYPE(TYPE_ID)
) ENGINE=InnoDB;





CREATE TABLE ORG_STRUCTURE (
	ORG_STRUCTURE_ID	VARCHAR(32) NOT NULL,
	SUPERVISOR_ID		VARCHAR(32) NOT NULL,
	STAFF_ID			VARCHAR(32) NOT NULL,
	SUPERVISOR_TYPE		VARCHAR(20) NULL,
    IS_PRIMARY_SUPER	INT NULL DEFAULT 0,
	START_DATE			DATETIME NULL,
	END_DATE			DATETIME NULL,
	STATUS				VARCHAR(20) NULL,
    COMMENTS			VARCHAR(255) NULL,
    PRIMARY KEY (ORG_STRUCTURE_ID),
	CONSTRAINT FK_SUPR_USER
         FOREIGN KEY (SUPERVISOR_ID)
                             	REFERENCES USERS(USER_ID),
	CONSTRAINT FK_STAFF_USER
         FOREIGN KEY (STAFF_ID)
                             	REFERENCES USERS(USER_ID)
) ENGINE=InnoDB;


CREATE TABLE USER_NOTE(
       USER_NOTE_ID   varchar(32) NOT NULL,
       USER_ID              varchar(32) NULL,
       NOTE_TYPE			VARCHAR(20) NULL,
       DESCRIPTION			VARCHAR(1000) NULL,
       CREATE_DATE			DATETIME NULL,
       CREATED_BY			VARCHAR(20) NULL,
       PRIMARY KEY (USER_NOTE_ID), 
	CONSTRAINT FK_USER_NOTE_USERS
       FOREIGN KEY (USER_ID)
                             REFERENCES USERS(USER_ID)
) ENGINE=InnoDB;

CREATE TABLE USER_ATTACHMENT_REF (
       USER_ATTACH_REF_ID   varchar(20) NOT NULL,
       USER_ID              varchar(20) NULL,
       NAME                 varchar(20) NULL,
       VALUE                varchar(50) NULL,
       URL                  varchar(50) NULL,
       PRIMARY KEY (USER_ATTACH_REF_ID), 
	CONSTRAINT FK_USER_ATTACHMENT_REF_USERS
       FOREIGN KEY (USER_ID)
                             REFERENCES USERS(USER_ID)
) ENGINE=InnoDB;


CREATE TABLE USER_ATTRIBUTES (
       ID                   varchar(32) NOT NULL,
       USER_ID              varchar(32) NULL,
       METADATA_ID          varchar(20) NULL,
       NAME                 varchar(20) NULL,
       VALUE                varchar(255) NULL,
	   ATTR_GROUP			VARCHAR(20) NULL,
       PRIMARY KEY (ID),
	CONSTRAINT FK_USER_ATTRIBUTES_METADATA_ELEMENT
       FOREIGN KEY (METADATA_ID)
                             REFERENCES METADATA_ELEMENT(METADATA_ID), 
       FOREIGN KEY (USER_ID)
                             REFERENCES USERS(USER_ID)
)ENGINE=InnoDB;



CREATE TABLE SERVICE (
       SERVICE_ID           varchar(20) NOT NULL,
       SERVICE_NAME         varchar(40) NULL,
       STATUS				VARCHAR(20) NULL,
       LOCATION_IP_ADDRESS	VARCHAR(80) NULL,
       COMPANY_OWNER_ID		VARCHAR(20) NULL,
       START_DATE			DATETIME NULL,
       END_DATE				DATETIME NULL,
       LICENSE_KEY			VARCHAR(255) NULL,	
       SERVICE_TYPE			VARCHAR(20) NULL,
       PARENT_SERVICE_ID	VARCHAR(20) NULL,
       ROOT_RESOURCE_ID     varchar(20) NULL,
       ACCESS_CONTROL_MODEL varchar(20) NULL,
       PRIMARY KEY (SERVICE_ID)
) ENGINE=InnoDB;

CREATE TABLE SERVICE_CONFIG (
       SERVICE_CONFIG_ID    varchar(20) NOT NULL,
       SERVICE_ID           varchar(20) NULL,
       NAME                 varchar(40) NULL,
       VALUE                varchar(40) NULL,
       PRIMARY KEY (SERVICE_CONFIG_ID),
       CONSTRAINT FK_SRV_SRV_CONF
       FOREIGN KEY (SERVICE_ID)
                             REFERENCES SERVICE(SERVICE_ID)
) ENGINE=InnoDB;


CREATE TABLE STATUS (
       STATUS_CD           	varchar(40) NOT NULL,
	   STATUS_TYPE			VARCHAR(20) NULL,
       DESCRIPTION          varchar(80) NULL,
       CODE_GROUP			VARCHAR(40) NOT NULL,
       LANGUAGE_CD			VARCHAR(2) NOT NULL,
       WEIGHT				INT	NULL,
       PRIMARY KEY (CODE_GROUP, STATUS_CD, LANGUAGE_CD)
) ENGINE=InnoDB;


CREATE TABLE MENU (
       MENU_ID              varchar(20) NOT NULL,
       LANGUAGE_CD          varchar(5) NOT NULL DEFAULT 'en',
       MENU_GROUP           varchar(20) NULL,
       MENU_NAME            varchar(40) NULL,
       MENU_DESC            varchar(40) NULL,
       URL                  varchar(100) NULL,
       ACTIVE               INT NULL,
       DISPLAY_ORDER        INT NULL,
	   PUBLIC_URL				INT NULL,
       PRIMARY KEY (MENU_ID, LANGUAGE_CD)
) ENGINE=InnoDB;


CREATE TABLE GRP (
       GRP_ID               varchar(32) NOT NULL,
       GRP_NAME             varchar(40) NULL,
       CREATE_DATE			DATETIME NULL,
       CREATED_BY			VARCHAR(20) NULL,
       COMPANY_ID			VARCHAR(32) NULL,
	   OWNER_ID				VARCHAR(32) NULL,
       PARENT_GRP_ID		VARCHAR(32) NULL,
       INHERIT_FROM_PARENT	INT NULL,
       PROVISION_METHOD		VARCHAR(20) NULL,
       PROVISION_OBJ_NAME	VARCHAR(80) NULL,
       TYPE_ID              varchar(20) NULL,
	   GROUP_CLASS			VARCHAR(40) NULL,
	   GROUP_DESC			VARCHAR(80) NULL,
   	   STATUS				VARCHAR(20) NULL,
	   LAST_UPDATE			DATETIME NULL,
	   LAST_UPDATED_BY		VARCHAR(20) NULL,	   
	   POLICY_ID			VARCHAR(20) NULL,
       PRIMARY KEY (GRP_ID),
       CONSTRAINT FK_GRP_METADATA_TYPE
         FOREIGN KEY (TYPE_ID)
                             	REFERENCES METADATA_TYPE(TYPE_ID)
       
) ENGINE=InnoDB;


CREATE TABLE GRP_ATTRIBUTES (
       ID                   varchar(32) NOT NULL,
       GRP_ID               varchar(32) NULL,
       METADATA_ID          varchar(20) NULL,
       NAME                 varchar(20) NULL,
       VALUE                varchar(255) NULL,
       PRIMARY KEY (ID),
	CONSTRAINT FK_GRP_ATTRIBUTES_METADATA_ELEMENT
       FOREIGN KEY (METADATA_ID)
                             REFERENCES METADATA_ELEMENT(METADATA_ID), 
       FOREIGN KEY (GRP_ID)
                             REFERENCES GRP(GRP_ID)
) ENGINE=InnoDB;


CREATE TABLE USER_GRP(
	USER_GRP_ID			VARCHAR(32) NOT NULL,
   	GRP_ID              varchar(32) NOT NULL,
   	USER_ID             varchar(32) NOT NULL,
	STATUS				VARCHAR(20) NULL DEFAULT 'ACTIVE',  
    CREATE_DATE			DATETIME NULL,
    CREATED_BY			VARCHAR(20) NULL,
	PRIMARY KEY (USER_GRP_ID),
       CONSTRAINT FK_USR_GRP_GPR
       FOREIGN KEY (GRP_ID)  REFERENCES GRP(GRP_ID),
       CONSTRAINT FK_USR_GRP_USR
       FOREIGN KEY (USER_ID)  REFERENCES USERS(USER_ID)
) ENGINE=InnoDB;


CREATE TABLE PERMISSIONS (
       MENU_ID              varchar(20) NOT NULL,
       ROLE_ID              varchar(20) NOT NULL,
       PRIMARY KEY (ROLE_ID, MENU_ID)
) ENGINE=InnoDB ;


CREATE TABLE SEQUENCE_GEN (
       ATTRIBUTE            varchar(32) NOT NULL,
       NEXT_ID              int NULL,
       PRIMARY KEY (ATTRIBUTE)
) ENGINE=InnoDB;

CREATE TABLE RELATION_SET (
       RELATION_SET_ID      varchar(20) NOT NULL,
       DESCRIPTION          varchar(80) NULL,
       PRIMARY KEY (RELATION_SET_ID)
)ENGINE=InnoDB;

CREATE TABLE RELATION_CATEGORY (
       RELATION_SET_ID      varchar(20) NOT NULL,
       CATEGORY_ID          varchar(20) NOT NULL,
       PRIMARY KEY (RELATION_SET_ID, CATEGORY_ID), 
	CONSTRAINT FK_RELATION_CATEGORY_CATEGORY
       FOREIGN KEY (CATEGORY_ID) REFERENCES CATEGORY(CATEGORY_ID),
	CONSTRAINT FK_RELATION_CATEGORY_RELATION_SET
       FOREIGN KEY (RELATION_SET_ID) REFERENCES RELATION_SET(RELATION_SET_ID)
) ENGINE=InnoDB;


CREATE TABLE RELATIONSHIP (
       RELATIONSHIP_ID      varchar(20) NOT NULL,
       RELATION_SET_ID      varchar(20) NULL,
       ITEM_OBJ             varchar(20) NULL,
       ITEM_ID              varchar(20) NULL,
       RANK                 int NULL,
       ACTIVE               bit NULL,
       PRIMARY KEY (RELATIONSHIP_ID), 
	CONSTRAINT FK_RELATIONSHIP_RELATION_SET
       FOREIGN KEY (RELATION_SET_ID)
                             REFERENCES RELATION_SET(RELATION_SET_ID)
) ENGINE=InnoDB;


CREATE TABLE RELATION_TYPE (
       RELATION_TYPE_ID     varchar(20) NOT NULL,
       DESCRIPTION          varchar(80) NOT NULL,
       PRIMARY KEY (RELATION_TYPE_ID)
) ENGINE=InnoDB;


CREATE TABLE RELATION_SOURCE (
       RELATION_TYPE_ID     varchar(20) NOT NULL,
       SOURCE_OBJ           varchar(20) NOT NULL,
       SOURCE_ID            varchar(20) NOT NULL,
       RELATION_SET_ID      varchar(20) NULL,
       PRIMARY KEY (RELATION_TYPE_ID, SOURCE_OBJ, SOURCE_ID), 
       CONSTRAINT FK_RELATION_SOURCE_RELATION_SET
       FOREIGN KEY (RELATION_SET_ID)
                             REFERENCES RELATION_SET (RELATION_SET_ID), 
	CONSTRAINT FK_RELATION_SOURCE_RELATION_TYPE
       FOREIGN KEY (RELATION_TYPE_ID)
                             REFERENCES RELATION_TYPE(RELATION_TYPE_ID)
) ENGINE=InnoDB;

CREATE TABLE CATEGORY_TYPE (
       CATEGORY_ID          varchar(20) NOT NULL,
       TYPE_ID              varchar(20) NOT NULL,
       PRIMARY KEY (TYPE_ID, CATEGORY_ID), 
	CONSTRAINT FK_CATEGORY_TYPE_CATEGORY
       FOREIGN KEY (CATEGORY_ID)
                             REFERENCES CATEGORY(CATEGORY_ID), 
	CONSTRAINT FK_CATEGORY_METADATA_TYPE
       FOREIGN KEY (TYPE_ID)
                             REFERENCES METADATA_TYPE(TYPE_ID)
) ENGINE=InnoDB;


CREATE TABLE IMAGE (
       IMAGE_ID             varchar(20) NOT NULL,
       IMAGE_FILE           varchar(80) NULL,
       IMAGE_TYPE           varchar(20) NULL,
       DESCRIPTION          varchar(250) NULL,
       MIME_TYPE            varchar(30) NULL,
       URL                  varchar(100) NULL,
       FILE_SIZE            int NULL,
       IMAGE_BLOB           BLOB NULL,
       PRIMARY KEY (IMAGE_ID)
) ENGINE=InnoDB;

CREATE TABLE LOCATION (
       LOCATION_ID           varchar(32) NOT NULL,
	   NAME					VARCHAR(80) NULL,
       COUNTRY              varchar(30) NULL,
	   BLDG_NUM				VARCHAR(10) NULL,
	   STREET_DIRECTION		VARCHAR(20) NULL,
       ADDRESS1             varchar(45) NULL,
       ADDRESS2             varchar(45) NULL,
	   ADDRESS3             varchar(45) NULL,
       CITY                 varchar(30) NULL,
       STATE                varchar(15) NULL,
       POSTAL_CD            varchar(10) NULL,
	   ORGANIZATION_ID		VARCHAR(20) NULL,
       ACTIVE				INT NULL DEFAULT 1,
       PRIMARY KEY (LOCATION_ID)
) ENGINE=InnoDB;

CREATE TABLE ADDRESS (
       ADDRESS_ID           varchar(32) NOT NULL,
	   NAME					VARCHAR(40) NULL,
       COUNTRY              varchar(30) NULL,
	   BLDG_NUM				VARCHAR(10) NULL,
	   STREET_DIRECTION		VARCHAR(20) NULL,
       ADDRESS1             varchar(45) NULL,
       ADDRESS2             varchar(45) NULL,
	   ADDRESS3             varchar(45) NULL,
	   ADDRESS4             varchar(45) NULL,
	   ADDRESS5             varchar(45) NULL,
	   ADDRESS6             varchar(45) NULL,
	   ADDRESS7             varchar(45) NULL,
       CITY                 varchar(30) NULL,
       STATE                varchar(15) NULL,
       POSTAL_CD            varchar(10) NULL,
       IS_DEFAULT           int NULL DEFAULT 0,
       DESCRIPTION          varchar(100) NULL,
       ACTIVE				INT NULL DEFAULT 1,
       PARENT_ID            varchar(32) NULL,
       PARENT_TYPE          varchar(30) NULL,
       PRIMARY KEY (ADDRESS_ID)
) ENGINE=InnoDB;




CREATE TABLE EMAIL_ADDRESS (
       EMAIL_ID             varchar(32) NOT NULL,
	   NAME					VARCHAR(40) NULL,
       DESCRIPTION          varchar(100) NULL,
       EMAIL_ADDRESS        varchar(320) NULL,
       IS_DEFAULT           int NULL,
       ACTIVE				INT NULL DEFAULT 1,
       PARENT_ID            varchar(32) NULL,
       PARENT_TYPE          varchar(30) NULL,
       PRIMARY KEY (EMAIL_ID)
) ENGINE=InnoDB;

CREATE TABLE PHONE (
       PHONE_ID             varchar(32) NOT NULL,
	   NAME					VARCHAR(40) NULL,
       AREA_CD              varchar(10) NULL,
       COUNTRY_CD           varchar(3) NULL,
       DESCRIPTION          varchar(100) NULL,
       PHONE_NBR            varchar(50) NULL,
       PHONE_EXT			VARCHAR(20) NULL,
       IS_DEFAULT           int NULL,
       ACTIVE				INT NULL DEFAULT 1,
       PARENT_ID            varchar(32) NULL,
       PARENT_TYPE          varchar(30) NULL,
       PRIMARY KEY (PHONE_ID)
) ENGINE=InnoDB;

CREATE TABLE ROLE(
       ROLE_ID              varchar(32) NOT NULL,
       SERVICE_ID           varchar(20) NOT NULL,
       ROLE_NAME            varchar(40) NULL,
       CREATE_DATE			DATETIME NULL,
       CREATED_BY			VARCHAR(20) NULL,
       DESCRIPTION          varchar(255) NULL,
       PROVISION_OBJ_NAME   varchar(80) NULL,
	   PARENT_ROLE_ID		VARCHAR(32) NULL,
	   TYPE_ID				VARCHAR(20) NULL,
	   INHERIT_FROM_PARENT	INT NULL,
	   OWNER_ID				VARCHAR(32) NULL,
       STATUS				VARCHAR(20) NULL,
      	 PRIMARY KEY (ROLE_ID, SERVICE_ID), 
	CONSTRAINT FK_ROLE_SERVICE
       FOREIGN KEY (SERVICE_ID) REFERENCES SECURITY_DOMAIN(DOMAIN_ID)
) ENGINE=InnoDB;



CREATE TABLE ROLE_ATTRIBUTE (
	   ROLE_ATTR_ID			VARCHAR(32) NOT NULL,
       NAME                 varchar(32) NULL,
       VALUE                varchar(255) NULL,
       METADATA_ID          varchar(20) NULL,
       ROLE_ID        	 	VARCHAR(32) NOT NULL,
	   SERVICE_ID           VARCHAR(20) NOT NULL,
	   ATTR_GROUP			VARCHAR(20) NULL,
       PRIMARY KEY (ROLE_ATTR_ID),
	CONSTRAINT FK_ROLE_ROLE_ATTRIBUTE
       FOREIGN KEY (ROLE_ID,SERVICE_ID)
                             REFERENCES ROLE(ROLE_ID,SERVICE_ID)
) ENGINE=InnoDB;



CREATE TABLE GRP_ROLE(
	   SERVICE_ID           varchar(20) NOT NULL,
	   ROLE_ID              varchar(32) NOT NULL,
       GRP_ID               varchar(32) NOT NULL,
	PRIMARY KEY (SERVICE_ID, ROLE_ID, GRP_ID),
       CONSTRAINT FK_GRP_ROLE_ROLE
       FOREIGN KEY (ROLE_ID,SERVICE_ID)  REFERENCES ROLE(ROLE_ID,SERVICE_ID),
       CONSTRAINT FK_GRP_ROLE_GRP
       FOREIGN KEY (GRP_ID)  REFERENCES GRP(GRP_ID)
) ENGINE=InnoDB;


CREATE TABLE USER_ROLE (
		USER_ROLE_ID		VARCHAR(32) NOT NULL,
	   	SERVICE_ID			VARCHAR(20) NOT NULL,
	   	ROLE_ID             VARCHAR(32) NOT NULL,
       	USER_ID             varchar(32) NOT NULL,
		STATUS				VARCHAR(20) NULL,	   
    	CREATE_DATE			DATETIME NULL,
    	CREATED_BY			VARCHAR(20) NULL,
	PRIMARY KEY (SERVICE_ID, ROLE_ID, USER_ID),
	   CONSTRAINT FK_USR_ROLE_ROLE
        FOREIGN KEY (ROLE_ID,SERVICE_ID)
                             REFERENCES ROLE(ROLE_ID,SERVICE_ID), 
       CONSTRAINT FK_USR_ROLE_USR
		FOREIGN KEY (USER_ID)
                             REFERENCES USERS(USER_ID)
) ENGINE=InnoDB;


CREATE TABLE RESOURCE_TYPE(
       RESOURCE_TYPE_ID     varchar(20) NOT NULL,
       DESCRIPTION          varchar(100) NULL,
       METADATA_TYPE_ID     varchar(20) NULL,
       PROVISION_RESOURCE   int NULL,
       PROCESS_NAME         varchar(80) NULL,
  PRIMARY KEY (RESOURCE_TYPE_ID)
) ENGINE=InnoDB;

CREATE TABLE RES(
       RESOURCE_ID          varchar(32) NOT NULL,
       RESOURCE_TYPE_ID     varchar(20) NULL,
       DESCRIPTION          varchar(100) NULL,
	   	 NAME      			varchar(40) NULL,
       RESOURCE_PARENT      varchar(32) NULL,
       BRANCH_ID            varchar(32) NULL,
       CATEGORY_ID	    	varchar(20) NULL,
       DISPLAY_ORDER	    int NULL  DEFAULT 1,
       NODE_LEVEL	    	int NULL  DEFAULT 1,
       SENSITIVE_APP		int null  DEFAULT 0,
	   MANAGED_SYS_ID		VARCHAR(40) NULL,
       PRIMARY KEY (RESOURCE_ID), 
	CONSTRAINT FK_RESOURCE_RESOURCE_TYPE
       FOREIGN KEY (RESOURCE_TYPE_ID)  REFERENCES RESOURCE_TYPE(RESOURCE_TYPE_ID),
	CONSTRAINT FK_RESOURCE_CATEGORY
       FOREIGN KEY (CATEGORY_ID) REFERENCES CATEGORY(CATEGORY_ID)
) ENGINE=InnoDB;


CREATE TABLE RESOURCE_PROP (
       RESOURCE_PROP_ID     varchar(32) NOT NULL,
       RESOURCE_ID          varchar(32) NULL,
       METADATA_ID          varchar(20) NULL,
	     NAME					VARCHAR(20) NOT NULL,
       PROP_VALUE           varchar(500) NULL,
       PROP_GROUP           VARCHAR(20) NULL,
       PRIMARY KEY (RESOURCE_PROP_ID), 
	CONSTRAINT FK_RESOURCE_PROP_RESOURCE
       FOREIGN KEY (RESOURCE_ID) REFERENCES RES(RESOURCE_ID)
) ENGINE=InnoDB;

CREATE TABLE PRIVILEGE(
       PRIVILEGE_ID         varchar(20) NOT NULL,
       DESCRIPTION          varchar(40) NULL,
       PRIMARY KEY (PRIVILEGE_ID)
)ENGINE=InnoDB;

CREATE TABLE RESOURCE_USER(
       RESOURCE_ID          varchar(32) NOT NULL,
       USER_ID              varchar(32) NOT NULL,
       PRIVILEGE_ID         varchar(20) NOT NULL,
       PRIMARY KEY (RESOURCE_ID, USER_ID, PRIVILEGE_ID), 
       CONSTRAINT FK_RESOURCE_USER_PRIVILEGE
       FOREIGN KEY (PRIVILEGE_ID)
				 REFERENCES PRIVILEGE(PRIVILEGE_ID),
	CONSTRAINT FK_RESOURCE_USER_USERS
         FOREIGN KEY (USER_ID)
                             REFERENCES USERS(USER_ID), 
       FOREIGN KEY (RESOURCE_ID)
                             REFERENCES RES(RESOURCE_ID)
) ENGINE=InnoDB;

CREATE TABLE RESOURCE_ROLE(
       RESOURCE_ID          varchar(32) NOT NULL,
       ROLE_ID              varchar(32) NOT NULL,
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
                             REFERENCES RES(RESOURCE_ID)
) ENGINE=InnoDB;



CREATE TABLE USER_IND (
       USER_IND             varchar(20) NOT NULL,
       USER_DESCRIPTION     varchar(40) NULL,
       PRIMARY KEY (USER_IND)
) ENGINE=InnoDB;

CREATE TABLE LOGIN(
       SERVICE_ID           varchar(20) NOT NULL,
       LOGIN                varchar(320) NOT NULL,
	   MANAGED_SYS_ID 		VARCHAR(20) NOT NULL,
	   CANONICAL_NAME		VARCHAR(100) NULL,
       USER_ID              varchar(32) NULL,
       PASSWORD             varchar(80) NULL,
       PWD_EQUIVALENT_TOKEN varchar(80) NULL,
       PWD_CHANGED          datetime NULL,				/* last time the password was changed */
       PWD_EXP              datetime NULL,				/* date when the pswd expires */
       RESET_PWD	    	integer NOT NULL DEFAULT 0,		/* 1 - PASSWORD HAS BEEN RESET. FIRST TIME LOGIN*/
       IS_LOCKED	        integer NOT NULL DEFAULT 0,		/* IS THIS IDENTITY LOCKED  */
       STATUS		    	varchar(20) NULL,			/* STATUS OF THIS IDENTITY  */
       GRACE_PERIOD			DATETIME NULL, 				/* WHEN THE GRACE_PRD_EXP 	*/	
       CREATE_DATE			DATETIME NULL,
       CREATED_BY			VARCHAR(32) NULL,
       CURRENT_LOGIN_HOST	VARCHAR(40) NULL,		/* HOST FROM WHICH YOU ARE LOGGED IN*/	
       AUTH_FAIL_COUNT		INTEGER NULL,				/* NUMBER OF TIMES AUTH. HAS FAILED */
       LAST_AUTH_ATTEMPT	DATETIME NULL,	    	/* WHEN WAS THE LAST AUTH. ATTEMPT  */   
	   LAST_LOGIN			DATETIME NULL,			/* LAST TIME THE PERSON SUCCESSFULLY LOGGED IN */
	   IS_DEFAULT			INTEGER NULL DEFAULT 0,
       PRIMARY KEY (SERVICE_ID, LOGIN,MANAGED_SYS_ID), 
	CONSTRAINT FK_LOGIN_USERS
       FOREIGN KEY (USER_ID)
                             REFERENCES USERS(USER_ID), 
       CONSTRAINT FK_LOGIN_SERVICE
       FOREIGN KEY (SERVICE_ID)
                             REFERENCES SECURITY_DOMAIN(DOMAIN_ID)
) ENGINE=InnoDB;

CREATE TABLE LOGIN_ATTRIBUTE (
	   LOGIN_ATTR_ID		VARCHAR(32) NOT NULL,
       LOGIN                varchar(320) NOT NULL,
       SERVICE_ID	    	varchar(20) NOT NULL,
       NAME                 varchar(20) NULL,
       VALUE                varchar(255) NULL,
       METADATA_ID          varchar(20) NULL,
	   ATTR_GROUP			VARCHAR(20) NULL,
       PRIMARY KEY (LOGIN_ATTR_ID), 
	CONSTRAINT FK_LOGIN_LOGIN_ATTRIBUTE
       FOREIGN KEY (SERVICE_ID, LOGIN)
                             REFERENCES LOGIN(SERVICE_ID, LOGIN)
) ENGINE=InnoDB;

CREATE TABLE AUTH_STATE (
       USER_ID              varchar(32) NOT NULL,
       AUTH_STATE           numeric(5,1) NULL,
       TOKEN                varchar(100) NULL,
       AA                   varchar(20) NULL,
       EXPIRATION           numeric(18,0) NULL,
       LAST_LOGIN	    datetime NULL,
       IP_ADDRESS	    varchar(20) NULL,
       PRIMARY KEY (USER_ID), 
	CONSTRAINT FK_AUTH_STATE_USERS FOREIGN KEY (USER_ID) REFERENCES USERS (USER_ID)
) ENGINE=InnoDB;



CREATE TABLE PWD_HISTORY (
       LOGIN                varchar(320) NOT NULL,
       SERVICE_ID	    	varchar(20) NOT NULL,
       DATE_CREATED         datetime NOT NULL,
       PASSWORD             varchar(80) NULL,
       PRIMARY KEY (LOGIN, SERVICE_ID, DATE_CREATED), 
	CONSTRAINT FK_PWD_HISTORY_LOGIN
       FOREIGN KEY (SERVICE_ID, LOGIN)
                             REFERENCES LOGIN(SERVICE_ID, LOGIN)
) ENGINE=InnoDB;

CREATE TABLE PWD_LOGIN_HISTORY (
       PWD_LOGIN_ID         varchar(32) NOT NULL,
       SERVICE_ID           varchar(20) NULL,
       LOGIN                varchar(30) NULL,
       PASSWORD             varchar(80) NULL,
       DATE_CREATED         datetime NULL,
       PRIMARY KEY (PWD_LOGIN_ID), 
	CONSTRAINT FK_PWD_LOGIN_HISTORY_LOGIN
       FOREIGN KEY (SERVICE_ID, LOGIN)
                             REFERENCES LOGIN(SERVICE_ID, LOGIN)
) ENGINE=InnoDB;
CREATE TABLE CREDENTIALS (
       USER_ID              varchar(32) NOT NULL,
       CREDENTIAL_TYPE      varchar(20) NOT NULL,
       VALUE                varchar(100) NULL,
       IS_PUBLIC            bit,
       PRIMARY KEY (USER_ID, CREDENTIAL_TYPE), 
	CONSTRAINT FK_CREDENTIALS_USERS
       FOREIGN KEY (USER_ID)
                             REFERENCES USERS(USER_ID)
) ENGINE=InnoDB;

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
      USER_ID              varchar(32) NULL,
      SERVICE_ID           varchar(20) NULL,
      LOGIN_ID             varchar(50) NULL,     
      HOST                 varchar(60) NULL,              /* HOST WHERE THE EVENT OCCURRED */
      CLIENT_ID            VARCHAR(20) NULL,              /* CLIENT, AGENT OR INTERFACE WHERE THIS EVENT OCCURRED      */
      REQ_URL              varchar(255) NULL,
      LINKED_LOG_ID		 	VARCHAR(32) NULL,			  /* INDICATES IF THIS EVENT IS LINKED TO ANOTHER EVENT 		*/
	  LINK_SEQUENCE			INT NULL,
	  ORIG_OBJECT_STATE   	TEXT NULL,                     /* SNAP SHOT OF THE ORIGINAL OBJECT                 */
      NEW_OBJECT_STATE		TEXT NULL,
	  LOG_HASH   			VARCHAR(80) NULL,                     /* VALUES THAT WERE CHANGED DURING THIS OPERATION                 */
	  SRC_SYSTEM_ID 		VARCHAR(40) NULL, 
 	  TARGET_SYSTEM_ID 		VARCHAR(40) NULL,
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
) ENGINE=InnoDB;



commit;