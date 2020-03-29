package pl.adaroz.springboot2.homework6.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import pl.adaroz.springboot2.homework6.service.MovieService;

import java.util.regex.Pattern;

@Aspect
@Service
public class MailSenderAspect {

    private JavaMailSender emailSender;
    private MovieService movieService;
    private String to;

    @Autowired
    public MailSenderAspect(JavaMailSender emailSender, MovieService movieService) {
        this.emailSender = emailSender;
        this.movieService = movieService;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @After("@annotation(MailSenderAfterAddingMovie)")
    private void sendEmail() {
        if (isValid(to)) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject("Your movie has been added");
            message.setText(movieService.getMoviesString());
            emailSender.send(message);
        }
    }

    //https://www.geeksforgeeks.org/check-email-address-valid-not-java/
    private boolean isValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

}
