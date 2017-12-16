package android.coolweather.user.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 2017-12-11.
 */
public class BasicBean {
    /**
     * cityName : 滁州
     * cnty : 中国
     * weatherId : CN101221101
     * lat : 32.30362701
     * lon : 118.31626129
     * update : {"updateLocTime":"2017-12-11 12:52","updateUtcTime":"2017-12-11 04:52"}
     */
    @SerializedName("city")
    private String cityName;
    private String cnty;
    @SerializedName("id")
    private String weatherId;
    private String lat;
    private String lon;
    private UpdateBean update;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCnty() {
        return cnty;
    }

    public void setCnty(String cnty) {
        this.cnty = cnty;
    }

    public String getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(String weatherId) {
        this.weatherId = weatherId;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public UpdateBean getUpdate() {
        return update;
    }

    public void setUpdate(UpdateBean update) {
        this.update = update;
    }

    public static class UpdateBean {
        /**
         * updateLocTime : 2017-12-11 12:52
         * updateUtcTime : 2017-12-11 04:52
         */
        @SerializedName("loc")
        private String updateLocTime;
        @SerializedName("utc")
        private String updateUtcTime;

        public String getUpdateLocTime() {
            return updateLocTime;
        }

        public void setUpdateLocTime(String updateLocTime) {
            this.updateLocTime = updateLocTime;
        }

        public String getUpdateUtcTime() {
            return updateUtcTime;
        }

        public void setUpdateUtcTime(String updateUtcTime) {
            this.updateUtcTime = updateUtcTime;
        }
    }
}
