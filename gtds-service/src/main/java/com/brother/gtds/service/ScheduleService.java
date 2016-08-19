package com.brother.gtds.service;

import com.brother.gtds.model.Schedule;

public interface ScheduleService  {

	//返回现在时间是否早于指定日期
	boolean beforeProposeBegin(String dId);

	//返回现在时间是否在指定时间之后
	boolean beyondProposeExpiry(String departmentId);

	//返回是否早于学生选题时间
	boolean beforeSelectBegin(String dId);

	//返回是否超过学生选题日期
	boolean beyondSelectEnd(String dId);

	//返回是否早于学生自拟课题的开始日期
	boolean beforeStuProposeBegin(String dId);

	//返回是否超过学生自拟课题的日期(比开始选题早一天)
	boolean beyondStuProposeExpiry(String dId);
	
	//返回时间安排类
	Schedule getSchedule(String dId);

}
