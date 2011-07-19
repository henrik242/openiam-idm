package org.openiam.selfsrvc.usradmin;

import org.openiam.idm.srvc.user.dto.UserAttribute;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Created by IntelliJ IDEA.
 * User: suneetshah
 * Date: 4/28/11
 * Time: 9:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class DelegationFilterHelper {



    static public List<String> getOrgIdFilterFromString(Map<String, UserAttribute> attrMap) {
           List<String> filterLst = new ArrayList<String>();

        if (attrMap.get("DLG_FLT_ORG") == null)  {
            return null;
        }
        String filter =  attrMap.get("DLG_FLT_ORG").getValue();
        if (filter == null || filter.length() ==0) {
            return null;
        }


        StringTokenizer tokenizer = new StringTokenizer(filter, ",");

        while ( tokenizer.hasMoreTokens()) {
             filterLst.add(tokenizer.nextToken());
        }
        return filterLst;

    }

    static public List<String> getDeptFilterFromString(Map<String, UserAttribute> attrMap) {
        List<String> filterLst = new ArrayList<String>();

        if (attrMap.get("DLG_FLT_DEPT") == null )  {
            return null;
        }
        String filter =  attrMap.get("DLG_FLT_DEPT").getValue();
        if (filter == null || filter.length() ==0) {
            return null;
        }


        StringTokenizer tokenizer = new StringTokenizer(filter, ",");

        while ( tokenizer.hasMoreTokens()) {
            filterLst.add(tokenizer.nextToken());
        }
        return filterLst;

    }

    static public List<String> getDivisionFilterFromString(Map<String, UserAttribute> attrMap) {
            List<String> filterLst = new ArrayList<String>();

        if (attrMap.get("DLG_FLT_DIV") == null)  {
            return null;
        }
        String filter =  attrMap.get("DLG_FLT_DIV").getValue();
                if (filter == null || filter.length() ==0) {
            return null;
        }



       StringTokenizer tokenizer = new StringTokenizer(filter, ",");

       while ( tokenizer.hasMoreTokens()) {
           filterLst.add(tokenizer.nextToken());
       }
       return filterLst;

    }

    static public List<String> getRoleFilterFromString(Map<String, UserAttribute> attrMap) {
            List<String> filterLst = new ArrayList<String>();

        if (attrMap.get("DLG_FLT_ROLE") == null)  {
            return null;
        }
        String filter =  attrMap.get("DLG_FLT_ROLE").getValue();
        if (filter == null || filter.length() ==0) {
            return null;
        }



       StringTokenizer tokenizer = new StringTokenizer(filter, ",");

       while ( tokenizer.hasMoreTokens()) {
           filterLst.add(tokenizer.nextToken());
       }
       return filterLst;

    }


      static public List<String> getGroupFilterFromString(Map<String, UserAttribute> attrMap) {
            List<String> filterLst = new ArrayList<String>();

        if (attrMap.get("DLG_FLT_GRP") == null)  {
            return null;
        }
        String filter =  attrMap.get("DLG_FLT_GRP").getValue();
              if (filter == null || filter.length() ==0) {
            return null;
        }



       StringTokenizer tokenizer = new StringTokenizer(filter, ",");

       while ( tokenizer.hasMoreTokens()) {
           filterLst.add(tokenizer.nextToken());
       }
       return filterLst;

    }


    static public List<String> getAPPFilterFromString(Map<String, UserAttribute> attrMap) {
            List<String> filterLst = new ArrayList<String>();

        if (attrMap.get("DLG_FLT_APP") == null)  {
            return null;
        }
        String filter =  attrMap.get("DLG_FLT_APP").getValue();
        if (filter == null || filter.length() ==0) {
            return null;
        }



       StringTokenizer tokenizer = new StringTokenizer(filter, ",");

       while ( tokenizer.hasMoreTokens()) {
           filterLst.add(tokenizer.nextToken());
       }
       return filterLst;

    }



}

