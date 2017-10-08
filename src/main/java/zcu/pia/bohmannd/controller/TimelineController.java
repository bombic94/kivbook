package zcu.pia.bohmannd.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TimelineController {
	
	final Logger logger = Logger.getLogger(HomepageController.class);
	
	@RequestMapping(value = "/timeline")
    public ModelAndView addItems(Model model) {
		logger.info("Timeline Controller");
		ModelAndView mv = new ModelAndView("timeline");       
        
        return mv;
    }
}
