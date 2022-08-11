package com.care.root.board.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.care.root.board.dto.BoardRepDTO;
import com.care.root.board.service.BoardService;
import com.care.root.common.SessionName;

@RestController//데이터를 수신하는 역할
@RequestMapping("board")
public class BoardRepController implements SessionName{
	@Autowired BoardService bs;
	
	
	
	@PostMapping(value = "addReply",
				produces = "application/json;charset=utf-8")
	public void addReply(//반드시 명시해야 데이터가 넘어옴
						@RequestBody Map<String, String>map,
						HttpSession session) {
		bs.addReply(map, (String)session.getAttribute(LOGIN));
	}
	@GetMapping(value = "replyData/{write_group}",
				produces = "application/json;charset=utf-8")
	public List<BoardRepDTO> replyData(
			@PathVariable int write_group){
		return bs.getRepList(write_group);
	}
}







