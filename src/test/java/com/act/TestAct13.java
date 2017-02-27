package com.act;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.impl.bpmn.diagram.ProcessDiagramGenerator;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.context.Context;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAct13 {

	public static void main(String[] args) throws Exception {
		
		// 获取Spring的环境
		ApplicationContext application =
			new ClassPathXmlApplicationContext("spring/spring-*.xml");
		
		// 获取流程框架的核心对象
		ProcessEngine pe = (ProcessEngine)application.getBean("processEngine");
		ProcessEngineConfigurationImpl cfg = 
				(ProcessEngineConfigurationImpl)application.getBean("processEngineConfiguration");
		 
		RepositoryService repositoryService = pe.getRepositoryService();
		RuntimeService runtimeService = pe.getRuntimeService();
		
		// 获取流程监控图：流程实例运行图
		// bpmnModel 表示当前流程定义图
		// imageType ： 图片类型
		// highLightedActivities : 流程正在执行的任务
		BpmnModel model = repositoryService.getBpmnModel("myProcess:2:604");
		List<String> ids = runtimeService.getActiveActivityIds("701");
		
		Context.setProcessEngineConfiguration(cfg);
		
		InputStream in = ProcessDiagramGenerator
		    .generateDiagram(model, "png", ids);
		
		FileOutputStream out = new FileOutputStream(new File("d:\\process.png"));
		
		int i = -1;
		
		while ( (i = in.read()) != -1 ) {
			out.write(i);
		}
		
		out.close();
		
		System.out.println( "图片生成成功" );
	}

}
