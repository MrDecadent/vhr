package com.dcd.vhr.mapper;

import com.dcd.vhr.model.MailSendLog;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface MailSendLogMapper {
    Integer updateMailSendLogStatus(@Param("msgId") String msgId
            ,@Param("status") Integer status);

    Integer insertSelective(MailSendLog mailSendLog);

    List<MailSendLog> getMailSendLogByStatus();

    Integer updateCount(@Param("msgId") String msgId,@Param("date") Date date);
}
