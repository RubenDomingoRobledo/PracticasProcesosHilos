package com.psp.ejercicio5RubenDomingo;

public class JoinThreads {
	public static void main(String[] args) {
		Hilo hilo1 = new Hilo("H1");
		Hilo hilo2 = new Hilo("H2");
		Thread h1 = new Thread(hilo1);
		Thread h2 = new Thread(hilo2);
		h1.start();
		h2.start();
		hilo1.setHilo(h2);
		
		System.out.println("Hilo principal terminado sin esperar a los hilos");
	}
}
