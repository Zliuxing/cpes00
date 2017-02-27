package com.atguigu.cpes.bean;

public class MemberCert {

	private Integer id, memberid,certid;
	private String path, certname;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMemberid() {
		return memberid;
	}
	public void setMemberid(Integer memberid) {
		this.memberid = memberid;
	}
	public Integer getCertid() {
		return certid;
	}
	public void setCertid(Integer certid) {
		this.certid = certid;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getCertname() {
		return certname;
	}
	public void setCertname(String certname) {
		this.certname = certname;
	}
	
}
