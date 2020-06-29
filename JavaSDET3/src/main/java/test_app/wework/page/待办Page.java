package test_app.wework.page;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class 待办Page extends BasePage {
    private final By add = By.id("h9p");
    private final By detail = By.id("b58");
    private final By save = byText("保存");
    private final By todoList = By.id("h7b");


    public 待办Page(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public 待办Page 添加(String content) {
        click(add);
        sendKeys(detail,content);
        click(save);

        return this;
    }

    public List<String> 获取待办列表() {

        return finds(todoList).stream().map(x -> x.getText()).collect(Collectors.toList());
    }

    public 待办Page 完成(String todoName) {
        By todo = By.xpath("//*[@resource-id='com.tencent.wework:id/h7b' and @text='" +todoName+ "']/../../*[@resource-id='com.tencent.wework:id/h7w']");
        click(todo);

        return this;
    }

    public void 回首页() {
        click(By.id("h9e"));
    }
}
