package com.xinghaol.guidedubbo.framework.common.util;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author: lixinghao
 * @date: 2020/7/24 10:55 下午
 * @Description: 读取properties文件工具类
 */
@Slf4j
public class PropertiesFileUtil {
    public PropertiesFileUtil() {
    }

    /**
     * 读取properties文件
     *
     * @param fileName 文件名称
     * @return
     */
    public static Properties readProperties(String fileName) {
        // 获取绝对根路径
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String configFilePath = rootPath + fileName;
        Properties properties = null;

        try (FileInputStream fileInputStream = new FileInputStream(configFilePath)) {
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("读取配置文件内容出错， file [{}]", fileName);
        }

        return properties;
    }
}
