package com.act;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAct02 {

	public static void main(String[] args) {
		
		// 获取Spring的环境
		ApplicationContext application =
			new ClassPathXmlApplicationContext("spring/spring-*.xml");
		
		// 获取流程框架的核心对象
		ProcessEngine pe = (ProcessEngine)application.getBean("processEngine");
		
		// 将流程定义图形加载到数据库流程框架的表中，我们将这个过程称之为部署
		RepositoryService repositoryService = pe.getRepositoryService();
		
		Deployment d = 
			repositoryService
		    .createDeployment()
		    .addClasspathResource("MyProcess1.bpmn")
		    .deploy();
		
		// DeploymentEntity[id=1, name=null]
		/*
		 * 部署完成时，框架表数据的变化
		 * 
		 * act_ge_bytearray（2）：二进制数据表，存储了流程定义图形的文件和图片信息
		 * act_re_deployment（1）：部署信息表， 存储了部署的相关信息（部署时间）
		 * act_re_procdef（1）：流程定义数据表，存储了当前流程图形的相关信息（id, name,版本号）
		 */
		System.out.println( d );
		
	}

}
