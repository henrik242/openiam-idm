package org.openiam.webadmin.admin.loc;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import org.openiam.idm.srvc.loc.dto.Location;
import org.openiam.idm.srvc.loc.ws.LocationDataWebService;


public class LocationDetailController extends SimpleFormController {


	private static final Log log = LogFactory.getLog(LocationDetailController.class);
	protected LocationDataWebService locationService;
 	protected String redirectView;
	
	
	public LocationDetailController() {
		super();
	}
	
	protected Object formBackingObject(HttpServletRequest request)
	throws Exception {

		
		String locationId = request.getParameter("locationId");
		if (locationId == null) {
			return (new LocationCommand());
		}
		
		Location loc = locationService.getLocation(locationId).getLocation();
		
		LocationCommand locationCmd = new LocationCommand();
		locationCmd.setLocation(loc);
	

		return locationCmd;


}

	

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		LocationCommand locationCmd = (LocationCommand)command;
		Location location = locationCmd.getLocation();

		String btn = request.getParameter("btn");
		if (btn != null && btn.equalsIgnoreCase("Delete")) {
			locationService.removeLocation(locationCmd.getLocation().getLocationId());
			ModelAndView mav = new ModelAndView("/deleteconfirm");
	        mav.addObject("msg", "Location has been successfully deleted.");
	        return mav;
		}else {	
			if (location.getLocationId() == null || location.getLocationId().length() == 0) {
				location.setLocationId(null);
				locationService.addLocation(location);
			}else {
				locationService.updateLocation(location);
			}
		}
		
		ModelAndView mav = new ModelAndView(new RedirectView(redirectView, true));
		mav.addObject("locationCmd", locationCmd);
		
		return mav;
	}


	public String getRedirectView() {
		return redirectView;
	}

	public void setRedirectView(String redirectView) {
		this.redirectView = redirectView;
	}

	public LocationDataWebService getLocationService() {
		return locationService;
	}

	public void setLocationService(LocationDataWebService locationService) {
		this.locationService = locationService;
	}
	


	



	

}
