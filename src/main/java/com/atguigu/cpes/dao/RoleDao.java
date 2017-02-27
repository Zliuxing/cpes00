package com.atguigu.cpes.dao;

import java.util.List;
import java.util.Map;

import com.atguigu.cpes.bean.Datas;
import com.atguigu.cpes.bean.Role;

public interface RoleDao {

	List<Role> queryRole4Page(Map<String, Object> paramMap);

	int queryRoleCount4Page(Map<String, Object> paramMap);

	void insertRole(Role role);

	Role queryRoleById(Integer id);

	void updateRole(Role role);

	void deleteRoleById(Integer id);

	void deleteRoles(Datas ds);

	List<Role> queryAll();

	void insertRolePermission(Map<String, Object> paramMap);

	void deletePermissionByRoleId(Integer roleid);

}
