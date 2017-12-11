package android.coolweather.user.coolweather.util;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by user on 2017-12-09.
 */

public class HttpUtil {
    /**
     * 用OkHttp发送网络请求
     *
     * @param urlPath  URL路径
     * @param callback 回调接口
     */
    public static void sendOkHttpRequest(String urlPath, Callback callback) {
        // 1.实例化 OkHttpClient 客户端
        OkHttpClient client = new OkHttpClient();
        // 2.实例化 Request.Builder 并添加 URL，然后构建出 Request 请求对象
        Request request = new Request.Builder().url(urlPath).build();
        // 3.用客户端调用 request 请求对象并把回调接口传入执行队列
        client.newCall(request).enqueue(callback);
    }
}
