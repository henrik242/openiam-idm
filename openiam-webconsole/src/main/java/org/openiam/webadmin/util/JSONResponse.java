package org.openiam.webadmin.util;

public class JSONResponse {

	private boolean success = true;
	private Object data = null;
	
	public boolean getSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}
