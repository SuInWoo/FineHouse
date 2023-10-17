package com.ssafy.pjt.recent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.pjt.recent.dto.RecentDto;
import com.ssafy.pjt.recent.repo.RecentMapper;

@Service
public class RecentService {

	@Autowired
	private RecentMapper recentMapper;

	// 전체 조회
	public List<RecentDto> getAll(String id) {
		return recentMapper.selectAll(id);
	}

	// 삭제 및 추가
	public int insertLimitFour(RecentDto recentDto) {
		// 먼저 전체 리스트 조회
		List<RecentDto> reList = recentMapper.selectAll(recentDto.getId());

		if (reList.size() > 0) { // 비어있지 않으면 확인
			// 중복인 친구 제거
			for (int i = 0; i < reList.size(); i++) {
				if (reList.get(i).getId().equals(recentDto.getId())
						&& reList.get(i).getDong().equals(recentDto.getDong())
						&& reList.get(i).getJibun().equals(recentDto.getJibun())) {
					recentMapper.delete(reList.get(i).getNo());
					reList.remove(i);
					break;
				}
			}
			
			// 중복인 애가 없이 full일때
			if (reList.size() == 4) {
				recentMapper.delete(reList.get(0).getNo());
			}
		}

		// 비어있으면 바로 넣기
		
		return recentMapper.insert(recentDto);

	}
}
