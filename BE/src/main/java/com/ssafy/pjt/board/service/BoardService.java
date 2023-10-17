package com.ssafy.pjt.board.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.pjt.board.dto.BoardDto;
import com.ssafy.pjt.board.repo.BoardMapper;

@Service
public class BoardService {
	@Autowired
	private BoardMapper boardMapper;

	public BoardDto write(BoardDto b) throws Exception {
		if (boardMapper.write(b) != 0)
			return b;
		else
			return null;
	}

	public List<BoardDto> getList() throws SQLException {
		return boardMapper.selectAll();
	}

	public BoardDto getDetail(int bno) throws Exception {
		System.out.println( boardMapper.getDetail(bno));
		return boardMapper.getDetail(bno);
	}

	public int modifyBoard(BoardDto boardDto) throws Exception {
		System.out.println(boardDto);
		int rnt = boardMapper.getModify(boardDto);
		return rnt;
	}

	public int deleteBoard(int bno) throws Exception {
		return boardMapper.delete(bno);
	}



}
