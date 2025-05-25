package org.example;

import org.example.repository.MyLogger;
import org.example.repository.CompanyRepository;
import org.example.service.CompanyService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

//@Configuration
@ComponentScan
public class ApplicationContextAnnotationConfig {

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationContextAnnotationConfig.class)) {

            CompanyRepository repository = context.getBean(CompanyRepository.class);
            repository.getPools().forEach(pool-> System.out.println("pool -> " + pool));

            CompanyService companyService = context.getBean("companyService", CompanyService.class);
            companyService.findById(1);

//            ConnectionPool pool1 = context.getBean("pool1", ConnectionPool.class);
//            ConnectionPool pool2 = context.getBean("pool2", ConnectionPool.class);
//            System.out.println("ConnectionPool -->  " + pool1);
//            System.out.println("ConnectionPool -->  " + pool2);

//            Map<String, Object> properties = pool.getProperties();
//            for (Map.Entry<String, Object> stringObjectEntry : properties.entrySet()) {
//                System.out.print(stringObjectEntry.getKey() + "  ->  ");
//                System.out.println(stringObjectEntry.getValue());
//            }

            MyLogger catTestLogger = context.getBean("catTestLogger", MyLogger.class);
            catTestLogger.testLogging();    // тест сквозного функционала логирования (см.LoggingBeanPostProcessor, аннотация @Logging)
        }
    }
}
