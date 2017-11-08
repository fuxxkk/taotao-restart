package com.taotao.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("page")
public class PageController {
	
	/**
	 * 跳转页面
	 * @param page
	 * @return
	 */
	@RequestMapping("{page}")
	public String toPage(@PathVariable("page") String page) {
		return page;
	}
	
}
