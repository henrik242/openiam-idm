package org.openiam.webadmin.admin.sysmsg;

import java.io.Serializable;

import org.openiam.idm.srvc.msg.dto.SysMessage;

/**
 * Command object for the SysMsgDetailController
 * @author suneet
 *
 */
public class SysMsgCommand implements Serializable {


	

	protected SysMessage msg = new SysMessage();
    
	public SysMsgCommand() {
    	
    }

	public SysMessage getMsg() {
		return msg;
	}

	public void setMsg(SysMessage msg) {
		this.msg = msg;
	}


	


	

}
