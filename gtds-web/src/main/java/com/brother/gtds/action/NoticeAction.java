package com.brother.gtds.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.RequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.brother.gtds.action.aware.UserAware;
import com.brother.gtds.model.Admin;
import com.brother.gtds.model.Notice;
import com.brother.gtds.model.User;
import com.brother.gtds.service.NoticeService;

@Controller
@Scope("prototype")
public class NoticeAction extends BaseAction<Notice> implements UserAware, RequestAware {

	private static final long serialVersionUID = -2900506042325044503L;
	
	@Resource
	private NoticeService noticeService;
	
	private String tId;
	private String tIds;
	private Integer nId;
	
	private InputStream inputStream;
	
	private User user;
	private Map<String, Object> request;
	
	//返回我的消息集合
	public String getMyNotices()
	{
		this.request.put("unread", noticeService.getMyNotices(user, false));
		this.request.put("read", noticeService.getMyNotices(user, true));
		return "myNoticeListPage";
	}
	
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
	
	//返回该消息是否已经确认
	public boolean hasConfirm(Integer nId)
	{
		Notice n = this.noticeService.getEntity(nId);
		return n.isReceive();
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

	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

}
