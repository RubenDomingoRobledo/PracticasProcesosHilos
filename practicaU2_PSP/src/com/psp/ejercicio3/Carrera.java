package com.psp.ejercicio3;

public class Carrera implements Runnable{
	private Thread hilo = null;
	
	public Carrera (String str) {
		hilo= new Thread (this,str);
		hilo.start();
	}
	
	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			System.out.println(hilo.getName()+ "->" + (i*100) + "mt");
			if (i == 10) {
				System.out.println("# Llego a la meta el "+ hilo.getName() + "#");
			}
			try {
				Thread.sleep((long)(Math.random() * 1000));
			}
			catch (InterruptedException e){}
		}
	}

}
