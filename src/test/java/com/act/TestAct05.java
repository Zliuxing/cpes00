package com.act;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAct05 {

	public static void main(String[] args) {
		
		// 获取Spring的环境
		ApplicationContext application =
			new ClassPathXmlApplicationContext("spring/spring-*.xml");
		
		// 获取流程框架的核心对象
		ProcessEngine pe = (ProcessEngine)application.getBean("processEngine");
		 
		// 获取用户的任务
		TaskService taskService = pe.getTaskService();
		
		TaskQuery query = taskService.createTaskQuery();
		
		List<Task> tasks = query.taskAssignee("zhangsan").list();
		List<Task> tasks1 = query.taskAssignee("lisi").list();
//		System.out.println( "完成任务之前..." );
//		for ( Task t : tasks ) {
//			System.out.println( "zhangsan的任务 = " + t.getName() );
//			// 完成任务
//			taskService.complete(t.getId());
//		}
//		System.out.println("*************************************************" );
//		for ( Task t : tasks1 ) {
//			System.out.println( "lisi的任务 = " + t.getName() );
//		}
//		
//		System.out.println( "完成任务之后..." );
//		tasks = query.taskAssignee("zhangsan").list();
//		tasks1 = query.taskAssignee("lisi").list();
//		for ( Task t : tasks ) {
//			System.out.println( "zhangsan的任务 = " + t.getName() );
//		}
//		System.out.println("*************************************************" );
//		for ( Task t : tasks1 ) {
//			System.out.println( "lisi的任务 = " + t.getName() );
//		}
		
		for ( Task t : tasks1 ) {
			taskService.complete(t.getId());
		}
	}

}
