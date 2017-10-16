package zcu.pia.bohmannd.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import zcu.pia.bohmannd.model.Chat;
import zcu.pia.bohmannd.model.Chat_Line;
import zcu.pia.bohmannd.model.User;
import zcu.pia.bohmannd.service.ChatService;
import zcu.pia.bohmannd.service.Chat_LineService;
import zcu.pia.bohmannd.service.UserService;

@Controller
public class MessagesController {

	@Autowired
	private UserService userService;

	@Autowired
	private ChatService chatService;

	@Autowired
	private Chat_LineService chat_lineService;

	final Logger logger = Logger.getLogger(MessagesController.class);

	@RequestMapping(value = "/messages")
	public ModelAndView addItems(ModelAndView mv, HttpSession session) {
		logger.info("Messages Controller");
		if (session.getAttribute("USER") == null || session.getAttribute("USER").equals("")) {
			logger.info("Not logged in");
			mv.setViewName("redirect:/homepage");
		} else {
			logger.info("Logged in: " + session.getAttribute("USER"));

			mv = new ModelAndView("messages");

			User user = userService.getUserByUsername(session.getAttribute("USER").toString());
			mv.addObject("loggedUser", user);

			List<Chat> chats = chatService.listChatByUser(user);
			mv.addObject("chats", chats);

			if (chats.size() > 0) {
				if (userService.getActiveChat(user) == null) {
					userService.setActiveChat(user, chats.get(0));
					logger.info(
							"Active chat not selected, selecting most recent chat: " + userService.getActiveChat(user));
				} else {
					logger.info("Active chat selected: " + userService.getActiveChat(user).toString());
				}
				mv.addObject("activeChat", chat_lineService.listChat_LinesByChat(userService.getActiveChat(user)));
				mv.addObject("selectedChat", userService.getActiveChat(user));
			}
			mv.addObject("unreadCount", chatService.listUnreadChatByUser(user).size());
			mv.addObject("usersToChat", userService.listUsersToChat(user));
			mv.addObject("chat_Line", new Chat_Line());

		}

		return mv;
	}

	@RequestMapping(value = "/messages/addChat/{friendId}")
	public ModelAndView addChat(ModelAndView mv, HttpSession session, @PathVariable Integer friendId,
			RedirectAttributes redirectAttributes) {
		logger.info("Profile Controller");
		if (session.getAttribute("USER") == null || session.getAttribute("USER").equals("")) {
			logger.info("Not logged in");
			mv.setViewName("redirect:/homepage");
		} else {
			logger.info("Logged in: " + session.getAttribute("USER"));

			mv = new ModelAndView("messages");

			User user = userService.getUserByUsername(session.getAttribute("USER").toString());
			User friend = userService.getUser(friendId);
			Chat chat = new Chat();
			chat.setUser1(user);
			chat.setUser2(friend);
			chat.setSeen(true);
			logger.info("Trying to create new chat: " + chat.toString());
			chatService.insertChat(chat);
			logger.info("Chat created, setting active: " + chat.toString());
			user.setActiveChat(chat);

			mv.setViewName("redirect:/messages");
		}

		return mv;
	}

	@RequestMapping(value = "/messages/setChat/{chatId}")
	public ModelAndView setChat(ModelAndView mv, HttpSession session, @PathVariable Integer chatId,
			RedirectAttributes redirectAttributes) {
		logger.info("Profile Controller");
		if (session.getAttribute("USER") == null || session.getAttribute("USER").equals("")) {
			logger.info("Not logged in");
			mv.setViewName("redirect:/homepage");
		} else {
			logger.info("Logged in: " + session.getAttribute("USER"));

			mv = new ModelAndView("messages");

			User user = userService.getUserByUsername(session.getAttribute("USER").toString());
			Chat chat = chatService.getChat(chatId);
			chat.setChat_Lines(chat_lineService.listChat_LinesByChat(chat));

			logger.info("Setting active chat: " + chat.toString());
			userService.setActiveChat(user, chat);
			chatService.readChat(chat, user);

			mv.setViewName("redirect:/messages");
		}

		return mv;
	}

	@RequestMapping(value = "/messages/newMessage", method = RequestMethod.POST)
	public ModelAndView newMessage(@ModelAttribute Chat_Line chat_Line, ModelAndView mv, HttpSession session,
			RedirectAttributes redirectAttributes) {
		logger.info("Profile Controller");
		if (session.getAttribute("USER") == null || session.getAttribute("USER").equals("")) {
			logger.info("Not logged in");
			mv.setViewName("redirect:/homepage");
		} else {
			logger.info("Logged in: " + session.getAttribute("USER"));

			mv = new ModelAndView("messages");

			User user = userService.getUserByUsername(session.getAttribute("USER").toString());
			chat_Line.setChat(userService.getActiveChat(user));
			chat_Line.setSender(user);

			logger.info("Saving new message: " + chat_Line.toString());
			chat_lineService.insertChat_Line(chat_Line);

			mv.setViewName("redirect:/messages");
		}

		return mv;
	}
}
