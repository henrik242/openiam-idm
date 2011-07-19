package org.openiam.webadmin.res;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public class ReconConfigurationValidator implements Validator {

    private static final Log log = LogFactory.getLog(ReconConfigurationValidator.class);

    public boolean supports(Class cls) {
        return ReconConfigurationCommand.class.equals(cls);
    }

    /* (non-Javadoc)
      * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
      */
    public void validate(Object cmd, Errors arg1) {
        ReconConfigurationCommand synchCmd = (ReconConfigurationCommand) cmd;

    }


}
