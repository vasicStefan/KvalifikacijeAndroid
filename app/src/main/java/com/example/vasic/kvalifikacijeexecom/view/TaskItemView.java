package com.example.vasic.kvalifikacijeexecom.view;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.vasic.kvalifikacijeexecom.R;
import com.example.vasic.kvalifikacijeexecom.model.Task;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;



/**
 * Represents a {@link android.view.View view} for one item in a list.
 */
@EViewGroup(R.layout.view_item_task)
public class TaskItemView extends LinearLayout {


    @ViewById
    TextView title;


    @ViewById
    TextView description;


    public TaskItemView(Context context) {
        super(context);
    }


    public TaskItemView bind(Task task) {
        title.setText(task.getTitle());
        description.setText(task.getDescription());


        return this;
    }
}

