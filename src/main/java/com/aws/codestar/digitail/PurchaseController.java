package com.aws.codestar.digitail;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/purchase")
public class PurchaseController {

	Logger logger = LoggerFactory.getLogger(PurchaseController.class);

	@PostMapping
	public String handleProductView(HttpServletRequest req, HttpServletResponse resp, Model model) {
		logger.info("purchase view controller :: starts");
		String productAdded = req.getParameter("productAdded");
		logger.info("Purchase view controller :: ends :: " + productAdded);
		return "createCart";
	}
	
	@GetMapping
	public String handleProductBuy(HttpServletRequest req, HttpServletResponse resp, Model model) {
		logger.info("purchase view controller :: starts");
		String productAdded = req.getParameter("productAdded");
		logger.info("Purchase view controller :: ends :: " + productAdded);
		return "createCart";
	}

}
