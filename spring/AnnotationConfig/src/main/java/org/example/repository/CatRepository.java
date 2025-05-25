package org.example.repository;

import org.example.bpp.InjectBean;
import org.example.pool.CatConnectionPool;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository
public class CatRepository {

    @InjectBean // своя аннотация, которая инжектит подходящий бин из контекста(см. InjectBeanPostProcessor.class)
    private CatConnectionPool catConnectionPool;


    @PostConstruct
    // метод, который должен быть выполнен после создания бина, но перед его использованием (можно подкрутить настройки бина)
    private void init(){
        System.out.println(" *** CatRepository init method");
    }
}
