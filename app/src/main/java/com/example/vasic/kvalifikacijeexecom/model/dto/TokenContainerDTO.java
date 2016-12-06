package com.example.vasic.kvalifikacijeexecom.model.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vasic on 12/1/2016.
 */

public class TokenContainerDTO {

    @SerializedName("access_token")
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    @Override
    public String toString() {
        return "TokenContainerDTO{" +
                "accessToken='" + accessToken + '\'' +
                '}';
    }
}