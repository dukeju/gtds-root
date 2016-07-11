package com.brother.gtds.utils;

public class ValidationUtils {

	//检验字符串是否为空
	public static boolean validateString(String str)
	{
		if(str != null && str != "")
			return true;
		return false;
	}
	
}
