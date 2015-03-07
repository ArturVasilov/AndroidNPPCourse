package ru.guar7387.multithreading;

public class ThreadsDemo {

    public void run() {

    }

    private synchronized void syncMethod() {

    }

    private final Object object = new Object();

    private void syncMethodWithMonitor() {
        synchronized (object) {

        }

        synchronized (this) {

        }
    }

    private synchronized static void syncStaticMethod() {

    }

    private static void a() {
        synchronized (ThreadsDemo.class) {

        }
    }


}
