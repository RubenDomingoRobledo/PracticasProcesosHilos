package com.psp.ejercicio6;

public class Cuenta {
	public int saldo = 0;
	
	public int getSaldo() {
		return saldo;
	}
	
	public void ingreso(int cantidad) {
		saldo += cantidad;
		System.out.println(Thread.currentThread().getName()+ " ha ingresado " + cantidad + " € en la cuenta");
	}
	
	public void retiro(int cantidad) {
		saldo -= cantidad;
	}
}
