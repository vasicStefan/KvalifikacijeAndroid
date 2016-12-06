package com.example.vasic.kvalifikacijeexecom.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.vasic.kvalifikacijeexecom.R;
import com.example.vasic.kvalifikacijeexecom.model.Task;
import com.example.vasic.kvalifikacijeexecom.view.TaskItemView;
import com.example.vasic.kvalifikacijeexecom.view.TaskItemView_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vasic on 12/1/2016.
 */
@EBean
public class TaskAdapter extends BaseAdapter {

    @RootContext
    Context context;

    @ViewById
    ImageView image;

    private final List<Task> tasks = new ArrayList<>();


    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Task getItem(int position) {
        return tasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Task task = getItem(position);
        View view = null;

        if (convertView == null) {
            // If convertView hasn't been created yet, create a new view.
            view = TaskItemView_.build(context).bind(task);

        } else {
            // If convertView exists just bind the task to it.
            view = ((TaskItemView) convertView).bind(task);
        }

        if (task.isFinished()) {
            view.setBackgroundColor(Color.parseColor("#6E6E6E"));
        } else {
            view.setBackgroundColor(Color.parseColor("#D8D8D8"));
        }
        return view;
    }

    public void setTasks(List<Task> newTasks) {
        tasks.clear();
        tasks.addAll(newTasks);
        notifyDataSetChanged();
    }

    public void addTask(Task task) {
        tasks.add(task);
        notifyDataSetChanged();
    }

}
