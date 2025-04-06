package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendActivationEmail(String toEmail, String name, String plainPassword) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Activation de votre compte");
        message.setText("Bonjour " + name + ",\n\nVotre compte a été activé avec succès.\nVoici votre mot de passe temporaire : " + plainPassword +
                "\n\nVeuillez le changer après votre première connexion.");
        message.setFrom("ilgazzkaya599@gmail.com");

        mailSender.send(message);
    }

}
