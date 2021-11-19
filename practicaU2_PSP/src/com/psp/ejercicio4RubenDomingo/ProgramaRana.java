package com.psp.ejercicio4RubenDomingo;

import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ProgramaRana {
	public static void main(String[] args) {
		RanaComeMoscas rana = new RanaComeMoscas();
		Thread hilo1 = new Thread(rana, "Rana");
		hilo1.start();
	}
}

class RanaComeMoscas implements Runnable{
	ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
	
	Runnable rn= new Runnable() {
		@Override
		public void run() {
			moscas();
		}
	};
	
	Runnable rn2 = new Runnable() {
		@Override
		public void run() {
			ses.shutdown();	
		}
	};
	
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() +" empieza a cazar moscas " + LocalTime.now().withNano(0));
		ses.scheduleAtFixedRate(rn , 5, 3, TimeUnit.SECONDS);
		ses.schedule(rn2, 30, TimeUnit.SECONDS);
	}

	private static void moscas() {
			System.out.println("Rana come mosca " + LocalTime.now().withNano(0));
	}
}
