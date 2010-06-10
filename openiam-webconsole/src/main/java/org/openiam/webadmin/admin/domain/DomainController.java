package org.openiam.webadmin.admin.domain;

//import net.sf.json.JSONObject;
//import net.sf.json.JSONSerializer;

import org.openiam.idm.srvc.secdomain.dto.SecurityDomain;
import org.openiam.idm.srvc.secdomain.service.SecurityDomainDataService;
import org.openiam.webadmin.util.*;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DomainController extends MultiActionController {

	//private DomainFormBean domainBean;
	private SecurityDomainDataService domainService;
	

	public ModelAndView domainList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	
		SecurityDomain[] domainAry = domainService.getAllSecurityDomains();
		
	//	DomainListFormBean listFormBean = new DomainListFormBean();
		//listFormBean.setRows(domainAry);
		
	/*	JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON( listFormBean ); 
		String json = jsonObject.toString();

		response.getOutputStream().write(json.getBytes());
*/	
		return null;
	}


	public ModelAndView saveDomain(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	
		
		String name = request.getParameter("name");
		String status = request.getParameter("status");
		String domainId = request.getParameter("domainId");
		String method = request.getParameter("method");
		
			SecurityDomain domain = domainService.getSecurityDomain(domainId);
			if (domain == null) {
				// new domain
				SecurityDomain newDomain = new SecurityDomain();
				newDomain.setDomainId(domainId);
				newDomain.setName(name);
				newDomain.setStatus(status);
				domainService.addSecurityDomain(newDomain);
			}else {
				// existing domain
				domain.setName(name);
				domain.setStatus(status);
				domainService.updateSecurityDomain(domain);
			}
	


		// send response to UI
	//	this.domainBean = new DomainFormBean();

		// return the status of this operation - True
/*		JSONResponse jsonResp = new JSONResponse();
		jsonResp.setData(domainBean);	
		JSONObject jsonObject = JSONObject.fromObject( jsonResp ); 
		String json =  jsonObject.toString();
		response.getOutputStream().write(json.getBytes());
*/		
		return null;
	}
	
	public ModelAndView deleteDomain(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	
		
		String name = request.getParameter("name");
		String status = request.getParameter("statusList");
		String domainId = request.getParameter("domainId");
		String method = request.getParameter("method");


		// delete the record
		domainService.removeSecurityDomainById(domainId);
		


		// send response to UI
		//this.domainBean = new DomainFormBean();

		// return the status of this operation - True
/*		JSONResponse jsonResp = new JSONResponse();
		jsonResp.setData(domainBean);	
		JSONObject jsonObject = JSONObject.fromObject( jsonResp ); 
		String json =  jsonObject.toString();
		response.getOutputStream().write(json.getBytes());
*/		
		return null;
	}

	
	


	public SecurityDomainDataService getDomainService() {
		return domainService;
	}


	public void setDomainService(SecurityDomainDataService domainService) {
		this.domainService = domainService;
	}

}
