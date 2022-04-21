package com.practice;


import com.practice.dao.EmployeeDao;
import com.practice.pojo.Employee;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;

@SpringBootTest
class SpringbootProjectApplicationTests {



    @Test
    void contextLoads() throws SQLException {

    }

}
