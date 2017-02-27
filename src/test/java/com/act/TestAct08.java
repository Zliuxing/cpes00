package com.act;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAct08 {

	public static void main(String[] args) {
		
		ApplicationContext application =
				new ClassPathXmlApplicationContext("spring/spring-*.xml");
			
		// 获取流程框架的核心对象
		ProcessEngine pe = (ProcessEngine)application.getBean("processEngine");
		 
		// 查询小组任务
		TaskService taskService = pe.getTaskService();
		
		List<Task> ts = taskService.createTaskQuery().taskCandidateGroup("cpes").list();
		
		long cnt = taskService.createTaskQuery().taskAssignee("zhangsan").count();
		System.out.println( "分配任务之前，zhangsan的任务数量 = " + cnt );
		
		// 分配任务， 领取任务
		for ( Task t : ts ) {
			taskService.claim(t.getId(), "zhangsan");
		}
		
		cnt = taskService.createTaskQuery().taskAssignee("zhangsan").count();
		System.out.println( "分配任务之后，zhangsan的任务数量 = " + cnt );
	}

}
