package com.psp.ejercicio2;



public class Filosofo implements Runnable {

    private final Object leftFork;
    private final Object rightFork;

    Filosofo(Object left, Object right) {
        this.leftFork = left;
        this.rightFork = right;
    }

    private void doAction(String action) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " " + action);
        Thread.sleep(((int) (Math.random() * 3000)));
    }

    @Override
    public void run() {
        try {
          
          doAction("que estoy haciendo?"); 

		  while (true) {
               
                synchronized (leftFork) {

                	synchronized (rightFork) {
                    }
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}