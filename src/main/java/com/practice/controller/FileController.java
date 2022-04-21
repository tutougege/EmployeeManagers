package com.practice.controller;

import com.practice.dao.FileRecordDao;
import com.practice.pojo.Employee;
import com.practice.pojo.FileRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/upload")
public class FileController {
    @Autowired
    FileRecordDao fileRecordDao;
    @RequestMapping("/toupload")
    public String toupload(){
        return "upload/upload";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("file")MultipartFile file, HttpServletRequest request, Model model, FileRecord fileRecord) throws IOException {
        try {
            if (file.isEmpty()) {
                model.addAttribute("msg5","文件为空");
                return "upload/upload";
            }
            // 获取文件名
            String fileName = file.getOriginalFilename();
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            fileRecord.setEmpID((Integer)request.getSession().getAttribute("empID"));
            fileRecord.setUploadFileName(fileName);
            fileRecord.setUploadDate(simpleDateFormat.format(date));
            //插入文件上传记录
            fileRecordDao.addRecord(fileRecord);
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
            model.addAttribute("greenmsg","文件上传成功");
            return "upload/upload";
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        model.addAttribute("redmsg","文件上传失败");
        return "upload/upload";
    }

    //多文件上传
    @RequestMapping("/uploadMore")
    public String handleFileUpload(HttpServletRequest request,Model model,FileRecord fileRecord) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        //System.out.println(files);
        MultipartFile file = null;
        MultipartFile file2 = null;
        MultipartFile file3 = null;
        BufferedOutputStream stream = null;
        List<Object> list=new ArrayList<>();
        file2=files.get(0);
        file3=files.get(1);
        file=files.get(2);
        list.add(file.getOriginalFilename());
        list.add(file2.getOriginalFilename());
        list.add(file3.getOriginalFilename());
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        fileRecord.setEmpID((Integer)request.getSession().getAttribute("empID"));
        fileRecord.setUploadFileName(list.toString().replace("[","").replace("]",""));
        fileRecord.setUploadDate(simpleDateFormat.format(date));
        //插入文件上传记录
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            String filePath = "D://Work//file//";
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(
                            new File(filePath + file.getOriginalFilename())));//设置文件路径及名字
                    stream.write(bytes);// 写入
                    stream.close();

                } catch (Exception e) {
                    stream = null;
                    model.addAttribute("redmsg","第"+(i+1)+"文件上传失败"+e.getMessage());
                    return "upload/upload";
                }
            } else {
                model.addAttribute("redmsg","第"+(i+1)+"个文件上传失败，因为文件为空");
                return "upload/upload";
            }
        }
        fileRecordDao.addRecord(fileRecord);
        model.addAttribute("greenmsg","上传成功");
        return "upload/upload";
    }

    //文件下载
    //文件下载相关代码
    @RequestMapping("/download")
    public String downloadFile(@RequestParam("fileName") String fileName,Model model,HttpServletRequest request, HttpServletResponse response,FileRecord fileRecord) throws UnsupportedEncodingException {
        if (fileName != null) {
            //设置文件路径
            String realPath = "D://Work//file//";
            File file = new File(realPath , fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" +new String(fileName.getBytes("UTF-8"),"iso-8859-1"));// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, buffer.length);
                        //os.flush解决只读到部分内容的问题
                        os.flush();
                        i = bis.read(buffer);
                    }
                    fileRecord.setEmpID((Integer)request.getSession().getAttribute("empID"));
                    fileRecord.setDownloadFile(fileName);
                    Date date = new Date();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    fileRecord.setDownloadDate(simpleDateFormat.format(date));
                    //插入文件下载记录
                    fileRecordDao.addRecord(fileRecord);
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
                return "upload/upload";
            }
        }
        return null;
    }

}
