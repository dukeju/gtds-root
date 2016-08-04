<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>我的课题</title>

  </head>
  
  <body>
  	<s:a action="TaskAction_myCurrentTasks">我的本届课题</s:a>
  	<s:a action="TaskAction_myAllTasks">我的全部课题</s:a>
  	<br>
  	<s:set value="haveNewTask()" var="newTask"></s:set>
    <s:if test="#newTask">
  		<s:a action="TaskAction_editTask" namespace="/">新建课题</s:a>
  	</s:if>
  	<br>
  	我的最小指导人数：${requestScope.minCount }
  	<s:set value="beyondMinCount()" var="beyondMinCount"></s:set>
  	<s:if test="#beyondMinCount">
  		(已经达到最小指导人数的要求)
  	</s:if>
  	<s:else>
  		(<font color="red">还没达到最小指导人数的要求</font>)
  	</s:else>
  	<br>
  	我的最大指导人数：${requestScope.maxCount }
  	
    <table border="1" cellspacing="0" width="100%">
    	<tr>
    		<td></td>
    		<td>课题名称</td>
    		<td>课题要求</td>
    		<td>类型</td>
    		<td>来源</td>
    		<td>面向专业</td>
    		<td>容量</td>
    		<td>是否通过评审</td>
    		<td>发布时间</td>
    		<td>编辑</td>
    		<td>删除</td>
    	</tr> 
    	<s:iterator value="%{myTasks}" status="st">
    		<tr>
    			<td><s:property value="#st.index + 1" /></td>
    			<td><s:property value="name"/></td>  
    			<td><s:property value="demand"/></td>  
    			<td><s:property value="type"/></td>
    			<td><s:property value="source"/></td>  
    			<td><s:property value="major.majorName"/></td>
    			<td><s:property value="capacity"/></td>
    			<td><s:property value="%{pass?'通过':'未通过'}" /></td>
    			<td><s:date name="publishDate" format="yyyy-MM-dd"/></td>
    	 		<s:set value="isEditable(id)" var="editable"></s:set>
    	 		<s:if test="#editable">
	    			<td><s:a action="TaskAction_editTask?taskId=%{id}">编辑</s:a></td>
    				<td><s:a action="TaskAction_deleteTask?taskId=%{id}">删除</s:a></td>
    			</s:if>
    			<s:else>
	    			<td></td>
	    			<td></td>
    			</s:else> 
    		</tr>
    	</s:iterator>
    </table>
  </body>
</html>
