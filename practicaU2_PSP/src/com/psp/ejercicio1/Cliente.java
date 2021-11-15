package com.psp.ejercicio1;

class Cliente implements Runnable {

    int idCliente;
 
    Barberia barberia;
 
    public Cliente(Barberia barberia) {
        this.barberia = barberia;
    }
 
    public int getidCliente() {									
        return idCliente;
    }
 
    public void setidCliente(int idCliente) {
        this.idCliente = idCliente;
    }
 
    public void run() {										
        cortePelo();
    }
    
    private synchronized void cortePelo() {			
    	barberia.añadir(this);
    }
}
