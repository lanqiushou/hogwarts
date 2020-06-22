package test_web.wework;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestWeWork {
    @Test
    void chrome(){
//        System.setProperty("webdriver.gecko.driver", "/Users/seveniruby/ke/java_3/selenium/drivers/geckodriver");

        String url="https://work.weixin.qq.com/wework_admin/frame";
//        FirefoxDriver driver=new FirefoxDriver();
        ChromeDriver driver=new ChromeDriver();
        driver.get(url);
        driver.manage().deleteAllCookies();

        driver.manage().addCookie(new Cookie("_ga", "GA1.2.1689801186.1592085294"));
        driver.manage().addCookie(new Cookie("_gid", "GA1.2.17725442.1592085294"));
        driver.manage().addCookie(new Cookie("o_cookie", "363804430"));
        driver.manage().addCookie(new Cookie("pac_uid", "1_363804430"));
        driver.manage().addCookie(new Cookie("pgv_info", "ssid=s1095849912"));
        driver.manage().addCookie(new Cookie("pgv_pvi", "4946772992"));
        driver.manage().addCookie(new Cookie("pgv_pvid", "231784032"));
        driver.manage().addCookie(new Cookie("ptcz", "cf9838a8d2d2a2b5f96581a1feed3b86e39cd2cbedc1dfe7e7a9530dcd4b00bc"));
        driver.manage().addCookie(new Cookie("RK", "GVrBEkPSSt"));
        driver.manage().addCookie(new Cookie("tvfe_boss_uuid", "2efc0ecfdc9c0467"));
        driver.manage().addCookie(new Cookie("Hm_lpvt_9364e629af24cb52acc78b43e8c9f77d", "1592085435"));
        driver.manage().addCookie(new Cookie("Hm_lvt_9364e629af24cb52acc78b43e8c9f77d", "1592085294"));
        driver.manage().addCookie(new Cookie("wwrtx.d2st", "a7331051"));
        driver.manage().addCookie(new Cookie("wwrtx.i18n_lan", "zh"));
        driver.manage().addCookie(new Cookie("wwrtx.logined", "true"));
        driver.manage().addCookie(new Cookie("wwrtx.ltype", "1"));
        driver.manage().addCookie(new Cookie("wwrtx.ref", "direct"));
        driver.manage().addCookie(new Cookie("wwrtx.refid", "03291676"));
        driver.manage().addCookie(new Cookie("wwrtx.sid", "3v6ofpNjzebb6RTv5TAadE4vj0MdVwRM_bJaulY-_4yNepbo-82fV0I-ocDUa-Qo"));
        driver.manage().addCookie(new Cookie("wwrtx.vid", "1688850968926321"));
        driver.manage().addCookie(new Cookie("wwrtx.vst", "FySRCoPrt9m_3yrqZCU2uJ2GpVVhRuJElhpsLvZIXLbgOnUpeGym-jC4IoEMJBSokrA_8AwFZ1VgDriles5b9AiegdBzjv81yOaOypPWvlA0YyNVckzdjAQzNssRkEgwY-267FwvXieIc14ZmMyXTmEsiObuaZnGUgJZkylghaIjILWoC25WkzmxEmEMvqasKDzaBukOhuZbU4DybOM0TLyQ7pOCByrSeDqKc40CVUn0-VfkDU9VUSr8vePxjdZu8lJIxVRzt04rM_rbHLDEug"));
        driver.manage().addCookie(new Cookie("wxpay.corpid", "1970325099147855"));
        driver.manage().addCookie(new Cookie("wxpay.vid", "1688850968926321"));
        driver.manage().addCookie(new Cookie("ww_rtkey", "5677510529"));

        System.out.println(driver.manage().getCookies());
        driver.get(url);

    }
}
