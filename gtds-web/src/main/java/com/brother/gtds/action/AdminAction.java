package com.brother.gtds.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.brother.gtds.model.Admin;
import com.brother.gtds.service.TeacherService;

@Controller
public class AdminAction extends BaseAction<Admin> {

	private static final long serialVersionUID = -8952902648562873446L;
	
	@Resource
	private TeacherService teacherService;
	
	private String tId;
	private Integer tType;
	
	//修改教师的指导类型
	public String updateType()
	{
		teacherService.updateType(tId, tType);
		return "teacherListAction";
	}

	public String gettId() {
		return tId;
	}

	public void settId(String tId) {
		this.tId = tId;
	}

	public Integer gettType() {
		return tType;
	}

	public void settType(Integer tType) {
		this.tType = tType;
	}

}
