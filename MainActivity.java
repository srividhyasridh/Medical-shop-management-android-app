package com.example.medicalshop;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	EditText e1,e2;
	Button b1,b2,b3;
	SQLiteDatabase db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	    e1=(EditText)findViewById(R.id.editText1);
		e2=(EditText)findViewById(R.id.editText2);
		b1=(Button)findViewById(R.id.button1);
		b2=(Button)findViewById(R.id.button2);
		b3=(Button)findViewById(R.id.button3);
		db=openOrCreateDatabase("SignupDB",Context.MODE_PRIVATE,null);
		db.execSQL("CREATE TABLE IF NOT EXISTS signup(name VARCHAR,pass VARCHAR);");
		b1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "crocin is recommended for fever", Toast.LENGTH_LONG).show();
				
			}
		});
		b2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i=new Intent(MainActivity.this,Signup.class);
				startActivity(i);
			}
		});
		b3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				String gName=e1.getText().toString();
				String gPass=e2.getText().toString();
				try
				{
				Cursor c=db.rawQuery("select pass from signup where name='"+gName+"';",null);
				
				if(c!=null)
				{
					String getting=null;
					if(c.moveToFirst())
					{
						do
						{
							getting = c.getString(0);
		                
		              //  Toast.makeText(MainActivity.this, "NAME= "+valueofcol1 +"PHONE= " +valueofcol2,Toast.LENGTH_SHORT).show();
						}					
						while(c.moveToNext());
					}
					if(gPass.equals(getting)){
						Intent i=new Intent(MainActivity.this,Mainmenu.class);
						Bundle b=new Bundle();
						b.putString("one", gName);
						i.putExtras(b);
						startActivity(i);
					}
				}
				else 
				{
					Toast.makeText(MainActivity.this, "No details saved this user",Toast.LENGTH_SHORT).show();
				}
				
				}catch(Exception e)
				{
					Toast.makeText(getApplicationContext(), "error="+e,Toast.LENGTH_SHORT).show();					
				}
			
				
				}
			});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
