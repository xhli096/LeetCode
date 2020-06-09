package com.xinghaol.net.tennis.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.io.CharStreams;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author dongyangz
 * @date 21:50 2018/9/3
 */
public class HttpUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    /**
     * get请求
     */
    public static String doGet(String url) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        long start = System.currentTimeMillis();
        HttpClient client = HttpClients.createDefault();
        String strResult = null;
        try {
            //发送get请求
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);
            /**请求发送成功，并得到响应**/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                /**读取服务器返回过来的json字符串数据**/
                strResult = EntityUtils.toString(response.getEntity());
                return strResult;
            }
        } catch (Exception e) {
            logger.error("发送get请求失败,url={},errorMsg={}", url, e.getMessage(), e);
        } finally {
            logger.info("get请求url={}, result = {},time={}ms", url, strResult, System.currentTimeMillis() - start);
        }
        return null;
    }

    /**
     * post请求(用于key-value格式的参数)
     */
    public static String doPost(String url, Map<String, String> params) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        long start = System.currentTimeMillis();
        HttpClient client = HttpClients.createDefault();
        try {
            // 定义HttpClient
            // 实例化HTTP方法
            HttpPost request = new HttpPost();
            request.setURI(new URI(url));
            if (MapUtils.isNotEmpty(params)) {
                //设置参数
                List<NameValuePair> nvps = Lists.newArrayList();
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
                request.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
            }
            HttpResponse response = client.execute(request);
            int code = response.getStatusLine().getStatusCode();
            //请求成功
            if (code == HttpStatus.SC_OK) {
                return CharStreams.toString(new InputStreamReader(response.getEntity().getContent(), HTTP.UTF_8));
            }
        } catch (Exception e) {
            logger.error("发送post请求失败.errorMsg={}", e.getMessage(), e);
            return null;
        } finally {
            logger.info("post请求url={},param={},speed={}ms", url, JSON.toJSONString(params),
                    System.currentTimeMillis() - start);
        }
        return null;
    }

    /**
     * post请求图片站上传图片
     */
    public static String uploadPictureTosubFolder(String url, String subFolder, File file) throws Exception {
        if (StringUtils.isBlank(url) || StringUtils.isBlank(subFolder) || file == null) {
            logger.warn("上传文件参数不正确.url={},subFolder={}", url, subFolder);
            return null;
        }
        long start = System.currentTimeMillis();
        ByteArrayBody byteArrayBody = new ByteArrayBody(FileUtils.readFileToByteArray(file), file.getName());
        StringBody name = new StringBody(subFolder, ContentType.TEXT_PLAIN);
        HttpEntity reqEntity = MultipartEntityBuilder.create()
                .addPart("subfolder", name)
                .addPart("fileData", byteArrayBody).build();
        HttpPost httpPost = new HttpPost(new URI(url));
        httpPost.setEntity(reqEntity);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(httpPost);
        HttpEntity resultEntity = response.getEntity();
        String responseMessage = "";
        try {
            if (resultEntity != null) {
                InputStream is = resultEntity.getContent();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                StringBuffer sb = new StringBuffer();
                String line = "";
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                responseMessage = sb.toString();
            }
            EntityUtils.consume(resultEntity);
        } finally {
            if (null != response) {
                response.close();
            }
            logger.info("上传文件时间={}ms", System.currentTimeMillis() - start);
        }
        if (StringUtils.isBlank(responseMessage)) {
            return null;
        }
        JSONObject jsonObject = JSON.parseObject(responseMessage);
        if (Objects.equals(jsonObject, null) || Objects.equals(jsonObject.getJSONObject("msg"), null)
                || Objects.equals(jsonObject.getJSONObject("msg").get("url"), null)) {
            return null;
        }
        return jsonObject.getJSONObject("msg").get("url").toString();
    }


    /**
     * post请求上传一个文件
     */
    public static String uploadFile(String url, String fileKey, File file) {
        try {
            return uploadFile(url, fileKey, new FileInputStream(file), file.getName());
        } catch (FileNotFoundException e) {
            logger.error("上传文件到{}出现异常", url, e);
        }
        return "";
    }

    /**
     * post请求上传一个文件
     */
    public static String uploadFile(String url, String fileKey, InputStream fileStream, String fileName) {
        long start = System.currentTimeMillis();
        String responseMessage = "";
        try {
            ContentBody fileBody = new InputStreamBody(fileStream, fileName);
            HttpEntity reqEntity = MultipartEntityBuilder.create()
                    .addPart(fileKey, fileBody).build();
            HttpPost httpPost = new HttpPost(new URI(url));
            httpPost.setEntity(reqEntity);
            CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse response = httpClient.execute(httpPost);
            HttpEntity resultEntity = response.getEntity();
            if (resultEntity != null) {
                InputStream is = resultEntity.getContent();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                StringBuffer sb = new StringBuffer();
                String line = "";
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                responseMessage = sb.toString();
            }
            EntityUtils.consume(resultEntity);
        } catch (Exception e) {
            logger.error("上传文件到{}出现异常", url, e);
        } finally {
            logger.info("上传文件到{},结果{},时间={}ms", url, responseMessage, System.currentTimeMillis() - start);
        }
        return responseMessage;
    }


    public static <P, R> R doJsonPost(String url, P params, Class<R> returnClass) {
        String strParams = JSON.toJSONString(params);
        String resultStr = doJsonPost(url, strParams);
        if (null == resultStr) {
            return null;
        }
        return JSON.parseObject(resultStr, returnClass);
    }

    /**
     * post请求（用于请求json格式的参数）
     */
    public static String doJsonPost(String url, String params) {
        long start = System.currentTimeMillis();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-Type", "application/json");
        StringEntity entity = new StringEntity(params, HTTP.UTF_8);
        httpPost.setEntity(entity);
        CloseableHttpResponse response = null;
        String result = "";
        try {
            response = httpClient.execute(httpPost);
            StatusLine status = response.getStatusLine();
            int state = status.getStatusCode();
            if (state == HttpStatus.SC_OK) {
                result = EntityUtils.toString(response.getEntity());
                return result;
            }
        } catch (Exception e) {
            logger.error("发送post请求失败.errorMsg={}", e.getMessage(), e);
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                logger.error("httpClient关流异常");
            } finally {
                logger.info("post请求url={},param={},result={},speed={}ms", url, params, result,
                        System.currentTimeMillis() - start);
            }
        }
        return null;
    }

    /**
     * @param urlPath 下载路径
     * @return 获取到的文件
     */
    public static File downloadFile(String urlPath, String filePath) {
        long start = System.currentTimeMillis();
        File file = null;
        BufferedInputStream bin = null;
        OutputStream out = null;
        try {
            URL url = new URL(urlPath);
            URLConnection urlConnection = url.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            httpURLConnection.connect();
            String filePathUrl = httpURLConnection.getURL().getFile();
            String fileName = filePathUrl.substring(filePathUrl.lastIndexOf(File.separatorChar) + 1);
            bin = new BufferedInputStream(httpURLConnection.getInputStream());
            file = new File(filePath + fileName);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            out = new FileOutputStream(file);
            int size = 0;
            byte[] buf = new byte[1024];
            while ((size = bin.read(buf)) != -1) {
                out.write(buf, 0, size);
            }
        } catch (Exception e) {
            logger.error("下载文件出现未知异常", e);
        } finally {
            if (null != bin) {
                try {
                    bin.close();
                } catch (IOException e) {
                    logger.error("关闭文件输入流失败", e);
                }
            }
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    logger.error("关闭文件输出流失败", e);
                }
            }
            logger.info("下载文件{},size{},耗时{}ms", urlPath, null == file ? 0 : file.length(),
                    System.currentTimeMillis() - start);
            return file;
        }

    }
}

