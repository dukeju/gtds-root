<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>教师信息</title>

  </head>
  
  <body>
  	<s:set value="#{0:'助教', 1:'讲师', 2:'副教授', 3:'教授' }" var="positions"></s:set>
  	<s:set value="#{0:'专科', 1:'本科', 2:'研究生', 3:'硕士', 4:'博士' }" var="types"></s:set>
  
  	工号：<s:property value="id"/><br>
  	姓名：<s:property value="name"/><br>
  	学院：<s:property value="department.name"/><br>
  	职称：<s:property value="%{#positions[position]}"/><br>
  	指导类型：<s:property value="%{#types[type]}"/><br>
  	最小指导人数：<s:property value="minCount"/><br>
  	最大指导人数：<s:property value="maxCount"/><br>
  	<a href='TeacherAction_editTypeAndCount?teacherId=<s:property value='id'/>'>修改指导类型和数目</a><br>
  	研究方向：<s:property value="research"/><br>
  	邮箱：<s:property value="email"/><br>
  	联系方式：<s:property value="phone"/><br>
  	近年课题：<br>
  	答辩小组：<br>
  	学生评价：<br>
  	<!-- 
  	<input type="button" value="返回" onclick="location.href='javascript:history.go(-1);'">
  	 -->
  	 <s:a action="TeacherAction_showTeachers">返回教师列表</s:a>
  </body>
</html>
