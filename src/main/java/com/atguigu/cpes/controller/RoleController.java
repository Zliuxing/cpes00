package com.atguigu.cpes.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.cpes.bean.Datas;
import com.atguigu.cpes.bean.Page;
import com.atguigu.cpes.bean.Role;
import com.atguigu.cpes.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

	@Autowired
	private RoleService roleService;
	
	@RequestMapping("/list")
	public String list() {
		return "role/list";
	}
	
	@RequestMapping("/add")
	public String add() {
		return "role/add";
	}
	
	@RequestMapping("/assign")
	public String assign() {
		return "role/assign";
	}
	
	@RequestMapping("/edit/{id}")
	public String edit( @PathVariable("id")Integer id, Model model ) {
		
		Role role = roleService.queryRoleById(id);
		model.addAttribute("role", role);
		
		return "role/edit";
	}
	
	@ResponseBody
	@RequestMapping("/insert")
	public Object insert( Role role ) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			roleService.insertRole(role);
			resultMap.put("success", true);
		} catch ( Exception e ) {
			e.printStackTrace();
			resultMap.put("success", false);
		}
		
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public Object update( Role role ) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			roleService.updateRole(role);
			resultMap.put("success", true);
		} catch ( Exception e ) {
			e.printStackTrace();
			resultMap.put("success", false);
		}
		
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Object delete( Integer id ) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			roleService.deleteRoleById(id);
			resultMap.put("success", true);
		} catch ( Exception e ) {
			e.printStackTrace();
			resultMap.put("success", false);
		}
		
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping("/deletes")
	public Object deletes( Datas ds ) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			roleService.deleteRoles(ds);
			resultMap.put("success", true);
		} catch ( Exception e ) {
			e.printStackTrace();
			resultMap.put("success", false);
		}
		
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping("/doassign")
	public Object doassign( Integer roleid, Datas ds ) {
		start();
		
		try {
			roleService.doassign(roleid, ds);
			success(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			success(false);
		}
		
		return end();
	}
	
	@ResponseBody
	@RequestMapping("/queryPageRoles")
	public Object queryPageRoles( Integer pageno, Integer pagesize ) {
		Map<String, Object> resultMap = new HashMap<String, Object>();// []
		resultMap.put("success", true);
		
		Page<Role> dataPage = roleService.queryPage(pageno, pagesize);
		dataPage.setPageno(pageno);
		dataPage.setPagesize(pagesize);
		resultMap.put("datapage", dataPage);
		
		return resultMap;
	}
}
