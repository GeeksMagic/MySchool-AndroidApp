package com.gmt.myschool.database;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by user on 5/22/2016.
 */
public final class MySchoolPreferences {

    private static final String SCHOOL_PREFERENCES = "SCHOOL_PREFERENCES";

    public enum SCHOOL_PREFERENCES {
        IS_LOGGED_IN, ROLL_NUMBER, STUDENT_NAME, STUDENT_IMAGE;
    }

    private static SharedPreferences getSharedPreferences(final Context context) {
        return context.getSharedPreferences(SCHOOL_PREFERENCES,
                Context.MODE_PRIVATE);

    }

    public static String getString(final Context context, SCHOOL_PREFERENCES key) {
        return getSharedPreferences(context).getString(key.name(), null);
    }

    public static Integer getInteger(final Context context, SCHOOL_PREFERENCES key) {
        return getSharedPreferences(context).getInt(key.name(), -1);
    }

    public static Float getFloat(final Context context, SCHOOL_PREFERENCES key) {
        return getSharedPreferences(context).getFloat(key.name(), -1);
    }

    public static Long getLong(final Context context, SCHOOL_PREFERENCES key) {
        return getSharedPreferences(context).getLong(key.name(), -1);
    }

    public static Boolean getBoolean(final Context context, SCHOOL_PREFERENCES key) {
        return getSharedPreferences(context).getBoolean(key.name(), false);
    }

    public static void setSharedPreferences(final Context context, SCHOOL_PREFERENCES key,
                                            Object value) {
        SharedPreferences prefs = getSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        if (value instanceof String) {
            editor.putString(key.name(), value.toString());
        } else if (value instanceof Integer) {
            editor.putInt(key.name(), Integer.parseInt(value.toString()));
        } else if (value instanceof Float) {
            editor.putFloat(key.name(), Float.parseFloat(value.toString()));
        } else if (value instanceof Long) {
            editor.putLong(key.name(), Long.parseLong(value.toString()));
        } else if (value instanceof Boolean) {
            editor.putBoolean(key.name(), Boolean.valueOf(value.toString()));
        }
        editor.commit();
    }

    public static void clear(Context context){
        getSharedPreferences(context).edit().clear().commit();
    }
}
