<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>学生<s:property value="name" />信息</title>
    
  </head>
  
  <body>
  	学号：<s:property value="id" />
  	<br>
  	姓名：<s:property value="name" />
  	<br>
  	专业：<s:property value="major.majorName" />
  	<br>
  	班级：<s:property value="cla" />
  	<br>
  	学院：<s:property value="department.name" />
  	<br>
  	邮箱：<s:property value="email" />
  	<br>
  	联系方式：<s:property value="phone" />
  	<br>
  	<a href="javascript:history.go(-1)">返回</a>
  </body>
</html>
