package com.atguigu.cpes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.cpes.bean.Cert;
import com.atguigu.cpes.bean.Datas;
import com.atguigu.cpes.bean.Page;
import com.atguigu.cpes.service.CertService;

@Controller
@RequestMapping("/cert")
public class CertController extends BaseController {

	@Autowired
	private CertService certService;
	
	@RequestMapping("/list")
	public String list() {
		return "cert/list";
	}
	
	@RequestMapping("/add")
	public String add() {
		return "cert/add";
	}
	
	@RequestMapping("/assign")
	public String assign() {
		return "cert/assign";
	}
	
	@RequestMapping("/edit/{id}")
	public String edit( @PathVariable("id")Integer id, Model model ) {
		
		Cert cert = certService.queryCertById(id);
		model.addAttribute("cert", cert);
		
		return "cert/edit";
	}
	
	@ResponseBody
	@RequestMapping("/insert")
	public Object insert( Cert cert ) {
		start();
		
		try {
			certService.insertCert(cert);
			success(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			success(false);
		}
		
		return end();
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public Object update( Cert cert ) {
		start();
		
		try {
			certService.updateCert(cert);
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
			certService.deleteCertById(id);
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
			certService.deleteCerts(ds);
			success(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			success(false);
		}
		
		return end();
	}
	
	@ResponseBody
	@RequestMapping("/queryPageCerts")
	public Object queryPageCerts( Integer pageno, Integer pagesize ) {
		start();
		success(true);
		
		Page<Cert> dataPage = certService.queryPage(pageno, pagesize);
		dataPage.setPageno(pageno);
		dataPage.setPagesize(pagesize);

		param("datapage", dataPage);
		return end();
	}
}
