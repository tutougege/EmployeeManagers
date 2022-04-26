package com.practice.test;

import com.practice.dao.EmployeeDao;
import com.practice.pojo.Employee;
import com.practice.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import java.util.List;
/**
 * @author liwei
 * @create 2022-04-25-20:42
 **/
public class test01 {
    @Test
    public void testAllEmp() {
//            SqlSession sqlSession = MybatisUtils.getSqlSession();
            //动态代理并用mapper中的sql语句实现对应方法
        EmployeeDao employeeDao = MybatisUtils.getMapper(EmployeeDao.class);
        List<Employee> employees = employeeDao.allEmp();
        for (Employee e :
                employees) {
            System.out.println(e);
        }
    }
}
