package com.psp.ejercicio6RubenDomingo;

public class Cuenta {
	public int saldo = 0;
	
	public int getSaldo() {
		return saldo;
	}
	
	public void ingresoDinero(int cantidad) {
		saldo += cantidad;
		System.out.println(Thread.currentThread().getName()+ " ha ingresado " + cantidad + " € en la cuenta");
	}
	
	public synchronized void retiroDinero(int cantidad) {
		if (getSaldo() >= cantidad) {
			System.out.println("Saldo actual: " + getSaldo());
			System.out.println("El usuario " + Thread.currentThread().getName() + " esta retirando " + cantidad + " € de la cuenta");
			saldo -= cantidad;
			System.out.println("El retiro se ha realizado correctamente. El nuevo saldo es de " + getSaldo()+ "€");
		}
		else {
			System.out.println("No hay saldo suficiente para realizar la transacción porque la cuenta se quedaria en numeros rojos.");
		}
	}
}
