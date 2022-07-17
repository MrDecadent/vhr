package com.dcd.vhr.service;

import com.dcd.vhr.mapper.EmployeeMapper;
import com.dcd.vhr.model.*;
import com.dcd.vhr.service.utils.EmpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.amqp.RabbitRetryTemplateCustomizer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class EmpService {
    @Resource
    EmployeeMapper employeeMapper;
    @Resource
    NationService nationService;
    @Resource
    politicsStatusService politicsStatusService;
    @Resource
    JobLevelService jobLevelService;
    @Resource
    PositionService positionService;
    @Resource
    MailSendLogService mailSendLogService;
    @Resource
    RabbitTemplate rabbitTemplate;

    public final static Logger logger = LoggerFactory.getLogger(EmpService.class);

    public RespPageBean getEmployeeByPage(Integer page, Integer size
            , String keywords, Employee employee,Date[] begindatescope) {
        if (page != null && size != null){
            page = (page-1)*size;
        }
        List<Employee> data = employeeMapper.getEmployeeByPage(page,size,keywords,employee,begindatescope);
        Long total = employeeMapper.getTotal(keywords,employee,begindatescope);
        RespPageBean bean = new RespPageBean();
        bean.setData(data);
        bean.setTotal(total);
        return bean;
    }

    public Integer addEmployee(Employee employee) {
        Double contractTerm = EmpUtils.getContractTerm(employee);
        employee.setContractterm(contractTerm);

        int result = employeeMapper.insertSelective(employee);
        if (result == 1){
            Employee emp = employeeMapper.getEmployeeById(employee.getId());
            //生成消息的id
            String msgId = UUID.randomUUID().toString();
            MailSendLog mailSendLog = new MailSendLog();
            mailSendLog.setMsgId(msgId);
            mailSendLog.setCreateTime(new Date());
            mailSendLog.setExchange(MailConstants.MAIL_EXCHANGE_NAME);
            mailSendLog.setRouteKey(MailConstants.MAIL_ROUTING_KEY_NAME);
            mailSendLog.setEmpId(emp.getId());
            mailSendLog.setCount(0);
            mailSendLog.setTryTime(
                    new Date(System.currentTimeMillis() + 1000 * 60 * MailConstants.MSG_TIMEOUT));
            mailSendLogService.insertSelective(mailSendLog);
            rabbitTemplate.convertAndSend(
                    MailConstants.MAIL_EXCHANGE_NAME
                    ,MailConstants.MAIL_ROUTING_KEY_NAME
                    ,emp
                    ,new CorrelationData(msgId));
            rabbitTemplate.convertAndSend(
                    MailConstants.MAIL_EXCHANGE_NAME
                    ,MailConstants.MAIL_ROUTING_KEY_NAME
                    ,emp
                    ,new CorrelationData(msgId));
        }
        return result;
    }

    public List<Nation> getAllNations() {
        return nationService.getAllNations();
    }

    public List<Politicsstatus> getAllPolitic() {
        return politicsStatusService.getAllPolitic();
    }

    public List<JobLevel> getAllJobLevels() {
        return jobLevelService.getAllJobLevel();
    }

    public List<Position> getAllPositions() {
        return positionService.getAllPositions();
    }

    public Integer getMaxWorkId() {
        return employeeMapper.getMaxWorkId();
    }

    public Integer deleteEmployeeById(Integer id) {
        return employeeMapper.deleteByPrimaryKey(id);
    }

    public Integer updateEmployee(Employee employee) {
        Double contractTerm = EmpUtils.getContractTerm(employee);
        employee.setContractterm(contractTerm);
        return employeeMapper.updateByPrimaryKeySelective(employee);
    }

    public Employee getEmployeeById(Integer empId) {
        return employeeMapper.getEmployeeById(empId);
    }
}
