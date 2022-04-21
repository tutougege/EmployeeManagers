package com.practice.controller;

import com.practice.dao.DepartmentDao;
import com.practice.dao.EmployeeDao;
import com.practice.pojo.Department;
import com.practice.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/emp")
public class EmployeeManage {
    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;

    @RequestMapping("/emps")
    public String employeeManage(Model model){
        List<Employee> employees = employeeDao.allEmp();
        model.addAttribute("allEmp",employees);
        return "emp/employeeManage";
    }
    @RequestMapping("/toupdate/{EmpID}")
    public String toupdate(@PathVariable ("EmpID") Integer EmpID,Model model){
        Employee empByID = employeeDao.getEmpByID(EmpID);
        List<Department> allDept = departmentDao.getAllDept();
        model.addAttribute("dept",allDept);
        model.addAttribute("emp",empByID);
        return "emp/update";
    }

    @RequestMapping("/update")
    public String update(Employee employee){
        //这里还需要思考一下
        employeeDao.updateEmp(employee);
        return "redirect:/emp/emps";
    }
    //删除
    @RequestMapping("/delEmp/{EmpID}")
    public String delEmp(@PathVariable("EmpID") Integer EmpID,Model model){
        employeeDao.delEmp(EmpID);
        return "redirect:/emp/emps";
    }
    @RequestMapping("/toaddEmp")
    public String toaddEmp(Model model){
        List<Department> allDept = departmentDao.getAllDept();
        model.addAttribute("dept",allDept);
        return "emp/add";
    }
    @GetMapping("/addEmp")
    public String addEmp(Employee employee,String empPhone,String empMail,Model model){
        List<Employee> employees = employeeDao.queryPhoneMail(empPhone, empMail);
        System.out.println(employees);
        int index=employees.size();
        if(employees.isEmpty()) {
            employeeDao.registerEmp(employee);
            model.addAttribute("msg2", "添加成功");
            List<Department> allDept = departmentDao.getAllDept();
            model.addAttribute("dept",allDept);
            return "emp/add";
        }
        if(employees.get(index-1).getEmpPhone().equals(empPhone)){
            model.addAttribute("msg","手机号已经被使用过了");
            List<Department> allDept = departmentDao.getAllDept();
            model.addAttribute("dept",allDept);
            return "emp/add";
        }else if(employees.get(index-1).getEmpMail().equals(empMail)){
            model.addAttribute("msg","邮箱已经被使用过了");
            List<Department> allDept = departmentDao.getAllDept();
            model.addAttribute("dept",allDept);
            return "emp/add";
        }
        return "redirect:/emp/emps";
    }

    //查询用户信息
    @GetMapping("/selectEmp")
    public String selectEmp( String message,Model model){

        HashMap map=new HashMap();
        if (message.contains("1")) {
            try {
                map.put("empID", Integer.parseInt(message));
            }catch (IllegalArgumentException e){
                e.printStackTrace();
                model.addAttribute("msg","未查询到此员工");
                return "emp/employeeManage";
            }
        } else {
            map.put("empName",message);
        }
        List<Employee> employees = employeeDao.selectEmp(map);
        //注意数组是一个对象，不能用employees==null判断，需要使用isEmpty
        if(employees.isEmpty()){
            model.addAttribute("msg","未查询到此员工");
            return "emp/employeeManage";
        }
        model.addAttribute("list",employees);
        return "emp/empInfo";
    }

}
