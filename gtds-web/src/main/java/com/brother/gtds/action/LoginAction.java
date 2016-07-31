package com.brother.gtds.action;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.brother.gtds.model.Notice;
import com.brother.gtds.model.User;
import com.brother.gtds.service.NoticeService;
import com.brother.gtds.service.UserService;
import com.brother.gtds.utils.ImageUtils;
import com.brother.gtds.utils.ValidationUtils;

@Controller
@Scope("prototype")
public class LoginAction extends BaseAction<User> implements SessionAware{

	private static final long serialVersionUID = -1981575389161456655L;
	
	private static String SESSION_IDENTITYING_CODE = "identityingCode";
	
	@Resource
	private UserService userService;
	@Resource
	private NoticeService noticeService;
	
	//用户输入的验证码
	private String identity;
	
	private InputStream inputStream;
	
	private Map<String, Object> session;
	
	//身份，0-学生，1-教师，2-管理员
	private Integer status;
	
	//去登陆
	public String toLogin()
	{
		return "loginPage";
	}

	//登陆
	public String doLogin()
	{
		return SUCCESS;
	}
	
	//进行登陆校验，并且只校验 doLogin 方法
	public void validateDoLogin()
	{
		User user = userService.validateUserInfo(model.getId(), model.getPassword(), status);
		//登陆失败
		if(user == null)
			this.addActionError("用户名或者密码错误！");
		//登陆成功，把用户放到session里
		else
			session.put("user", user);
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
		return "valiIdenAjax";
	}
	
	//返回有关自己的通知的集合
	public List<Notice> getMyNotices()
	{
		List<Notice> list = this.noticeService.getMyNotices((User) session.get("user"));
		return ValidationUtils.validateColl(list)? list : null;
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

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
