package zcu.pia.bohmannd.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import zcu.pia.bohmannd.model.User;
import zcu.pia.bohmannd.service.UserService;

@Controller
public class ErrorController {

	@Autowired
	private UserService userService;

	final Logger logger = Logger.getLogger(ErrorController.class);

	@RequestMapping(value = "errors", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView renderErrorPage(HttpServletRequest httpRequest, HttpSession session, ModelAndView mv) {

		logger.info("Error Controller");
		if (session.getAttribute("USER") == null || session.getAttribute("USER").equals("")) {
			logger.info("Not logged in");
			mv.setViewName("redirect:/homepage");
		} else {
			mv = new ModelAndView("errorPage");
			String errorMsg = "";
			int httpErrorCode = getErrorCode(httpRequest);

			switch (httpErrorCode) {
			case 400: {
				errorMsg = "HTTP Error Code: 400. Bad Request";
				break;
			}
			case 401: {
				errorMsg = "HTTP Error Code: 401. Unauthorized";
				break;
			}
			case 404: {
				errorMsg = "HTTP Error Code: 404. Resource not found";
				break;
			}
			case 500: {
				errorMsg = "HTTP Error Code: 500. Internal Server Error";
				break;
			}
			default: {
				errorMsg = "Error";
				break;
			}
			}
			logger.info("Error: " + errorMsg);
			mv.addObject("errorMsg", errorMsg);
			User userL = userService.getUserByUsername(session.getAttribute("USER").toString());
			mv.addObject("loggedUser", userL);
		}
		return mv;
	}

	private int getErrorCode(HttpServletRequest httpRequest) {
		return (Integer) httpRequest.getAttribute("javax.servlet.error.status_code");
	}
}
