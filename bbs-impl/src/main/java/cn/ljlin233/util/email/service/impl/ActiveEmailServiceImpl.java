package cn.ljlin233.util.email.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import cn.ljlin233.util.email.entity.ActiveEmail;
import cn.ljlin233.util.email.service.ActiveEmailService;
import cn.ljlin233.util.exception.entity.SystemException;

/**
 * ActiveEmailServiceImpl
 */
@Service
public class ActiveEmailServiceImpl implements ActiveEmailService {

    private JavaMailSender mailSender;

    public ActiveEmailServiceImpl() {}

    @Autowired
    public ActiveEmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendActiveEamil(ActiveEmail email) {

        SimpleMailMessage simpleMessage = new SimpleMailMessage();
        simpleMessage.setFrom(email.getSendFrom());
        simpleMessage.setTo(email.getSendTo());
        simpleMessage.setSubject(email.getTitle());
        simpleMessage.setText(email.getMessage());

        try {
            mailSender.send(simpleMessage);
        } catch (MailException e) {
            throw new SystemException("激活邮件发送失败，重新注册", e.getMessage());
        }

    }
}