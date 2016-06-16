package com.brother.gtds.action;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.brother.gtds.utils.ImageUtils;

@Controller
@Scope("prototype")
public class LoginAction<User> extends BaseAction<User> {

	private static final long serialVersionUID = -1981575389161456655L;
	
	private static String SESSION_IDENTITYING_CODE = "identityingCode";
	
	//用户输入的验证码
	private String identity;
	
	private InputStream inputStream;

	//登陆
	public String doLogin()
	{
		return SUCCESS;
	}
	
	//去登陆
	public String toLogin()
	{
		return "loginPage";
	}
	
	//显示验证码
	public String showIdentity()
	{
		OutputStream out = null;
		HttpServletResponse response = ServletActionContext.getResponse();
		//设置不缓存图片  
        response.setHeader("Pragma", "No-cache");  
        response.setHeader("Cache-Control", "No-cache");  
        response.setDateHeader("Expires", 0);  
        //设置输出类型,一定不能缺少这句话,否则错误.
		response.setContentType("image/jpeg");
		
		//获得装载验证码字符串和验证码图片的Map
		Map<String, Object> map = ImageUtils.getIndentityingCodeImage();
		
		//把验证码字符串放到session里
		ServletActionContext.getRequest().getSession().setAttribute(SESSION_IDENTITYING_CODE, map.get("identityingCode"));
		
		//输出图片
		try {
			out = response.getOutputStream();
			ImageIO.write((BufferedImage)(map.get("image")), "jpeg", out);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(out != null)
			{
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		//注意，此方法必须放回null
		return null;
	}
	
	//验证验证码是否正确
	public String validateIdentity() throws Exception
	{
		String identityingCode = (String) ServletActionContext.getRequest().getSession().getAttribute(SESSION_IDENTITYING_CODE);
		//验证通过（忽略大小写）
		if(identityingCode.equalsIgnoreCase(identity))
		{
			inputStream = new ByteArrayInputStream("1".getBytes("utf-8"));
		}
		else
		{
			inputStream = new ByteArrayInputStream("0".getBytes("utf-8"));
		}
		return "valiIden";
	}
	
	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}
	
	public InputStream getInputStream() {
		return inputStream;
	}
}
