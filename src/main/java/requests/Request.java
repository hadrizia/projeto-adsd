package requests;

public class Request {
	
	private RequestType rType;
	
	public Request(RequestType r) {
		rType = r;
	}
	
	public RequestType getRequestType() {
		return rType;
	}
}