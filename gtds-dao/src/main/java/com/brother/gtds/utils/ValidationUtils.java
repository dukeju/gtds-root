package com.brother.gtds.utils;

import java.util.Collection;

public class ValidationUtils {

	//检验字符串的有效性
	public static boolean validateString(String str)
	{
		if(str != null && str.trim().length() != 0)
			return true;
		return false;
	}
	
	//检验集合的有效性
	public static boolean validateColl(Collection c)
	{
		if(c != null && c.size() > 0)
			return true;
		return false;
	}
	
}
