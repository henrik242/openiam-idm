package org.openiam.base.id;

import org.junit.Test;
import org.openiam.idm.srvc.audit.dto.*;
import org.openiam.idm.srvc.audit.service.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class SequenceGenTest extends AbstractDependencyInjectionSpringContextTests  {


	ApplicationContext ctx = null;
	SequenceGenDAO seqGenDao;
	

	
	@Override

	protected void onSetUp() throws Exception {
		// TODO Auto-generated method stub
		super.onSetUp();
		ctx = new ClassPathXmlApplicationContext(
					new String[] {"/applicationContext.xml"} ) ;

		seqGenDao = (SequenceGenDAO)ctx.getBean("seqGenDAO");
		
		
	} 

	
	/* Test direct address methods */
	
	@Test
	public void testGenerateID() {
				
		String id = seqGenDao.getNextId("USER_ID");
		this.assertNotNull(id); 		
		
	}

}
