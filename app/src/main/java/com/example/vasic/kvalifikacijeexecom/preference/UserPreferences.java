package com.example.vasic.kvalifikacijeexecom.preference;

import org.androidannotations.annotations.sharedpreferences.SharedPref;

/**
 * Created by vasic on 12/1/2016.
 */

@SharedPref(SharedPref.Scope.UNIQUE)
public interface UserPreferences {

    long userId();

    String accessToken();


}