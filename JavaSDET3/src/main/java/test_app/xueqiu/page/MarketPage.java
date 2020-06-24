package test_app.xueqiu.page;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

public class MarketPage extends BasePage{
    public MarketPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public MarketPage clearAll() {

        click(By.id("com.xueqiu.android:id/edit_group"));
        click(By.id("com.xueqiu.android:id/check_all"));
        click(By.id("com.xueqiu.android:id/cancel_follow"));
        click(By.id("com.xueqiu.android:id/tv_right"));
        click(By.id("com.xueqiu.android:id/action_close"));

        return this;
    }


}
