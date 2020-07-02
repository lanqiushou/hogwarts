package test_web.wework.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.concurrent.TimeUnit;

public class MainPage extends WebBasePage{

    public MainPage() {
        super();
//        System.setProperty("webdriver.gecko.driver", "/Users/seveniruby/ke/java_3/selenium/drivers/geckodriver");

        String url = "https://work.weixin.qq.com/wework_admin/frame";
//        FirefoxDriver driver=new FirefoxDriver();
        driver.get(url);
        driver.manage().deleteAllCookies();

        //todo: 改成从文件读取

        //done: 使用自己的cookie
        driver.manage().addCookie(new Cookie("_ga", "GA1.2.1689801186.1592085294"));
        driver.manage().addCookie(new Cookie("_gat", "1"));
        driver.manage().addCookie(new Cookie("_gid", "GA1.2.2044891347.1592789754"));
        driver.manage().addCookie(new Cookie("o_cookie", "363804430"));
        driver.manage().addCookie(new Cookie("pac_uid", "1_363804430"));
        driver.manage().addCookie(new Cookie("pgv_pvi", "4946772992"));
        driver.manage().addCookie(new Cookie("pgv_pvid", "231784032"));
        driver.manage().addCookie(new Cookie("ptcz", "cf9838a8d2d2a2b5f96581a1feed3b86e39cd2cbedc1dfe7e7a9530dcd4b00bc"));
        driver.manage().addCookie(new Cookie("RK", "GVrBEkPSSt"));
        driver.manage().addCookie(new Cookie("tvfe_boss_uuid", "2efc0ecfdc9c0467"));
        driver.manage().addCookie(new Cookie("Hm_lvt_9364e629af24cb52acc78b43e8c9f77d", "1592085294"));
        driver.manage().addCookie(new Cookie("wwrtx.d2st", "a7718447"));
        driver.manage().addCookie(new Cookie("wwrtx.i18n_lan", "zh"));
        driver.manage().addCookie(new Cookie("wwrtx.logined", "true"));
        driver.manage().addCookie(new Cookie("wwrtx.ltype", "1"));
        driver.manage().addCookie(new Cookie("wwrtx.ref", "direct"));
        driver.manage().addCookie(new Cookie("wwrtx.refid", "40271251752152153"));
        driver.manage().addCookie(new Cookie("wwrtx.sid", "3v6ofpNjzebb6RTv5TAadEhrPhcYvGPTpL8nTQjm0xCboSB74d1oWDImz8IQG_rv"));
        driver.manage().addCookie(new Cookie("wwrtx.vid", "1688850968926321"));
        driver.manage().addCookie(new Cookie("wwrtx.vst", "7STk6uTSwUhB3_04OsJ3KfdSDYyT1D5RnqEP9hft5Gb1ZbI68m-MURNDYkRmqapEfMi6mZ90tCA_wxWvJKnWZqLZz-5KJPp4lCvSuACsPJHHAo91ICYC-ZWu_WprD-ooEX37T_pO-_C3CvacH09N9vwSJUEWI8ph57iTP9dD97W46-eZNjGkQ3eyP1NZr1k2mspAkcmCn7sIXUPQnaezkciCWWyELiPBxWlNFInyjmD2zM565XTOyCBtO0h_xfS7pwo_i6IFNolUqVvDY79ddA"));
        driver.manage().addCookie(new Cookie("wxpay.corpid", "1970325099147855"));
        driver.manage().addCookie(new Cookie("wxpay.vid", "1688850968926321"));
        driver.manage().addCookie(new Cookie("ww_rtkey", "3136009287"));


        System.out.println(driver.manage().getCookies());
        driver.get(url);

    }

    public ContactPage toContact() {
        //todo:
        click(By.cssSelector("#menu_contacts"));
//        driver.findElement(By.cssSelector("#menu_contacts")).click();
        return new ContactPage(driver);
    }

}
