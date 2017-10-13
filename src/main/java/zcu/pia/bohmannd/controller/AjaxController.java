package zcu.pia.bohmannd.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import zcu.pia.bohmannd.model.User;
import zcu.pia.bohmannd.service.ChatService;
import zcu.pia.bohmannd.service.FriendshipService;
import zcu.pia.bohmannd.service.UserService;

@Controller
public class AjaxController {

	@Autowired
	private UserService userService;

	@Autowired
	private ChatService chatService;

	@Autowired
	private FriendshipService friendshipService;

	final Logger logger = Logger.getLogger(AjaxController.class);

	@RequestMapping(value = "/ajaxNotif", method = RequestMethod.GET)
	@ResponseBody
	public List<Integer> addItems(HttpSession session, HttpServletResponse response) {
		List<Integer> notif = new ArrayList<Integer>();

		if (session.getAttribute("USER") == null || session.getAttribute("USER").equals("")) {
			notif.add(0);
			notif.add(0);
		} else {
			User user = userService.getUserByUsername(session.getAttribute("USER").toString());

			notif.add(chatService.listUnreadChatByUser(user).size());
			notif.add(friendshipService.listPendingFriendshipByUser(user).size());
			response.setContentType("application/json");
			logger.info("Ajax notifications - Chats: " + notif.get(0) + ", Friendships: " + notif.get(1));
		}

		return notif;
	}
}
