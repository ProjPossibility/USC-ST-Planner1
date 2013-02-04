package com.sun.courseplanner;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class DescriptionActivity extends Activity implements OnClickListener {

	private Button finish_button;
	private Button back_button;
	private EditText title_txt;
	private EditText dtstart_txt;
	private EditText dtend_txt;
	private EditText location_txt;
	private EditText description_txt;
	private String _title;
	private Bundle bl;
	private int _id;
	Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.description);
		title_txt=(EditText)findViewById(R.id.title_txt);
		dtstart_txt=(EditText)findViewById(R.id.dtstart_txt);
		dtend_txt=(EditText)findViewById(R.id.dtend_txt);
		location_txt=(EditText)findViewById(R.id.location_txt);
		description_txt=(EditText)findViewById(R.id.description_txt);
		
		finish_button = (Button)findViewById(R.id.finish_button);
		finish_button.setOnClickListener(this);
		
		back_button = (Button)findViewById(R.id.back_button);
		back_button.setOnClickListener(this);
		intent = new Intent();
		bl = new Bundle();
		intent =this.getIntent();
		bl = intent.getExtras();
	//	_title = bl.getString("title");
		_id = bl.getInt("id");
		Log.i("ID", ""+_id);
		title_txt.setText("task: "+bl.getString("title"));
		dtstart_txt.setText("start time: "+bl.getString("dtstart"));
		dtend_txt.setText("end time: "+bl.getString("dtend"));
		location_txt.setText("location: "+bl.getString("location"));
		description_txt.setText("description: "+bl.getString("description"));
	}
	
	public void showdialog()
	{
		System.out.println("this is great!");
	    AlertDialog.Builder builder = new AlertDialog.Builder(DescriptionActivity.this); 
	    builder.setMessage("Are you sure you finished this task?") 
	           .setCancelable(false) 
	           .setPositiveButton("Yes", new DialogInterface.OnClickListener() { 
	               public void onClick(DialogInterface dialog, int id) { 
	            	     
	                   SearchAgendaActivity.insertRecord(""+_id);
	               } 
	           }) 
	           .setNegativeButton("No", new DialogInterface.OnClickListener() { 
	               public void onClick(DialogInterface dialog, int id) { 
	                    dialog.cancel(); 
	               } 
	           }).show(); 
	    AlertDialog alert = builder.create(); 
		
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.finish_button:
			showdialog();
			break;
		case R.id.back_button:
		     DescriptionActivity.this.finish(); 
		     break;
		
		}
	}
}
