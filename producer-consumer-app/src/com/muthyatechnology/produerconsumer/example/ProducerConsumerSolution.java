package com.muthyatechnology.produerconsumer.example;

import java.util.Vector;

/**
 * Java program to solve Producer Consumer problem using wait and notify
 * method in Java. Producer Consumer is also a popular concurrency design pattern.
 *
 * @author Nagaraju
 */
public class ProducerConsumerSolution {

    public static void main(String args[]) {
        Vector sharedQueue = new Vector();
        Vector sharedQueueTwo = new Vector();
        int size = 4;
        Thread prodThread = new Thread(new Producer(sharedQueue, size), "Producer");
        Thread consThread = new Thread(new Consumer(sharedQueue, size), "Consumer");
        prodThread.start();
        consThread.start();
        
        Thread prodThreadTwo = new Thread(new Producer(sharedQueueTwo, size), "ProducerTwo");
        Thread consThreadTwo = new Thread(new Consumer(sharedQueueTwo, size), "ConsumerTwo");
        prodThreadTwo.start();
        consThreadTwo.start();
    }
}





