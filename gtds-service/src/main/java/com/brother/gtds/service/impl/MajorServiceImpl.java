package com.brother.gtds.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.brother.gtds.dao.BaseDao;
import com.brother.gtds.model.Major;
import com.brother.gtds.service.MajorService;

@Service("majorService")
public class MajorServiceImpl extends BaseServiceImpl<Major> implements
		MajorService {

	@Resource(name="majorDao")
	public void setDao(BaseDao<Major> dao)
	{
		super.setDao(dao);
	}

}
