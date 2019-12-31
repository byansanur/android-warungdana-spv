package com.byandev.warnaspvdev.DBHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DbHelperSqlite extends SQLiteOpenHelper {

  private static final int DATABASE_VERSION = 1;
  private static final String DATABASE_NAME = "template_activity.db";
  private static final String TABLE_NAME = "t_activity";
  private static final String KEY_ID = "id";
  private static final String KEY_TYPE = "type";
  private static final String KEY_LOCATION = "location";
  private static final String[] COLUMNS = { KEY_ID, KEY_TYPE, KEY_LOCATION};

  public DbHelperSqlite(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
    // TODO Auto-generated constructor stub
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    String CREATION_TABLE = "CREATE TABLE STUDENT ( "
        + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
        + "type TEXT, "
        + "location TEXT)";
    db.execSQL(CREATION_TABLE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    this.onCreate(db);
  }

//  public List<TemplateSql> templateSqlList() {
//    List<TemplateSql> templateSqlLists = new ArrayList<>();
//    String selectQuery = "SELECT  * FROM t_activity";
//    SQLiteDatabase db = this.getWritableDatabase();
//    Cursor cursor = db.rawQuery(selectQuery, null);
//    if (cursor.moveToFirst()) {
//      do {
//
//      } while (cursor.moveToNext());
//    }
//
//  }
}
