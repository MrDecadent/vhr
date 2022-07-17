package com.dcd.vhr.config;

import com.dcd.vhr.model.MailConstants;
import com.dcd.vhr.service.MailSendLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;


/**
 * Description: 重新定义一个RabbitTemplate
 * @Date 2022/7/17 1:35
 * @Param
 */
@Configuration
public class RabbitConfig {
    public final static Logger logger = LoggerFactory.getLogger(RabbitConfig.class);
    @Resource
    CachingConnectionFactory factory;
    @Resource
    MailSendLogService mailSendLogService;
    @Bean
    RabbitTemplate rabbitTemplate(){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
        rabbitTemplate.setConfirmCallback((data,ack,cause) ->{
            String msgId = data.getId();
            if (ack){//判断消息是否发送成功
                logger.info("["+msgId+"]:邮件发送成功");
                //把数据库中的记录状态改成1(发送成功)
                mailSendLogService.updateMailSendLogStatus(msgId,1);
            }else {
                logger.info("["+msgId+"]:邮件发送失败");
            }
        });
        rabbitTemplate.setReturnsCallback((returnedMessage)-> logger.info("消息发送失败"));
        return rabbitTemplate;
    }
    @Bean
    Queue mailQueue(){
        return new Queue(MailConstants.MAIL_QUEUE_NAME,true);
    }
    @Bean
    DirectExchange mailExchange(){
        return new DirectExchange(MailConstants.MAIL_EXCHANGE_NAME,true,false);
    }
    @Bean
    Binding mailBinding(){
        return BindingBuilder.bind(mailQueue())
                .to(mailExchange())
                .with(MailConstants.MAIL_ROUTING_KEY_NAME);
    }
}
