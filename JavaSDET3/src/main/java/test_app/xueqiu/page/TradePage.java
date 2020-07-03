package test_app.xueqiu.page;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

public class TradePage extends BasePage{
    private String tradePage = "实盘交易";
    private By A股开户 = By.xpath("//h1[contains(text(),'A股开户')]");
    private String A股开户Page = "极速开户";
    private By mobileLocator = By.cssSelector("#phone-number");
    private By codeLocator = By.cssSelector("#code");
    private By submit = By.cssSelector(".btn-submit");

    public TradePage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public TradePage A股开户(String mobile, String code) {
        switchContext("webview");
        switchWindowByTitle(tradePage);

        click(A股开户);

        switchWindowByTitle(A股开户Page);
        sendKeys(mobileLocator,mobile);
        sendKeys(codeLocator,code);
        click(submit);

        return this;
    }
}
