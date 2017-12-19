package android.coolweather.user.coolweather.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.coolweather.user.coolweather.gson.Weather;
import android.coolweather.user.coolweather.util.HttpUtil;
import android.coolweather.user.coolweather.util.JsonUtil;
import android.os.IBinder;
import android.os.SystemClock;
import android.preference.PreferenceManager;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class AutoUpdateService extends Service {
    public AutoUpdateService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 1.更新天气信息
        updateWeather();
        // 2.更新每日一图
        updateBingPic();
        // 3.服务定时器定时更新
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        // 初始化时长
        int anHour = 8 * 60 * 60 * 1000;  // 8小时
        long triggerAtTime = SystemClock.elapsedRealtime() + anHour;  // 延时到的时间
        // 初始化意图
        Intent i = new Intent(this, AutoUpdateService.class);
        PendingIntent pi = PendingIntent.getService(this, 0, i, 0);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 更新每日一图
     */
    private void updateBingPic() {
        String bingPicUrl = "http://guolin.tech/api/bing_pic";
        HttpUtil.sendOkHttpRequest(bingPicUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // 1.获取响应的图片链接
                String bingPic = response.body().string();
                // 2.保存到 SharedPreferences 缓存中
                SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(AutoUpdateService.this).edit();
                edit.putString("bing_pic", bingPic);
                edit.apply();
            }
        });
    }

    /**
     * 更新天气信息
     */
    private void updateWeather() {
        // 1.获取缓存的天气 Json 数据
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String weatherJson = prefs.getString("weather", null);
        if (weatherJson != null) {
            // 1.有缓存时解析成 Weather 对象
            Weather weather = JsonUtil.handleWeatherResponse(weatherJson);
            // 2.获取天气 id ，拼接天气 URL
            String weatherId = weather.getBasic().getWeatherId();
            String weatherUrl = "http://guolin.tech/api/weather?cityid=" + weatherId + "&key=9a74fd6fc92c4663ba089d52da699b8e";
            // 3.访问网络请求天气信息
            HttpUtil.sendOkHttpRequest(weatherUrl, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    // 1.获取响应
                    String newWeatherJson = response.body().string();
                    // 2.解析 json
                    Weather newWeather = JsonUtil.handleWeatherResponse(newWeatherJson);
                    // 3.如果网络访问成功的操作
                    if (newWeather != null && "ok".equals(newWeather.getStatus())) {
                        // 存储缓存
                        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(AutoUpdateService.this).edit();
                        edit.putString("weather", newWeatherJson);
                        edit.apply();
                    }
                }
            });
        }
    }
}
