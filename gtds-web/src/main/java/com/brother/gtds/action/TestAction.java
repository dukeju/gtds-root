package com.brother.gtds.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import javax.annotation.Resource;
import com.brother.gtds.service.TestService;
import com.opensymphony.xwork2.ActionSupport;

@Controller("testAction")
@Scope("prototype")
public class TestAction extends ActionSupport {

	private static final long serialVersionUID = -3122800968638530034L;
	
	@Resource
	private TestService testService;
	
	public String testAction() {
		System.out.println("testAction ........");
		testService.testService();
		return SUCCESS;
	}

}
