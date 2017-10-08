package zcu.pia.bohmannd.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public ModelAndView addItems(Model model) {
		logger.info("Homepage Controller");
		ModelAndView mv = new ModelAndView("homepage");
		
        mv.addObject("user", new User());
        int userCount = userService.listUsers().size();
        mv.addObject("userCount", userCount);
        
        
        return mv;
    }
	
	@RequestMapping(value = "/homepage/register", method = RequestMethod.POST)
	public ModelAndView register(@ModelAttribute User user, ModelAndView mv, RedirectAttributes redirectAttributes) {
		logger.info("Registration controller");
		logger.info("Trying to register: " + user.getUsername());
		
        String response = userService.register(user);
        
        logger.info("User registered: " + user.getUsername());
        mv = new ModelAndView("homepage");
        redirectAttributes.addFlashAttribute("message", response);
        //mv.addObject("message", response);
        mv.setViewName("redirect:/homepage");
        return mv;
    }
	
	@RequestMapping(value = "/homepage/login", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute User user, ModelAndView mv, RedirectAttributes redirectAttributes) {
		logger.info("Login Controller");
		logger.info("Trying to login: " + user.getUsername());
		
        boolean validated = userService.validateUser(user);

        if (validated) {
        	logger.info("User validated: " + user.getUsername());
        	mv = new ModelAndView("timeline");
        	mv.setViewName("redirect:/timeline");
        	logger.info("Redirecting to timeline");
        } else {
        	logger.info("User NOT validated: " + user.getUsername());
        	mv = new ModelAndView("homepage");
        	redirectAttributes.addFlashAttribute("message", "Wrong name or password");
        	//mv.addObject("message", "Wrong name or password");
        	mv.setViewName("redirect:/homepage");
        }
        return mv;
    }
}
