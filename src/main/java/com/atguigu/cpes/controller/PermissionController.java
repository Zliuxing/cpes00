package com.atguigu.cpes.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.cpes.bean.Menu;
import com.atguigu.cpes.bean.Permission;
import com.atguigu.cpes.service.MenuService;
import com.atguigu.cpes.service.PermissionService;

@Controller
@RequestMapping("/permission")
public class PermissionController extends BaseController {

	@Autowired
	private PermissionService permissionService;
	@Autowired
	private MenuService menuService;
	
	@RequestMapping("/add")
	public String add() {
		return "permission/add";
	}
	
	@RequestMapping("/edit")
	public String edit( Integer id, Model model ) {
		Permission permission = permissionService.queryById(id);
		model.addAttribute("permission", permission);
		return "permission/edit";
	}
	
	@ResponseBody
	@RequestMapping("/insert")
	public Object insert( Permission permission ) {
		start();
		
		try {
			permissionService.insertPermission(permission);
			success(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			success(false);
		}
		
		return end();
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public Object update( Permission permission ) {
		start();
		
		try {
			permissionService.updatePermission(permission);
			success(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			success(false);
		}
		
		return end();
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Object delete( Integer id ) {
		start();
		
		try {
			permissionService.deletePermission(id);
			success(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			success(false);
		}
		
		return end();
	}
	
	/**
	 * 查询许可构建许可树
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/loadPermissions4Role")
	public Object loadPermissions4Role( Integer roleid ) {
		
		// 获取角色已经分配过的权限数据
		List<Integer> permissionids = permissionService.queryPermissionsByRoleId(roleid);
		
		// 查询菜单数据,形成父子关系
		List<Permission> permissions = permissionService.queryAll();
		Map<Integer, Permission> permissionMap = new HashMap<Integer, Permission>();
		for ( Permission permission : permissions ) {
			if ( permissionids.contains(permission.getId()) ) {
				permission.setChecked(true);
			}
			permissionMap.put(permission.getId(), permission);
		}
		List<Permission> permissionList = new ArrayList<Permission>();
		for (Permission permission : permissions) {
			// 子菜单
			Permission childpermission = permission;
			
			if ( permission.getPid() == 0 ) {
				permissionList.add(permission);
			} else {
				// 父菜单
				Permission parentpermission = permissionMap.get(childpermission.getPid());
				// 组合父菜单和子菜单的关系
				parentpermission.getChildren().add(childpermission);
			}
		}

		return permissionList;
	}
	
	/**
	 * 查询许可构建许可树
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/loadPermissions")
	public Object loadPermissions() {
		
		// 查询菜单数据,形成父子关系
		List<Permission> permissions = permissionService.queryAll();
		Map<Integer, Permission> permissionMap = new HashMap<Integer, Permission>();
		for ( Permission permission : permissions ) {
			permissionMap.put(permission.getId(), permission);
		}
		List<Permission> permissionList = new ArrayList<Permission>();
		for (Permission permission : permissions) {
			// 子菜单
			Permission childpermission = permission;
			
			if ( permission.getPid() == 0 ) {
				permissionList.add(permission);
			} else {
				// 父菜单
				Permission parentpermission = permissionMap.get(childpermission.getPid());
				// 组合父菜单和子菜单的关系
				parentpermission.getChildren().add(childpermission);
			}
		}

		return permissionList;
	}
	
	@ResponseBody
	@RequestMapping("/loadMenus")
	public Object loadMenus() {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		// 查询菜单数据,形成父子关系
		List<Menu> menus = menuService.queryAll();
		Map<Integer, Menu> menuMap = new HashMap<Integer, Menu>();
		for ( Menu menu : menus ) {
			menuMap.put(menu.getId(), menu);
		}
		List<Menu> menuList = new ArrayList<Menu>();
		for (Menu menu : menus) {
			// 子菜单
			Menu childmenu = menu;
			
			if ( menu.getPid() == 0 ) {
				menuList.add(menu);
			} else {
				// 父菜单
				Menu parentmenu = menuMap.get(childmenu.getPid());
				// 组合父菜单和子菜单的关系
				parentmenu.getChildren().add(childmenu);
			}
		}
		resultMap.put("success", true);
		resultMap.put("menus", menuList);
		return resultMap;
	}
	
	@RequestMapping("/tree")
	public String tree() {		
		return "permission/tree";
	}
}
