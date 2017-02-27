package com.atguigu.cpes.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.cpes.bean.Menu;
import com.atguigu.cpes.dao.MenuDao;
import com.atguigu.cpes.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuDao menuDao;

	public List<Menu> queryParentMenu() {
		return menuDao.queryParentMenu();
	}

	public List<Menu> queryChildMenu(Menu pmenu) {
		return menuDao.queryChildMenu(pmenu.getId());
	}

	public List<Menu> queryAll() {
		return menuDao.queryAll();
	}
}
