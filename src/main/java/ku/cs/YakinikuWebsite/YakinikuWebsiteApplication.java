package ku.cs.YakinikuWebsite;

import ku.cs.YakinikuWebsite.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import javax.mail.MessagingException;

@SpringBootApplication
public class YakinikuWebsiteApplication {

	public static void main(String[] args) {

		SpringApplication.run(YakinikuWebsiteApplication.class, args);
	}
	// for email sending
//	@Autowired
//	private EmailSenderService senderService;
//
//	@EventListener(ApplicationReadyEvent.class)
//	public void triggerMail() throws MessagingException {
//		senderService.sendSimpleEmail("tanapat.bu@ku.th",
//				"This is email body",
//				"This is email subject");
//
//	}
}
