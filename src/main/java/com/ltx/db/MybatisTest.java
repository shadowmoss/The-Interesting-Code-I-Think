package com.ltx.db;

import com.ltx.db.entity.UserDO;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Properties;

public class MybatisTest {
    public static void main(String[] args) throws IOException {
        // 读取XML配置创建SqlSessionFactory方式(单数据源配置)
        String resource = "mybatis-config-single.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory singleDataSource = new SqlSessionFactoryBuilder().build(inputStream);
        // 纯Java代码的SqlSessionFactory配置方式,需要自己创建配置建造器。
//        try{
//            SqlSession session = sqlSessionFactory.openSession();
//            UserDO userDO = session.selectOne("com.ltx.db.mapper.UserMapper.selectOneUser", 1);
//            System.out.println(userDO);
//        }catch (Exception e){
//            System.out.println(e);
//        }
        // 多数据源配置

        // 不同的数据库配置
        Properties mysqlProperty = new Properties();
        mysqlProperty.setProperty("driver","com.mysql.cj.jdbc.Driver");
        mysqlProperty.setProperty("url","jdbc:mysql://192.168.154.130:3306/mybatis-test?useUnicode=true&characterEncoding=utf8&serverTimeZone=GMT%2B8&allowMultQueries=true");
        mysqlProperty.setProperty("username","root");
        mysqlProperty.setProperty("password","forbug123");
        String multipleResource = "mybatis-config-muiltple.xml";
        Reader resourceAsReader = Resources.getResourceAsReader(multipleResource);
        // 专用于mysql的sqlSession
        SqlSessionFactory mysqlSqlSession = new SqlSessionFactoryBuilder().build(resourceAsReader,"mysql",mysqlProperty);
//        try
//        {
            SqlSession session = mysqlSqlSession.openSession();
            UserDO userDO = session.selectOne("com.ltx.db.mapper.UserMapper.selectOneUser",1);
//            String databaseProductName = session.getConnection().getMetaData().getDatabaseProductName();
//            System.out.println("154.130:databaseProductName:"+databaseProductName);
            System.out.println(userDO);
//        }
//        catch (Exception e){
//
//        }

        // 根据第二个环境的配置生成专属的mysqlTwoSqlSession
//        Properties mysqlTwoProperty = new Properties();
//        mysqlTwoProperty.setProperty("driver","com.mysql.cj.jdbc.Driver");
//        mysqlTwoProperty.setProperty("url","jdbc:mysql://172.18.0.2:3306/todos?useUnicode=true&characterEncoding=utf8&serverTimeZone=GMT%2B8&allowMultQueries=true");
//        mysqlTwoProperty.setProperty("username","root");
//        mysqlTwoProperty.setProperty("password","secret");
//        Reader mysqlTwoResource = Resources.getResourceAsReader(multipleResource);
//        SqlSessionFactory mysqlTwoSqlSession = new SqlSessionFactoryBuilder().build(mysqlTwoResource,"mysqlTwo",mysqlTwoProperty);
//        try{
//            SqlSession session = mysqlTwoSqlSession.openSession();
//            UserDO userDO = session.selectOne("com.ltx.db.mapper.UserMapper.selectOneUser",1);
//            String databaseProductName = session.getConnection().getMetaData().getDatabaseProductName();
//            System.out.println("172.0.2:databaseProductName:"+databaseProductName);
//            System.out.println(userDO);
//        }catch(Exception e){
//            System.out.println(e);
//        }
    }
}
