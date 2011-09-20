package org.openiam.webadmin.sync;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;

import org.openiam.idm.srvc.synch.dto.SynchConfig;

public class SynchConfigurationValidator implements Validator {

	private static final Log log = LogFactory.getLog(SynchConfigurationValidator.class);
	
	public boolean supports(Class cls) {
		 return SynchConfigurationCommand.class.equals(cls);
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object cmd, Errors err) {
		SynchConfigurationCommand synchCmd =  (SynchConfigurationCommand) cmd;

        SynchConfig config = synchCmd.getSyncConfig();


        if (config.getSynchAdapter() == null || config.getSynchAdapter().length() ==0 ) {
            err.rejectValue("syncConfig.synchAdapter","required");
        }

        if (config.getValidationRule() == null || config.getValidationRule().length() ==0 ) {
            err.rejectValue("syncConfig.validationRule","required");
        }

        if (config.getTransformationRule() == null || config.getTransformationRule().length() ==0 ) {
            err.rejectValue("syncConfig.transformationRule","required");
        }
        if (config.getName() == null || config.getName().length() ==0 ) {
            err.rejectValue("syncConfig.name","required");
        }

	}



	


}
