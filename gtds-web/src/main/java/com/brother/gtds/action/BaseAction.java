package com.brother.gtds.action;

import com.brother.gtds.utils.ReflectionUtils;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public abstract class BaseAction<T> extends ActionSupport implements Preparable, ModelDriven<T> {

	private static final long serialVersionUID = 354037217658449436L;
	
	protected T model;
	
	public BaseAction()
	{
		Class<T> clazz = ReflectionUtils.getSuperGenericType(this.getClass());
		try {
			model = clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public T getModel() {
		// TODO Auto-generated method stub
		return model;
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
