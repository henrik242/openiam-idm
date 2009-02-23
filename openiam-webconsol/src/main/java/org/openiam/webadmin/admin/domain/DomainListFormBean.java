package org.openiam.webadmin.admin.domain;

import org.openiam.idm.srvc.secdomain.dto.SecurityDomain;

public class DomainListFormBean {

 private SecurityDomain rows[];

public SecurityDomain[] getRows() {
	return rows;
}

public void setRows(SecurityDomain[] rows) {
	this.rows = rows;
}
}
