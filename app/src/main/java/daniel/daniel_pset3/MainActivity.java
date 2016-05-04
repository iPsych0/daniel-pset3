package daniel.daniel_pset3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> arrayListToDo;
    private ArrayAdapter<String> arrayAdapterToDo;
    EditText editText;
    ListView updatedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainactivity);

        arrayListToDo = new ArrayList<>();
        arrayAdapterToDo = new ArrayAdapter<>(this,
                android.R.layout.simple_expandable_list_item_1, arrayListToDo);
        ListView todoList = (ListView) findViewById(R.id.todoList);
        todoList.setAdapter(arrayAdapterToDo);
    }

    public void createTodo(View view) {
        String input = String.valueOf(editText.getText());

        if (!input.isEmpty()) {
            Toast.makeText(MainActivity.this, "Please fill in a to-do", Toast.LENGTH_SHORT).show();
        } else {
            arrayAdapterToDo.add(input);
            editText.setText("");
        }
    }
}
