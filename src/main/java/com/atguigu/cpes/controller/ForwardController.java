package com.atguigu.cpes.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.cpes.bean.Menu;
import com.atguigu.cpes.bean.Permission;
import com.atguigu.cpes.bean.User;
import com.atguigu.cpes.service.MenuService;
import com.atguigu.cpes.service.UserService;
import com.atguigu.cpes.util.Const;

/**
 * 页面跳转控制器
 * @author 18801
 *
 */
@Controller
@RequestMapping("/")
public class ForwardController {

	@Autowired
	private MenuService menuService;
	@Autowired
	private UserService userService;
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		// 会话失效
		session.invalidate();
		
		// 跳转页面，回到登陆页面
		return "redirect:/";
	}
	
	/**
	 * 跳转到登陆页面
	 * @return
	 */
	@RequestMapping("/login")
	public String login( HttpServletRequest req, HttpSession session ) {
		
		// 判断当前是否为自动登陆，如果为自动登陆，那么需要判断用户的正确性
		// 如果用户存在，就直接跳转到主页面，如果用户不存在，就跳转到登陆页面
		// 浏览器禁用cookie,那么请求对象获取不到cookie，会发生空指针异常。
		boolean needlogin = true;
		String logintype = "";
		Cookie[] cs = req.getCookies();
		if ( cs != null ) {
			for ( Cookie c : cs ) {
				if ( "logincode".equals(c.getName()) ) {
					// 自动登陆的场合
					String code = c.getValue();
					// acct=admin&userpswd=admin&logintype=0
					String[] ss = code.split("&");
					String loginacct = ss[0].split("=")[1];
					String userpswd = ss[1].split("=")[1];
					logintype = ss[2].split("=")[1];
					User user = new User();
					user.setLoginacct(loginacct);
					user.setUserpswd(userpswd);
					User dbUser = userService.queryUser4Login(user);
					if ( dbUser == null ) {
						needlogin = true;
					} else {
						needlogin = false;
						session.setAttribute(Const.SESSION_USER, dbUser);
					}
				}
			}
		}

		if ( needlogin ) {
			return "login";
		} else {
			if ( "0".equals(logintype) ) {
				return "redirect:member.htm";
			} else {
				return "redirect:main.htm";
			}
		}
	}
	
	/**
	 * 跳转到用户主页面
	 * @return
	 */
	@RequestMapping("/member")
	public String member( Model model, HttpSession session ) {
		return "member";
	}
	
	/**
	 * 跳转到用户主页面
	 * @return
	 */
	@RequestMapping("/main")
	public String main( Model model, HttpSession session ) {
		User loginUser = (User)session.getAttribute(Const.SESSION_USER);
		List<Permission> permissons = userService.queryPermissionsByUser(loginUser);
		
		// 组合菜单数据
		Permission root = null;
		Map<Integer, Permission> permissionMap = new HashMap<Integer, Permission>();
		for ( Permission permisson : permissons ) {
			permissionMap.put(permisson.getId(), permisson);
		}
		for (Permission permisson : permissons) {
			// 子菜单
			Permission child = permisson;
			
			if ( permisson.getPid() == 0 ) {
				root = permisson;
			} else {
				// 父菜单
				Permission parent = permissionMap.get(child.getPid());
				// 组合父菜单和子菜单的关系
				parent.getChildren().add(child);
			}
		}
		//model.addAttribute("menus", menuList);
		session.setAttribute("smenus", root.getChildren());
		
		// ArrayList 浅拷贝
		return "main";
	}

	public void queryMenus( Menu pmenu ) {
		// 子菜单
		List<Menu> childMenus = menuService.queryChildMenu(pmenu);
		
		// 继续查询子菜单
		for ( Menu childMenu : childMenus ) {
			queryMenus(childMenu);
		}
		
		// 整合父子菜单的关系
		pmenu.setChildrenMenus(childMenus);
	}
}
 