package sbu.cs.Semaphore;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Controller {

    /**
     * A number of operators are trying to access a resource in parallel to each other.
     * A maximum of 2 operators should access the resource concurrently. Once 2 operators have entered
     the critical section, others must wait for at least one of them to leave.

     * Use a Semaphore to solve this synchronization problem. Semaphores behave similarly to locks,
     with the exception that they can allow more than 1 thread to enter the critical section.
     * Similar to a lock, a single instance of the Semaphore class must be shared between multiple
     threads, otherwise the threads won't be synchronized.

     * You are allowed to add new code to any of the three provided classes or change the existing
     lines of code, as long as you use a Semaphore to solve this problem.
     * Every time a thread accesses the resource, print its name and the system time.
     */
    private static ArrayList<Operator> operators = new ArrayList<>();
    public static void addToOperators(Operator operator){
        operators.add(operator);
    }
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(2);


        Operator operator1 = new Operator("operator1", semaphore);
        Operator operator2 = new Operator("operator2", semaphore);
        Operator operator3 = new Operator("operator3", semaphore);
        Operator operator4 = new Operator("operator4", semaphore);
        Operator operator5 = new Operator("operator5", semaphore);

        operator1.start();
        operator2.start();
        operator3.start();
        operator4.start();
        operator5.start();

        for(Operator operator : operators){
            operator.join();
        }
    }
}
