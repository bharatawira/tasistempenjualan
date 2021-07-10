package com.viss.fishstore;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionHandler {
    private static final String PREF_NAME = "UserSession";
    private static final String KEY_ID = "id";
    private static final String KEY_NAMA = "name";
    private static final String KEY_EMPTY = "";
    private Context mContext;
    private SharedPreferences.Editor mEditor;
    private SharedPreferences mPreferences;

    public SessionHandler(Context mContext) {
        this.mContext = mContext;
        mPreferences = mContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        this.mEditor = mPreferences.edit();
    }

    public void loginUser(String id, String nama) {
        mEditor.putString(KEY_ID, id);
        mEditor.putString(KEY_NAMA, nama);
        mEditor.commit();
    }

    public User getUserDetails() {
        User user = new User();
        user.setId(mPreferences.getString(KEY_ID, KEY_EMPTY));
        user.setNama(mPreferences.getString(KEY_NAMA, KEY_EMPTY));
        return user;
    }

    public void logoutUser(){
        mEditor.clear();
        mEditor.commit();
    }

}
