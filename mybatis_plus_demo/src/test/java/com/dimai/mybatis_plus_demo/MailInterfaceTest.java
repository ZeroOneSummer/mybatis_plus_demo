package com.dimai.mybatis_plus_demo;

import com.dimai.mybatis_plus_demo.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by pijiang on 2019/6/24.
 */
@Slf4j
public class MailInterfaceTest extends BaseTest{

    @Autowired
    MailService mailService;

    /**
     * 纯文本邮件
     */
    @Test
    public void sendTextMail(){
        mailService.sendSimpleMail();
    }

    /**
     * 含附件邮件
     */
    @Test
    public void sendAttachmentsMail(){
        mailService.sendAttachmentsMail();
    }

}