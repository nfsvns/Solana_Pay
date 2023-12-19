package com.poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TrangThaiController {
	@RequestMapping("/success")
	public String success() {
		return "success";
	}
	@RequestMapping("/cancel")
	public String cancel() {
		return "cancel";
	}
	@RequestMapping("/checktest")
	public String checktest() {
		return "NewFile";
	}
}
