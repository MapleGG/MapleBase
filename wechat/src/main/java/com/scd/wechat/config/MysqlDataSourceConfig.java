package com.scd.wechat.config;

import javax.sql.DataSource;

import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@MapperScan(basePackages=MysqlDataSourceConfig.PACKAGE, sqlSessionFactoryRef="mySqlSessionFactory")
public class MysqlDataSourceConfig {
	// 精确到 master 目录，以便跟其他数据源隔离
    static final String PACKAGE = "com.scd.wechat.mapper.mysql";
    static final String MAPPER_LOCATION = "classpath:mapper/mysql/*.xml";
    
    @Value("${app.datasource.mysql.url}")
    private String d2xurl;
    
    @Value("${app.datasource.mysql.username}")
    private String d2xusername;
    
    @Value("${app.datasource.mysql.password}")
    private String d2xpassword;
    
    @Value("${app.datasource.mysql.driverClassName}")
    private String d2xdriverClass;
    
    //配置第二个数据源
    @Bean(name="mysqlDB")
    public DataSource mysqlDataSource() {
    	UnpooledDataSource dataSource=new UnpooledDataSource();
    	dataSource.setDriver(d2xdriverClass);
    	dataSource.setUrl(d2xurl);
    	dataSource.setUsername(d2xusername);
    	dataSource.setPassword(d2xpassword);
    	return dataSource;
    }
    
    @Bean(name="mysqlTransactionManager")
    public DataSourceTransactionManager mysqlTransactionManager() {
    	return new DataSourceTransactionManager(mysqlDataSource());
    }
    
  //生成 SqlSessionFactory 实例bean
    @Bean(name = "mySqlSessionFactory")
    public SqlSessionFactory mysqlSqlSessionFactory(@Qualifier("mysqlDB") DataSource mysqlDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        
        //设置数据源
        sessionFactory.setDataSource(mysqlDataSource);
        
        ClassPathResource resource = new ClassPathResource("mybatis.xml");
        sessionFactory.setConfigLocation(resource);
        
        //配置mapper.xml文件路径
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(MysqlDataSourceConfig.MAPPER_LOCATION));
        
        
        return sessionFactory.getObject();
    }
}
