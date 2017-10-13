package zcu.pia.bohmannd.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import zcu.pia.bohmannd.model.User;
import zcu.pia.bohmannd.service.UserService;

@Controller
public class HomepageController {

	@Autowired
    private UserService userService;
	
	final Logger logger = Logger.getLogger(HomepageController.class);
	
	@RequestMapping(value = "/homepage")
    public ModelAndView addItems(ModelAndView mv, HttpSession session, @CookieValue(value = "user", defaultValue = "") String userCookie) {
		logger.info("Homepage Controller");
		if (session.getAttribute("USER") == null || session.getAttribute("USER").equals("")) {
			if (userCookie.equals("")) {
				mv = new ModelAndView("homepage");	
				
		        mv.addObject("user", new User());
		        int userCount = userService.listUsers().size();
		        mv.addObject("userCount", userCount);
			} else {
				logger.info("Cookie found: " + userCookie);
				session.setAttribute("USER", userCookie);
				mv.setViewName("redirect:/timeline");
			}
		} else {
			logger.info("Already logged in: " + session.getAttribute("USER"));
			mv.setViewName("redirect:/timeline");
		}
        return mv;
    }
	
	@RequestMapping(value = "/homepage/register", method = RequestMethod.POST)
	public ModelAndView register(@ModelAttribute User user, ModelAndView mv, RedirectAttributes redirectAttributes, HttpSession session, @CookieValue(value = "user", defaultValue = "") String userCookie) {
		logger.info("Homepage - registration controller");
		if (session.getAttribute("USER") == null || session.getAttribute("USER").equals("")) {		
			if (userCookie.equals("")) {
				user.setPhoto("default-profile-picture.jpg");
				logger.info("Trying to register: " + user.toString());		
				
		        boolean registered = userService.register(user);
		        
		        if (registered) {
		        	logger.info("User registered: " + user.toString());
		            mv = new ModelAndView("homepage");
		            redirectAttributes.addFlashAttribute("message", "Registration successfull, now you can log in");
		            mv.setViewName("redirect:/homepage");
		        } else {
		        	logger.info("User registered: " + user.toString());
		            mv = new ModelAndView("homepage");
		            redirectAttributes.addFlashAttribute("message", "User already registered, use different username");    
		            mv.setViewName("redirect:/homepage");
		        }
			} else {
				logger.info("Cookie found: " + userCookie);
				session.setAttribute("USER", userCookie);
				mv.setViewName("redirect:/timeline");
			}
		} else {
			logger.info("Already logged in: " + session.getAttribute("USER"));
			mv.setViewName("redirect:/timeline");
		}
        return mv;
    }
	
	@RequestMapping(value = "/homepage/login", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute User user, HttpServletResponse response, ModelAndView mv, HttpSession session, RedirectAttributes redirectAttributes, @CookieValue(value = "user", defaultValue = "") String userCookie) {
		logger.info("Homepage - login Controller");
		if (session.getAttribute("USER") == null || session.getAttribute("USER").equals("")) {			
			if (userCookie.equals("")) {
				logger.info("Trying to login: " + user.toString());
				
		        boolean validated = userService.validateUser(user);
		
		        if (validated) {
		        	logger.info("User validated: " + user.toString());
		        	session.setAttribute("USER", user.getUsername());
		        	if (user.isRememberMe()) {
						logger.info("Remember me checked, setting cookies");
						Cookie cookie = new Cookie("user", user.getUsername());
						cookie.setMaxAge(365 * 24 * 60 * 60);
						response.addCookie(cookie);
					}
		        	mv = new ModelAndView("timeline");
		        	mv.setViewName("redirect:/timeline");
		        	logger.info("Redirecting to timeline");
		        } else {
		        	logger.info("User NOT validated: " + user.toString());
		        	mv = new ModelAndView("homepage");
		        	redirectAttributes.addFlashAttribute("message", "Wrong name or password");
		        	mv.setViewName("redirect:/homepage");
		        }
			} else {
				logger.info("Cookie found: " + userCookie);
				session.setAttribute("USER", userCookie);
				mv.setViewName("redirect:/timeline");
			}
		} else {
			logger.info("Already logged in: " + session.getAttribute("USER"));
			mv.setViewName("redirect:/timeline");
		}
        return mv;
    }
	
	@RequestMapping(value = "/homepage/logout")
    public ModelAndView logout(ModelAndView mv, HttpSession session, HttpServletResponse response, @CookieValue(value = "user", defaultValue = "") String userCookie) {
		logger.info("Homepage Controller - after logout");
		mv = new ModelAndView("homepage");
		if (session != null) {
		    session.invalidate();
		    logger.info("Invalidated http session");
		}	
		if (! userCookie.equals("")) {
			Cookie cookie = new Cookie("user", userCookie);
			cookie.setMaxAge(0);
			response.addCookie(cookie);
			logger.info("Removed cookie");
		}
		mv.setViewName("redirect:/homepage");
        
        return mv;
    }
}
