//package com.brother.gtds.action;
//
//import java.io.File;
//import java.util.Calendar;
//
//import javax.servlet.ServletContext;
//
//import org.apache.struts2.util.ServletContextAware;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Controller;
//
//@SuppressWarnings("rawtypes")
//@Controller
//@Scope("prototype")
//public class UploadAction extends BaseAction implements ServletContextAware {
//
//	private static final long serialVersionUID = 370001332804318115L;
//
//	/*
//	 * 1-广东工业大学本科生毕业设计（论文）创新奖申报表.docx 
//	 * 2-广东工业大学本科生毕业设计（论文）答辩安排表.docx
//	 * 3-广东工业大学本科生毕业设计（论文）答辩记录.docx 
//	 * 4-广东工业大学本科生毕业设计（论文）教师拟题审批表.docx
//	 * 5-广东工业大学本科生毕业设计（论文）评议表(二).docx 
//	 * 6-广东工业大学本科生毕业设计（论文）评议表(一).docx
//	 * 7-广东工业大学本科生毕业设计（论文）任务书.docx 
//	 * 8-广东工业大学本科生毕业设计（论文）指导情况记录表.docx
//	 * 9-广东工业大学本科生毕业设计（论文）中期检查表.docx
//	 */
//	private Integer number;
//	private Integer taskId;
//
//	private File file;
//	private String fileFileName;
//	private String fileContentType;
//	
//	private ServletContext sc;
//
//	public String execute()
//	{
//		Calendar calendar = Calendar.getInstance();
//		String path = "";
//		String fileName = "";
//		switch(number)
//		{
//		case(1):break;
//		case(2):break;
//		case(3):break;
//		case(4):
//			path = sc.getRealPath("/upload/教师拟题审批表");
////			fileName = 
//			break;
//		case(5):break;
//		case(6):break;
//		case(7):break;
//		case(8):break;
//		case(9):break;
//		}
//		return SUCCESS;
//	}
//	
//	public Integer getNumber() {
//		return number;
//	}
//
//	public void setNumber(Integer number) {
//		this.number = number;
//	}
//
//	public File getFile() {
//		return file;
//	}
//
//	public void setFile(File file) {
//		this.file = file;
//	}
//
//	public String getFileFileName() {
//		return fileFileName;
//	}
//
//	public void setFileFileName(String fileFileName) {
//		this.fileFileName = fileFileName;
//	}
//
//	public String getFileContentType() {
//		return fileContentType;
//	}
//
//	public void setFileContentType(String fileContentType) {
//		this.fileContentType = fileContentType;
//	}
//
//	public Integer getTaskId() {
//		return taskId;
//	}
//
//	public void setTaskId(Integer taskId) {
//		this.taskId = taskId;
//	}
//
//	@Override
//	public void setServletContext(ServletContext arg0) {
//		this.sc = arg0;
//	}
//
//}
