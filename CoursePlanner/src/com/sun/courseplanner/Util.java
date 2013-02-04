package com.sun.courseplanner;


import android.app.Activity;
import android.database.Cursor;
import android.widget.TextView;

public class Util extends Activity{
    private DBHelper mDBHelper = null;
	  public void createDB() {
		  System.out.println("success build start");
          String DB_NAME = "sqlitedb1";
          mDBHelper = new DBHelper(
                          Util.this, DB_NAME, null, 1);
          assert(mDBHelper != null);
          System.out.println("success build");
  }
  public void insertRecord(String id) {
          mDBHelper.addRecord(id);
  }
  public boolean existInDB(String id){
      Cursor c = mDBHelper.getWritableDatabase().query(
              DBHelper.TB_NAME,null,null,null,null,null,null   
     );  
      if (c.moveToFirst()) {
        //  int idxID = c.getColumnIndex(DBHelper.ID);
         // int idxNumber = c.getColumnIndex(DBHelper.NUMBER1);
          // Iterator the records
          do {
        	  String t = c.getString(c.getColumnIndex(DBHelper.NUMBER1));
        	  if(id.equals(t))
        		  return true;
          } while (c.moveToNext());
  }
  c.close();
  return false;
  }
  
  public void ViewRecords() {
          // Make the query
          Cursor c = mDBHelper.getWritableDatabase().query(
                          DBHelper.TB_NAME,null,null,null,null,null,null   
                 ); 
          StringBuilder sbRecords = new StringBuilder("");
          if (c.moveToFirst()) {
                  int idxID = c.getColumnIndex(DBHelper.ID);
                  int idxNumber = c.getColumnIndex(DBHelper.NUMBER1);
                  // Iterator the records
                  do {
                          sbRecords.append(c.getInt(idxID));
                          sbRecords.append(". ");

                          sbRecords.append(c.getInt(idxNumber));
                          sbRecords.append("\n");
                  } while (c.moveToNext());
          }
          c.close();
          // Refresh the content of TextView

  }
  private void DelOne() {
          int id;
          Cursor c = mDBHelper.getWritableDatabase().query(
                          DBHelper.TB_NAME,null,null,null,null,null,   
                  null);
          if (c.moveToFirst()) {
                  int idxID = c.getColumnIndex(DBHelper.ID);
                  id = c.getInt(idxID);
                  mDBHelper.delPeople(id);
          }
  }
  
	/*public static final String TAG = SearchAgendaActivity.class.getName();
    public static final String FILENAME = "myFile.txt";
    public static File file;
    Util(){
    	//if(file)
    //	File file = new File();
    	
    }
    
	public  void writeToFile(String s){    
	        
	        try {
	             OutputStreamWriter out = new OutputStreamWriter(openFileOutput(FILENAME,MODE_APPEND));

	              out.write(s);
	              out.write('\n');   
	              out.close();

	             //Toast.makeText(this,"Text Saved !",Toast.LENGTH_LONG).show();
	           } 

	catch (java.io.IOException e) {

	             //do something if an IOException occurs.
//	Toast.makeText(this,"Sorry Text could't be added",Toast.LENGTH_LONG).show();
	           }               
	    }
		

	public  Boolean existInFile(String text) {

			try {
				//if()
				java.io.File f = new java.io.File(FILENAME);
				if(!f.exists())
					return false;
				InputStream inputStream = openFileInput(FILENAME);
                
			    if ( inputStream != null ) {
			    	InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			    	BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			    	String receiveString = "";
			    	
			    	while ( (receiveString = bufferedReader.readLine()) != null ) {
			    		if(receiveString.equals(text)){
			    	        return true;
			    		}
			    	}		    	
			    	inputStream.close();
			    }
			}

			catch (FileNotFoundException e) {
				Log.e(TAG, "File not found: " + e.toString());
			} catch (IOException e) {
				Log.e(TAG, "Can not read file: " + e.toString());
			}
			return false;
	}*/
}
