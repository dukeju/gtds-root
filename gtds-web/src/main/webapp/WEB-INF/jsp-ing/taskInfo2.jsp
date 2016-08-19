<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    <title>课题详细信息</title>
    
  </head>
  
  <body>
  	<%-- <div>
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
		  		</tr>
		  		<tr>
		  			<td colspan="7" align="center">课题要求</td>
		  		</tr>
		  		<tr>
		  			<td colspan="7"><s:property value="demand" /></td>
		  		</tr>
		  		<tr>
		  			<td colspan="7" align="center">
		  				<a href="javascript:history.go(-1)">返回</a>
		  			</td>
		  		</tr>
		  	</table>
  		</fieldset>
  	</div> --%>
  	<s:set value="#{0:'工程设计',1:'理论研究',2:'实验研究',3:'计算机软件研制',4:'综合',5:'经管文',6:'法学',7:'艺术'}"
			var="taskType"></s:set>
	<s:set value="#{0:'国家级科研项目',1:'部级科研项目',2:'省级科研项目',3:'厅级科研项目',4:'市级科研项目',5:'校级科研项目',6:'企业项目',7:'生产实际',8:'实际应用',9:'自选'}"
			var="taskSource"></s:set>
  	课题名称：<s:property value="name" />
  	<br>
	导师姓名：<s:property value="tutor.name" />
	<br>
	面向专业：<s:property value="major.majorName" />
	<br>
	课题类型：<s:property value="%{#taskType[type]}" />
	<br>
	课题来源：<s:property value="%{#taskSource[source]}" />
	<br>
	课题要求：<s:property value="demand" />
	<br>
	招收人数：<s:property value="capacity" />
	<br>
	已选人数：<s:property value="getSelectedCount(id)" />
	<br>
	<s:form action="TaskAction_uploadStuTaskTable" method="post" enctype="multipart/form-data">
		<s:set value="%{student.id == null? 4 : 10}" var="number"></s:set>
		审批表：<s:a action="DownloadAction?number=%{#number}&path=%{path}">下载</s:a>
		<s:hidden name="taskId" value="%{id}"></s:hidden>
		上传：<s:file name="file"></s:file>
		<s:submit value="提交"></s:submit>
	</s:form>
	<a href="TaskAction_showStuProposeTasks">返回列表</a>
  </body>
</html>
