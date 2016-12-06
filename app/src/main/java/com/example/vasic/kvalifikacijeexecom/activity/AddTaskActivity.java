package com.example.vasic.kvalifikacijeexecom.activity;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;

import com.example.vasic.kvalifikacijeexecom.R;
import com.example.vasic.kvalifikacijeexecom.model.Task;
import com.google.gson.Gson;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by vasic on 12/1/2016.
 */
@EActivity(R.layout.activity_add_task)
public class AddTaskActivity extends AppCompatActivity{

    @ViewById
    TextInputEditText title;

    @ViewById
    TextInputEditText description;

    @Click
    void saveTask(){
        final Task task = new Task(title.getText().toString(),
                description.getText().toString());
        final Intent intent = new Intent();
        final Gson gson = new Gson();
        intent.putExtra("task", gson.toJson(task));
        setResult(RESULT_OK, intent);
        finish();


    }

}
