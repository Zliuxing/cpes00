package com.atguigu.cpes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.cpes.bean.Cert;
import com.atguigu.cpes.bean.Certtype;
import com.atguigu.cpes.bean.Datas;
import com.atguigu.cpes.bean.Page;
import com.atguigu.cpes.service.CertService;
import com.atguigu.cpes.service.CerttypeService;

@Controller
@RequestMapping("/certtype")
public class CerttypeController extends BaseController {

	@Autowired
	private CerttypeService certtypeService;
	@Autowired
	private CertService certService;
	
	@RequestMapping("/list")
	public String list( Model model ) {
		
		// 读取资质数据
		List<Cert> cs = certService.queryAll();
		model.addAttribute("cs", cs);
		
		// 获取已经分类的资质数据
		List<Certtype> cts = certtypeService.queryAll();
		model.addAttribute("cts", cts);
		
		return "certtype/list";
	}
	
	@RequestMapping("/add")
	public String add() {
		return "certtype/add";
	}
	
	@RequestMapping("/assign")
	public String assign() {
		return "certtype/assign";
	}
	
	@RequestMapping("/edit/{id}")
	public String edit( @PathVariable("id")Integer id, Model model ) {
		
		Certtype certtype = certtypeService.queryCerttypeById(id);
		model.addAttribute("certtype", certtype);
		
		return "certtype/edit";
	}
	
	@ResponseBody
	@RequestMapping("/insert")
	public Object insert( Certtype certtype ) {
		start();
		
		try {
			certtypeService.insertCerttype(certtype);
			success(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			success(false);
		}
		
		return end();
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public Object update( Certtype certtype ) {
		start();
		
		try {
			certtypeService.updateCerttype(certtype);
			success(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			success(false);
		}
		
		return end();
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Object delete( Certtype certtype ) {
		start();
		
		try {
			certtypeService.deleteCerttype(certtype);
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
			certtypeService.deleteCerttypes(ds);
			success(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			success(false);
		}
		
		return end();
	}
	
	@ResponseBody
	@RequestMapping("/queryPageCerttypes")
	public Object queryPageCerttypes( Integer pageno, Integer pagesize ) {
		start();
		success(true);
		
		Page<Certtype> dataPage = certtypeService.queryPage(pageno, pagesize);
		dataPage.setPageno(pageno);
		dataPage.setPagesize(pagesize);

		param("datapage", dataPage);
		return end();
	}
}
