package test_app.wework.page;

import org.openqa.selenium.By;

public class Wework extends BasePage{
    public Wework() {
        super("com.tencent.wework", ".launch.LaunchSplashActivity");
    }

    public 日程Page 日程(){
        click(By.xpath("//*[@text='日程']"));
        return new 日程Page(driver);
    }

    public 待办Page 待办() {
        click(By.id("h7x"));

        return new 待办Page(driver);
    }

    public 汇报Page 汇报() {
        click("工作台");
        //done: swipe to find element
        scrollFind("汇报").click();

        return new 汇报Page(driver);
    }
}
