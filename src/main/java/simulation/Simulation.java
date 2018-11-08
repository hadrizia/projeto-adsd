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
		
    	Database bd = new Database("Database", 240.0, 133.0);
    	
    	Dispatcher dispatcher1 = new Dispatcher("Dispatcher_1", 50, "Out_mobile1", "Out_web1");
    	Dispatcher dispatcher2 = new Dispatcher("Dispatcher_2", 50, "Out_mobile2", "Out_web2");
    	Dispatcher dispatcher3 = new Dispatcher("Dispatcher_3", 50, "Out_mobile3", "Out_web3");
    	
		Mobile mob1 = new Mobile("Mobile_1", 120.0, 50.0, "In_mobile1", "Out_mobile1");
		Mobile mob2 = new Mobile("Mobile_2", 630.0, 20.0, "In_mobile2", "Out_mobile2");
		Mobile mob3 = new Mobile("Mobile_3", 630.0, 20.0, "In_mobile3", "Out_mobile3");
		
		Web web1 = new Web("Web_1", 320.0, 100.0, "In_web1", "Out_web1");
		Web web2 = new Web("Web_2", 340.0, 200.0,  "In_web2", "Out_web2");
		Web web3 = new Web("Web_3", 340.0, 200.0,  "In_web3", "Out_web3");
		
		
		// Dispatcher 1, Mobile 1 e Web 1
		Sim_system.link_ports(dispatcher1.get_name(), mob1.getOut().get_pname(), mob1.get_name(), mob1.getIn().get_pname());
		Sim_system.link_ports(dispatcher1.get_name(), web1.getOut().get_pname(), web1.get_name(), web1.getIn().get_pname());
		
		Sim_system.link_ports(mob1.get_name(), mob1.getOut().get_pname(), bd.get_name(), bd.getIn().get_pname());
		Sim_system.link_ports(bd.get_name(), bd.getOut().get_pname(), mob1.get_name(), mob1.getIn().get_pname());
		Sim_system.link_ports(web1.get_name(), web1.getOut().get_pname(), bd.get_name(), bd.getIn().get_pname());
		Sim_system.link_ports(bd.get_name(), bd.getOut().get_pname(), web1.get_name(), web1.getIn().get_pname());
		
		// Mobile 2 e Web 2
		Sim_system.link_ports(dispatcher2.get_name(), mob2.getOut().get_pname(), mob2.get_name(), mob2.getIn().get_pname());
		Sim_system.link_ports(dispatcher2.get_name(), web2.getOut().get_pname(), web2.get_name(), web2.getIn().get_pname());
		
		Sim_system.link_ports(mob2.get_name(), mob2.getOut().get_pname(), bd.get_name(), bd.getIn().get_pname());
		Sim_system.link_ports(bd.get_name(), bd.getOut().get_pname(), mob2.get_name(), mob2.getIn().get_pname());
		Sim_system.link_ports(web2.get_name(), web2.getOut().get_pname(), bd.get_name(), bd.getIn().get_pname());
		Sim_system.link_ports(bd.get_name(), bd.getOut().get_pname(), web2.get_name(), web2.getIn().get_pname());
		
		// Mobile 3 e Web 3
		Sim_system.link_ports(dispatcher3.get_name(), mob3.getOut().get_pname(), mob3.get_name(), mob3.getIn().get_pname());
		Sim_system.link_ports(dispatcher3.get_name(), web3.getOut().get_pname(), web3.get_name(), web3.getIn().get_pname());
		
		Sim_system.link_ports(mob3.get_name(), mob3.getOut().get_pname(), bd.get_name(), bd.getIn().get_pname());
		Sim_system.link_ports(bd.get_name(), bd.getOut().get_pname(), mob3.get_name(), mob3.getIn().get_pname());
		Sim_system.link_ports(web3.get_name(), web3.getOut().get_pname(), bd.get_name(), bd.getIn().get_pname());
		Sim_system.link_ports(bd.get_name(), bd.getOut().get_pname(), web3.get_name(), web3.getIn().get_pname());

		Sim_system.set_trace_detail(false, true, false);
	
		Sim_system.run();
    }
  }