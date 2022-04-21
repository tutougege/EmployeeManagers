package com.practice.controller;

import com.practice.dao.EmployeeDao;
import com.practice.dao.ReportDao;
import com.practice.pojo.Employee;
import com.practice.pojo.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/report")
public class ReportController {
    @Autowired
    ReportDao reportDao;
    @Autowired
    EmployeeDao employeeDao;
    @RequestMapping("/toReport")
    public String toReport(){
        return "report/addReport";
    }

    @PostMapping("/addreport")
    public String report(@PathVariable("file") MultipartFile file, Report report, Model model) {
        Employee employee = employeeDao.queryIDName(report.getReporterID(), report.getReporterName());
        Report reportID = reportDao.getReportID(report.getReporterID());
        System.out.println(file);
        if (employee == null) {
            model.addAttribute("redmsg", "该员工不存在");
            return "report/addReport";
        }
        if (reportID == null) {
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            report.setReportDate(simpleDateFormat.format(date));
            //注意这里的参数名称不要和数据库中的字段名重复，要不然汇报类型不一致错误
            try {
                if (file.isEmpty()) {
                    model.addAttribute("redmsg", "文件为空");
                    return "report/addReport";
                }
                // 获取文件名
                String fileName = file.getOriginalFilename();
                System.out.println(fileName);
                // 获取文件的后缀名
                String suffixName = fileName.substring(fileName.lastIndexOf("."));
                // System.out.println("文件的后缀名为：" + suffixName);

                // 设置文件存储路径
                String filePath = "D://Work//file//";
                String path = filePath + fileName;

                File dest = new File(path);
                // 检测是否存在目录
                if (!dest.getParentFile().exists()) {
                    dest.getParentFile().mkdirs();// 新建文件夹
                }
                file.transferTo(dest);// 文件写入
                report.setReportFile(fileName);
                reportDao.addReport(report);
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            model.addAttribute("greenmsg","汇报成功");
            return "report/addReport";
        }
        else{
        model.addAttribute("redmsg", "员工已经汇报过了");
        return "report/addReport";
    }

    }
    @RequestMapping("/toCheck")
    public String toCheck(Model model){
        List<Report> Reports = reportDao.getAllReport();
        model.addAttribute("Reports",Reports);
        model.addAttribute("greenmsg","删除成功");
        return "report/checkReport";
    }

    @RequestMapping("/checkFile/{reportFile}")
    public String checkFile(@PathVariable("reportFile") String reportFile, Model model, HttpServletResponse response) throws UnsupportedEncodingException {
        if (reportFile != null) {
            //设置文件路径
            String realPath = "D://Work//file//";
            File file = new File(realPath , reportFile);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" +new String(reportFile.getBytes("UTF-8"),"iso-8859-1"));// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    System.out.println("==========>"+i);
                    while (i != -1) {
                        os.write(buffer, 0, buffer.length);
                        //os.flush解决只读到部分内容的问题
                        os.flush();
                        i = bis.read(buffer);
                        System.out.println(i);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            else {
                model.addAttribute("redmsg","下载失败，找不到该文件");
                List<Report> Reports = reportDao.getAllReport();
                model.addAttribute("Reports",Reports);
                return "report/checkReport";
            }
        }
        return null;
    }
    //删除汇报内容
    @RequestMapping("/delreport/{reporterID}")
    public String delreport(@PathVariable("reporterID") Integer reporterID,Model model){
       reportDao.delreport(reporterID);
        return "redirect:/report/toCheck";
    }
    //搜索员工汇报
    @GetMapping("/selectReport")
    public String selectReport(String message,Model model){
        HashMap map=new HashMap();
        if (message.contains("1")) {
            try {
                map.put("reporterID", Integer.parseInt(message));
            }catch (IllegalArgumentException e){
                e.printStackTrace();
                model.addAttribute("msg","未查询到此员工汇报");
                return "report/reportInfo";
            }
        } else {
            map.put("reporterName",message);
        }
        List<Report> reports = reportDao.selectReport(map);
        //注意数组是一个对象，不能用employees==null判断，需要使用isEmpty
        if(reports.isEmpty()){
            model.addAttribute("msg","未查询到此员工汇报");
            return "report/reportInfo";
        }
        model.addAttribute("reports",reports);
        return "report/reportInfo";
    }
    }

