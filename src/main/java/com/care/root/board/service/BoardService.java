package com.care.root.board.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.care.root.board.dto.BoardDTO;

public interface BoardService {
	public void boardAllList(Model model);
	public int writeSave(BoardDTO dto);
}
