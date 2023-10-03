package org.example.java.concurrent.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicDemo {
    public static void main(String[] args) {
        /** AtomicInteger - потокобезопасная обертка над примитивным типом int
         * такж есть AtomicDouble, AtomicLong, AtomicBoolean, AtomicReference */
        AtomicInteger atomicInteger = new AtomicInteger(20);
//        atomicInteger.incrementAndGet(), decrementAndGet() - инкрементит/декрементит значение и возвращает результат
//        atomicInteger.addAndGet(int delta) -  добавляет delta и возвращает результат
        atomicInteger.incrementAndGet();
        System.out.println(atomicInteger);      // 21
        atomicInteger.addAndGet(9);
        System.out.println(atomicInteger);      // 30

    }
}
