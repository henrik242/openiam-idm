package org.openiam.webadmin.busdel.base;

import diamelle.base.prop.*;
import diamelle.common.cat.*;
import diamelle.common.meta.*;
import diamelle.ebc.navigator.*;
import java.util.*;
import javax.servlet.http.*;

/**
 * <p>Title: Diamelle Technologies Web Site</p>
 * <p>Description: Powered by Diamelle EJB Components</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Diamelle</p>
 * @author Arun
 * @version 1.0
 */

public class MetadataAccess extends NavigationAccess {

    MetadataManager meta = null;

    public MetadataAccess() {
      try{
        MetadataManagerHome metaHome = (MetadataManagerHome) this.getHome("MetadataManager");
        meta = metaHome.create();
   	  }
      catch(Exception e){
        e.printStackTrace();
      }
    }
		
    // getting all metadatatypes irrespective of a categoryId
    public Map getAllMetadataTypes() throws Exception {
       return meta.getMetadataTypes();
    }

     // get metadatatypes based on category id
    // returns map of metadata types
    public Map getMetadataTypes(String categoryId) throws java.rmi.RemoteException {
      return(meta.getMetadataTypes(categoryId));
    }

    // get metadata types
    // return map of meta data types
    public Map getUncategorisedMetadataTypes() throws java.rmi.RemoteException {
      return(meta.getUncategorisedMetdataTypes());
    }

    // get metadata type description
    // returns a string
    public String getMetadataTypeDescription(String typeId) throws java.rmi.RemoteException {
      Map metadataTypeMap = meta.getMetadataTypes();
      return((String)metadataTypeMap.get(typeId));
    }

	/**
		 * Gets the Metadata Element based on the Type Id.It also returns the MetadataOptions
		 * with the MetadataElements.
		 *
		 * @param typeId String.
		 * @return Map. It returns the Map which contains MetadataId as Key and
		 *  MetadataElementValue objects as Values.
		 */
    public Map getMetadataByType(String typeId) throws java.rmi.RemoteException {
       return meta.getMetadataByType(typeId);
    }

    /**
     * Useful in getting a list of properties for a given metadata type
     * Value is not included
     * This method is useful in creating a template to add properties
     *  
     * @param typeId
     * @param parentId
     * @param idName
     * @return List of Property objects
     * @throws java.rmi.RemoteException
     */
    public List getPropByType(String typeId, String parentId, String idName) throws java.rmi.RemoteException {
    	List propList = new ArrayList();
    	Map m = meta.getMetadataByType(typeId);
    	
        Collection c = m.values();
        Iterator i = c.iterator();
        while (i.hasNext()) {
          MetadataElementValue e = (MetadataElementValue)i.next();
          Property prop =  new PropertyImpl();  	
          prop.setParentKey(parentId);
      	  prop.setId( this.getNextId(idName) );
      	  prop.setMetaDataId(e.getMetadataId());
      	  prop.setName(e.getAttributeName());
      	  propList.add(prop);
        }  
        return propList;
     }

    public Map getTemplates(String typeId) throws java.rmi.RemoteException {
      return meta.getTemplates(typeId);
    }

    public void addMetadataOption(Property prop) throws java.rmi.RemoteException {
        if (prop.getId() == null || prop.getId().equals("")){
         String valueId = this.getNextId("METADATA_VALUE_ID");
         prop.setId(valueId);
        }
         meta.addMetadataOption(prop);
    }


    public void updateMetadataOption(Property prop) throws java.rmi.RemoteException {
      meta.updateMetadataOption(prop);
    }
    
	public void updateMetadataType(String id ,String desc) throws java.rmi.RemoteException {
	  meta.updateMetdataType(id,desc);
	}

    public Map getMetadataOptionMap(String metadataId) throws java.rmi.RemoteException {
      return meta.getMetadataOptionMap(metadataId);
    }

    public Map getMetadataOptions(String typeId) throws java.rmi.RemoteException {
      return meta.getMetadataOptions(typeId);
    }

    public void addCategoryType(String id, String catId) throws java.rmi.RemoteException {
      meta.addCategoryType(id,catId);
    }

    public String addMetadataType(String id ,String desc) throws java.rmi.RemoteException {
      if (id == null || id.equals(""))
       id = this.getNextId("TYPE_ID");
       meta.addMetadataType(id, desc);
      return id;
   }

      
    public void removeMetadataOption(String metaId,String id) throws java.rmi.RemoteException {
      meta.removeMetadataOption(metaId, id);
    }

    public void updateTemplate(String typeId, String name, String url) throws java.rmi.RemoteException {
      meta.updateTemplate(typeId, name, url);
    }

    public String getTemplate(String typeId, String name) throws java.rmi.RemoteException {
       return meta.getTemplate(typeId, name);
    }

    public void updateMetadataElement(MetadataElementValue mv) throws java.rmi.RemoteException {
       meta.updateMetadataElement(mv);
    }

    public void addMetadataElement(MetadataElementValue mv) throws java.rmi.RemoteException {
        if (mv.getMetadataId() == null || mv.getMetadataId().equals("")){
          String metadataId = this.getNextId("METADATA_ID");
          mv.setMetadataId(metadataId);
        }
        meta.addMetadataElement(mv);
     }
 
    public void addTemplate(String typeId, String name, String url) throws java.rmi.RemoteException {
       meta.addTemplate(typeId, name, url);
    }

    public void removeTemplate(String typeId, String name) throws java.rmi.RemoteException {
       meta.removeTemplate(typeId, name);
    }

    public void removeMetadataType(String typeId) throws java.rmi.RemoteException {
       meta.removeMetadataType(typeId);
    }
    
    public void removeCategoryType(String id, String catId) throws java.rmi.RemoteException {
       meta.removeCategoryType(id, catId);
    }
    
	public void removeCategoryTypes(String typeId) throws java.rmi.RemoteException {
	   meta.removeCategoryTypes(typeId);
	}

    public void  removeMetadataElement(String id) throws java.rmi.RemoteException {
       meta.removeMetadataElement(id);
    }

    public MetadataElementValue  getMetadataElementById(String metadataId) throws java.rmi.RemoteException {
       return meta.getMetadataElementById(metadataId) ;
    }

/**
 * @deprecated - use methods in NavigationAction
 */
    public void setMenuCategoryList(HttpServletRequest req, String catId, String path) throws Exception{
       HttpSession session = req.getSession(true);
       NavigatorBar navBar = new NavigatorBar();

      Collection categoryList = this.getCategoryList(catId);

      if ((categoryList != null) &&  (!categoryList.isEmpty()))	{
         session.setAttribute("categoryList", categoryList);
      } else {
         session.removeAttribute("categoryList");
      }
       this.setNavBar(req, catId, path);
       session.setAttribute("path", path);
    }

    /**
     * @deprecated - use methods in NavigationAction
     */
    public void setNavBar(HttpServletRequest req, String catId,String path ) throws Exception {
       HttpSession session = req.getSession(true);
       CategoryValue cv  = null;

       NavigatorBar navBar = (NavigatorBar)session.getAttribute("navbar");
       if (navBar == null)
         navBar = new NavigatorBar();

       if (catId != null)
         cv = getCategory(catId);

        if(cv != null)
           navBar.add(cv.getCategoryName(), (path + "?" + req.getQueryString()) );

       session.setAttribute("navbar", navBar);
    }


}
