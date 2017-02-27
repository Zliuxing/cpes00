package com.atguigu.cpes.dao;

import java.util.List;

import com.atguigu.cpes.bean.Menu;

public interface MenuDao {

	List<Menu> queryParentMenu();

	List<Menu> queryChildMenu(Integer id);

	List<Menu> queryAll();

}
