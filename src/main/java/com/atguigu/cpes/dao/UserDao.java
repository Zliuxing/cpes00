package com.atguigu.cpes.dao;

import java.util.List;
import java.util.Map;

import com.atguigu.cpes.bean.Datas;
import com.atguigu.cpes.bean.Permission;
import com.atguigu.cpes.bean.User;

/**
 * 用户数据访问对象
 * @author 18801
 *
 */
public interface UserDao {

	User queryUser4Login(User user);

	List<User> queryUser4Page(Map<String, Object> paramMap);

	int queryUserCount4Page(Map<String, Object> paramMap);

	void insertUser(User user);

	User queryUserById(Integer id);

	int updateUser(User user);

	int deleteUserById(Integer id);

	void deleteUsers(Map<String, Object> paramMap);

	void deleteBatUsers(Datas ds);

	List<Integer> queryRoleIdsByUserid(Integer id);

	void insertUserRole(Map<String, Object> paramMap);

	void deleteUserRoles(Map<String, Object> paramMap);

	List<Permission> queryPermissionsByUser(User loginUser);

}
