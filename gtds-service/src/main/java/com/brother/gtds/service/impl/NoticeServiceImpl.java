package com.brother.gtds.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.brother.gtds.dao.BaseDao;
import com.brother.gtds.model.Admin;
import com.brother.gtds.model.Notice;
import com.brother.gtds.model.Student;
import com.brother.gtds.model.Teacher;
import com.brother.gtds.model.User;
import com.brother.gtds.service.NoticeService;
import com.brother.gtds.service.TaskService;
import com.brother.gtds.service.TeacherService;

@Service("noticeService")
public class NoticeServiceImpl extends BaseServiceImpl<Notice> implements
		NoticeService {

	@Resource
	private TeacherService teacherService;
	@Resource
	private TaskService taskService;
	
	@Resource(name="noticeDao")
	@Override
	public void setDao(BaseDao<Notice> dao) {
		super.setDao(dao);
	}

	@Override
	public void saveOrUpdateNotice(Notice notice) {
		notice.setDate(new Date());
		this.saveOrUpdateEntity(notice);
	}

	//发送指导人数未达到要求通知
	@Override
	public void dissatisfyNumber(Admin admin, String tId) {
		Notice notice = new Notice();
		notice.setAdminSender(admin);
		Teacher t = teacherService.getEntity(tId);
		notice.setTeaReceiver(t);
		notice.setTitle("指导人数未达到要求通知");
		notice.setMessage("你的最小指导人数为"+ t.getMinCount() +"，最大指导人数为"+ t.getMaxCount() +
				"，目前指导人数为"+ taskService.getTotalCount(t) +"，请尽快满足要求！");
		this.saveOrUpdateNotice(notice);
	}

	//发送所有指导人数未达到要求通知
	@Override
	public void allDissatisfyNumber(Admin admin, String tIds) {
		String[] arr = tIds.substring(0, tIds.length() - 1).split(",");
		for(int i = 0; i < arr.length; i++)
		{
			Notice notice = new Notice();
			notice.setAdminSender(admin);
			notice.setTitle("指导人数未达到要求通知");
			Teacher t = teacherService.getEntity(arr[i]);
			notice.setTeaReceiver(t);
			notice.setMessage("你的最小指导人数为"+ t.getMinCount() +"，最大指导人数为"+ t.getMaxCount() +
					"，目前指导人数为"+ taskService.getTotalCount(t) +"，请尽快满足要求！");
			this.saveOrUpdateNotice(notice);
		}
	}

	//返回有关自己的通知的集合
	@Override
	public List<Notice> getMyNotices(User user) {
		String hql = "";
		if(user instanceof Teacher)
			hql = "from Notice n where n.teaReceiver.id = ?";
		else if(user instanceof Student)
			hql = "from Notice n where n.stuReceiver.id = ?";
		hql += " and n.receive = ?";
		List<Notice> list = this.findEntityByHQL(hql, user.getId(), false);
		for(Notice n : list)
		{
			if(n.getAdminSender() != null)
				n.getAdminSender().getName();
			if(n.getStuReceiver() != null)
				n.getStuReceiver().getName();
			if(n.getTeaReceiver() != null)
				n.getTeaReceiver().getName();
			if(n.getTeaSender() != null)
				n.getTeaSender().getName();
			if(n.getDapartment() != null)
				n.getDapartment().getName();
		}
		return list;
	}

	//确认通知
	@Override
	public void confirmNotice(Integer nId) {
		Notice notice = this.getEntity(nId);
		notice.setReceive(true);
	}

	@Override
	public Notice getNotice(Integer id) {
		Notice n = this.getEntity(id);
		
		if(n.getAdminSender() != null)
			n.getAdminSender().getName();
		if(n.getStuReceiver() != null)
			n.getStuReceiver().getName();
		if(n.getTeaReceiver() != null)
			n.getTeaReceiver().getName();
		if(n.getTeaSender() != null)
			n.getTeaSender().getName();
		if(n.getDapartment() != null)
			n.getDapartment().getName();
		return n;
	}
	
}
