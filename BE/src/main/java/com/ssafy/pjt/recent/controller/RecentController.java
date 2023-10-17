package com.ssafy.pjt.recent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.pjt.like.dto.LikeDto;
import com.ssafy.pjt.recent.dto.RecentDto;
import com.ssafy.pjt.recent.service.RecentService;

@CrossOrigin("*")
@RestController
@RequestMapping("/recent")
public class RecentController {

	@Autowired
	private RecentService recentService;

	@GetMapping("/list/{id}")
	public ResponseEntity<?> getAll(@PathVariable("id") String id) throws Exception {
		List<RecentDto> rnt = recentService.getAll(id);
		return new ResponseEntity<List<RecentDto>>(rnt, HttpStatus.OK);
	}

	@PostMapping("/write")
	public ResponseEntity<?> insertLimitFour(@RequestBody RecentDto recentDto) throws Exception {
		int rnt = recentService.insertLimitFour(recentDto);
		return new ResponseEntity<Integer>(rnt, HttpStatus.OK);
	}
	

}
