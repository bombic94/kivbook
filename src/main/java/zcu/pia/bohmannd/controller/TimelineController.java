package zcu.pia.bohmannd.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import zcu.pia.bohmannd.model.Chat;
import zcu.pia.bohmannd.model.Comment;
import zcu.pia.bohmannd.model.Like;
import zcu.pia.bohmannd.model.Status;
import zcu.pia.bohmannd.model.User;
import zcu.pia.bohmannd.service.ChatService;
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
			
			User user = userService.getUserByUsername(session.getAttribute("USER").toString());
			mv.addObject("loggedUser", user);
			
			List<Chat> chats = chatService.listChatByUser(user);
			mv.addObject("chats", chats);
			mv.addObject("pendingFriendships", friendshipService.listPendingFriendshipByUser(user));
			mv.addObject("usersToFriend", userService.listUsersToFriend(user));
			
			mv.addObject("statuses", statusService.listStatusesForUser(user));
			mv.addObject("userLikes", likeService.listLikesByUser(user));
			
			logger.info(statusService.listStatusesForUser(user).size());
		}
	
        return mv;
    }
	
	@RequestMapping(value = "/timeline/newStatus", method = RequestMethod.POST)
    public ModelAndView newStatus(@RequestParam("file") MultipartFile file, @RequestParam("text") String text, ModelAndView mv, HttpSession session) {
		logger.info("Timeline Controller - new status");
		if (session.getAttribute("USER") == null || session.getAttribute("USER").equals("")) {
			logger.info("Not logged in");
			mv.setViewName("redirect:/homepage");
		} else {
			logger.info("Logged in: " + session.getAttribute("USER"));
			
			mv = new ModelAndView("timeline");
			
			User user = userService.getUserByUsername(session.getAttribute("USER").toString());
			
			Status status = new Status();
			status.setStatus_text(text);
			status.setUser(user);
			
			if (!file.isEmpty()) {
				try {
					byte[] bytes = file.getBytes();
	
					// Creating the directory to store file
					String rootPath = System.getProperty("catalina.home");
					File dir = new File(rootPath + File.separator + "img");
					if (!dir.exists())
						dir.mkdirs();
					
					// Create the file on server
					File serverFile = new File(dir.getAbsolutePath() + File.separator + user.getId() + "-status-" + file.hashCode() + ".png");
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();
	
					logger.info("Server File Location=" + serverFile.getAbsolutePath());
						
					status.setPhoto(user.getId() + "-status-" + file.hashCode() + ".png");
					
				} catch (Exception e) {
					logger.info("Changes NOT saved");
		        	mv.setViewName("redirect:/settings");
				}
			}			
			
			logger.info("Saving new status: " + status.toString());
			
			statusService.insertStatus(status);
			
			mv.setViewName("redirect:/timeline");
		}
	
        return mv;
    }
	
	@RequestMapping(value = "/timeline/newComment/{statusId}")
    public ModelAndView newComment(ModelAndView mv, HttpSession session, @RequestParam("comment_text") String comment_text, @PathVariable Integer statusId) {
		logger.info("Timeline Controller - new comment");
		if (session.getAttribute("USER") == null || session.getAttribute("USER").equals("")) {
			logger.info("Not logged in");
			mv.setViewName("redirect:/homepage");
		} else {
			logger.info("Logged in: " + session.getAttribute("USER"));
			
			mv = new ModelAndView("timeline");
			
			User user = userService.getUserByUsername(session.getAttribute("USER").toString());
			Comment comment = new Comment();
			comment.setComment_text(comment_text);
			comment.setStatus(statusService.getStatus(statusId));
			comment.setUser(user);

			logger.info("Saving new comment: " + comment.toString());
			
			commentService.insertComment(comment);
			
			mv.setViewName("redirect:/timeline");
		}
	
        return mv;
    }

	@RequestMapping(value = "/timeline/like/{statusId}")
    public ModelAndView like(ModelAndView mv, HttpSession session, @PathVariable Integer statusId) {
		logger.info("Timeline Controller - like");
		if (session.getAttribute("USER") == null || session.getAttribute("USER").equals("")) {
			logger.info("Not logged in");
			mv.setViewName("redirect:/homepage");
		} else {
			logger.info("Logged in: " + session.getAttribute("USER"));
			
			mv = new ModelAndView("timeline");
			
			User user = userService.getUserByUsername(session.getAttribute("USER").toString());
			Like like = new Like();
			like.setStatus(statusService.getStatus(statusId));
			like.setUser(user);
			
			if (likeService.isLiked(like) != null) {
				like = likeService.isLiked(like);
				logger.info("Status was liked by this user, removing like: " + like.toString());
				likeService.deleteLike(like);
			} else {
				logger.info("Saving new like: " + like.toString());
				likeService.insertLike(like);
			}
			
			mv.setViewName("redirect:/timeline");
		}
	
        return mv;
    }
}
