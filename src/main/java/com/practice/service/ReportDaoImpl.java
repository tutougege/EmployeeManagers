package com.practice.service;

import com.practice.pojo.Employee;
import com.practice.pojo.Report;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class ReportDaoImpl implements ReportDao{
    @Autowired
    ReportDao reportDao;
    @Override
    public int addReport(Report report) {
        return reportDao.addReport(report);
    }

    @Override
    public List<Report> getAllReport() {
        return reportDao.getAllReport();
    }

    @Override
    public Report getReportID(Integer reporterID) {
        return reportDao.getReportID(reporterID);
    }

    @Override
    public int delreport(Integer reporterID) {
        return reportDao.delreport(reporterID);
    }

    @Override
    public List<Report> selectReport(Map map) {
        return reportDao.selectReport(map);
    }


}
