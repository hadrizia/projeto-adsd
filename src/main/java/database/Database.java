package database;

import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_event;
import eduni.simjava.Sim_port;
import eduni.simjava.Sim_stat;
import eduni.simjava.Sim_system;
import eduni.simjava.distributions.Sim_normal_obj;
import events.EventType;

 public class Database extends Sim_entity {
	 Sim_stat stat;

	private Sim_normal_obj db_delay;
	private Sim_normal_obj network_delay;
	 
     private Sim_port in;
     private Sim_port out;

     public Database(String name, double mean, double variance) {
    	super(name);
 		
    	in = new Sim_port("In_BD");
 		out = new Sim_port("Out_BD");
 		
 		add_port(in);
 		add_port(out);
 		
 		db_delay = new Sim_normal_obj("Delay do sistema (Banco de Dados)", mean, variance);
 	    add_generator(db_delay);
 	    
 	    network_delay = new Sim_normal_obj("Delay da internet (Banco de Dados)", mean, variance);
 	    add_generator(network_delay);
 	    
 	    stat = new Sim_stat();
 	    stat.add_measure(Sim_stat.ARRIVAL_RATE);
 	    stat.add_measure(Sim_stat.QUEUE_LENGTH);
        stat.add_measure(Sim_stat.THROUGHPUT);
        stat.add_measure(Sim_stat.UTILISATION);
        stat.add_measure(Sim_stat.WAITING_TIME);
        stat.add_measure(Sim_stat.SERVICE_TIME);
        stat.add_measure(Sim_stat.RESIDENCE_TIME);
        set_stat(stat);
     }

     public void body() {
	 while(Sim_system.running()) {
		Sim_event e = new Sim_event();
		sim_get_next(e);
		sim_process(db_delay.sample());
		sim_completed(e);
		sim_trace(1, "The database has responded a request, returning data to the requester");
		sim_schedule(out, 0, EventType.ORIGEM_BD.value);
		sim_pause(network_delay.sample());
 		}
     }
     
 	public Sim_port getOut() {
		return this.out;
	}
	
	public Sim_port getIn() {
		return this.in;
	}	
	
 }
