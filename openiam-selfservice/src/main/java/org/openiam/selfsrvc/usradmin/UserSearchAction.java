

package org.openiam.selfsrvc.usradmin;


import org.openiam.idm.srvc.meta.ws.MetadataElementArrayResponse;
import org.openiam.idm.srvc.user.dto.UserAttribute;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.struts.DispatchActionSupport;


import org.openiam.idm.srvc.cd.dto.ReferenceData;
import org.openiam.idm.srvc.cd.service.ReferenceDataService;
import org.openiam.idm.srvc.grp.dto.Group;
import org.openiam.idm.srvc.grp.ws.GroupDataWebService;
import org.openiam.idm.srvc.meta.dto.MetadataElement;
import org.openiam.idm.srvc.meta.ws.MetadataWebService;
import org.openiam.idm.srvc.org.service.OrganizationDataService;
import org.openiam.idm.srvc.role.dto.Role;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.role.ws.RoleDataWebService;
import org.openiam.idm.srvc.user.ws.UserDataWebService;
import org.openiam.idm.srvc.user.dto.UserSearch;
import org.openiam.selfsrvc.AppConfiguration;


import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.struts.action.*;
import org.apache.struts.util.LabelValueBean;
import org.apache.struts.validator.DynaValidatorForm;

public class UserSearchAction extends DispatchActionSupport {

	private RoleDataWebService roleDataService;
	private GroupDataWebService groupManager;
	private OrganizationDataService orgManager;
	private String defaultSecurityDoamin;
	private ReferenceDataService refDataService;
	private int maxResultSize;
	private UserDataWebService userMgr;
	protected AppConfiguration configuration;
	protected MetadataWebService metadataService;
	

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse res)
			throws IOException, ServletException {

		// set the side menu bar
		String menuId = request.getParameter("menuid");
		request.setAttribute("menuGroup", menuId);
		String mode = request.getParameter("mode");
		
		
		request.setAttribute("groupList", allGroupListAsLabels());
		request.setAttribute("roleList", allRoleListAsLabels());
		if (mode != null && mode.length() > 0) {
			request.setAttribute("msg", "Information has been successfully updated. ");
		}
		HttpSession session = request.getSession();
		List statusList = (List) session.getAttribute("statusList");

		if (statusList == null) {
			statusList = getUserStatusList();
			List secondaryStatusList = getUserSecondaryStatusList();
			session.setAttribute("statusList", statusList);
			session.setAttribute("secondaryStatusList", secondaryStatusList);
		}
		session.setAttribute("elementList", getComleteMetadataElementList());
		return (mapping.findForward("view"));
	}

	private List getComleteMetadataElementList()  {
		log.info("getUserMetadataTypes called.");
		
		ArrayList<LabelValueBean> newCodeList = new ArrayList();
        if (metadataService == null) {
            return newCodeList;
        }

        MetadataElementArrayResponse aryResp = metadataService.getAllElementsForCategoryType("USER_TYPE");

        if (aryResp != null && aryResp.getMetadataElementAry() != null) {
            MetadataElement[] elementAry = metadataService.getAllElementsForCategoryType("USER_TYPE").getMetadataElementAry();
            if (elementAry != null && elementAry.length > 0) {
                newCodeList.add(new LabelValueBean("",""));
                for ( MetadataElement elm: elementAry) {
                    LabelValueBean label = new LabelValueBean(elm.getMetadataTypeId() + "->"
                            + elm.getAttributeName(), elm.getAttributeName());
                    newCodeList.add(label);
                }
             }
        }
        return newCodeList;
    }
	
	/**
	 * Retrieves a list of Users based on the search criteria
	 */
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse res)
			throws IOException, ServletException {
		try {
			List statusList = null;

			HttpSession session = request.getSession();
            User usr = (User)session.getAttribute("userObj");

            System.out.println("UserObj = " + usr);


			request.setAttribute("groupList", allGroupListAsLabels());
			request.setAttribute("roleList", allRoleListAsLabels());

			// Search search = new SearchImpl();
			UserSearch search = createSearch((DynaValidatorForm) form, usr);
			
    		if (search.isEmpty()) {
   		  	 	request.setAttribute("msg", "Please enter search criteria ");
   		  	 	return (mapping.findForward("success"));
    		}
    		search.setMaxResultSize(new Integer(this.maxResultSize));
			
			List userList = userMgr.search(search).getUserList();

			if (userList != null) {
				request.setAttribute("userList", userList);
				request.setAttribute("resultSize", new Integer(userList.size()));
			}

			// request.setAttribute("groupList", groupList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (mapping.findForward("success"));
	}

	private List getUserStatusList(){
		ArrayList<LabelValueBean> newCodeList = new ArrayList();
    	List<ReferenceData> codeList = refDataService.getRefByGroup("USER","en");

		if (codeList != null && codeList.size() > 0) {
			newCodeList.add(new LabelValueBean("", ""));
			for (int i = 0; i < codeList.size(); i++) {
				ReferenceData val = codeList.get(i);
				LabelValueBean label = new LabelValueBean(val.getDescription(),
						val.getId().getStatusCd());
				newCodeList.add(label);
			}
		}
		return newCodeList;

	}

	private List getUserSecondaryStatusList(){
		ArrayList<LabelValueBean> newCodeList = new ArrayList();
    	List<ReferenceData> codeList = refDataService.getRefByGroup("USER_2ND_STATUS","en");

		if (codeList != null && codeList.size() > 0) {
			newCodeList.add(new LabelValueBean("", ""));
			for (int i = 0; i < codeList.size(); i++) {
				ReferenceData val = codeList.get(i);
				LabelValueBean label = new LabelValueBean(val.getDescription(),
						val.getId().getStatusCd());
				newCodeList.add(label);
			}
		}
		return newCodeList;

	}
	
	
	private UserSearch createSearch(DynaValidatorForm form, User usr) {
		UserSearch search = new UserSearch();

		// lastname
		if (form.get("lastName") != null
				&& ((String) form.get("lastName")).length() > 0) {
			search.setLastName(form.get("lastName") + "%");
		}

		if (form.get("firstName") != null
				&& ((String) form.get("firstName")).length() > 0) {
			search.setFirstName(form.get("firstName") + "%");
		}
		if (form.get("companyName") != null
				&& ((String) form.get("companyName")).length() > 0) {
			search.setOrgId((String) form.get("companyName"));
		}

		if (form.get("dept") != null
				&& ((String) form.get("dept")).length() > 0) {
			search.setDeptCd((String) form.get("dept"));
		}
		if (form.get("areaCode") != null
				&& ((String) form.get("areaCode")).length() > 0) {
			search.setPhoneAreaCd((String) form.get("areaCode"));
		}
		if (form.get("phoneNumber") != null
				&& ((String) form.get("phoneNumber")).length() > 0) {
			search.setPhoneNbr((String) form.get("phoneNumber"));
		}
		if (form.get("role") != null
				&& ((String) form.get("role")).length() > 0) {
			
			String r = (String) form.get("role");
			int indx = r.indexOf("*");
			String roleId = r.substring(indx+1, r.length()) ;
			String domainId = r.substring(0, indx);
			List<String> roleList = new ArrayList<String>();
			roleList.add(roleId );	
			search.setRoleIdList(roleList);
			search.setDomainId(domainId);
		}
		if (form.get("group") != null
				&& ((String) form.get("group")).length() > 0) {
			List<String> groupList = new ArrayList<String>();
			groupList.add((String) form.get("group"));
			search.setGroupIdList(groupList);
		}
		if (form.get("email") != null
				&& ((String) form.get("email")).length() > 0) {
			search.setEmailAddress((String) form.get("email"));
		}
		if (form.get("status") != null
				&& ((String) form.get("status")).length() > 0) {
			search.setStatus((String) form.get("status"));
		}

		if (form.get("secondaryStatus") != null
				&& ((String) form.get("secondaryStatus")).length() > 0) {
			search.setSecondaryStatus((String) form.get("secondaryStatus"));
		}

		if (form.get("attributeName") != null
				&& ((String) form.get("attributeName")).length() > 0) {
			String attrName = (String) form.get("attributeName");
		
			if (form.get("attributeValue") != null
					&& ((String) form.get("attributeValue")).length() > 0) {
				
				search.setAttributeName(attrName);
				search.setAttributeValue((String) form.get("attributeValue"));
				
			}
			
		}

        if (usr.getDelAdmin() != null &&  usr.getDelAdmin().intValue() == 0) {
            Map<String, UserAttribute> attrMap = usr.getUserAttributes();
            List<String> deptFilterList = null;
            List<String> orgFilterList = null;
            List<String> divFilterList = null;

            deptFilterList =  DelegationFilterHelper.getDeptFilterFromString(attrMap);
            if (deptFilterList != null && deptFilterList.size() > 0) {
                search.setDeptIdList(deptFilterList);

            }


            orgFilterList = DelegationFilterHelper.getOrgIdFilterFromString (attrMap);
            if (orgFilterList != null && orgFilterList.size() > 0) {
                search.setOrgIdList(orgFilterList);
            }


            divFilterList = DelegationFilterHelper.getDivisionFilterFromString(attrMap);
            if (divFilterList != null && divFilterList.size() > 0) {
                search.setDivisionIdList(divFilterList);

            }

         }

		return search;
	}




	public List allGroupListAsLabels() {
		List<LabelValueBean> newCodeList = new LinkedList();
		try {
			List<Group> grpList = groupManager.getAllGroups().getGroupList();
			if (grpList != null && grpList.size() > 0) {
				newCodeList.add(new LabelValueBean("", ""));
				for (int i = 0; i < grpList.size(); i++) {
					Group val = grpList.get(i);
					LabelValueBean label = new LabelValueBean(val.getGrpName(), val
							.getGrpId());
					newCodeList.add(label);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return newCodeList;
	}

	public List allRoleListAsLabels() {
		List<LabelValueBean> newCodeList = new LinkedList();
		try {
			List<Role> roleList = roleDataService.getAllRoles().getRoleList();
			if (roleList != null && roleList.size() > 0) {
				newCodeList.add(new LabelValueBean("", ""));
				for (int i = 0; i < roleList.size(); i++) {
					Role val = roleList.get(i);
					LabelValueBean label = new LabelValueBean(val.getId().getServiceId() + "->"+ val.getRoleName(),
							val.getId().getServiceId() + "*"+  val.getId().getRoleId());
					newCodeList.add(label);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return newCodeList;
	}



	public OrganizationDataService getOrgManager() {
		return orgManager;
	}

	public void setOrgManager(OrganizationDataService orgManager) {
		this.orgManager = orgManager;
	}

	public String getDefaultSecurityDoamin() {
		return defaultSecurityDoamin;
	}

	public void setDefaultSecurityDoamin(String defaultSecurityDoamin) {
		this.defaultSecurityDoamin = defaultSecurityDoamin;
	}

	public ReferenceDataService getRefDataService() {
		return refDataService;
	}

	public void setRefDataService(ReferenceDataService refDataService) {
		this.refDataService = refDataService;
	}

	public int getMaxResultSize() {
		return maxResultSize;
	}

	public void setMaxResultSize(int maxResultSize) {
		this.maxResultSize = maxResultSize;
	}

	public RoleDataWebService getRoleDataService() {
		return roleDataService;
	}

	public void setRoleDataService(RoleDataWebService roleDataService) {
		this.roleDataService = roleDataService;
	}

	public GroupDataWebService getGroupManager() {
		return groupManager;
	}

	public void setGroupManager(GroupDataWebService groupManager) {
		this.groupManager = groupManager;
	}

	public UserDataWebService getUserMgr() {
		return userMgr;
	}

	public void setUserMgr(UserDataWebService userMgr) {
		this.userMgr = userMgr;
	}

	public AppConfiguration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(AppConfiguration configuration) {
		this.configuration = configuration;
	}

	public MetadataWebService getMetadataService() {
		return metadataService;
	}

	public void setMetadataService(MetadataWebService metadataService) {
		this.metadataService = metadataService;
	}

}
