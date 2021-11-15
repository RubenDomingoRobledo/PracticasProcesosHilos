package com.psp.ejercicio1;

public class BarberoDurmiente {
	public static void main (String args[]) {
		int nBarberos=1, idCliente=1, nClientes=10, nSillas=5;
		
		Barberia barberia = new Barberia(nBarberos, nSillas);
    	
		System.out.println("Barberia Abierta.");
        
		for (int i = 0; i < nBarberos; i++) {
			Barbero barbero = new Barbero(barberia, 1);	
			Thread hiloBarbero = new Thread(barbero);
			hiloBarbero.start();
		}
		
        for (int i = 0; i < nClientes; i++) {
        	Cliente cliente = new Cliente(barberia);
            Thread hiloCliente = new Thread(cliente);
            cliente.setidCliente(idCliente++);
            hiloCliente.start();
            
            try {
            	long tiempo = (long) (Math.random() * 1000);
            	Thread.sleep(tiempo);	
            }
            catch(InterruptedException iex) {
                System.out.println("Fallo al ejecutar");
            }
        }
        
        
			System.out.println("La barberia ha cerrado");
	        System.out.println("Clientes totales: "+nClientes);
	        System.out.println("Total de clientes atendidos: "+barberia.getcortesPeloTotales());
	        System.out.println("Total de clientes perdidos: "+barberia.getclientesPerdidos());

	}
}

