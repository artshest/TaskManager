package com.example.taskmanagerjava;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.Calendar;
import java.util.Date;

import android.text.format.DateUtils;

import android.widget.DatePicker;


public class NewTaskActivity extends AppCompatActivity {

    TextView currentDateTime;
    Calendar dateAndTime=Calendar.getInstance();

    CheckBox noDateCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_task);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        currentDateTime = findViewById(R.id.currentDateTime);
        noDateCheck = findViewById(R.id.noDateCheck);
        setInitialDateTime();
    }

    public void setDate(View v) {
        new DatePickerDialog(NewTaskActivity.this, d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    // установка начальных даты и времени
    private void setInitialDateTime() {

        currentDateTime.setText(DateUtils.formatDateTime(this,
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR));
    }

    // установка обработчика выбора даты
    DatePickerDialog.OnDateSetListener d=new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDateTime();
        }
    };

    public void addTask(View view) {
        TextView titleView = findViewById(R.id.editTaskNameText);
        TextView descriptionView = findViewById(R.id.editTextTextMultiLine);
        UserTask newTask;
        if(noDateCheck.isChecked())
        {
             newTask = new UserTask(titleView.getText().toString(), descriptionView.getText().toString());
        }
        else
        {
             newTask = new UserTask(titleView.getText().toString(), descriptionView.getText().toString(), dateAndTime.getTime());
        }


        Intent data = new Intent();

        data.putExtra(MainActivity.ACCESS_MESSAGE, newTask);
        setResult(RESULT_OK, data);
        finish();
//        finish();
//        startActivity(intent);
    }

    public void goBack(View view) {
        startActivity(new Intent(NewTaskActivity.this, MainActivity.class));
    }
}