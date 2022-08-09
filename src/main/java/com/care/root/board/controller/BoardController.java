package com.care.root.board.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.care.root.board.service.BoardFileService;
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
	public void writeSave(MultipartHttpServletRequest mul,
						HttpServletResponse response,
						HttpServletRequest request) throws Exception {
		
		
		String message = bs.writeSave(mul, request);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(message);
	}
	@GetMapping("contentView")
	public String contentView(Model model, String title) {
		bs.contentView(model, title);
		return "board/contentView";
	}
	@GetMapping("download")
	public void download(String file,
						 HttpServletResponse response) 
										throws Exception{
		System.out.println("file : "+file);
		response.addHeader("Content-disposition", "attachment; fileName="+file);
		File f = new File(BoardFileService.IMAGE_REPO+"/"+file);
		FileInputStream in = new FileInputStream(f);
		FileCopyUtils.copy(in, response.getOutputStream());
		in.close();
	}
	@GetMapping("delete")
	public void delete(String file, String title,
						HttpServletRequest request,
						HttpServletResponse response) throws Exception {
		
		String message = bs.delete(file,title, request);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(message);
	}
}
