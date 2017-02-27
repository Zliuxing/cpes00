package com.act;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAct03 {

	public static void main(String[] args) {
		
		// 获取Spring的环境
		ApplicationContext application =
			new ClassPathXmlApplicationContext("spring/spring-*.xml");
		
		// 获取流程框架的核心对象
		ProcessEngine pe = (ProcessEngine)application.getBean("processEngine");
		
		// 将流程定义图形加载到数据库流程框架的表中，我们将这个过程称之为部署
		RepositoryService repositoryService = pe.getRepositoryService();
		
		// 查询流程定义数据
		ProcessDefinitionQuery query =
			repositoryService.createProcessDefinitionQuery();
		
		List<ProcessDefinition> pds = query.list();
		//query.processDefinitionKey("myProcess").latestVersion().si;
		//query.count();
		//query.listPage(start, limit);
		//query.orderByProcessDefinitionVersion().desc();
		//query.latestVersion().singleResult();
		
		for ( ProcessDefinition pd : pds ) {
			System.out.println( pd.getVersion() );
		}
		
	}

}
