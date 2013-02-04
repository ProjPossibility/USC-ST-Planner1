package com.sun.courseplanner;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {   
    
    public static final String TB_NAME = "records";   
    public static final String ID = "_id";     
    public static final String NUMBER1 = "number1";  

	  public void clear(){
		   this.getWritableDatabase().delete(
                  DBHelper.TB_NAME, null, null);
	  }
	  
    public DBHelper(Context context, String name,CursorFactory factory, int version) { 
    
        super(context, name, factory, version);
   	 System.out.println("success build here haha");
        this.getWritableDatabase();  
        System.out.println("success build here haha gag");
    }
    
    /**
     * should be invoke when you never use DBhelper
     * To release the database and etc.
     */
    public void Close() {
            this.getWritableDatabase().close();
    }
  
    public void onCreate(SQLiteDatabase db) {   
        db.execSQL("CREATE TABLE IF NOT EXISTS "    
                + TB_NAME + " ("    
                + ID + " INTEGER PRIMARY KEY,"     
                + NUMBER1 + " VARCHAR)");   
    }   
  
    public void onUpgrade(SQLiteDatabase db,    
            int oldVersion, int newVersion) {   
        db.execSQL("DROP TABLE IF EXISTS "+TB_NAME);   
        onCreate(db);   
    }
    
    public void addRecord(String number) {
            ContentValues values = new ContentValues();    
        values.put(DBHelper.NUMBER1, number);   
        this.getWritableDatabase().insert(
                        DBHelper.TB_NAME, DBHelper.ID, values);  
        Log.i("dbhelper.id",""+DBHelper.ID);
    }
    
    public void delPeople(int id) {
        this.getWritableDatabase().delete(
                        DBHelper.TB_NAME, this.ID + " = " + id, null);
    }
    
    public void delAllPeople() {
        this.getWritableDatabase().delete(
                        DBHelper.TB_NAME, null, null);
    }
}  
