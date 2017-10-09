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
public class TimelineController {
	
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
	
	@RequestMapping(value = "/timeline")
    public ModelAndView addItems(ModelAndView mv, HttpSession session) {
		logger.info("Timeline Controller");
		if (session.getAttribute("USER") == null || session.getAttribute("USER").equals("")) {
			logger.info("Not logged in");
			mv.setViewName("redirect:/homepage");
		} else {
			logger.info("Logged in: " + session.getAttribute("USER"));
			
			mv = new ModelAndView("timeline");     
			mv.addObject("comment", new Comment());
			mv.addObject("friendship", new Friendship());
			mv.addObject("chat_line", new Chat_Line());
			mv.addObject("chat", new Chat());
			mv.addObject("like", new Like());
			mv.addObject("status", new Status());
			mv.addObject("user", new User());
			
			mv.addObject("newFriendships", friendshipService.listFriendships().size());
			mv.addObject("newMessages", chatService.listChats().size());
			mv.addObject("newStatuses", statusService.listStatuss().size());
			
			mv.addObject("friendships", friendshipService.listFriendships());
			mv.addObject("messages", chatService.listChats());
			mv.addObject("statuses", statusService.listStatuss());
		}
		
		
		
		
        return mv;
    }
}
