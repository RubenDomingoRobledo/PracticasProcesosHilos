package com.psp.ejercicio2RubenDomingo;

public class  CenaFilosofos {

	 public static void main(String[] args) throws Exception {
		 	//Creacion de los filosofos
	        Filosofo[] philosophers = new Filosofo[5];
	        //Crea la misma cantidad de palillos que de filosofos
	        Object[] forks = new Object[philosophers.length];

	        //Creacion de los palillos 
	        for (int i = 0; i < forks.length; i++) {
	            forks[i] = new Object();
	        }
	        
	        for (int i = 0; i < philosophers.length; i++) {
	        	//Palillos izquierdos
	            Object leftFork = forks[i];
	            //Palillos derechos, el modulo es para que el 5º filosofo coja el palillo izquierdo del 1º
	            Object rightFork = forks[(i + 1) % forks.length];
	            
	            //Crea un filosofo con los palillos asignados
	            if (i == philosophers.length - 1) {
	                philosophers[i] = new Filosofo(rightFork, leftFork); 
	            } else {
	                philosophers[i] = new Filosofo(leftFork, rightFork);
	            }
	            //Crea un hilo para cada filosofo y lo inicia.
	            Thread t = new Thread(philosophers[i], "Filósofo " + (i + 1));
	            t.start();
				
	        }
	    }
	}
