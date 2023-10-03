package org.example.java.concurrent.threadPool;

import java.util.concurrent.*;

public class ThreadPoolDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        Executors.newSingleThreadExecutor() - создастся один поток в пуле
//        Executors.newFixedThreadPool(5) - создадутся 5 потоков в пуле
//        Executors.newCachedThreadPool() - безграничный пул, солько задач отравлено, столько потоков и создастся
//        Executors.newScheduledThreadPool(3) - используется для запуска по расписанию
//        Executors.newWorkStealingPool() - создаст оптимальное кол-во потоков в зависимости от кол-ва процессоров на компе

        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        Future<Integer> future = threadPool.submit(() -> {
            Thread.sleep(5000);
            System.out.println("Из метода future");
            return 1;
        });

        System.out.println("Result from future: " + future.get());
        threadPool.shutdown();  // закрывает потоки после выполения всех задач
        threadPool.awaitTermination(2, TimeUnit.SECONDS);    // ожидание пулом времени выполнения всех задач
        System.out.println("После выполнения метода awaitTermination");
    }
}
