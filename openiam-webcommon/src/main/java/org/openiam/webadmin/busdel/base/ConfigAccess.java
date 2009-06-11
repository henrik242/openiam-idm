package org.openiam.webadmin.busdel.base;

import javax.naming.*;

import diamelle.common.config.*;
import java.util.*;

import diamelle.base.prop.*;
/**
 * @author amit.nigam
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class ConfigAccess extends NavigationAccess{
	
	private Context ctx = null;
	private ConfigManagerHome mgrHome = null;
	private ConfigManager mgr = null;
	
			
	
	public ConfigAccess(){
		
	 try {
		  ctx = getInitialContext();
		  mgrHome = (ConfigManagerHome)this.getHome("diamelle.common.ConfigManager",ConfigManagerHome.class);
		
		  mgr = mgrHome.create();		 
		  } catch(Exception e) {		
		  e.printStackTrace();
		}
	  }

	public void addConfig (ConfigValue configVal)throws Exception {
	  if (configVal.getConfigId() == null )
	  configVal.setConfigId(this.getNextId("CONFIG_ID"));  
	  mgr.addConfiguration(configVal);	    
	 }
	
	public void addParam(String configId,Property p) throws Exception{				  
			if (p.getId() == null )
	        p.setId(this.getNextId("CONFIG_PARAM_ID")); 
		    mgr.addParam(configId,p);		 
		   }
   
	public ConfigValue getConfig(String configId) throws Exception{	
			ConfigValue configValue = mgr.getConfiguration(configId);			
			return  configValue; 
		    }
		
	public Property getParamById(String configId,String configParamId) throws Exception {		 
		 Property p = mgr.getParamById(configId,configParamId);	     
	     return p;	
	    }
	
	public Map getParamMap (String configId) throws Exception 
		{
		  return mgr.getParamMap(configId);	
	    }
	  
	public void saveConfiguration(ConfigValue configValue) throws Exception {		 
			 mgr.saveConfiguration(configValue);  			 	
			}	

		
}