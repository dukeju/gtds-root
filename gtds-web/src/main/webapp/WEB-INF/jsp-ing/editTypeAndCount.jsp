<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>编辑指导类型和数目</title>
    <script type="text/javascript" src='<s:url value="/script/jquery-1.12.1.js"/>'></script>
	<script type="text/javascript">
		function validateCount()
		{
			var min = $("input[name='minCount']").val();
			var max = $("input[name='maxCount']").val();
			min = parseInt(min);
			max = parseInt(max);
			if(min > max)
			{
				alert("最大指导人数不能小于最小指导人数！");			
				return false;
			}
			return true;
		}
	</script>
    
  </head>
  
  <body>
    <s:form action="TeacherAction_updateTypeAndCount" method="post">
    	<s:hidden name="teacherId" value="%{id}"></s:hidden>
    	姓名：<s:property value="name"/><br>
    	指导类型：<s:select list="#{0:'专科', 1:'本科', 2:'研究生', 3:'硕士', 4:'博士' }" name="type"></s:select><br>
    	最小数目：<s:textfield name="minCount" value="%{minCount == null?6:minCount}"></s:textfield><br>
    	最大数目：<s:textfield name="maxCount" value="%{maxCount == null?6:maxCount}"></s:textfield><br>
    	<s:submit value="确定" onclick="return validateCount()"></s:submit>
    </s:form>
  </body>
</html>
