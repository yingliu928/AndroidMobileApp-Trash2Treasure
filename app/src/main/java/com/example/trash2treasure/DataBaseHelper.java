package com.example.trash2treasure;
//refering to : https://github.com/mitchtabian/SaveReadWriteDeleteSQLite/blob/master/SaveAndDisplaySQL/app/src/main/java/com/tabian/saveanddisplaysql/DatabaseHelper.java
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by User on 2/28/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";
    private static final String DATABASE_NAME= "DatabaseHelper";
    private static final String TABLE_NAME = "USERS";


    private static final String COL1 = "ID";
    private static final String  COL2 = "LAT";
    private static final String  COL3 = "LON";


    private static final String  COL4 = "TRASH_CATE";
    private static final String  COL5 = "TRASH_NUM";
    private static final String  COL6 = "TREASURE_CATE";
    private static final String  COL7 = "TREASURE_NUM";




    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 + " REAL DEFAULT 0," + COL3 + " REAL DEFAULT 0," + COL4 + " TEXT ,"
                +COL5 + " INTEGER DEFAULT 0,"
                +COL6 + " TEXT ,"
                +COL7+ " INTEGER DEFAULT 0)";

        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String lat, String lot,String trash_cate, int trash_num, String treasure_cate, int treasure_num) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, lat);
        contentValues.put(COL3, lot);
        contentValues.put(COL4, trash_cate);
        contentValues.put(COL5, trash_num);
        contentValues.put(COL6, treasure_cate);
        contentValues.put(COL7, treasure_num);

        Log.d(TAG, "addData: Adding " + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Returns all the data from database
     * @return
     */
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /**
     * Returns only the ID that matches the name passed in
     * @param
     * @return latitude
     */
    public Cursor getDataByFilter(String Cate){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + "*"+ " FROM " + TABLE_NAME +
                " WHERE " + COL4 + " LIKE " + "\""+ Cate + "\"";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /**
     * Returns only the ID that matches the name passed in
     * @param id
     * @return longitude
     */

    public Cursor getItemTrashCate(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL4+ " FROM " + TABLE_NAME +
                " WHERE " + COL1 + " = '" + id+ "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getItemTrashNum(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL5+ " FROM " + TABLE_NAME +
                " WHERE " + COL1 + " = '" + id+ "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getItemTreasureCate(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL6+ " FROM " + TABLE_NAME +
                " WHERE " + COL1 + " = '" + id+ "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }


    public Cursor getItemTreasureNum(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL7+ " FROM " + TABLE_NAME +
                " WHERE " + COL1 + " = '" + id+ "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /**
     * Updates the name field
     * @param newName
     * @param id
     * @param oldName
     */
    public void updateName(String newName, int id, String oldName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL2 +
                " = '" + newName + "' WHERE " + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + oldName + "'";
        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting name to " + newName);
        db.execSQL(query);
    }

    /**
     * Delete from database
     * @param id
     * @param name
     */
    public void deleteName(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + name + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + name + " from database.");
        db.execSQL(query);
    }

}
