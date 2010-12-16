package org.openiam.base;

import org.openiam.base.id.SequenceGenDAO;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;


public class AbstractOpenIAMTestCase extends
		AbstractDependencyInjectionSpringContextTests {

	protected void onSetUp() throws Exception {
		// TODO Auto-generated method stub
		super.onSetUp();
		
		//DerbyUtil.buildOpeniamDB();
	}
	
	protected void onTearDown()  throws Exception
    {
		//DerbyUtil.stopDatabase();
		//DerbyUtil.deleteOpeniamDB();
    }
	
	
}
