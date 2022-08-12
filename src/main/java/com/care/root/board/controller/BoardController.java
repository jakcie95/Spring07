package com.care.root.board.controller;


import java.io.File;
import java.io.FileInputStream;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.care.root.board.service.BoardFileService;
import com.care.root.board.service.BoardService;

@Controller
@RequestMapping("board")
public class BoardController{
	@Autowired BoardService bs;
	
	@GetMapping("boardAllList")
	public String boardAllList(Model model, 
								//페이징처리
								@RequestParam(value="num", required = false, defaultValue="1") int num) {
		
		bs.boardAllList(model, num);
		return "board/boardAllList";
	}
	@GetMapping("writeForm")
	public String writeForm() {
		System.out.println("writeForm......");
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
	public String contentView(@RequestParam("writeNo") int writeNo, Model model) {
		bs.contentView(writeNo, model);
		return "board/contentView";
	}
	@GetMapping("download")
	public void download(@RequestParam("imageFileName") String imgFileName,
						 HttpServletResponse response) 
										throws Exception{
		System.out.println("file : "+imgFileName);
		response.addHeader("Content-disposition", "attachment; fileName="+imgFileName);
		File f = new File(BoardFileService.IMAGE_REPO+"/"+imgFileName);
		FileInputStream in = new FileInputStream(f);
		FileCopyUtils.copy(in, response.getOutputStream());
		in.close();
	}
	@GetMapping("delete")
	public void delete(int writeNo, String imageFileName, 
						HttpServletRequest request,
						HttpServletResponse response) throws Exception {
		
		String message = bs.delete(writeNo, imageFileName, request);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(message);
	}
	@GetMapping("modifyForm")
	public String modifyForm(int writeNo, Model model) {
		bs.modifyForm(writeNo, model);
		return "board/modifyForm";
	}
	@PostMapping("modify")
	public void modify(MultipartHttpServletRequest mul,
						HttpServletRequest request,
						HttpServletResponse response) throws Exception {
		String message = bs.modify(mul, request);
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(message);
	}
}








