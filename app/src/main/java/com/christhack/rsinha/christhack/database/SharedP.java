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
    public static double getAnger(Context cont){
        createSharedPref(cont);
        return sp.getFloat("Anger", 0);
    }
    public static double getFear(Context cont){
        createSharedPref(cont);
        return sp.getFloat("Fear", 0);
    }
    public static double getHappy(Context cont){
        createSharedPref(cont);
        return sp.getFloat("Happy", 0);
    }
    public static double getSad(Context cont){
        createSharedPref(cont);
        return sp.getFloat("Sad", 0);
    }
    public static double getDisgust(Context cont){
        createSharedPref(cont);
        return sp.getFloat("Disgust", 0);
    }


    public static double getAnger1(Context cont){
        createSharedPref(cont);
        return sp.getFloat("AngerO", 0);
    }
    public static double getFear1(Context cont){
        createSharedPref(cont);
        return sp.getFloat("FearO", 0);
    }
    public static double getHappy1(Context cont){
        createSharedPref(cont);
        return sp.getFloat("HappyO", 0);
    }
    public static double getSad1(Context cont){
        createSharedPref(cont);
        return sp.getFloat("SadO", 0);
    }
    public static double getDisgust1(Context cont){
        createSharedPref(cont);
        return sp.getFloat("DisgustO", 0);
    }


    public static String getValues(Context cont, String name){
        createSharedPref(cont);
        return sp.getString(name, "U0000");
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
    public static void setAnger(Context cont,double id){
        createSharedPref(cont);
        SharedPreferences.Editor spe = sp.edit();
        spe.putFloat("Anger", (float)id);
        spe.apply();
    }
    public static void setFear(Context cont,double id){
        createSharedPref(cont);
        SharedPreferences.Editor spe = sp.edit();
        spe.putFloat("Fear", (float)id);
        spe.apply();
    }
    public static void setHappy(Context cont,double id){
        createSharedPref(cont);
        SharedPreferences.Editor spe = sp.edit();
        spe.putFloat("Happy", (float)id);
        spe.apply();
    }
    public static void setSad(Context cont,double id){
        createSharedPref(cont);
        SharedPreferences.Editor spe = sp.edit();
        spe.putFloat("Sad", (float)id);
        spe.apply();
    }
    public static void setDisgust(Context cont,double id){
        createSharedPref(cont);
        SharedPreferences.Editor spe = sp.edit();
        spe.putFloat("Disgust", (float)id);
        spe.apply();
    }
    public static void setValues(Context cont,String name, String value){
        createSharedPref(cont);
        SharedPreferences.Editor spe = sp.edit();
        spe.putString(name, value);
        spe.apply();
    }



    public static void setAnger1(Context cont,double id){
        createSharedPref(cont);
        SharedPreferences.Editor spe = sp.edit();
        spe.putFloat("AngerO", (float)id);
        spe.apply();
    }
    public static void setFear1(Context cont,double id){
        createSharedPref(cont);
        SharedPreferences.Editor spe = sp.edit();
        spe.putFloat("FearO", (float)id);
        spe.apply();
    }
    public static void setHappy1(Context cont,double id){
        createSharedPref(cont);
        SharedPreferences.Editor spe = sp.edit();
        spe.putFloat("HappyO", (float)id);
        spe.apply();
    }
    public static void setSad1(Context cont,double id){
        createSharedPref(cont);
        SharedPreferences.Editor spe = sp.edit();
        spe.putFloat("SadO", (float)id);
        spe.apply();
    }
    public static void setDisgust1(Context cont,double id){
        createSharedPref(cont);
        SharedPreferences.Editor spe = sp.edit();
        spe.putFloat("DisgustO", (float)id);
        spe.apply();
    }

}
