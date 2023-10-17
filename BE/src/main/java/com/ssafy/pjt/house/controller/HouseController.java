package com.ssafy.pjt.house.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.pjt.house.dto.RegionCodeDto;
import com.ssafy.pjt.house.service.HouseService;

@RestController
@RequestMapping("/house")
public class HouseController {
	
	@Autowired
	private HouseService houseService;
	
	@GetMapping("/sido")
	public ResponseEntity<List<RegionCodeDto>> getSido() throws Exception {
		return new ResponseEntity<List<RegionCodeDto>>(houseService.selectSido(), HttpStatus.OK);
	}
	
	@GetMapping("/gugun/{sido}")
	public ResponseEntity<List<RegionCodeDto>> getGugun(@PathVariable("sido") String sido) throws Exception {
		return new ResponseEntity<List<RegionCodeDto>>(houseService.selectGugun(sido), HttpStatus.OK);
	}
	
	@GetMapping("/dong/{gugun}")
	public ResponseEntity<List<RegionCodeDto>> getDong(@PathVariable("gugun") String gugun) throws Exception {
		return new ResponseEntity<List<RegionCodeDto>>(houseService.selectDong(gugun), HttpStatus.OK);
	}
}
