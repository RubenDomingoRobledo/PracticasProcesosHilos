package com.psp.ejercicio5RubenDomingo;

import java.util.Random;

class Hilo implements Runnable {
	private final String nombre;
	private Thread hilo = null;
	
	Hilo(String nombre) {
		this.nombre = nombre;
	}
	
	void setHilo(Thread hilo) {
		this.hilo = hilo;
	}

	@Override
	public void run() {
		System.out.printf("Hola, soy el hilo: %s.\n", this.nombre);
		for (int i = 0; i < 5; i++) {
			Random r = new Random();
			int pausa = 10 + r.nextInt(500 - 10);
			System.out.printf("Hilo: %s hace pausa de %d ms.\n", this.nombre, pausa);
			
			//El hilo que se esta ejecutando espera a que el otro hilo termine su ejecucion para poder completar la suya
			if (hilo != null) {
				try {
					hilo.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			try {
				Thread.sleep(pausa);
			} catch (InterruptedException e) {
				System.out.printf("Hilo %s interrumpido.\n", this.nombre);
			}
		}
		System.out.printf("Hilo %s terminado.\n", this.nombre);
	}
}