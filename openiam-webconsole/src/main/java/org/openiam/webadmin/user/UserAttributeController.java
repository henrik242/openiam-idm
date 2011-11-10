package org.openiam.webadmin.user;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.CancellableFormController;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.beans.propertyeditors.CustomDateEditor;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.base.AttributeOperationEnum;
import org.openiam.base.ws.ResponseStatus;
import org.openiam.idm.srvc.auth.dto.Login;
import org.openiam.idm.srvc.auth.ws.LoginDataWebService;
import org.openiam.idm.srvc.menu.dto.Menu;
import org.openiam.idm.srvc.menu.ws.NavigatorDataWebService;
import org.openiam.idm.srvc.meta.dto.MetadataElement;
import org.openiam.idm.srvc.meta.ws.MetadataElementArrayResponse;
import org.openiam.idm.srvc.meta.ws.MetadataWebService;
import org.openiam.idm.srvc.mngsys.dto.AttributeMap;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.dto.UserAttribute;
import org.openiam.idm.srvc.user.ws.UserDataWebService;
import org.openiam.idm.srvc.user.ws.UserResponse;
import org.openiam.provision.dto.ProvisionUser;
import org.openiam.provision.service.ProvisionService;
import org.openiam.webadmin.admin.AppConfiguration;


public class UserAttributeController extends CancellableFormController {


	protected UserDataWebService userMgr;
	protected LoginDataWebService loginManager;
	
	protected ProvisionService provRequestService; 
	protected NavigatorDataWebService navigationDataService;
	protected String redirectView;
	protected MetadataWebService metadataService;	

	protected AppConfiguration configuration;
	
	
	private static final Log log = LogFactory.getLog(UserAttributeController.class);

	
	public UserAttributeController() {
		super();
	}
	

	@Override
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("MM/dd/yyyy"),true) );
		
	}

            @Override
      protected ModelAndView onCancel(Object command) throws Exception {
          return new ModelAndView(new RedirectView(getCancelView(),true));
      }

	
	
	@Override
	protected Map referenceData(HttpServletRequest request) throws Exception {
		log.info("refernceData called.");
		Map model = new HashMap();
		
		String personId =  request.getParameter("personId");
		UserResponse resp = userMgr.getUserWithDependent(personId, false);
		if ( resp.getStatus() == ResponseStatus.SUCCESS) {
			User u = resp.getUser();
			if (u.getMetadataTypeId() == null || u.getMetadataTypeId().length() == 0) {
				return model;
			}
			MetadataElementArrayResponse mResp = metadataService.getMetadataElementByType(u.getMetadataTypeId());
			model.put("elementAry", mResp.getMetadataElementAry());
			
		}

			
		return model;

	}
	


	@Override
	protected Object formBackingObject(HttpServletRequest request)		throws Exception {
		
		UserAttributeCommand idCmd = new UserAttributeCommand();
		
		HttpSession session =  request.getSession();
		String userId = (String)session.getAttribute("userId");
		
		String menuGrp = request.getParameter("menugrp");
		String personId = request.getParameter("personId");
		
		idCmd.setPerId(personId);
		
		// get the level 3 menu
		List<Menu> level3MenuList =  navigationDataService.menuGroupByUser(menuGrp, userId, "en").getMenuList();
		request.setAttribute("menuL3", level3MenuList);	
		request.setAttribute("personId", personId);		
		
		User usr =  userMgr.getUserWithDependent(personId, true).getUser();
		if (usr  != null) {
			Map<String, UserAttribute> userAttrMap =  usr.getUserAttributes();
			if (userAttrMap != null && !userAttrMap.isEmpty()) {
				List<UserAttribute> attrList = toList(userAttrMap);
				// add a new row for the user enter values
				UserAttribute attr = new UserAttribute();
				attr.setName("<Enter name>");
				attr.setValue("<Enter value>");
				attrList.add(attr);				
				idCmd.setAttributeList(attrList);
				
			}else {
				// attribute map is null - get it from metadata
				if (usr.getMetadataTypeId() != null && usr.getMetadataTypeId().length() > 0) {
					MetadataElement[] elementAry = metadataService.getMetadataElementByType( usr.getMetadataTypeId()).getMetadataElementAry();
					List<UserAttribute> attrList = toAttributeList(elementAry);
                    if (attrList == null) {
                        attrList = new ArrayList<UserAttribute>();
                    }
					// add a new row for the user enter values
					UserAttribute attr = new UserAttribute();
					attr.setName("<Enter name>");
					attr.setValue("<Enter value>");
					attrList.add(attr);					
					idCmd.setAttributeList(attrList);
				}else {
					// create an empty list that the user can enter some data into
					List<UserAttribute> attrList = new ArrayList<UserAttribute>();
					// add a new row for the user enter values
					UserAttribute attr = new UserAttribute();
					attr.setName("<Enter name>");
					attr.setValue("<Enter value>");
					attrList.add(attr);
					idCmd.setAttributeList(attrList);					
				}
			}
		}
		
		return idCmd;
		
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
			attrList.add(attr);
		}
		return attrList;
		
	}
	private List<UserAttribute> toList(Map<String, UserAttribute> userAttrMap) {
		List<UserAttribute> attrList = new ArrayList<UserAttribute>();
		Collection<UserAttribute> col = userAttrMap.values();
		Iterator<UserAttribute> it = col.iterator();
		while (it.hasNext()) {
			attrList.add( it.next() );
		}
		if (attrList.isEmpty())
			return null;
		return attrList;
	}

		
	
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors) throws Exception {
	
		User usr = null;
		
		System.out.println("UserAttributeController: onSubmit");

		String btn = request.getParameter("saveBtn");
		if (btn.equals("Cancel")) {
			return new ModelAndView(configuration.getHomePage());
		}
		
		UserAttributeCommand cmd =(UserAttributeCommand)command;
		String personId = cmd.getPerId();
		if (personId == null || personId.length() == 0 ) {
			ModelAndView mv = new ModelAndView(configuration.getErrorUrl());
			mv.addObject("msg", "User id is null. ");
			return mv;

		}
		UserResponse resp = userMgr.getUserWithDependent(personId, true);
		if (resp.getStatus() == ResponseStatus.FAILURE) {
			ModelAndView mv = new ModelAndView(configuration.getErrorUrl());
			mv.addObject("msg", "Invalid user id. ");	
			return mv;
		}
		usr = resp.getUser();
		
		
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("userId");

		List<UserAttribute> attrList =  cmd.getAttributeList();
		

		if (btn.equalsIgnoreCase("Delete")) {

			if (attrList != null) {
				
				for ( UserAttribute ua : attrList) {
					if (ua.getSelected()) {
						if ( ua.getId() != null && ua.getId().length() > 0) {
							// set the delete flag
							updateUserAttr(usr, ua, 3, personId);
						}
					}
				}			
			}			
		}else {
			

				for ( UserAttribute ua : attrList) {
					if ( ua.getId() != null && ua.getId().length() > 0) {
						// UPDATE
						if (!ua.getName().equalsIgnoreCase("<Enter name>")) {
							updateUserAttr(usr, ua, 2, personId);
						}
					}else {
						// NEW
						if (!ua.getName().equalsIgnoreCase("<Enter name>")) {
							updateUserAttr(usr, ua, 1, personId);
						}
					}
				}			
		}
		ProvisionUser pUser = new ProvisionUser(usr);
		
		// check the user attributes
		
	 	Map<String, UserAttribute> attrMap = pUser.getUserAttributes();

        String login = (String)session.getAttribute("login");
        String domain = (String)session.getAttribute("domainId");
        pUser.setRequestClientIP(request.getRemoteHost());
        pUser.setRequestorLogin(login);
        pUser.setRequestorDomain(domain);
			 	
		this.provRequestService.modifyUser(pUser);
				
		return new ModelAndView(new RedirectView(redirectView+"&mode=1", true));

	}
	
	private void updateUserAttr(User user, UserAttribute ua, int operation, String personId) {
		if (ua.getMetadataElementId() != null && 
				ua.getMetadataElementId().length() < 1) {
				ua.setMetadataElementId( null);
			}
		
		if (operation == 1) {
			// new 
			ua.setOperation(AttributeOperationEnum.ADD);
			ua.setUserId(personId);
			ua.setId(null);

		}
		if (operation == 2) {
			// modify
			UserAttribute atr = user.getUserAttributes().get(ua.getName());
			if (atr.getValue() != null && atr.getValue().equals(ua.getValue())) {
				ua.setOperation(AttributeOperationEnum.NO_CHANGE);
			}else {
				System.out.println("updating attribute");
				atr.setValue( ua.getValue());
				atr.setOperation(AttributeOperationEnum.REPLACE);
				user.getUserAttributes().put(atr.getName(), atr);
				return;
			}
			ua.setUserId(personId);
		}else if (operation == 3) {
			// delete
			ua.setOperation(AttributeOperationEnum.DELETE);
			ua.setUserId(personId);
		}
		user.getUserAttributes().put(ua.getName(), ua);
		return;
	}


	public void setProvRequestService(ProvisionService provRequestService) {
		this.provRequestService = provRequestService;
	}


	public ProvisionService getProvRequestService() {
		return provRequestService;
	}


	public NavigatorDataWebService getNavigationDataService() {
		return navigationDataService;
	}


	public void setNavigationDataService(
			NavigatorDataWebService navigationDataService) {
		this.navigationDataService = navigationDataService;
	}


	public LoginDataWebService getLoginManager() {
		return loginManager;
	}


	public void setLoginManager(LoginDataWebService loginManager) {
		this.loginManager = loginManager;
	}


	public UserDataWebService getUserMgr() {
		return userMgr;
	}


	public void setUserMgr(UserDataWebService userMgr) {
		this.userMgr = userMgr;
	}


	public String getRedirectView() {
		return redirectView;
	}


	public void setRedirectView(String redirectView) {
		this.redirectView = redirectView;
	}


	public MetadataWebService getMetadataService() {
		return metadataService;
	}


	public void setMetadataService(MetadataWebService metadataService) {
		this.metadataService = metadataService;
	}


	public AppConfiguration getConfiguration() {
		return configuration;
	}


	public void setConfiguration(AppConfiguration configuration) {
		this.configuration = configuration;
	}




	
}
