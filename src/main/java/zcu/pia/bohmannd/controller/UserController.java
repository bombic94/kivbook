package zcu.pia.bohmannd.controller;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import zcu.pia.bohmannd.service.UserService;

@Controller
public class UserController {

	@Autowired
    private UserService userService;

	@RequestMapping("/user")
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		
		System.out.println("in UserController");
		ModelAndView mv = new ModelAndView("userList");		
		mv.addObject("userList", userService.list());
		
		return mv;
	}
	
}
