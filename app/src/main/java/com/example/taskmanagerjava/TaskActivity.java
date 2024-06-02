package com.example.taskmanagerjava;

import android.app.ActionBar;
import android.os.Bundle;
import android.util.EventLogTags;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class TaskActivity extends AppCompatActivity {

    TextView nameView;
    TextView dateView;
    TextView descriptionView;
    UserTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_task);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        task = (UserTask) getIntent().getSerializableExtra(MainActivity.SHOW_TASK_MESSAGE);

        nameView = findViewById(R.id.nameText);
        dateView = findViewById(R.id.dateText);
        descriptionView = findViewById(R.id.descriptionText);

        if(task != null)
        {
            nameView.setText(task.getName());
            descriptionView.setText(task.getDescription());
            if(task.getDeadline() != null)
            {
                DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
                String date = formatter.format(task.getDeadline());
                dateView.setText(date);
            }
        }

    }


    public void onClickBackTaskActivity(View view) {
        finish();
    }
}