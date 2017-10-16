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
import zcu.pia.bohmannd.service.Chat_LineService;
import zcu.pia.bohmannd.service.FriendshipService;
import zcu.pia.bohmannd.service.StatusService;
import zcu.pia.bohmannd.service.UserService;

@Controller
public class AjaxController {

	@Autowired
	private UserService userService;

	@Autowired
	private ChatService chatService;

	@Autowired
	private FriendshipService friendshipService;

	@Autowired
	private Chat_LineService chat_lineService;

	@Autowired
	private StatusService statusService;

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

	@RequestMapping(value = "/ajaxMessages", method = RequestMethod.GET)
	@ResponseBody
	public List<Integer> chatUpdate(HttpSession session, HttpServletResponse response) {
		List<Integer> chatCountList = new ArrayList<Integer>();
		if (session.getAttribute("USER") == null || session.getAttribute("USER").equals("")) {
			chatCountList.add(0);
			chatCountList.add(0);
		} else {

			User user = userService.getUserByUsername(session.getAttribute("USER").toString());
			chatCountList.add(chat_lineService.listChat_LinesByChat(userService.getActiveChat(user)).size());
			chatCountList.add(chatService.listUnreadChatByUser(user).size());
			logger.info("Ajax messages - Messages count: " + chatCountList);
		}
		return chatCountList;
	}

	@RequestMapping(value = "/ajaxFriends", method = RequestMethod.GET)
	@ResponseBody
	public List<Integer> friendUpdate(HttpSession session, HttpServletResponse response) {
		List<Integer> friendCountList = new ArrayList<Integer>();
		if (session.getAttribute("USER") == null || session.getAttribute("USER").equals("")) {
			friendCountList.add(0);
			friendCountList.add(0);
			friendCountList.add(0);
		} else {
			User user = userService.getUserByUsername(session.getAttribute("USER").toString());
			friendCountList.add(friendshipService.listFriendshipByUser(user).size());
			friendCountList.add(friendshipService.listPendingFriendshipByUser(user).size());
			friendCountList.add(userService.listUsersToFriend(user).size());
			logger.info("Ajax friends - friendships count: " + friendCountList);
		}
		return friendCountList;
	}

	@RequestMapping(value = "/ajaxTimeline", method = RequestMethod.GET)
	@ResponseBody
	public List<Integer> timelineUpdate(HttpSession session, HttpServletResponse response) {
		List<Integer> timelineCountList = new ArrayList<Integer>();
		if (session.getAttribute("USER") == null || session.getAttribute("USER").equals("")) {
			timelineCountList.add(0);
			timelineCountList.add(0);
			timelineCountList.add(0);
			timelineCountList.add(0);
			timelineCountList.add(0);
		} else {
			User user = userService.getUserByUsername(session.getAttribute("USER").toString());
			timelineCountList.add(chatService.listUnreadChatByUser(user).size());
			timelineCountList.add(friendshipService.listPendingFriendshipByUser(user).size());
			timelineCountList.add(userService.listUsersToFriend(user).size());
			timelineCountList.add(statusService.listStatusesForUser(user).size());
			logger.info("Ajax friends - timeline info count: " + timelineCountList);
		}
		return timelineCountList;
	}
}
