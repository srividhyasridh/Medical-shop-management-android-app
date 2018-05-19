package com.example.medicalshop;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Staffdetails extends Activity {
	EditText t1,t2,t3;
	Button a,b,c,d,e,f;
    SQLiteDatabase db;
	public void showMessage(String title,String msg)
	{
		Builder builder=new Builder(this);
		builder.setCancelable(true);
		builder.setTitle(title);
		builder.setMessage(msg);
		builder.show();
	}
	public void clearText()
	{
		t1.setText("");
		t2.setText("");
		t3.setText("");
		t1.requestFocus();
	}
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_staffdetails);
		t1=(EditText)findViewById(R.id.editText1);
		t2=(EditText)findViewById(R.id.editText2);
		t3=(EditText)findViewById(R.id.editText3);
		a=(Button)findViewById(R.id.button1);
		b=(Button)findViewById(R.id.button2);
		c=(Button)findViewById(R.id.button3);
		d=(Button)findViewById(R.id.button4);
		e=(Button)findViewById(R.id.button5);
		f=(Button)findViewById(R.id.button6);
		db=openOrCreateDatabase("StaffDB",Context.MODE_PRIVATE,null);
		db.execSQL("CREATE TABLE IF NOT EXISTS staff(sid VARCHAR,name VARCHAR,phno VARCHAR);");
		a.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				db.execSQL("INSERT INTO staff VALUES('"+t1.getText()+"','"+t2.getText()+"','"+t3.getText()+"');");
				showMessage("success","record added");
				clearText();
				}
		});
		b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Cursor c=db.rawQuery("SELECT * FROM staff WHERE sid='"+t1.getText()+"'", null);
				if(c.moveToFirst())
				{
					db.execSQL("DELETE FROM staff WHERE sid='"+t1.getText()+"'");
				showMessage("success","record deleted");
				}
				clearText();
			}
		});
		c.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Cursor c=db.rawQuery("SELECT * FROM staff WHERE sid='"+t1.getText()+"'", null);
				if(c.moveToFirst())
				{
					db.execSQL("UPDATE staff SET name='"+t2.getText()+"',phno='"+t3.getText()+"'WHERE sid='"+t1.getText()+"'");
					
				}
				showMessage("success","record modified");
				clearText();
			}
		});
		d.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Cursor c=db.rawQuery("SELECT * FROM staff WHERE sid='"+t1.getText()+"'", null);
				if(c.moveToFirst())
				{
					t2.setText(c.getString(1));
					t3.setText(c.getString(2));
				}
			}
		});
		e.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			Cursor c=db.rawQuery("SELECT *FROM staff", null);
			StringBuffer buffer=new StringBuffer();
			while(c.moveToNext())
			{
				buffer.append("STAFFID:"+c.getString(0)+"\n");
				buffer.append("NAME:"+c.getString(1)+"\n");
				buffer.append("PH.NO:"+c.getString(2)+"\n\n");
			}
				showMessage("Employee Details",buffer.toString());
			}
		});
		f.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String gName=t2.getText().toString();
				Intent i=new Intent (Staffdetails.this,Mainmenu.class);
                Bundle b=new Bundle();
				b.putString("one",gName);
				i.putExtras(b);
				startActivity(i);
			}
		});
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_staffdetails, menu);
		return true;
	}

}
