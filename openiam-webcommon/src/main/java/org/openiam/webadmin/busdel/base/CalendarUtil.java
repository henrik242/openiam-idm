//Calendar utility class

package org.openiam.webadmin.busdel.base;

import java.text.*;
import java.util.*;
import java.util.Calendar;


public class CalendarUtil {

    /**
     * Returns an java.sql.Date for a dateString
     *
     * @param dateString String
     * @param format String
     *
     * @return java.sql.Date
     */
    public static java.sql.Date getSqlDate(String dateString, String format) {

       DateFormat in = new SimpleDateFormat(format);
       java.util.Date utilDate = new java.util.Date();
       java.sql.Date sqldate = new java.sql.Date(System.currentTimeMillis());

       try {
           utilDate = in.parse(dateString);
           sqldate = new java.sql.Date(utilDate.getTime());
       } catch (ParseException e) {
            e.printStackTrace();
       }
       return sqldate;
    }

   /**
     * Returns an java.util.Date for a dateString
     *
     * @param dateString String
     * @param format String
     *
     * @return java.util.Date
     */
    public static java.util.Date getUtilDate(String dateString, String format) {

       DateFormat in = new SimpleDateFormat(format);
       java.util.Date utilDate = new java.util.Date();

       try {
           utilDate = in.parse(dateString);

       } catch (ParseException e) {
            e.printStackTrace();
       }
       return utilDate;
    }

    // to pass a date String in any format and get a date String in any given format
    public static String getDateString(String date, String informat, String outformat) throws ParseException {
          DateFormat in = new SimpleDateFormat(informat);
          DateFormat out = new SimpleDateFormat(outformat);
          java.util.Date utilDate = new java.util.Date();
          String dateString = "";

          try {
              utilDate = in.parse(date);
              dateString = out.format(utilDate);

          } catch (ParseException e) {
               e.printStackTrace();
          }
          return dateString;
    }


    
    /**
     * Returns an String equivalent of an java.sql.Date in the desired format
     *
     * @param sqlDate java.sql.Date
     * @param format String
     *
     * @return String
     */
    public static String getDateString(java.sql.Date sqlDate, String format) throws ParseException {
          DateFormat in = new SimpleDateFormat(format);
          String dateString = "";

          try {
              dateString = in.format(sqlDate);

          } catch (Exception e) {
               e.printStackTrace();
          }
          return dateString;
      }

      // to pass a java.util.Date and get a date string in the given format
    /**
     * Returns an String equivalent of an java.util.Date in the desired format
     *
     * @param date java.util.Date
     * @param format String
     *
     * @return String
     */
      public static String getDateString(java.util.Date date, String format) throws ParseException {
          DateFormat in = new SimpleDateFormat(format);
          java.util.Date utilDate = new java.util.Date();
          String dateString = "";

          try {
              dateString = in.format(utilDate);

          } catch (Exception e) {
               e.printStackTrace();
          }
          return dateString;
      }

    /**
     * Returns an List of months in the format 1 Jan 2004 .. 1 Dec 2004
     *
     * @return List
     */
      public static List getStartMonthList() throws ParseException {
          DateFormat in = new SimpleDateFormat("d MMM yyyy");
          java.util.Calendar cal = java.util.Calendar.getInstance();
          List dateList = new ArrayList(12);
          java.util.Date utilDate = new java.util.Date(System.currentTimeMillis());
          cal.set(java.util.Calendar.MONTH, Calendar.JANUARY);
          cal.set(Calendar.DATE, 1);
          utilDate = cal.getTime();
          String dateString = "";

          for (int i=0; i < 12; i++) {
              dateString = in.format(utilDate);
              dateList.add(dateString);

              cal.add(java.util.Calendar.MONTH, 1);
              utilDate = cal.getTime();

          }
          return dateList;
      }
      
      
    /**
     * Returns an List of months in the format 31 Jan 2004 .. 31 Dec 2004
     *
     * @return List
     */
     public static List getEndMonthList() throws ParseException {
                DateFormat in = new SimpleDateFormat("d MMM yyyy");
                java.util.Calendar cal = java.util.Calendar.getInstance();
                List dateList = new ArrayList(12);
                java.util.Date utilDate = new java.util.Date(System.currentTimeMillis());
                cal.set(java.util.Calendar.MONTH, Calendar.JANUARY);
                cal.set(java.util.Calendar.DATE, 31);
                utilDate = cal.getTime();
                String dateString = "";
      
                for (int i=0; i < 12; i++) {
                    dateString = in.format(utilDate);
                    dateList.add(dateString);
                    cal.add(Calendar.DATE, 1);  
                    cal.add(java.util.Calendar.MONTH,1);
                    cal.add(Calendar.DATE, -1);   
                    utilDate = cal.getTime();
      
                }
                return dateList;
      }
      
    
    /**
     * Returns an java.sql.Timestamp for the dateString , hh,mm,ampm passed
     *
     * @param dateString string
     * @param hr String
     * @param mm String
     * @param ampm String
     * @param format String
     * 
     * @return java.sql.Timestamp
     */
      public static java.sql.Timestamp getTimestamp(String dateString, String hr, String mm, String ampm, String format) throws ParseException {
            DateFormat in = new SimpleDateFormat(format);
            java.util.Calendar cal = java.util.Calendar.getInstance();
            java.sql.Timestamp timestamp = new java.sql.Timestamp(System.currentTimeMillis());

         
            try {
              java.util.Date utilDate = new java.util.Date();
              utilDate = in.parse(dateString);
              
              int hour = Integer.parseInt(hr);
              int minutes = Integer.parseInt(mm);
           
              cal.setTime(utilDate);
              cal.set(Calendar.HOUR, hour);
              cal.set(Calendar.MINUTE, minutes);

              
              if (ampm.equalsIgnoreCase("AM"))
                cal.set(Calendar.AM_PM, 0);
              else if (ampm.equalsIgnoreCase("PM"))
                cal.set(Calendar.AM_PM, 1);
              
              utilDate = cal.getTime();
              timestamp = new java.sql.Timestamp(utilDate.getTime());
              
            } catch (ParseException e) {
               e.printStackTrace();
            }
            return timestamp;
      }
      
      
 
   /**
     * Returns an java.sql.Timestamp equivalent of the dateString 
     * 
     * @param dateString String
     * @param format String
     * 
     * @return java.sql.Timestamp
     */
    public static java.sql.Timestamp getTimestamp(String dateString, String format) {

       DateFormat in = new SimpleDateFormat(format);
       java.util.Date utilDate = new java.util.Date();
       java.sql.Timestamp timestamp = new java.sql.Timestamp(System.currentTimeMillis());
       
       try {
           utilDate = in.parse(dateString);
           timestamp = new java.sql.Timestamp(utilDate.getTime());
       } catch (ParseException e) {
            e.printStackTrace();
       }
       return timestamp;
    }


    /**
     * Returns an String equivalent of the java.sql.Timestamp
     * 
     * @param timestamp java.sql.Timestamp
     * @param format String
     * 
     * @return String
     */
    public static String getDateString(java.sql.Timestamp timestamp, String format) throws ParseException {
          DateFormat in = new SimpleDateFormat(format);
          String dateString = "";

          try {
              dateString = in.format(timestamp);

          } catch (Exception e) {
               e.printStackTrace();
          }
          return dateString;
    }


    
    /**
     * Returns an List of time i.e incremented by the given minutes, say min = 15 then 12:00 , 12:15 etc
     * 
     * @param min int
     * 
     * @return List
     */
    public static List getTimeList(int min) {
      
       Calendar cal = Calendar.getInstance();
       java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
       
       SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");     
       SimpleDateFormat label = new SimpleDateFormat("hh:mm a");     
       
       List timeList = new ArrayList();
       
       try {
       
         java.sql.Timestamp stDate = new java.sql.Timestamp(format.parse(date +" 00:00:00 AM").getTime());
         java.sql.Timestamp lastDate = new java.sql.Timestamp(format.parse(date +" 24:00:00 AM").getTime());
                             
         while(!stDate.equals(lastDate)) {
             timeList.add(stDate);
           
             cal.setTime(stDate);
             cal.add(Calendar.MINUTE, min);
             stDate = new java.sql.Timestamp(cal.getTimeInMillis());         
         }
       } catch (Exception e) {
            e.printStackTrace();
       }
       return timeList;
    }
       
    
    /**
     * Returns true if the firstDate is before the secondDate
     * 
     * @param date1 java.sql.Date
     * @param date2 java.sql.Date
     * 
     * @return boolean
     */
    public static boolean isDateBefore (java.sql.Date date1, java.sql.Date date2) throws Exception {
        if (date1.before(date2))
           return true;
              
        return false;
    }  
    
    
    /**
     * Returns true if the firstDate is before the secondDate
     * 
     * @param date1 java.sql.Timestamp
     * @param date2 java.sql.Timestamp
     * 
     * @return boolean
     */
    public static boolean isDateBefore (java.sql.Timestamp date1, java.sql.Timestamp date2) throws Exception {
        if (date1.before(date2))
           return true;
              
        return false;
    }  
    
    
    /**
     * Returns String equivalent of sysDate
     * 
     * @param format String
     * 
     * @return String
     */
    public static String getSysDate (String format) throws ParseException {
        DateFormat in = new SimpleDateFormat(format);
              
        return in.format(new java.util.Date(System.currentTimeMillis()));
        
    }  
       
  /**
    * Elapsed days based on current time
    *
    * @param date Date
    *
    * @return int number of days
    */
    public static int getElapsedDays(java.sql.Date date) {
        return elapsed(date, Calendar.DATE);
    }
   
   /**
    * Elapsed days based on two Date objects
    *
    * @param d1 Date
    * @param d2 Date
    *
    * @return int number of days
    */
    public static int getElapsedDays(java.sql.Date d1, java.sql.Date d2) {
        return elapsed(d1, d2, Calendar.DATE);
    }
    /**
    * Elapsed months based on current time
    *
    * @param date Date
    *
    * @return int number of months
    */
    public static int getElapsedMonths(java.sql.Date date) {
        return elapsed(date, Calendar.MONTH);
    }
   /**
    * Elapsed months based on two Date objects
    *
    * @param d1 Date
    * @param d2 Date
    *
    * @return int number of months
    */
    public static int getElapsedMonths(java.sql.Date d1, java.sql.Date d2) {
        return elapsed(d1, d2, Calendar.MONTH);
    }
     /**
    * Elapsed years based on current time
    *
    * @param date Date
    *
    * @return int number of years
    */
    public static int getElapsedYears(java.sql.Date date) {
        return elapsed(date, Calendar.YEAR);
    } 
   /**
    * Elapsed years based on two Date objects
    *
    * @param d1 Date
    * @param d2 Date
    *
    * @return int number of years
    */
    /*public static int getElapsedYears(Date d1, Date d2) {
        return elapsed(d1, d2, Calendar.YEAR);
    }*/
     /**
     * All elaspsed types
     *
     * @param g1 GregorianCalendar
     * @param g2 GregorianCalendar
     * @param type int (Calendar.FIELD_NAME)
     *
     * @return int number of elapsed "type"
     */
    private static int elapsed(GregorianCalendar g1, GregorianCalendar g2, int type) {
        GregorianCalendar gc1, gc2;
        int elapsed = 0;
        // Create copies since we will be clearing/adding
        if (g2.after(g1)) {
            gc2 = (GregorianCalendar) g2.clone();
            gc1 = (GregorianCalendar) g1.clone();
        } else  {
            gc2 = (GregorianCalendar) g1.clone();
            gc1 = (GregorianCalendar) g2.clone();
        }
        if (type == Calendar.MONTH || type == Calendar.YEAR) {
            gc1.clear(Calendar.DATE);
            gc2.clear(Calendar.DATE);
        }
        if (type == Calendar.YEAR) {
            gc1.clear(Calendar.MONTH);
            gc2.clear(Calendar.MONTH);
        }
        while (gc1.before(gc2)) {
            gc1.add(type, 1);
            elapsed++;
        }
        return elapsed;
    }
     /**
     * All elaspsed types based on date and current Date
     *
     * @param date Date
     * @param type int (Calendar.FIELD_NAME)
     *
     * @return int number of elapsed "type"
     */
    private static int elapsed(java.sql.Date date, int type) {
        return elapsed(date, new java.sql.Date(System.currentTimeMillis()), type);
    }
     /**
     * All elaspsed types
     *
     * @param d1 Date
     * @param d2 Date
     * @param type int (Calendar.FIELD_NAME)
     *
     * @return int number of elapsed "type"
     */
    private static int elapsed(java.sql.Date d1, java.sql.Date d2, int type) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d1);
        GregorianCalendar g1 = new GregorianCalendar(
            cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
        cal.setTime(d2);
        GregorianCalendar g2 = new GregorianCalendar(
            cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
        return elapsed(g1, g2, type);
    }


			
    
    



  /**
   * 
   * @webmethod 
   */
     public static void main (String args[]) {
                  
      
         try {
            java.sql.Timestamp st = new java.sql.Timestamp(System.currentTimeMillis());
            
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            
            String start = "2004-06-04";
            
            System.out.println("  nnn   " + new java.sql.Date(sdf.parse(start).getTime()));
            
            Calendar cal = Calendar.getInstance();
            java.util.Date dt = new java.util.Date(sdf.parse(start).getTime());
            cal.setTime(dt);
            
            int month = cal.get(Calendar.MONTH);
            cal.set(Calendar.DATE, 1);
            
          
            cal.set(Calendar.DAY_OF_WEEK, 1);
          
            DateFormat sd = new SimpleDateFormat("EEE");
            dt = cal.getTime();
           
            System.out.println("day of week "+ sd.format(dt));

            java.util.Date utilDate = cal.getTime();
            java.sql.Date nextDate = new java.sql.Date( utilDate.getTime());

            

               while (cal.get(Calendar.MONTH) == month) {
            
                     System.out.println("cal  "+cal.getTime());
                     cal.add(Calendar.DATE, 1);
                     java.util.Date nextDay = cal.getTime();

                     nextDate = new java.sql.Date(nextDay.getTime());
                     
               }
               
               
            
         } catch (Exception e) {
               e.printStackTrace();
         }
     }
}