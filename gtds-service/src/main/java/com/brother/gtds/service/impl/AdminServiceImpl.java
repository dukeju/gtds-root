package com.brother.gtds.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.brother.gtds.dao.BaseDao;
import com.brother.gtds.model.Admin;
import com.brother.gtds.service.AdminService;

@Service("adminService")
public class AdminServiceImpl extends BaseServiceImpl<Admin> implements
		AdminService {

	@Resource(name="adminDao")
	@Override
	public void setDao(BaseDao<Admin> dao) {
		super.setDao(dao);
	}
	
}
