package sbu.cs.CalculatePi;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PiCalculator {

    /**
     * Calculate pi and represent it as a BigDecimal object with the given floating point number (digits after . )
     * There are several algorithms designed for calculating pi, it's up to you to decide which one to implement.
     Experiment with different algorithms to find accurate results.

     * You must design a multithreaded program to calculate pi. Creating a thread pool is recommended.
     * Create as many classes and threads as you need.
     * Your code must pass all of the test cases provided in the test folder.

     * @param floatingPoint the exact number of digits after the floating point
     * @return pi in string format (the string representation of the BigDecimal object)
     */
    public static class MyClass implements Runnable{
        private int number;
        public MyClass (int number) {
            this.number = number;
        }
        @Override
        public void run() {
            BigDecimal sign = new BigDecimal(1);
            if(number % 2 == 0)
                sign = new BigDecimal(-1);
            BigDecimal up = new BigDecimal(4);
//            BigDecimal up = new BigDecimal(1);
            up = up.multiply(sign);
            int myNumber = number * 2 - 1;
//            int myNumber = (number * 2) * (number * 2 + 1) * (number * 2 + 2);
            BigDecimal down = new BigDecimal(myNumber);
            BigDecimal result = up.divide(down, new MathContext(1001));
            addToBigDecimal(result);
        }
    }

    public static BigDecimal bigDecimal = new BigDecimal(0);

    public static synchronized void addToBigDecimal(BigDecimal value){
        bigDecimal = bigDecimal.add(value);
    }

    public static String calculate(int floatingPoint) {
        ExecutorService executorService = Executors.newFixedThreadPool(40);;

        for(int i = 1; i <= 100000000; i++){
            MyClass myClass = new MyClass(i);
            executorService.execute(myClass);
        }

        executorService.shutdown();
        try {
            executorService.awaitTermination(100000, TimeUnit.MILLISECONDS);
        }
        catch (InterruptedException e) {
            e.printStackTrace();

        }
        bigDecimal = bigDecimal.multiply(new BigDecimal(1), new MathContext(1001));
        bigDecimal = bigDecimal.multiply(new BigDecimal(1), new MathContext(floatingPoint + 1, RoundingMode.FLOOR));

        return bigDecimal.toString();
    }

    public static void main(String[] args) {
        // Use the main function to test the code yourself
        System.out.println(calculate(8));
    }
}
