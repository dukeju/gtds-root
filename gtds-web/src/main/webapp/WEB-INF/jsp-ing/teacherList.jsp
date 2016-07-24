<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %> 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>教师信息</title>

  </head>
  
  <body>
  	<s:set value="#{-1:'全部', 0:'助教', 1:'讲师', 2:'副教授', 3:'教授' }" var="positions"></s:set>
  	<s:set value="#{-1:'全部', 0:'专科', 1:'本科', 2:'研究生', 3:'硕士', 4:'博士' }" var="types"></s:set>
  	
  	<s:form action="TeacherAction_showTeachers" method="post">
  		工号：<s:textfield name="idQuery"></s:textfield><br>
  		姓名：<s:textfield name="nameQuery"></s:textfield><br>
  		职称：<s:select list="%{#positions}" name="positionQuery"></s:select><br>
  		指导类型：<s:select list="%{#types}" name="typeQuery"></s:select><br>
  		学院：<s:select list="departments" listKey="id" listValue="name" name="departmentQuery"></s:select><br>
  		<s:submit value="确定"></s:submit>
  	</s:form>
  	
    <table width="100%" border="1" cellspacing="0">
    	<tr>
    		<td>工号</td>
  			<td>姓名</td>
  			<td>学院</td>
  			<td>职称</td>
  			<td>研究方向</td>
  			<td>指导类别</td>
  			<td>邮箱</td>
  			<td>联系方式</td>
  			<td>详细信息</td>
    	</tr>
    	<s:iterator value="%{teachers}">
    		<tr>
    			<td><s:property value="id"/></td>
    			<td><s:property value="name"/></td>
    			<td><s:property value="department.name"/></td>
    			<td><s:property value="%{#positions[position]}"/></td>
    			<td><s:property value="research"/></td>
    			<td><s:property value="%{#types[type]}"/></td>
    			<td><s:property value="email"/></td> 
    			<td><s:property value="phone"/></td>
    			<td><s:a action="TeacherAction_getDetailInfo?teacherId=%{Id}" namespace="/">详细信息</s:a></td>
    		</tr>
    	</s:iterator>
    </table>
  </body>
</html>
