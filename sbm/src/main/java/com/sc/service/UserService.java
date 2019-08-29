package com.sc.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.sc.bean.Users;

public interface UserService {
	//添加用户
	public void addUsers(Users u);
	//更新用户
	public void updateUsers(Users u);
	//删除用户
	public void delUsers(Users u);
	//根据用户id查找用户
	public Users getUserById(Integer uid);
	//获取用户list
	public List<Users> selectUsers();
	
	//获取用户list分页
	public PageInfo<Users> selectUsersPage(
			Integer pageBum,Integer pageSize,Users users);
	
	//用户登录
	public Users login(String uanme,String upass);

}
