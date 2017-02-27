package com.atguigu.cpes.dao;

import java.util.List;
import java.util.Map;

import com.atguigu.cpes.bean.Datas;
import com.atguigu.cpes.bean.Cert;

public interface CertDao {

	List<Cert> queryCert4Page(Map<String, Object> paramMap);

	int queryCertCount4Page(Map<String, Object> paramMap);

	void insertCert(Cert cert);

	Cert queryCertById(Integer id);

	void updateCert(Cert cert);

	void deleteCertById(Integer id);

	void deleteCerts(Datas ds);

	List<Cert> queryAll();

	List<Cert> queryCertByAcctype(String acctype);

}