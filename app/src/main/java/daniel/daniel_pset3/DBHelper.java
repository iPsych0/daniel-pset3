package daniel.daniel_pset3;

/*
 * Student name: Daniel Oliemans
 * Student number: 11188669
 * University of Amsterdam
 * Source used for code: https://www.youtube.com/watch?v=Jcmp09LkU-I
 */

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import java.util.ArrayList;


public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "todo.db";
    public static final String TABLE_ToDoList = "todolist";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TODO = "todo";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_ToDoList + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TODO + " TEXT " +
                ")";
        db.execSQL(query);

        // Creates the to-dos here in onCreate method (so only for the first time the app is opened)
        ContentValues values = new ContentValues();
        values.put(COLUMN_TODO, "EXAMPLE: Your to-do could be here.");
        db.insert(TABLE_ToDoList, null, values);

        values = new ContentValues();
        values.put(COLUMN_TODO, "You can add a to-do by entering it in the text box below.");
        db.insert(TABLE_ToDoList, null, values);

        values = new ContentValues();
        values.put(COLUMN_TODO, "Delete a to-do by long-pressing the to-do you wish to delete.");
        db.insert(TABLE_ToDoList, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_ToDoList);
    }


}

