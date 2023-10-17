package com.ssafy.pjt.member.repo;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.pjt.member.dto.MemberDto;

@Mapper
public interface MemberMapper {
	public MemberDto selectOne(String userId); // 개별 조회
	
	public List<MemberDto> selectAll(); // 전체 조회
	
	public int insert(MemberDto memberDto); // DB에 회원 추가
	
	public int delete(String userId); // DB에 회원 삭제
	
	
	public MemberDto login(MemberDto memberDto) throws SQLException;
	
	public MemberDto userInfo(String userid) throws SQLException;
	
}
