package android.coolweather.user.coolweather.util;

import android.coolweather.user.coolweather.db.City;
import android.coolweather.user.coolweather.db.County;
import android.coolweather.user.coolweather.db.Province;
import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by user on 2017-12-09.
 */

public class JsonUtil {
    /**
     * 处理省份响应
     *
     * @param json json字符串
     * @return 处理完成返回true，失败返回false
     */
    public static boolean handleProvinceResponse(String json)  {
        if (!TextUtils.isEmpty(json)) {
            try {
                JSONArray jsonArray = new JSONArray(json);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(jsonObject.getString("name"));
                    province.setProvinceCode(jsonObject.getInt("id"));
                    // LitePal 数据库对象记得保存
                    province.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 处理城市响应
     *
     * @param json       json字符串
     * @param provinceId 城市对应的省份代号
     * @return 处理完成返回true，失败返回false
     */
    public static boolean handleCityResponse(String json, int provinceId) {
        if (!TextUtils.isEmpty(json)) {
            try {
                JSONArray jsonArray = new JSONArray(json);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    City city = new City();
                    city.setCityName(jsonObject.getString("name"));
                    city.setCityCode(jsonObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    // LitePal 数据库对象记得保存
                    city.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 处理县区响应
     *
     * @param json   json字符串
     * @param cityId 县区对应的城市代号
     * @return 处理完成返回true，失败返回false
     */
    public static boolean handleCountyResponse(String json, int cityId) {
        if (!TextUtils.isEmpty(json)) {
            try {
                JSONArray jsonArray = new JSONArray(json);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(jsonObject.getString("name"));
                    county.setCityId(cityId);
                    county.setWeatherId(jsonObject.getString("weather_id"));
                    // LitePal 数据库对象记得保存
                    county.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
