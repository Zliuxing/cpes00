package ${basepackage}.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.cpes.bean.Datas;
import com.atguigu.cpes.bean.Page;
import com.atguigu.cpes.bean.${className?cap_first};
import com.atguigu.cpes.dao.${className?cap_first}Dao;
import com.atguigu.cpes.service.${className?cap_first}Service;

@Service
public class ${className?cap_first}ServiceImpl implements ${className?cap_first}Service {

	@Autowired
	private ${className?cap_first}Dao ${className}Dao;

	public Page<${className?cap_first}> queryPage(Integer pageno, Integer pagesize) {
		// 获取分页对象
		Page<${className?cap_first}> page = new Page<${className?cap_first}>();
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("start", (pageno-1)*pagesize);
		paramMap.put("pagesize", pagesize);
		List<${className?cap_first}> data = ${className}Dao.query${className?cap_first}4Page(paramMap);
		int count = ${className}Dao.query${className?cap_first}Count4Page(paramMap);
		
		page.setDatas(data);
		page.setTotalsize(count);
		
		return page;
	}

	public void insert${className?cap_first}(${className?cap_first} ${className}) {
		${className}Dao.insert${className?cap_first}(${className});
	}

	public ${className?cap_first} query${className?cap_first}ById(Integer id) {
		return ${className}Dao.query${className?cap_first}ById(id);
	}

	public void update${className?cap_first}(${className?cap_first} ${className}) {
		${className}Dao.update${className?cap_first}(${className});
	}

	public void delete${className?cap_first}ById(Integer id) {
		${className}Dao.delete${className?cap_first}ById(id);
	}

	public void delete${className?cap_first}s(Datas ds) {
		${className}Dao.delete${className?cap_first}s(ds);
	}

	public List<${className?cap_first}> queryAll() {
		return ${className}Dao.queryAll();
	}
}
