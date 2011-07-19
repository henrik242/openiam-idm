package org.openiam.selfsrvc.bpm;


import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.view.RedirectView;
import org.openiam.base.ws.ResponseStatus;
//import org.openiam.idm.srvc.bpm.service.BpmService;
import org.openiam.idm.srvc.menu.dto.Menu;
import org.openiam.idm.srvc.menu.ws.NavigatorDataWebService;
import org.openiam.idm.srvc.user.ws.UserDataWebService;
import org.openiam.idm.srvc.user.ws.UserListResponse;
import org.openiam.idm.srvc.user.dto.UserSearch;
import org.openiam.idm.srvc.user.dto.User;



public class LaunchProcessController extends AbstractController {


	private static final Log log = LogFactory.getLog(LaunchProcessController.class);
	//protected BpmService bpmService;
	protected NavigatorDataWebService navigationDataService;
	
	
	public LaunchProcessController() {
		super();
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	
		System.out.println("Launch process called");


		//String menuId =  request.getParameter("menuId");
		//Menu menu = navigationDataService.getMenu(menuId, "en").getMenu();
		
		//String identityKey = (String)bpmService.getBpmSecurityToken("admin","bpm").getResponseValue();
		
		//String linkUrl = menu.getUrl();
     try {

        String linkUrl = request.getParameter("url");
        if (linkUrl == null || linkUrl.length() ==0) {
            linkUrl = "http://localhost:8080/MyProcess2--1.0/application/BonitaApplication.html?locale=en#process=MyProcess2--1.0";
        }

		if (linkUrl.contains("?")) {
			//linkUrl = linkUrl + "&identityKey=" + identityKey;
		}else {
			//linkUrl = linkUrl + "?identityKey=" + identityKey;
		}
		
		System.out.println("LinkUrl=" + linkUrl);
		
		ModelAndView mav = new ModelAndView(new RedirectView(linkUrl));
		return mav;

     }catch(Exception e) {
        e.printStackTrace();
         return null;
     }
	}




    public NavigatorDataWebService getNavigationDataService() {
		return navigationDataService;
	}

	public void setNavigationDataService(
			NavigatorDataWebService navigationDataService) {
		this.navigationDataService = navigationDataService;
	}



	

}
