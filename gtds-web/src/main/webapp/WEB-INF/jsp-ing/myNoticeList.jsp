<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>我的消息</title>

  </head>
  
  <body>
    <div>
    	<fieldset>
    		<legend>未读消息</legend>
    		<table width="100%" border="1" cellspacing="0">
    		<tr>
    			<td></td>
    			<td>发送者</td>
    			<td>标题</td>
    			<td>日期</td>
    			<td>查看</td>
    		</tr>
    		<s:iterator value="%{#request.unread}" status="st">
    			<tr>
    				<td><s:property value="#st.index + 1" /></td>
    				<td>
						<s:property value="%{adminSender == null? stuSender == null ? teaSender.name : stuSender.name : adminSender.name}" />
					</td>
    				<td><s:property value="title" /></td>
    				<td><s:date format="yyyy-MM-dd HH:mm:ss" name="date"></s:date></td>
    				<td><s:a action="NoticeAction_noticeInfo?nId=%{id}">查看</s:a></td>
    			</tr>
    		</s:iterator>
    	</table>
    	</fieldset>
    </div>
    <div>
    	<fieldset>
    		<legend>已读消息</legend>
    		<table width="100%" border="1" cellspacing="0">
    		<tr>
    			<td></td>
    			<td>发送者</td>
    			<td>标题</td>
    			<td>日期</td>
    			<td>查看</td>
    		</tr>
    		<s:iterator value="%{#request.read}" status="st">
    			<tr>
    				<td><s:property value="#st.index + 1" /></td>
    				<td>
						<s:property value="%{adminSender == null? stuSender == null ? teaSender.name : stuSender.name : adminSender.name}" />
					</td>
    				<td><s:property value="title" /></td>
    				<td><s:date format="yyyy-MM-dd HH:mm:ss" name="date"></s:date></td>
    				<td><s:a action="NoticeAction_noticeInfo?nId=%{id}">查看</s:a></td>
    			</tr>
    		</s:iterator>
    	</table>
    	</fieldset>
    </div>
  </body>
</html>
