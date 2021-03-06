package android.coolweather.user.coolweather.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.coolweather.user.coolweather.R;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ActivityMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        if (sp.getString("weather", null) != null) {
            Intent intent = new Intent(this, ActivityWeather.class);
            startActivity(intent);
            finish();
        }
    }
}
