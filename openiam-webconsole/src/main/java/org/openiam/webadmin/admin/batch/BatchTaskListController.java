package org.openiam.webadmin.admin.batch;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.openiam.idm.srvc.batch.service.BatchDataService;
import org.openiam.idm.srvc.batch.dto.BatchTask;



/**
 * Displays a list of locations.
 * @author suneet
 *
 */
public class BatchTaskListController extends AbstractController {


	protected static final Log log = LogFactory.getLog(BatchTaskListController.class);
	protected String successView;
	protected BatchDataService batchDataService;
	
	

	
	public BatchTaskListController() {
		super();
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		List<BatchTask> batchTaskAry =  batchDataService.getAllTasks();

		ModelAndView mav = new ModelAndView(getSuccessView());
		mav.addObject("taskAry", batchTaskAry);
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

	public BatchDataService getBatchDataService() {
		return batchDataService;
	}

	public void setBatchDataService(BatchDataService batchDataService) {
		this.batchDataService = batchDataService;
	}


	

}
