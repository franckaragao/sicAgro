package br.edu.ifpb.sicAgro.util.date;

import java.util.Date;

import com.ibm.icu.text.SimpleDateFormat;

/**
 * 
 * @author franck
 *
 */
public class DateUtils {

	public static String formatDate(Date date) {
		return new SimpleDateFormat("dd/MM/yyy").format(date);
	}
}
