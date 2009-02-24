package org.openiam.selfsrvc;

/**
 * Obtains configuration information for the self service application.
 * @author suneet
 * @version 2
 *
 */
public class AppConfiguration {

	private String logoUrl;
	private String title;
	private Integer maxResultSetSize;	

	
	public Integer getMaxResultSetSize() {
		return maxResultSetSize;
	}
	public void setMaxResultSetSize(Integer maxResultSetSize) {
		this.maxResultSetSize = maxResultSetSize;
	}
	
	public String getLogoUrl() {
		return logoUrl;
	}
	public void setLogoUrl(String url) {
		logoUrl = url;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String t) {
		title = t;
	}


	
}


