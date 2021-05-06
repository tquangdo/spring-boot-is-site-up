package com.example.demo.controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlCheckController {
    private final String SITE_IS_UP = "Site is up!";
    private final String SITE_IS_DOWN = "Site is down!";
    private final String INCORRECT_URL = "URL is incorrect!!!";

    @GetMapping("/check")
    public String getUrlStatusMsg(@RequestParam String arg_url) {
        String returnMsg = "";
        try {
            URL urlObj = new URL(arg_url);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int respCodeCategory = conn.getResponseCode() / 100;
            System.out.println("respCodeCategory: " + respCodeCategory);
            if (respCodeCategory != 2 && respCodeCategory != 3) {
                returnMsg = SITE_IS_DOWN;
            } else {
                returnMsg = SITE_IS_UP;
            }
        } catch (MalformedURLException e) {
            returnMsg = INCORRECT_URL;
        } catch (IOException e) {
            returnMsg = SITE_IS_DOWN;
        }
        return returnMsg;
    }
}
