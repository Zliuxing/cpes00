package com.atguigu.cpes.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.cpes.bean.Permission;
import com.atguigu.cpes.dao.PermissionDao;
import com.atguigu.cpes.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionDao permissionDao;

	public List<Permission> queryAll() {
		return permissionDao.queryAll();
	}

	public void insertPermission(Permission permission) {
		permissionDao.insertPermission(permission);
	}

	public Permission queryById(Integer id) {
		return permissionDao.queryById(id);
	}

	public void updatePermission(Permission permission) {
		permissionDao.updatePermission(permission);
	}

	public void deletePermission(Integer id) {
		permissionDao.deletePermission(id);
	}

	public List<Integer> queryPermissionsByRoleId(Integer roleid) {
		return permissionDao.queryPermissionsByRoleId(roleid);
	}
}
