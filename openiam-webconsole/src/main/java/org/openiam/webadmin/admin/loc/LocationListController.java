package org.openiam.webadmin.admin.loc;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.openiam.idm.srvc.loc.dto.Location;
import org.openiam.idm.srvc.loc.ws.LocationDataWebService;


/**
 * Displays a list of locations.
 * @author suneet
 *
 */
public class LocationListController extends AbstractController {


	protected static final Log log = LogFactory.getLog(LocationListController.class);
	protected String successView;
	protected LocationDataWebService locationService;
	
	


	
	
	public LocationListController() {
		super();
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		Location[] locationAry = locationService.allLocations().getLocationAry();

		ModelAndView mav = new ModelAndView(getSuccessView());
		mav.addObject("locationAry", locationAry);
		String mode = request.getParameter("mode");
		if (mode != null && mode.equalsIgnoreCase("1")) {
			mav.addObject("msg", "Your information has been sucessfully saved.");
		}
		
		
		return mav;
	}





	public String getSuccessView() {
		return successView;
	}


	public void setSuccessView(String successView) {
		this.successView = successView;
	}

	public LocationDataWebService getLocationService() {
		return locationService;
	}

	public void setLocationService(LocationDataWebService locationService) {
		this.locationService = locationService;
	}


	

}
