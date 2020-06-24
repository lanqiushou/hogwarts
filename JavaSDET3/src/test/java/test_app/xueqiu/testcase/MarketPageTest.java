package test_app.xueqiu.testcase;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import test_app.xueqiu.page.MainPage;
import test_app.xueqiu.page.MarketPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

class MarketPageTest {
    static MainPage mainPage = new MainPage();
    static MarketPage marketPage;

    @BeforeAll
    static void setup(){
        marketPage = mainPage.toMarket();
    }


    @Test
    void clearAll() {
//        System.out.println(marketPage.clearAll().find(By.id("com.xueqiu.android:id/tv_des")).getText());
        assertThat(marketPage.clearAll().find(By.id("com.xueqiu.android:id/tv_des")).getText(),containsString("添加感兴趣的股票"));
    }

    @AfterAll
    static void tearDown() {
        marketPage.quit();
    }
}