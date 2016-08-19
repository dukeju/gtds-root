<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    <title>课题详细信息</title>
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
							alert("你已经选择了课题!请退选了再选择。");
						else if(data == 3)
							alert("选择成功！");
						else
							alert("服务器错误！");
					}
				})
			})
		})  
    </script>

  </head>
  
  <body>
  	<div>
  		<fieldset>
  			<legend>课题信息</legend>
		  	<table width="100%" border="1" cellspacing="0">
		  		<tr>
		  			<td>课题名称</td>
		  			<td>导师姓名</td>
		  			<td>面向专业</td>
		  			<td>课题类型</td>
		  			<td>课题来源</td>
		  			<td>招收人数</td>
		  			<td>已选人数</td>
		  			<td>我的审批表</td>
		  		</tr>
		  		<tr>
		  			<s:set value="#{0:'工程设计',1:'理论研究',2:'实验研究',3:'计算机软件研制',4:'综合',5:'经管文',6:'法学',7:'艺术'}"
		  					var="taskType"></s:set>
		  			<s:set value="#{0:'国家级科研项目',1:'部级科研项目',2:'省级科研项目',3:'厅级科研项目',4:'市级科研项目',5:'校级科研项目',6:'企业项目',7:'生产实际',8:'实际应用',9:'自选'}"
		  					var="taskSource"></s:set>
		  			<td><s:property value="name" /></td>
		  			<td><s:property value="tutor.name" /></td>
		  			<td><s:property value="major.majorName" /></td>
		  			<td><s:property value="%{#taskType[type]}" /></td>
		  			<td><s:property value="%{#taskSource[source]}" /></td>
		  			<td><s:property value="capacity" /></td>
		  			<td><s:property value="getSelectedCount(id)" /></td>
		  			<td>
		  				<s:if test="student == null">无</s:if>
		  				<s:else>
							审批表：<s:a action="DownloadAction?number=10&path=%{path}">下载</s:a>
		  				</s:else>
		  			</td>
		  		</tr>
		  		<tr>
		  			<td colspan="8" align="center">课题要求</td>
		  		</tr>
		  		<tr>
		  			<td colspan="8"><s:property value="demand" /></td>
		  		</tr>
		  		<tr>
		  			<td colspan="8" align="center">
		  				<s:set value="isMySelectedTask(id)" var="selected"></s:set>
		  				<s:if test="!#selected">
			  				<a href="" class="select">选择课题</a>
			  				<s:hidden value="%{id}"></s:hidden>
		  				</s:if>
		  				<s:set value="#request.currentPage" var="currentPage"></s:set>
						<s:set value="#request.pageSize" var="size"></s:set>
		  				<s:a action="TaskAction_showChoiceTasks?pageNum=%{#currentPage}&pageSize=%{#size}">返回列表</s:a>
		  			</td>
		  		</tr>
		  	</table>
  		</fieldset>
  	</div>
  	<div>
  		<fieldset>
  			<legend>导师信息</legend>
  			<table width="100%" border="1" cellspacing="0">
  				<tr>
  					<td>姓名</td>
  					<td>学院</td>
  					<td>职称</td>
  					<td>研究方向</td>
  					<td>联系方式</td>
  					<td>邮箱</td>
  				</tr>
  				<tr>
  					<td>${requestScope.teacher.name }</td>
  					<td>${requestScope.teacher.department.name }</td>
  					<s:set value="#{0:'助教', 1:'讲师', 2:'副教授', 3:'教授' }" var="positions"></s:set>
  					<s:set value="#request.teacher.position" var="position"></s:set>
  					<td><s:property value="%{#positions[#position]}" /></td>
  					<td>${requestScope.teacher.research }</td>
  					<td>${requestScope.teacher.phone }</td>
  					<td>${requestScope.teacher.email }</td>
  				</tr>
  				<tr>
  					<td colspan="6" align="center">
  						<a href="">往届导师指导情况</a>
  						<a href="">往届毕业学生评价</a>
  					</td>
  				</tr>
  			</table>
  		</fieldset>
  	</div>
  </body>
</html>
