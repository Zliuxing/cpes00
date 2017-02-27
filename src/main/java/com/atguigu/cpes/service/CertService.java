package com.atguigu.cpes.service;

import java.util.List;

import com.atguigu.cpes.bean.Datas;
import com.atguigu.cpes.bean.Page;
import com.atguigu.cpes.bean.Cert;

public interface CertService {

	Page<Cert> queryPage(Integer pageno, Integer pagesize);

	void insertCert(Cert cert);

	Cert queryCertById(Integer id);

	void updateCert(Cert cert);

	void deleteCertById(Integer id);

	void deleteCerts(Datas ds);

	List<Cert> queryAll();

	List<Cert> queryCertByAcctype(String acctype);


}
