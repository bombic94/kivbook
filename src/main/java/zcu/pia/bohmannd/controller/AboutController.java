package zcu.pia.bohmannd.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import zcu.pia.bohmannd.model.User;
import zcu.pia.bohmannd.service.ChatService;
import zcu.pia.bohmannd.service.FriendshipService;
import zcu.pia.bohmannd.service.UserService;

@Controller
public class AboutController {

	@Autowired
    private UserService userService;
	
	@Autowired
    private ChatService chatService;
	
	@Autowired
    private FriendshipService friendshipService;
	
	final Logger logger = Logger.getLogger(HomepageController.class);
	
	@RequestMapping(value = "/about")
    public ModelAndView addItems(ModelAndView mv, HttpSession session) {
		logger.info("About Controller");
		if (session.getAttribute("USER") == null || session.getAttribute("USER").equals("")) {
			logger.info("Not logged in");
			mv.setViewName("redirect:/homepage");
		} else {
			logger.info("Logged in: " + session.getAttribute("USER"));
			
			mv = new ModelAndView("about");  
			
			User user = userService.getUserByUsername(session.getAttribute("USER").toString());
			mv.addObject("loggedUser", user);			
			mv.addObject("newFriendships", friendshipService.listPendingFriendshipByUser(user).size());
			mv.addObject("newMessages", chatService.listUnreadChatByUser(user).size());

		}
	
        return mv;
    }
}
