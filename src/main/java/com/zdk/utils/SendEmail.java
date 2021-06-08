package com.zdk.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;

/**
 * @author zdk
 * @date 2021/4/28 18:27
 * 发送邮件工具类
 */
public class SendEmail {
    public static String sendEmail(String email){
        String code="";
        try {
            // 创建Properties 类用于记录邮箱的一些属性
            Properties props = new Properties();
            // 表示SMTP发送邮件，必须进行身份验证
            props.put("mail.smtp.auth", "true");
            //此处填写SMTP服务器
            props.put("mail.smtp.host", "smtp.qq.com");
            props.put("mail.smtp.port", "587");
            // 此处填写你的账号
            props.put("mail.user", "369365576@qq.com");
            // 此处的密码就是前面说的16位STMP口令
            props.put("mail.password", "rkhdtqhavscpbggh");
            //创建电子邮件对象
            // 构建授权信息，用于进行SMTP进行身份验证
            Authenticator authenticator = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    // 用户名、密码
                    String userName = props.getProperty("mail.user");
                    String password = props.getProperty("mail.password");
                    return new PasswordAuthentication(userName, password);
                }
            };
            //获取随机验证码
            String str="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
            for(int i=0;i<4;i++)
            {
                char ch=str.charAt(new Random().nextInt(str.length()));
                code +=ch;
            }
            // 使用环境属性和授权信息，创建邮件会话
            Session mailSession = Session.getInstance(props, authenticator);
            // 创建邮件消息
            MimeMessage message = new MimeMessage(mailSession);
            // 设置发件人
            InternetAddress form = new InternetAddress(props.getProperty("mail.user"));
            message.setFrom(form);
            // 设置收件人的邮箱
            InternetAddress to = new InternetAddress(email);
            message.setRecipient(Message.RecipientType.TO, to);
            // 设置邮件标题
            message.setSubject("验证码：");
            // 设置邮件的内容体
            message.setContent(code, "text/html;charset=UTF-8");
            Transport.send(message);
        }catch (Exception e){
            e.printStackTrace();
        }
        return code;
    }
}
