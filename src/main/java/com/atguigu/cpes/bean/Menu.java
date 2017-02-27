package com.atguigu.cpes.bean;

import java.util.ArrayList;
import java.util.List;

public class Menu {

	private Integer id,pid;
	private String name,url;
	private List<Menu> childrenMenus = new ArrayList<Menu>();
	private List<Menu> children = new ArrayList<Menu>();
	
	public List<Menu> getChildren() {
		return children;
	}
	public void setChildren(List<Menu> children) {
		this.children = children;
	}
	public List<Menu> getChildrenMenus() {
		return childrenMenus;
	}
	public void setChildrenMenus(List<Menu> childrenMenus) {
		this.childrenMenus = childrenMenus;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
