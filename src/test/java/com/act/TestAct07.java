package com.act;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAct07 {

	public static void main(String[] args) {
		
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
		
		// 创建（启动）流程实例
		RuntimeService runtimeService = pe.getRuntimeService();
		ProcessInstance pi = runtimeService.startProcessInstanceById(pd.getId());
		 
		// 查询已经完成的流程实例
		HistoryService historyService = pe.getHistoryService();
		
		HistoricProcessInstanceQuery query =
			historyService.createHistoricProcessInstanceQuery();
		
		HistoricProcessInstance hpi =
				query.processInstanceId(pi.getId()).finished().singleResult();
		
		System.out.println( hpi );
	}

}
