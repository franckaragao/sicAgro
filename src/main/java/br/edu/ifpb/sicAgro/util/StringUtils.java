package br.edu.ifpb.sicAgro.util;

public class StringUtils {
	
	
	public static String removerLeters(String str){
		String res = "";
		res= str.replaceAll("[^1-9 ]", "");
		
		return res.trim();
	}
	
	public static Long parseToLong(String str){
		Long nLong = null;
		if(!str.equals(""))
			nLong = Long.parseLong(str);
		
		return nLong;
	}
}
