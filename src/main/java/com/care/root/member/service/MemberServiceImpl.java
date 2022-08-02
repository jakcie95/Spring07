package com.care.root.member.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.care.root.member.dto.MemberDTO;
import com.care.root.mybatis.member.MemberMapper;
@Service
public class MemberServiceImpl 
					implements MemberService{
	@Autowired
	MemberMapper mapper;
	BCryptPasswordEncoder en = new BCryptPasswordEncoder();
	
	public int user_check(HttpServletRequest req) {
		MemberDTO dto = mapper.getUser(req.getParameter("id"));
		if(dto !=null) {
				if(en.matches(req.getParameter("pw"), dto.getPw()) ||dto.getPw().equals(req.getParameter("pw"))) {
					//앞은 사용자가 제출한 비밀번호, 뒤는 암호화된 데이터베이스에서 가져온 비밀번호
					return 0;
				}
		}
		return 1;
	}
	public void memberInfo(Model model) {
		List<MemberDTO> list = mapper.memberInfo();
		model.addAttribute("list", list);
	}
	public void getUser(Model model, String id) {
		model.addAttribute("info", mapper.getUser(id));
	}
	public int register(MemberDTO dto) {
		System.out.println("암호화 전 : "+dto.getPw());
		String seq = en.encode( dto.getPw());
		System.out.println("암호화 후 : "+seq);
		
		dto.setPw( seq );
		try {
			return mapper.register(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	public void delete(String id) {
		mapper.delete(id);
	}
	public void modifyForm(String id, Model model) {
		MemberDTO dto = mapper.getUser(id);
		System.out.println(dto.getAddr());
		String[] a = dto.getAddr().split(",");
		System.out.println(a[0]);
		System.out.println(a[1]);
		System.out.println(a[2]);
		
		model.addAttribute("dto", dto);
		model.addAttribute("addrs", a);
	}
	public void modify(HttpServletRequest req, String addr) {
		MemberDTO dto =new MemberDTO();
		dto.setId(req.getParameter("id"));
		dto.setAddr(addr);
		if(req.getParameter("new_pw").contentEquals("******")) {
			dto.setPw(req.getParameter("old_pw"));
		}else {
			dto.setPw(en.encode(req.getParameter("new_pw")));
		}
	mapper.modify(dto);
	}
	public void keepLogin(String id, String cookieId) {
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("cookieId", cookieId);
		mapper.keepLogin(map);	
	}
	public MemberDTO getCookieUser(String cookie) {
		return mapper.getCookieUser( cookie );
	}
}







