package com.brother.gtds.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

//文件处理类
public class FileUtils {

	//保存文件
	public static void saveFile(File source, File dest) throws Exception
	{
		InputStream is = new FileInputStream(source);
		OutputStream os = new FileOutputStream(dest);
		int length = 0;
		byte[] bytes = new byte[1024];
		while((length = is.read(bytes)) != -1)
		{
			os.write(bytes, 0, length);
		}
		is.close();
		os.close();
	}
	
	//删除文件
	public static void deleteFile(File file)
	{
		// 路径为文件且不为空则进行删除
		if(file.isFile() && file.exists())
			file.delete();
	}
	
}
