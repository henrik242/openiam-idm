
package org.openiam.base.ws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;



/**
 * ResponseStatus provides valid values that an operation in a service can return.
 * @author suneet
 *
 */
@XmlType(name = "ResponseErrorCode")
@XmlEnum
public enum ResponseCode {

    @XmlEnumValue("objectNotFound")
    OBJECT_NOT_FOUND("objectNotFound"),

    @XmlEnumValue("classNotFound")
    CLASS_NOT_FOUND("classNotFound"),
    
    @XmlEnumValue("principalNotFound")
    PRINCIPAL_NOT_FOUND("principalNotFound"),

    @XmlEnumValue("userNotFound")
    USER_NOT_FOUND("userNotFound"),

    @XmlEnumValue("userStatus")
    USER_STATUS("userStatus"),
    
    @XmlEnumValue("supervisorlNotFound")
    SUPERVISOR_NOT_FOUND("supervisorNotFound"),

    @XmlEnumValue("DUPLICATE_PRINCIPAL")
    DUPLICATE_PRINCIPAL("DUPLICATE_PRINCIPAL"),
    
    // PASSWORD ERROR CODES
    @XmlEnumValue("failPasswordPolicy")
    FAIL_PASSWORD_POLICY("failPasswordPolicy"),
    
    // AUTHENTICATION ERROR CODES
    
    //GROUP ERROR CODES
    @XmlEnumValue("groupIdNull")
    GROUP_ID_NULL("groupIdNull"),  
    
    @XmlEnumValue("groupIdInvalid")
    GROUP_ID_INVALID("groupIdInvalid"),  
    
    // ROLE ERROR CODES
    @XmlEnumValue("roleIdNull")
    ROLE_ID_NULL("roleIdNull"), 

    @XmlEnumValue("roleIdInvalid")
    ROLE_ID_INVALID("roleIdInvalid"),

    @XmlEnumValue("success")
    SUCCESS("success"), 

    // GENERAL ERROR CODES
    @XmlEnumValue("FAIL_SQL_ERORR")
    FAIL_SQL_ERROR("FAIL_SQL_ERROR"),

    @XmlEnumValue("FAIL_CONNECTION")
    FAIL_CONNECTION("FAIL_CONNECTION"),

  // Password Policy ERROR CODES
    
    @XmlEnumValue("FAIL_ALPHA_CHAR_RULE")
    FAIL_ALPHA_CHAR_RULE("FAIL_ALPHA_CHAR_RULE"),
    
    @XmlEnumValue("FAIL_LOWER_CASE_RULE")
    FAIL_LOWER_CASE_RULE("FAIL_LOWER_CASE_RULE"),
    
    @XmlEnumValue("FAIL_UPPER_CASE_RULE")
    FAIL_UPPER_CASE_RULE("FAIL_UPPER_CASE_RULE"),
    
    @XmlEnumValue("FAIL_NON_APHANUMERIC_RULE")
    FAIL_NON_APHANUMERIC_RULE("FAIL_NON_APHANUMERIC_RULE"),
    
    @XmlEnumValue("FAIL_NUMERIC_CHAR_RULE")
    FAIL_NUMERIC_CHAR_RULE("FAIL_NUMERIC_CHAR_RULE"),   
    
    @XmlEnumValue("FAIL_HISTORY_RULE")
    FAIL_HISTORY_RULE("FAIL_HISTORY_RULE"),
    
    @XmlEnumValue("FAIL_LENGTH_RULE")
    FAIL_LENGTH_RULE("FAIL_LENGTH_RULE"),
    
    @XmlEnumValue("FAIL_NEQ_NAME")
    FAIL_NEQ_NAME("FAIL_NEQ_NAME"),
    
    @XmlEnumValue("FAIL_NEQ_PASSWORD")
    FAIL_NEQ_PASSWORD("FAIL_NEQ_PASSWORD"),
    
    @XmlEnumValue("FAIL_NEQ_PRINCIPAL")
    FAIL_NEQ_PRINCIPAL("FAIL_NEQ_PRINCIPAL"),

    @XmlEnumValue("FAIL_PASSWORD_CHANGE_FREQUENCY")
    FAIL_PASSWORD_CHANGE_FREQUENCY("FAIL_PASSWORD_CHANGE_FREQUENCY"),
    
    @XmlEnumValue("PASSWORD_POLICY_NOT_FOUND")
    PASSWORD_POLICY_NOT_FOUND("PASSWORD_POLICY_NOT_FOUND"),
    
    @XmlEnumValue("FAIL_PASSWORD_CHANGE_ALLOW")
    FAIL_PASSWORD_CHANGE_ALLOW("FAIL_PASSWORD_CHANGE_ALLOW"),
        
    @XmlEnumValue("FAIL_REJECT_CHARS_IN_PSWD")
    FAIL_REJECT_CHARS_IN_PSWD("FAIL_REJECT_CHARS_IN_PSWD"),

    @XmlEnumValue("FAIL_ENCRYPTION")
    FAIL_ENCRYPTION("FAIL_ENCRYPTION"),
    
    @XmlEnumValue("FAIL_DECRYPTION")
    FAIL_DECRYPTION("FAIL_DECRYPTION"),

    @XmlEnumValue("DIRECTORY_NAMING_EXCEPTION")
    DIRECTORY_NAMING_EXCEPTION("DIRECTORY_NAMING_EXCEPTION"),

    @XmlEnumValue("COMMUNICATION_EXCEPTION")
    COMMUNICATION_EXCEPTION("COMMUNICATION_EXCEPTION"),

    @XmlEnumValue("FAIL_CONNECTOR")
    FAIL_CONNECTOR("FAIL_CONNECTOR"),


    @XmlEnumValue("IO_EXCEPTION")
    IO_EXCEPTION("IO_EXCEPTION"),

    @XmlEnumValue("FILE_EXCEPTION")
    FILE_EXCEPTION("FILE_EXCEPTION"),

    @XmlEnumValue("SQL_EXCEPTION")
    SQL_EXCEPTION("SQL_EXCEPTION"),

    @XmlEnumValue("WS_SERVICE_EXCEPTION")
    WS_SERVICE_EXCEPTION("WS_SERVICE_EXCEPTION"),

    @XmlEnumValue("SYNCHRONIZATION_EXCEPTION")
    SYNCHRONIZATION_EXCEPTION("SYNCHRONIZATION_EXCEPTION"),

    @XmlEnumValue("LIMIT_EXCEEDED_EXCEPTION")
    LIMIT_EXCEEDED_EXCEPTION("LIMIT_EXCEEDED_EXCEPTION"),

    @XmlEnumValue("AUTHENTICATION_EXCEPTION")
    AUTHENTICATION_EXCEPTION("AUTHENTICATION_EXCEPTION"),

    @XmlEnumValue("PERMISSION_EXCEPTION")
    PERMISSION_EXCEPTION("PERMISSION_EXCEPTION"),

    @XmlEnumValue("SERVICE_UNAVAILABLE_EXCEPTION")
    SERVICE_UNAVAILABLE_EXCEPTION("SERVICE_UNAVAILABLE_EXCEPTION"),

    @XmlEnumValue("SCHEMA_VIOLATION_EXCEPTION")
    SCHEMA_VIOLATION_EXCEPTION("SCHEMA_VIOLATION_EXCEPTION"),

    @XmlEnumValue("FAIL_OTHER")
    FAIL_OTHER("FAIL_OTHER");

    
    private final String value;
  
    ResponseCode(String val) {
    	value = val;
    }

}
