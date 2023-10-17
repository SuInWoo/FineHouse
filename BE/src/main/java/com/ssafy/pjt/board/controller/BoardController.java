package com.ssafy.pjt.board.controller;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.pjt.board.dto.BoardDto;
import com.ssafy.pjt.board.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin("*")
@RequestMapping("/board")
@RestController
public class BoardController {

	@Autowired
	private BoardService boardService;

	// 공지사항 게시글 작성 (c)
	@PostMapping("/write")
	public ResponseEntity<?> write(@RequestBody BoardDto boardDto) throws Exception {
		BoardDto board = boardService.write(boardDto);
		return new ResponseEntity<BoardDto>(board, HttpStatus.OK);
	}

	// 공지사항 게시글 리스트 (r)
//	@GetMapping("/list")
//	public ResponseEntity<?> read() throws Exception {
//		List<BoardDto> list = boardService.getList();
//		return new ResponseEntity<List<BoardDto>>(list, HttpStatus.OK);
//	}
	@GetMapping
	public ResponseEntity<?> read() throws Exception {
		List<BoardDto> list = boardService.getList();
		return new ResponseEntity<List<BoardDto>>(list, HttpStatus.OK);
	}

	// 상세 조회
	@GetMapping("/detail/{bno}")
	public ResponseEntity<?> readDetail(@PathVariable("bno") int bno) throws Exception {
		BoardDto board = boardService.getDetail(bno);
		return new ResponseEntity<BoardDto>(board, HttpStatus.OK);
	}

	// 공지사항 게시글 업데이트 u
	@PutMapping("/modify")
	public ResponseEntity<?> modify(@RequestBody BoardDto boardDto) throws Exception {
		int rnt = boardService.modifyBoard(boardDto);
		return new ResponseEntity<Integer>(rnt, HttpStatus.OK);
	}

	// 공지사항 게시글 삭제 d
	@DeleteMapping("/delete/{bno}")
	public ResponseEntity<?> delete(@PathVariable("bno") int bno) throws Exception {
		int check = boardService.deleteBoard(bno);
		return new ResponseEntity<Integer>(check, HttpStatus.OK);
	}

}
