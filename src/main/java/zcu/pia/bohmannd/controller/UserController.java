package zcu.pia.bohmannd.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import zcu.pia.bohmannd.model.*;

public class UserController {
	
	private UserJdbcTemplate userJdbcTemplate;
	
	public UserJdbcTemplate getUserJdbcTemplate() {
		return userJdbcTemplate;
	}

	public void setUserJdbcTemplate(UserJdbcTemplate userJdbcTemplate) {
		this.userJdbcTemplate = userJdbcTemplate;
	}

	@RequestMapping("/user")
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		
		System.out.println("in UserController");
		ModelAndView mv = new ModelAndView("userList");		
		mv.addObject("userList", userJdbcTemplate.listUsers());
		
		return mv;
	}
	
}
