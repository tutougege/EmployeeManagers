package com.practice.controller;

import com.fasterxml.jackson.core.JsonToken;
import com.practice.dao.DepartmentDao;
import com.practice.dao.EmployeeDao;
import com.practice.pojo.Department;
import com.practice.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
        EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;

    @RequestMapping({"/"})
    public String login(){
        return "toemp";
    }

    @RequestMapping("/tologin")
    public String tologin(){
        return "login";
    }

    @GetMapping("/login")
    public  String register(String empID, String empPassword, Model model, HttpSession session){
            Employee employee = employeeDao.queryEmp(empID, empPassword);
            if (employee == null) {
                model.addAttribute("msg", "用户ID或密码错误");
                 return "login";
            } if(Integer.parseInt(empID) == employee.getEmpID() && empPassword.equals(employee.getEmpPassword())) {
                Employee  empByID = employeeDao.getEmpByID(Integer.parseInt(empID));
                //其他方法获取这个session
                session.setAttribute("empID",empByID.getEmpID());
                session.setAttribute("LoginUser", empByID.getEmpName());
                return "Main";
        }
        return null;
    }

    @RequestMapping("/toregister")
    public String toregister(Model model){
        List<Department> allDept = departmentDao.getAllDept();
        model.addAttribute("dept",allDept);
        return "Register";
    }

      @PostMapping("/register")
      public String register(Employee employee, String empPhone, String empMail,Model model){
        /*注意注册的select中name属性的名字要跟实体类中字段的名字一样*/
          System.out.println("==>"+employee);
          List<Employee> employees = employeeDao.queryPhoneMail(empPhone, empMail);
          int index=employees.size();
          if (employees.isEmpty()){
              employeeDao.registerEmp(employee);
              Employee employee2 = employeeDao.registerEmp2(employee.getEmpPhone());
              model.addAttribute("msg2", "注册成功，请记住您的登录ID："+employee2.getEmpID());
              List<Department> allDept = departmentDao.getAllDept();
              model.addAttribute("dept",allDept);
              return "Register";
          }
          if(employees.get(index-1).getEmpPhone().equals(empPhone)){
              model.addAttribute("msg","手机号已经被注册过了");
              List<Department> allDept = departmentDao.getAllDept();
              model.addAttribute("dept",allDept);
              return "Register";
          }else if(employees.get(index-1).getEmpMail().equals(empMail)){
              model.addAttribute("msg","邮箱已经被注册过了");
              List<Department> allDept = departmentDao.getAllDept();
              model.addAttribute("dept",allDept);
              return "Register";
          }
          return null;
      }

    @RequestMapping("/about")
    public String toAbout(){
        return "about";
    }

    @RequestMapping("/toMain")
    public String toMain(){

        return "Main";
    }

    @RequestMapping("/out")
    public String logout(HttpSession session){
       session.invalidate();
        return "login";
    }
    //个人信息修改
    @RequestMapping("/Info/update")
    public String updateInfo(Employee employee,Model model){
        employeeDao.updateEmp(employee);
        model.addAttribute("msg2","修改成功");
        return "redirect:/checkInfo";
    }
    //查看个人信息
    @RequestMapping("/checkInfo")
    public String checkInfo(HttpServletRequest request,Model model){
        //获取登录的员工ID，得到该员工对应的相关信息
        Employee empByID = employeeDao.getEmpByID((Integer) request.getSession().getAttribute("empID"));
        model.addAttribute("Info",empByID);
        List<Department> allDept = departmentDao.getAllDept();
        model.addAttribute("dept",allDept);
        return "Info/checkInfo";
    }

}
