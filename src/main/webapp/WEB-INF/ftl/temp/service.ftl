package ${basepackage}.service;

import java.util.List;

import com.atguigu.cpes.bean.Datas;
import com.atguigu.cpes.bean.Page;
import com.atguigu.cpes.bean.${className?cap_first};

public interface ${className?cap_first}Service {

	Page<${className?cap_first}> queryPage(Integer pageno, Integer pagesize);

	void insert${className?cap_first}(${className?cap_first} ${className});

	${className?cap_first} query${className?cap_first}ById(Integer id);

	void update${className?cap_first}(${className?cap_first} ${className});

	void delete${className?cap_first}ById(Integer id);

	void delete${className?cap_first}s(Datas ds);

	List<${className?cap_first}> queryAll();


}
