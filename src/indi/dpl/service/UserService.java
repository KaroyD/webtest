package indi.dpl.service;

import indi.dpl.dao.UserDao;
import indi.dpl.domain.User;

/**
*
*Description:提供注册和登陆功能，与servlet和Dao沟通
*@author Karoy
*Start time:2018年9月7日
*Version:v0.1 2018年9月7日
*
*/

public class UserService {
	private UserDao userDao = new UserDao();
	
	
	//注册
	public void regist(User user) throws UserException {
		//先查询user是否已经存在
		User _user = userDao.findByUsername(user.getUsername());
		//若_user为空，表明没有注册,则在数据库中新增一个条目，若不为空则抛出自定义异常
		if(_user != null ) throw new UserException("用户名已存在!");
		else {
			userDao.add(user);
		}
	}
	
	//登陆
	public User login(User user) throws UserException{
		User _user=userDao.findByUsername(user.getUsername());
		
		//判断数据库中是否有该用户，并且检验密码，有错误抛出自定义异常	
		if(_user==null) throw new UserException("用户名不存在！");
		else if(!_user.getPassword().equals(user.getPassword())) throw new UserException("密码错误");
		return _user;
	}
}
