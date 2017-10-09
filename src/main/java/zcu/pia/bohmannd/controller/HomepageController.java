package zcu.pia.bohmannd.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    public ModelAndView addItems(ModelAndView mv, HttpSession session) {
		logger.info("Homepage Controller");
		mv = new ModelAndView("homepage");
		//after logout
		if (session != null) {
		    session.invalidate();
		}
		
		
        mv.addObject("user", new User());
        int userCount = userService.listUsers().size();
        mv.addObject("userCount", userCount);
        
        
        return mv;
    }
	
	@RequestMapping(value = "/homepage/register", method = RequestMethod.POST)
	public ModelAndView register(@ModelAttribute User user, ModelAndView mv, RedirectAttributes redirectAttributes) {
		logger.info("Registration controller");
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
        return mv;
    }
	
	@RequestMapping(value = "/homepage/login", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute User user, ModelAndView mv, HttpSession session, RedirectAttributes redirectAttributes) {
		logger.info("Login Controller");
		logger.info("Trying to login: " + user.toString());
		
        boolean validated = userService.validateUser(user);

        if (validated) {
        	logger.info("User validated: " + user.toString());
        	session.setAttribute("USER", user.getUsername());
        	mv = new ModelAndView("timeline");
        	redirectAttributes.addFlashAttribute("logged_user", userService.getUserByUsername(user.getUsername()));
        	mv.setViewName("redirect:/timeline");
        	logger.info("Redirecting to timeline");
        } else {
        	logger.info("User NOT validated: " + user.toString());
        	mv = new ModelAndView("homepage");
        	redirectAttributes.addFlashAttribute("message", "Wrong name or password");
        	mv.setViewName("redirect:/homepage");
        }
        return mv;
    }
}
