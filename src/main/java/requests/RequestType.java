package requests;

public enum RequestType {
	GET(0),
	PUT(1),
	POST(2);
	//DELETE
	
	private final int value;
	
	private RequestType(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}