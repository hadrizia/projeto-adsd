package mobile;

import java.util.Random;

import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_event;
import eduni.simjava.Sim_port;
import eduni.simjava.Sim_system;
import events.EventType;
import requests.RequestType;

 public class Mobile extends Sim_entity {
	
	private Sim_port out;
	private Sim_port in;
	private double delay_mobile;
	private double delay_network;
	
	public Mobile(String name, double delay_mobile, double delay_network) {
		super(name);
		
		this.delay_mobile = delay_mobile;
		this.delay_network = delay_network;
		
		in = new Sim_port("In_mobile");
		out = new Sim_port("Out_mobile");
		add_port(out);
		add_port(in);
	}

	public String getOut() {
		return this.out.get_pname();
	}
	
	public String getIn() {
		return this.in.get_pname();
	}	
	
	public void body() {
		buscaServiços();

		// O Mobile vai receber uma resposta do banco de dados
		// e vai consumir esses dados povoando a lista.
		while(Sim_system.running()) {
			Sim_event e = new Sim_event();
			sim_get_next(e);
			sim_process(delay_mobile);
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
		sim_pause(delay_network);
	}

	private void buscaServiços() {
		sim_trace(1, "Mobile enviou uma requisição GET para o banco de dados para listar os serviços");
		sim_schedule(out, 0, RequestType.GET.getValue());
		sim_pause(delay_network);
	}
 }
