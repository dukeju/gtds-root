package com.brother.gtds.service;

import java.util.List;

import com.brother.gtds.model.Admin;
import com.brother.gtds.model.Notice;
import com.brother.gtds.model.User;

public interface NoticeService extends BaseService<Notice> {

	void saveOrUpdateNotice(Notice notice);

	//发送指导人数未达到要求通知
	void dissatisfyNumber(Admin admin, String tId);
	
	//发送所有指导人数未达到要求通知
	void allDissatisfyNumber(Admin admin, String tIds);

	//返回有关自己的通知的集合
	List<Notice> getMyNotices(User user);

	//确认通知
	void confirmNotice(Integer nId);
	
	Notice getNotice(Integer id);
}
