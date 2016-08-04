<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>通知</title>
 	<script type="text/javascript" src='<s:url value="/script/jquery-1.12.1.js"/>'></script>
    <script type="text/javascript">
    	$(function(){
    		$("#confirm").click(function(){
    			var html = $(this).html();
    			if(html == "确认")
    			{
	    			var id = $(this).next().val();
	    			var url = "NoticeAction_confirmNotice";
	    			var args = {"nId":id};
	    			$.ajax({
	    				async : false, type : "post", url : url, data : args, dataType : "json",
	    				success : function(data){
	    					if(data == 1)
	    					{
				    			html = "返回";
	    					}
	    					else
	    						alert("服务器错误！");
	    				}
	    			})
	    			$(this).html(html);
    			}
    			else if(html == "返回")
    			{
    				//跳转到首页
    				window.location.href = "/gtds/index";
    			}
    		})
    	})
    </script>

  </head>
  
  <body>
  	<h3><s:property value="title" /></h3>
  	<br>
  	发送人：<s:property value="%{adminSender != null? adminSender.name : teaSender.name}" />
  	<br>
  	接受者：<s:property value="#session.user.name"/><br>
  	<br>
  	<br>
  	<s:property value="message"/>
  	<br>
  	<a href="#" id="confirm">确认</a>
  	<s:hidden name="id"></s:hidden>
  </body>
</html>
