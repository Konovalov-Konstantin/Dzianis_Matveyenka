package org.example;

import org.example.database.ConnectionPool;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContext {
    public static void main(String[] args) {
        ConnectionPool pool;
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml")) {

            pool = context.getBean("p1", ConnectionPool.class);
            System.out.println("ConnectionPool -->  " + pool);

        }
    }
}
