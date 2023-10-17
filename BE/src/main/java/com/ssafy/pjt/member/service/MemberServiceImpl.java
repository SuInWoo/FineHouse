package com.ssafy.pjt.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.pjt.member.dto.MemberDto;
import com.ssafy.pjt.member.repo.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public MemberDto login(MemberDto memberDto) throws Exception {
		if(memberDto.getUserId() == null || memberDto.getUserPwd() == null)
			return null;
		return memberMapper.login(memberDto);
	}

	@Override
	public MemberDto userInfo(String userId) throws Exception {
		return memberMapper.userInfo(userId);
	}
	// 회원가입(join)
	public MemberDto join(MemberDto memberDto) throws Exception {
	   // 이미 ID가 중복이면 회원가입 x -> Exception(예외)발생
		MemberDto checkMember = memberMapper.selectOne(memberDto.getUserId());		
		if(checkMember != null)	// 아이디가 중복이면 
			throw new Exception("아이디가 이미 존재합니다");
	     // 회원가입 진행
		memberMapper.insert(memberDto);
	    return memberDto;
	 }
		

}
