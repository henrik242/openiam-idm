CREATE TABLE SECURITY_DOMAIN (
       DOMAIN_ID            varchar(20) NOT NULL,
       NAME         		varchar(40) NULL,
       STATUS				VARCHAR(20) NULL,
       PRIMARY KEY (DOMAIN_ID)
);


CREATE TABLE LANGUAGE (
       LANGUAGE_CD          varchar(5) NOT NULL,
       LANGUAGE             varchar(20) NULL,
       PRIMARY KEY (LANGUAGE_CD)
);


CREATE TABLE EXCLUDE_WORD_LIST (
       WORD                 varchar(30) NOT NULL,
       LANGUAGE_CD          varchar(5) NOT NULL,
       PRIMARY KEY (WORD, LANGUAGE_CD), 
	CONSTRAINT FK_EXCLUDE_WORD_LIST_LANGUAGE
       FOREIGN KEY (LANGUAGE_CD)
                             REFERENCES LANGUAGE(LANGUAGE_CD)
);

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
);

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
);



CREATE TABLE METADATA_TYPE (
       TYPE_ID              varchar(20) NOT NULL,
       DESCRIPTION          varchar(40) NULL,
	   ACTIVE				INTEGER NULL DEFAULT 0,
	   SYNC_MANAGED_SYS		INTEGER NULL DEFAULT 0,
       PRIMARY KEY (TYPE_ID)
);


CREATE TABLE METADATA_ELEMENT (
       METADATA_ID          varchar(20) NOT NULL,
       TYPE_ID              varchar(20) NULL,
       ATTRIBUTE_NAME       varchar(20) NULL,
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
		MULTI_VALUE			VARCHAR(500),
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
);





CREATE TABLE COMPANY (
       COMPANY_ID           varchar(20) NOT NULL,
       COMPANY_NAME         nvarchar(60) NULL,
       LST_UPDATE           datetime NULL,
       LST_UPDATED_BY		VARCHAR(20) NULL,
       PARENT_ID            varchar(20) NULL,
       STATUS			    VARCHAR(20) NULL,
       TYPE_ID              varchar(20) NULL,
	   CREATE_DATE			datetime NULL,
	   CREATED_BY			VARCHAR(20) NULL,
	   ALIAS				VARCHAR(40) NULL,
	   DESCRIPTION			VARCHAR(40) NULL,
	   DOMAIN_NAME			VARCHAR(40) NULL,
	   LDAP_STR				VARCHAR(255) NULL,				
 	   PRIMARY KEY (COMPANY_ID), 
	CONSTRAINT FK_COMPANY_METADATA_TYPE
       FOREIGN KEY (TYPE_ID)
                             REFERENCES METADATA_TYPE(TYPE_ID)
);



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
);


CREATE TABLE COMPANY_ATTRIBUTE (
       COMPANY_ATTR_ID      varchar(20) NOT NULL,
       COMPANY_ID           varchar(20) NULL,
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
);


CREATE TABLE USERS(
       USER_ID              varchar(20) NOT NULL,
       FIRST_NAME           varchar(40) NULL,
       LAST_NAME            varchar(40) NULL,
       MIDDLE_INIT          VARCHAR(40) NULL,
       TYPE_ID              varchar(20) NULL,
       TITLE                varchar(30) NULL,
	   DEPT_CD				VARCHAR(50) NULL, /* Dept - can POINT TO AN OU in a directory */
	   DEPT_NAME			VARCHAR(100) NULL,
	   MAIL_CODE			VARCHAR(50) NULL,	   
       DIVISION				VARCHAR(50) NULL,
		COST_CENTER			VARCHAR(20) NULL,
		STATUS              varchar(20) NULL,
       BIRTHDATE            datetime NULL,
       SEX                  char(1) NULL,
	   CREATE_DATE			DATEtime NULL,
 	   CREATED_BY			VARCHAR(20) NULL,
	   LAST_UPDATE			DATETIME NULL,
	   LAST_UPDATED_BY		VARCHAR(20) NULL,
       PREFIX               VARCHAR(4) NULL,
       SUFFIX               VARCHAR(20) NULL,
       USER_TYPE_IND		VARCHAR(20) NULL,
       EMPLOYEE_ID			VARCHAR(20) NULL,
       EMPLOYEE_TYPE		VARCHAR(20) NULL,	/* TEMP, FULL-TIME, INTERN, PART-TIME, CONTRACTOR,  */			
       LOCATION_CD			VARCHAR(50) NULL,
	   LOCATION_NAME		VARCHAR(100) NULL,
       COMPANY_ID        	VARCHAR(20) NULL,
       COMPANY_OWNER_ID		VARCHAR(20) NULL,
       JOB_CODE				VARCHAR(50) NULL,
       MANAGER_ID			VARCHAR(20) NULL,
	   START_DATE			DATE NULL,
       LAST_DATE			DATE NULL,
 	   MAIDEN_NAME 			VARCHAR(40) NULL,
 	   NICKNAME 			VARCHAR(40) NULL,
	   PASSWORD_THEME		VARCHAR(20) NULL,
       PRIMARY KEY (USER_ID), 
	CONSTRAINT FK_USERS_METADATA_TYPE
         FOREIGN KEY (TYPE_ID)
                             	REFERENCES METADATA_TYPE(TYPE_ID)
);


ALTER TABLE USERS
 ADD CONSTRAINT FK_USERS_METADATA_TYPE FOREIGN KEY (TYPE_ID) REFERENCES demo.metadata_type (TYPE_ID) ON UPDATE RESTRICT ON DELETE RESTRICT;




CREATE TABLE ORG_STRUCTURE (
	ORG_STRUCTURE_ID	VARCHAR(20) NOT NULL,
	SUPERVISOR_ID		VARCHAR(20) NOT NULL,
	STAFF_ID			VARCHAR(20) NOT NULL,
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
);


CREATE TABLE USER_NOTE(
       USER_NOTE_ID   varchar(20) NOT NULL,
       USER_ID              varchar(20) NULL,
       NOTE_TYPE			VARCHAR(20) NULL,
       DESCRIPTION			VARCHAR(1000) NULL,
       CREATE_DATE			DATETIME NULL,
       CREATED_BY			VARCHAR(20) NULL,
       PRIMARY KEY (USER_NOTE_ID), 
	CONSTRAINT FK_USER_NOTE_USERS
       FOREIGN KEY (USER_ID)
                             REFERENCES USERS(USER_ID)
);

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
);


CREATE TABLE USER_ATTRIBUTES (
       ID                   varchar(20) NOT NULL,
       USER_ID              varchar(20) NULL,
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
);



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
);

CREATE TABLE SERVICE_CONFIG (
       SERVICE_CONFIG_ID    varchar(20) NOT NULL,
       SERVICE_ID           varchar(20) NULL,
       NAME                 varchar(40) NULL,
       VALUE                varchar(40) NULL,
       PRIMARY KEY (SERVICE_CONFIG_ID),
       CONSTRAINT FK_SRV_SRV_CONF
       FOREIGN KEY (SERVICE_ID)
                             REFERENCES SERVICE(SERVICE_ID)
);


CREATE TABLE STATUS (
       STATUS_CD           	varchar(20) NOT NULL,
	   STATUS_TYPE			VARCHAR(20) NULL,
       DESCRIPTION          varchar(80) NULL,
       CODE_GROUP			VARCHAR(40) NOT NULL,
       LANGUAGE_CD			VARCHAR(2) NOT NULL,
       WEIGHT				INT	NULL,
       PRIMARY KEY (CODE_GROUP, STATUS_CD, LANGUAGE_CD)
);


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
);


CREATE TABLE GRP (
       GRP_ID               varchar(20) NOT NULL,
       GRP_NAME             varchar(40) NULL,
       CREATE_DATE			DATETIME NULL,
       CREATED_BY			VARCHAR(20) NULL,
       COMPANY_ID			VARCHAR(20) NULL,
       PARENT_GRP_ID		VARCHAR(20) NULL,
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
       
);


CREATE TABLE GRP_ATTRIBUTES (
       ID                   varchar(20) NOT NULL,
       GRP_ID               varchar(20) NULL,
       METADATA_ID          varchar(20) NULL,
       NAME                 varchar(20) NULL,
       VALUE                varchar(255) NULL,
       PRIMARY KEY (ID),
	CONSTRAINT FK_GRP_ATTRIBUTES_METADATA_ELEMENT
       FOREIGN KEY (METADATA_ID)
                             REFERENCES METADATA_ELEMENT(METADATA_ID), 
       FOREIGN KEY (GRP_ID)
                             REFERENCES GRP(GRP_ID)
);


CREATE TABLE USER_GRP(
   	GRP_ID               varchar(20) NOT NULL,
   	USER_ID              varchar(20) NOT NULL,
	PRIMARY KEY (GRP_ID, USER_ID),
       CONSTRAINT FK_USR_GRP_GPR
       FOREIGN KEY (GRP_ID)  REFERENCES GRP(GRP_ID),
       CONSTRAINT FK_USR_GRP_USR
       FOREIGN KEY (USER_ID)  REFERENCES USERS(USER_ID)
);


CREATE TABLE PERMISSIONS (
       MENU_ID              varchar(20) NOT NULL,
       ROLE_ID              varchar(20) NOT NULL,
       PRIMARY KEY (ROLE_ID, MENU_ID)
) ;


CREATE TABLE SEQUENCE_GEN (
       ATTRIBUTE            varchar(32) NOT NULL,
       NEXT_ID              int NULL,
       PRIMARY KEY (ATTRIBUTE)
);

CREATE TABLE RELATION_SET (
       RELATION_SET_ID      varchar(20) NOT NULL,
       DESCRIPTION          varchar(80) NULL,
       PRIMARY KEY (RELATION_SET_ID)
);

CREATE TABLE RELATION_CATEGORY (
       RELATION_SET_ID      varchar(20) NOT NULL,
       CATEGORY_ID          varchar(20) NOT NULL,
       PRIMARY KEY (RELATION_SET_ID, CATEGORY_ID), 
	CONSTRAINT FK_RELATION_CATEGORY_CATEGORY
       FOREIGN KEY (CATEGORY_ID) REFERENCES CATEGORY(CATEGORY_ID),
	CONSTRAINT FK_RELATION_CATEGORY_RELATION_SET
       FOREIGN KEY (RELATION_SET_ID) REFERENCES RELATION_SET(RELATION_SET_ID)
);


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
) ;


CREATE TABLE RELATION_TYPE (
       RELATION_TYPE_ID     varchar(20) NOT NULL,
       DESCRIPTION          varchar(80) NOT NULL,
       PRIMARY KEY (RELATION_TYPE_ID)
);


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
);

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
);


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
);

CREATE TABLE ADDRESS (
       ADDRESS_ID           varchar(20) NOT NULL,
	   NAME					VARCHAR(40) NULL,
       COUNTRY              varchar(30) NULL,
       ADDRESS1             varchar(45) NULL,
       ADDRESS2             varchar(45) NULL,
       CITY                 varchar(30) NULL,
       STATE                varchar(15) NULL,
       POSTAL_CD            varchar(10) NULL,
       IS_DEFAULT           int NULL DEFAULT 0,
       DESCRIPTION          varchar(100) NULL,
       ACTIVE				INT NULL DEFAULT 1,
       PARENT_ID            varchar(20) NOT NULL,
       PARENT_TYPE          varchar(30) NOT NULL,
       PRIMARY KEY (ADDRESS_ID)
);


Alter table ADDRESS
  add column ADDRESS3 VARCHAR(45) NULL,
  add column ADDRESS4 VARCHAR(45) NULL,
  add column ADDRESS5 VARCHAR(45) NULL,
  add column ADDRESS6 VARCHAR(45) NULL,
  add column ADDRESS7 VARCHAR(45) NULL;



CREATE TABLE EMAIL_ADDRESS (
       EMAIL_ID             varchar(20) NOT NULL,
	   NAME					VARCHAR(40) NULL,
       DESCRIPTION          varchar(100) NULL,
       EMAIL_ADDRESS        varchar(100) NULL,
       IS_DEFAULT           int NULL,
       ACTIVE				INT NULL DEFAULT 1,
       PARENT_ID            varchar(20) NOT NULL,
       PARENT_TYPE          varchar(30) NOT NULL,
       PRIMARY KEY (EMAIL_ID)
);
CREATE TABLE PHONE (
       PHONE_ID             varchar(20) NOT NULL,
	   NAME					VARCHAR(40) NULL,
       AREA_CD              varchar(10) NULL,
       COUNTRY_CD           varchar(3) NULL,
       DESCRIPTION          varchar(100) NULL,
       PHONE_NBR            varchar(50) NULL,
       PHONE_EXT			VARCHAR(20) NULL,
       IS_DEFAULT           int NULL,
       ACTIVE				INT NULL DEFAULT 1,
       PARENT_ID            varchar(20) NOT NULL,
       PARENT_TYPE          varchar(30) NOT NULL,
       PRIMARY KEY (PHONE_ID)
);



commit;