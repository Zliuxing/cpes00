package com.act;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAct06 {

	public static void main(String[] args) {
		
		// 获取Spring的环境
		ApplicationContext application =
			new ClassPathXmlApplicationContext("spring/spring-*.xml");
		
		// 获取流程框架的核心对象
		ProcessEngine pe = (ProcessEngine)application.getBean("processEngine");
		 
		// 查询已经完成的流程实例
		HistoryService historyService = pe.getHistoryService();
		
		HistoricProcessInstanceQuery query =
			historyService.createHistoricProcessInstanceQuery();
		
		HistoricProcessInstance hpi =
				query.processInstanceId("101").finished().singleResult();
		
		System.out.println( hpi );
	}

}
