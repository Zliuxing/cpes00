package com.atguigu.cpes.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.cpes.bean.Datas;
import com.atguigu.cpes.bean.Page;
import com.atguigu.cpes.bean.Permission;
import com.atguigu.cpes.bean.User;
import com.atguigu.cpes.dao.UserDao;
import com.atguigu.cpes.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	public User queryUser4Login(User user) {
		
		// 调用数据访问对象， 查询用户信息
		return userDao.queryUser4Login(user);
	}

	public List<User> queryUser4Page(Integer pageno, Integer pagesize) {
		// 分页查询用户数据
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("start", (pageno-1)*pagesize);
		paramMap.put("pagesize", pagesize);
		
		return userDao.queryUser4Page(paramMap);
	}

	public Page<User> queryPage(Integer pageno, Integer pagesize, String qname) {
		// 获取分页对象
		Page<User> page = new Page<User>();
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("start", (pageno-1)*pagesize);
		paramMap.put("pagesize", pagesize);
		paramMap.put("qname", qname);
		//if ( StringUtil.isNotEmpty(qname) ) {
			
		//}
		// 查询用户数据
		List<User> users = userDao.queryUser4Page(paramMap);
		
		// 查询用户数量
		int count = userDao.queryUserCount4Page(paramMap);
		
		page.setDatas(users);
		page.setTotalsize(count);
		
		// 计算数据总页码
		int totalno = 0;
		if ( count % pagesize == 0 ) {
			totalno = count / pagesize;
		} else {
			totalno = count / pagesize + 1;
		}
		page.setTotalno(totalno);
		
		return page;
	}

	public void insertUser(User user) {
		userDao.insertUser(user);
	}

	public User queryUserById(Integer id) {
		return userDao.queryUserById(id);
	}

	public int updateUser(User user) {
		return userDao.updateUser(user);
	}

	public int deleteUserById(Integer id) {
		return userDao.deleteUserById(id);
	}

	public void deleteUsers(Integer[] id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ids", id);
		userDao.deleteUsers(paramMap);
	}

	public void deleteBatUsers(Datas ds) {
		userDao.deleteBatUsers(ds);
	}

	public List<Integer> queryRoleIdsByUserid(Integer id) {
		return userDao.queryRoleIdsByUserid(id);
	}

	public void assignRoles(Integer userid, Datas ds) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userid", userid);
		for ( Integer roleid : ds.getIds() ) {
			paramMap.put("roleid", roleid);
			userDao.insertUserRole(paramMap);
		}
	}

	public void cancleAssignRoles(Integer userid, Datas ds) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("userid", userid);
		paramMap.put("roleids", ds.getIds());
		userDao.deleteUserRoles(paramMap);
	}

	public List<Permission> queryPermissionsByUser(User loginUser) {
		return userDao.queryPermissionsByUser(loginUser);
	}
}
