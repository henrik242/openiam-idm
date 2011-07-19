package org.openiam.webadmin.user;

import java.io.Serializable;
import java.util.List;
import java.util.StringTokenizer;
import java.util.ArrayList;

import org.openiam.idm.srvc.auth.dto.Login;


/**
 * Command object for the EditUserController
 * @author suneet
 *
 */
public class DelegationFilterCommand implements Serializable {

	 
	protected List<Login> principalList;
	protected String perId; // personId

    protected List<String> orgFilter;
    protected List<String> deptFilter;
    protected List<String> divisionFilter;
    protected List<String> groupList;
    protected List<String>  roleList;
    protected List<String> appList;


	public String getPerId() {
		return perId;
	}


	public void setPerId(String perId) {
		this.perId = perId;
	}


	public List<Login> getPrincipalList() {
		return principalList;
	}


	public void setPrincipalList(List<Login> principalList) {
		this.principalList = principalList;
	}


    public List<String> getOrgFilter() {
        return orgFilter;
    }

    public void setOrgFilter(List<String> orgFilter) {
        this.orgFilter = orgFilter;
    }

    public List<String> getDeptFilter() {
        return deptFilter;
    }

    public void setDeptFilter(List<String> deptFilter) {
        this.deptFilter = deptFilter;
    }

    public List<String> getDivisionFilter() {
        return divisionFilter;
    }

    public void setDivisionFilter(List<String> divisionFilter) {
        this.divisionFilter = divisionFilter;
    }

    public List<String> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<String> groupList) {
        this.groupList = groupList;
    }

    public List<String> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<String> roleList) {
        this.roleList = roleList;
    }

    public List<String> getAppList() {
        return appList;
    }

    public void setAppList(List<String> appList) {
        this.appList = appList;
    }

    public void setOrgFilterFromString(String filter) {
        if (filter == null || filter.length() == 0) {
            return;
        }

        StringTokenizer tokenizer = new StringTokenizer(filter, ",");
        orgFilter = new ArrayList<String>();
        while ( tokenizer.hasMoreTokens()) {
            orgFilter.add(tokenizer.nextToken());
        }

    }
    public void setDeptFilterFromString(String filter) {

        if (filter == null || filter.length() == 0) {
            return;
        }

        StringTokenizer tokenizer = new StringTokenizer(filter, ",");
        deptFilter = new ArrayList<String>();
        while ( tokenizer.hasMoreTokens()) {
            deptFilter.add(tokenizer.nextToken());
        }

    }

    public void setDivisionFilterFromString(String filter) {
        if (filter == null || filter.length() == 0) {
            return;
        }


        StringTokenizer tokenizer = new StringTokenizer(filter, ",");
        this.divisionFilter = new ArrayList<String>();
        while ( tokenizer.hasMoreTokens()) {
            divisionFilter.add(tokenizer.nextToken());
        }

    }

    public void setAppFilterFromString(String filter) {
        if (filter == null || filter.length() == 0) {
            return;
        }


        StringTokenizer tokenizer = new StringTokenizer(filter, ",");
        this.appList = new ArrayList<String>();
        while ( tokenizer.hasMoreTokens()) {
            appList.add(tokenizer.nextToken());
        }

    }

    public void setGroupFilterFromString(String filter) {
        if (filter == null || filter.length() == 0) {
            return;
        }

        StringTokenizer tokenizer = new StringTokenizer(filter, ",");
        this.groupList = new ArrayList<String>();
        while ( tokenizer.hasMoreTokens()) {
            groupList.add(tokenizer.nextToken());
        }

    }


    public void setRoleFilterFromString(String filter) {
        if (filter == null || filter.length() == 0) {
            return;
        }

        StringTokenizer tokenizer = new StringTokenizer(filter, ",");
        this.roleList = new ArrayList<String>();
        while ( tokenizer.hasMoreTokens()) {
            roleList.add(tokenizer.nextToken());
        }

    }



    public String getOrgFilterAsString() {

        if (orgFilter == null) {
            return null;
        }
        StringBuffer buf = new StringBuffer();
        int ctr = 0;
        for (String s : orgFilter) {
            if (ctr == 0) {
                buf.append(s);
            }else {
                buf.append("," + s);
            }
            ctr++;

        }
        return buf.toString();

    }


     public String getDivFilterAsString() {

        if (divisionFilter == null) {
            return null;
        }
        StringBuffer buf = new StringBuffer();
        int ctr = 0;
        for (String s : divisionFilter) {
            if (ctr == 0) {
                buf.append(s);
            }else {
                buf.append("," + s);
            }
            ctr++;

        }
        return buf.toString();

    }

    public String getDeptFilterAsString() {

        if (deptFilter == null) {
            return null;
        }
        StringBuffer buf = new StringBuffer();
        int ctr = 0;
        for (String s : deptFilter) {
            if (ctr == 0) {
                buf.append(s);
            }else {
                buf.append("," + s);
            }
            ctr++;

        }
        return buf.toString();

    }



     public String getgroupFilterAsString() {

        if (groupList == null) {
            return null;
        }
        StringBuffer buf = new StringBuffer();
        int ctr = 0;
        for (String s : groupList) {
            if (ctr == 0) {
                buf.append(s);
            }else {
                buf.append("," + s);
            }
            ctr++;

        }
        return buf.toString();

    }


     public String getRoleFilterAsString() {

        if (roleList == null) {
            return null;
        }
        StringBuffer buf = new StringBuffer();
        int ctr = 0;
        for (String s : roleList) {
            if (ctr == 0) {
                buf.append(s);
            }else {
                buf.append("," + s);
            }
            ctr++;

        }
        return buf.toString();

    }


     public String getAppFilterAsString() {

        if (this.appList == null) {
            return null;
        }
        StringBuffer buf = new StringBuffer();
        int ctr = 0;
        for (String s : appList) {
            if (ctr == 0) {
                buf.append(s);
            }else {
                buf.append("," + s);
            }
            ctr++;

        }
        return buf.toString();

    }

}
