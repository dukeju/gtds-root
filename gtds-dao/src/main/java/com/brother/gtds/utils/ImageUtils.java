package com.brother.gtds.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

//处理图片的工具类
public class ImageUtils {

	//随机字符字典，不包括0，O，1，I等难以辨认的字符
		private static final char[] CHARS = { '2', '3', '4', '5', '6', '7', '8',
			'9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M',
			'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
		
		private static Random random = new Random();
		
		//返回装载验证码字符串和验证码图片的Map
		public static Map<String, Object> getIndentityingCodeImage()
		{
			Map<String, Object> map = new HashMap<String, Object>();
			
			String identity = getRandomStr();
			map.put("identityingCode", identity);
			
			//图片宽度
			int width = 80;
			//图片高度
			int height = 30;
			//背景色
			Color bgColor = getRandomColor();
			//前景色
			Color fgColor = getReverseColor(bgColor);
			
			//创建一个彩色图片
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			//获取绘图对象,其作用相当于画笔 
			Graphics2D graphics = image.createGraphics();
			graphics.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
			graphics.setColor(bgColor);
			graphics.fillRect(0, 0, width, height);
			graphics.setColor(fgColor);
			graphics.drawString(identity, 18, 20);
			//画最多50个噪音点
			for(int i = 0, n = random.nextInt(50); i < n; i++)
			{
				graphics.drawRect(random.nextInt(width), random.nextInt(height), 1, 1);
			}
			//释放g所占用的系统资源
			graphics.dispose();
			map.put("image", image);
			
			return map;
		}
		
		//获取4个随机字符
		private static String getRandomStr()
		{
			StringBuffer buffer = new StringBuffer();
			for(int i = 0; i < 4; i++)
			{
				buffer.append(CHARS[random.nextInt(CHARS.length)]);
			}
			return buffer.toString();
		}
		
		//获取随机的颜色，作为背景色
		private static Color getRandomColor()
		{
			return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
		}
		
		//返回指定颜色的反色，作前景色
		private static Color getReverseColor(Color color)
		{
			return new Color(255 - color.getRed(), 255 - color.getGreen(), 255 - color.getBlue());
		}
	
}
