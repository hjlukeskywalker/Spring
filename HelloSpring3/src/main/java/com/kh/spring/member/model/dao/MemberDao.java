package com.kh.spring.member.model.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.kh.spring.member.model.vo.Member;

public interface MemberDao {
	int InsertMember(SqlSessionTemplate session, Member m);
	Member selectMember(SqlSessionTemplate session,Map param);
}
