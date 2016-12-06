package com.example.vasic.kvalifikacijeexecom.api.wrapper;

import com.example.vasic.kvalifikacijeexecom.api.RestApi;
import com.example.vasic.kvalifikacijeexecom.model.Task;

import org.androidannotations.annotations.EBean;
import org.androidannotations.rest.spring.annotations.RestService;

import java.util.List;

/**
 * Created by vasic on 12/1/2016.
 */

@EBean
public class TaskWrapperAPI {

    @RestService
    RestApi restApi;

    public Task create(Task task){
        return restApi.createTask(task);
    }

    public void update(Task task ){
          Task newTas=restApi.updateTask(task.getId(),task);
    }

    public List<Task> getAllTasks(){
        return restApi.getAllTasks();
    }

    public void delete(int id){
        Task task=restApi.removeTask(id);
    }
}
