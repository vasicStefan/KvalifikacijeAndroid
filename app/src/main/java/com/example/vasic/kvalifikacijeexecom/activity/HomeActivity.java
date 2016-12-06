package com.example.vasic.kvalifikacijeexecom.activity;


import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.vasic.kvalifikacijeexecom.R;
import com.example.vasic.kvalifikacijeexecom.adapter.TaskAdapter;
import com.example.vasic.kvalifikacijeexecom.api.RestApi;
import com.example.vasic.kvalifikacijeexecom.api.wrapper.TaskWrapperAPI;
import com.example.vasic.kvalifikacijeexecom.api.wrapper.UserWrapperAPI;
import com.example.vasic.kvalifikacijeexecom.model.Task;
import com.example.vasic.kvalifikacijeexecom.preference.UserPreferences_;
import com.google.gson.Gson;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.springframework.web.client.RestClientException;

import java.util.List;

@EActivity(R.layout.activity_home)
@OptionsMenu({R.menu.menu})
public class HomeActivity extends AppCompatActivity {

    private static final String TAG = HomeActivity.class.getSimpleName();

    protected static final int ADD_TASK_REQUEST_CODE = 43;
    protected static final int UPDATE_TASK_REQUEST_CODE = 42;
    protected static final int LOGIN_REQUEST_CODE = 420;

    private List<Task> tasks;

    @ViewById
    FloatingActionButton addTask;

    @ViewById
    ListView listView;

    @Bean
    TaskAdapter adapter;


    @Bean
    UserWrapperAPI userWrapperAPI;

    @Bean
    TaskWrapperAPI taskWrapperAPI;

    @Pref
    UserPreferences_ userPreferences;


    @AfterViews
    @Background
    void checkUser() {
        if (!userPreferences.accessToken().exists()) {
            LoginActivity_.intent(this).startForResult(LOGIN_REQUEST_CODE);
            return;
        }
        try {
            tasks = taskWrapperAPI.getAllTasks();
        } catch (RestClientException e) {
            Log.e(TAG, e.getMessage(), e);
        }

        initData();

    }

    @UiThread
    void initData() {
        listView.setAdapter(adapter);
        adapter.setTasks(tasks);

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == SCROLL_STATE_IDLE) {
                    // button show
                    addTask.show();
                } else {
                    // button hide
                    addTask.hide();
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    @ItemClick
    void listViewItemClicked(Task task) {

        final Gson gson = new Gson();
        //taskPref.task().put(gson.toJson(task));
        String theTask=gson.toJson(task);
        UpdateActivity_.intent(this)
                .extra("theTask",theTask)
                .startForResult(UPDATE_TASK_REQUEST_CODE);
    }

    @OnActivityResult(UPDATE_TASK_REQUEST_CODE)
    @Background
    void updateTask(int resultCode, @OnActivityResult.Extra String task, @OnActivityResult.Extra int code) {
        if (resultCode == RESULT_OK) {
            final Gson gson = new Gson();
            Task newTask = gson.fromJson(task, Task.class);

            if(code==1){
                //delete
                taskWrapperAPI.delete(newTask.getId());
            }else{
                taskWrapperAPI.update(newTask);
            }
            checkUser();
        }
    }


    @Click
    void addTask() {
        AddTaskActivity_.intent(this).startForResult(ADD_TASK_REQUEST_CODE);
    }

    @OnActivityResult(ADD_TASK_REQUEST_CODE)
    @Background
    void onResult(int resultCode, @OnActivityResult.Extra String task) {
        if (resultCode == RESULT_OK) {
            final Gson gson = new Gson();
            final Task newTask = gson.fromJson(task, Task.class);
            try {
                final Task nnewTask = taskWrapperAPI.create(newTask);
                onTaskCreated(nnewTask);

            } catch (Exception e) {
                e.getMessage();
            }
        }

    }

    @UiThread
    void onTaskCreated(Task task) {
        tasks.add(task);
        adapter.addTask(task);
    }

    @OnActivityResult(LOGIN_REQUEST_CODE)
    void onLogin(int resultCode, @OnActivityResult.Extra("token") String token) {
        if (resultCode == RESULT_OK) {
            userPreferences.accessToken().put(token);
            checkUser();
        }
    }


    @OptionsItem({R.id.logout})
    void multipleMenuItems() {
        logout();
    }

    private void logout() {
        userPreferences.accessToken().remove();
        checkUser();
    }

}
