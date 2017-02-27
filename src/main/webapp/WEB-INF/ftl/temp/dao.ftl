package ${basepackage}.dao;

import java.util.List;
import java.util.Map;

import com.atguigu.cpes.bean.Datas;
import com.atguigu.cpes.bean.${className?cap_first};

public interface ${className?cap_first}Dao {

	List<${className?cap_first}> query${className?cap_first}4Page(Map<String, Object> paramMap);

	int query${className?cap_first}Count4Page(Map<String, Object> paramMap);

	void insert${className?cap_first}(${className?cap_first} ${className});

	${className?cap_first} query${className?cap_first}ById(Integer id);

	void update${className?cap_first}(${className?cap_first} ${className});

	void delete${className?cap_first}ById(Integer id);

	void delete${className?cap_first}s(Datas ds);

	List<${className?cap_first}> queryAll();

}