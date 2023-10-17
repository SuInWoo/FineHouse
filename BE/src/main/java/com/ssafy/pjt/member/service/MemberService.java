package com.ssafy.pjt.member.service;

import com.ssafy.pjt.member.dto.MemberDto;

public interface MemberService {
	public MemberDto login(MemberDto memberDto) throws Exception;
	public MemberDto userInfo(String userId) throws Exception;
	public MemberDto join(MemberDto memberDto) throws Exception;
}
