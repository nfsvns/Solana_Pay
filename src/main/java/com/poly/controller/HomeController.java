package com.poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@RequestMapping("/admin/home/index")
	public String admin() {
		return "redirect:/assets/admin/index.html";
	}
	@RequestMapping("/a")
	public String adminaa() {
		return "NewFile";
	}
	@RequestMapping("/activity")
	public String activity() {
		return "activity.html";
	}
}
