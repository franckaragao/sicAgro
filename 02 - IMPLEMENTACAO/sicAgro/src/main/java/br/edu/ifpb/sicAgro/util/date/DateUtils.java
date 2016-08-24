package br.edu.ifpb.sicAgro.util.date;

import java.util.Date;

import br.edu.ifpb.sicAgro.exceptions.SicAgroExceptionHandler;

/**
 * 
 * @author franck
 *
 */
public class DateUtils {
	
	public static Date validatorDate(Date arg1, Date arg2, String throwMsg) throws SicAgroExceptionHandler{
		
		if(arg1.after(arg2)){
			throw new SicAgroExceptionHandler(throwMsg);
		}
		
		
		
		return arg2;
	}
	

}
