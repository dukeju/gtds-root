<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>历史课题</title>

  </head>
  
  <body>
  	<s:form action="TaskAction_showHistoryTasks" method="post">
  		时间：<s:select list="#{0:'本届',1:'近一年',2:'近两年',3:'近三年'}" name="year"></s:select>
  		面向专业：<s:select list="%{majors}" listKey="id" listValue="majorName" name="majorQuery"></s:select>
  		<s:submit value="确定"></s:submit>
  	</s:form>
    <table border="1" cellspacing="0" width="100%">
    	<tr>
    		<td></td>
    		<td>课题名称</td>
    		<td>课题要求</td>
    		<td>类型</td>
    		<td>来源</td>
    		<td>面向专业</td>
    		<td>容量</td>
    		<td>拟定者</td>
    		<td>发布时间</td>
    	</tr> 
    	<s:iterator value="%{historyTasks}" status="st">
    		<tr>
    			<td><s:property value="#st.index + 1" /></td>
    			<td><s:property value="name"/></td>
    			<td><s:property value="demand"/></td>
    			<td><s:property value="type"/></td>
    			<td><s:property value="source"/></td>  
    			<td><s:property value="major.majorName"/></td>
    			<td><s:property value="capacity"/></td>
    			<td><s:property value="tutor.name"/></td>
    			<td><s:date name="publishDate" format="yyyy-MM-dd"/></td>
    		</tr>
    	</s:iterator>
    </table>
  </body>
</html>
