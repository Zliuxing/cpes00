package com.atguigu.cpes.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.cpes.bean.Member;
import com.atguigu.cpes.bean.MemberCert;
import com.atguigu.cpes.bean.Ticket;
import com.atguigu.cpes.dao.MemberDao;
import com.atguigu.cpes.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao memberDao;

	public Member queryMember4Login(Member member) {
		return memberDao.queryMember4Login(member);
	}

	public void updateAcctype(Member member) {
		memberDao.updateAcctype(member);
	}

	public void updateMemberInfo(Member member) {
		memberDao.updateMemberInfo(member);
	}

	public void insertMemberCert(Map<String, Object> paramMap) {
		memberDao.insertMemberCert(paramMap);
	}

	public void insertTicket(Ticket ticket) {
		memberDao.insertTicket(ticket);
	}

	public void updateEmail(Member member) {
		memberDao.updateEmail(member);
	}

	public void updateTicketStatus(Ticket ticket) {
		memberDao.updateTicketStatus(ticket);
	}

	public Ticket queryTicketByMemberid(Integer id) {
		return memberDao.queryTicketByMemberid(id);
	}

	public Ticket queryTicketByPiid(String processInstanceId) {
		return memberDao.queryTicketByPiid(processInstanceId);
	}

	public List<MemberCert> queryMemberCertsByMemberid(Integer memberid) {
		return memberDao.queryMemberCertsByMemberid(memberid);
	}

	public void updateStatus(Integer memberid) {
		memberDao.updateStatus(memberid);
	}
}
