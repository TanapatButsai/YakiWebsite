package ku.cs.YakinikuWebsite.service;

import ku.cs.YakinikuWebsite.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MemberRepository memberRepository;
    public void sendEmail(String toEmail,
                                String subject,
                                String body
    ) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("fromemail@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        mailSender.send(message);
        System.out.println("Mail Send...");
    }
    	public void triggerMail(String username) throws MessagingException {
		sendEmail(memberRepository.findByUsername(username).getEmail(),
				"Yakiniku Delivery Submitted order",
				"You have ordered food from Yakiniku Delivery! " +"\n"+
                        "We will notify you if the order is confirmed.");

	}
}

