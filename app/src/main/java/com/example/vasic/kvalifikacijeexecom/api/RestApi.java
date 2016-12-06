package com.example.vasic.kvalifikacijeexecom.api;

import com.example.vasic.kvalifikacijeexecom.api.interceptor.AuthenticationInterceptor;
import com.example.vasic.kvalifikacijeexecom.constant.ApiConstants;
import com.example.vasic.kvalifikacijeexecom.model.Task;
import com.example.vasic.kvalifikacijeexecom.model.User;
import com.example.vasic.kvalifikacijeexecom.model.dto.TokenContainerDTO;

import org.androidannotations.rest.spring.annotations.Body;
import org.androidannotations.rest.spring.annotations.Delete;
import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Header;
import org.androidannotations.rest.spring.annotations.Path;
import org.androidannotations.rest.spring.annotations.Post;
import org.androidannotations.rest.spring.annotations.Put;
import org.androidannotations.rest.spring.annotations.RequiresAuthentication;
import org.androidannotations.rest.spring.annotations.Rest;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;

import java.util.List;

/**
 * Created by vasic on 12/1/2016.
 */

@Rest(rootUrl = ApiConstants.ROOT_URL, converters = {GsonHttpMessageConverter.class,
        FormHttpMessageConverter.class},
        interceptors = AuthenticationInterceptor.class)
public interface RestApi {

    @Header(name = "Content-Type", value = "application/x-www-form-urlencoded")
    @Post(value = ApiConstants.LOGIN_PATH)
    TokenContainerDTO login(@Body LinkedMultiValueMap<String, String> accountInfo);

    @Get(value = ApiConstants.TASK_PATH)
    List<Task> getAllTasks();

    @Post(value = ApiConstants.TASK_PATH)
    Task createTask(@Body Task task);


    @Post(value = ApiConstants.REGISTER_PATH)
    User createUser(@Body User user);

    @Put(value = ApiConstants.UPDATE_TASK_PATH)
    Task updateTask(@Path int id,@Body Task task);

    @Delete(value = ApiConstants.DELETE_TASK_PATH)
    Task removeTask(@Path int id);


}