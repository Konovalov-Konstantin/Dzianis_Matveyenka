package org.example.repository;

import org.example.bpp.InjectBean;
import org.example.bpp.Logging;
import org.example.pool.CatConnectionPool;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Logging    // своя аннотация, которая логирует все методы классов, помеченных аннотацией @Logging
public class CatTestLogger implements MyLogger {

    @InjectBean // своя аннотация, которая инжектит подходящий бин из контекста(см. InjectBeanPostProcessor.class)
                // это для примера, есть готовая - @Autowired
    private CatConnectionPool catConnectionPool;


    @PostConstruct
    // метод, который должен быть выполнен после создания бина, но перед его использованием (можно подкрутить настройки бина)
    private void init(){
        System.out.println(" *** CatTestLogger init method");
    }

    @Override
    public void testLogging() {
        // при вызове метода должен вывести в консоль 'Logging method testLogging of class CatTestLogger' из LoggingBeanPostProcessor.postProcessAfterInitialization
    }
}
