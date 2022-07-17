package com.dcd.vhr.service;

import com.dcd.vhr.mapper.MailSendLogMapper;
import com.dcd.vhr.model.MailSendLog;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class MailSendLogService {
    @Resource
    MailSendLogMapper mailSendLogMapper;

    public Integer updateMailSendLogStatus(String msgId, Integer status) {
        return mailSendLogMapper.updateMailSendLogStatus(msgId,status);
    }

    public Integer insertSelective(MailSendLog mailSendLog) {
        return mailSendLogMapper.insertSelective(mailSendLog);
    }

    public List<MailSendLog> getMailSendLogByStatus() {
        return mailSendLogMapper.getMailSendLogByStatus();
    }

    public Integer updateCount(String msgId, Date date) {
        return mailSendLogMapper.updateCount(msgId,date);
    }
}
