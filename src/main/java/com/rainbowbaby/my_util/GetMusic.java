package com.rainbowbaby.my_util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.stereotype.Component;

/**
 * 工具类，获取音乐id
 */
@Component
public class GetMusic {
    public String GetMusicByName(String musicName){
        ChromeOptions options = new ChromeOptions();
        // 设置允许弹框
        //options.addArguments("disable-infobars","disable-web-security");
        // 设置无gui 开发时还是不要加，可以看到浏览器效果
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-gpu");
        options.addArguments("--hide-scrollbars");
        options.addArguments("blink-settings=imagesEnabled=false");
        //String driverPath =  "D:\\crawler-plugin\\chromedriver.exe";
        //System.setProperty("webdriver.chrome.driver", driverPath);
        RemoteWebDriver driver=  new ChromeDriver(options);
        driver.get("https://music.163.com/#/search/m/?s="+musicName);
        WebElement iframe = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(iframe);
        // 然后再去获取元素或者其他操作、操作完需切换回来
        //driver.switchTo().parentFrame();
        //driver.executeScript("return document.documentElement.outerHTML");
        String st = driver.findElement(By.className("text")).findElement(By.tagName("a")).getAttribute("href");
        //System.out.println(driver.findElement(By.tagName("a")).getText());
        System.out.println(st);
        driver.close();
        return st;
    }
}
