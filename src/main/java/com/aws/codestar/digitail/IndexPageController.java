package com.digitail;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexPageController {

	@GetMapping
	public String homePage(Model model) {
		return "index";
	}
	
	@GetMapping("/error")
	public String errorPage(Model model) {
		return "error";
	}

}
