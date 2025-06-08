package multithreading;

import multithreading.task.MyClassExtends;
import multithreading.task.MyTaskImplements;

public class MultithreadingAppl {
    private static final int MAX = 10;
    private static final int SIZE = 3;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Main thread started");
        Runnable task1 = new MyTaskImplements("taskImplements",MAX);
        // N B - implements Runnable лучше - так мы можем потом ещё и унаследоваться через extends от чего нибудь

//        task1.run();  // сработает в одном треде - сперва выполнится эта часть, потом продолжится мейн

        //a вот так будет работать многопоточно!
        Thread thread1 = new Thread(task1);
        Thread thread2 = new MyClassExtends("TaskExtends", MAX);

        //  групповая работа (можно было сделать в одном цикле, но так нагляднее)
        // Создание тасков -
        Runnable[] tasks = new Runnable[SIZE];
        for (int i = 0; i < tasks.length; i++) {
            tasks[i] = new MyTaskImplements("Tasks.Implements"+i, SIZE);
        }
        // создание тредов под каждый таск
        Thread[] threads = new Thread[SIZE];
        for (int i = 0; i < tasks.length; i++) {
            threads[i] = new Thread(tasks[i]);
        }
        //и запуск тредов!
        for (int i = 0; i < tasks.length; i++) {
            threads[i].start();
        }

        thread1.start();
        thread2.start();


        for (int i = 0; i < MAX; i++) {
            System.out.println("Main thread, count " + i);
            Thread.sleep(1);
        }

        thread1.join(); //хочу, чтобы Main thread завершался ПОСЛЕ thread1 (taskImplements)
        thread2.join(); //... и thread2 (taskExtends)
        for (int i = 0; i < SIZE; i++) {    //...и после всех тредов из группы!
            threads[i].join();
        }
        System.out.println("Main thread finished");
    }
}
