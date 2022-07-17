package com.example.mail_server.receiver;

import com.dcd.vhr.model.Employee;
import com.dcd.vhr.model.MailConstants;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import org.springframework.messaging.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Date;

@Component
public class MailReceiver {

    @Resource
    JavaMailSender javaMailSender;
    @Resource
    MailProperties mailProperties;
    @Resource
    TemplateEngine templateEngine;
    @Resource
    StringRedisTemplate stringRedisTemplate;

    public static final Logger logger = LoggerFactory.getLogger(MailReceiver.class);

    @RabbitListener(queues = MailConstants.MAIL_QUEUE_NAME)
    public void handler(Message message, Channel channel) throws IOException {
        Employee emp = (Employee) message.getPayload();
        MessageHeaders headers = message.getHeaders();
        Long tag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        String msgId = (String) headers.get("spring_returned_message_correlation");
        if (stringRedisTemplate.opsForHash().entries("mail-log").containsKey(msgId)){
            //redis 中包含该 key，说明该消息已经被消费过
            logger.info("["+msgId + "]:消息已经被消费");
            channel.basicAck(tag, false);//确认消息已消费
            return;
        }
        logger.info(emp.toString());
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
            stringRedisTemplate.opsForHash().put("mail_log", msgId, "dcd");
            channel.basicAck(tag, false);//确认消息已消费
        } catch (MessagingException e) {
            channel.basicNack(tag, false, true);
            e.printStackTrace();
            logger.error("邮件发送失败");
        }
    }

}
