package test_app.wework.page;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test_framework.BasePage;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
//import androidx.test.uiautomator.UiScrollable;
//import androidx.test.uiautomator.UiSelector;

public class AppBasePage extends BasePage{
    private final int timeOutInSecondsDefault = 60;
    //    AndroidDriver<MobileElement> driver;
    AppiumDriver<MobileElement> driver;
    //    IOSDriver
    WebDriverWait wait;
    String packageName;
    String activityName;

    public AppBasePage() {
    }

    public AppBasePage(String packageName, String activityName) {
        this.packageName = packageName;
        this.activityName = activityName;
        startApp(this.packageName, this.activityName);

    }

    public AppBasePage(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, timeOutInSecondsDefault);
    }

    public void startApp(String packageName, String activityName){
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("deviceName", "hogwarts");
        desiredCapabilities.setCapability("appPackage", packageName);
        desiredCapabilities.setCapability("appActivity", activityName);
        desiredCapabilities.setCapability("noReset", "true");
        desiredCapabilities.setCapability("udid", "");
        desiredCapabilities.setCapability("dontStopAppOnReset", "true");
        desiredCapabilities.setCapability("skipLogcatCapture", "true");

        URL remoteUrl = null;
        try {
            remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.exit(1);
        }

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        //todo: 等待优化
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, timeOutInSecondsDefault);
    }


    public void quit() {
        driver.quit();
    }


    public By byText(String text){
        return By.xpath("//*[@text='"+ text + "']");
    }
    public MobileElement find(By by) {
        return driver.findElement(by);
    }

    public MobileElement find(String text) {
        return driver.findElement(byText(text));
    }

    public List<MobileElement> finds(By by) {
        return driver.findElements(by);
    }

    public List<MobileElement> finds(String text) {
        return driver.findElements(byText(text));
    }

    public void click(By by) {
        //todo: 异常处理
        wait.until(ExpectedConditions.visibilityOfElementLocated(by)).click();
    }

    public void click(String text) {
        //todo: 异常处理
        find(text).click();
    }


    public void sendKeys(By by, String content) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by)).sendKeys(content);
//        driver.findElement(by).sendKeys(content);
    }

    //done:
    public WebElement waitElement(By by) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public WebElement scrollFind(String text) {
        AndroidDriver androidDriver = (AndroidDriver<MobileElement>) this.driver;

        return androidDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\""+text+"\").instance(0));");
    }

}
