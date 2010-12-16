//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.8.2/xslt/JavaClass.xsl

package org.openiam.webadmin.rpt;

import javax.naming.*;
import javax.servlet.http.*;
import javax.sql.*;
import java.sql.*;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;

/** 
 * MyEclipse Struts
 * Creation date: 03-02-2005
 * 
 * XDoclet definition:
 * @struts:action
 */
public class ShowReportAction extends DispatchAction {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods

	/** 
	 * Method execute
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
		
		String name = request.getParameter("name");
		if (name.equalsIgnoreCase("UserAct")) {
			String sql =  userActivityRpt(request,(DynaActionForm)form, mapping);
			request.setAttribute("sql",sql);
			return mapping.findForward("userAct");
		}
		if (name.equalsIgnoreCase("UserProf")) {
			String sql =  userProfileRpt(request,(DynaActionForm)form, mapping);
			request.setAttribute("sql",sql);
			return mapping.findForward("userProf");
		}
		if (name.equalsIgnoreCase("srvcProf")) {
			String sql =  serviceProfileRpt(request,(DynaActionForm)form, mapping);
			request.setAttribute("sql",sql);
			return mapping.findForward("srvcProf");
		}
		if (name.equalsIgnoreCase("roleProf")) {
			String sql =  roleProfileRpt(request,(DynaActionForm)form, mapping);
	System.out.println(" sql = " + sql);
			request.setAttribute("sql",sql);
			return mapping.findForward("roleProf");
		}
		if (name.equalsIgnoreCase("utilize")) {
			String sql =  serviceUtilizationRpt(request,(DynaActionForm)form, mapping);
			request.setAttribute("sql",sql);
			return mapping.findForward("utilizeRpt");
		}
		if (name.equalsIgnoreCase("inactive")) {
			//String sql =  serviceProfileRpt(request,(DynaActionForm)form, mapping);
			//request.setAttribute("sql",sql);
			return mapping.findForward("inactiveRpt");
		}
		if (name.equalsIgnoreCase("except")) {
			//String sql =  exceptionRpt(request,(DynaActionForm)form, mapping);
			//request.setAttribute("sql",sql);
			return mapping.findForward("exceptionRpt");
		}
		return null;

	}
	
	public String userActivityRpt(HttpServletRequest request, DynaActionForm form, 
				ActionMapping mapping) {

		StringBuffer sqlBuffer = new StringBuffer("select distinct sl.log_time, sl.service_id, sl.login_id,u.first_name, " +
		" u.last_name,action, description, req_url,host_ip " + 
		" from security_access_log sl, login l, users u " + 
		" where sl.login_id = l.login and l.user_id = u.user_id " );
		String order = " order by sl.login_id, sl.log_time ";

		// get the parameters
		String login = (String)form.get("login");
		String startDate = (String)form.get("startDate");
		String endDate = (String)form.get("endDate");
		// build the sql
		if (login != null && login.length() > 0) {
			sqlBuffer.append(" and sl.login_id = '" + login + "' ");
		}
		if (startDate != null && startDate.length() > 0) {
			sqlBuffer.append(" and  sl.log_time >= '" + startDate + "' ");
		}	
		if (endDate != null && endDate.length() > 0) {
			sqlBuffer.append(" and  sl.log_time <= '" + endDate + "' ");
		}
		sqlBuffer.append(order);
		return sqlBuffer.toString();
	

	}

	public String serviceUtilizationRpt(HttpServletRequest request, DynaActionForm form, 
			ActionMapping mapping) {

	StringBuffer sqlBuffer = new StringBuffer("select distinct sl.log_time, sl.service_id, sl.login_id,u.first_name, " +
	" u.last_name,action, description, req_url,host_ip " + 
	" from security_access_log sl, login l, users u " + 
	" where sl.login_id = l.login and l.user_id = u.user_id " );
	String order = " order by sl.login_id, sl.log_time ";

	// get the parameters
	String login = (String)form.get("login");
	String startDate = (String)form.get("startDate");
	String endDate = (String)form.get("endDate");
	String service = (String)form.get("service");
	String filter = (String)request.getParameter("filter");
	
	// build the sql
	if (login != null && login.length() > 0) {
		sqlBuffer.append(" and sl.login_id = '" + login + "' ");
	}
	if (startDate != null && startDate.length() > 0) {
		sqlBuffer.append(" and  sl.log_time >= '" + startDate + "' ");
	}	
	if (endDate != null && endDate.length() > 0) {
		sqlBuffer.append(" and  sl.log_time <= '" + endDate + "' ");
	}
	if (service != null && service.length() > 0) {
		sqlBuffer.append(" and sl.service_id = '" + service + "' ");
	}
	if (filter != null && filter.length() > 0) {
		sqlBuffer.append(" and req_url like '" + filter + "%' ");
	}	
	sqlBuffer.append(order);
	return sqlBuffer.toString();


}
	
	
	public String userProfileRpt(HttpServletRequest request, DynaActionForm form, 
			ActionMapping mapping) {

		StringBuffer sqlBuffer = new StringBuffer(
				"select l.login,u.first_name, u.last_name, l.service_id, l.status, " + 
				" l.create_date, g.grp_name, tur.token_serial_nbr, l.current_login_host, " + 
				" au.address1, au.city, au.state, au.postal_cd " +
				" from LOGIN l INNER JOIN USERS u " + 
				" 	on l.user_id = u.user_id " + 
			    " INNER JOIN USER_GRP ug " + 
				" on u.user_id = ug.user_id " + 
			    "  INNER JOIN GRP g " + 
				" on ug.grp_id = g.grp_id " + 
			    " LEFT OUTER JOIN TOKEN_USER_REQ tur " + 
				" on u.user_id = tur.user_id " +  
			    "  LEFT OUTER JOIN ADDRESS_USER au " + 
				" on u.user_id = au.user_id and au.is_default=1 " );

		String order = " order by l.login ";

		
	// get the parameters
	String login = (String)form.get("login");
	String lastName = (String)form.get("lastName");
	String group = (String)form.get("group");
	StringBuffer where = new StringBuffer(" where ");
	// build the sql
	if (login != null && login.length() > 0) {
		if (where.length() > 8 ) {
			where.append(" and ");
		}
		where.append("  l.login = '" + login + "' ");
	}
	if (lastName != null && lastName.length() > 0) {
		if (where.length() > 8 ) {
			where.append(" and ");
		}
		where.append(" u.last_name = '" + lastName + "' ");
	}	
	if (group != null && group.length() > 0) {
		if (where.length() > 8 ) {
			where.append(" and ");
		}
		where.append(" g.grp_name = '" + group + "' ");
	}
	if (where.length() > 8) {
		sqlBuffer.append(where.toString());
	}
	sqlBuffer.append(order);
	return sqlBuffer.toString();


}

	public String serviceProfileRpt(HttpServletRequest request, DynaActionForm form, 
			ActionMapping mapping) {

    StringBuffer sqlBuffer = new StringBuffer(
    		"select s.SERVICE_ID, s.SERVICE_NAME, g.GRP_ID, g.GRP_NAME " +
			"		from service s INNER JOIN ROLE r " +
			" 			ON s.SERVICE_ID = r.SERVICE_ID " + 
			" 		INNER JOIN GRP_ROLE gr " + 
			"			on r.ROLE_ID = gr.ROLE_ID " + 
			"		INNER JOIN GRP g " +
			"			on gr.GRP_ID = g.GRP_ID ");
    String order = "ORDER BY s.SERVICE_ID, g.GRP_ID";
		

	// get the parameters
	String service = (String)form.get("service");

	// build the sql
	if (service != null && service.length() > 0) {
		sqlBuffer.append(" where s.SERVICE_ID = '" + service + "' ");
	}

	sqlBuffer.append(order);
	return sqlBuffer.toString();


}

	public String roleProfileRpt(HttpServletRequest request, DynaActionForm form, 
			ActionMapping mapping) {

	StringBuffer sqlBuffer = new StringBuffer(
			"select s.SERVICE_ID, s.SERVICE_NAME, r.ROLE_ID, " +
			" 	r.ROLE_NAME, res.RESOURCE_ID, res.RESOURCE_TYPE_ID, " +
			" 	res.DESCRIPTION, res.NODE_LEVEL, rr.PRIVILEGE_ID " + 
			" from service s INNER JOIN ROLE r " + 
			" 		ON s.SERVICE_ID = r.SERVICE_ID " + 
			" 	   INNER JOIN RESOURCE_ROLE rr " + 
			" 		ON r.ROLE_ID = rr.ROLE_ID " + 
			"      INNER JOIN RESOURCES res "  +
			" 		ON rr.RESOURCE_ID = res.RESOURCE_ID ");
	String order = " ORDER BY s.SERVICE_ID, rr.role_id, res.resource_id ";

	// get the parameters
	String service = (String)form.get("service");
	String role = (String)form.get("role");

	// build the sql
	StringBuffer where = new StringBuffer(" where ");
	// build the sql
	if (service != null && service.length() > 0) {
		if (where.length() > 8 ) {
			where.append(" and ");
		}
		where.append("  s.SERVICE_ID = '" + service + "' ");
	}
	if (role != null && role.length() > 0) {
		if (where.length() > 8 ) {
			where.append(" and ");
		}
		where.append("  rr.ROLE_ID = '" + role + "' ");
	}
	if (where.length() > 8 ) {
		sqlBuffer.append(where.toString());
	}
	sqlBuffer.append(order);
	return sqlBuffer.toString();


}

}