package ${basepackage}.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.cpes.bean.Datas;
import com.atguigu.cpes.bean.Page;
import com.atguigu.cpes.bean.${className?cap_first};
import com.atguigu.cpes.service.${className?cap_first}Service;

@Controller
@RequestMapping("/${className}")
public class ${className?cap_first}Controller extends BaseController {

	@Autowired
	private ${className?cap_first}Service ${className}Service;
	
	@RequestMapping("/list")
	public String list() {
		return "${className}/list";
	}
	
	@RequestMapping("/add")
	public String add() {
		return "${className}/add";
	}
	
	@RequestMapping("/assign")
	public String assign() {
		return "${className}/assign";
	}
	
	@RequestMapping("/edit/{id}")
	public String edit( @PathVariable("id")Integer id, Model model ) {
		
		${className?cap_first} ${className} = ${className}Service.query${className?cap_first}ById(id);
		model.addAttribute("${className}", ${className});
		
		return "${className}/edit";
	}
	
	@ResponseBody
	@RequestMapping("/insert")
	public Object insert( ${className?cap_first} ${className} ) {
		start();
		
		try {
			${className}Service.insert${className?cap_first}(${className});
			success(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			success(false);
		}
		
		return end();
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public Object update( ${className?cap_first} ${className} ) {
		start();
		
		try {
			${className}Service.update${className?cap_first}(${className});
			success(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			success(false);
		}
		
		return end();
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Object delete( Integer id ) {
		start();
		
		try {
			${className}Service.delete${className?cap_first}ById(id);
			success(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			success(false);
		}
		
		return end();
	}
	
	@ResponseBody
	@RequestMapping("/deletes")
	public Object deletes( Datas ds ) {
		start();
		
		try {
			${className}Service.delete${className?cap_first}s(ds);
			success(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			success(false);
		}
		
		return end();
	}
	
	@ResponseBody
	@RequestMapping("/queryPage${className?cap_first}s")
	public Object queryPage${className?cap_first}s( Integer pageno, Integer pagesize ) {
		start();
		success(true);
		
		Page<${className?cap_first}> dataPage = ${className}Service.queryPage(pageno, pagesize);
		dataPage.setPageno(pageno);
		dataPage.setPagesize(pagesize);

		param("datapage", dataPage);
		return end();
	}
}
