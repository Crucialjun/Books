package tk.crucial.books;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

public class SpUtils {
    private SpUtils(){

    }
    public static final String PREF_NAME = "BookPreferences";
    public static final String POSITION = "position";
    public static final String QUERY = "query";

    public static SharedPreferences getPrefs(Context context){
        return context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
    }

    public static String getPreferenceString(Context context,String key){
        return getPrefs(context).getString(key,"");

    }

    public static int getPreferenceInt(Context context,String key){
        return getPrefs(context).getInt(key,0);
    }

    public static void setPreferenceString(Context context,String key,String value){
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putString(key,value);
        editor.apply();
    }

    public static void setPreferenceInt(Context context,String key,int value){
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putInt(key,value);
        editor.apply();
    }

    public static ArrayList<String> getQueryList(Context context){
        ArrayList<String> querylist  = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            String query = getPrefs(context).getString(QUERY + Integer.toString(i),"");
            if (query != null && !query.isEmpty()) {
                query = query.replace(",", " ");
                querylist.add(query.trim());
            }
        }
        return querylist;
    }
}
