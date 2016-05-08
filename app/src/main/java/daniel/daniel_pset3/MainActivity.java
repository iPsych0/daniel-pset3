package daniel.daniel_pset3;

/*
 * Student name: Daniel Oliemans
 * Student number: 11188669
 * University of Amsterdam
 * Source used for code: https://www.youtube.com/watch?v=PrMhjEh2ySk
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Declaring variables
    private ArrayAdapter arrayAdapterToDo;
    EditText inputBox;
    ListView todoList;
    DBHelper DBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainactivity);

        //Giving context to the variables by telling AS what IDs or context to look at
        todoList = (ListView) findViewById(R.id.todoList);
        inputBox = (EditText) findViewById(R.id.inputBox);
        DBHelper = new DBHelper(this, null, null, 1);
        todoList.setAdapter(arrayAdapterToDo);

        // Creating the array list for the DBHelper to write into
        final ArrayList<ToDoList> todos = DBHelper.getToDo();
        arrayAdapterToDo = new ArrayAdapter<>(this,
                android.R.layout.simple_expandable_list_item_1, todos);
        todoList.setAdapter(arrayAdapterToDo);

        // Setting an OnItemLongClickListener here to keep listening whether the user has long
        // pressed an item to delete it
        todoList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ToDoList todo = (ToDoList) todoList.getItemAtPosition(position);
                // Deletes the item from the database
                DBHelper.deleteTodo(todo.get_id());
                // Deletes the item from the ToDoList
                arrayAdapterToDo.remove(todo);
                return false;
            }
        });
    }

    /*
     * Function that waits for the user to give input in the EditText after pressing the "Add"
     * button and adds the user's input
     */
    public void createTodo(View view) {
        // Get the user input from the input box below
        String input = String.valueOf(inputBox.getText());
        ToDoList todo = new ToDoList(inputBox.getText().toString());

        // Checks whether entered characters are valid
        if (!input.matches("[a-zA-Z1-9\\s]+")) {
            Toast.makeText(this, "Please enter valid characters.", Toast.LENGTH_SHORT).show();
        } else {
            // If the entered characters are valid, add the user's input to the database
            DBHelper.addTodo(todo);
            inputBox.setText("");
            // And add them to the ListView afterwards
            arrayAdapterToDo.add(todo);
            arrayAdapterToDo.notifyDataSetChanged();
        }
    }
}
