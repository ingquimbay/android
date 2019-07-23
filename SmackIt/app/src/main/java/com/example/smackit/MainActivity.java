package com.example.smackit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<String> todos = new ArrayList<>();
    private ArrayAdapter<String> todosAdapter;
    private ListView todoslw;
    private Button addbutton;
    private EditText newtodotext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todoslw = findViewById(R.id.todolistview);
        readFile();
        todosAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, todos);
        todoslw.setAdapter(todosAdapter);

        setupListViewListener();

    }

    private void readFile() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            todos = new ArrayList<String>(FileUtils.readLines(todoFile));
        } catch (IOException ioe) {
            todos = new ArrayList<>();
        }
    }

    private void writeFile() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            FileUtils.writeLines(todoFile, todos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setupListViewListener() {
        todoslw.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                todos.remove(position);
                todosAdapter.notifyDataSetChanged();
                writeFile();
                return true;
            }
        });
    }

    @Override
    public void onClick(View v) {
        newtodotext = findViewById(R.id.newtodoeditText);
        String todoText = newtodotext.getText().toString();
        todosAdapter.add(todoText);
        writeFile();
        newtodotext.setText("");
    }
}
