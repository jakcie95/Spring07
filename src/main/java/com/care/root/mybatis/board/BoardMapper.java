package com.care.root.mybatis.board;

import java.util.List;

import com.care.root.board.dto.BoardDTO;

public interface BoardMapper {
	public List<BoardDTO> boardAllList();
	public int writeSave(BoardDTO dto);
	public BoardDTO contentView(String title);
	public int delete(String title);
}
