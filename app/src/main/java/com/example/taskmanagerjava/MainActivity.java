package com.example.taskmanagerjava;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
//
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;

//
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<UserTask> tasks = new ArrayList<UserTask>();
    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    private UserTask newTask;
    private static final String TAG = "MyApp";

    static final String TASK_KEY = "TASK";
    static final String ACCESS_MESSAGE="ACCESS_MESSAGE";
    static final String SHOW_TASK_MESSAGE="SHOW_TASK_MESSAGE";

    ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {

                    if(result.getResultCode() == Activity.RESULT_OK){
                        Intent intent = result.getData();
                        newTask = (UserTask) intent.getSerializableExtra(ACCESS_MESSAGE);
                        addTask(newTask);
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = (RecyclerView) findViewById(R.id.taskView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        taskAdapter = new TaskAdapter(this);
        recyclerView.setAdapter(taskAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new RecyclerItemTouchHelper(taskAdapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);

        tasks = loadFromJsonArray();

        taskAdapter.setTaskArrayList(tasks);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ArrayList<UserTask> tasksToSave = new ArrayList<UserTask>();
        for (UserTask task : tasks)
        {
            if(!task.IsDone())
            {
                tasksToSave.add(task);
            }
        }

        saveToJsonArray(tasksToSave);
    }

    private void addTask(UserTask task)
    {
        tasks.add(task);
        taskAdapter.setTaskArrayList(tasks);
    }

    public void openTaskActivity(UserTask task)
    {
        Log.d("MyLog", task.toString());
        Intent intent = new Intent(MainActivity.this, TaskActivity.class);
        intent.putExtra(MainActivity.SHOW_TASK_MESSAGE, task);
        startActivity(intent);
    }
    public void addTaskButton_OnClick(View view) {
        Intent intent = new Intent(MainActivity.this, NewTaskActivity.class);

        mStartForResult.launch(intent);
    }

    public void saveToJsonArray(ArrayList<UserTask> myObjects) {
        Gson gson = new Gson();
        String json = gson.toJson(myObjects);
        Log.d("MyApp", json);
        SharedPreferences.Editor editor = getSharedPreferences("MyPrefs", MODE_PRIVATE).edit();
        editor.putString("myObjectsJson", json);
        editor.commit();
    }

    public ArrayList<UserTask> loadFromJsonArray() {
        Gson gson = new Gson();
        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        String json = prefs.getString("myObjectsJson", "");
        Log.d("MyApp", json);

        Type type = new TypeToken<ArrayList<UserTask>>(){}.getType();
        ArrayList<UserTask> tasks = gson.fromJson(json, type);
        return tasks;
    }

}