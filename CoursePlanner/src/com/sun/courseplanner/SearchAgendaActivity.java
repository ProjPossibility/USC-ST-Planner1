package com.sun.courseplanner;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class SearchAgendaActivity extends Activity implements OnClickListener,OnItemClickListener{
	//private EditText tx_condition;
	private Bundle bl;
	private Button btn_search;
	private Button btn_search_urgent;
	public static DBHelper mDBHelper = null;
	//public  Util util= new Util();
	  private ListView lv;
	  
   public void clearall(){
	   mDBHelper.clear();
   }
   public void createDB() {
		  System.out.println("success build start");
          String DB_NAME = "sqlitedb5";
          mDBHelper = new DBHelper(
        		  SearchAgendaActivity.this, DB_NAME, null, 1);
          assert(mDBHelper != null);
          System.out.println("success build");
  }
  public static void insertRecord(String id) {
          mDBHelper.addRecord(id);
  }
  public boolean existInDB(String id){
      Cursor c = mDBHelper.getWritableDatabase().query(
              DBHelper.TB_NAME,null,null,null,null,null,null   
     );  
      if (c.moveToFirst()) {
        
          do {
        	  String t = c.getString(c.getColumnIndex(DBHelper.NUMBER1));
        	  Log.i("ID2",t);
        	//  System.out.println(t);
        	//  System.out.println(c.getColumnIndex(DBHelper.NUMBER1));
        	  if(id.equals(t))
        		  return true;
          } while (c.moveToNext());
  }
  c.close();
  return false;
  }
  

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	  // TODO Auto-generated method stub
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.search_agenda);
	  init();
	}
	
	void init() {
		//onUpgrade();
		 createDB();
		 // tx_condition = (EditText) findViewById(R.id.condition);
		  System.out.println("start to build");
		//  util = new Util();
	
		  btn_search = (Button) findViewById(R.id.search_normal_button);
		  btn_search.setOnClickListener(this);
		  btn_search_urgent = (Button) findViewById(R.id.search_urgent_button);
		  btn_search_urgent.setOnClickListener(this);
		  lv=(ListView)findViewById(R.id.agenda_list);
		  lv.setOnItemClickListener(this);
		  lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		  lv.setVerticalScrollBarEnabled(false);
		}

/*	void search_all_events(){
		Cursor cursor = null;
        cursor = getContentResolver().query(Uri.parse("content://com.android.calendar/events"), null, null, null,"dtstart asc");
        if(null!=cursor){
	           List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
	           while(cursor.moveToNext()){
		        	   if(existInDB(cursor.getString(cursor.getColumnIndex("_id"))))
		        		   continue;
	            HashMap<String, Object> item = new HashMap<String, Object>();
	            item.put("_id", cursor.getString(cursor.getColumnIndex("_id")));
	            item.put("title",cursor.getString(cursor.getColumnIndex("title")));
	            item.put("description", cursor.getString(cursor.getColumnIndex("description")));
	            item.put("dtstart",cursor.getString(cursor.getColumnIndex("dtstart")));
	            item.put("dtend",cursor.getString(cursor.getColumnIndex("dtend")));
	            item.put("eventLocation", cursor.getString(cursor.getColumnIndex("eventLocation")));
	            data.add(item);
	           }
	           SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.search_agenda_item,
	  	             new String[]{"title", "description", "eventLocation","dtstart","dtend"},new int[]{R.id.item_title, R.id.item_desc, R.id.item_address,R.id.item_dtstart,R.id.item_dtend});
	  	           lv.setAdapter(adapter);
	  	            cursor.close();
	  	        }
		
	}*/
	
	
	public void test(){
		System.out.println("I am here!");
		Intent intent = new Intent();
        intent.setClass(SearchAgendaActivity.this, DescriptionActivity.class);  
        bl = new Bundle();
         bl.putLong("id", 124);
        bl.putString("title", "1234567890");
        bl.putString("dtstart", "312313");
        bl.putString("dtend", "121214324");
        bl.putString("location", "locat");
        bl.putString("description", "description");
        intent.putExtras(bl);
        startActivity(intent);  
      //  finish();//停止当前的Activity,如果不写,则按返回键会跳转回原来的Activity  
	}
	
	void search_urgent_events(){
		Cursor cursor = null;
        cursor = getContentResolver().query(Uri.parse("content://com.android.calendar/events"), null, null, null,"dtstart asc");
        if(null!=cursor){
	           List<HashMap<String, Object>> data1 = new ArrayList<HashMap<String, Object>>();
	           List<HashMap<String, Object>> data2 = new ArrayList<HashMap<String, Object>>();
	           while(cursor.moveToNext()){
	              
	        	   if(existInDB(cursor.getString(cursor.getColumnIndex("_id"))))
	        		   continue;
	        	 //  Log.i("ID", cursor.getString(cursor.getColumnIndex("_id")));
	        	String dead = cursor.getString(cursor.getColumnIndex("dtend")) ;
	        	String begin = cursor.getString(cursor.getColumnIndex("dtstart")) ;
	        	//Log.i("deadline", "22"+deadline+"22");
	        	long deadline_time,start_time;
	        	if(null==dead){//||null==begin
	        		deadline_time = Long.MAX_VALUE;
	        		start_time = Long.MAX_VALUE;
	        	}
	        	else
	        		{
	        		deadline_time = Long.parseLong(dead);
	        		start_time = Long.parseLong(begin);
	        		}
	        	//System.out.println(l);
	        	Date d_date = new Date(deadline_time);
	        	Date s_date = new Date(start_time);
	        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        	String d_time=sdf.format(d_date);
	        	String s_time=sdf.format(s_date);
	        //	Log.i("deadline", ""+deadline_time+"");
	        	Calendar calendar = Calendar.getInstance();//获取当前日历对象
	        	long curTime = calendar.getTimeInMillis();//获取当前时区下日期时间对应的时间戳
	        	//System.out.println(unixTime);
	        //	Log.i("unixTime", ""+curTime+"");
	        	if(deadline_time-curTime<28800000*3){//当前距离deadline还有1天
	        		//需要读取文件，if(cursor.getString(cursor.getColumnIndex("_id")==文件中的)
		            HashMap<String, Object> item1 = new HashMap<String, Object>();
		            item1.put("_id", cursor.getString(cursor.getColumnIndex("_id")));
		            item1.put("title",cursor.getString(cursor.getColumnIndex("title")));
		            item1.put("description", cursor.getString(cursor.getColumnIndex("description")));
		            item1.put("dtstart",s_time);
		            item1.put("dtend",d_time);
		            item1.put("eventLocation", cursor.getString(cursor.getColumnIndex("eventLocation")));
		            data1.add(item1);
		          }//end if li-
	        	else{//距离deadline 多于1天
	        		HashMap<String, Object> item2 = new HashMap<String, Object>();
		            item2.put("_id", cursor.getString(cursor.getColumnIndex("_id")));
		            item2.put("title",cursor.getString(cursor.getColumnIndex("title")));
		            item2.put("description", cursor.getString(cursor.getColumnIndex("description")));
		            item2.put("dtstart",s_time);
		            item2.put("dtend",d_time);
		            item2.put("eventLocation", cursor.getString(cursor.getColumnIndex("eventLocation")));
		            data2.add(item2);
	        		}
	           	}//end while
		           SimpleAdapter adapter1 = new SimpleAdapter(this, data1, R.layout.search_agenda_item_1,
		  	             new String[]{"title", "description", "eventLocation","dtstart","dtend"},new int[]{R.id.item_title, R.id.item_desc, R.id.item_address,R.id.item_dtstart,R.id.item_dtend});
		  	           lv.setAdapter(adapter1);
//		  	        SimpleAdapter adapter2 = new SimpleAdapter(this, data1, R.layout.search_agenda_item_2,
//			  	             new String[]{"title", "description", "eventLocation","dtend"},new int[]{R.id.item_title, R.id.item_desc, R.id.item_address,R.id.item_dtend});
//			  	       lv.setAdapter(adapter2);
		  	            cursor.close();
	           
	  	   }//end if cursor!=null
		
	}
	void search_normal_events(){
		Cursor cursor = null;
        cursor = getContentResolver().query(Uri.parse("content://com.android.calendar/events"), null, null, null,"dtstart asc");
        if(null!=cursor){
	           List<HashMap<String, Object>> data1 = new ArrayList<HashMap<String, Object>>();
	           List<HashMap<String, Object>> data2 = new ArrayList<HashMap<String, Object>>();
	           while(cursor.moveToNext()){
		              
		        	   if(existInDB(cursor.getString(cursor.getColumnIndex("_id"))))
		        		   continue;
	        	String dead = cursor.getString(cursor.getColumnIndex("dtend")) ;
	        	String begin = cursor.getString(cursor.getColumnIndex("dtstart")) ;
	        	//Log.i("deadline", "22"+deadline+"22");
	        	
	        	long deadline_time,start_time;
	        	if(null==dead){//||null==begin
	        		deadline_time = Long.MAX_VALUE;
	        		start_time = Long.MAX_VALUE;
	        	}
	        	else
	        		{
	        		deadline_time = Long.parseLong(dead);
	        		start_time = Long.parseLong(begin);
	        		}
	        	//System.out.println(l);
	        	Date d_date = new Date(deadline_time);
	        	Date s_date = new Date(start_time);
	        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        	String d_time=sdf.format(d_date);
	        	String s_time=sdf.format(s_date);
	       // 	Log.i("deadline", ""+deadline_time+"");
	        	Calendar calendar = Calendar.getInstance();//获取当前日历对象
	        	long curTime = calendar.getTimeInMillis();//获取当前时区下日期时间对应的时间戳
	        	//System.out.println(unixTime);
	        //	Log.i("unixTime", ""+curTime+"");
	        	if(deadline_time-curTime>28800000*3){//当前距离deadline还有1天
	        		//需要读取文件，if(cursor.getString(cursor.getColumnIndex("_id")==文件中的)
		            HashMap<String, Object> item1 = new HashMap<String, Object>();
		            item1.put("_id", cursor.getString(cursor.getColumnIndex("_id")));
		            item1.put("title",cursor.getString(cursor.getColumnIndex("title")));
		            item1.put("description", cursor.getString(cursor.getColumnIndex("description")));
		            item1.put("dtstart",s_time);
		            item1.put("dtend",d_time);
		            item1.put("eventLocation", cursor.getString(cursor.getColumnIndex("eventLocation")));
		            data1.add(item1);
		          }//end if li-
	        	else{//距离deadline 多于1天
	        		HashMap<String, Object> item2 = new HashMap<String, Object>();
		            item2.put("_id", cursor.getString(cursor.getColumnIndex("_id")));
		            item2.put("title",cursor.getString(cursor.getColumnIndex("title")));
		            item2.put("description", cursor.getString(cursor.getColumnIndex("description")));
		            item2.put("dtstart",s_time);
		            item2.put("dtend",d_time);
		            item2.put("eventLocation", cursor.getString(cursor.getColumnIndex("eventLocation")));
		            data2.add(item2);
	        		}
	           	}//end while
//		           SimpleAdapter adapter1 = new SimpleAdapter(this, data1, R.layout.search_agenda_item_1,
//		  	             new String[]{"title", "description", "eventLocation","dtend"},new int[]{R.id.item_title, R.id.item_desc, R.id.item_address,R.id.item_dtend});
//		  	           lv.setAdapter(adapter1);
		  	        SimpleAdapter adapter2 = new SimpleAdapter(this, data1, R.layout.search_agenda_item_2,
			  	             new String[]{"title", "description", "eventLocation","dtstart","dtend"},new int[]{R.id.item_title, R.id.item_desc, R.id.item_address,R.id.item_dtstart,R.id.item_dtend});
			  	       lv.setAdapter(adapter2);
		  	            cursor.close();
	           
	  	   }//end if cursor!=null
		
		
	}
	
	
	@Override
	public void onClick(View v) {
//	   TODO Auto-generated method stub
	  switch (v.getId()) {
	  //case R.id.search_button:
		//   String cond = tx_condition.getText().toString();
		  // search(cond);
		//   break;
	  case R.id.search_urgent_button:
	  	   search_urgent_events();
	  	   break;
	  case R.id.search_normal_button:
	  	   search_normal_events();
	  	   break;
	  }
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int ponstion, long id) {//需要重写
	  // TODO Auto-generated method stub
	   if (id != -1) {
	    ListView lView=(ListView)parent;
	    HashMap<String, Object> data=(HashMap<String, Object>)lView.getItemAtPosition(ponstion);
	    int _id=Integer.parseInt(data.get("_id").toString()) ;
	    Log.i("ID1",""+_id);
	      /*    Uri uri = ContentUris.withAppendedId(Uri.parse("content://com.android.calendar/events"), _id);
	          Intent intent = new Intent(Intent.ACTION_VIEW).setData(uri);

	          startActivity(intent);*/
		System.out.println("I am here!");
		Intent intent = new Intent();
        intent.setClass(SearchAgendaActivity.this, DescriptionActivity.class);  
        bl = new Bundle();
         bl.putInt("id", _id);

        bl.putString("title", data.get("title").toString());
        bl.putString("dtstart", data.get("dtstart").toString());
        bl.putString("dtend", data.get("dtend").toString());
        bl.putString("location", data.get("eventLocation").toString());
        bl.putString("description", data.get("description").toString());
        intent.putExtras(bl);
        startActivity(intent);  
        
	   } 
	}
	}