package android.coolweather.user.coolweather.db;

import org.litepal.crud.DataSupport;

/**
 * Created by user on 2017-12-08.
 */

public class City extends DataSupport {
    /**
     * id   id
     * cityName 城市名称
     * cityCode 城市编码
     * provinceId   省份id
     */
    private int id;
    private String cityName;
    private int cityCode;
    private int provinceId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }
}
