package android.coolweather.user.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 2017-12-11.
 */
public class NowBean {
    /**
     * more : {"code":"100","info":"晴"}
     * fl : 4
     * hum : 20
     * pcpn : 0
     * pres : 1026
     * temperature : 12
     * vis : 10
     * wind : {"deg":"353","dir":"北风","sc":"微风","spd":"10"}
     */
    @SerializedName("cond")
    private CondBean more;
    private String fl;
    private String hum;
    private String pcpn;
    private String pres;
    @SerializedName("tmp")
    private String temperature;
    private String vis;
    private WindBean wind;

    public CondBean getMore() {
        return more;
    }

    public void setMore(CondBean more) {
        this.more = more;
    }

    public String getFl() {
        return fl;
    }

    public void setFl(String fl) {
        this.fl = fl;
    }

    public String getHum() {
        return hum;
    }

    public void setHum(String hum) {
        this.hum = hum;
    }

    public String getPcpn() {
        return pcpn;
    }

    public void setPcpn(String pcpn) {
        this.pcpn = pcpn;
    }

    public String getPres() {
        return pres;
    }

    public void setPres(String pres) {
        this.pres = pres;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getVis() {
        return vis;
    }

    public void setVis(String vis) {
        this.vis = vis;
    }

    public WindBean getWind() {
        return wind;
    }

    public void setWind(WindBean wind) {
        this.wind = wind;
    }

    public static class CondBean {
        /**
         * code : 100
         * info : 晴
         */

        private String code;
        @SerializedName("txt")
        private String info;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }

    public static class WindBean {
        /**
         * deg : 353
         * dir : 北风
         * sc : 微风
         * spd : 10
         */

        private String deg;
        private String dir;
        private String sc;
        private String spd;

        public String getDeg() {
            return deg;
        }

        public void setDeg(String deg) {
            this.deg = deg;
        }

        public String getDir() {
            return dir;
        }

        public void setDir(String dir) {
            this.dir = dir;
        }

        public String getSc() {
            return sc;
        }

        public void setSc(String sc) {
            this.sc = sc;
        }

        public String getSpd() {
            return spd;
        }

        public void setSpd(String spd) {
            this.spd = spd;
        }
    }
}
