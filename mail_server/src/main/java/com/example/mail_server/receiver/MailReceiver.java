package com.example.mail_server.receiver;

import com.dcd.vhr.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

@Component
public class MailReceiver {

    @Resource
    JavaMailSender javaMailSender;
    @Resource
    MailProperties mailProperties;
    @Resource
    TemplateEngine templateEngine;

    public static final Logger logger = LoggerFactory.getLogger(MailReceiver.class);

    @RabbitListener(queues = "dcd.mail")
    public void handler(Employee emp){
        //收到消息,发送邮件
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        try {
            helper.setFrom(mailProperties.getUsername());
            helper.setTo(emp.getEmail());
            helper.setSubject("入职欢迎");
            helper.setSentDate(new Date());
            Context context = new Context();
            context.setVariable("name", emp.getName());
            context.setVariable("posName", emp.getPosition().getName());
            context.setVariable("jobLevelName", emp.getJobLevel().getName());
            context.setVariable("departmentName", emp.getDepartment().getName());
            String mail = templateEngine.process("mail", context);
            helper.setText(mail,true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
            logger.error("邮件发送失败");
        }
    }

}
