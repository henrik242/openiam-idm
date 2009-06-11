package org.openiam.webadmin.busdel.base;

import java.util.*;

import diamelle.common.relation.*;
import java.rmi.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 *
 */

public class RelationAccess extends NavigationAccess{
   RelationManager relation  = null;

   public RelationAccess(){
    try{
      RelationManagerHome home = (RelationManagerHome) getHome("RelationManager");
      relation = home.create();
    }
    catch(Exception e) {
     e.printStackTrace();
    }
   }

   public Map getRelationSources(String sourceId) throws java.rmi.RemoteException {
    //return  relation.getAllRelationSetId();
    return  relation.getRelationSources(sourceId);
   }

   public  List getRelationSets() throws java.rmi.RemoteException {
    //return  relation.getAllRelationSetId();
    return  relation.getRelationSets();
   }

   public List getRelationSetsByCategory(String categoryId) throws java.rmi.RemoteException {
//    return relation.getRelationSets(categoryId);
      return relation.getRelationSetsByCategory(categoryId);
   }

   public String addRelationSet(String relationsetId, String desc) throws java.rmi.RemoteException {
     if (relationsetId == null || relationsetId.length() <1)
                  relationsetId = getNextId("RELATION_SET_ID");
     relation.addRelationSet(relationsetId,desc);

     return relationsetId;
  }

   public void addRelationCategory(String idList,String categoryId) throws java.rmi.RemoteException {
    //relation.addRelationSetToCategory(idList,categoryId);
     relation.addRelationCategory(idList,categoryId);
   }

   public void removeRelationSet(String setList) throws java.rmi.RemoteException {
    //relation.removeRelationSetId(setList);
     relation.removeRelationSet(setList);
   }

   public RelationshipValue  getRelationshipById(String id) throws java.rmi.RemoteException {
    return relation.getRelationshipById(new  RelationshipKey(id));
   }

   public void  removeRelationshipById(String relationshipId)  throws java.rmi.RemoteException {
    relation.removeRelationshipById(new  RelationshipKey(relationshipId));
   }

   public void addRelation(String sourceObj, String sourceId,String relationTypeId,String setId) throws java.rmi.RemoteException {
    //relation.addRelationSetToSource(rv,relationTypeId,setId);
    relation.addRelation(sourceObj ,sourceId ,relationTypeId,setId);
   }

   public List getRelationTypes() throws java.rmi.RemoteException {
    //return relation.getAllRelationTypes();
    return relation.getRelationTypes();
   }

   public void removeRelationType(String  typeList) throws java.rmi.RemoteException {
    relation.removeRelationType(typeList);
   }

    //adding a relationship
    public void addRelationship(RelationshipValue relValue) throws java.rmi.RemoteException {
          if (relValue.getRelationshipId() == null || relValue.getRelationshipId().length() < 1) {
                    relValue.setRelationshipId(getNextId("RELATIONSHIP_ID"));

          }
          relation.addRelationship(relValue);

    }

    //getting relationships
    public List getRelationships(String setId) throws java.rmi.RemoteException {
         return relation.getRelationships(setId);
    }

    //getting relationships
    public List getRelationships(String sourceObj, String sourceId, String reltypeId, boolean active) throws java.rmi.RemoteException {
         return relation.getRelationships(sourceObj, sourceId, reltypeId, active);
    }


   public void  updateRelationship( RelationshipValue rv) throws java.rmi.RemoteException {
    relation.updateRelationship(rv);
   }

   public void  removeRelationCategory(String categoryId,String sId) throws java.rmi.RemoteException{
    //relation.removeRelationSetFromCategory(categoryId,sId);
     relation.removeRelationCategory(categoryId,sId);
   }

   public void removeRelation(String sourceObj, String sourceId,String typeId) throws java.rmi.RemoteException {
     //relation.removeRelationSource(rs,typeId);
     relation.removeRelation(sourceObj,sourceId,typeId);
   }

   public void  addRelationType(String id,String desc) throws java.rmi.RemoteException {
    relation.addRelationType(id,desc);
   }

   public List getRelationSetsByItem(String itemObj,String itemId) throws java.rmi.RemoteException {
     return relation.getRelationSetsByItem(itemObj,itemId);
   }

   public RelationSetValue getRelationSetValue(String relationSetId) throws RemoteException {
     return relation.getRelationSet(relationSetId);
   }
}