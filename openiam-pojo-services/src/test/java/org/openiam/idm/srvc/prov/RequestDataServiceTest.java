package org.openiam.idm.srvc.prov;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.*;

import org.openiam.idm.srvc.prov.request.dto.ProvisionRequest;
import org.openiam.idm.srvc.prov.request.dto.RequestUser;

import org.openiam.idm.srvc.prov.request.service.RequestDataService;



import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.openiam.base.AbstractOpenIAMTestCase;

public class RequestDataServiceTest extends AbstractOpenIAMTestCase {

	ApplicationContext ctx = null;

	RequestDataService requestService;


	static String requestId = null;


	@Override
	protected void onSetUp() throws Exception {
		// TODO Auto-generated method stub
		super.onSetUp();
		ctx = new ClassPathXmlApplicationContext(new String[] {
				"/applicationContext.xml" });
		requestService = (RequestDataService) ctx.getBean("provRequestService");
	}

	@Test
/*	public void testAdd() {
		ProvisionRequest req = new ProvisionRequest();
		req.setRequestorId("3006");
		req.setStatus("PENDING");
		req.setStatusDate(new Date(System.currentTimeMillis()));
		req.setRequestReason("Test");
		req.setRequestDate(new Date(System.currentTimeMillis()));

		requestService.addRequest(req);
		
		requestId = req.getRequestId();
		
		System.out.println("Request Id =" + requestId);

	
	}
*/
	public void testAddWithUser() {
		ProvisionRequest req = new ProvisionRequest();
		req.setRequestorId("3006");
		req.setStatus("PENDING");
		req.setStatusDate(new Date(System.currentTimeMillis()));
		req.setRequestReason("Test");
		req.setRequestDate(new Date(System.currentTimeMillis()));

		Set<RequestUser> reqUserSet =  req.getRequestUsers();
		
		RequestUser user = new RequestUser();
		user.setUserId("100000");
		user.setFirstName("J");
		user.setLastName("Openheimer");
		reqUserSet.add(user);
		
		requestService.addRequest(req);
		
		requestId = req.getRequestId();
		
		System.out.println("Request Id =" + requestId);

	
	}
	
	public void testGet() {
		ProvisionRequest req =  requestService.getRequest(requestId);
		
		assertNotNull(req);
		this.assertEquals(requestId, req.getRequestId());
	}
	


	public void testUpdate() {
		ProvisionRequest req =  requestService.getRequest(requestId);
		req.setStatus("APPROVED");
		req.setRequestReason("Reason updated..");
		
		requestService.updateRequest(req);
	
	}



	
	

}
