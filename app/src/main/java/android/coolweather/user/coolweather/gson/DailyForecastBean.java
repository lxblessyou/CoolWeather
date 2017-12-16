package android.coolweather.user.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 2017-12-11.
 */
public class DailyForecastBean {
    /**
     * astro : {"mr":"00:14","ms":"13:00","sr":"06:58","ss":"17:03"}
     * more : {"code_d":"100","code_n":"101","info":"晴","txt_n":"多云"}
     * date : 2017-12-11
     * hum : 33
     * pcpn : 0.0
     * pop : 0
     * pres : 1027
     * temperature : {"max":"12","min":"0"}
     * uv : 3
     * vis : 20
     * wind : {"deg":"91","dir":"东风","sc":"微风","spd":"7"}
     */

    private AstroBean astro;
    @SerializedName("cond")
    private More more;
    private String date;
    private String hum;
    private String pcpn;
    private String pop;
    private String pres;
    @SerializedName("tmp")
    private Temperature temperature;
    private String uv;
    private String vis;
    private WindBeanX wind;

    public AstroBean getAstro() {
        return astro;
    }

    public void setAstro(AstroBean astro) {
        this.astro = astro;
    }

    public More getMore() {
        return more;
    }

    public void setMore(More more) {
        this.more = more;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getPop() {
        return pop;
    }

    public void setPop(String pop) {
        this.pop = pop;
    }

    public String getPres() {
        return pres;
    }

    public void setPres(String pres) {
        this.pres = pres;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public String getUv() {
        return uv;
    }

    public void setUv(String uv) {
        this.uv = uv;
    }

    public String getVis() {
        return vis;
    }

    public void setVis(String vis) {
        this.vis = vis;
    }

    public WindBeanX getWind() {
        return wind;
    }

    public void setWind(WindBeanX wind) {
        this.wind = wind;
    }

    public static class AstroBean {
        /**
         * mr : 00:14
         * ms : 13:00
         * sr : 06:58
         * ss : 17:03
         */

        private String mr;
        private String ms;
        private String sr;
        private String ss;

        public String getMr() {
            return mr;
        }

        public void setMr(String mr) {
            this.mr = mr;
        }

        public String getMs() {
            return ms;
        }

        public void setMs(String ms) {
            this.ms = ms;
        }

        public String getSr() {
            return sr;
        }

        public void setSr(String sr) {
            this.sr = sr;
        }

        public String getSs() {
            return ss;
        }

        public void setSs(String ss) {
            this.ss = ss;
        }
    }

    public static class More {
        /**
         * code_d : 100
         * code_n : 101
         * info : 晴
         * txt_n : 多云
         */

        private String code_d;
        private String code_n;
        @SerializedName("txt_d")
        private String info;
        private String txt_n;

        public String getCode_d() {
            return code_d;
        }

        public void setCode_d(String code_d) {
            this.code_d = code_d;
        }

        public String getCode_n() {
            return code_n;
        }

        public void setCode_n(String code_n) {
            this.code_n = code_n;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getTxt_n() {
            return txt_n;
        }

        public void setTxt_n(String txt_n) {
            this.txt_n = txt_n;
        }
    }

    public static class Temperature {
        /**
         * max : 12
         * min : 0
         */

        private String max;
        private String min;

        public String getMax() {
            return max;
        }

        public void setMax(String max) {
            this.max = max;
        }

        public String getMin() {
            return min;
        }

        public void setMin(String min) {
            this.min = min;
        }
    }

    public static class WindBeanX {
        /**
         * deg : 91
         * dir : 东风
         * sc : 微风
         * spd : 7
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
