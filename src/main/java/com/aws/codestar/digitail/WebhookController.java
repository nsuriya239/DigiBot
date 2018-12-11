package com.aws.codestar.digitail;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aws.codestar.digitail.bean.PageObjectBean;
import com.google.gson.Gson;

@Controller
@RequestMapping("/webhook")
public class WebhookController {

	Logger logger = LoggerFactory.getLogger(WebhookController.class);
	@Autowired
	UserMessageController messageController;
	@Value("${TOKEN}")
	private String token;
	@Value("${ACCESS_TOKEN}")
	private String access_token;
	@Value("${MESSENGER_URL}")
	private String messenger_url;
	@Value("${GRAPH_URL}")
	private String graph_url;

	@GetMapping
	public void webhookGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.info("webhookGet: Start");

		String hubMode = req.getParameter("hub.mode");
		String token = req.getParameter("hub.verify_token");
		String challenge = req.getParameter("hub.challenge");

		logger.info("mode :: token :: challenge :: " + hubMode + " :: " + token + " :: " + challenge);

		if (hubMode != null && hubMode.equalsIgnoreCase("subscribe") && token != null
				&& token.equalsIgnoreCase(token)) {
			resp.setStatus(HttpServletResponse.SC_OK);
			resp.getWriter().print(challenge);
			logger.debug("response :: " + resp);
		}
		logger.info("webhookGet: End");
	}

	@PostMapping
	public void webhookPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.info("webhookPost: Start");
		StringBuilder builder = new StringBuilder();
		Iterator<String> requestStreamIterator = req.getReader().lines().iterator();

		while (requestStreamIterator.hasNext()) {
			builder.append(requestStreamIterator.next());
		}

		logger.info(builder.toString());

		Gson gson = new Gson();
		PageObjectBean bean = gson.fromJson(builder.toString(), PageObjectBean.class);

		if (bean != null) {
			logger.info(bean.getObject());
			if (bean.getEntry()[0] != null && bean.getEntry()[0].getMessaging()[0] != null
					&& bean.getEntry()[0].getMessaging()[0].getMessage() != null) {
				logger.info(bean.getEntry()[0].getMessaging()[0].getMessage().getText());
			}
		}

		messageController.sendReply(builder.toString());
		resp.setStatus(HttpServletResponse.SC_OK);
	}

}
