package dispatcher;

import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_port;
import events.EventType;

public class Dispatcher extends Sim_entity {
	
	private Sim_port out_mobile;
	private Sim_port out_web;
	
    private double delay;

    public Dispatcher(String name, double delay) {
      super(name);
      this.delay = delay;
      // Porta que envia eventos para o processador
      out_mobile = new Sim_port("Out_mobile");
      out_web = new Sim_port("Out_web");
      
      // Adicionando as portas de mobile e de web
      add_port(out_mobile);
      add_port(out_web);
    }

    public void body() {
    	// Envia um processo a cada N vezes (neste caso, 10000)
    	for (int i=0; i < 10000; i++) {
            sim_schedule(out_mobile, 0.0, EventType.ORIGEM_DISPATCHER.value);
            
            // Deve sair (7 vezes) mais requisições via mobile do que web. O número 7 não é representativo.
            if (i % 7 == 0) {
            	sim_schedule(out_web, 0.0, EventType.ORIGEM_DISPATCHER.value);
            }
            
            sim_pause(delay);
          }
    }
}