package org.openiam.selfsrvc.prov;



import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.beans.propertyeditors.CustomDateEditor;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class NewRequestController extends SimpleFormController {


	private static final Log log = LogFactory.getLog(NewRequestController.class);

	
	public NewRequestController() {
		super();
	}
	

	@Override
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("MM/dd/yyyy"),true) );
	}

	
	
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors) throws Exception {
		// TODO Auto-generated method stub
		
		
		NewRequestCommand newHireCmd =(NewRequestCommand)command;
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("userId");
		

	
		
		ModelAndView mav = new ModelAndView(getSuccessView());
		mav.addObject("newRequestCmd",newHireCmd);
		
		
		return mav;
	}


}
