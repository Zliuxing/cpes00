package com.atguigu.cpes.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.cpes.bean.Datas;
import com.atguigu.cpes.bean.Page;
import com.atguigu.cpes.bean.Role;
import com.atguigu.cpes.dao.RoleDao;
import com.atguigu.cpes.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;

	public Page<Role> queryPage(Integer pageno, Integer pagesize) {
		// 获取分页对象
		Page<Role> page = new Page<Role>();
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("start", (pageno-1)*pagesize);
		paramMap.put("pagesize", pagesize);
		// 查询用户数据
		List<Role> data = roleDao.queryRole4Page(paramMap);
		
		// 查询用户数量
		int count = roleDao.queryRoleCount4Page(paramMap);
		
		page.setDatas(data);
		page.setTotalsize(count);
		
		return page;
	}

	public void insertRole(Role role) {
		roleDao.insertRole(role);
	}

	public Role queryRoleById(Integer id) {
		return roleDao.queryRoleById(id);
	}

	public void updateRole(Role role) {
		roleDao.updateRole(role);
	}

	public void deleteRoleById(Integer id) {
		roleDao.deleteRoleById(id);
	}

	public void deleteRoles(Datas ds) {
		roleDao.deleteRoles(ds);
	}

	public List<Role> queryAll() {
		return roleDao.queryAll();
	}

	public void doassign(Integer roleid, Datas ds) {
		roleDao.deletePermissionByRoleId(roleid);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("roleid", roleid);
		for ( Integer permissionid : ds.getIds() ) {
			paramMap.put("permissionid", permissionid);
			roleDao.insertRolePermission(paramMap);
		}
	}
}
