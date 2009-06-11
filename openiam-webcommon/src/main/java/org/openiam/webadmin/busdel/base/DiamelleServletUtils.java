package org.openiam.webadmin.busdel.base;

import javax.servlet.http.*;

public class DiamelleServletUtils {
  private HttpServletRequest request = null;
  private HttpSession session = null;
  private int DEFAULT_MAX = 10;
  private String NEXT_PAGE = "Next";
  //private String PREV_PAGE = "Prev";

  public DiamelleServletUtils(HttpSession session, HttpServletRequest request)  {
    this.request = request;
    this.session = session;
  }

  public int getMaxRec(String maxRecAttribute) {
      int maxRec = DEFAULT_MAX;
      Integer i = (Integer) session.getAttribute(maxRecAttribute);
      if (i != null)  maxRec = i.intValue();

      return maxRec;
  }

  public int getStartRec(String startRecAttrib) {
      int startRec = 1;
      Integer i = (Integer) session.getAttribute(startRecAttrib);
      if (i != null)  startRec = i.intValue();
      return startRec;
  }

  public int getNewStart(String pageAttrib, int startRec, int maxRec) {
      String page = request.getParameter(pageAttrib);
      int newStart = 0;

      if (page!= null) {
        if (page.equalsIgnoreCase(NEXT_PAGE)) {
          newStart = startRec + maxRec;
        } else {
           newStart = startRec - maxRec;
           if (newStart < 0) newStart = 0;
        }
      }
      return newStart;
  }

}