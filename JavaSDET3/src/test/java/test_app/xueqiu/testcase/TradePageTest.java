package test_app.xueqiu.testcase;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import test_app.xueqiu.page.MainPage;
import test_app.xueqiu.page.TradePage;

import static org.junit.jupiter.api.Assertions.*;

class TradePageTest {
    static TradePage tradePage;

    @BeforeAll
    static void beforeAll() {
        tradePage = new MainPage().toTrade();
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void a股开户() {
        assertEquals("请先获取验证码",
                tradePage.A股开户("18028750223", "123456")
                        .getToast(By.cssSelector(".toast"))
        );

    }

    @AfterAll
    static void afterAll(){
//        tradePage.quit();
    }
}