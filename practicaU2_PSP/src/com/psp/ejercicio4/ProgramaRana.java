package com.psp.ejercicio4;

import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ProgramaRana {
	public static void main(String[] args) {
		RanaComeMoscas hilo1 = new RanaComeMoscas("Rana");
	}
}

class RanaComeMoscas implements Runnable{
	private Thread hilo = null;
	ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
	
	public RanaComeMoscas (String str) {
		hilo= new Thread(this, str);
		hilo.start();
	}
	
	@Override
	public void run() {
		System.out.println("Rana empieza a cazar moscas " + LocalTime.now());
		ses.scheduleAtFixedRate(new Runnable() {
			public void run() {
				moscas();
			}
		}, 5, 3, TimeUnit.SECONDS);
	}
	
	private static void moscas() {
			System.out.println("Rana come mosca " + LocalTime.now());
	}
}
