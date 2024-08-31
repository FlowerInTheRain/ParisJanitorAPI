package fr.jypast.parisjanitorapi.server.adapter;

import fr.jypast.parisjanitorapi.domain.functionnal.exception.EmailNotSendException;
import fr.jypast.parisjanitorapi.domain.functionnal.model.user.User;
import fr.jypast.parisjanitorapi.domain.port.out.EmailingSpi;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailingAdapter implements EmailingSpi {

    @Autowired
    private final JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String emailSenderUsername;

    @Value("${api.base.url}")
    private String apiBaseUrl;


    @Override
    public void sendAccountValidationEmail(User user) {
        String senderName = "DnD Gate";
        String subject = "Validation de votre compte";
        String content = "Bonjour [[name]],<br>"
                + "<br>"
                + "Nous vous envoyons ce mail suite à la création de votre compte sur notre plateforme ParisJanitorte.<br>"
                + "Il vous reste une dernière étape avant de pouvoir profiter pleinement de nos services : valider votre adresse mail.<br>"
                + "<br>"
                + "Cliquez sur le lien ci-dessous pour vérifier votre compte :<br>"
                 + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFIER MON COMPTE</a></h3>"
                + "Merci,<br>"
                + "L'équipe ParisJanitor.";

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            System.out.println("Sending account validation email to: " + user.getEmail());
            helper.setFrom(emailSenderUsername, senderName);
            helper.setTo(user.getEmail());
            helper.setSubject(subject);

            String verifyUrl = apiBaseUrl + "/users/verify?email=" + user.getEmail() + "&code=" + user.getVerificationCode();
            content = content.replace("[[name]]", user.getFirstName());
            content = content.replace("[[URL]]", verifyUrl);

            helper.setText(content, true);

            emailSender.send(message);
            System.out.println("Email sent successfully to: " + user.getEmail());
        } catch (Exception e) {
            System.out.println("Failed to send email to " + user.getEmail() + ": " + e.getMessage());
            e.printStackTrace(); // Print the stack trace for debugging
            throw new EmailNotSendException();
        }
    }

    @Override
    public void sendInvitationCodeEmail(User sender, User receiver, String code) {
        String senderName = "DnD Gate";
        String subject = "Invitation de [[senderName]]";
        String content = "Salutation [[receiverName]],<br>"
                + "<br>"
                + "[[senderName]] t'as invité à rejoindre son aventure sur notre plateforme ParisJanitorte.<br>"
                + "Voici le code pour rejoindre la partie : [[code]].<br>"
                + "<br>"
                + "L'équipe ParisJanitor.";

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setFrom(emailSenderUsername, senderName);
            helper.setTo(receiver.getEmail());

            subject = subject.replace("[[senderName]]", sender.getFirstName());
            helper.setSubject(subject);

            content = content.replace("[[senderName]]", sender.getFirstName());
            content = content.replace("[[receiverName]]", receiver.getFirstName());
            content = content.replace("[[code]]", code);

            helper.setText(content, true);

            emailSender.send(message);
        } catch (Exception e) {
            throw new EmailNotSendException();
        }
    }

    @Override
    public void sendCodeVerifier(User user, String code) {
        String senderName = "DnD Gate";
        String subject = "Modification de mot de passe";
        String content = "Bonjour [[name]],<br>"
                + "<br>"
                + "Nous vous envoyons ce mail suite afin de confirmer votre identité.<br>"
                + "<br>"
                + "Voici le code pour modifier votre mot de passe : [[code]] <br>"
                + "Merci,<br>"
                + "L'équipe ParisJanitor.";

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setFrom(emailSenderUsername, senderName);
            helper.setTo(user.getEmail());
            helper.setSubject(subject);

            content = content.replace("[[name]]", user.getFirstName());
            content = content.replace("[[code]]", code);

            helper.setText(content, true);

            emailSender.send(message);
        } catch (Exception e) {
            throw new EmailNotSendException();
        }
    }
}
