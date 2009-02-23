package org.openiam.webadmin.poldom;


import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.util.LabelValueBean;

import org.openiam.webadmin.busdel.base.*;
import org.openiam.webadmin.busdel.security.ACLAccess;
import diamelle.security.resource.*;

import com.jenkov.prizetags.tree.impl.*;
import com.jenkov.prizetags.tree.itf.*;


/**
 * Renders a Resource Tree for a policy domain.
 * @author Suneet Shah
 *
 */
public class ResourceTreeViewAction extends NavigationDispatchAction {
	private ACLAccess acl = new ACLAccess();
	
	public ActionForward view(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {

			HttpSession session = request.getSession();
			
			ITree tree = new Tree();
//			(node id , node name , node type)
			ITreeNode root = new TreeNode("rootId" , "Root" , "root");
			
			// get the resources
			try {
				List resourceList = acl.getResourcesByCategory("ACL");
				int size = resourceList.size();
				for (int i=0; i<size; i++) {
					ResourceValue resVal = (ResourceValue)resourceList.get(i);
					ITreeNode treeNode = new TreeNode(resVal.getResourceId(), resVal.getDescription(), "Proxy");
					root.addChild(treeNode);
				}
			}catch(Exception e)  {
				e.printStackTrace();
			}
			
			tree.setRoot(root);
			session.setAttribute("tree.model", tree);
			
		
			return mapping.findForward("success");


	}
	
	public ActionForward detail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		System.out.println("ResourceAction - newResource...");
		HttpSession session = request.getSession();

		DynaActionForm resourceForm = (DynaActionForm) form;

		// NOTE!! these are PARENT RESOURCE values
		String resourceId = (String) resourceForm.get("resourceId");
		diamelle.security.resource.ResourceValue val = acl
				.getResource(resourceId);

		// set form values for display in jsp
		if (val != null) {
			resourceForm.set("mode", "addResource");
			resourceForm.set("resourceId", null);
			resourceForm.set("branchId", val.getBranchId());
			resourceForm.set("categoryId", val.getCategoryId());
			resourceForm.set("resourceParent", val.getResourceId());
			resourceForm.set("resourceTypeId", val.getResourceTypeId());
		} else {
			String categoryId = (String) session.getAttribute("categoryId");
			resourceForm.set("mode", "addRoot");
			resourceForm.set("categoryId", categoryId);
		}

		List resourceTypes = acl.getAllResourceTypes();
		List resourceTypeLabels = resourceTypesToLabels(resourceTypes);
		request.setAttribute("resourceTypes", resourceTypeLabels);
		request.setAttribute("detail", "new");

		return mapping.findForward("resourcetab");
	}
	
	private List resourceTypesToLabels(List resourceTypes) {
		List labels = new java.util.ArrayList();
		LabelValueBean emptybean = new LabelValueBean("<Select a value>", "-1");
		labels.add(emptybean);

		if (resourceTypes != null) {
			for (int i = 0; i < resourceTypes.size(); i++) {
				ResourceTypeValue val = (ResourceTypeValue) resourceTypes
						.get(i);
				LabelValueBean bean = new LabelValueBean(val.getDescription(),
						val.getResourceTypeId());
				labels.add(bean);
			}
		}
		return labels;
	}

	
}
