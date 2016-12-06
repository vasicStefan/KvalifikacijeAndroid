package com.example.vasic.kvalifikacijeexecom.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vasic.kvalifikacijeexecom.R;
import com.example.vasic.kvalifikacijeexecom.api.RestApi;
import com.example.vasic.kvalifikacijeexecom.api.wrapper.UserWrapperAPI;
import com.example.vasic.kvalifikacijeexecom.model.User;
import com.example.vasic.kvalifikacijeexecom.model.dto.TokenContainerDTO;
import com.example.vasic.kvalifikacijeexecom.util.NetworkingUtils;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.EditorAction;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;

/**
 * Created by vasic on 12/1/2016.
 */
@EActivity(R.layout.activity_register)
public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = RegisterActivity.class.getSimpleName();
    @ViewById
    EditText confirmPassword;

    @ViewById
    EditText username;

    @ViewById
    EditText password;

    @Bean
    UserWrapperAPI userWrapperAPI;


    @EditorAction(R.id.password)
    @Click
    void register() {

        final String username = this.username.getText().toString();
        final String password = this.password.getText().toString();
        final String confirmPassword = this.confirmPassword.getText().toString();
        final User user = new User(username, password, confirmPassword);

        registerUser(user);
    }


    @Background
    void registerUser(User user) {

        final boolean userCreated = userWrapperAPI.create(user);

        if (userCreated) {
            loginSuccess(user.getEmail(), user.getPassword());
        } else {
            Toast.makeText(this,
                    "Invalid registration ",
                    Toast.LENGTH_SHORT)
                    .show();
        }
    }


    @UiThread
    void loginSuccess(String username, String password) {
        final Intent intent = new Intent();
        intent.putExtra("username", username)
                .putExtra("password", password);

        setResult(RESULT_OK, intent);
        finish();
    }

}
