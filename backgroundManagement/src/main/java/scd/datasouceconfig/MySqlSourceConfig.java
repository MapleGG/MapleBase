package scd.datasouceconfig;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;

@MapperScan(basePackages = "scd.mapper.EmployeeMapper",sqlSessionFactoryRef="mysqlSessionFactory")
@Configuration
public class MySqlSourceConfig {


    @Autowired
    private Environment env;

    @Bean(name="scdsfcdatasource")
    public DataSource getDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(env.getProperty("app.datasource.mysql.url"));
        dataSource.setUsername(env.getProperty("app.datasource.mysql.username"));
        dataSource.setPassword(env.getProperty("app.datasource.mysql.password"));
        dataSource.setDriverClassName(env.getProperty("app.datasource.mysql.driverClassName"));
        return dataSource;
    }
    @Bean(name = "mysqlSessionFactory")
    public SqlSessionFactory mysqlSessionFactory(@Qualifier("scdsfcdatasource") DataSource mysqlDataSource) {

        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(mysqlDataSource);


        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            //设置xml扫描路径
            bean.setMapperLocations(resolver.getResources("classpath:scd.mapper.*.xml"));
            return bean.getObject();
        } catch (Exception e) {
            throw new RuntimeException("sqlSessionFactory init fail",e);
        }
    }

    /**
     * 用于实际查询的sql工具,传统dao开发形式可以使用这个,基于mapper代理则不需要注入
     * @param mysqlSessionFactory
     * @return
     */
    @Bean(name = "mysqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory mysqlSessionFactory) {
        return new SqlSessionTemplate(mysqlSessionFactory);
    }
    /**
     * 事务管理,具体使用在service层加入@Transactional注解
     */
    @Bean(name = "transactionManager")
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(getDataSource());
    }
    
   

}