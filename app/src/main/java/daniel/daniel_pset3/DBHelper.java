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

    // Declaring database variables here
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "todo.db";
    public static final String TABLE_ToDoList = "todolist";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TODO = "todo";

    // Giving context to the database here
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    /*
     * On create, make a table using COLUMN_ID as unique key and auto-increment it to give new
     * unique id values to new rows
     */
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

    /*
     * Function to add to-dos
     */
    public void addTodo(ToDoList ToDoList) {
        // Give value to a new row
        ContentValues values = new ContentValues();
        values.put(COLUMN_TODO, ToDoList.get_todo());
        SQLiteDatabase db = getWritableDatabase();
        // Insert the row into the database and give it a rowId
        long rowId = db.insert(TABLE_ToDoList, null,values);
        // Assign the rowId an integer
        ToDoList.set_id(((int) rowId));
        // Close the database after adding the row to free memory
        db.close();
    }

    /*
     * Function to delete to-dos
     */
    public void deleteTodo (int id) {
        SQLiteDatabase db = getWritableDatabase();
        // Delete row from database
        db.execSQL("DELETE FROM " + TABLE_ToDoList + " WHERE " + COLUMN_ID + " =\"" + id + "\";");
        // Close the database after removing to free memory
        db.close();
    }

    //Print out the database
    public ArrayList<ToDoList> getToDo(){
        ArrayList<ToDoList> todos = new ArrayList<>();
        SQLiteDatabase db =  getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_ToDoList + " WHERE 1";

        //Cursor point to a location in your result
        Cursor c = db.rawQuery(query, null);
        //Move to first row in your result
        c.moveToFirst();

        //Position after the last row means the end of the results
        while (!c.isAfterLast()) {
                // Add the to-do
                ToDoList todo = new ToDoList();
                todo.set_id(c.getInt(0));
                todo.set_todo(c.getString(1));
                todos.add(todo);
                // Move the cursor to the next object in the list
                c.moveToNext();
            }
        // Close the database and cursor to free memory
        db.close();
        c.close();
        return todos;
    }

}

