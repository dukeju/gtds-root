package com.brother.gtds.service.impl;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.brother.gtds.dao.BaseDao;
import com.brother.gtds.model.Schedule;
import com.brother.gtds.service.ScheduleService;

@Service("scheduleService")
public class ScheduleServiceImpl extends BaseServiceImpl<Schedule> implements
		ScheduleService {
	
	@Resource(name="scheduleDao")
	public void setDao(BaseDao<Schedule> dao)
	{
		super.setDao(dao);
	}
	
	@Override
	//返回时间安排类
	public Schedule getSchedule(String dId)
	{
		String hql = "from Schedule s where s.department.id = ?";
		return (Schedule) this.uniqueResult(hql, dId);
	}
	
	//返回现在时间是否早于指定日期
	private boolean beforeDate(Date date)
	{
		if(date != null && date.after(new Date()))
			return true;
		return false;
	}
	
	//返回现在时间是否在指定时间之后
	private boolean afterDate(Date date)
	{
		if(date != null && date.before(new Date()))
			return true;
		return false;
	}

	//返回是否早于老师拟题的日期
	@Override
	public boolean beforeProposeBegin(String dId)
	{
		Schedule d = this.getSchedule(dId);
		return beforeDate(d.getProposeBegin());
	}
	
	//返回是否超过截止出题日期
	@Override
	public boolean beyondProposeExpiry(String departmentId) {
		Schedule d = this.getSchedule(departmentId);
		return afterDate(d.getProposeExpiry());
	}

	//返回是否早于学生选题时间
	@Override
	public boolean beforeSelectBegin(String dId) {
		Schedule d = this.getSchedule(dId);
		return beforeDate(d.getSelectBegin());
	}

	//返回是否超过学生选题日期
	@Override
	public boolean beyondSelectEnd(String dId) {
		Schedule d = this.getSchedule(dId);
		return afterDate(d.getSelectEnd());
	}

	//返回是否超过学生自拟课题的日期(比开始选题早一天)
	@Override
	public boolean beyondStuProposeExpiry(String dId) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) +1);
		Date date = calendar.getTime();
		Schedule d = this.getSchedule(dId);
		//如果超过
		if(d.getSelectBegin() != null && d.getSelectBegin().before(date))
			return true;
		else
			return false;
	}

	//返回是否早于学生自拟课题的开始日期
	@Override
	public boolean beforeStuProposeBegin(String dId) {
		Schedule d = this.getSchedule(dId);
		return beforeDate(d.getStuProposeBegin());
	}

}
