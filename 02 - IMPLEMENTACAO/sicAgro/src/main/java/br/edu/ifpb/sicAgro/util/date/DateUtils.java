package br.edu.ifpb.sicAgro.util.date;

import java.util.Calendar;
import java.util.Date;

import com.ibm.icu.text.SimpleDateFormat;

/**
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
public class DateUtils {

	public static String formatDate(Date date) {
		return new SimpleDateFormat("dd/MM/yyy").format(date);
	}
	
	public static Date dateWithHours(Date date, Integer hours){
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR, hours);
		
		return calendar.getTime();
	}
}
