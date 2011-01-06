package org.openiam.webadmin.sync;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.text.SimpleDateFormat;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractWizardFormController;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.beans.propertyeditors.CustomDateEditor;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.base.ws.ResponseStatus;
import org.openiam.idm.srvc.audit.dto.IdmAuditLog;
import org.openiam.idm.srvc.audit.dto.SearchAudit;
import org.openiam.idm.srvc.audit.ws.IdmAuditLogListResponse;
import org.openiam.idm.srvc.audit.ws.IdmAuditLogWebDataService;
import org.openiam.idm.srvc.cd.dto.ReferenceData;
import org.openiam.idm.srvc.cd.service.ReferenceDataService;
import org.openiam.idm.srvc.continfo.dto.Address;
import org.openiam.idm.srvc.continfo.dto.ContactConstants;
import org.openiam.idm.srvc.continfo.dto.EmailAddress;
import org.openiam.idm.srvc.continfo.dto.Phone;

import org.openiam.idm.srvc.user.dto.Supervisor;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.dto.UserStatusEnum;
import org.openiam.idm.srvc.grp.dto.Group;
import org.openiam.idm.srvc.grp.ws.GroupDataWebService;
import org.openiam.idm.srvc.org.dto.Organization;
import org.openiam.idm.srvc.org.service.OrganizationDataService;
import org.openiam.idm.srvc.res.dto.Resource;
import org.openiam.idm.srvc.res.service.ResourceDataService;
import org.openiam.idm.srvc.role.dto.Role;
import org.openiam.idm.srvc.role.dto.RoleId;
import org.openiam.idm.srvc.role.ws.RoleDataWebService;
import org.openiam.idm.srvc.secdomain.dto.SecurityDomain;
import org.openiam.idm.srvc.secdomain.service.SecurityDomainDataService;
import org.openiam.idm.srvc.synch.dto.SynchConfig;
import org.openiam.idm.srvc.synch.ws.IdentitySynchWebService;
import org.openiam.idm.srvc.loc.dto.Location;
import org.openiam.idm.srvc.loc.ws.LocationDataWebService;
import org.openiam.idm.srvc.menu.dto.Menu;
import org.openiam.idm.srvc.menu.ws.NavigatorDataWebService;
import org.openiam.idm.srvc.meta.dto.MetadataType;
import org.openiam.idm.srvc.meta.ws.MetadataWebService;
import org.openiam.idm.srvc.mngsys.dto.ManagedSys;
import org.openiam.idm.srvc.mngsys.dto.ManagedSystemObjectMatch;
import org.openiam.idm.srvc.mngsys.service.ManagedSystemDataService;
import org.openiam.webadmin.admin.AppConfiguration;
import org.openiam.provision.dto.ProvisionUser;
import org.openiam.provision.service.ProvisionService;


/**
 * Controller for the new hire form.
 * @author suneet
 *
 */
public class SynchLogListController extends SimpleFormController {


	protected String redirectView; 	

	protected IdentitySynchWebService synchConfig;
	private static final Log log = LogFactory.getLog(SynchLogListController.class);
	protected NavigatorDataWebService navigationDataService;
	protected IdmAuditLogWebDataService auditService;

	
	public SynchLogListController() {
		super();
	}
	

	@Override
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		df.setLenient(false);
		
		binder.registerCustomEditor(Date.class, new CustomDateEditor(df,true) );

	}
	
	protected Object formBackingObject(HttpServletRequest request) 			throws Exception {
		
		SynchLogListCommand cmd = new SynchLogListCommand();
		cmd.setStartDate(new Date(System.currentTimeMillis()));
		cmd.setEndDate(null);	
		
		return cmd;
	}
	
	protected Map referenceData(HttpServletRequest request) throws Exception {
		
		log.debug("referenceData called.");
		List<SynchConfig> configList =  synchConfig.getAllConfig().getConfigList();
		
		Map model = new HashMap();
		model.put("configList", configList);
		
		return model;
	}
	
	private void loadReferenceDataMAV(ModelAndView model) {
		List<SynchConfig> configList =  synchConfig.getAllConfig().getConfigList();
		model.addObject("configList", configList);
	}
	

	

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		SynchLogListCommand cmd = (SynchLogListCommand)command;
		
		ModelAndView mav = new ModelAndView(getSuccessView());		
		loadReferenceDataMAV(mav);
		mav.addObject("syncExecLogCmd", cmd);
		
		SearchAudit search = new SearchAudit();

		search.setObjectTypeId("SYNCH_USER");
		search.setActionId("START");
		if (cmd.getConfigId() != null && cmd.getConfigId().length() > 0) {
			search.setObjectId(cmd.getConfigId());
		}
		
		if (cmd.getStartDate() != null) {
			search.setStartDate(cmd.getStartDate());
		}
		if (cmd.getEndDate() != null) {
			search.setEndDate(cmd.getEndDate());
		}
		
		IdmAuditLogListResponse logListResp = auditService.search(search);
		if (logListResp != null && logListResp.getStatus() != ResponseStatus.FAILURE) {
			List<IdmAuditLog> logList = logListResp.getLogList();
			mav.addObject("auditLog", logList);
		}
		
		return mav;
		
		
		
        
	}


	public String getRedirectView() {
		return redirectView;
	}


	public void setRedirectView(String redirectView) {
		this.redirectView = redirectView;
	}


	public IdentitySynchWebService getSynchConfig() {
		return synchConfig;
	}


	public void setSynchConfig(IdentitySynchWebService synchConfig) {
		this.synchConfig = synchConfig;
	}




	public NavigatorDataWebService getNavigationDataService() {
		return navigationDataService;
	}


	public void setNavigationDataService(
			NavigatorDataWebService navigationDataService) {
		this.navigationDataService = navigationDataService;
	}


	public IdmAuditLogWebDataService getAuditService() {
		return auditService;
	}


	public void setAuditService(IdmAuditLogWebDataService auditService) {
		this.auditService = auditService;
	}



	
	




}
