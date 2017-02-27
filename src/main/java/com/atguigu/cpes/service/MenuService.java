package com.atguigu.cpes.service;

import java.util.List;

import com.atguigu.cpes.bean.Menu;

public interface MenuService {

	List<Menu> queryParentMenu();

	List<Menu> queryChildMenu(Menu pmenu);

	List<Menu> queryAll();

}
