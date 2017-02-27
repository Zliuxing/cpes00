package com.atguigu.cpes.dao;

import java.util.List;

import com.atguigu.cpes.bean.Permission;

public interface PermissionDao {

	List<Permission> queryAll();

	void insertPermission(Permission permission);

	Permission queryById(Integer id);

	void updatePermission(Permission permission);

	void deletePermission(Integer id);

	List<Integer> queryPermissionsByRoleId(Integer roleid);

}
