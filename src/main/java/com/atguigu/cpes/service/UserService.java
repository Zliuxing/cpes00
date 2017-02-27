package com.atguigu.cpes.service;

import java.util.List;

import com.atguigu.cpes.bean.Datas;
import com.atguigu.cpes.bean.Page;
import com.atguigu.cpes.bean.Permission;
import com.atguigu.cpes.bean.User;

/**
 * 用户服务对象
 * @author 18801
 *
 */
public interface UserService {

	User queryUser4Login(User user);

	List<User> queryUser4Page(Integer pageno, Integer pagesize);

	Page<User> queryPage(Integer pageno, Integer pagesize, String qname);

	void insertUser(User user);

	User queryUserById(Integer id);

	int updateUser(User user);

	int deleteUserById(Integer id);

	void deleteUsers(Integer[] id);

	void deleteBatUsers(Datas ds);

	List<Integer> queryRoleIdsByUserid(Integer id);

	void assignRoles(Integer userid, Datas ds);

	void cancleAssignRoles(Integer userid, Datas ds);

	List<Permission> queryPermissionsByUser(User loginUser);

}
