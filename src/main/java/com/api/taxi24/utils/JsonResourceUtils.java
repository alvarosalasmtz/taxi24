package com.api.taxi24.utils;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Slf4j
public final class JsonResourceUtils {

    private JsonResourceUtils() {
    }

    public static String getStringFromResource(String filename) {
        try {
            InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
            if (inputStream != null) {
                StringBuilder sb = new StringBuilder();
                InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                BufferedReader bfReader = new BufferedReader(reader);
                String content = null;
                while ((content = bfReader.readLine()) != null) {
                    sb.append(content);
                }
                bfReader.close();
                return sb.toString();
            } else {
                log.info("[{}] file not exist", filename);
            }
        } catch (Exception e) {
            log.error("read file to string error", e);
        }
        return null;
    }

    public static <T> T getObjectByJsonFile(String filename, Class<T> classT) {
        String stringJson = JsonResourceUtils.getStringFromResource(filename);
        return new Gson().fromJson(stringJson, classT);
    }
}