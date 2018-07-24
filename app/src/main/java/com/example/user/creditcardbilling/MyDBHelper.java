package com.example.user.creditcardbilling;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MyDBHelper extends SQLiteOpenHelper {


    private static final String DB_NAME = "TravelSpots";
    private static final int DB_VERSION = 2;


    private static final String TABLE_NAME = "pet";
    private static final String COL_id = "_id";
    private static final String COL_cdate = "cdate";
    private static final String COL_amount = "amount";
    private static final String COL_reserved = "reserved";
    private static final String COL_description = "description";
    private static final String COL_image = "image";


    private static final String TABLE_CREATE =
                " CREATE  TABLE " + TABLE_NAME + " ( "+
                        COL_id + " INTEGER PRIMARY KEY  NOT NULL, " +
                        COL_cdate + " DATETIME, " +
                        COL_amount + " INTEGER, " +
                        COL_reserved + " INTEGER, " +
                        COL_description + " TEXT ); ";

    public MyDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }



    public List<Pet> getAllPet(){
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {
                COL_id, COL_cdate, COL_amount, COL_reserved, COL_description
        };
        Cursor cursor = db.query(TABLE_NAME, columns, null, null,
                null, null, null);

        List<Pet> petList = new ArrayList<>();

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String cdate = cursor.getString(1);
            String amount = cursor.getString(2);
            String reserved = cursor.getString(3);
            String description = cursor.getString(4);

            Pet pet = new Pet(id, cdate, amount, reserved, description);
            petList.add(pet);
        }
        cursor.close();
        return petList;
    }

    public Pet findById(int id){
        SQLiteDatabase db = getWritableDatabase();
        String[] columns = {
                COL_cdate, COL_amount, COL_reserved, COL_description
        };
        String selection = COL_id + " = ?; ";
        String[] selectionArgs = {String.valueOf(id)};
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        Pet pet = null;
        if(cursor.moveToNext()){
            String cdate = cursor.getString(0);
            String amount = cursor.getString(1);
            String reserved = cursor.getString(2);
            String description = cursor.getString(3);
            pet = new Pet(id, cdate, amount, reserved, description);
        }
        cursor.close();
        return pet;
    }


    public long insert(Pet pet){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_cdate, pet.getDate());
        values.put(COL_amount, pet.getAmount());
        values.put(COL_reserved, pet.getRserved());
        values.put(COL_description, pet.getDescription());
        return db.insert(TABLE_NAME, null,values);
    }

    public int update(Pet pet){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_cdate, pet.getDate());
        values.put(COL_amount, pet.getAmount());
        values.put(COL_reserved, pet.getRserved());
        values.put(COL_description, pet.getDescription());
        String whereClause = COL_id + " = ?; ";
        String[] whereArgs = {Integer.toString(pet.getId())};
        return db.update(TABLE_NAME, values, whereClause, whereArgs);
    }

    public int deleteById(int id) {
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = COL_id + " = ?;";
        String[] whereArgs = {String.valueOf(id)};
        return db.delete(TABLE_NAME, whereClause, whereArgs);
        }

    }
