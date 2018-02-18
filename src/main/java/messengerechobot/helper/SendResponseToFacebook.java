package messengerechobot.helper;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import messengerechobot.response.Message;
import messengerechobot.response.Recipient;
import messengerechobot.response.Response;

public class SendResponseToFacebook {

	private static final String PAGE_ACCESS_TOKEN = "EAAPvBcClhLcBAApnzSZA9wGtwdHJfCNCocVQ5GCnByZCVXOU0ZAKgeJZBlNa5t2Rp8xZB0T3xykvYAZAMOxdZCAhtlvc7g5Rddak0J03dKr0wbi0jJ67UtaYLnDuUGRnZCoQ7Pb8Q0WGKhzSJb8TIJhKmvXWfhzZAhdUkxYZCbFqjBuwZDZD";
	private static final String URL1 = "https://graph.facebook.com/v2.6/me/messages?access_token=" + PAGE_ACCESS_TOKEN;
	private static final String USER_AGENT = "Mozilla/5.0";

	private void sendResponse(Response response) {
		
		String json = new Gson().toJson(response);
		URL obj;
		HttpURLConnection con = null;
		try {
			obj = new URL(URL1);
			con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Content-Type", "application/json");
			con.setDoOutput(true);
			OutputStream os = con.getOutputStream();
			os.write(json.getBytes());
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		int responseCode;
		try {
			responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer res = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					res.append(inputLine);
				}
				in.close();
			} else {
				System.out.println("POST request not worked");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    public void createResponse(String reply, String id) {
        Response response = new Response();
		Recipient recipient = new Recipient();
		recipient.setId(id);;
		Message message = new Message();
		message.setText(reply);;
		response.setMessage(message);
		response.setRecipient(recipient);
		sendResponse(response);
    }

}
