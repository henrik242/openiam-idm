package org.openiam.webadmin.busdel.base;

/**
 * Represents the details of a Tab option on the webpage.
 */
public class TabOption implements java.io.Serializable {

  public TabOption() {
  }

  public TabOption(String title, boolean active, String urlParam, String page) {
    this.title = title;
    this.active = active;
    this.urlParam = urlParam;
    this.relatedPage = page;
  }

  private String title;
  private boolean active;
  private String urlParam;
  private String relatedPage;


  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public void setActive(boolean active) {
    this.active = active;
  }
  public boolean isActive() {
    return active;
  }
  /**
   * URL fragment that refers to this option.
   * @param urlParam
   */
  public void setUrlParam(String urlParam) {
    this.urlParam = urlParam;
  }
  public String getUrlParam() {
    return urlParam;
  }
  public String getRelatedPage() {
    return relatedPage;
  }
  public void setRelatedPage(String relatedPage) {
    this.relatedPage = relatedPage;
  }
}