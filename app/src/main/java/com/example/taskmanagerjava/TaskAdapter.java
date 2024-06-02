package com.example.taskmanagerjava;



import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private ArrayList<UserTask> taskArrayList;
    private MainActivity activity;

    public TaskAdapter( MainActivity activity) {
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_layout, parent, false);
        return new ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.ViewHolder holder, int position) {

        UserTask item = taskArrayList.get(position);
        holder.name.setText(item.getName());
        holder.description.setText(item.getDescription());
        holder.isDone.setChecked(item.IsDone());
        if(item.getDeadline() != null)
        {
            DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
            String date = formatter.format(item.getDeadline());
            holder.data.setText(date);
        }

        holder.isDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setDone(holder.isDone.isChecked());
            }
        });

        holder.description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.openTaskActivity(item);
            }
        });

    }

    @Override
    public int getItemCount() {
        return taskArrayList.size();
    }

    public Activity getActivity()
    {
        return activity;
    }


    @SuppressLint("NotifyDataSetChanged")
    public void setTaskArrayList(ArrayList<UserTask> tasks)
    {
        taskArrayList = tasks;
        notifyDataSetChanged();
    }

    public void deleteItem(int position){
        UserTask item = taskArrayList.get(position);
        taskArrayList.remove(position);
        notifyItemRemoved(position);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox isDone;
        TextView name;
        TextView description;
        TextView data;

        ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.textViewTaskTitle);
            isDone = view.findViewById(R.id.checkBoxTaskDone);
            description = view.findViewById(R.id.textViewTaskDescription);
            data = view.findViewById(R.id.dateTextView);
        }
    }


}