package com.example.mail_server.receiver;

import com.dcd.vhr.model.MailConstants;

import java.util.Date;

public class Hello {
    public static void main(String[] args) {
        System.out.println(new Date(System.currentTimeMillis()+1000*60* MailConstants.MSG_TIMEOUT));
        System.out.println(new Date());
    }
}
