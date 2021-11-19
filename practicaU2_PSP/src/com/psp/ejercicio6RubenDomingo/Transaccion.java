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
		
		//Adem�s del mecanismo utilizado (sinchronized), existen otros mecanismos para proporcionar la exclusi�n mutua 
		//como pueden ser el uso de los m�todos wait/notify, o implementar el uso del ScheduledExecutorService. 
	}
}
