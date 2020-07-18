package test_web.wework.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test_framework.BasePage;

//import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WebBasePage extends BasePage {
    RemoteWebDriver driver;
    WebDriverWait wait;

    public WebBasePage() {
        driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        selenium 4.0 use duration
//        wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        wait=new WebDriverWait(driver, 30);
    }

    public WebBasePage(RemoteWebDriver driver) {
        this.driver = driver;
//        wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        wait=new WebDriverWait(driver,30);

    }


    public void quit() {
        driver.quit();
    }

    public void click(By by){
        System.out.println(by.toString());
        //todo: 异常处理
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        wait.until(ExpectedConditions.elementToBeClickable(by));
        driver.findElement(by).click();
    }

    public void clickNonClickble(By by) {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        driver.findElement(by).click();
    }


    @Override
    public String getResult(HashMap<String, Object> m) {
        super.getResult(m);
        HashMap<String, Object> result = (HashMap<String, Object>) m.get("expect");

        String actualResult = driver.findElement(getLocator(result)).getText();
        String expectedResult = (String)result.get("result");

        return actualResult.equals(expectedResult) ? "passed" : "failed";
    }

    @Override
    public void sendKeys(HashMap<String, Object> map,HashMap<String, Object> params) {
        super.sendKeys(map, params);

        String keys = null;
        if(null == params) {
            keys = (String)map.get("sendKeys");
        } else {
            //如果参数不为空，从参数中获取实际要输入的值
            keys = (String)params.get(map.get("sendKeys"));
        }
        sendKeys(getLocator(map), keys);
    }

    public void sendKeys(By by, String content){
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        driver.findElement(by).sendKeys(content);
    }

    public void upload(By by, String path){
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        driver.findElement(by).sendKeys(path);
    }


    @Override
    public void click(HashMap<String, Object> map) {
        super.click(map);
//        String key= (String) map.keySet().toArray()[0];
//        String value= (String) map.values().toArray()[0];
//
//        By by = null;
//        if(key.toLowerCase().equals("id")){
//            by=By.id(value);
//        }
//        if(key.toLowerCase().equals("linkText".toLowerCase())){
//            by=By.linkText(value);
//        }
//
//        if(key.toLowerCase().equals("partialLinkText".toLowerCase())){
//            by=By.partialLinkText(value);
//        }

        click(getLocator(map));
    }

    @Override
    public void action(HashMap<String, Object> map) {
        super.action(map);

        if(map.containsKey("action")) {
            String action = map.get("action").toString().toLowerCase();
            if (action.equals("get")) {
                String url = map.get("url").toString();
                driver.get(url);

                if(map.containsKey("cookies")) {
                    driver.manage().deleteAllCookies();

                    HashMap<String, Object> cookies = (HashMap<String, Object>)map.get("cookies");
                    cookies.forEach((key, value) -> {
                        driver.manage().addCookie(new Cookie(key, (String)value));
                    });

                    System.out.println(driver.manage().getCookies());
                    driver.get(url);
                }
            }


        }
    }
}
