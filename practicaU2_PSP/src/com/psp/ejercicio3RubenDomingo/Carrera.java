package com.psp.ejercicio3RubenDomingo;

public class Carrera implements Runnable{
	
	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			System.out.println(Thread.currentThread().getName()+ "->" + (i*100) + "mt");
			if (i == 10) {
				System.out.println("# Llego a la meta el "+ Thread.currentThread().getName() + "#");
			}
			try {
				Thread.sleep((long)(Math.random() * 1000));
			}
			catch (InterruptedException e){}
		}
	}

}
