package messengerechobot.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestReceived {

	private ArrayList<Entry> entry;

	public ArrayList<Entry> getEntry() {
		return entry;
	}

	public void setEntry(ArrayList<Entry> entry) {
		this.entry = entry;
	}
}
