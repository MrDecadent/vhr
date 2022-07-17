package com.dcd.vhr.task;

import com.dcd.vhr.model.Employee;
import com.dcd.vhr.model.MailConstants;
import com.dcd.vhr.model.MailSendLog;
import com.dcd.vhr.service.EmpService;
import com.dcd.vhr.service.MailSendLogService;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Component
public class MailSendTask {
    @Resource
    MailSendLogService mailSendLogService;
    @Resource
    RabbitTemplate rabbitTemplate;
    @Resource
    EmpService empService;
    @Scheduled(cron = "0/10 * * * * ?")
    public void mailResendTask(){
        List<MailSendLog> logs = mailSendLogService.getMailSendLogByStatus();
        logs.forEach(mailSendLog -> {
            if (mailSendLog.getCount() >= 3){
                //发送次数大于等于三，认定为发送失败
                mailSendLogService.updateMailSendLogStatus(mailSendLog.getMsgId(), 2);
            }else {
                mailSendLogService.updateCount(mailSendLog.getMsgId(),new Date());
                Employee emp = empService.getEmployeeById(mailSendLog.getEmpId());
                rabbitTemplate.convertAndSend(MailConstants.MAIL_EXCHANGE_NAME
                        ,MailConstants.MAIL_ROUTING_KEY_NAME
                        ,emp
                        ,new CorrelationData(mailSendLog.getMsgId()));
            }
        });
    }
}
