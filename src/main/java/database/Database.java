package database;

import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_event;
import eduni.simjava.Sim_port;
import eduni.simjava.Sim_system;
import events.EventType;

 public class Database extends Sim_entity {
     private Sim_port in;
     private Sim_port out;
     private double delay;

     public Database(String name, double delay) {
       super(name);
       this.delay = delay;
       
       in = new Sim_port("In_bd");
       out = new Sim_port("Out_bd");
       
       add_port(in);
       add_port(out);
     }

     public void body() {
       while (Sim_system.running()) {
         Sim_event e = new Sim_event();
         // Pega o próximo evento
         sim_get_next(e);
         // Processa o evento
         sim_process(delay);
         // O evento foi concluido
         sim_completed(e);
         // Exibe mensagem de recebimento de requisição
         sim_trace(1, "O banco recebeu uma requisição, retornando dados ao requisitante");
         // Programa acessos ao BD
         sim_schedule(out, 0, EventType.ORIGEM_BD.value);
         // Delay
         sim_pause(delay);
       }
     }
     
 	public String getOut() {
		return this.out.get_pname();
	}
	
	public String getIn() {
		return this.in.get_pname();
	}	
	
 }
