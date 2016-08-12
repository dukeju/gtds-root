<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>编辑课题</title>
    
    <script type="text/javascript" src='<s:url value="/script/jquery-1.12.1.js"/>'></script>
    <script type="text/javascript">
    	//检验是否有同名的本届课题
    	function validateName()
    	{
    		var result = false;
    		var id = $("input[name='id']").val();
    		var name = $("input[name='name']").val();
    		name = $.trim(name);
    		if(name == "")
    		{
    			alert("课题名称不能为空！");
    		}
    		else
    		{
    			var url = "TaskAction_validateName";
    			var args = {"taskId":id, "taskName":name};
					$.ajax({
								//要设置为同步的，要不该方法的返回值永远为false
								async : false,
								type : "post",
								url : url,
								data : args,
								dataType : "json",
								success : function(data) {
									//表示验证通过
									if (data == 1) {
										result = true;
									}
									//表示验证没通过
									else if (data == 0) {
										alert("存在重名课题！");
									}
									//表示服务器发生错误
									else {
										alert("未知错误");
									}
								}
							})
						}
						//把节点后面的所有font兄弟节点删除
						$("input[name='maxCapacity']").nextAll("font").remove();
						var maxCapacity = $("input[name='maxCapacity']").val();
						var capacity = $("input[name='capacity']").val();
						capacity = $.trim(capacity);
						maxCapacity = parseInt(maxCapacity);
						capacity = parseInt(capacity);
						if(capacity > maxCapacity)
						{
							$("input[name='maxCapacity']").after("<font color='red'>不能超过最大剩余容量！</font>");
							result = false;
						}
						return result;
					}
				</script>

  </head>
  
  <body>
  	<s:form action="TaskAction_saveOrUpdateTask" method="post" enctype="multipart/form-data">
  		<s:hidden name="id"></s:hidden>
    	课题名称：<s:textfield name="name"></s:textfield>
    	<br>
    	课题要求：<s:textarea name="demand" cols="25" rows="4"></s:textarea><s:fielderror name="demand"></s:fielderror>
    	<br>
    	课题类型：<s:select list="#{0:'工程设计',1:'理论研究',2:'实验研究',3:'计算机软件研制',4:'综合',5:'经管文',6:'法学',7:'艺术'}" 
    				name="type"></s:select>
    	<br>
    	课题来源：<s:select list="#{0:'国家级科研项目',1:'部级科研项目',2:'省级科研项目',3:'厅级科研项目',4:'市级科研项目',5:'校级科研项目',6:'企业项目',7:'生产实际',8:'实际应用',9:'自选'}"
    				name="source"></s:select>
    	<br>
    	面向专业：<s:select list="majors" listKey="id" listValue="majorName" name="major.id"></s:select>
    	<br>
    	课题容量：<s:textfield name="capacity"></s:textfield>
    	<s:set value="getRestCapacity(id)" var="maxCapacity"></s:set>
    	提示：最大剩余容量：<s:property value="%{#maxCapacity}" />
    	<s:hidden name="maxCapacity" value="%{#maxCapacity}"></s:hidden>
    	<br>
    	上传教师拟题评审表：<s:file name="file"></s:file>
    	<s:hidden name="path"></s:hidden>
    	<s:set value="path" var="path"></s:set>
    	<s:if test="#path != null && #path !=''">
    		<font color="red">你已经上传过评审表，再次上传可以覆盖</font>
    	</s:if>
    	<s:fielderror name="file"></s:fielderror>
    	<br>
    	下载附件：<s:a action="DownloadAction?number=4">广东工业大学本科生毕业设计（论文）教师拟题审批表.docx</s:a><br>
    	<s:submit value="确定" onclick="return validateName()"></s:submit>
  	</s:form>
  </body>
</html>
