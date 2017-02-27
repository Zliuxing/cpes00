package com.atguigu.cpes.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.cpes.bean.Datas;
import com.atguigu.cpes.bean.Page;
import com.atguigu.cpes.bean.Cert;
import com.atguigu.cpes.dao.CertDao;
import com.atguigu.cpes.service.CertService;

@Service
public class CertServiceImpl implements CertService {

	@Autowired
	private CertDao certDao;

	public Page<Cert> queryPage(Integer pageno, Integer pagesize) {
		// 获取分页对象
		Page<Cert> page = new Page<Cert>();
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("start", (pageno-1)*pagesize);
		paramMap.put("pagesize", pagesize);
		List<Cert> data = certDao.queryCert4Page(paramMap);
		int count = certDao.queryCertCount4Page(paramMap);
		
		page.setDatas(data);
		page.setTotalsize(count);
		
		return page;
	}

	public void insertCert(Cert cert) {
		certDao.insertCert(cert);
	}

	public Cert queryCertById(Integer id) {
		return certDao.queryCertById(id);
	}

	public void updateCert(Cert cert) {
		certDao.updateCert(cert);
	}

	public void deleteCertById(Integer id) {
		certDao.deleteCertById(id);
	}

	public void deleteCerts(Datas ds) {
		certDao.deleteCerts(ds);
	}

	public List<Cert> queryAll() {
		return certDao.queryAll();
	}

	public List<Cert> queryCertByAcctype(String acctype) {
		return certDao.queryCertByAcctype(acctype);
	}
}
