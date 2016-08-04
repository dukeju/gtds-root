package com.brother.gtds.action.interceptors;

import com.brother.gtds.action.BaseAction;
import com.brother.gtds.action.LoginAction;
import com.brother.gtds.action.aware.UserAware;
import com.brother.gtds.model.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

//登陆拦截器，检验是否登陆了
public class LoginInterceptor implements Interceptor {

	private static final long serialVersionUID = 777547288242002617L;

	@Override
	public void destroy() {
	}

	@Override
	public void init() {
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		BaseAction action = (BaseAction) arg0.getAction();
		if(action instanceof LoginAction)
		{
			return arg0.invoke();
		}
		else
		{
			User user = (User) arg0.getInvocationContext().getSession().get("user");
			//有登陆
			if(user != null)
			{
				//注入user到action
				if(action instanceof UserAware)
				{
					((UserAware) action).setUser(user);
				}
				return arg0.invoke();
			}
		}
		//没有登陆
		return "loginPage";
	}

}
