package kg.geektrch.newapp38;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

public class Prefs {
    private SharedPreferences preferences;
    public Prefs(Context context) {
        preferences = context.getSharedPreferences("database", Context.MODE_PRIVATE);
    }
    public void dataSave(String name){
        preferences.edit().putString("name",name).apply();
    }
    public String getSave(){
        return preferences.getString("name","");
    }
    public void photoSave(Uri photo){
        preferences.edit().putString("photo",photo.toString()).apply();
    }
    public String getPhoto(){
        return preferences.getString("photo","");
    }
}
