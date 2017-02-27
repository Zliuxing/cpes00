package com.act;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAct09 {

	public static void main(String[] args) {
		
		// 获取Spring的环境
		ApplicationContext application =
			new ClassPathXmlApplicationContext("spring/spring-*.xml");
		
		// 获取流程框架的核心对象
		ProcessEngine pe = (ProcessEngine)application.getBean("processEngine");
		 
		RepositoryService repositoryService = pe.getRepositoryService();
		// 查询流程定义
		ProcessDefinition pd =
			repositoryService
			    .createProcessDefinitionQuery()
			    .processDefinitionKey("myProcess")
			    .latestVersion()
			    .singleResult();
		
		// 使用流程变量
		Map<String, Object> varMap = new HashMap<String, Object>();
		varMap.put("tl", "wangwu");
		varMap.put("pm", "zhaoliu");
		
		
		RuntimeService runtimeService = pe.getRuntimeService();
		//ProcessInstance pi = runtimeService.startProcessInstanceById(pd.getId());
		ProcessInstance pi =
				runtimeService.startProcessInstanceById(pd.getId(), varMap);
		
		TaskService taskService = pe.getTaskService();
		long cnt = taskService.createTaskQuery().taskAssignee("wangwu").count();

		System.out.println( "wangwu的任务数量 = " + cnt );
	}

}
