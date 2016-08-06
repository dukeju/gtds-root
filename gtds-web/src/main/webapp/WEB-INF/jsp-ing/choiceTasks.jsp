<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>可选课题</title>
	<script type="text/javascript" src='<s:url value="/script/jquery-1.12.1.js"/>'></script>
    <script type="text/javascript">
		$(function(){
			$(".select").click(function(){
				var taskId = $(this).next().val();
				var url = "StudentAction_selectTask";
				var args = {"taskId":taskId};
				$.ajax({
					async:false, type:"post", data:args, dataType:"json", url:url,
					success:function(data){
						if(data == 0)
							alert("还没到选择课题的时间!");
						else if(data == 1)
							alert("已经超过了选择课题的时间!");
						else if(data == 2)
							alert("你已经选择过课题!请退选了再选择。");
						else if(data == 3)
							alert("选择成功！");
						else
							alert("服务器错误！");
					}
				})
			})
			$("#unselect").click(function(){
				var flag = confirm("确认要退选？");
				if(flag)
				{
					var taskId = $(this).next().val();
					var url = "StudentAction_unselectTask";
					var args = {"taskId":taskId};
					$.ajax({
						async:false, url:url, data:args, dataType:"json", type:"post",
						success:function(data){
							if(data == 0)
								alert("已经过了选择课题的时间！");
							else if(data == 1)
								alert("退选成功！");
							else
								alert("服务器错误，退选失败！");
						}
					})
				}
				else
					return false;
			})
		}) 	  
    </script>
  </head>
  
  <body>
	<s:set value="#request.taskPage.currentPage" var="currentPage"></s:set>
	<s:set value="#request.taskPage.pageSize" var="size"></s:set>
	
  	<s:form action="TaskAction_showChoiceTasks" method="post">
  		<s:set value="getChoiceTutors()" var="tutors"></s:set>
  		导师：<s:select list="%{#tutors}" listKey="id" listValue="name" name="tutorQuery"></s:select>
  		<s:submit value="确定"></s:submit>
  	</s:form>
  
    <table width="100%" border="1" cellspacing="0">
    	<tr>
    		<td></td>
    		<td>名称</td>
    		<td>导师</td>
    		<td>容量</td>
    		<td>已选人数</td>
    		<td>详情</td>
    		<td>选择</td>
    	</tr>
    	<s:iterator value="%{#request.taskPage.list}" status="st">
    		<tr>
    			<td><s:property value="#st.index + 1" /></td>
    			<td><s:property value="name" /></td>
    			<td><s:property value="tutor.name" /></td>
    			<td><s:property value="capacity" /></td>
    			<td><s:property value="getSelectedCount(id)" /></td>
    			<td><s:a action="TaskAction_showTaskInfo?taskId=%{id}&pageNum=%{#currentPage}&pageSize=%{#size}">详情</s:a></td>
    			<td>
					<a href="" class="select">选择课题</a>
		  			<s:hidden value="%{id}"></s:hidden>
				</td>
    		</tr>
    	</s:iterator>
     </table>
   	<center>
		<s:form action="TaskAction_showChoiceTasks" method="post">
			第${requestScope.taskPage.currentPage }页/共${requestScope.taskPage.totalPageCount }页&nbsp;
			<s:if test="#request.taskPage.hasPrev">
				<s:a action="TaskAction_showChoiceTasks?pageNum=%{#currentPage - 1}&pageSize=%{#size}">上一页</s:a>
			</s:if>
			<s:if test="#request.taskPage.hasNext">
				<s:a action="TaskAction_showChoiceTasks?pageNum=%{#currentPage + 1}&pageSize=%{#size}">下一页</s:a>
			</s:if>
			共${requestScope.taskPage.rows }条记录
			每页显示<s:textfield name="pageSize" value="%{#request.taskPage.pageSize}" size="1"></s:textfield>条记录
			<%-- <s:if test="pageNumber != 1">
				<s:a action="StudentAction_getChoiceTasks?pageNumber=%{pageNumber - 1}">上一页</s:a>
			</s:if>
			<s:a action="StudentAction_getChoiceTasks?pageNumber=1">1</s:a>
			<s:if test="pageNumber > 4">...</s:if>
			<s:bean name="org.apache.struts2.util.Counter">
				<s:param name="first" value="pageNumber>4 ? pageNumber-2 : 2"></s:param>
				<s:param name="last" 
					value="pageNumber<4 ? totalPage>6 ? 6 : totalPage : pageNumber < totalPage-2 ? pageNumber + 2 : totalPage"></s:param>
				<s:iterator status="st">
					<!-- 本页 -->
					<s:if test="#st.index + 1 == pageNumber">
						<font color="#ff0000"><s:property /></font>
					</s:if>
					<s:else>
						<s:a action="StudentAction_getChoiceTasks?pageNumber=<s:property/>"><s:property/></s:a>
					</s:else>
				</s:iterator>
			</s:bean>
			<s:if test="pageNumber < totalPage - 4">...</s:if>
			<s:a action="StudentAction_getChoiceTasks?pageNumber=%{totalPage}"><s:property value="totalPage" /></s:a>
			<s:if test="pageNumber != totalPage">
				<s:a action="StudentAction_getChoiceTasks?pageNumber=%{pageNumber + 1}">下一页</s:a>
			</s:if> --%>
		</s:form>
		<s:form action="TaskAction_showChoiceTasks">
			<s:hidden name="pageSize" value="%{#size}"></s:hidden>
			跳转到<s:textfield name="pageNum" value="" size="1"></s:textfield>页
		</s:form>
   	</center>
    <div>
    	<fieldset>
    		<legend>已选课题</legend>
    		<table width="100%" border="1" cellspacing="0">
    			<tr>	
    				<td>课题名称</td>
    				<td>导师</td>
    				<td>容量</td>
		    		<td>已选人数</td>
		    		<td>详情</td>
		    		<td>退选</td>
    			</tr>
    			<s:if test="#request.selectedTask != null">
	    			<tr>
	    				<td>${requestScope.selectedTask.name }</td>
	    				<td>${requestScope.selectedTask.tutor.name }</td>
	    				<td>${requestScope.selectedTask.capacity }</td>
	    				<s:set value="#request.selectedTask.id" var="taskId"></s:set>
	    				<td><s:property value="getSelectedCount(#taskId)" /></td>
	    				<td><s:a action="TaskAction_showTaskInfo?taskId=%{#taskId}">详情</s:a></td>
	    				<td>
	    					<a href="" id="unselect">退选</a>
	    					<s:hidden value="%{#request.selectedTask.id}"></s:hidden>
	    				</td>
	    			</tr>
    			</s:if>
    		</table>
    	</fieldset>
    </div>
  </body>
</html>
