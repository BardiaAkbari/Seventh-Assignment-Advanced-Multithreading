package sbu.cs.Semaphore;

import java.sql.Time;
import java.util.concurrent.Semaphore;

public class Operator extends Thread {
    private final Semaphore semaphore;
    public Operator(String name, Semaphore semaphore) {
        super(name);
        Controller.addToOperators(this);
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName()+ " Thread is access to resource at " + System.currentTimeMillis() + " in milli second.");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Resource.accessResource();
            semaphore.release();
            // critical section - a Maximum of 2 operators can access the resource concurrently
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
