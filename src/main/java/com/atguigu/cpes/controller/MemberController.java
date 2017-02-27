package com.atguigu.cpes.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.atguigu.cpes.bean.Cert;
import com.atguigu.cpes.bean.Member;
import com.atguigu.cpes.bean.Ticket;
import com.atguigu.cpes.service.CertService;
import com.atguigu.cpes.service.MemberService;
import com.atguigu.cpes.util.Const;
import com.atguigu.cpes.util.MD5Util;
import com.atguigu.cpes.util.NumberUtil;

/**
 * 会员控制器
 * @author 18801
 *
 */
@Controller
@RequestMapping("/member")
public class MemberController extends BaseController {

	@Autowired
	private MemberService memberService;
	@Autowired
	private CertService certService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private TaskService taskService;
	
	@RequestMapping("/cert")
	public String cert() {
		return "member/cert";
	}
	
	@RequestMapping("/apply-1")
	public String apply1( String acctype, HttpSession session ) {
		
		Member member = (Member)session.getAttribute(Const.SESSION_USER);
		member.setAcctype(acctype);
		memberService.updateAcctype(member);
		
		return "member/apply-1";
	}
	
	@RequestMapping("/apply-2")
	public String apply2( HttpSession session, Model model ) {
		
		// 读取会员账户类型所对应的资质文件
		Member member = (Member)session.getAttribute(Const.SESSION_USER);
		List<Cert> certs = certService.queryCertByAcctype(member.getAcctype());
		model.addAttribute("certs", certs);
		return "member/apply-2";
	}
	
	@RequestMapping("/apply-3")
	public String apply3( HttpSession session, Model model ) {
		
		return "member/apply-3";
	}
	
	@RequestMapping("/apply-4")
	public String apply4( HttpSession session, Model model ) {
		return "member/apply-4";
	}
	
	@ResponseBody
	@RequestMapping("/doapply1")
	public Object doapply1( String realname, String cardno, HttpSession session ) {
		start();
		
		try {
			
			Member member = (Member)session.getAttribute(Const.SESSION_USER);
			member.setRealname(realname);
			member.setCardno(cardno);
			memberService.updateMemberInfo(member);
			success(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			success(false);
		}
		
		return end();
	}
	
	@ResponseBody
	@RequestMapping("/doapply2")
	public Object doapply2( HttpServletRequest req, HttpSession session ) {
		start();
		
		try {
			
			String realPath = session.getServletContext().getRealPath("imgs");
			
			Member member = (Member)session.getAttribute(Const.SESSION_USER);
			
			MultipartHttpServletRequest request =
				(MultipartHttpServletRequest)req;
			Iterator<String> iters = request.getFileNames();
			while ( iters.hasNext() ) {
				String filename = iters.next();
				String certid = filename.split("-")[1];
				
				MultipartFile file = request.getFile(filename);
				String name = file.getOriginalFilename();
				name = name.substring(name.lastIndexOf("."));
				String path = UUID.randomUUID().toString();
				name = path + name;
				InputStream in = file.getInputStream();
				FileOutputStream out = new FileOutputStream(new File(realPath + "/" + name));
				
				int i = -1;
				while ( (i = in.read()) != -1 ) {
					out.write(i);
				}
				
				out.close();
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("memberid", member.getId());
				paramMap.put("certid", Integer.parseInt(certid));
				paramMap.put("path", name);
				memberService.insertMemberCert(paramMap);
			}
			
			success(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			success(false);
		}
		
		return end();
	}
	
	@ResponseBody
	@RequestMapping("/doapply3")
	public Object doapply3( String email, HttpSession session ) {
		start();
		
		try {
			Member member = (Member)session.getAttribute(Const.SESSION_USER);
			// 查询实名认证流程定义
			ProcessDefinition pd = 
				repositoryService
			    .createProcessDefinitionQuery()
			    .processDefinitionKey("cpes")
			    .latestVersion()
			    .singleResult();
			
			// 启动流程, 自动发送邮件
			Map<String, Object> varmap = new HashMap<String, Object>();
			varmap.put("memberemail", email);
			String certno = NumberUtil.getNumber(4);
			varmap.put("mailcontent", "会员"+member.getUsername()+"，您好，您的验证码为：<b style='color:red'>"+certno+"</b>, 请尽快认证..");
			varmap.put("memberid", "" + member.getId());
			
			ProcessInstance pi = runtimeService.startProcessInstanceById(pd.getId(), varmap);
			
			// 创建审批流程单，将会员和流程关联在一起
			Ticket ticket = new Ticket();
			ticket.setMemberid(member.getId());
			ticket.setPiid(pi.getId());
			ticket.setStatus("0");
			ticket.setCertno(certno);
			memberService.insertTicket(ticket);
			
			member.setEmail(email);
			memberService.updateEmail(member);
			
			success(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			success(false);
		}
		
		return end();
	}
	
	@ResponseBody
	@RequestMapping("/doapply4")
	public Object doapply4( String certno, HttpSession session ) {
		start();
		
		try {
			Member member = (Member)session.getAttribute(Const.SESSION_USER);
			// 查询认证码
			Ticket ticket = memberService.queryTicketByMemberid(member.getId());
			
			// 判断认证码是否有效
			if ( certno.equals(ticket.getCertno()) ) {
				// 让审批流程继续执行
				Task t = taskService
				    .createTaskQuery()
				    .taskAssignee("" + member.getId())
				    .processInstanceId(ticket.getPiid())
				    .singleResult();
				taskService.complete(t.getId());
				
				// 更改流程单的状态
				ticket.setStatus("1");
				memberService.updateTicketStatus(ticket);
				
				success(true);
			} else {
				error("认证码不正确，请确认后输入");
				success(false);
			}
		} catch ( Exception e ) {
			e.printStackTrace();
			success(false);
		}
		
		return end();
	}
	
	@ResponseBody
	@RequestMapping("/dologin")
	public Object dologin( String rememberme, Member member, Model model, HttpSession session, HttpServletResponse resp) throws Exception {
		// Model数据存储范围为request
		Map<String, Object> resultMap = new HashMap<String, Object>();

		member.setUserpswd(MD5Util.digest(member.getUserpswd()));
		Member dbMember = memberService.queryMember4Login(member);
		if ( dbMember == null ) {
			resultMap.put("success", false);
		} else {
			session.setAttribute(Const.SESSION_USER, dbMember);
			
			// 登陆成功后，判断是否需要自动登陆
			if ( "1".equals(rememberme) ) {
				// 生成Cookie,保存当前登录的用户名和密码
				String logincode = "loginacct="+member.getLoginacct()+"&userpswd="+member.getUserpswd()+"&logintype=0";
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
