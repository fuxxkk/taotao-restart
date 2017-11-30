package com.taotao.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.taotao.manage.service.ContentService;

@Controller
public class IndexController {
	
	@Autowired
	private ContentService contentService;
	
	@RequestMapping(value = "index",method=RequestMethod.GET)
	public ModelAndView index() {

		//111
		ModelAndView mv = new ModelAndView( "index");
		try {
			String str = contentService.queryBigAdData();
			mv.addObject("bigAdData",str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mv;
	}
}
