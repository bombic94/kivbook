package zcu.pia.bohmannd.controller;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

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
public class ProfileController {

	@Autowired
    private UserService userService;
	
	@Autowired
    private StatusService statusService;
	
	@Autowired
    private ChatService chatService;
	
	@Autowired
    private FriendshipService friendshipService;
	
	final Logger logger = Logger.getLogger(HomepageController.class);
	
	@RequestMapping(value = "/profile/{userId}")
    public ModelAndView addItems(ModelAndView mv, HttpSession session, @PathVariable Integer userId) {
		logger.info("Profile Controller");
		if (session.getAttribute("USER") == null || session.getAttribute("USER").equals("")) {
			logger.info("Not logged in");
			mv.setViewName("redirect:/homepage");
		} else {
			logger.info("Logged in: " + session.getAttribute("USER"));
			
			mv = new ModelAndView("profile");     

			User user = userService.getUser(userId);
			
			List<Friendship> friendships = friendshipService.listFriendshipByUser(user);
			
			String age = "Unknown";
			if (user.getDateofbirth() != null) {
				age = Integer.toString(Period.between(user.getDateofbirth().toLocalDate(), LocalDate.now()).getYears());
			}
			
			String gender = "Male";
			if (user.getGender().equals("f")) {
				gender = "Female";
			}
			
			logger.info("Showing user: " + user);
			mv.addObject("user", user);
			mv.addObject("userAge", age);
			mv.addObject("userGender", gender);
			mv.addObject("userFriends", friendships.size());
			
			mv.addObject("loggedUser", userService.getUserByUsername(session.getAttribute("USER").toString()));			
			mv.addObject("newFriendships", friendshipService.listFriendships().size());
			mv.addObject("newMessages", chatService.listChats().size());
			mv.addObject("newStatuses", statusService.listStatuss().size());

		}
	
        return mv;
    }
}
