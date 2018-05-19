package com.example.medicalshop;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class Emergency extends Activity {
	Button b1,b2;
    EditText t1,t2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_emergency);
		t1=(EditText)findViewById(R.id.editText1);
		t2=(EditText)findViewById(R.id.editText2);
		b1=(Button)findViewById(R.id.button1);
		b2=(Button)findViewById(R.id.button2);
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String msg=t1.getText().toString();
				String num=t2.getText().toString();
			    SmsManager manager=SmsManager.getDefault();
				manager.sendTextMessage(num, null, msg, null, null);
				Toast.makeText(getApplicationContext(), "message sent", Toast.LENGTH_SHORT).show();
			}
		});
		b2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			Intent i=new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+t2.getText().toString()));
			startActivity(i);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_emergency, menu);
		return true;
	}

}
