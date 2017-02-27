package com.atguigu.cpes.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.atguigu.cpes.bean.Page;

@Controller
@RequestMapping("/process")
public class ProcessController extends BaseController {

	@Autowired
	private RepositoryService repositoryService;
	
	@RequestMapping("/showimg")
	public String showimg( String id, Model model ) {
		model.addAttribute("pdid", id);
		return "process/showimg";
	}
	
	@RequestMapping("/list")
	public String list() {
		return "process/list";
	}
	
	@RequestMapping("/queryProcDefImg")
	public void queryProcDefImg( String pdid, HttpServletResponse resp ) throws Exception {
		
		// 主键查询流程定义对象
		ProcessDefinition pd = 
			repositoryService
				.createProcessDefinitionQuery()
				.processDefinitionId(pdid)
				.singleResult();
		
		// 读取流程定义的图片
		InputStream in = repositoryService.getResourceAsStream(pd.getDeploymentId(), pd.getDiagramResourceName());
		OutputStream out = resp.getOutputStream();
		
		int i = -1;
		while ( (i = in.read()) != -1 ) {
			out.write(i);
		}
	}
	
	@ResponseBody
	@RequestMapping("/uploadProcDefFile")
	public Object uploadProcDefFile( HttpServletRequest req ) {
		start();
		
		try {
			// 文件上传的时候，springMVC框架会对基本的请求对象（request）进行封装
			MultipartHttpServletRequest request = (MultipartHttpServletRequest)req;
			
			// 可以从请求对象中获取上传的文件
			MultipartFile file = request.getFile("procDefFile");
			
			// getName方法获取的名值对中的名称,而不是文件数据
			//System.out.println("name = " + file.getName());
			// getOriginalFilename方法获取文件真实名称
			//System.out.println("name1 = " + file.getOriginalFilename());
			
			// 部署流程定义文件
			repositoryService
			    .createDeployment()
			    .addInputStream(file.getOriginalFilename(), file.getInputStream()).deploy();
			
			success(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			success(false);
		}
		
		return end();
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Object delete( String deploymentId ) {
		start();
		
		try {
			// 级联删除流程定义
			repositoryService.deleteDeployment(deploymentId, true);
			success(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			success(false);
		}
		
		return end();
	}
	
	@ResponseBody
	@RequestMapping("/queryPageProcesss")
	public Object queryPageProcesss( Integer pageno, Integer pagesize ) {
		start();
		success(true);
		
		ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();
		
		List<ProcessDefinition> pds = query.listPage((pageno-1)*pagesize, pagesize);
		int cnt = (int)query.count();
		
		Page<Map<String, Object>> dataPage = new Page<Map<String, Object>>();
		
		List<Map<String, Object>> dataMapList = new ArrayList<Map<String, Object>>();
		for ( ProcessDefinition pd : pds ) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", pd.getId());
			map.put("key", pd.getKey());
			map.put("name", pd.getName());
			map.put("version", pd.getVersion());
			map.put("deployid", pd.getDeploymentId());
			dataMapList.add(map);
		}
		
		dataPage.setDatas(dataMapList);
		dataPage.setTotalsize(cnt);
		dataPage.setPageno(pageno);
		dataPage.setPagesize(pagesize);

		param("datapage", dataPage);
		return end();
	}
}
