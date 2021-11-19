package com.psp.ejercicio6RubenDomingo;

public class Banco implements Runnable{
	Cuenta cuenta = new Cuenta();

	@Override
	public void run() {
		cuenta.ingresoDinero(1000);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		cuenta.retiroDinero(2000);
	}	
}
