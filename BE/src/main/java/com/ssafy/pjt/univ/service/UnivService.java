package com.ssafy.pjt.univ.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.pjt.univ.dto.UnivDto;
import com.ssafy.pjt.univ.repo.UnivMapper;

@Service
public class UnivService {
	
	@Autowired
	private UnivMapper univMapper;
	
	public List<UnivDto> getAll(double houseLat, double houseLng) {
		
		List<UnivDto> selectUnivs = univMapper.selectAll();
		List<UnivDto> ansUnivs = new ArrayList<>();
		double distance;
	    double EARTH_RADIUS = 6371; // 지구 반지름(km)
	    double radLat1 = Math.toRadians(houseLat);
        double radLon1 = Math.toRadians(houseLng);

	    for (UnivDto univDto : selectUnivs) {
	    	// 위도 및 경도를 라디안 단위로 변환
	        
	        double radLat2 = Math.toRadians(univDto.getLat());
	        double radLon2 = Math.toRadians(univDto.getLng());

	        // 두 지점의 위도 및 경도 차이 계산
	        double deltaLat = radLat2 - radLat1;
	        double deltaLon = radLon2 - radLon1;

	        // Haversine 공식을 사용하여 거리 계산
	        double a = Math.pow(Math.sin(deltaLat / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(deltaLon / 2), 2);
	        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	        distance = EARTH_RADIUS * c;
		    
		    if(distance <= 3) {// 3km로 설정
		    	ansUnivs.add(univDto);
		    }
		}

	    return ansUnivs;
	}
}
