package com.psp.ejercicio1;

class Barbero implements Runnable {								

    Barberia barberia;
    int idBarbero;
 
    public Barbero (Barberia barberia, int idBarbero) {
        this.barberia = barberia;
        this.idBarbero = idBarbero;
    }
    
    public void run() {
        while(true) {
        	barberia.cortarPelo(idBarbero);
        }
    }
}