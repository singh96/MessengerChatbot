package messengerechobot.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Entry {
	
	private String id;
	private Long time;
    private ArrayList<Messaging> messaging;

	public ArrayList<Messaging> getMessaging() {
		return messaging;
	}

	public void setMessaging(ArrayList<Messaging> messaging) {
		this.messaging = messaging;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}
}
