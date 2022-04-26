package com.practice.test;

import com.practice.dao.EmployeeDao;
import com.practice.pojo.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author liwei
 * @create 2022-04-25-20:42
 **/
public class test01 {
    @Test
    public void testAllEmp(){
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("mybatis-config.xml");
            //读取mybatis和数据库的会话
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
            SqlSession sqlSession = factory.openSession();
            //动态代理并用mapper中的sql语句实现对应方法
            EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
            List<Employee> employees = employeeDao.allEmp();
            for (Employee e:
                 employees) {
                System.out.println(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
