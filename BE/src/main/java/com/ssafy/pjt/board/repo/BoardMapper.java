package com.ssafy.pjt.board.repo;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.pjt.board.dto.BoardDto;

@Mapper
public interface BoardMapper {

	int write(BoardDto b);

	BoardDto getDetail(int bno);

	int getModify(BoardDto boardDto);

	int delete(int bno);

	List<BoardDto> selectAll();

}
