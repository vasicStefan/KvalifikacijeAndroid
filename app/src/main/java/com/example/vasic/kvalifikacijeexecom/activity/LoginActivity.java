package com.example.vasic.kvalifikacijeexecom.activity;

/**
 * Created by vasic on 12/1/2016.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vasic.kvalifikacijeexecom.R;
import com.example.vasic.kvalifikacijeexecom.api.wrapper.UserWrapperAPI;
import com.example.vasic.kvalifikacijeexecom.model.dto.TokenContainerDTO;


import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.EditorAction;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;





@EActivity(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity {

    public static final int REGISTER_RESULT = 1;
    private static final String TAG =
            LoginActivity.class.getSimpleName();

    @Bean
    UserWrapperAPI userWrapperAPI;

    @ViewById
    EditText username;

    @ViewById
    EditText password;


    @EditorAction(R.id.password)
    @Click
    void login() {
        final String username = this.username.getText().toString();
        final String password = this.password.getText().toString();

        tryLogin(username, password);
    }

    @Background
    void tryLogin(String username, String password) {
        try {
            final TokenContainerDTO tokenContainerDTO =
                    userWrapperAPI.login(username,password);

            loginSuccess(tokenContainerDTO.getAccessToken());
        } catch (Exception e) {
            showLoginError();
            Log.e(TAG, e.getMessage(), e);
        }


    }

    @UiThread
    void showLoginError() {
        Toast.makeText(this,
                "Invalid username and password combination.",
                Toast.LENGTH_SHORT)
                .show();
    }

    @Click
    void register() {
        RegisterActivity_.intent(this).startForResult(REGISTER_RESULT);
    }

    @OnActivityResult(value = REGISTER_RESULT)
    void loginUser(int resultCode, @OnActivityResult.Extra("username") String  username,@OnActivityResult.Extra("password") String  password) {
        if (resultCode == RESULT_OK) {
            tryLogin(username,password);
        }
    }

    @UiThread
    void loginSuccess(String accessToken) {
        final Intent intent = new Intent();
        intent.putExtra("token", accessToken);

        setResult(RESULT_OK, intent);
        finish();
    }
}
