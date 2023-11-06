package ku.cs.YakinikuWebsite.service;

import ku.cs.YakinikuWebsite.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

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
    	public void triggerMailConfirm(String username) throws MessagingException {
		sendEmail(memberRepository.findByUsername(username).getEmail(),
				":) Yakiniku Delivery Submitted order",
				"You have ordered food from Yakiniku Delivery! " +"\n"+
                        "We will let you know when the order is Confirm By Admin." +"\n" +
                        "Please pay as soon as possible to " + "\n" +
                        "Account number:83464937129"
        );

	}
    public void triggerMailOrderReceived(String username) throws MessagingException {
        sendEmail(memberRepository.findByUsername(username).getEmail(),
                "Yakiniku Delivery Submitted order",
                "OrderReceived! " +"\n"+
                        "We will notify you if the order is delivered.");

    }
    public void triggerMailDelivered(String username) throws MessagingException {
        sendEmail(memberRepository.findByUsername(username).getEmail(),
                "Yakiniku Delivery Submitted order",
                "You have ordered food from Yakiniku Delivery! " +"\n"+
                        "Delivered.");

    }
}

