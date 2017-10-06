package zcu.pia.bohmannd.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import zcu.pia.bohmannd.model.User;
import zcu.pia.bohmannd.service.UserService;

@Controller
public class HomepageController {

	@Autowired
    private UserService userService;
	
	@RequestMapping("/homepage")
	public ModelAndView getUserCount(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		
		System.out.println("in HomepageController");
		ModelAndView mv = new ModelAndView("homepage");		
		mv.addObject("userCount", userService.listUsers().size());
		
		return mv;
		
//		User user = new User();
//		user.setFirstname(request.getParameter("name"));
//		user.setLastname(request.getParameter("surname"));
//		user.setUsername(request.getParameter("username"));
//		//user.setDateofbirth(request.getParameter("dateofbirth"));
//		user.setGender("m");
//		user.setEmail(request.getParameter("email"));
//		user.setPassword(request.getParameter("password"));
//		System.out.println(user.toString());
//		
//		userService.insertUser(user);
	}
}
