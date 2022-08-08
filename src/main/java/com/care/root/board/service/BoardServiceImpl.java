package com.care.root.board.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.care.root.board.dto.BoardDTO;
import com.care.root.mybatis.board.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService{
	@Autowired
	BoardMapper mapper;
	
	public void boardAllList(Model model) {
		List<BoardDTO> list = mapper.boardAllList();
		model.addAttribute("list", list);
	}
	public int writeSave(BoardDTO dto) {
		
		try {
			return mapper.writeSave(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
