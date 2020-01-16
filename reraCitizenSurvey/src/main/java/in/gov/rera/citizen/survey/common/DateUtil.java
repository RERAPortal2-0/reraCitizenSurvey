package in.gov.rera.citizen.survey.common;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class DateUtil {
	
	
	public static int getYearBack(int inp){
		int year=0;
		try{
		year=	Calendar.getInstance().get(Calendar.YEAR)-inp;
		
			
		}catch(Exception E){
			E.printStackTrace();
		}
		
		return year;
		
	}

	
	public static Date getDate(String cal)throws Exception{
		SimpleDateFormat dat=new SimpleDateFormat("dd/MM/yyyy");
		if(cal!=null){
			if(cal.contains("-"))
				 dat=new SimpleDateFormat("dd-MM-yyyy");
		return dat.parse(cal);
		}
		
		
		return null;
	}
	public static String getDateString(Calendar cal){
		if(cal!=null){
//		SimpleDateFormat dat=new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat dat=new SimpleDateFormat("dd/MM/yyyy");
		return dat.format(cal.getTime());
		}
		
		return " Date Not exist ";
	}
	
	public static String getDateString(Date cal){
		if(cal!=null){
		SimpleDateFormat dat=new SimpleDateFormat("yyyy-MM-dd");
		return dat.format(cal);
		}
		
		return " Date Not exist ";
	}
	
	public static String getDateStringFormat(Date cal){
		if(cal!=null){
		SimpleDateFormat dat=new SimpleDateFormat("dd/MM/yyyy");
		return dat.format(cal);
		}
		
		return " Date Not exist ";
	}
	
	public static int diffInDays(Calendar first,Calendar second){
		int diff=0;
		if(first!=null && second!=null){
		Calendar c1=Calendar.getInstance();
		Calendar c2=Calendar.getInstance();
		c1.setTimeInMillis(first.getTimeInMillis());
		c2.setTimeInMillis(second.getTimeInMillis());
		
		c1.set(Calendar.HOUR, 0);
		c1.set(Calendar.MINUTE, 0);
		c1.set(Calendar.SECOND, 0);
		c1.set(Calendar.MILLISECOND, 10);
		c2.set(Calendar.HOUR, 0);
		c2.set(Calendar.MINUTE, 0);
		c2.set(Calendar.SECOND, 0);
		c2.set(Calendar.MILLISECOND, 12);
		diff=(int)((c2.getTimeInMillis()-c1.getTimeInMillis())/(1000*60*60*24));
		
		
		}
		
		return diff;
	}
	
	public static boolean isFutureDate(Date dt){
		return new Date().getTime()<dt.getTime();
	}
	
	
	public static List<String> getYearList(Calendar start,Calendar end){
		 List<String> list=new ArrayList<String>();
		 int st=start.get(Calendar.YEAR);
		 int en=end.get(Calendar.YEAR);
		 do{
			 list.add(""+st);
			 st++;
		 }
		 while(st<=en);
		 
		 return list;
	}
	
	public static boolean isPastDate(Date dt){
		return new Date().getTime()>dt.getTime();
	}
	

	public static String getCurrentDateForCert()throws Exception{
		
		Calendar cal=Calendar.getInstance();
		SimpleDateFormat dat=new SimpleDateFormat("dd/MM/yyyy hh:mm");
		return dat.format(cal.getTime());
		
	}
	
public static String getCurrentDateForCertAMPM()throws Exception{
		
		Calendar cal=Calendar.getInstance();
		SimpleDateFormat dat=new SimpleDateFormat("dd/MM/yyyy hh:mm a");
		return dat.format(cal.getTime());
		
	}
	
	public static String getDateFromString(String cal) {
		String rtnDate = "";
		try {
			if (cal != null) {
				SimpleDateFormat dat = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				Date d = dat.parse(cal);
				DateFormat dformat = new SimpleDateFormat("dd/MM/yyyy");
				rtnDate = dformat.format(d);	
			}

		} catch (Exception e) {
			return "";
		}
		return rtnDate;
	}
	
	public static Calendar getStringToCalendar(String dataTime)throws Exception{
		Calendar cal = Calendar.getInstance();
		if(dataTime!=null){
			SimpleDateFormat dat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			cal.setTime(dat.parse(dataTime));
			return cal;
		}
		
		
		return null;
	}
	
	public static Calendar getStringToCalendarFormat(String dataTime)throws Exception{
		Calendar cal = Calendar.getInstance();
		if(dataTime!=null){
			SimpleDateFormat dat=new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			cal.setTime(dat.parse(dataTime));
			return cal;
		}
		
		
		return null;
	}
	
	
public static Calendar getStartDateOfFinYear(){
	Calendar cal=Calendar.getInstance();
	
	
	int mnth=cal.get(Calendar.MONTH);
	if(mnth<3){
		cal.set(Calendar.YEAR,cal.get( Calendar.YEAR)-1);
	}
		
	cal.set(Calendar.MONTH, 3);
	cal.set(Calendar.DATE, 1);
	
    	return cal;
    }


public static Calendar getEndDateOfFinYear(){
	Calendar cal=Calendar.getInstance();
	
	
	int mnth=cal.get(Calendar.MONTH);
	if(mnth>2){
		cal.set(Calendar.YEAR,cal.get( Calendar.YEAR)+1);
	}
		
	cal.set(Calendar.MONTH, 2);
	cal.set(Calendar.DATE, 31);
	
    	return cal;
    }
public static Calendar getStartDateOfFinYear(Calendar c){
	Calendar cal=Calendar.getInstance();
	cal.setTime(c.getTime());
	int mnth=cal.get(Calendar.MONTH);
	if(mnth<3){
		cal.set(Calendar.YEAR,cal.get( Calendar.YEAR)-1);
	}
		
	cal.set(Calendar.MONTH, 3);
	cal.set(Calendar.DATE, 1);
	
    	return cal;
    }


public static Calendar getEndDateOfFinYear(Calendar c){
	Calendar cal=Calendar.getInstance();
	cal.setTime(c.getTime());
	int mnth=cal.get(Calendar.MONTH);
	if(mnth>2){
		cal.set(Calendar.YEAR,cal.get( Calendar.YEAR)+1);
	}
		
	cal.set(Calendar.MONTH, 2);
	cal.set(Calendar.DATE, 31);
	
    	return cal;
    }

public static String getFinYearId(Calendar cal){
	
	Calendar stdt=getStartDateOfFinYear(cal);
	Calendar endt=getEndDateOfFinYear(cal);
	return ""+(stdt.get(Calendar.YEAR))+"-"+(endt.get(Calendar.YEAR)%100);
	
}

public static void main(String[] args) {
	System.out.println(getStartDateOfFinYear().getTime());
	System.out.println(getEndDateOfFinYear().getTime());
}
}
