package com.ssafy.pjt.house.repo;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.pjt.house.dto.RegionCodeDto;

@Mapper
public interface HouseMapper {
	public List<RegionCodeDto> selectSido();
	
	public List<RegionCodeDto> selectGugun(String sido);
	
	public List<RegionCodeDto> selectDong(String gugun);
}
