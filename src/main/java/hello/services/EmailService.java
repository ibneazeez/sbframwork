package hello.services;

import java.net.URI;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import hello.dto.EmailDTO;
import hello.dto.MessageDTO;

@Service
public class EmailService {

    private final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender emailSender;

    /**
     * Method for sending simple e-mail message.
     * @param emailDTO - data to be send.
     */
    public Boolean sendSimpleMessage(EmailDTO emailDTO)
    {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(emailDTO.getRecipients().stream().collect(Collectors.joining(",")));
        mailMessage.setSubject(emailDTO.getSubject());
        mailMessage.setText(emailDTO.getBody());

        Boolean isSent = false;
        try
        {
            emailSender.send(mailMessage);
            isSent = true;
        }
        catch (Exception e) {
            LOGGER.error("Sending e-mail error: {}", e.getMessage());
        }
        return isSent;
    }
    
    
    public Boolean sendSMSMessage(MessageDTO messageDTO)
    {
       LOGGER.info("EmailService.sendSMSMessage()==BEGIN");
        Boolean isSent = false;
        try
        {
        	 
        	String url = messageDTO.getURL();
        	 System.out.println("URL -- " + url);
        	URI expanded = URI.create(url); // this is what RestTemplate uses 
        	//url = URLDecoder.decode(expanded.toString(), "UTF-8"); // java.net class
        	new RestTemplate().getForObject(url, String.class); 
        	
			//new RestTemplate().getForObject(url, HttpMethod.GET, null, null);
            isSent = true;
        }
        catch (Exception e) {
            LOGGER.error("Sending Message error: {}", e.getMessage());
        }
        LOGGER.info("EmailService.sendSMSMessage()--END");
        return isSent;
    }


}
