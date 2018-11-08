package web;

import java.util.Random;

import eduni.simjava.*;
import eduni.simjava.distributions.*;
import events.EventType;
import requests.RequestType;

public class Web extends Sim_entity {
	
	Sim_stat stat;
	
	private Sim_port out;
	private Sim_port in;
	
	private Sim_normal_obj delay_sytem;
	private Sim_normal_obj delay_network;
	
	public Web(String name, double mean, double variance, String in_name, String out_name) {
		super(name);
		in = new Sim_port(in_name);
		out = new Sim_port(out_name);
		add_port(out);
		add_port(in);
		
		
		delay_sytem = new Sim_normal_obj("Delay do sistema (Web)", mean, variance);
	    add_generator(delay_sytem);
	    
	    delay_network = new Sim_normal_obj("Delay da internet (Web)", mean, variance);
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
		buscaServiços();

		// O Mobile vai receber uma resposta do banco de dados
		// e vai consumir esses dados povoando a lista.
		while(Sim_system.running()) {
			Sim_event e = new Sim_event();
			sim_get_next(e);
			sim_process(delay_sytem.sample());
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
		sim_trace(1, "Web enviou uma requisição POST para o banco de dados para criar um novo serviço.");
		sim_schedule(out, 0, RequestType.POST.getValue());
		sim_pause(delay_network.sample());
	}

	private void buscaServiços() {
		sim_trace(1, "Web enviou uma requisição GET para o banco de dados para listar os serviços");
		sim_schedule(out, 0, RequestType.GET.getValue());
		sim_pause(delay_network.sample());
	}
 }

