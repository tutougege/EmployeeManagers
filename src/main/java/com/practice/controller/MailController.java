package com.practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Controller
@RequestMapping("/mail")
@EnableAsync
public class MailController {
    @Autowired
    JavaMailSenderImpl mailSender;
    @RequestMapping("/toMail")
    public String toMail(){
        return "Mail";
    }

    @RequestMapping("/sendSimpleMail")
    public String mail(Model model, String sendName, String acceptName, String Subject, String content) {
        //System.out.println(sendName + acceptName + Subject + content);
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            //发送到哪里，收件人邮箱
            helper.setTo(acceptName);
            //从哪里发送，发件人邮箱
            helper.setFrom(sendName);
            //发送主题
            helper.setSubject(Subject);
            //发送内容，可以支持html编辑
            helper.setText(content, true);
            //host:smtp.qq.comusername:2294407483@qq.comport:-1session:javax.mail.Session@66df1f50
            //System.out.println("host:" + mailSender.getHost() + "username:" + mailSender.getUsername() + "port:" + mailSender.getPort() + "session:" + mailSender.getSession());
           // System.out.println(mailSender.getPassword());
            mailSender.send(mimeMessage);
            model.addAttribute("msg2","发送成功");
            return "Mail";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msg1","发送失败");
            return "Mail";
        }
    }


    //复杂邮件上传
    @RequestMapping("/sendComplicatedMail")
    public String complicatedmail(@RequestParam("file") MultipartFile file, Model model, String sendName, String acceptName, String Subject, String content) throws MessagingException {
        try {
            // 获取文件名
            String fileName = file.getOriginalFilename();
            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            // System.out.println("文件的后缀名为：" + suffixName);
            // 设置文件存储路径
            String filePath = "D:\\Work\\file\\";
            String path = filePath + fileName;

            File dest = new File(path);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();// 新建文件夹
            }
            file.transferTo(dest);// 文件写入
            //得到文件的原始名
            // 设置文件存储路径
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            //发送到哪里，收件人邮箱
            helper.setTo(acceptName);
            //从哪里发送，发件人邮箱
            helper.setFrom(sendName);
            //发送主题
            helper.setSubject(Subject);
            //发送内容，可以支持html编辑
            helper.setText(content, true);
            //发送附件
            System.out.println(path);
            //这里发送的是文件名fileName，后面是文件路径path
            helper.addAttachment(fileName, new File(path));
            mailSender.send(mimeMessage);
            model.addAttribute("msg2", "发送成功");
            return "Mail";
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("msg1","发送失败");
            return "Mail";
        }
    }
}
