/* Copyright (c) 2005-2007, Diamelle Technologies and the OpenIAM team
* All rights reserved.
* 
* Licensed under the GNU Lesser General Public License;
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
* 
*      http://www.gnu.org/licenses/lgpl.html
* 
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package org.openiam.webadmin.poldom;

import javax.servlet.http.*;
import java.util.*;

import org.apache.log4j.Logger;
import org.apache.struts.action.*;


import org.openiam.webadmin.busdel.base.*;
import diamelle.security.policy.PolicyManagerBean;
import diamelle.util.LoggerUtil;
import diamelle.common.cat.*;

public class PolicyDomainAction extends NavigationDispatchAction  {

	CategoryAccess catAccess = new CategoryAccess();
	public static String POLICY_DOMAIN = "POLICYDOM";
	
   final static String className = PolicyDomainAction.class.getName();
  // final static Logger logger = Logger.getLogger(className);
	   
	public ActionForward viewDomains(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
		
	//	LoggerUtil.info(logger, "in ViewDomains");
		
		try {
			List domainList = catAccess.getAllCategories(POLICY_DOMAIN);
			request.setAttribute("domainList", domainList);
		}catch(Exception e) {
		//	LoggerUtil.error(logger,e);
			e.printStackTrace();
		}
		
		return mapping.findForward("policyDomainList");
	}

	public ActionForward newDomain(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
		
		//LoggerUtil.info(logger, "in New Domain");
		
		System.out.println("in new domains");
		request.setAttribute("mode","new");
		
		return mapping.findForward("newPolicyDomain");
	}
	
	public ActionForward save(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
		
	//	LoggerUtil.info(logger, "in saveDomain");
		
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("userId");
	
		
 		
        CategoryValue catVal = new CategoryValue();
		DynaActionForm dynForm = (DynaActionForm)form;
		catVal.setCategoryName( (String)dynForm.get("name") );
		catVal.setDescription( (String)dynForm.get("description") );
		catVal.setCategoryId((String)dynForm.get("id") );
		catVal.setCreatedBy(userId);
		catVal.setParentId(POLICY_DOMAIN);

	//	LoggerUtil.info(logger, "save domain information");
		
		try {
			catAccess.addCategory(catVal);
		}catch(Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("mode","new");
		
		return mapping.findForward("newPolicyDomain");
	}
	
	
	public ActionForward viewDomainDetail(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
		
		System.out.println("in edit domains");
		request.setAttribute("mode","edit");
		
		return mapping.findForward("newPolicyDomain");
	}
}
