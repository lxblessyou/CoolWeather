package android.coolweather.user.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 2017-12-11.
 */
public class SuggestionBean {
    /**
     * air : {"brf":"中","info":"气象条件对空气污染物稀释、扩散和清除无明显影响，易感人群应适当减少室外活动时间。"}
     * comfort : {"brf":"较舒适","info":"白天虽然天气晴好，但早晚会感觉偏凉，午后舒适、宜人。"}
     * carWash : {"brf":"较适宜","info":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"}
     * drsg : {"brf":"较冷","info":"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。"}
     * flu : {"brf":"易发","info":"昼夜温差很大，易发生感冒，请注意适当增减衣服，加强自我防护避免感冒。"}
     * sport : {"brf":"较适宜","info":"天气较好，无雨水困扰，较适宜进行各种运动，但因气温较低，在户外运动请注意增减衣物。"}
     * trav : {"brf":"适宜","info":"天气较好，温度适宜，是个好天气哦。这样的天气适宜旅游，您可以尽情地享受大自然的风光。"}
     * uv : {"brf":"中等","info":"属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。"}
     */

    private AirBean air;
    @SerializedName("comf")
    private Comfort comfort;
    @SerializedName("cw")
    private CarWash carWash;
    private DrsgBean drsg;
    private FluBean flu;
    private SportBean sport;
    private TravBean trav;
    private UvBean uv;

    public AirBean getAir() {
        return air;
    }

    public void setAir(AirBean air) {
        this.air = air;
    }

    public Comfort getComfort() {
        return comfort;
    }

    public void setComfort(Comfort comfort) {
        this.comfort = comfort;
    }

    public CarWash getCarWash() {
        return carWash;
    }

    public void setCarWash(CarWash carWash) {
        this.carWash = carWash;
    }

    public DrsgBean getDrsg() {
        return drsg;
    }

    public void setDrsg(DrsgBean drsg) {
        this.drsg = drsg;
    }

    public FluBean getFlu() {
        return flu;
    }

    public void setFlu(FluBean flu) {
        this.flu = flu;
    }

    public SportBean getSport() {
        return sport;
    }

    public void setSport(SportBean sport) {
        this.sport = sport;
    }

    public TravBean getTrav() {
        return trav;
    }

    public void setTrav(TravBean trav) {
        this.trav = trav;
    }

    public UvBean getUv() {
        return uv;
    }

    public void setUv(UvBean uv) {
        this.uv = uv;
    }

    public static class AirBean {
        /**
         * brf : 中
         * info : 气象条件对空气污染物稀释、扩散和清除无明显影响，易感人群应适当减少室外活动时间。
         */

        private String brf;
        private String txt;

        public String getBrf() {
            return brf;
        }

        public void setBrf(String brf) {
            this.brf = brf;
        }

        public String getTxt() {
            return txt;
        }

        public void setTxt(String txt) {
            this.txt = txt;
        }
    }

    public static class Comfort {
        /**
         * brf : 较舒适
         * info : 白天虽然天气晴好，但早晚会感觉偏凉，午后舒适、宜人。
         */

        private String brf;
        @SerializedName("txt")
        private String info;

        public String getBrf() {
            return brf;
        }

        public void setBrf(String brf) {
            this.brf = brf;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }

    public static class CarWash {
        /**
         * brf : 较适宜
         * info : 较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。
         */

        private String brf;
        @SerializedName("txt")
        private String info;

        public String getBrf() {
            return brf;
        }

        public void setBrf(String brf) {
            this.brf = brf;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }

    public static class DrsgBean {
        /**
         * brf : 较冷
         * info : 建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。
         */

        private String brf;
        private String txt;

        public String getBrf() {
            return brf;
        }

        public void setBrf(String brf) {
            this.brf = brf;
        }

        public String getTxt() {
            return txt;
        }

        public void setTxt(String txt) {
            this.txt = txt;
        }
    }

    public static class FluBean {
        /**
         * brf : 易发
         * info : 昼夜温差很大，易发生感冒，请注意适当增减衣服，加强自我防护避免感冒。
         */

        private String brf;
        private String txt;

        public String getBrf() {
            return brf;
        }

        public void setBrf(String brf) {
            this.brf = brf;
        }

        public String getTxt() {
            return txt;
        }

        public void setTxt(String txt) {
            this.txt = txt;
        }
    }

    public static class SportBean {
        /**
         * brf : 较适宜
         * info : 天气较好，无雨水困扰，较适宜进行各种运动，但因气温较低，在户外运动请注意增减衣物。
         */

        private String brf;
        @SerializedName("txt")
        private String info;

        public String getBrf() {
            return brf;
        }

        public void setBrf(String brf) {
            this.brf = brf;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }

    public static class TravBean {
        /**
         * brf : 适宜
         * info : 天气较好，温度适宜，是个好天气哦。这样的天气适宜旅游，您可以尽情地享受大自然的风光。
         */

        private String brf;
        private String txt;

        public String getBrf() {
            return brf;
        }

        public void setBrf(String brf) {
            this.brf = brf;
        }

        public String getTxt() {
            return txt;
        }

        public void setTxt(String txt) {
            this.txt = txt;
        }
    }

    public static class UvBean {
        /**
         * brf : 中等
         * info : 属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。
         */

        private String brf;
        private String txt;

        public String getBrf() {
            return brf;
        }

        public void setBrf(String brf) {
            this.brf = brf;
        }

        public String getTxt() {
            return txt;
        }

        public void setTxt(String txt) {
            this.txt = txt;
        }
    }
}
