package com.atguigu.cpes.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.cpes.bean.MemberCert;
import com.atguigu.cpes.bean.Page;
import com.atguigu.cpes.bean.Ticket;
import com.atguigu.cpes.service.MemberService;

@Controller
@RequestMapping("/auth")
public class AuthController extends BaseController {

	@Autowired
	private TaskService taskService;
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/list")
	public String list() {
		return "auth/list";
	}
	
	@RequestMapping("/showcert")
	public String showcert( Integer memberid, String id, Model model ) {
		
		List<MemberCert> mcs = memberService.queryMemberCertsByMemberid(memberid);
		model.addAttribute("mcs", mcs);
		model.addAttribute("memberid", memberid);
		model.addAttribute("id", id);
		return "auth/showcert";
	}

	@ResponseBody
	@RequestMapping("/ok")
	public Object ok( Integer memberid, String taskid ) {
		start();
		
		try {
			
			// 结束流程
			taskService.complete(taskid);
			
			// 修改会员状态
			memberService.updateStatus(memberid);
			
			success(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			success(false);
		}
		
		return end();
	}
	
	@ResponseBody
	@RequestMapping("/queryPageTasks")
	public Object queryPageTasks( Integer pageno , Integer pagesize ) {
		start();
		success(true);
		
		// 查询任务列表
		List<Task> ts = taskService
		    .createTaskQuery()
		    .taskCandidateGroup("authcpes")
		    .listPage((pageno-1)*pagesize, pagesize);
		
		int count = (int)taskService
		    .createTaskQuery()
		    .taskCandidateGroup("authcpes").count();
		
		Page<Map<String, Object>> taskpage = new Page<Map<String, Object>>();
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		
		for ( Task t : ts ) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", t.getId());
			map.put("name", t.getName());
			map.put("piid", t.getProcessInstanceId());
			Ticket ticket = memberService.queryTicketByPiid(t.getProcessInstanceId());
			map.put("username", ticket.getUsername());
			map.put("memberid", ticket.getMemberid());
			mapList.add(map);
		}
		
		taskpage.setDatas(mapList);
		taskpage.setTotalsize(count);

		param("datapage", taskpage);
		return end();
	}
}
