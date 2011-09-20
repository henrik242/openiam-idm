package org.openiam.webadmin.admin.batch;




import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import org.openiam.idm.srvc.batch.dto.BatchTask;
import org.openiam.idm.srvc.batch.service.BatchDataService;



public class BatchTaskDetailController extends SimpleFormController {


	private static final Log log = LogFactory.getLog(BatchTaskDetailController.class);
 	protected String redirectView;
	protected BatchDataService batchDataService;
	
	public BatchTaskDetailController() {
		super();
	}
	
	
	



	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		log.info("formBackObject called.");
		
		String taskId = request.getParameter("taskId");
		if (taskId == null) {
			return (new BatchTaskCommand());
		}
		BatchTask task= batchDataService.getBatchTask(taskId);
	
		BatchTaskCommand cmd = new BatchTaskCommand();
		cmd.setBatch(task);	

		return cmd;


}

	

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		log.info("BatchTaskDetailController: OnSubmit called.");
		
		BatchTaskCommand cmd = (BatchTaskCommand)command;
		BatchTask task  = cmd.getBatch();

		String btn = request.getParameter("btn");
		if (btn != null && btn.equalsIgnoreCase("Delete")) {
			batchDataService.removeBatchTask(task.getTaskId());
			ModelAndView mav = new ModelAndView("/deleteconfirm");
	        mav.addObject("msg", "Batch task has been successfully deleted.");
	        return mav;
		}else {	
			if (task.getTaskId() == null || task.getTaskId().length() == 0) {
				log.info("BatchTaskDetailController: OnSubmit add new task.");
				task.setTaskId(null); 
				batchDataService.addBatchTask(task);
			}else {
				log.info("BatchTaskDetailController: OnSubmit update existing task.");
				batchDataService.upateBatchTask(task);
			}
		}
		
		ModelAndView mav = new ModelAndView(new RedirectView(redirectView, true));
		mav.addObject("batchTaskCmd",cmd);
		
		return mav;
	}


	public String getRedirectView() {
		return redirectView;
	}

	public void setRedirectView(String redirectView) {
		this.redirectView = redirectView;
	}






	public BatchDataService getBatchDataService() {
		return batchDataService;
	}






	public void setBatchDataService(BatchDataService batchDataService) {
		this.batchDataService = batchDataService;
	}




	



	

}
