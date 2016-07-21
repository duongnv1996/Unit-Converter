package com.duongkk.unitconverter.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by DuongKK on 3/28/2016.
 */
public class DataStore {
    final static String namePre ="Pref";
   public static SharedPreferences preferences;
    public  static  SharedPreferences.Editor editor;
    public static DataStore instance=null;

    public static DataStore getInstance() {
        if (instance == null) {
            throw new RuntimeException("DataStore has not initialized!");
        }
        return instance;
    }

    public void setInstance(DataStore instance) {

        this.instance = instance;
    }

    public static void init(Context context){
        if (instance==null){
            instance=new DataStore();
            preferences=context.getSharedPreferences(namePre, Context.MODE_APPEND);
        }

    }
    public DataStore saveString(String key,String msg){
        if(editor==null) editor=preferences.edit();
        editor.putString(key,msg);
        return this;
    }
    public String getString(String key){
        return preferences.getString(key,"");
    }
    public String getString(String key,String defaultVal){
        return preferences.getString(key,defaultVal);
    }
    public DataStore removeKey(String key) {
        if (editor == null)
            throw new RuntimeException("Pref Editor not started");
        editor.remove(key);
        return this;
    }
    public DataStore saveBoolen(String key,boolean msg){
        if(editor==null) editor=preferences.edit();
        editor.putBoolean(key, msg);
        return this;
    }
    public boolean getBoolen(String key,boolean defaultVal){
        return preferences.getBoolean(key, defaultVal);
    }
    public DataStore saveInt(String key,int msg){
        if(editor==null) editor=preferences.edit();
        editor.putInt(key, msg);
        return this;
    }
    public int getInt(String key,int defaultVal){
        return preferences.getInt(key, defaultVal);
    }
    public DataStore startEdit() {
        if (editor != null) {
            editor.apply();
        }
        editor = preferences.edit();
        return this;
    }

    public DataStore commit() {
        if (editor == null)
            throw new RuntimeException("Pref Editor not started");
        editor.apply();
        return this;
    }
}
