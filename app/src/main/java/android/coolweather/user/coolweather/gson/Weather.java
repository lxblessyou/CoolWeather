package android.coolweather.user.coolweather.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 2017-12-11.
 */

public class Weather {

    /**
     * aqi : {"city":{"aqi":"80","qlty":"良","pm25":"59","pm10":"108","no2":"50","so2":"19","co":"0.867","o3":"51"}}
     * aqi_forecast : []
     * basic : {"city":"滁州","cnty":"中国","id":"CN101221101","lat":"32.30362701","lon":"118.31626129","update":{"loc":"2017-12-11 12:52","utc":"2017-12-11 04:52"}}
     * forecastList : [{"astro":{"mr":"00:14","ms":"13:00","sr":"06:58","ss":"17:03"},"cond":{"code_d":"100","code_n":"101","txt_d":"晴","txt_n":"多云"},"date":"2017-12-11","hum":"33","pcpn":"0.0","pop":"0","pres":"1027","tmp":{"max":"12","min":"0"},"uv":"3","vis":"20","wind":{"deg":"91","dir":"东风","sc":"微风","spd":"7"}},{"astro":{"mr":"01:13","ms":"13:34","sr":"06:58","ss":"17:03"},"cond":{"code_d":"101","code_n":"101","txt_d":"多云","txt_n":"多云"},"date":"2017-12-12","hum":"59","pcpn":"0.0","pop":"0","pres":"1029","tmp":{"max":"9","min":"1"},"uv":"3","vis":"20","wind":{"deg":"90","dir":"东风","sc":"3-4","spd":"13"}},{"astro":{"mr":"02:09","ms":"14:07","sr":"06:59","ss":"17:03"},"cond":{"code_d":"101","code_n":"305","txt_d":"多云","txt_n":"小雨"},"date":"2017-12-13","hum":"63","pcpn":"0.0","pop":"0","pres":"1030","tmp":{"max":"10","min":"2"},"uv":"3","vis":"20","wind":{"deg":"84","dir":"东风","sc":"4-5","spd":"22"}}]
     * now : {"cond":{"code":"100","txt":"晴"},"fl":"4","hum":"20","pcpn":"0","pres":"1026","tmp":"12","vis":"10","wind":{"deg":"353","dir":"北风","sc":"微风","spd":"10"}}
     * status : ok
     * suggestion : {"air":{"brf":"中","txt":"气象条件对空气污染物稀释、扩散和清除无明显影响，易感人群应适当减少室外活动时间。"},"comf":{"brf":"较舒适","txt":"白天虽然天气晴好，但早晚会感觉偏凉，午后舒适、宜人。"},"cw":{"brf":"较适宜","txt":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"},"drsg":{"brf":"较冷","txt":"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。"},"flu":{"brf":"易发","txt":"昼夜温差很大，易发生感冒，请注意适当增减衣服，加强自我防护避免感冒。"},"sport":{"brf":"较适宜","txt":"天气较好，无雨水困扰，较适宜进行各种运动，但因气温较低，在户外运动请注意增减衣物。"},"trav":{"brf":"适宜","txt":"天气较好，温度适宜，是个好天气哦。这样的天气适宜旅游，您可以尽情地享受大自然的风光。"},"uv":{"brf":"中等","txt":"属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。"}}
     */

    private AqiBean aqi;
    private BasicBean basic;
    private NowBean now;
    private String status;
    private SuggestionBean suggestion;
    private List<?> aqi_forecast;
    @SerializedName("daily_forecast")
    private List<DailyForecastBean> forecastList;

    public AqiBean getAqi() {
        return aqi;
    }

    public void setAqi(AqiBean aqi) {
        this.aqi = aqi;
    }

    public BasicBean getBasic() {
        return basic;
    }

    public void setBasic(BasicBean basic) {
        this.basic = basic;
    }

    public NowBean getNow() {
        return now;
    }

    public void setNow(NowBean now) {
        this.now = now;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public SuggestionBean getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(SuggestionBean suggestion) {
        this.suggestion = suggestion;
    }

    public List<?> getAqi_forecast() {
        return aqi_forecast;
    }

    public void setAqi_forecast(List<?> aqi_forecast) {
        this.aqi_forecast = aqi_forecast;
    }

    public List<DailyForecastBean> getForecastList() {
        return forecastList;
    }

    public void setForecastList(List<DailyForecastBean> forecastList) {
        this.forecastList = forecastList;
    }
}
