<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>我的课题选择情况</title>
	<script type="text/javascript" src='<s:url value="/script/jquery-1.12.1.js"/>'></script>
    <script type="text/javascript">
    	$(function(){
    		$("#submit").click(function(){
    			return confirm("提交以后就不能修改了，确定要提交吗？");
    		})
    	})
    </script>
  </head>
  
  <body>
    <s:form action="StudentAction_batchUpdateStuTasks" method="post">
    	<table width="100%" border="1" cellspacing="0">
    		<tr>
    			<td></td>
    			<td>课题名称</td>
    			<td>选题学生</td>
    			<td>选题时间</td>
    			<td>通过</td>
    		</tr>
    		<s:iterator value="%{studentTasks}" status="st">
    			<tr>
    				<td><s:property value="#st.index + 1" /></td>
    				<td><s:a action="TaskAction_showTaskInfo2?taskId=%{task.id}"><s:property value="task.name" /></s:a></td>
    				<td><s:a action="StudentAction_studentInfo?idQuery=%{student.id}"><s:property value="student.name" /></s:a></td>
    				<td><s:date name="date" format="yyyy-MM-dd HH:mm" /></td>
    				<td>
    					<s:if test="pass">通过</s:if>
    					<s:else>
    						<s:hidden name="studentTasks[%{#st.index}].id"></s:hidden>
    						<s:checkbox name="studentTasks[%{#st.index}].pass"></s:checkbox>
    					</s:else>
    				</td>
    			</tr>
    		</s:iterator>
    	</table>
    	<center><s:submit value="确定" id="submit" /></center>
    </s:form>
  </body>
</html>
