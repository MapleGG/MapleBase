package com.scd.mrpm01b.config;

import javax.sql.DataSource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;


@Configuration
@MapperScan(basePackages=DataSourceConfig.PACKAGE, sqlSessionFactoryRef="SqlSessionFactory")
public class DataSourceConfig {

    // 精确到 master 目录，以便跟其他数据源隔离
    static final String PACKAGE = "com.scd.mrpm01b.mapper";
    static final String MAPPER_LOCATION = "classpath:mapper/oracle/*.xml";
  
    @Value("${app.datasource.oracle.url}")
    private String url;
 
    @Value("${app.datasource.oracle.username}")
    private String user;
 
    @Value("${app.datasource.oracle.password}")
    private String password;
 
    @Value("${app.datasource.oracle.driverClassName}")
    private String driverClass;
    
    
    //配置数据源
    @Bean(name = "DataSource")
    @Primary
    public DataSource masterDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        
        return dataSource;
    }
    
    
    @Bean(name = "TransactionManager")
    @Primary
    public DataSourceTransactionManager masterTransactionManager() {
        return new DataSourceTransactionManager(masterDataSource());
    }
 

    //生成 SqlSessionFactory 实例bean
    @Bean(name = "SqlSessionFactory")
    @Primary
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("DataSource") DataSource imesDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        
        //设置数据源
        sessionFactory.setDataSource(imesDataSource);
        
        
        ClassPathResource resource = new ClassPathResource("config/mybatis.xml");
        sessionFactory.setConfigLocation(resource);
        
        //配置mapper.xml文件路径
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(DataSourceConfig.MAPPER_LOCATION));
        
        
        return sessionFactory.getObject();
    }
	

}
