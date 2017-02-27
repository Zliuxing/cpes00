package com.atguigu.cpes.service;

import java.util.List;

import com.atguigu.cpes.bean.Permission;

public interface PermissionService {

	List<Permission> queryAll();

	void insertPermission(Permission permission);

	Permission queryById(Integer id);

	void updatePermission(Permission permission);

	void deletePermission(Integer id);

	List<Integer> queryPermissionsByRoleId(Integer roleid);

}
