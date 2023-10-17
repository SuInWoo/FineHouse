package com.ssafy.pjt.like.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.pjt.like.dto.LikeDto;
import com.ssafy.pjt.like.repo.LikeMapper;

@Service
public class LikeService {
	@Autowired
	private LikeMapper likeMapper;

	// 찜 목록에 추가
	public int putLike(LikeDto likeDto) {
		System.out.println(likeDto);
		return likeMapper.insert(likeDto);
	}

	// 찜 목록에서 삭제
	public int deleteLike(LikeDto likeDto) {
		return likeMapper.delete(likeDto);
	}

	// 찜 리스트
	public List<LikeDto> getList(String id) {
		return likeMapper.selectAll(id);
	}

	// 찜 목록에서 한개만 조회
	public LikeDto getOne(LikeDto likeDto) {
		return likeMapper.selectOne(likeDto);
	}

}
