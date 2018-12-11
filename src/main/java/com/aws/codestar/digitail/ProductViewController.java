package com.aws.codestar.digitail;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aws.codestar.digitail.model.ProductView;
import com.aws.codestar.digitail.util.DigitailStringConstants;

@Controller
@RequestMapping("/view")
public class ProductViewController {

	Logger logger = LoggerFactory.getLogger(ProductViewController.class);

	@GetMapping
	public String handleProductView(HttpServletRequest req, HttpServletResponse resp, Model model) {
		logger.info("Product view controller :: starts");
		String productName = req.getParameter(DigitailStringConstants.PRODUCT);
		logger.info("product name :: " + productName);
		ProductView view = new ProductView();
		view.setName(productName);
		model.addAttribute("productView", view);
		logger.info("Product view controller :: ends");
		return "view";
	}

}
