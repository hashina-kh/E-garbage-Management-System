package com.example.trashtracker;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class MemberSession {
    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "user";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    // User name (make variable public to access from outside)


    // Email address (make variable public to access from outside)


    // Constructor
    public MemberSession(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     * */
    public void createLoginSession(String id,String name, String email,String phonenumber,String password,String address,String ward){

        editor.putBoolean(IS_LOGIN, true);
        editor.putString("id", id);
        editor.putString("name", name);
        editor.putString("email", email);
        editor.putString("mobilenumber", phonenumber);
        editor.putString("password", password);
        editor.putString("address", address);
        editor.putString("ward", ward);
        editor.commit();
    }

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */
    public boolean checkLogin(){
        // Check login status
        if(this.isLoggedIn()) {
            return true;
        } else {
            return false;
            // user is not logged in redirect him to Login Activity
//            Intent i = new Intent(_context, LoginActivity.class);
            // Closing all the Activities
//            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
//            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
//            _context.startActivity(i);
        }

    }



    /**
     * Get stored session data
     * */
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put("id", pref.getString("id", null));
        user.put("name", pref.getString("name", null));
        user.put("email", pref.getString("email", null));
        user.put("mobilenumber", pref.getString("mobilenumber", null));
        user.put("password", pref.getString("password", null));
        user.put("address", pref.getString("address", null));
        user.put("ward", pref.getString("ward", null));

        return user;
    }

    /**
     * Clear session details
     * */
    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
//        Intent i = new Intent(_context, LoginActivity.class);
        // Closing all the Activities
//        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
//        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
//        _context.startActivity(i);
    }

    /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }

}
