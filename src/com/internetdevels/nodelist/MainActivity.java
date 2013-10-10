package com.internetdevels.nodelist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener, OnCheckedChangeListener {

	EditText user_id;
	CheckBox publish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        CheckBox get_by_id = (CheckBox) findViewById(R.id.checkBox2);
		publish = (CheckBox) findViewById(R.id.checkBox1);
		user_id = (EditText) findViewById(R.id.editText1);
		Button button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(this);
		get_by_id.setOnCheckedChangeListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button1:
			Intent nextScreen = new Intent(getApplicationContext(), List_viewActivity.class);
			nextScreen.putExtra("user_id", user_id.getText().toString());
			if (publish.isChecked()) {
				nextScreen.putExtra("publish", "1");
			}
			else {
				nextScreen.putExtra("publish", "0");
			}
			startActivity(nextScreen);
			/*if (isNumeric(user_id.getResources().toString())) {
				
			}
			else {
				Toast.makeText(this, "Enter numeric value", Toast.LENGTH_LONG).show();
			}*/
			
			break;
		}
	}


	@Override
	public void onCheckedChanged(CompoundButton checkbox, boolean checked) {
		// TODO Auto-generated method stub
		switch (checkbox.getId()) {
			case R.id.checkBox2:
				if (checked) {
					user_id.setEnabled(true);
				}
				else {
					user_id.setEnabled(false);
				}
				break;
		}
	}
	
	public static boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}

    
}