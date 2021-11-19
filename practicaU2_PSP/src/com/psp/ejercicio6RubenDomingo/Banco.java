package com.psp.ejercicio6RubenDomingo;

public class Banco implements Runnable{
	Cuenta cuenta = new Cuenta();

	@Override
	public void run() {
		cuenta.ingreso(1000);
		retiroDinero(2000);
		if (cuenta.getSaldo()<0) {
			System.out.println("La cuenta posee saldo negativo");
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void retiroDinero(int cantidad) {
		if (cuenta.getSaldo() >= cantidad) {
			System.out.println("Saldo actual: " + cuenta.getSaldo());
			System.out.println("El usuario " + Thread.currentThread().getName() + " esta retirando " + cantidad + " € de la cuenta");
			cuenta.retiro(cantidad);
			System.out.println("El retiro se ha realizado correctamente. El nuevo saldo es de " + cuenta.getSaldo()+ "€");
		}
		else {
			System.out.println("No hay saldo suficiente para realizar la transacción porque la cuenta se quedaria en numeros rojos.");
		}
	}
}
