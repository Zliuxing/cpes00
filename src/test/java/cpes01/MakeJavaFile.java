package cpes01;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

public class MakeJavaFile {

	private static void genController(Configuration cfg, Map<String, Object> paramMap, String className) throws Exception {
        // 模板
        Template t = cfg.getTemplate("controller.ftl");
        // 组合生成
        className = className.substring(0, 1).toUpperCase() + className.substring(1);
        Writer out = new OutputStreamWriter(new FileOutputStream(new File("D:\\company\\atguigu\\workspace\\20161224\\cpes09-RBAC-1\\src\\main\\java\\com\\atguigu\\cpes\\controller\\"+className+"Controller.java")), "UTF-8");
        t.process(paramMap, out);
	}
	
	private static void genBean(Configuration cfg, Map<String, Object> paramMap, String className) throws Exception {
        // 模板
        Template t = cfg.getTemplate("bean.ftl");
        // 组合生成
        className = className.substring(0, 1).toUpperCase() + className.substring(1);
        Writer out = new OutputStreamWriter(new FileOutputStream(new File("D:\\company\\atguigu\\workspace\\20161224\\cpes09-RBAC-1\\src\\main\\java\\com\\atguigu\\cpes\\bean\\"+className+".java")), "UTF-8");
        t.process(paramMap, out);
	}
	
	private static void genService(Configuration cfg, Map<String, Object> paramMap, String className) throws Exception {
        // 模板
        Template t = cfg.getTemplate("service.ftl");
        // 组合生成
        className = className.substring(0, 1).toUpperCase() + className.substring(1);
        Writer out = new OutputStreamWriter(new FileOutputStream(new File("D:\\company\\atguigu\\workspace\\20161224\\cpes09-RBAC-1\\src\\main\\java\\com\\atguigu\\cpes\\service\\"+className+"Service.java")), "UTF-8");
        t.process(paramMap, out);
	}
	
	private static void genServiceImpl(Configuration cfg, Map<String, Object> paramMap, String className) throws Exception {
        // 模板
        Template t = cfg.getTemplate("serviceimpl.ftl");
        // 组合生成
        className = className.substring(0, 1).toUpperCase() + className.substring(1);
        Writer out = new OutputStreamWriter(new FileOutputStream(new File("D:\\company\\atguigu\\workspace\\20161224\\cpes09-RBAC-1\\src\\main\\java\\com\\atguigu\\cpes\\service\\impl\\"+className+"ServiceImpl.java")), "UTF-8");
        t.process(paramMap, out);
	}
	
	private static void genDao(Configuration cfg, Map<String, Object> paramMap, String className) throws Exception {
        // 模板
        Template t = cfg.getTemplate("dao.ftl");
        // 组合生成
        className = className.substring(0, 1).toUpperCase() + className.substring(1);
        Writer out = new OutputStreamWriter(new FileOutputStream(new File("D:\\company\\atguigu\\workspace\\20161224\\cpes09-RBAC-1\\src\\main\\java\\com\\atguigu\\cpes\\dao\\"+className+"Dao.java")), "UTF-8");
        t.process(paramMap, out);
	}
	
	public static void main(String[] args) throws Exception {
        // 创建Freemarker对象的配置对象
        Configuration cfg = new Configuration();
        // 设定默认的位置（路径）
        cfg.setDirectoryForTemplateLoading(new File("D:\\company\\atguigu\\workspace\\20161224\\cpes09-RBAC-1\\src\\main\\webapp\\WEB-INF\\ftl\\temp"));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        
        // 数据
        Map<String, Object> paramMap = new HashMap<String, Object>();
        
        String className = "member";
        
        paramMap.put("basepackage", "com.atguigu.cpes");
        paramMap.put("className", className);
        paramMap.put("author", "");
        
        List<Attribute> attrs = new ArrayList<Attribute>();
        
        Attribute a1 = new Attribute();
        a1.setType("String");
        a1.setName("loginacct");
        
        Attribute a2 = new Attribute();
        a2.setType("String");
        a2.setName("username");
        
        Attribute a3 = new Attribute();
        a3.setType("Integer");
        a3.setName("id");
        
        Attribute a4 = new Attribute();
        a4.setType("String");
        a4.setName("userpswd");
        
        Attribute a5 = new Attribute();
        a5.setType("String");
        a5.setName("email");
        
        attrs.add(a1);
        attrs.add(a2);
        attrs.add(a3);
        attrs.add(a4);
        attrs.add(a5);
        paramMap.put("attrs", attrs);
        
        // 生成文件
        genController(cfg, paramMap, className);
        genBean(cfg, paramMap, className);
        genService(cfg, paramMap, className);
        genServiceImpl(cfg, paramMap, className);
        genDao(cfg, paramMap, className);
	}

}
