package cn.edu.gdpt.wechatnews;

import android.content.Context;
import android.content.SharedPreferences;

public class SPUtils {
    SharedPreferences sp;
    Context context;
    SharedPreferences.Editor edit;

    public SPUtils(Context context) {
        this.context = context;
        sp = context.getSharedPreferences("info", Context.MODE_PRIVATE);
        edit = sp.edit();
    }

    public void putBoolean(String key, boolean value) {
        edit.putBoolean(key, value).apply();
    }

    public void putString(String key, String value) {
        edit.putString(key, value).apply();
    }
    public boolean getBoolean(String key, boolean value) {
        return sp.getBoolean(key, value);
    }
    public String getString(String key, String value) {
        return sp.getString(key, value);
    }

}
