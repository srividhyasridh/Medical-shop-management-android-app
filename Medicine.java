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

public class Medicine extends Activity {
	EditText t1,t2,t3,t4,t5;
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
		t4.setText("");
		t5.setText("");
		t1.requestFocus();
	}
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_medicine);
		t1=(EditText)findViewById(R.id.editText1);
		t2=(EditText)findViewById(R.id.editText2);
		t3=(EditText)findViewById(R.id.editText3);
		t4=(EditText)findViewById(R.id.editText4);
		t5=(EditText)findViewById(R.id.editText5);
		a=(Button)findViewById(R.id.button1);
		b=(Button)findViewById(R.id.button2);
		c=(Button)findViewById(R.id.button3);
		d=(Button)findViewById(R.id.button4);
		e=(Button)findViewById(R.id.button5);
		f=(Button)findViewById(R.id.button6);
		db=openOrCreateDatabase("MedicineDB",Context.MODE_PRIVATE,null);
		db.execSQL("CREATE TABLE IF NOT EXISTS medicine(mid VARCHAR,name VARCHAR,expdte VARCHAR,qty VARCHAR,cost VARCHARS);");
		a.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				db.execSQL("INSERT INTO medicine VALUES('"+t1.getText()+"','"+t2.getText()+"','"+t3.getText()+"','"+t4.getText()+"','"+t5.getText()+"');");
				showMessage("success","record added");
				clearText();
			}
		});
		b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Cursor c=db.rawQuery("SELECT * FROM medicine WHERE mid='"+t1.getText()+"'", null);
				if(c.moveToFirst())
				{
					db.execSQL("DELETE FROM medicine WHERE mid='"+t1.getText()+"'");
				showMessage("success","record deleted");
				}
				clearText();	
			}
		});
		e.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Cursor c=db.rawQuery("SELECT * FROM medicine WHERE mid='"+t1.getText()+"'", null);
				if(c.moveToFirst())
				{
					t2.setText(c.getString(1));
					t3.setText(c.getString(2));
					t4.setText(c.getString(3));
					t5.setText(c.getString(4));
				}
			}
		});
		c.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Cursor c=db.rawQuery("SELECT * FROM medicine WHERE mid='"+t1.getText()+"'", null);
				if(c.moveToFirst())
				{
					db.execSQL("UPDATE medicine SET name='"+t2.getText()+"',expdte='"+t3.getText()+"',qty='"+t4.getText()+"',cost='"+t5.getText()+"'WHERE mid='"+t1.getText()+"'");
					
				}
				showMessage("success","record modified");
				clearText();
			}
		});
		d.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Cursor c=db.rawQuery("SELECT *FROM medicine", null);
				StringBuffer buffer=new StringBuffer();
				while(c.moveToNext())
				{
					buffer.append("MEDID:"+c.getString(0)+"\n");
					buffer.append("NAME:"+c.getString(1)+"\n");
					buffer.append("EXPDATE:"+c.getString(2)+"\n");
					buffer.append("QTY:"+c.getString(3)+"\n");
					buffer.append("COST:"+c.getString(4)+"\n\n");
					
				}
					showMessage("Medicine Details",buffer.toString());
			}
		});
		f.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String gName=t2.getText().toString();
				Intent i=new Intent (Medicine.this,Mainmenu.class);
                Bundle b=new Bundle();
				b.putString("one", gName);
				i.putExtras(b);
				startActivity(i);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_medicine, menu);
		return true;
	}

}
