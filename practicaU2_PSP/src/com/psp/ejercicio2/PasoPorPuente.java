package com.psp.ejercicio2;


import java.util.Random;

//Creamos clase puente y definimos variables como el peso maximo, y el maximo numero de persona en el puente
//e inicializamos variables 
class Puente {  

  private static final int PESO_MAXIMO = 200;
  private static final int MAX_PERSONAS = 3;
  private int peso = 0;
  private int numPersonas = 0;

  //Creamos métodos getter y añadimos la palabra clave synchronized en su declaracion
  synchronized public int getPeso() {
    return peso;
  }

  synchronized public int getNumPersonas() {
    return numPersonas;
  }

//Creamos metodo para comprobar que el peso de la personas sea menor que el peso maximo que puede soportar el puente
//y para que no se sobrepase el numero maximo de personas
  synchronized public boolean autorizacionPaso(Persona persona) {

    boolean result;

    if (this.peso + persona.getPeso() <= Puente.PESO_MAXIMO && this.numPersonas < Puente.MAX_PERSONAS) {
      this.numPersonas++;
      this.peso += persona.getPeso();
      result = true;
    } else {
      result = false;
    }
    return result;
  }

  //Metodo para restar el peso de la persona al pseo maximo que uede soportar el puente
  synchronized public void terminaPaso(Persona persona) {
    this.peso -= persona.getPeso();
    this.numPersonas--;
  }
}

//Creamos clase persona con sus correspondientes atributos, metodos, y constructor implementando la interfaz Runnable
class Persona implements Runnable {
  private final Puente puente;

  private final String idPersona;
  private final int peso;
  private final int tMinPaso, tMaxPaso;

  public int getPeso() {
    return peso;
  }

  Persona(Puente puente, int peso, int tMinPaso, int tMaxPaso, String idP) {
    this.puente = puente;
    this.peso = peso;
    this.tMinPaso = tMinPaso;
    this.tMaxPaso = tMaxPaso;
    this.idPersona = idP;
  }

  //Creamos metodo abstracto para comprobar si la persona puede acceder al puente o tiene que esperar
  @Override
  public void run() {
	  
    boolean autorizado = false;
    while (!autorizado) {
      synchronized (this.puente) {
        autorizado = this.puente.autorizacionPaso(this);
      	System.out.println("> "+ idPersona+ " con peso "+ peso + " puede cruzar, puente soporta peso " + puente.getPeso() + ", con "+ puente.getNumPersonas() + " personas.");
        if (!autorizado) {
          try {
         	  System.out.println("# " + idPersona + " debe esperar.");
        	  this.puente.wait();
          } catch (InterruptedException ex) {
          }
        }
      }
      
      
    }
    
    //Generamos un numero aleatorio para ver el tiempo que tarda una persona en cruzar el puente
    // y hacemos que los demás hilos se mantengan dormidos durante ese tiempo
    Random r = new Random();
    int tiempoPaso = this.tMinPaso + r.nextInt(this.tMaxPaso - this.tMinPaso + 1);
    try {
    	System.out.println(idPersona + " va a tardar tiempo " + tiempoPaso + " en cruzar.");
    	Thread.sleep(1000*tiempoPaso); 
    } 
    catch (InterruptedException ex) {
    	
    }
    
    //Una vez que una persona haya terminado de cruzar el puente notifica a todos los demás hilos 
    //que ha terminado su ejecución
    synchronized (this.puente) {
      this.puente.terminaPaso(this);
      System.out.println("< " + idPersona+ " sale del puente, puente soporta peso " + puente.getPeso() + ", con "+ puente.getNumPersonas() + " personas.");
      puente.notifyAll();
    }
  }
}

//Método main
public class PasoPorPuente {

  public static void main(String[] args) {
	
	//Creamos objeto puente
    final Puente puente = new Puente();

    //Definimos variables
    int tMinParaLlegadaPersona = 1;
    int tMaxParaLlegadaPersona = 30;
    int tMinPaso = 10;
    int tMaxPaso = 50;
    int minPesoPersona = 40;
    int maxPesoPersona = 120;

    //Creamos bucle para generar numeros aleatorios para simular la llegada de la siguiente persona al puente y peso de la persona
    System.out.println(">>>>>>>>>>>> Comienza simulación.");
    Random r = new Random();
    int idPersona = 1;

    while (true) {

      int tParaLlegadaPersona = tMinParaLlegadaPersona + r.nextInt(
              tMaxParaLlegadaPersona - tMinParaLlegadaPersona + 1);
     
      int pesoPersona = minPesoPersona + r.nextInt(
              maxPesoPersona - minPesoPersona + 1);

      try {
        Thread.sleep(1000*tParaLlegadaPersona);
      } catch (InterruptedException ex) {

      }

      //creamos los hilos simulando personas con un id que se va incrementando de 1 en 1 con un contador
      Thread hiloPersona = new Thread(new Persona(puente, pesoPersona, tMinPaso, tMaxPaso, "P"+idPersona));
      hiloPersona.start();
      System.out.println("Siguiente persona llega en "+ tParaLlegadaPersona);
      System.out.println("- P"+ idPersona+ " de "+ pesoPersona + " kg quiere cruzar, en puente "+ puente.getPeso() + " kg, "+ puente.getNumPersonas()+ " personas.");
      idPersona++;

    }

  }

}
