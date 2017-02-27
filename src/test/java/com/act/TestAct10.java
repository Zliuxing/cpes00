package com.act;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAct10 {

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
		
		// 网关 - 多个分支同时只能执行一个分支， 称之为排他网关
		//       其他的框架也称之为 ：  决策
		
		// 使用流程变量
		Map<String, Object> varMap = new HashMap<String, Object>();
		varMap.put("days", 3);
		
		
		RuntimeService runtimeService = pe.getRuntimeService();
		//ProcessInstance pi = runtimeService.startProcessInstanceById(pd.getId());
		ProcessInstance pi =
				runtimeService.startProcessInstanceById(pd.getId(), varMap);
		System.out.println( "启动流程实例 : " + pi.getId() );
		List<Task> tasks = pe.getTaskService().createTaskQuery().taskAssignee("zhangsan").list();
		
		for ( Task t : tasks ) {
			System.out.println( "zhangsan完成的任务 = " + t.getName() );
			pe.getTaskService().complete(t.getId());
		}
		
		HistoricProcessInstance hpi =
			pe.getHistoryService()
			.createHistoricProcessInstanceQuery()
			.processInstanceId(pi.getId())
			.finished()
			.singleResult();
		
		System.out.println( "流程是否结束：" +(hpi != null) );
	}

}
