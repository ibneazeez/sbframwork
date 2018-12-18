package hello.dto;

import org.springframework.context.annotation.Description;

import java.util.List;

@Description(value = "OTP SMS DTO class.")
public class MessageDTO {

    public MessageDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	private String userName;
    private String password;
    private String host;
    private String to;
    private String from;
    private String message;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public MessageDTO(String userName, String password, String host, String to, String from, String message) {
		super();
		this.userName = userName;
		this.password = password;
		this.host = host;
		this.to = to;
		this.from = from;
		this.message = message;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
    
    public String getURL(){
    	return host+"?username="+userName+"&password="+password+"&to="+to+"&sender="+from+"&message="+message;
    }

   
}
