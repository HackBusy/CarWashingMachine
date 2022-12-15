public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Create a shared object and a latch
        SharedObject obj = new SharedObject();
        CountDownLatch latch = new CountDownLatch(1);

        // Create and start the threads
        Thread t1 = new MyThread1(obj, latch);
        Thread t2 = new MyThread2(obj, latch);
        t1.start();
        t2.start();

        // Wait for the threads to finish
        t1.join();
        t2.join();
    }
}

class SharedObject {
    // A shared variable
    private int value = 0;

    // A synchronized method that updates the shared variable
    public synchronized void setValue(int value) {
        this.value = value;
    }

    // A synchronized method that returns the shared variable
    public synchronized int getValue() {
        return value;
    }
}

class MyThread1 extends Thread {
    private SharedObject obj;
    private CountDownLatch latch;

    public MyThread1(SharedObject obj, CountDownLatch latch) {
        this.obj = obj;
        this.latch = latch;
    }

    public void run() {
        // Update the shared variable and count down the latch
        obj.setValue(1);
        latch.countDown();
    }
}

class MyThread2 extends Thread {
    private SharedObject obj;
    private CountDownLatch latch;

    public MyThread2(SharedObject obj, CountDownLatch latch) {
        this.obj = obj;
        this.latch = latch;
    }

    public void run() {
        try {
            // Wait for the latch to reach zero
            latch.await();
        } catch (InterruptedException e) {
