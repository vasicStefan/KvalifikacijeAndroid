package com.example.vasic.kvalifikacijeexecom.api.wrapper;

import com.example.vasic.kvalifikacijeexecom.api.RestApi;
import com.example.vasic.kvalifikacijeexecom.model.User;
import com.example.vasic.kvalifikacijeexecom.model.dto.TokenContainerDTO;
import com.example.vasic.kvalifikacijeexecom.util.NetworkingUtils;

import org.androidannotations.annotations.EBean;
import org.androidannotations.rest.spring.annotations.RestService;

/**
 * Created by vasic on 12/1/2016.
 */
@EBean
public class UserWrapperAPI {

    @RestService
    RestApi restApi;

    public boolean create(User user){

        restApi.createUser(user);
        return true;
    }

    public TokenContainerDTO login(String username, String password){
        return restApi.login(NetworkingUtils.packUserCredentials(username, password));
    }
}
