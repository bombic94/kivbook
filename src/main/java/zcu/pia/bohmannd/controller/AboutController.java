package zcu.pia.bohmannd.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import zcu.pia.bohmannd.model.Chat;
import zcu.pia.bohmannd.model.Chat_Line;
import zcu.pia.bohmannd.model.Comment;
import zcu.pia.bohmannd.model.Friendship;
import zcu.pia.bohmannd.model.Like;
import zcu.pia.bohmannd.model.Status;
import zcu.pia.bohmannd.model.User;
import zcu.pia.bohmannd.service.ChatService;
import zcu.pia.bohmannd.service.Chat_LineService;
import zcu.pia.bohmannd.service.CommentService;
import zcu.pia.bohmannd.service.FriendshipService;
import zcu.pia.bohmannd.service.LikeService;
import zcu.pia.bohmannd.service.StatusService;
import zcu.pia.bohmannd.service.UserService;

@Controller
public class AboutController {

	@Autowired
    private UserService userService;
	
	@Autowired
    private StatusService statusService;
	
	@Autowired
    private LikeService likeService;
	
	@Autowired
    private ChatService chatService;
	
	@Autowired
    private Chat_LineService chat_lineService;
	
	@Autowired
    private FriendshipService friendshipService;
	
	@Autowired
    private CommentService commentService;
	
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
			
	        mv.addObject("userCount", userService.listUsers().size());			
	        mv.addObject("loggedUser", userService.getUserByUsername(session.getAttribute("USER").toString()));	        
			mv.addObject("newFriendships", friendshipService.listFriendships().size());
			mv.addObject("newMessages", chatService.listChats().size());
			mv.addObject("newStatuses", statusService.listStatuss().size());

		}
	
        return mv;
    }
}
