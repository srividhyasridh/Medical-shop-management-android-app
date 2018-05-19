package com.example.medicalshop;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Mainmenu extends Activity {
     Button a,m,c;
     TextView t;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mainmenu);
		t=(TextView)findViewById(R.id.textView1);
		Bundle b=getIntent().getExtras();
		String gete1=b.getString("one");
		t.setText(gete1);
		a=(Button)findViewById(R.id.button1);
		m=(Button)findViewById(R.id.button2);
		c=(Button)findViewById(R.id.button3);
		a.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i=new Intent (Mainmenu.this,Staffdetails.class);
                startActivity(i);
			}
		});
		m.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i=new Intent (Mainmenu.this,Medicine.class);
                startActivity(i);
			}
		});
		c.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i=new Intent (Mainmenu.this,Emergency.class);
                startActivity(i);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_mainmenu, menu);
		return true;
	}

}
