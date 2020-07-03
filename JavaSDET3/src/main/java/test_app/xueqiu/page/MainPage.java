package test_app.xueqiu.page;

import org.openqa.selenium.By;

public class MainPage extends BasePage{

    public SearchPage toSearch(){
        click(By.id("home_search"));
//        MobileElement el4 = (MobileElement) driver.findElementById("com.xueqiu.android:id/home_search");
//        el4.click();
        return new SearchPage(driver);
    }

    public MarketPage toMarket(){
        click(By.xpath("//*[@resource-id='com.xueqiu.android:id/tab_name' and @text='行情']"));

        return new MarketPage(driver);
    }

    public TradePage toTrade() {
        click(By.xpath("//*[@text='交易']"));

        return new TradePage(driver);
    }

}
