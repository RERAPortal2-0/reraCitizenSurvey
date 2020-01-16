package in.gov.rera.citizen.survey.common;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import in.gov.rera.citizen.survey.exception.ResourceNotFoundException;

public class Util {

	private static final int    FIRST_FISCAL_MONTH  = Calendar.MARCH;

    private Calendar calendarDate;

    public Util(Calendar calendarDate) {
        this.calendarDate = calendarDate;
    }

	public static String checkNullSpace(String val, String msg) throws ResourceNotFoundException {
		if ("" != val.trim() && val != null ) {
			return val;
		} else {
			throw new ResourceNotFoundException( msg + " value is Required");
		}
	}
    
	public static String getDateString(Calendar cal){
		if(cal!=null){
//		SimpleDateFormat dat=new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat dat=new SimpleDateFormat("dd/MM/yyyy");
		return dat.format(cal.getTime());
		}
		
		return " Date Not exist ";
	}
	
	public static String getCanvertDateFormat(String strDate) throws ResourceNotFoundException {
		 //String strDate="28-May-2019";
		  String newDate="";
	      SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
	      try {
	          Date varDate=dateFormat.parse(strDate);
	          dateFormat=new SimpleDateFormat("yyyy-MM-dd");
	          newDate= dateFormat.format(varDate);
	      }catch (Exception e) {
	          // TODO: handle exception
	    	  throw new  ResourceNotFoundException("Please enter Right format in Date,Please Use the format:'DD-MMM-YYYY'");
	      }
		return newDate;
	    
	}
    
	public static String isNumeric(String str)throws ResourceNotFoundException { 
		  try {  
		    Double.parseDouble(str);  
		    return str;
		  } catch(Exception e){  
			  throw new  ResourceNotFoundException("Please enter Right format of number values");
		  }  
		}
    
    public Util(Date date) {
        this.calendarDate = Calendar.getInstance();
        this.calendarDate.setTime(date);
    }

    public int getFiscalMonth() {
        int month = calendarDate.get(Calendar.MONTH);
        int result = ((month - FIRST_FISCAL_MONTH - 1) % 12) + 1;
        if (result < 0) {
            result += 12;
        }
        return result;
    }

    public int getFiscalYear() {
        int month = calendarDate.get(Calendar.MONTH);
        int year = calendarDate.get(Calendar.YEAR);
        return (month >= FIRST_FISCAL_MONTH) ? year : year - 1;
    }

    public int getCalendarMonth() {
        return calendarDate.get(Calendar.MONTH);
    }

    public int getCalendarYear() {
        return calendarDate.get(Calendar.YEAR);
    }

	
	/*
	 * public static void main(String[] args) {
	 * //displayFinancialDate(Calendar.getInstance());
	 * //displayFinancialDate(setDate(2013, 1, 1));
	 * //displayFinancialDate(setDate(2012, 6, 25)); }
	 */
	 

    
    public static long yearBetween(Calendar startDate, Calendar endDate) {
        long end = endDate.YEAR;
        long start = startDate.YEAR;
        return (end - start);
    }
    
    
    
    
    
    
    private static Calendar setDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar;
    }

	public static List<String> getFinancialYear(Calendar startDate, Calendar endDate)
	{
		List<String> list = new ArrayList<String>();
		//int dayStart = startDate.get(startDate.DAY_OF_MONTH);
		//int monthStart = startDate.get(startDate.MONTH);
		int yearStart = startDate.get(startDate.YEAR);
		int dayEnd = endDate.get(endDate.DAY_OF_MONTH);
		int monthEnd = endDate.get(endDate.MONTH);
		int yearEnd = endDate.get(endDate.YEAR);
		if(monthEnd<=10 && dayEnd<=30) {
			--yearEnd;
		}
		for(int i=yearStart;i<=yearEnd;i++)
		{
			String fYear=i + "-" + (i + 1);
			list.add(fYear);
		}
		return list;
		
	}
    
	  private static void displayFinancialDate(Calendar calendar) {
	  Util fiscalDate = new Util(calendar); 
	  int year = fiscalDate.getFiscalYear();
	  System.out.println("Current Date : " + calendar.getTime().toString());
	  System.out.println("Fiscal Years : " + year + "-" + (year + 1));
	  System.out.println("Fiscal Month : " + fiscalDate.getFiscalMonth());
	  System.out.println(" "); }
	 

}
