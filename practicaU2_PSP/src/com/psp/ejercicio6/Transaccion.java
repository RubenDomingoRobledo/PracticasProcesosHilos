package com.psp.ejercicio6;

public class Transaccion {
	public static void main(String[] args) {
		Banco banco = new Banco();
		
		Thread cliente1 = new Thread(banco, "Jhon");
		Thread cliente2 = new Thread(banco, "Elena");
		Thread cliente3 = new Thread(banco, "Juan");
		
		cliente1.start();
		cliente2.start();
		cliente3.start();
	}
}
