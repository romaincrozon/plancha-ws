package com.theia.httpConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.util.StringUtils;

import com.theia.utils.HTTPRequestUtils;

public class HttpRequest {

//	t=brooklyn%20nINe-Nine&apikey=8d4c4f52
	public static void main(String[] args) {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("apikey", "8d4c4f52");
		parameters.put("t", "brooklyn Nine-Nine");
		
		String url = "http://www.omdbapi.com/";
		
		JSONObject jsonResults = request(url, parameters);
 
	}
	
	public static JSONObject request(String url, Map<String, String> parameters) {
		try {
			HttpURLConnection con = openConnection(url, parameters);
			if (con != null) {
				String requestResult = readStream(new InputStreamReader(con.getInputStream()));
				if (!StringUtils.isEmpty(requestResult)) {
					return parseToJSONObject(requestResult);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static HttpURLConnection openConnection(String url, Map<String, String> parameters) throws Exception {

		URL apiURL = new URL(url + HTTPRequestUtils.getParamsString(parameters));
		HttpURLConnection con = (HttpURLConnection) apiURL.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("Content-Type", "application/json");

		con.setConnectTimeout(5000);
		con.setReadTimeout(5000);

		con.setDoOutput(true);
		if (isConnectionValid(con)) {
			return con;
		} else {
			handleErrorStatus(con);
			return null;
		}
	}

	private static void handleErrorStatus(HttpURLConnection con) {
		String error = readStream(new InputStreamReader(con.getErrorStream()));
		System.out.println("Error:" + error);
	}

	public static boolean isConnectionValid(HttpURLConnection con) { 
		try {
			return (con.getResponseCode() < 299);
		} catch (IOException e) {
			System.out.println("Invalid response code : " + e.getMessage());
		}
		return false;
	}

	public static String readStream(InputStreamReader streamReader) {
		try {
			BufferedReader in = new BufferedReader(streamReader);
			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}
			in.close();
			return content.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static JSONObject parseToJSONObject(String stringToParse){
		try {
			JSONParser parser = new JSONParser(); 
			return (JSONObject) parser.parse(stringToParse);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
