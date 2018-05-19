package com.example.medicalshop;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class Signup extends Activity {
	SQLiteDatabase db;
	EditText e3,e4;
	Button b4,b5;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);
		e3=(EditText)findViewById(R.id.editText1);
		e4=(EditText)findViewById(R.id.editText2);
		b4=(Button)findViewById(R.id.button1);
		b5=(Button)findViewById(R.id.button2);
		db=openOrCreateDatabase("SignupDB",Context.MODE_PRIVATE,null);
		db.execSQL("CREATE TABLE IF NOT EXISTS signup(name VARCHAR,pass VARCHAR);");
		b5.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i=new Intent(Signup.this,MainActivity.class);
				startActivity(i);
				}
		});
		b4.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				db.execSQL("INSERT INTO signup VALUES('"+e3.getText()+"','"+e4.getText()+"');");
			    Toast.makeText(getApplicationContext(), "account created", Toast.LENGTH_LONG).show();
                Intent i=new Intent (Signup.this,MainActivity.class);
                startActivity(i);
				}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_signup, menu);
		return true;
	}

}
