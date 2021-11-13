package com.psp.ejercicio2;

public class  CenaFilosofos {

	 public static void main(String[] args) throws Exception {

	        Filosofo[] philosophers = new Filosofo[5];
	        Object[] forks = new Object[philosophers.length];

	        for (int i = 0; i < forks.length; i++) {
	            forks[i] = new Object();
	        }

	        for (int i = 0; i < philosophers.length; i++) {

	            Object leftFork = forks[i];
	            Object rightFork = forks[(i + 1) % forks.length];

	            if (i == philosophers.length - 1) {
	                philosophers[i] = new Filosofo(rightFork, leftFork); 
	            } else {
	                philosophers[i] = new Filosofo(leftFork, rightFork);
	            }

	            Thread t = new Thread(philosophers[i], "Filósofo " + (i + 1));
	            t.start();
	        }
	    }
	}
