package com.psp.ejercicio6RubenDomingo;

public class Transaccion {
	public static void main(String[] args) {
		Banco banco = new Banco();
		
		Thread cliente1 = new Thread(banco, "Jhon");
		Thread cliente2 = new Thread(banco, "Elena");
		Thread cliente3 = new Thread(banco, "Juan");
		
		cliente1.start();
		cliente2.start();
		cliente3.start();
		
		//Además del mecanismo utilizado (sinchronized), existen otros mecanismos para proporcionar la exclusión mutua 
		//como pueden ser el uso de los métodos wait/notify, o implementar el uso del ScheduledExecutorService. 
	}
}
