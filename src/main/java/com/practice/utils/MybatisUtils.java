package com.practice.utils;

import com.sun.org.apache.bcel.internal.generic.F2L;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author liwei
 * @create 2022-04-26-14:19
 **/
public class MybatisUtils {
    private static SqlSessionFactory factory;
    private static ThreadLocal<SqlSession> local = new ThreadLocal<>();
    static {
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            factory = new SqlSessionFactoryBuilder().build(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //读取mybatis和数据库的会话
    }
    public static SqlSession getSqlSession(){
        SqlSession sqlSession = local.get();
        if(sqlSession == null){
            sqlSession = factory.openSession();
            local.set(sqlSession);
        }
        return sqlSession;
    }
    public static <T extends Object> T getMapper(Class<T> tClass){
        SqlSession sqlSession = getSqlSession();
        return sqlSession.getMapper(tClass);
    }
}
