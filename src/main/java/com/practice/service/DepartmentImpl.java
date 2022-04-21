package com.practice.service;

import com.practice.pojo.Department;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DepartmentImpl implements DepartmentDao{
    @Autowired
    private DepartmentDao departmentDao;
    @Override
    public List<Department> getAllDept() {
        return departmentDao.getAllDept() ;
    }
}
