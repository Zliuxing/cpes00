package com.atguigu.cpes.dao;

import java.util.List;
import java.util.Map;

import com.atguigu.cpes.bean.Member;
import com.atguigu.cpes.bean.MemberCert;
import com.atguigu.cpes.bean.Ticket;

/**
 * 会员数据访问对象
 * @author 18801
 *
 */
public interface MemberDao {

	Member queryMember4Login(Member member);

	void updateAcctype(Member member);

	void updateMemberInfo(Member member);

	void insertMemberCert(Map<String, Object> paramMap);

	void insertTicket(Ticket ticket);

	void updateEmail(Member member);

	void updateTicketStatus(Ticket ticket);

	Ticket queryTicketByMemberid(Integer id);

	Ticket queryTicketByPiid(String processInstanceId);

	List<MemberCert> queryMemberCertsByMemberid(Integer memberid);

	void updateStatus(Integer memberid);

}
