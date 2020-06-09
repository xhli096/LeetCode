package com.xinghaol.net.tennis;

import com.alibaba.fastjson.JSONObject;
import com.xinghaol.net.tennis.util.HttpUtil;
import com.xinghaol.net.tennis.vo.AddOrderResponseVo;
import com.xinghaol.net.tennis.vo.TennisResponseVo;
import com.xinghaol.net.tennis.vo.TennisUserInfo;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: lixinghao
 * @date: 2020/6/4 6:21 下午
 * @Description:
 */
public class TennisTest {
    private static String USERNAME = "15848017450";
    private static String PASSWORD = "diudiu521";

    private static String LOGIN_URL = "http://tennis.coopcloud.cn/TennisCenterInterface/umUser/userLogin.action?loginname=%s&password=%s";
    private static String GET_PLACE_URL = "http://tennis.coopcloud.cn/TennisCenterInterface/pmPark/getParkShowByParam.action";
    private static String USER_AGENT = "Mozilla/5.0 (Linux; Android 10; LYA-AL00 Build/HUAWEILYA-AL00; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/78.0.3904.62 XWEB/2353 MMWEBSDK/200502 Mobile Safari/537.36 MMWEBID/4791 MicroMessenger/7.0.15.1680(0x27000F39) Process/tools WeChat/arm64 NetType/WIFI Language/zh_CN ABI/arm64";
    private static String ADD_ORDER_URL = "http://tennis.coopcloud.cn/TennisCenterInterface/pmPark/addParkOrder.action";

    private static CookieManager cookieManager;
    private static Map<String, String> cookiesMap;
    private static TennisUserInfo tennisUserInfo;

    static {
        cookieManager = new CookieManager();
        cookiesMap = new ConcurrentHashMap<>();
    }

    public static void main(String[] args) throws IOException {
        init();
        getTennisPlaceInfo();
        order();
    }

    private static void init() {
        String response = HttpUtil.doGet(String.format(LOGIN_URL, USERNAME, PASSWORD));
        TennisResponseVo tennisResponseVo = JSONObject.parseObject(response, TennisResponseVo.class);
        cookiesMap.put("openid", tennisResponseVo.getDatas().getUser().getOpenid());
        tennisUserInfo = tennisResponseVo.getDatas().getUser();
        login();
    }

    private static void login() {
        try {
            CookieHandler.setDefault(cookieManager);
            cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);

            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(LOGIN_URL).openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.connect();

            if (httpURLConnection.getResponseCode() == HttpStatus.SC_OK) {
                CookieStore cookieStore = cookieManager.getCookieStore();
                List<HttpCookie> cookies = cookieStore.getCookies();
                for (HttpCookie httpCookie : cookies) {
                    cookiesMap.put(httpCookie.getName(), httpCookie.getValue());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void order() throws IOException {
        while (true) {
            String ss = "JSESSIONID=" + cookiesMap.get("JSESSIONID") +
                    "; openid=" + cookiesMap.get("openid");
            SimpleDateFormat YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");

            HttpPost httpPost = new HttpPost(ADD_ORDER_URL);
            // 创建集合 添加参数
            List<NameValuePair> list = new LinkedList<>();
            BasicNameValuePair userid = new BasicNameValuePair("userid", tennisUserInfo.getId() + "");
            BasicNameValuePair parkList = new BasicNameValuePair("parkList", "[{\"date\":\"2020-06-12\",\"time\":\"19\",\"parkid\":\"75\",\"parkname\":\"K10\"},{\"date\":\"2020-06-12\",\"time\":\"20\",\"parkid\":\"75\",\"parkname\":\"K10\"}]");
            BasicNameValuePair paywaycode = new BasicNameValuePair("paywaycode", "2");
            BasicNameValuePair addOrderType = new BasicNameValuePair("addOrderType", "wx");
            list.add(userid);
            list.add(parkList);
            list.add(paywaycode);
            list.add(addOrderType);
            // 使用URL实体转换工具
            UrlEncodedFormEntity entityParam = new UrlEncodedFormEntity(list, "UTF-8");
            httpPost.setEntity(entityParam);

            httpPost.addHeader("User-Agent", USER_AGENT);
            httpPost.addHeader("Connection", "keep-alive");
            httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");

            org.apache.http.client.CookieStore cookieStore = new BasicCookieStore();
            BasicClientCookie cookie = new BasicClientCookie("JSESSIONID", ss);
            cookie.setDomain("tennis.coopcloud.cn");
            cookie.setPath("/TennisCenterInterface/");
            cookie.setSecure(false);
            cookieStore.addCookie(cookie);
            HttpClient httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
            HttpResponse httpResponse = httpClient.execute(httpPost);
            // 将response对象转换成String类型
            String responseStr = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
            AddOrderResponseVo addOrderResponseVo = JSONObject.parseObject(responseStr, AddOrderResponseVo.class);
            if (Integer.parseInt(addOrderResponseVo.getRespCode()) == 1001) {
                System.out.println(addOrderResponseVo.getDatas().getOrderNo());
                break;
            }
        }
    }

    private static void getTennisPlaceInfo() throws IOException {
        String ss = "JSESSIONID=" + cookiesMap.get("JSESSIONID") +
                "; openid=" + cookiesMap.get("openid");
        SimpleDateFormat YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");

        HttpPost httpPost = new HttpPost(GET_PLACE_URL);
        // 创建集合 添加参数
        List<NameValuePair> list = new LinkedList<>();
        BasicNameValuePair userid = new BasicNameValuePair("userid", tennisUserInfo.getId() + "");
        BasicNameValuePair cardtypecode = new BasicNameValuePair("cardtypecode", "-1");
        BasicNameValuePair date = new BasicNameValuePair("date", YYYY_MM_DD.format(DateUtils.addDays(new Date(), 3)));
        BasicNameValuePair ballcode = new BasicNameValuePair("ballcode", "1");
        BasicNameValuePair parkstatus = new BasicNameValuePair("parkstatus", "0");
        BasicNameValuePair parktypeinfo = new BasicNameValuePair("parktypeinfo", "1");
        list.add(userid);
        list.add(cardtypecode);
        list.add(date);
        list.add(ballcode);
        list.add(parkstatus);
        list.add(parktypeinfo);
        // 使用URL实体转换工具
        UrlEncodedFormEntity entityParam = new UrlEncodedFormEntity(list, "UTF-8");
        httpPost.setEntity(entityParam);

        httpPost.addHeader("User-Agent", USER_AGENT);
        httpPost.addHeader("Connection", "keep-alive");
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");

        org.apache.http.client.CookieStore cookieStore = new BasicCookieStore();
        BasicClientCookie cookie = new BasicClientCookie("JSESSIONID", ss);
        cookie.setDomain("tennis.coopcloud.cn");
        cookie.setPath("/TennisCenterInterface/");
        cookie.setSecure(false);
        cookieStore.addCookie(cookie);
        HttpClient httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
        HttpResponse httpResponse = httpClient.execute(httpPost);
        // 将response对象转换成String类型
        String responseStr = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
    }
}
