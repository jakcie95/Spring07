package com.care.root.board.service;


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
	private void upHit(int writeNo) {
		mapper.upHit(writeNo);
	}
	public void contentView(int writeNo, Model model) {
		upHit(writeNo);
		model.addAttribute("dto", mapper.contentView(writeNo));
	}

	public String delete(int writeNo, String imageFileName,
						HttpServletRequest request) {
		int result = mapper.delete(writeNo);
		String msg, url;
		if(result == 1) {
			bfs.deleteImage(imageFileName);
			msg = "글이 삭제되었습니다!!";
			url = request.getContextPath()+"/board/boardAllList";
		}else {
			msg = "문제가 발생했습니다";
			url = request.getContextPath()+"/board/boardAllList";
		}
		return bfs.getMessage(msg, url);
	}
	
	public void modifyForm(int writeNo,Model model) {
		model.addAttribute("dto", mapper.contentView(writeNo));
	}
	
	public String modify(MultipartHttpServletRequest mul,
							HttpServletRequest request) {
		BoardDTO dto = new BoardDTO();
		dto.setWriteNo(Integer.parseInt(mul.getParameter("writeNo")));
		dto.setTitle(mul.getParameter("title"));
		dto.setContent(mul.getParameter("content"));
		
		MultipartFile file = mul.getFile("imageFileName");
		if(file.getSize() != 0) {
			dto.setImageFileName(bfs.saveFile(file));
			bfs.deleteImage(mul.getParameter("originFileName"));
			//이미지 변경됨
		}else {
			dto.setImageFileName(mul.getParameter("originFileName"));
			//이미지 변경 안됨
		}
		int result = mapper.modify(dto);
		String msg, url;
		if(result == 1) {
			msg="성공적으로 수정되었습니다";
			url= request.getContextPath()+"/board/contentView?writeNo="+dto.getWriteNo();
		}else {
			msg = "수정 중 문제 발생!!!";
			url = request.getContextPath()+"board/modifyForm?writeNo="+dto.getWriteNo();
		}
		return bfs.getMessage(msg, url);
	}
}











