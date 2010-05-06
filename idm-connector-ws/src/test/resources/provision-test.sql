/* Test data to test provisioning */
/* Goal - 3 systems - Oracle, AD, LDAP   
 * Each role has different system associated with them
 * When a user is created, add them to 1 role
 * Change the role, deprovision the user and add them into the role
 * Terminate a user - disable the user and remove from connected systems
 * Set password
 * Test for multiple roles
 */

insert into RESOURCE_ROLE (RESOURCE_ID, SERVICE_ID, ROLE_ID, PRIVILEGE_ID) VALUES ('100', 'USR_SEC_DOMAIN','END_USER', 'NA');

insert into RESOURCE_ROLE (RESOURCE_ID, SERVICE_ID, ROLE_ID, PRIVILEGE_ID) VALUES ('101', 'USR_SEC_DOMAIN','MANAGER', 'NA');
insert into RESOURCE_ROLE (RESOURCE_ID, SERVICE_ID, ROLE_ID, PRIVILEGE_ID) VALUES ('100', 'USR_SEC_DOMAIN','MANAGER', 'NA');

insert into RESOURCE_ROLE (RESOURCE_ID, SERVICE_ID, ROLE_ID, PRIVILEGE_ID) VALUES ('102', 'USR_SEC_DOMAIN','SECURITY_MANAGER', 'NA');

