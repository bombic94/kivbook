package zcu.pia.bohmannd.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import zcu.pia.bohmannd.model.User;
import zcu.pia.bohmannd.service.ChatService;
import zcu.pia.bohmannd.service.FriendshipService;
import zcu.pia.bohmannd.service.StatusService;
import zcu.pia.bohmannd.service.UserService;

@Controller
public class SettingsController {

	@Autowired
    private UserService userService;
	
	@Autowired
    private StatusService statusService;
	
	@Autowired
    private ChatService chatService;
	
	@Autowired
    private FriendshipService friendshipService;
	
	final Logger logger = Logger.getLogger(HomepageController.class);
	
	@RequestMapping(value = "/settings")
    public ModelAndView addItems(ModelAndView mv, HttpSession session) {
		logger.info("Settings Controller");
		if (session.getAttribute("USER") == null || session.getAttribute("USER").equals("")) {
			logger.info("Not logged in");
			mv.setViewName("redirect:/homepage");
		} else {
			logger.info("Logged in: " + session.getAttribute("USER"));
			
			mv = new ModelAndView("settings");     
		
			User user = userService.getUserByUsername(session.getAttribute("USER").toString());
			mv.addObject("loggedUser", user);
			mv.addObject("user", user);
			mv.addObject("datepickerDefault", user.getDateofbirth().getTime());
			mv.addObject("newFriendships", friendshipService.listFriendships().size());
			mv.addObject("newMessages", chatService.listChats().size());
			mv.addObject("newStatuses", statusService.listStatuss().size());
		}
	
        return mv;
    }
	
	@RequestMapping(value = "/settings/changeSettings")
    public ModelAndView changeSettings(@ModelAttribute User user, ModelAndView mv, HttpSession session, RedirectAttributes redirectAttributes) {
		logger.info("Settings - changeSettings Controller");
		if (session.getAttribute("USER") == null || session.getAttribute("USER").equals("")) {
			logger.info("Not logged in");
			mv.setViewName("redirect:/homepage");
		} else {
			logger.info("Logged in: " + session.getAttribute("USER"));
			
			logger.info("Trying to change user settings: " + user.toString());
			userService.changeSettings(user);
			
			logger.info("Changes saved: " + user.toString());
			mv = new ModelAndView("settings");     				
			redirectAttributes.addFlashAttribute("message", "Changes saved");
        	mv.setViewName("redirect:/settings");			
			
		}
	
        return mv;
    }
	
	@RequestMapping(value = "/settings/changePassword")
    public ModelAndView changePassword(@ModelAttribute User user, ModelAndView mv, HttpSession session, RedirectAttributes redirectAttributes) {
		logger.info("Settings - changeSettings Controller");
		if (session.getAttribute("USER") == null || session.getAttribute("USER").equals("")) {
			logger.info("Not logged in");
			mv.setViewName("redirect:/homepage");
		} else {
			logger.info("Logged in: " + session.getAttribute("USER"));
			
			logger.info("Trying to change user password: " + user.toString());
			userService.changePassword(user);
			
			logger.info("Changes saved: " + user.toString());
			mv = new ModelAndView("settings");     				
			redirectAttributes.addFlashAttribute("message", "Changes saved");
        	mv.setViewName("redirect:/settings");			
			
		}
	
        return mv;
    }
	
	@RequestMapping(value = "/settings/delete")
    public ModelAndView deleteUser(@ModelAttribute User user, ModelAndView mv, HttpSession session, RedirectAttributes redirectAttributes) {
		logger.info("Settings - changeSettings Controller");
		if (session.getAttribute("USER") == null || session.getAttribute("USER").equals("")) {
			logger.info("Not logged in");
			mv.setViewName("redirect:/homepage");
		} else {
			logger.info("Logged in: " + session.getAttribute("USER"));
			
			logger.info("Trying to delete user: " + user.toString());
			userService.deleteUser(user);
			
			logger.info("User deleted: " + user.toString());
			
			redirectAttributes.addFlashAttribute("message", "User successfully deleted");
        	mv.setViewName("redirect:/homepage");					
		}
	
        return mv;
    }
}
