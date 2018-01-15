package com.dev.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "test")
public class testController {

	@Autowired
	private final static Logger logger =  LoggerFactory.getLogger(testController.class);
	//@Autowired 
	//private AppConfigService appConfigService;
	
	//@RequestMapping("/index")
	@RequestMapping(value="/index")
	public String getIndex(Model model ){
		logger.info("This is test loger");
		model.addAttribute("hello", "hello world");
		//appConfigService.getAppConfig(1);
		return "index";
	}
}
