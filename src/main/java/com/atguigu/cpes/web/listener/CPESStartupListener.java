package com.atguigu.cpes.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;


public class CPESStartupListener extends ContextLoaderListener {

	public void contextInitialized(ServletContextEvent event) {
		
		// 加载整个Spring环境
		//super.contextInitialized(event);
		
		// 获取web应用对象
		ServletContext application = event.getServletContext();
		
		// 获取当前web应用路径
		String appPath = application.getContextPath();
		
		// 将路径保存到web应用范围中
		application.setAttribute("APP_PATH", appPath);
		
		super.contextInitialized(event);
	}

}
