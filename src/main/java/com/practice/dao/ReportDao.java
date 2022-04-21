package com.practice.dao;

import com.practice.pojo.Employee;
import com.practice.pojo.Report;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface ReportDao {
    //添加汇报
    int addReport(Report report);
    //查询所有的汇报
    List<Report> getAllReport();
    //查询员工ID存在
    Report getReportID(Integer reporterID);
    //删除员工汇报
    int delreport(@PathVariable("reporterID") Integer reporterID);
    //搜索员工汇报
    List<Report> selectReport(Map map);
}
