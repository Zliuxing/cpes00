package com.atguigu.cpes.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.cpes.bean.Datas;
import com.atguigu.cpes.bean.Page;
import com.atguigu.cpes.bean.Certtype;
import com.atguigu.cpes.dao.CerttypeDao;
import com.atguigu.cpes.service.CerttypeService;

@Service
public class CerttypeServiceImpl implements CerttypeService {

	@Autowired
	private CerttypeDao certtypeDao;

	public Page<Certtype> queryPage(Integer pageno, Integer pagesize) {
		// 获取分页对象
		Page<Certtype> page = new Page<Certtype>();
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("start", (pageno-1)*pagesize);
		paramMap.put("pagesize", pagesize);
		List<Certtype> data = certtypeDao.queryCerttype4Page(paramMap);
		int count = certtypeDao.queryCerttypeCount4Page(paramMap);
		
		page.setDatas(data);
		page.setTotalsize(count);
		
		return page;
	}

	public void insertCerttype(Certtype certtype) {
		certtypeDao.insertCerttype(certtype);
	}

	public Certtype queryCerttypeById(Integer id) {
		return certtypeDao.queryCerttypeById(id);
	}

	public void updateCerttype(Certtype certtype) {
		certtypeDao.updateCerttype(certtype);
	}

	public void deleteCerttypeById(Integer id) {
		certtypeDao.deleteCerttypeById(id);
	}

	public void deleteCerttypes(Datas ds) {
		certtypeDao.deleteCerttypes(ds);
	}

	public List<Certtype> queryAll() {
		return certtypeDao.queryAll();
	}

	public void deleteCerttype(Certtype certtype) {
		certtypeDao.deleteCerttype(certtype);
	}
}
