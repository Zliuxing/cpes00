package com.act;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAct04 {

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
		
		// 创建（启动）流程实例
		RuntimeService runtimeService = pe.getRuntimeService();
		ProcessInstance pi = runtimeService.startProcessInstanceById(pd.getId());
		
		// ProcessInstance[301]
		/*
		 * 启动流程实例之后，数据库表会发生变化
		 * act_ru_execution : 运行时流程执行实例表
		 * act_ru_task : 运行时任务节点表
		 * act_hi_procinst : 历史流程实例表
		 * act_hi_taskinst : 历史任务实例表
		 */
		System.out.println( pi );
	}

}
