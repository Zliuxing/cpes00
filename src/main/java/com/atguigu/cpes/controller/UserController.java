package com.atguigu.cpes.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.cpes.bean.Datas;
import com.atguigu.cpes.bean.Page;
import com.atguigu.cpes.bean.Role;
import com.atguigu.cpes.bean.User;
import com.atguigu.cpes.service.RoleService;
import com.atguigu.cpes.service.UserService;
import com.atguigu.cpes.util.Const;
import com.atguigu.cpes.util.MD5Util;

/**
 * 用户控制器
 * @author 18801
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	
	/**
	 * 跳转到用户新增页面
	 * @return
	 */
	@RequestMapping("/add")
	public String add() {
		return "user/add";
	}
	
	/**
	 * 跳转到用户修改页面
	 * @return
	 */
	@RequestMapping("/edit")
	public String edit( Integer id, Model model ) {
		
		// 根据主键查询用户信息
		User user = userService.queryUserById(id);
		model.addAttribute("user", user);
		
		return "user/edit";
	}
	
	@RequestMapping("/auth/{id}")
	public String auth( @PathVariable("id")Integer id, Model model ) {
		
		// 查询所有的角色数据
		List<Role> roles = roleService.queryAll();
		
		// 获取未分配的角色数据和已分配的角色数据
		List<Role> unassignList = new ArrayList<Role>();
		List<Role> assignList = new ArrayList<Role>();
		
		// 查询用户分配的角色数据
		List<Integer> roleids = userService.queryRoleIdsByUserid(id);
		
		for ( Role role : roles ) {
			if ( roleids.contains(role.getId()) ) {
				assignList.add(role);
			} else {
				unassignList.add(role);
			}
		}
		
		model.addAttribute("unassignList", unassignList);
		model.addAttribute("assignList", assignList);
		
		model.addAttribute("userid", id);
		
		return "user/auth";
	}
	
	/**
	 * 新增用户数据
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/insert")
	public Object insert( User user ) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			
			// 新增用户数据
			user.setUserpswd(MD5Util.digest("123456"));
			
			// 获取当前的系统时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateString = sdf.format(new Date());
			user.setCreatetime(dateString);
			
			userService.insertUser(user);
			
			resultMap.put("success", true);
		} catch ( Exception e ) {
			e.printStackTrace();
			resultMap.put("success", false);
		}
		
		return resultMap;
	}
	
	/**
	 * 修改用户数据
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/update")
	public Object update( User user ) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			int result = userService.updateUser(user);
			System.out.println( "result = " + result );
			resultMap.put("success", result==1);
		} catch ( Exception e ) {
			e.printStackTrace();
			resultMap.put("success", false);
		}
		
		return resultMap;
	}
	
	/**
	 * 分配选择的角色信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/assignRole")
	public Object assignRole( Datas ds, Integer userid ) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			userService.assignRoles(userid, ds);
			resultMap.put("success", true);
		} catch ( Exception e ) {
			e.printStackTrace();
			resultMap.put("success", false);
		}
		
		return resultMap;
	}
	
	
	/**
	 * 取消分配选择的角色信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/cancleAssignRole")
	public Object cancleAssignRole( Datas ds, Integer userid ) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			userService.cancleAssignRoles(userid, ds);
			resultMap.put("success", true);
		} catch ( Exception e ) {
			e.printStackTrace();
			resultMap.put("success", false);
		}
		
		return resultMap;
	}
	
	/**
	 * 删除选择的多条用户数据
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/doDeletes")
	public Object doDeletes(Datas ds) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			userService.deleteBatUsers(ds);
			resultMap.put("success", true);
		} catch ( Exception e ) {
			e.printStackTrace();
			resultMap.put("success", false);
		}
		
		return resultMap;
	}
	
	/**
	 * 删除选择的多条用户数据
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deletes")
	public Object deletes( Integer[] id ) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			userService.deleteUsers(id);
			resultMap.put("success", true);
		} catch ( Exception e ) {
			e.printStackTrace();
			resultMap.put("success", false);
		}
		
		return resultMap;
	}
	
	/**
	 * 删除用户数据
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public Object delete( Integer id ) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			
			int count = userService.deleteUserById(id);
			resultMap.put("success", count==1);
		} catch ( Exception e ) {
			e.printStackTrace();
			resultMap.put("success", false);
		}
		
		return resultMap;
	}
	
	/**
	 * 异步分页查询用户数据
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/queryPageUsers")
	public Object queryPageUsers( String qname, Integer pageno, @RequestParam(defaultValue="1",required=false)Integer pagesize ) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("success", true);
		
		Page<User> userPage = userService.queryPage(pageno, pagesize, qname);
		userPage.setPageno(pageno);
		userPage.setPagesize(pagesize);
		resultMap.put("userpage", userPage);
		
		return resultMap;
	}
	
	/**
	 * 跳转到用户列表页面
	 * @return
	 */
	@RequestMapping("/list")
	public String list( HttpSession session, @RequestParam(defaultValue="1",required=false)Integer pageno, @RequestParam(defaultValue="1",required=false)Integer pagesize, Model model ) {
		// 分页查询用户数据
		//List<User> users = userSevice.queryUser4Page(pageno, pagesize);
		/*
		Page<User> userPage = userService.queryPage(pageno, pagesize);
		userPage.setPageno(pageno);
		userPage.setPagesize(pagesize);
		
		// 保存用户分页数据（请求）
		model.addAttribute("userpage", userPage);
		*/
		return "user/list";
	}
	
	/**
	 * 用户登录，查询用户信息
	 * @return
	 */
//	@RequestMapping("/dologin")
//	public String dologin( User user, Model model ) {
//		
//		// 获取请求参数
//		
//		// 调用服务对象,查询用户信息
//		User dbUser = userSevice.queryUser4Login(user);
//		
//		// 对查询结果进行有效性验证
//		if ( dbUser == null ) {
//			model.addAttribute("errorMsg", "用户信息不存在，请重新登陆");
//			return "login";
//		} else {
//			return "redirect:/main.htm";
//		}
//	}
	/**
	 * 使用AJAX实现用户登录，查询用户信息
	 * 
	 * ResponseBody注解表示将当前方法的返回数据作为响应体的内容，因为在配置文件中已经对返回结果的类型进行了配置，所以方法执行后
	 *             会将返回对象自动转换为JSON格式的字符串
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/dologin")
	public Object dologin( String rememberme, User user, Model model, HttpSession session, HttpServletResponse resp) throws Exception {
		// Model数据存储范围为request
		Map<String, Object> resultMap = new HashMap<String, Object>();
		// map ==> JSON ==> { key : value }
		// List ==> JSON ==> []
		// 查询数据之前，应该将密码进行加密后和数据库进行匹配
		// 管理用户登录
//		user.setUserpswd(MD5Util.digest(user.getUserpswd()));
		User dbUser = userService.queryUser4Login(user);
		if ( dbUser == null ) {
			resultMap.put("success", false);
		} else {
			session.setAttribute(Const.SESSION_USER, dbUser);
			
			// 登陆成功后，判断是否需要自动登陆
			if ( "1".equals(rememberme) ) {
				// 生成Cookie,保存当前登录的用户名和密码
				String logincode = "loginacct="+user.getLoginacct()+"&userpswd="+user.getUserpswd()+"&logintype=1";
				Cookie c = new Cookie("logincode", logincode);
				c.setMaxAge(60*60*24*14);
				c.setPath("/");
				resp.addCookie(c);
			}
			
			resultMap.put("success", true);
		}
		
		return resultMap;
	}
	
}
