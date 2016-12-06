package com.example.vasic.kvalifikacijeexecom.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;


import com.example.vasic.kvalifikacijeexecom.R;
import com.example.vasic.kvalifikacijeexecom.model.Task;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

/**
 * Created by vasic on 12/2/2016.
 */


@EActivity(R.layout.activity_update)
public class UpdateActivity extends AppCompatActivity {

    @ViewById
    EditText title;

    @ViewById
    EditText description;

    @ViewById
    CheckBox isChecked;

    @ViewById
    Button saveTask;

    @ViewById
    Button deleteTask;

    @Extra
    String theTask;

    private Task task;


    @AfterViews
    void setTitleUpdate() {

        Gson gson = new GsonBuilder().create();
        task = gson.fromJson(theTask, Task.class);


        title.setText(task.getTitle().toString());
        description.setText(task.getDescription().toString());
        isChecked.setChecked(task.isFinished());

    }

    @Click
    void saveTask() {
        final Gson gson = new Gson();
        final Task taskUpdated = new Task(task.getId(), title.getText().toString(),
                description.getText().toString(), isChecked.isChecked());
        final Intent intent = new Intent();

        intent.putExtra("task", gson.toJson(taskUpdated));
        setResult(RESULT_OK, intent);
        finish();
    }

    @Click
    void deleteTask() {
        final Gson gson = new Gson();
        final Task taskUpdated = new Task(task.getId(), title.getText().toString(),
                description.getText().toString(), isChecked.isChecked());
        final Intent intent = new Intent();

        intent.putExtra("task", gson.toJson(taskUpdated))
                .putExtra("code", 1);
        setResult(RESULT_OK, intent);
        finish();

    }
}
