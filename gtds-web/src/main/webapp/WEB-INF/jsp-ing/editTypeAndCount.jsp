<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>编辑指导类型和数目</title>
    
  </head>
  
  <body>
    <s:form action="TeacherAction_updateTypeAndCount" method="post">
    	<s:hidden name="teacherId" value="%{id}"></s:hidden>
    	姓名：<s:property value="name"/><br>
    	指导类型：<s:select list="#{0:'专科', 1:'本科', 2:'研究生', 3:'硕士', 4:'博士' }" name="teacherType" value="type"></s:select><br>
    	数目：<s:textfield name="teacherCount"></s:textfield><br>
    	<s:submit value="确定"></s:submit>
    </s:form>
  </body>
</html>
