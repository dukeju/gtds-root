package com.brother.gtds.listeners;

import javax.annotation.Resource;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.brother.gtds.service.TaskService;

/*
 * 删除超过三年的课题数据
 */
@SuppressWarnings("rawtypes")
@Component
public class DeleteTasksListener implements ApplicationListener {

	@Resource
	private TaskService taskService;
	
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		//在spring初始化完成后调用
		if(event instanceof ContextRefreshedEvent)
		{
			taskService.deleteBeyondYears(3);
		}
	}

}
