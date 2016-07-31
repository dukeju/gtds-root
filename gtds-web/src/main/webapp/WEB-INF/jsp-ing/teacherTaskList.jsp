<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>教师出题情况</title>
    <script type="text/javascript" src='<s:url value="/script/jquery-1.12.1.js"/>'></script>
    <script type="text/javascript">
    	$(function(){
    		$("#notice").click(function(){
    			var url = "NoticeAction_dissatisfyNumber"; 
    			var tId = $(this).next().val();
    			var args = {"tId":tId};
    			$.ajax({
    				//要设置为同步的，要不该方法的返回值永远为false
					async : false,
					type : "post",
					url : url,
					data : args,
					dataType : "json",
					success : function(data){
						if(data == 1)
						{
							alert("通知成功！");
						}
						else
						{
							alert("服务器错误，通知失败!");
						}
					}
    			})
    		});
    		
    		$("#noticeAll").click(function(){
    			var tIds = "";
    			$(".h1").each(function(){
    				tIds += $(this).val() + ",";
    				alert(tIds);
    			})
				var url = "NoticeAction_allDissatisfyNumber";    				
				var args = {"tIds":tIds};
				$.ajax({
					async : false, type : "post", url : url, data : args, dataTyp : "json",
					success : function(data){
						if(data == 1)
							alert("通知成功！");
						else
							alert("服务器错误，通知失败！");
					}
				})
    		});
    	});
    </script>

  </head>
  
  <body>
  	<a href="#" id="noticeAll">通知全部未达到要求的老师</a>
    <table width="100%" border="1" cellspacing="0">
    	<tr>
    		<td>工号</td>
    		<td>名字</td>
    		<td>指导类型</td>
    		<td>最小指导人数</td>
    		<td>最大指导人数</td>
    		<td>课题总容量</td>
    		<td>是否达到要求</td>
    	</tr>
    	<s:set value="#{-1:'全部', 0:'专科', 1:'本科', 2:'研究生', 3:'硕士', 4:'博士' }" var="types"></s:set>
    	<s:iterator value="%{teachers}">
    		<tr>
    			<td><s:property value="id" /></td>
    			<td><s:property value="name" /></td>
    			<td><s:property value="%{#types[type]}" /></td>
    			<td><s:property value="minCount" /></td>
    			<td><s:property value="maxCount" /></td>
    			<s:set value="getTotalCount(id)" var="totalCount"></s:set>
    			<td><s:property value="#totalCount" /></td>
    			<s:if test="#totalCount >= minCount">
    				<td>达到要求</td>
    			</s:if>
    			<s:else>
    				<td>
    					<font color="red">未达到要求，</font>
    					<a href="#" id="notice">通知他（她）</a>
    					<s:hidden cssClass="h1" value="%{id}"></s:hidden>
    				</td>
    			</s:else>
    		</tr>
    	</s:iterator>
    </table>
  </body>
</html>
