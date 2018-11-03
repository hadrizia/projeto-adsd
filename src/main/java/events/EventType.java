package events;

public enum EventType {
	ORIGEM_DISPATCHER(0),
	ORIGEM_WEB(1),
	ORIGEM_MOBILE(2),
	ORIGEM_BD(3);
	
	public final int value;
	
	private EventType(int value) {
		this.value = value;
	}

}
