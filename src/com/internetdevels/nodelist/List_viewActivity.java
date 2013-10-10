package com.internetdevels.nodelist;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class List_viewActivity extends Activity {
	
	final String ATTRIBUTE_NAME_NID = "nid";
	final String ATTRIBUTE_NAME_TITLE = "title";
	final String ATTRIBUTE_NAME_USERNAME = "username";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_view);
		
		Intent intent = getIntent();
		
		String user_id = intent.getStringExtra("user_id");
		String publish = intent.getStringExtra("publish");
		
		String data;
		try {
			StringBuilder url = new StringBuilder();
			url.append("http://192.168.0.132/drupal7.loc/?q=view/articles&");
			if (user_id.toString() != "" && publish.toString() != "0") {
				url.append("uid=" + user_id.toString().trim());
				url.append("&status=" + publish.toString());
			}
			else if (user_id.toString() != "") {
				url.append("uid=" + user_id.toString().trim());
			}
			else if (publish.toString() != "0") {
				url.append("status=1");
			}
			data = GetData.getContentOfHTTPPage("http://192.168.0.132/drupal7.loc/?q=view/articles&", "UTF-8");
			Log.d("gek", data);
			if (data == "[]") {
				Log.d("gek", "empty");
				TextView textview2 = (TextView) findViewById(R.id.textView2);
				textview2.setText("Not found results.");
			}
			else {
				Log.d("gek", "not empty");
				ArrayList<HashMap<String, String>> content = GetData.jsonParser(data);
				String[] from = { ATTRIBUTE_NAME_NID, ATTRIBUTE_NAME_TITLE,
				        ATTRIBUTE_NAME_USERNAME };
				int[] to = { R.id.textView1, R.id.textView2, R.id.textView3};
				SimpleAdapter sAdapter = new SimpleAdapter(this, content, R.layout.item,
				        from, to);
				ListView lvSimple = (ListView) findViewById(R.id.listView1);
				lvSimple.setAdapter(sAdapter);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}