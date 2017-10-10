package zcu.pia.bohmannd.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import zcu.pia.bohmannd.model.Friendship;
import zcu.pia.bohmannd.model.User;
import zcu.pia.bohmannd.service.ChatService;
import zcu.pia.bohmannd.service.FriendshipService;
import zcu.pia.bohmannd.service.StatusService;
import zcu.pia.bohmannd.service.UserService;

@Controller
public class UsersController {

	@Autowired
    private UserService userService;
	
	@Autowired
    private StatusService statusService;
	
	@Autowired
    private ChatService chatService;
	
	@Autowired
    private FriendshipService friendshipService;
	
	final Logger logger = Logger.getLogger(HomepageController.class);
	
	@RequestMapping(value = "/users")
    public ModelAndView addItems(ModelAndView mv, HttpSession session) {
		logger.info("Users Controller");
		if (session.getAttribute("USER") == null || session.getAttribute("USER").equals("")) {
			logger.info("Not logged in");
			mv.setViewName("redirect:/homepage");
		} else {
			logger.info("Logged in: " + session.getAttribute("USER"));
			
			mv = new ModelAndView("users");    
			
			User user = userService.getUserByUsername(session.getAttribute("USER").toString());
			mv.addObject("loggedUser", user);			
			mv.addObject("newFriendships", friendshipService.listPendingFriendshipByUser(user).size());
			mv.addObject("newMessages", chatService.listChats().size());
			mv.addObject("newStatuses", statusService.listStatuss().size());
			
			
			mv.addObject("friendships", friendshipService.listFriendshipByUser(user));
			mv.addObject("pendingFriendships", friendshipService.listPendingFriendshipByUser(user));
			mv.addObject("usersToFriend", userService.listUsersToFriend(user));
			
		}
	
        return mv;
    }
	
	@RequestMapping(value = "/users/deleteFriend/{friendshipId}")
    public ModelAndView addFriend(ModelAndView mv, HttpSession session, @PathVariable Integer friendshipId) {
		logger.info("Profile Controller");
		if (session.getAttribute("USER") == null || session.getAttribute("USER").equals("")) {
			logger.info("Not logged in");
			mv.setViewName("redirect:/homepage");
		} else {
			logger.info("Logged in: " + session.getAttribute("USER"));
			
			mv = new ModelAndView("users");     

			Friendship friendship = friendshipService.getFriendship(friendshipId);
			logger.info("Trying to delete friendship: " + friendship.toString());
			friendshipService.deleteFriendship(friendship);
			
			logger.info("Friendship deleted: " + friendship.toString());
			mv.setViewName("redirect:/users");
		}
	
        return mv;
    }
	
	@RequestMapping(value = "/users/acceptFriend/{friendshipId}")
    public ModelAndView acceptFriend(ModelAndView mv, HttpSession session, @PathVariable Integer friendshipId) {
		logger.info("Profile Controller");
		if (session.getAttribute("USER") == null || session.getAttribute("USER").equals("")) {
			logger.info("Not logged in");
			mv.setViewName("redirect:/homepage");
		} else {
			logger.info("Logged in: " + session.getAttribute("USER"));
			
			mv = new ModelAndView("users");     
			
			Friendship friendship = friendshipService.getFriendship(friendshipId);
			logger.info("Trying to accept friendship: " + friendship.toString());
			friendshipService.acceptFriendship(friendship);
			
			logger.info("Friendship accepted: " + friendship.toString());
			
			mv.setViewName("redirect:/users");
		}
	
        return mv;
    }
	
	@RequestMapping(value = "/users/addFriend/{friendId}")
    public ModelAndView deleteFriend(ModelAndView mv, HttpSession session, @PathVariable Integer friendId) {
		logger.info("Profile Controller");
		if (session.getAttribute("USER") == null || session.getAttribute("USER").equals("")) {
			logger.info("Not logged in");
			mv.setViewName("redirect:/homepage");
		} else {
			logger.info("Logged in: " + session.getAttribute("USER"));
			
			mv = new ModelAndView("users");     
			
			
			User user = userService.getUserByUsername(session.getAttribute("USER").toString());
			User friend = userService.getUser(friendId);
			Friendship friendship = new Friendship();
			friendship.setUser1(user);
			friendship.setUser2(friend);			
			friendship.setAccepted(false);
			logger.info("Trying to create new friendship: " + friendship.toString());
			friendshipService.insertFriendship(friendship);
			logger.info("New friendship request sent: " + friendship.toString());
			mv.setViewName("redirect:/users");
		}
	
        return mv;
    }
}
