package com.example.vasic.kvalifikacijeexecom.api.interceptor;

import com.example.vasic.kvalifikacijeexecom.preference.UserPreferences_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * Created by vasic on 12/1/2016.
 */

@EBean
public class AuthenticationInterceptor implements ClientHttpRequestInterceptor {

    @Pref
    UserPreferences_ userPreferences;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        request.getHeaders()
                .add("Authorization",
                        "bearer " + userPreferences.accessToken().getOr(""));
        return execution.execute(request, body);
    }
}
