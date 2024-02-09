package com.example.crudapplication.utils;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.crudapplication.models.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "myDb";
    private static final String TABLE_MENUITEMS = "MenuItems";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_weight = "weight";
    private static final String COLUMN_COMPOSITION = "composition";
    private static final String COLUMN_MENUIMAGE = "menuImage";

    private final String CREATE_MENUITEMS_SCRIPT = "CREATE TABLE " +
            TABLE_MENUITEMS + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
            COLUMN_NAME + " TEXT NOT NULL , " + COLUMN_weight + " INTEGER NOT NULL , " +
            COLUMN_COMPOSITION + " TEXT NOT NULL , " + COLUMN_MENUIMAGE + " INTEGER NOT NULL )";

    public boolean addMenuItem(MenuItem menuItem){
        try {
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, menuItem.getName());
            values.put(COLUMN_weight, menuItem.getWeight());
            values.put(COLUMN_COMPOSITION, menuItem.getComposition());
            values.put(COLUMN_MENUIMAGE, menuItem.getMenuImage());
            sqLiteDatabase.insert(TABLE_MENUITEMS, null, values);
            sqLiteDatabase.close();
            return true;
        }
        catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    public int updateMenuItem(MenuItem menuItem){
        try{
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, menuItem.getName());
            values.put(COLUMN_weight, menuItem.getWeight());
            values.put(COLUMN_COMPOSITION, menuItem.getComposition());
            values.put(COLUMN_MENUIMAGE, menuItem.getMenuImage());
            int result = sqLiteDatabase.update(TABLE_MENUITEMS, values, COLUMN_ID + " = ?",
                    new String[]{String.valueOf(menuItem.getId())});
            sqLiteDatabase.close();
            return result;
        }
        catch (Exception ex){
            ex.printStackTrace();
            return -1;
        }
    }

    public void deleteMenuItem(MenuItem menuItem){
        try{
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            sqLiteDatabase.delete(TABLE_MENUITEMS, COLUMN_ID + " = ?", new String[]{String.valueOf(menuItem.getId())});
            sqLiteDatabase.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public MenuItem getById(int id){
        try{
            SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
            Cursor cursor = sqLiteDatabase.query(TABLE_MENUITEMS, new String[]{COLUMN_ID, COLUMN_NAME, COLUMN_weight,
            COLUMN_COMPOSITION, COLUMN_MENUIMAGE}, COLUMN_ID + " = ?", new String[]{String.valueOf(id)},
                    null, null, null, null);
            if (cursor!=null){
                cursor.moveToFirst();
            }

            MenuItem menuItem = new MenuItem(cursor.getInt(0), cursor.getString(1), cursor.getInt(2),
                    cursor.getString(3), cursor.getInt(4));
            sqLiteDatabase.close();
            return menuItem;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public List<MenuItem> getAll(){
        List<MenuItem> list= new ArrayList<>();
        try{
            SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_MENUITEMS, null);
            if (cursor!=null){
                cursor.moveToFirst();
                do {
                    MenuItem menuItem = new MenuItem(cursor.getInt(0), cursor.getString(1), cursor.getInt(2),
                            cursor.getString(3), cursor.getInt(4));
                    list.add(menuItem);
                }while (cursor.moveToNext());
            }
            sqLiteDatabase.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return list;
    }

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_MENUITEMS_SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MENUITEMS);
        onCreate(db);
    }
}
