package com.ssafy.pjt.univ.repo;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.pjt.univ.dto.UnivDto;

@Mapper
public interface UnivMapper {
	// 전체 조회
	public List<UnivDto> selectAll();

}
