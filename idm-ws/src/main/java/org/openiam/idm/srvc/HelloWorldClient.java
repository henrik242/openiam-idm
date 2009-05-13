package org.openiam.idm.srvc;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloWorldClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("In helloworldclient...4");
    	ClassPathXmlApplicationContext context 
            = new ClassPathXmlApplicationContext(new String[] {"client-beans.xml"});

        HelloWorld client = (HelloWorld)context.getBean("client");
        System.out.println(" WS call result=" +  client.test() );
	}

}
