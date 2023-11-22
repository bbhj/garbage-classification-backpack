package cn.czh0123.garbageclassification.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import okhttp3.*;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 获取百度ai token
 */
public class BaiduTokenUtil {
    static final OkHttpClient HTTP_CLIENT = new OkHttpClient().newBuilder().build();

    public static String getToken() throws IOException {

        String clientId = "68ME3MFlub4PQKTpBQ7h5LuQ";
        String clientSecret = "RZYlNQh5eBldgcQ8EHiH8LYOn16rGD5o";

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("https://aip.baidubce.com/oauth/2.0/token?client_id="+clientId+"&client_secret="+clientSecret+"&grant_type=client_credentials")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build();
        Response response = HTTP_CLIENT.newCall(request).execute();
        String result = response.body().string();
        return JSON.parseObject(result).getString("access_token");
    }

}
