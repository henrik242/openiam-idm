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
/*
 * Created on Apr 11, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.openiam.webadmin.maint;

import java.rmi.RemoteException;
import java.util.List;

import javax.servlet.http.*;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.openiam.webadmin.busdel.base.NavigationAction;

/**
 * @author suneet
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MaintIndexAction extends NavigationAction {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods

	/** 
	 * Shows the maintenance menu.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {

		HttpSession session = request.getSession();
		try {
			List maintMenu = navAccess.getMenuList("MAINT");
			session.setAttribute("topLevelMenus", maintMenu);
			session.setAttribute("MENU_MODE","HELP_DESK");
		}catch(RemoteException re) {
			re.printStackTrace();
		}		
		return mapping.findForward("home");


	}

}
