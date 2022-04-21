package com.practice.dao;

import com.practice.pojo.Department;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface DepartmentDao {
    List<Department> getAllDept();
}
