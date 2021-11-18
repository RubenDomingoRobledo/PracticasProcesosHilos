package com.psp.ejercicio2;

import java.time.LocalTime;

//Creacion de la clase persona con sus correspondientes atributos, metodos, y constructor implementando la interfaz Runnable
public class Filosofo implements Runnable {

    private final Object leftFork;
    private final Object rightFork;

    Filosofo(Object left, Object right) {
        this.leftFork = left;
        this.rightFork = right;
    }

    private void doAction(String action) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " " + action);
        //Cada filosofo espera un tiempo aleatorio entre 0 y 3000 ms
        Thread.sleep(((int) (Math.random() * 3000)));
    }

    @Override
    public void run() {
        try {
        	//Implementamos clase LocalTime y metodo now para obtener la hora actual,
        	//y quitamos los milisegundos con withNano(0)
        	//Todos los filosofos comienzan pensando
        	doAction(LocalTime.now().withNano(0) + " Pensando"); 
        	
        	//Creamos bucle para sincronizar cuando un filosofo coje el palillo izquierdo o derecho
        	//y para cuando deja cada uno de los palillos en la mesa
        	while (true) {
                synchronized (leftFork) {
                	doAction(LocalTime.now().withNano(0) + " Coge el palillo izquierdo");
                	synchronized (rightFork) {
                		doAction(LocalTime.now().withNano(0) + " Coge el palillo derecho");
                		doAction(LocalTime.now().withNano(0) + " Deja el palillo derecho");
                    }
                	doAction(LocalTime.now().withNano(0) + " Deja palillo izquierdo. Se pone a pensar");
                	doAction(LocalTime.now().withNano(0) + " Pensando");
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}