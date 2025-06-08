package multithreading.task;

public class MyClassExtends extends Thread{
    private String name;
    private int max;

    public MyClassExtends(String name, int max) {
        this.name = name;
        this.max = max;
    }

    public void run() {
        System.out.println(name + " task started");
        for (int i = 0; i < max; i++) {
            System.out.println("Task " + name + ", count = " + i);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(name + " task finished");
    }
}
