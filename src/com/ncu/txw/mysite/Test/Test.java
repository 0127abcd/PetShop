package com.ncu.txw.mysite.Test;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	private ApplicationContext  ctx = null; 
	
	{
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	
	@org.junit.Test
	public void test() throws SQLException {
		DataSource datasource = ctx.getBean(DataSource.class);
		System.out.println(datasource.getConnection());
	}

}
