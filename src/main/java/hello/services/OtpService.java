package hello.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import hello.dto.EmailDTO;
import hello.dto.MessageDTO;

@Description(value = "Service responsible for handling OTP related functionality.")
@Service
public class OtpService {

    private final Logger LOGGER = LoggerFactory.getLogger(OtpService.class);

    private OtpGenerator otpGenerator;
    private EmailService emailService;
    private UserService userService;

    /**
     * Constructor dependency injector
     * @param otpGenerator - otpGenerator dependency
     * @param emailService - email service dependency
     * @param userService - user service dependency
     */
    public OtpService(OtpGenerator otpGenerator, EmailService emailService, UserService userService)
    {
        this.otpGenerator = otpGenerator;
        this.emailService = emailService;
        this.userService = userService;
    }

    /**
     * Method for generate OTP number
     *
     * @param key - provided key (username in this case)
     * @return boolean value (true|false)
     */
    public Boolean generateOtp(String key)
    {
        // generate otp
        Integer otpValue = otpGenerator.generateOTP(key);
        if (otpValue == -1)
        {
            LOGGER.error("OTP generator is not working...");
            return  false;
        }

        LOGGER.info("Generated OTP: {}", otpValue);

        // fetch user e-mail from database
        String userEmail = userService.findEmailByUsername(key);
        List<String> recipients = new ArrayList<>();
        recipients.add(userEmail);

        // generate emailDTO object
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setSubject("Spring Boot OTP Password.");
        emailDTO.setBody("OTP Password: " + otpValue);
        emailDTO.setRecipients(recipients);

        // send generated e-mail
        return emailService.sendSimpleMessage(emailDTO);
    }

    
    
    /**
     * Method for generate OTP number
     *
     * @param key - provided key (username in this case)
     * @return boolean value (true|false)
     */
    public Boolean generateSMSOtp(String key)
    {
        // generate otp
        Integer otpValue = otpGenerator.generateOTP(key);
        if (otpValue == -1)
        {
            LOGGER.error("OTP generator is not working...");
            return  false;
        }

        LOGGER.info("Generated OTP: {}", otpValue);

        // fetch user e-mail from database
        String mobile = userService.findMobileByUsername(key);
        
        // generate emailDTO object
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setHost("http://hpsms.dial4sms.com/api/web2sms.php");
        messageDTO.setUserName("demo4trans");
        messageDTO.setPassword("Welcome@5");
        messageDTO.setFrom("MRMFALFARZ");
        messageDTO.setTo(mobile);
        messageDTO.setMessage("Hello%20"+ key +"%20OTP%20" + otpValue +"Please%20use%20this%20for%20Authenticate" );
        LOGGER.info("Hello "+ key +" OTP:" + otpValue +" Please use this  " );
        // send generated e-mail
        return emailService.sendSMSMessage(messageDTO);
    }

    /**
     * Method for validating provided OTP
     *
     * @param key - provided key
     * @param otpNumber - provided OTP number
     * @return boolean value (true|false)
     */
    public Boolean validateOTP(String key, Integer otpNumber)
    {
        // get OTP from cache
        Integer cacheOTP = otpGenerator.getOPTByKey(key);
        if (cacheOTP.equals(otpNumber))
        {
            otpGenerator.clearOTPFromCache(key);
            return true;
        }
        return false;
    }
}
