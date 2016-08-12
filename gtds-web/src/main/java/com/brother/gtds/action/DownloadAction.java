package com.brother.gtds.action;

import java.io.InputStream;

import javax.servlet.ServletContext;

import org.apache.struts2.util.ServletContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@SuppressWarnings("rawtypes")
@Controller
@Scope("prototype")
public class DownloadAction extends BaseAction implements ServletContextAware {

	private static final long serialVersionUID = 3267432232253938690L;

	/*
	 * 1-广东工业大学本科生毕业设计（论文）创新奖申报表.docx
	 * 2-广东工业大学本科生毕业设计（论文）答辩安排表.docx
	 * 3-广东工业大学本科生毕业设计（论文）答辩记录.docx
	 * 4-广东工业大学本科生毕业设计（论文）教师拟题审批表.docx
	 * 5-广东工业大学本科生毕业设计（论文）评议表(二).docx
	 * 6-广东工业大学本科生毕业设计（论文）评议表(一).docx
	 * 7-广东工业大学本科生毕业设计（论文）任务书.docx
	 * 8-广东工业大学本科生毕业设计（论文）指导情况记录表.docx
	 * 9-广东工业大学本科生毕业设计（论文）中期检查表.docx
	 * 10-广东工业大学本科生毕业设计（论文）学生拟题审批表.docx
	 */
	private Integer number;
	private String fileName;
	
	private InputStream inputStream;
	
	private ServletContext sc;
	
	public String execute() throws Exception
	{
		switch(number)
		{
			case 1 : fileName = "广东工业大学本科生毕业设计（论文）创新奖申报表.docx"; break;
			case 2 : fileName = "广东工业大学本科生毕业设计（论文）答辩安排表.docx"; break;
			case 3 : fileName = "广东工业大学本科生毕业设计（论文）答辩记录.docx"; break;
			case 4 : fileName = "广东工业大学本科生毕业设计（论文）教师拟题审批表.docx"; break;
			case 5 : fileName = "广东工业大学本科生毕业设计（论文）评议表(二).docx"; break;
			case 6 : fileName = "广东工业大学本科生毕业设计（论文）评议表(一).docx"; break;
			case 7 : fileName = "广东工业大学本科生毕业设计（论文）任务书.docx"; break;
			case 8 : fileName = "广东工业大学本科生毕业设计（论文）指导情况记录表.docx"; break;
			case 9 : fileName = "广东工业大学本科生毕业设计（论文）中期检查表.docx"; break;
			case 10 : fileName = "广东工业大学本科生毕业设计（论文）学生拟题审批表.docx"; break;
		}
		inputStream = sc.getResourceAsStream("/相关表格/" + fileName);
		//解解乱码 ，iso-8859-1是JAVA网络传输使用的标准字符集
        fileName = new String(this.fileName.getBytes("GBK"),"ISO-8859-1");
		return SUCCESS;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	@Override
	public void setServletContext(ServletContext arg0) {
		this.sc = arg0;
	}
	
}
