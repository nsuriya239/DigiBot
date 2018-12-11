package com.digitail;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.digitail.model.UserLogin;
import com.digitail.util.DigitailStringConstants;

@Controller
@RequestMapping("/authorize")
public class UserAuthorizeController {

	Logger logger = LoggerFactory.getLogger(UserAuthorizeController.class);
	@Value("#{${credentials}}")
	private Map<String, String> credentials;

	@PostMapping
	public String authorizeUser(@ModelAttribute UserLogin userLogin) throws IOException {
		logger.info("authorize start");
		String endpoint = "error";
		String authorization = "123456";
		logger.debug("redirect uri :: " + userLogin.getRedirect_uri());
		logger.debug("token :: " + userLogin.getAccount_linking_token());
		logger.info("pwd :: " + userLogin.getPwd());
		logger.info("username :: " + userLogin.getUserName());
		logger.info("username :: " + credentials.get("user"));
		logger.info("username :: " + credentials.get("pwd"));

		if (credentials.get("user").equals(userLogin.getUserName()) && credentials.get("pwd").equals(userLogin.getPwd())) {
			endpoint = "redirect:" + userLogin.getRedirect_uri() + "&authorization_code=" + authorization;
			logger.info("endpoint : " + endpoint);
		} else {
			endpoint = "redirect:" + userLogin.getRedirect_uri();
		}
		return endpoint;
	}

	@GetMapping
	public String authorizeUserFlow(HttpServletRequest req, HttpServletResponse resp, Model model) throws IOException {
		logger.info("authorizeUserFlow GET Start");
		String accountLinkingToken = req.getParameter(DigitailStringConstants.ACCOUNT_LINKING_TOKEN);
		String redirectURI = req.getParameter(DigitailStringConstants.REDIRECT_URI);
		logger.info("redirect uri :: " + redirectURI);
		logger.info("token :: " + accountLinkingToken);
		UserLogin userLogin = new UserLogin();
		userLogin.setAccount_linking_token(accountLinkingToken);
		userLogin.setRedirect_uri(redirectURI);
		model.addAttribute("userLogin", userLogin);
		return "authorize";
	}

}
