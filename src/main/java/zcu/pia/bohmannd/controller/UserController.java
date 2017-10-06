package zcu.pia.bohmannd.controller;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import zcu.pia.bohmannd.service.ChatService;
import zcu.pia.bohmannd.service.Chat_LineService;
import zcu.pia.bohmannd.service.CommentService;
import zcu.pia.bohmannd.service.FriendshipService;
import zcu.pia.bohmannd.service.LikeService;
import zcu.pia.bohmannd.service.StatusService;
import zcu.pia.bohmannd.service.UserService;

@Controller
public class UserController {

	@Autowired
    private UserService userService;
	
	@Autowired
    private StatusService statusService;
	
	@Autowired
    private CommentService commentService;
	
	@Autowired
    private LikeService likeService;
	
	@Autowired
    private FriendshipService friendshipService;
	
	@Autowired
    private ChatService chatService;
	
	@Autowired
    private Chat_LineService chat_LineService;

	@RequestMapping("/user")
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		
		System.out.println("in UserController");
		ModelAndView mv = new ModelAndView("userList");		
		mv.addObject("userList", userService.listUsers());
		mv.addObject("statusList", statusService.listStatuss());
		mv.addObject("commentList", commentService.listComments());
		mv.addObject("likeList", likeService.listLikes());
		mv.addObject("friendshipList", friendshipService.listFriendships());
		mv.addObject("chatList", chatService.listChats());
		mv.addObject("chat_LineList", chat_LineService.listChat_Lines());
		
		return mv;
	}
	
}
