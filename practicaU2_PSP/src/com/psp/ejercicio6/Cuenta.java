package com.psp.ejercicio6;

public class Cuenta implements Runnable{
	private Thread hilo = null;
	public int saldo = 0;

	public Cuenta(String str) {
		hilo= new Thread (this, str);
		hilo.start();
	}

	@Override
	public void run() {
		System.out.println("Saldo de la cuenta: " + saldo);
		for (int i = 0; i <= 3; i++) {
			ingreso(1000);
			System.out.println(hilo.getName() + " ingresa 1000 € en la cuenta");
			
			retirada(2000);
			System.out.println(hilo.getName() + " saca 2000 € de la cuenta");
			System.out.println("Saldo de la cuenta: " + saldo);
		}
	}
	
	public void ingreso(int cantidad) {
		saldo += cantidad;
	}
	
	synchronized public boolean retirada(int cantidad) {
		saldo -= cantidad;
		if (saldo < 0) {
			return false;
		}
		else {
			return true;
		}
	}
}
