<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %> 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
      
    <title>登陆</title>
    
    <script type="text/javascript" src='<s:url value="/script/jquery-1.12.1.js"/>'></script>
    <script type="text/javascript">
    	//换验证码
    	function changeIdentity()
    	{
    		document.getElementById("identityingCode").src = document.getElementById("identityingCode").src + 
    			"?nocache" + new Date().getTime();
    		//$("#identityingCode").attr("src", $("#identityingCode").attr("src") + new Date().getTime());
    	}
    	
    	
    	//验证验证码
    	function validateIdentity()
    	{
    		var identity = $('#identity').val();
    		identity = $.trim(identity);
    		var result = false;
    		if(identity == "")
    		{
    			alert("验证码不能为空");
    		}
    		else
    		{
	    		var url = "LoginAction_validateIdentity"; 
	    		var args = {"identity":identity, "time":new Date()};
		/*此方法不行	$.post(url, args, function(data){
					//表示验证通过
					if(data == 1)
					{
						alert("^^^");
						result = true;
					}
					//表示验证没通过
					else if(data == 0)
					{
						alert("验证码错误");
						//return false;
					}
					//表示服务器发生错误
					else
					{
						alert("服务器发生错误");
						//return false;
					}
				});  */  		
	    		 $.ajax({
	    		 //要设置为同步的，要不该方法的返回值永远为false
	    		 async:false,
	             type: "post",
	             url: url,
	             data: args,
				 dataType: "json",
	             success: function(data){
	             	//表示验证通过
						if(data == 1)
						{
							result = true;
						}
						//表示验证没通过
						else if(data == 0)
						{
							alert("验证码错误");
						}
						//表示服务器发生错误
						else
						{
							alert("服务器发生错误");
						}
	             }}) 		
             }
    		return result;
    	}
    </script>

  </head>
  
  <body>
    <s:form action="LoginAction_doLogin" namespace="/" method="post"> 
    	<table>
    		<tr>
    			<td>账号：</td>
    			<td><s:textfield name="id" value="2006020002"></s:textfield></td>
    		</tr>
    		<tr>
    			<td>密码：</td>
    			<td><s:password name="password"></s:password></td>
    		</tr>
    		<tr>
    			<td></td>
    			<td><s:actionerror/></td>
    		</tr>
    		<!-- 
    		<tr>
    			<td>验证码：</td>
    			<td><s:textfield name="identity" id="identity"></s:textfield></td>  
    		</tr>
    		<tr>
    			<td></td>
    			<td>
    				<img src="<s:url action='LoginAction_showIdentity'/>" id="identityingCode" />
    				<a href="#" onclick="changeIdentity()">看不清，换一个</a>
    			</td>
    		</tr>
    		-->
    		<tr>
    			<td>身份</td>
    			<td>
    				<s:radio list="#{0:'学生',1:'教师',2:'管理员'}" name="status"></s:radio>
    			</td>
    		</tr>
    		<tr>
    			<td></td>
    			<td><s:submit value="登陆"></s:submit></td>  
    		</tr>
    	</table>  
    </s:form>  
  </body>
</html>
