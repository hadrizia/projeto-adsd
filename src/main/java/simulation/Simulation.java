package simulation;

import database.Database;
import dispatcher.Dispatcher;
import eduni.simjava.Sim_system;
import mobile.Mobile;
import web.Web;

public class Simulation {
    // The main method
    public static void main(String[] args) {
    	Sim_system.initialise();
		
    	Dispatcher dispatcher = new Dispatcher("Dispatcher", 50);
		Mobile mob = new Mobile("Mobile", 20, 25);
		Database bd = new Database("Database", 80);
		Web web = new Web("Web", 30, 25);
		
		Sim_system.link_ports(dispatcher.get_name(), mob.getOut(), mob.get_name(), mob.getIn());
		Sim_system.link_ports(dispatcher.get_name(), web.getOut(), web.get_name(), web.getIn());
		
		Sim_system.link_ports(mob.get_name(), mob.getOut(), bd.get_name(), bd.getIn());
		Sim_system.link_ports(bd.get_name(), bd.getOut(), mob.get_name(), mob.getIn());
		Sim_system.link_ports(web.get_name(), web.getOut(), bd.get_name(), bd.getIn());
		Sim_system.link_ports(bd.get_name(), bd.getOut(), web.get_name(), web.getIn());
	
		Sim_system.run();
    }
  }