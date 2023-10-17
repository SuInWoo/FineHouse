package com.ssafy.pjt.house.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.pjt.house.dto.RegionCodeDto;
import com.ssafy.pjt.house.repo.HouseMapper;

@Service
public class HouseService {
	
	@Autowired
	private HouseMapper houseMapper;
	
	public List<RegionCodeDto> selectSido() throws Exception {
		return houseMapper.selectSido();
	}
	
	public List<RegionCodeDto> selectGugun(String sido) throws Exception {
		return houseMapper.selectGugun(sido);
	}
	
	public List<RegionCodeDto> selectDong(String gugun) throws Exception {
		return houseMapper.selectDong(gugun);
	} 
	
	
}
