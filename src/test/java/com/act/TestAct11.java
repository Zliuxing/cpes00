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

public class TestAct11 {

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
		
		// 网关 - 多个分支同时都执行，并且，所有的分支执行完毕后，流程才会继续执行，
		//      如果其中有的分支没有执行完毕，流程需要等待，称之为并行网关
		//      其他框架中称之为 ：会签

		RuntimeService runtimeService = pe.getRuntimeService();
		
		ProcessInstance pi = runtimeService.startProcessInstanceById(pd.getId());
		
		System.out.println( "启动流程实例 : " + pi.getId() );
		List<Task> tasks = pe.getTaskService().createTaskQuery().taskAssignee("zhangsan").list();
		List<Task> tasks1 = pe.getTaskService().createTaskQuery().taskAssignee("lisi").list();
		
		System.out.println( "zhangsan的任务数量 = " + tasks.size() );
		System.out.println( "lisi的任务数量 = " + tasks1.size() );
		
		for ( Task t : tasks ) {
			pe.getTaskService().complete(t.getId());
		}
		System.out.println( "zhangsan完成了任务" );
		
		HistoricProcessInstance hpi =
			pe.getHistoryService()
			.createHistoricProcessInstanceQuery()
			.processInstanceId(pi.getId())
			.finished()
			.singleResult();
		
		System.out.println( "流程是否结束：" +(hpi != null) );
		
		for ( Task t : tasks1 ) {
			pe.getTaskService().complete(t.getId());
		}
		System.out.println( "lisi完成了任务" );
		
		hpi =
			pe.getHistoryService()
			.createHistoricProcessInstanceQuery()
			.processInstanceId(pi.getId())
			.finished()
			.singleResult();
		
		System.out.println( "流程是否结束：" +(hpi != null) );
		
	}

}
