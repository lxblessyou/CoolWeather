package android.coolweather.user.coolweather.activity;

import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.coolweather.user.coolweather.R;
import android.coolweather.user.coolweather.gson.DailyForecastBean;
import android.coolweather.user.coolweather.gson.Weather;
import android.coolweather.user.coolweather.util.HttpUtil;
import android.coolweather.user.coolweather.util.JsonUtil;
import android.graphics.Color;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ActivityWeather extends AppCompatActivity {
    // 声明控件
    private ScrollView sv_weather;
    private TextView tv_title_city;
    private TextView tv_title_update_time;
    private TextView tv_degree;
    private TextView tv_weather_info;
    private LinearLayout ll_forecast;
    private TextView tv_aqi;
    private TextView tv_pm25;
    private TextView tv_comfort;
    private TextView tv_car_wash;
    private TextView tv_sport;
    private ImageView iv_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 融入状态栏
        if (Build.VERSION.SDK_INT >= 21) {
            // 该功能只支持 Android 5.0 以上的版本
            // 获取当前窗口的装饰者
            View decorView = getWindow().getDecorView();
            // 窗口装饰者的布局标记为全屏或固定布局
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            // 状态栏透明化
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_weather);
        // 1.初始化控件
        sv_weather = (ScrollView) findViewById(R.id.sv_weather);
        tv_title_city = (TextView) findViewById(R.id.tv_title_city);
        tv_title_update_time = (TextView) findViewById(R.id.tv_title_update_time);
        tv_degree = (TextView) findViewById(R.id.tv_degree);
        tv_weather_info = (TextView) findViewById(R.id.tv_weather_info);
        ll_forecast = (LinearLayout) findViewById(R.id.ll_forecast);
        tv_aqi = (TextView) findViewById(R.id.tv_aqi);
        tv_pm25 = (TextView) findViewById(R.id.tv_pm25);
        tv_comfort = (TextView) findViewById(R.id.tv_comfort);
        tv_car_wash = (TextView) findViewById(R.id.tv_car_wash);
        tv_sport = (TextView) findViewById(R.id.tv_sport);
        iv_img = (ImageView) findViewById(R.id.iv_img);
        // 2.获取 SharedPreferences 对象，查询缓存
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        // 3.初始化天气背景
        String bingPic = prefs.getString("bing_pic", null);
        if (bingPic != null) {
            Glide.with(this).load(bingPic).into(iv_img);
        } else {
            showWeatherBackground();
        }
        // 4.初始化天气数据
        String weatherStr = prefs.getString("weather", null);
        if (weatherStr != null) {
            // 有缓存时直接解析天气数据
            Weather weather = JsonUtil.handleWeatherResponse(weatherStr);
            showWeatherInfo(weather);
        } else {
            // 无缓存时去服务器查询天气
            String weatherId = getIntent().getStringExtra("weather_id");
            sv_weather.setVisibility(View.INVISIBLE);
            requestWeather(weatherId);
        }
    }

    /**
     * 显示必应背景
     */
    private void showWeatherBackground() {
        String urlBingPic = "http://guolin.tech/api/bing_pic";
        HttpUtil.sendOkHttpRequest(urlBingPic, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String bingPic = response.body().string();
                SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(ActivityWeather.this);
                SharedPreferences.Editor edit = sp.edit();
                edit.putString("bing_pic", bingPic);
                edit.apply();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(ActivityWeather.this).load(bingPic).into(iv_img);
                    }
                });
            }
        });
    }

    /**
     * 根据天气 id 请求城市天气信息
     *
     * @param weatherId 天气 id
     */
    private void requestWeather(final String weatherId) {
        String weatherUrl = "http://guolin.tech/api/weather?cityid=" + weatherId + "&key=9a74fd6fc92c4663ba089d52da699b8e";
        HttpUtil.sendOkHttpRequest(weatherUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "获取天气信息失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String json = response.body().string();
                final Weather weather = JsonUtil.handleWeatherResponse(json);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (weather != null && "ok".equals(weather.getStatus())) {
                            SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(ActivityWeather.this).edit();
                            edit.putString("weather", json);
                            edit.apply();
                            showWeatherInfo(weather);
                        } else {
                            Toast.makeText(getApplicationContext(), "获取天气信息失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        // 显示天气背景
        showWeatherBackground();
    }

    /**
     * 显示天气实体类中信息
     *
     * @param weather 天气对象
     */
    private void showWeatherInfo(Weather weather) {
        // 1.标题和当前天气板块
        String cityName = weather.getBasic().getCityName();
        String updateLocTime = weather.getBasic().getUpdate().getUpdateLocTime();//.split(" ")[1];
        String degree = weather.getNow().getTemperature() + "℃";
        String weatherInfo = weather.getNow().getMore().getInfo();
        tv_title_city.setText(cityName);
        tv_title_update_time.setText(updateLocTime);
        tv_degree.setText(degree);
        tv_weather_info.setText(weatherInfo);
        // 3.移除 预报 布局中中所有 View ，重新建立
        ll_forecast.removeAllViews();
        // 4.预报板块
        for (DailyForecastBean forecast : weather.getForecastList()) {
            View view = LayoutInflater.from(this).inflate(R.layout.layout_sub_forecast, ll_forecast, false);
            TextView tv_date = (TextView) view.findViewById(R.id.tv_date);
            TextView tv_info = (TextView) view.findViewById(R.id.tv_info);
            TextView tv_max = (TextView) view.findViewById(R.id.tv_max);
            TextView tv_min = (TextView) view.findViewById(R.id.tv_min);
            tv_date.setText(forecast.getDate());
            tv_info.setText(forecast.getMore().getInfo());
            tv_max.setText(forecast.getTemperature().getMax() + "℃");
            tv_min.setText(forecast.getTemperature().getMin() + "℃");
            ll_forecast.addView(view);
        }
        // 5.空气质量板块
        if (weather.getAqi() != null) {
            tv_aqi.setText(weather.getAqi().getCity().getAqi());
            tv_pm25.setText(weather.getAqi().getCity().getPm25());
        }
        // 6.建议板块
        String comfort = "舒适度：" + weather.getSuggestion().getComfort().getInfo();
        String carWash = "洗车指数：" + weather.getSuggestion().getCarWash().getInfo();
        String sport = "运动指数：" + weather.getSuggestion().getSport().getInfo();
        tv_comfort.setText(comfort);
        tv_car_wash.setText(carWash);
        tv_sport.setText(sport);
        sv_weather.setVisibility(View.VISIBLE);
    }
}
