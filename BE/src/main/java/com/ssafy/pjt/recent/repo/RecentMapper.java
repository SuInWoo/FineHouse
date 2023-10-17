package com.ssafy.pjt.recent.repo;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.pjt.recent.dto.RecentDto;

@Mapper
public interface RecentMapper {
	
	// 전체 조회 	
	public List<RecentDto> selectAll(String id);
	
	// 삭제
	public int delete(int no);
	
	// 추가
	public int insert(RecentDto recentDto);
}
