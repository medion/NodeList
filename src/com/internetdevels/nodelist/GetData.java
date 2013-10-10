package com.internetdevels.nodelist;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GetData {
	
public static String getContentOfHTTPPage(String pageAddress, String codePage) throws Exception {
	StringBuilder sb = new StringBuilder();
	URL pageURL = new URL(pageAddress);
	URLConnection uc = pageURL.openConnection();
	BufferedReader br = new BufferedReader(
 	new InputStreamReader(
 			uc.getInputStream(), codePage));
	try {
		String inputLine;
		while ((inputLine = br.readLine()) != null) {
			sb.append(inputLine);
		} 
	} finally {
		br.close();
	}
	return sb.toString();
}

public static ArrayList<HashMap<String, String>> jsonParser(String json) throws ParseException {
	JSONParser parser = new JSONParser();
	ArrayList<HashMap<String, String>> array = new ArrayList<HashMap<String, String>>();
	Object obj = parser.parse(json);
	JSONArray jsonObj = (JSONArray) obj;
	for (int i=0; i < jsonObj.size(); i++) {
		JSONObject simpleObject = (JSONObject) jsonObj.get(i);
		Map<String, String> map = new HashMap<String, String>();
		Object[] keys = simpleObject.keySet().toArray();
		for (int j = 0; j < keys.length; j++) { 
			map.put(keys[j].toString(), simpleObject.get(keys[j]).toString());
		} 
		array.add(i, (HashMap<String, String>) map);
	}
	return array;
}

}