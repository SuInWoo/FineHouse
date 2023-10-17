package com.ssafy.pjt.like.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.pjt.like.dto.LikeDto;
import com.ssafy.pjt.like.service.LikeService;

@CrossOrigin("*")
@RestController
@RequestMapping("/like")
public class LikeController {

	@Autowired
	LikeService likeService;

	@PostMapping("/write")
	public ResponseEntity<?> write(@RequestBody LikeDto likeDto) throws Exception {
		int writeNum = likeService.putLike(likeDto);
		return new ResponseEntity<Integer>(writeNum, HttpStatus.OK);
	}

	@GetMapping("/list/{id}")
	public ResponseEntity<?> getList(@PathVariable("id") String id) throws Exception {
		List<LikeDto> rnt = likeService.getList(id);
		return new ResponseEntity<List<LikeDto>>(rnt, HttpStatus.OK);
	}

	@PostMapping("/delete")
	public ResponseEntity<?> delete(@RequestBody LikeDto likeDto) throws Exception {
		int deleteNum = likeService.deleteLike(likeDto);
		return new ResponseEntity<Integer>(deleteNum, HttpStatus.OK);
	}

	@PostMapping("/getOne")
	public ResponseEntity<?> getOne(@RequestBody LikeDto likeDto) throws Exception {

		LikeDto gLikeDto = likeService.getOne(likeDto);
		return new ResponseEntity<LikeDto>(gLikeDto, HttpStatus.OK);

	}

}
