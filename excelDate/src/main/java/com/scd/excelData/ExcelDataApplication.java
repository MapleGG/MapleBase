package com.scd.excelData;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
public class ExcelDataApplication extends SpringBootServletInitializer {

	 @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(ExcelDataApplication.class);
	    }
		
	    
		public static void main(String[] args) {
			//System.out.println("------------SpringBoot开始启动------------------");
			SpringApplication.run(ExcelDataApplication.class, args);
			//System.out.println("------------SpringBoot启动完成------------------");

		}
}
