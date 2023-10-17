package com.ssafy.pjt.like.repo;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.pjt.like.dto.LikeDto;

@Mapper
public interface LikeMapper {
	
	// 추가
	public int insert(LikeDto likeDto);
	
	// 삭제
	public int delete(LikeDto likeDto);
	
	// 전체 조회
	public List<LikeDto> selectAll(String id);
	
	// 개별조회
	public LikeDto selectOne(LikeDto likeDto);
}
