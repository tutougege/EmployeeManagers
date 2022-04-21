package com.practice.controller;

import com.practice.dao.FileRecordDao;
import com.practice.pojo.FileRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FileRecordController {
    @Autowired
    FileRecordDao fileRecordDao;

    //文件上传记录
    @RequestMapping("/uploadRecord")
    public String uploadRecord(HttpServletRequest request, Model model, FileRecord fileRecord){
      Integer empID=(Integer)request.getSession().getAttribute("empID");
        List<FileRecord> fileRecords = fileRecordDao.selectRecord(empID);
        model.addAttribute("records",fileRecords);
        return "upload/uploadRecord";
    }
    @RequestMapping("/del/record/{recordID}")
    public String delRecord( @PathVariable("recordID") Integer recordID){
        System.out.println(recordID);
        fileRecordDao.deleteRecord(recordID);
        return "redirect:/uploadRecord";
    }
}
