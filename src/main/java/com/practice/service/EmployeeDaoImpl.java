package com.practice.service;

import com.practice.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class EmployeeDaoImpl  implements EmployeeDao{
    @Autowired
    EmployeeDao employeeDao;
    @Override
    public Employee queryEmp(String empID, String empPassword) {
        return employeeDao.queryEmp(empID,empPassword);
    }

    @Override
    public List<Employee> queryPhoneMail(String empPhone, String empMail) {
        return employeeDao.queryPhoneMail(empPhone,empMail);
    }


    @Override
    public int registerEmp(Employee employee) {
        return employeeDao.registerEmp(employee);
    }

    @Override
    public List<Employee> allEmp() {
        return employeeDao.allEmp();
    }

    @Override
    public Employee getEmpByID(Integer EmpID) {
        return employeeDao.getEmpByID(EmpID);
    }

    @Override
    public int updateEmp(Employee employee) {
        return employeeDao.updateEmp(employee);
    }

    @Override
    public int delEmp(Integer EmpID) {
        return employeeDao.delEmp(EmpID);
    }

    @Override
    public List<Employee> selectEmp(Map map) {
        return employeeDao.selectEmp(map);
    }
    @Override
    public Employee queryIDName(Integer reporterID, String reporterName) {
        return employeeDao.queryIDName(reporterID,reporterName);
    }

    @Override
    public Employee registerEmp2(String empPhone) {
        return employeeDao.registerEmp2(empPhone);
    }

    @Override
    public Employee queryMail(String empMail) {
        return employeeDao.queryMail(empMail);
    }


}
