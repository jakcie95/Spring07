package com.care.root.board.service;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.care.root.board.dto.BoardDTO;
import com.care.root.mybatis.board.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService{
	@Autowired BoardMapper mapper;
	@Autowired BoardFileService bfs;
	
	public void boardAllList(Model model) {
		model.addAttribute("boardList", mapper.boardAllList());
	}
	public String writeSave(MultipartHttpServletRequest mul,
							HttpServletRequest request) {
		BoardDTO dto = new BoardDTO();
		dto.setTitle(mul.getParameter("title"));
		dto.setContent(mul.getParameter("content"));
		dto.setId(mul.getParameter("id"));
		
		MultipartFile file = mul.getFile("image_file_name");
		if(file.getSize() != 0) {
			dto.setImageFileName(bfs.saveFile(file));
		}else {
			dto.setImageFileName("nan");
		}
		int result = 0;
		result = mapper.writeSave(dto);
		
		String msg, url;
		if( result == 1) {
			msg = "새글이 추가되었습니다!!";
			url = request.getContextPath()+"/board/boardAllList";
		}else {
			msg = "문제가 발생했습니다";
			url = request.getContextPath()+"/board/writeForm";
		}
		return bfs.getMessage(msg, url);
		
	}	
	public void contentView(Model model, String title) {
		model.addAttribute("dto", mapper.contentView(title));
	}

	public String delete(String file,String title,
						HttpServletRequest request) {
		int result = mapper.delete(title);
		String msg, url;
		if(result == 1) {
			bfs.deleteFile(file);
			msg = "글이 삭제되었습니다!!";
			url = request.getContextPath()+"/board/boardAllList";
		}else {
			msg = "문제가 발생했습니다";
			url = request.getContextPath()+"/board/boardAllList";
		}
		return bfs.getMessage(msg, url);
	}
}











