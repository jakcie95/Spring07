package com.care.root.board.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.care.root.board.dto.BoardDTO;
import com.care.root.board.service.BoardService;



@Controller
@RequestMapping("board")
public class BoardController{
	@Autowired BoardService bs;
	
	@GetMapping("boardAllList")
	public String boardAllList(Model model) {
		bs.boardAllList(model);
		return "board/boardAllList";
	}
	@GetMapping("writeForm")
	public String writeForm() {
		return "board/writeForm";
	}
	@PostMapping("writeSave")
	public String write(BoardDTO dto) {
		int result =bs.writeSave(dto);
		if(result == 1)
			return "redirect:boardAllList";
			return "redirect:writeForm";
	}
	
}
