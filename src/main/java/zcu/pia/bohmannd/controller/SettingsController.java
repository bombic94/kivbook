package zcu.pia.bohmannd.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import zcu.pia.bohmannd.model.User;
import zcu.pia.bohmannd.service.UserService;

@Controller
public class SettingsController {

	@Autowired
	private UserService userService;

	final Logger logger = Logger.getLogger(HomepageController.class);

	@RequestMapping(value = "/settings")
	public ModelAndView addItems(ModelAndView mv, HttpSession session) {
		logger.info("Settings Controller");
		if (session.getAttribute("USER") == null || session.getAttribute("USER").equals("")) {
			logger.info("Not logged in");
			mv.setViewName("redirect:/homepage");
		} else {
			logger.info("Logged in: " + session.getAttribute("USER"));

			mv = new ModelAndView("settings");

			User user = userService.getUserByUsername(session.getAttribute("USER").toString());
			mv.addObject("loggedUser", user);

			mv.addObject("user", user);
			mv.addObject("datepickerDefault", user.getDateofbirth().getTime());
		}

		return mv;
	}

	/**
	 * Upload single file using Spring Controller
	 */
	@RequestMapping(value = "/settings/changePicture", method = RequestMethod.POST)
	public ModelAndView changePicture(@RequestParam("file") MultipartFile file, @RequestParam("id") Integer id,
			ModelAndView mv, HttpSession session, RedirectAttributes redirectAttributes) {
		logger.info("Settings - changeSettings Controller");
		if (session.getAttribute("USER") == null || session.getAttribute("USER").equals("")) {
			logger.info("Not logged in");
			mv.setViewName("redirect:/homepage");
		} else {
			User user = userService.getUser(id);
			if (!file.isEmpty()) {
				try {
					byte[] bytes = file.getBytes();

					// Creating the directory to store file
					String rootPath = System.getProperty("catalina.home");
					File dir = new File(rootPath + File.separator + "img");
					if (!dir.exists())
						dir.mkdirs();

					// Create the file on server
					File serverFile = new File(
							dir.getAbsolutePath() + File.separator + id + "-profile-" + file.hashCode() + ".png");
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();

					logger.info("Server File Location=" + serverFile.getAbsolutePath());

					user.setPhoto(id + "-profile-" + file.hashCode() + ".png");
					userService.changePhoto(user);

					logger.info("Changes saved: " + user.toString());
					mv = new ModelAndView("settings");
					redirectAttributes.addFlashAttribute("message", "Changes saved");
					mv.setViewName("redirect:/settings");
				} catch (Exception e) {
					logger.info("Changes NOT saved: " + user.toString());
					mv = new ModelAndView("settings");
					redirectAttributes.addFlashAttribute("message", "Something went wrong");
					mv.setViewName("redirect:/settings");
				}
			} else {
				logger.info("Changes NOT saved: " + user.toString());
				mv = new ModelAndView("settings");
				redirectAttributes.addFlashAttribute("message", "Something went wrong");
				mv.setViewName("redirect:/settings");
			}
		}
		return mv;
	}

	@RequestMapping(value = "/settings/changeSettings", method = RequestMethod.POST)
	public ModelAndView changeSettings(@ModelAttribute User user, ModelAndView mv, HttpSession session,
			RedirectAttributes redirectAttributes) {
		logger.info("Settings - changeSettings Controller");
		if (session.getAttribute("USER") == null || session.getAttribute("USER").equals("")) {
			logger.info("Not logged in");
			mv.setViewName("redirect:/homepage");
		} else {
			logger.info("Logged in: " + session.getAttribute("USER"));

			logger.info("Trying to change user settings: " + user.toString());
			userService.changeSettings(user);

			logger.info("Changes saved: " + user.toString());
			mv = new ModelAndView("settings");
			redirectAttributes.addFlashAttribute("message", "Changes saved");
			mv.setViewName("redirect:/settings");

		}

		return mv;
	}

	@RequestMapping(value = "/settings/changePassword", method = RequestMethod.POST)
	public ModelAndView changePassword(@ModelAttribute User user, ModelAndView mv, HttpSession session,
			RedirectAttributes redirectAttributes) {
		logger.info("Settings - changeSettings Controller");
		if (session.getAttribute("USER") == null || session.getAttribute("USER").equals("")) {
			logger.info("Not logged in");
			mv.setViewName("redirect:/homepage");
		} else {
			logger.info("Logged in: " + session.getAttribute("USER"));

			logger.info("Trying to change user password: " + user.toString());
			userService.changePassword(user);

			logger.info("Changes saved: " + user.toString());
			mv = new ModelAndView("settings");
			redirectAttributes.addFlashAttribute("message", "Changes saved");
			mv.setViewName("redirect:/settings");

		}

		return mv;
	}

	@RequestMapping(value = "/settings/delete", method = RequestMethod.POST)
	public ModelAndView deleteUser(@ModelAttribute User user, ModelAndView mv, HttpSession session,
			RedirectAttributes redirectAttributes) {
		logger.info("Settings - changeSettings Controller");
		if (session.getAttribute("USER") == null || session.getAttribute("USER").equals("")) {
			logger.info("Not logged in");
			mv.setViewName("redirect:/homepage");
		} else {
			logger.info("Logged in: " + session.getAttribute("USER"));

			logger.info("Trying to delete user: " + user.toString());
			userService.deleteUser(user);

			logger.info("User deleted: " + user.toString());

			redirectAttributes.addFlashAttribute("message", "User successfully deleted");
			mv.setViewName("redirect:/homepage/logout");
		}

		return mv;
	}
}
