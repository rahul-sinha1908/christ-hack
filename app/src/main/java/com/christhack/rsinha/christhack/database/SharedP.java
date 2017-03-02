package com.christhack.rsinha.christhack.database;

/**
 * Created by rsinha on 3/2/17.
 */

import android.content.Context;
import android.content.SharedPreferences;

public class SharedP {
    public static SharedPreferences sp;

    public static SharedPreferences createSharedPref(Context cont){
        if(sp==null)
            sp=cont.getSharedPreferences("MyUserName",Context.MODE_PRIVATE);

        return sp;
    }
    public static String getMyID(Context cont){
        createSharedPref(cont);
        return sp.getString("MyID", "U0000");
    }
    public static String getValues(Context cont, String name){
        createSharedPref(cont);
        return sp.getString(name, "");
    }
    public static String getValues(Context cont, String name, String defaultV){
        createSharedPref(cont);
        return sp.getString(name, defaultV);
    }
    public static void setMyID(Context cont,String id){
        createSharedPref(cont);
        SharedPreferences.Editor spe = sp.edit();
        spe.putString("MyID", id);
        spe.apply();
    }
    public static void setValues(Context cont,String name, String value){
        createSharedPref(cont);
        SharedPreferences.Editor spe = sp.edit();
        spe.putString(name, value);
        spe.apply();
    }

}
