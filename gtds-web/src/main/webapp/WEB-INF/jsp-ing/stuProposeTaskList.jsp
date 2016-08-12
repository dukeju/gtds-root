<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>自拟课题申请列表</title>

  </head>
  
  <body>
  	<s:form action="TaskAction_batchUpdateStudentTasks" method="post">
	    <table width="100%" border="1" cellspacing="0">
	    	<tr>
	    		<td></td>
	    		<td>课题名称</td>
	    		<td>类型</td>
	    		<td>来源</td>
	    		<td>要求</td>
	    		<td>拟题学生</td>
	    		<td>专业</td>
	    		<td>时间</td>
	    		<td>通过</td>
	    	</tr>
	    	<s:set value="#{0:'工程设计',1:'理论研究',2:'实验研究',3:'计算机软件研制',4:'综合',5:'经管文',6:'法学',7:'艺术'}"
				var="type"></s:set>
			<s:set value="#{0:'国家级科研项目',1:'部级科研项目',2:'省级科研项目',3:'厅级科研项目',4:'市级科研项目',5:'校级科研项目',6:'企业项目',7:'生产实际',8:'实际应用',9:'自选'}"
				var="source"></s:set>
	    	<s:iterator value="%{tasks}" status="st">
	    		<tr>
	    			<td><s:property value="#st.index + 1" /></td>
	    			<td><s:property value="name" /></td>
	    			<td><s:property value="%{#type[type]}"/></td>
    				<td><s:property value="%{#source[source]}"/></td>
    				<td><s:property value="demand" /></td>
	    			<td><s:a action="StudentAction_studentInfo?idQuery=%{student.id}"><s:property value="student.name" /></s:a></td>
	    			<td><s:property value="major.majorName" /></td>
	    			<td><s:date name="publishDate" format="yyyy-MM-dd"/></td>
	    			<td>
	    				<s:if test="pass">通过</s:if>
	    				<s:else>
		    				<s:hidden name="tasks[%{#st.index}].id"></s:hidden>
		    				<s:checkbox name="tasks[%{#st.index}].pass"></s:checkbox>
	    				</s:else>
	    			</td>
	    		</tr>
	    	</s:iterator>
	    </table>
	    <br>
	    <center><s:submit value="确定" /></center>
  	</s:form>
  </body>
</html>
