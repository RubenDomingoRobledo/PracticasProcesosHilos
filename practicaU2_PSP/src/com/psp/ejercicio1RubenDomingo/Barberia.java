package com.psp.ejercicio1RubenDomingo;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

class Barberia {

	private final AtomicInteger cortesPeloTotales = new AtomicInteger(0);
	private final AtomicInteger clientesPerdidos = new AtomicInteger(0);
	int nSillas, nBarberos, barberosDisponibles;
    List<Cliente> listaClientes;
    
    Random r = new Random();	 
    
    public Barberia (int nBarberos, int nSillas){ 
        this.nSillas = nSillas;						
        listaClientes = new LinkedList<Cliente>();						
        this.nBarberos = nBarberos;									
        barberosDisponibles = nBarberos;
    }
 
    public AtomicInteger getcortesPeloTotales() {
    	cortesPeloTotales.get();
    	return cortesPeloTotales;
    }
    
    public AtomicInteger getclientesPerdidos() {
    	clientesPerdidos.get();
    	return clientesPerdidos;
    }
    
    public void cortarPelo(int idBarbero) {
        Cliente cliente;
        
        synchronized (listaClientes) {									
            while(listaClientes.size()==0) {
                System.out.println("Barbero"+idBarbero+" esta durmiendo... Esperando a que entre un cliente\n");
                try {
                	listaClientes.wait();								
                }
                catch(InterruptedException e) {
                    System.out.println("Fallo al ejecutar");
                }
            }
            
            cliente = (Cliente)((LinkedList<?>)listaClientes).poll();	
            System.out.println("Cliente"+cliente.getidCliente()+ " es atendido por el barbero" +idBarbero );
        }
       
        try {        	
        	barberosDisponibles--; 																								
            System.out.println("Barbero"+idBarbero+" corta el pelo a cliente"+ cliente.getidCliente());
        	
            long tiempo = (long) (Math.random() * 1000);
        	Thread.sleep(tiempo);
        	
        	System.out.println("Corte de pelo completado del cliente"+ cliente.getidCliente()+" por el barbero" + idBarbero +" en "+tiempo+ " milisegundos.");
        
        	cortesPeloTotales.incrementAndGet();         														
            barberosDisponibles++;			
        }
        
        catch(InterruptedException iex) {
        	System.out.println("Fallo al ejecutar");
        }
    }
 
    public void añadir(Cliente cliente) {
        System.out.println("Cliente"+cliente.getidCliente()+ " entra a la barberia");
 
        synchronized (listaClientes) {
            if(listaClientes.size() == nSillas) {						
                System.out.println("No hay sillas disponibles en este momento para el cliente"+ cliente.getidCliente() + ".El cliente se va a casa"); 
                clientesPerdidos.incrementAndGet();
                return;
            }
            
            else if (barberosDisponibles > 0) {							
            	((LinkedList<Cliente>)listaClientes).offer(cliente);
            	listaClientes.notify();
			}
            
            else {														
            	((LinkedList<Cliente>)listaClientes).offer(cliente);
            	System.out.println("Todos los barberos estan ocupados, el cliente" + cliente.getidCliente()+ " se sienta y espera a que termine alguno");
                if(listaClientes.size()==1) {
                	listaClientes.notify();
                }
            }
        }
    }
}
