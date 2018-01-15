package com.dev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.dao.TuserMapper;
import com.dev.domain.Tuser;

@Service("userService")
public class TuserService {

	@Autowired
	private TuserMapper userDao;
	
	
	public int deleteByPrimaryKey(Integer id){
		return userDao.deleteByPrimaryKey(id);
	}

	public int insert(Tuser record){
		return userDao.insert(record);
	}

	public  Tuser selectByPrimaryKey(Integer id){
		return userDao.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKey(Tuser record){
		return userDao.updateByPrimaryKey(record);
	}
}
