package test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dev.dao.TuserMapper;
import com.dev.domain.Tuser;
import com.dev.service.TuserService;

import test.base.baseTest;



public class test extends baseTest {

	private final static Logger logger =  LoggerFactory.getLogger(test.class);
	//private static redisSingleNodeUtil redis=new redisSingleNodeUtil();
	
	@Autowired
	private TuserService userService;
	
	@Autowired
	private TuserMapper userDao;
	
	@Test
	public void insert(){
		Tuser u=new Tuser();
		u.setId(1);
		u.setName("123");
		userService.insert(u);
	}
	
	@Test
	public void deleByid(){
	 	System.out.println(userService.deleteByPrimaryKey(1));
	}
	
	@Test
	public void insertA(){
		Tuser u=new Tuser();
		u.setId(1);
		u.setName("123222");
		userDao.insert(u);
	}
}
