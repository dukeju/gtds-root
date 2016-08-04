package com.brother.gtds.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.brother.gtds.action.aware.UserAware;
import com.brother.gtds.model.Admin;
import com.brother.gtds.model.Notice;
import com.brother.gtds.model.User;
import com.brother.gtds.service.NoticeService;

@Controller
@Scope("prototype")
public class NoticeAction extends BaseAction<Notice> implements UserAware {

	private static final long serialVersionUID = -2900506042325044503L;
	
	@Resource
	private NoticeService noticeService;
	
	private String tId;
	private String tIds;
	private Integer nId;
	
	private InputStream inputStream;
	
	private User user;
	
	//发送指导人数未达到要求通知
	public String dissatisfyNumber()
	{
		try
		{
			noticeService.dissatisfyNumber((Admin) user, tId);
			
			//通知成功
			inputStream = new ByteArrayInputStream("1".getBytes("utf-8"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "ajax";
	}
	
	//发送所有指导人数未达到要求通知
	public String allDissatisfyNumber()
	{
		try
		{
			noticeService.allDissatisfyNumber((Admin) user, tIds);
			//通知成功
			inputStream = new ByteArrayInputStream("1".getBytes("utf-8"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "ajax";
	}
	
	//返回通知的详细页面
	public String noticeInfo()
	{
		this.model = noticeService.getNotice(nId);
		return "noticeInfoPage";
	}
	
	//确认通知
	public String confirmNotice()
	{
		try
		{
			this.noticeService.confirmNotice(nId);
			inputStream = new ByteArrayInputStream("1".getBytes("utf-8"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "ajax";
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getTId() {
		return tId;
	}

	public void setTId(String tId) {
		this.tId = tId;
	}

	@Override
	public void setUser(User user) {
		this.user = user;
	}

	public String getTIds() {
		return tIds;
	}

	public void setTIds(String tIds) {
		this.tIds = tIds;
	}

	public Integer getNId() {
		return nId;
	}

	public void setNId(Integer nId) {
		this.nId = nId;
	}

}
