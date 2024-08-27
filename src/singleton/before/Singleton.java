package singleton.before;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Singleton {
    static int numberOfThreads = 1000;
    public static void main(String[] args) {
        System.out.println("dddd");
        syncTest();

        eagerTest();
    }

    static void syncTest() {
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < numberOfThreads; i++) {
            executorService.execute(() -> {
                SynchSettings instance = SynchSettings.getInstance();
            });
        }
        long endTime = System.currentTimeMillis();
        // 모든 스레드가 종료될 때까지 대기
        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Synch Execution Time: " + (endTime - startTime) + " ms");
    }

    static void eagerTest() {
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < numberOfThreads; i++) {
            executorService.execute(() -> {
                EagerSettings instance = EagerSettings.getInstance();
            });
        }
        long endTime = System.currentTimeMillis();
        // 모든 스레드가 종료될 때까지 대기
        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Eager Execution Time: " + (endTime - startTime) + " ms");
    }

}
