package com.care.root.member.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.care.root.member.dto.MemberDTO;
import com.care.root.mybatis.member.MemberMapper;
@Service
public class MemberServiceImpl 
					implements MemberService{
	@Autowired
	MemberMapper mapper;
	
	public int user_check(HttpServletRequest req) {
		MemberDTO dto = mapper.getUser(req.getParameter("id"));
		if(dto !=null) {
				if(dto.getPw().equals(req.getParameter("pw"))) {
					return 0;
				}
		}
		return 1;
	}
	public void memberinfo(Model model) {
		model.addAttribute("list", mapper.getMember());
	}
	
}
