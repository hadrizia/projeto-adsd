package mobile;

import java.util.Random;

import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_event;
import eduni.simjava.Sim_port;
import eduni.simjava.Sim_stat;
import eduni.simjava.Sim_system;
import eduni.simjava.distributions.Sim_normal_obj;
import events.EventType;
import requests.RequestType;

 public class Mobile extends Sim_entity {
	 Sim_stat stat;
	 
	 private Sim_normal_obj delay_mobile;
	 private Sim_normal_obj delay_network;
	 
	 private Sim_port in;
	 private Sim_port out;
	
	 public Mobile(String name, double mean, double variance, String in_name, String out_name) {
		super(name);
		in = new Sim_port(in_name);
		out = new Sim_port(out_name);
		add_port(out);
		add_port(in);
		
		delay_mobile = new Sim_normal_obj("Delay do sistema (Mobile)", mean, variance);
	    add_generator(delay_mobile);
	    
	    delay_network = new Sim_normal_obj("Delay da internet (Mobile)", mean, variance);
	    add_generator(delay_network);
	    
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

	public Sim_port getOut() {
		return this.out;
	}
	
	public Sim_port getIn() {
		return this.in;
	}	
	
	public void body() {
		//buscaServiços();

		// O Mobile vai receber uma resposta do banco de dados
		// e vai consumir esses dados povoando a lista.
		while(Sim_system.running()) {
			Sim_event e = new Sim_event();
			sim_get_next(e);
			sim_process(delay_mobile.sample());
			sim_completed(e);
			
			if (e.get_tag() == EventType.ORIGEM_BD.value) {
				sim_trace(1, "Mobile recebeu resposta do banco de dados.");
			}
			
			if (e.get_tag() == EventType.ORIGEM_DISPATCHER.value) {
				int randomInt = new Random().nextInt(3);
				// 1/3 das vezes que o usuário abre o aplicativo ele requisitará um novo serviço.
				if (randomInt < 1) {
					createServiço();
				}	
			}	
		}
	}

	private void createServiço() {
		sim_trace(1, "Mobile enviou uma requisição POST para o banco de dados para criar um novo serviço.");
		sim_schedule(out, 0, RequestType.POST.getValue());
		sim_pause(delay_network.sample());
	}

	private void buscaServiços() {
		sim_trace(1, "Mobile enviou uma requisição GET para o banco de dados para listar os serviços");
		sim_schedule(out, 0, RequestType.GET.getValue());
		sim_pause(delay_network.sample());
	}
 }
