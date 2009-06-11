package org.openiam.webadmin.busdel.base;

import diamelle.base.id.*;
import javax.naming.*;

/**
 * Provided commonly needed functionalty for all action classes.
 */

public class DiamelleBaseAccess {
  public Context jndiContext;

  public DiamelleBaseAccess() {
  }

 /**
  * Return the home interface of the bean class.
  * @param beanName - JNDI Name of the bean.
  * @param homeClassName - Class of the home class that is to be used.
  * @return the home object of the bean.
  */
  public Object getHome(String beanName, Class homeClass) {
    try {
      jndiContext = getInitialContext();
      //return javax.rmi.PortableRemoteObject.narrow(jndiContext.lookup(beanName),homeClass);
      return jndiContext.lookup(beanName);
      
    } catch(Exception e) {
      e.printStackTrace();
      return null;
    }
  }
  public Object getHome(String beanName) {
    try {
      jndiContext = getInitialContext();
      Object obj = jndiContext.lookup("java:comp/env/ejb/" + beanName);
      
      return obj;
    } catch(Exception e) {
       e.printStackTrace();
      return null;
    }
  }

//java:comp/env/ejb/Idgenerator

 /**
  * Dynamically generates the next id for the item passed.
  * @param idName the name of the item(id).
  * @return the next id of the item.
  * @throws ServletException
  */
  public String getNextId(String idName) {
    try {
      IdgeneratorHome idHome = (IdgeneratorHome)getHome("diamelle.util.Idgenerator", IdgeneratorHome.class);
      Idgenerator idGenerator = idHome.findByPrimaryKey(new IdgeneratorKey(idName));
      String id = idGenerator.generateNextIdAsString();
      return id;
    } catch (Exception e){
      e.printStackTrace();
      return null;
    }
  }


  /**
   * Returns a context using hte EJBClientFactory. This provides a level of abstraction to
   * handle difference among application servers.
   */
  public static Context getInitialContext() throws Exception {
  try {
    return new InitialContext();
    } catch(Exception e){
      diamelle.util.Log.error("getInitialContext", e);
    }
    return null;
  }


}

