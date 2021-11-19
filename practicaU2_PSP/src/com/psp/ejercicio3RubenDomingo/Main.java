package com.psp.ejercicio3RubenDomingo;

public class Main {
	public static void main(String[] args) {
		Carrera carrera = new Carrera();

		Thread galgo = new Thread(carrera, "GALGO");
		Thread liebre = new Thread(carrera, "LIEBRE");
		Thread conejo = new Thread(carrera, "CONEJO");

		galgo.start();
		liebre.start();
		conejo.start();
	}
}
