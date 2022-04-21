package com.practice.service;

import com.practice.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

public interface EmployeeDao {
    //查询用户名、密码
    Employee queryEmp(String empID, String empPassword);
    //查询邮箱和号码
    List<Employee> queryPhoneMail(@Param("empPhone") String empPhone,@Param("empMail") String empMail);
    //注册员工、添加员工
    int registerEmp(Employee employee);
    //查询所有员工
    List<Employee> allEmp();
    //通过id查询到员工
    Employee getEmpByID(@PathVariable("EmpID") Integer EmpID);
    //修改员工
    int updateEmp(Employee employee);
    //根据id删除员工
    int delEmp(@PathVariable("EmpID") Integer EmpID);
    //查询员工
    List<Employee> selectEmp(Map map);
    //查询员工的ID和姓名
    Employee queryIDName(@Param("reporterID") Integer reporterID, @Param("reporterName") String reporterName);
    //注册员工，并返回用户ID
    Employee registerEmp2(String empPhone);
    //查询邮箱
    Employee queryMail(@Param("empMail") String empMail);
}