package org.openiam.selfsrvc.hire;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.base.AttributeOperationEnum;
import org.openiam.idm.srvc.meta.dto.MetadataElement;
import org.openiam.idm.srvc.meta.ws.MetadataWebService;
import org.openiam.idm.srvc.mngsys.dto.ApproverAssociation;
import org.openiam.idm.srvc.mngsys.service.ManagedSystemDataService;
import org.openiam.idm.srvc.res.service.ResourceDataService;
import org.openiam.idm.srvc.user.dto.Supervisor;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.dto.UserAttribute;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;

import java.util.ArrayList;
import java.util.List;


public class NewHireValidator implements Validator {

	private static final Log log = LogFactory.getLog(NewHireValidator.class);
    protected ResourceDataService resourceDataService;
    protected ManagedSystemDataService managedSysService;
    protected String requestType;
    protected MetadataWebService metadataService;
	
	public boolean supports(Class cls) {
		 return NewHireCommand.class.equals(cls);
	}

    public void validateNewUserType(Object cmd, Errors err) {


		NewHireCommand newHireCmd =  (NewHireCommand) cmd;

		User user = newHireCmd.getUser();

		if (user.getMetadataTypeId() == null ||
			user.getMetadataTypeId().length() == 0) {
			err.rejectValue("user.metadataTypeId", "required");
			return;
		}
		// set metadata attributes - if they exist
		if (user.getLastName() == null || user.getLastName().length() == 0) {
			MetadataElement[] elementAry = metadataService.getMetadataElementByType( user.getMetadataTypeId() ).getMetadataElementAry();
			List<UserAttribute> attrList = toAttributeList(elementAry);
			newHireCmd.setAttributeList(attrList);
		}


	}

	public void validateNewHireForm(Object cmd, Errors err) {
		// TODO Auto-generated method stub
		NewHireCommand newHireCmd =  (NewHireCommand) cmd;
		
		
		User user = newHireCmd.getUser();
		log.info("User from form = " + user);
		

        if (newHireCmd.getUser().getFirstName() == null || newHireCmd.getUser().getFirstName().length() == 0) {
			err.rejectValue("user.firstName", "required");

		}
		if (user.getLastName() == null || user.getLastName().length() == 0) {
			err.rejectValue("user.lastName", "required");
		}


		if (newHireCmd.getStatus() == null || newHireCmd.getStatus().equalsIgnoreCase("-") ) {
			err.rejectValue("status", "required");
		}
		if (user.getSex() == null || user.getSex().equalsIgnoreCase("-") ) {
			err.rejectValue("user.sex", "required");
		}
		if (newHireCmd.getRole() == null || newHireCmd.getRole().equalsIgnoreCase("-") ) {
			err.rejectValue("role", "required");
        }
        // check if the required attributes have been filled in
        List<UserAttribute> attrList =  newHireCmd.getAttributeList();
        if (attrList != null) {
            for ( UserAttribute ua : attrList) {
                if (ua.getRequired() == true ) {
                    if (ua.getValue() == null || ua.getValue().length() == 0) {
                        err.rejectValue("attributeList", "required");
                        return;
                    }
                }
            }
        }

        //


        // supervisor is required if the form uses supervisor as the approver
        ApproverAssociation ap = managedSysService.getApproverByRequestType(requestType, 1);

        if (ap.getAssociationType().equalsIgnoreCase("SUPERVISOR")) {
            if (newHireCmd.getSupervisorId() == null || newHireCmd.getSupervisorId().length() == 0) {
                err.rejectValue("supervisorId", "required");
            }
        }
		
	}

    private List<UserAttribute> toAttributeList(MetadataElement[] elementAry) {
		List<UserAttribute> attrList = new ArrayList<UserAttribute>();
		if (elementAry == null) {
			return null;
		}

		for (MetadataElement elem  :elementAry) {
			UserAttribute attr = new UserAttribute();
			attr.setMetadataElementId(elem.getMetadataElementId());
			attr.setName(elem.getAttributeName());
            attr.setRequired(elem.isRequired());
			attrList.add(attr);
		}
		return attrList;

	}


	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object cmd, Errors arg1) {
		NewHireCommand newHireCmd =  (NewHireCommand) cmd;
		
	}


    public ResourceDataService getResourceDataService() {
        return resourceDataService;
    }

    public void setResourceDataService(ResourceDataService resourceDataService) {
        this.resourceDataService = resourceDataService;
    }

    public ManagedSystemDataService getManagedSysService() {
        return managedSysService;
    }

    public void setManagedSysService(ManagedSystemDataService managedSysService) {
        this.managedSysService = managedSysService;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public MetadataWebService getMetadataService() {
        return metadataService;
    }

    public void setMetadataService(MetadataWebService metadataService) {
        this.metadataService = metadataService;
    }
}
