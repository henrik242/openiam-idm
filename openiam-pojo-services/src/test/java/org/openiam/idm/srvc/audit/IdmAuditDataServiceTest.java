package org.openiam.idm.srvc.audit;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;
import org.openiam.idm.srvc.audit.dto.*;
import org.openiam.idm.srvc.audit.service.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.openiam.base.AbstractOpenIAMTestCase;

public class IdmAuditDataServiceTest extends  AbstractOpenIAMTestCase  {


	ApplicationContext ctx = null;

	IdmAuditLog log;
	IdmAuditLogDataService auditDataService;
	SearchAudit search;

	
	@Override

	protected void onSetUp() throws Exception {
		// TODO Auto-generated method stub
		super.onSetUp();
		ctx = new ClassPathXmlApplicationContext(
					new String[] {"/applicationContext.xml",
								  "/idmAuditLogTest-applicationContext.xml"} ) ;

		log = (IdmAuditLog)ctx.getBean("auditLogBean");
		auditDataService = (IdmAuditLogDataService)ctx.getBean("auditDataService");
	
		
	} 

	@Test
	public void testSearch() {
		
		DateFormat df = new SimpleDateFormat("dd/MM/yy");
		
		SearchAudit search = new SearchAudit();

		String startDt = "10/23/08";
		try {
		if (startDt != null && startDt.length() > 0) {
			search.setStartDate( new java.sql.Date(  df.parse(startDt).getTime() ) ); 
		}
		}catch(ParseException pe) {
			pe.printStackTrace();
		}
		java.util.Collection searchCol = auditDataService.search(search);
		
		this.assertEquals(1, searchCol.size());
	}
	
	/* Test direct address methods */
	
	@Test
	public void testAddLog() {
				
		auditDataService.addLog(log); 		
		
		
	}

}
