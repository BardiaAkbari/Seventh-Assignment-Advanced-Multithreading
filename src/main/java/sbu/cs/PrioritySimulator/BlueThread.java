package sbu.cs.PrioritySimulator;

import java.util.concurrent.CountDownLatch;

public class BlueThread extends ColorThread {

    private static final String MESSAGE = "hi finished blacks, hi whites!";
    private CountDownLatch countDownLatch;
    public BlueThread(CountDownLatch countDownLatch){
        this.countDownLatch = countDownLatch;
    }

    void printMessage() {
        super.printMessage(new Message(this.getClass().getName(), getMessage()));
    }

    @Override
    String getMessage() {
        return MESSAGE;
    }

    @Override
    public void run() {
        // TODO call printMessage
        printMessage();
        countDownLatch.countDown();
    }
}
