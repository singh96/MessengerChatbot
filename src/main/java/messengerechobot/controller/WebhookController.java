package messengerechobot.controller;

import java.io.IOException;

import messengerechobot.helper.SendResponseToFacebook;
import messengerechobot.request.Messaging;
import messengerechobot.request.RequestReceived;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class WebhookController {

    @ResponseBody
	@RequestMapping(value = "/webhook", method = RequestMethod.GET)
	public Integer get(@RequestParam(value = "hub.mode") String mode,
			@RequestParam(value = "hub.verify_token") String verifyToken, 
			@RequestParam(value = "hub.challenge") int challenge) {
		if(mode.equals("subscribe") && verifyToken.equals("verifyToken")) {
			return challenge;
		}
		return -22;
	}


	@RequestMapping(value = "/webhook", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void post(@RequestBody RequestReceived request) throws IOException {

		String senderId = "", receivedText = "";
		try {
			Messaging messaging = request.getEntry().get(0).getMessaging().get(0);
		    senderId = messaging.getSender().getId();
		    receivedText = messaging.getMessage().getText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		new SendResponseToFacebook().createResponse(receivedText, senderId);
	}
}
