package com.teraenergy.bisolution;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
public class javaDbConnTest {
    @Autowired
	private ApplicationContext context;
	
	@Autowired
	private SqlSessionFactory sessionFactory;
	
	@Test
	void contextLoads() {}
	
	@Test
	public void testApplicationContext() {
		try {
			System.out.println("=========================================");
			System.out.println(context.getBean("sqlSessionFactory"));
			System.out.println("=========================================");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSqlSessionFactory() {
		try {
			System.out.println("=========================================");
			System.out.println(sessionFactory.toString());
			System.out.println("=========================================");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
