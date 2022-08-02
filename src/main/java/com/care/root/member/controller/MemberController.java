package com.care.root.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.care.root.common.SessionName;
import com.care.root.member.dto.MemberDTO;
import com.care.root.member.service.MemberService;

@Controller
@RequestMapping("member")
public class MemberController implements SessionName{
	@Autowired MemberService ms;
	
	@GetMapping("login")
	public String login() {
		return "member/login";
	}
	@PostMapping("user_check")
	public String user_check(HttpServletRequest req,
							 RedirectAttributes rs) {
		int result = ms.user_check(req);
		if(result == 0) {
			rs.addAttribute("id", req.getParameter("id"));
			return "redirect:successLogin";
		}
		return "redirect:login";
	}
	@GetMapping("successLogin")
	public String successLogin(String id, HttpSession session) {
		//session.setAttribute("loginUser", id);
		session.setAttribute(LOGIN, id);
		System.out.println("id : "+id);
		return "member/successLogin";
	}
	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/index";
	}
	@GetMapping("memberInfo")
	public String memberInfo(Model model) {
		ms.memberInfo(model);
		return "member/memberInfo";
	}
	@GetMapping("info")
	public String info(Model model, String id) {
		ms.getUser(model, id);
		return "member/info";
	}
	@GetMapping("register_form")
	public String register_form() {
		return "member/register";
	}
	@PostMapping("register")
	public String register(MemberDTO dto) {
		
		int result = ms.register(dto);
		if(result == 1)
			return "redirect:login";
		return "redirect:register_form";
	}
	@GetMapping("delete")
	public String delete(String id) {
		ms.delete(id);
		return "redirect:logout";
	}
	@GetMapping("modify_form")
	public String modify_form(String id, Model model) {
		ms.modifyForm( id, model );
		return "member/modify_form";
	}
	@PostMapping("modify")
	public String modify(HttpServletRequest req, String addr) {
		//System.out.println(req.getParameter("id"));
		//System.out.println(req.getParameter("new_pw"));
		//System.out.println(req.getParameter("old_pw"));
		//System.out.println(addr);
		ms.modify(req, addr);
		return "redirect:info?id="+req.getParameter("id");
	}
}






