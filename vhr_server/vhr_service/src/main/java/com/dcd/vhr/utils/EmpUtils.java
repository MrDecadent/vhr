package com.dcd.vhr.service.utils;

import com.dcd.vhr.model.Employee;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EmpUtils {

    //计算合同期限(求月份差)
    public static Double getContractTerm(Employee employee){
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");


        Date beginContract = employee.getBegincontract();
        Date endContract = employee.getEndcontract();
        //年份差异
        Double year = (Double.parseDouble(yearFormat.format(endContract)) - Double.parseDouble(yearFormat.format(beginContract)))*12;
        //月份差异
        Double month = Double.parseDouble(monthFormat.format(endContract)) - Double.parseDouble(monthFormat.format(beginContract));

        return Double.parseDouble(decimalFormat.format((year + month)/12));
    }
}
