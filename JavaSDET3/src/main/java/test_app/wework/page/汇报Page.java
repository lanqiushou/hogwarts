package test_app.wework.page;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

public class 汇报Page extends AppBasePage {
    private final By dailyReport = By.xpath("//*[@resource-id='com.tencent.wework:id/el8' and @text='日报']");
    private final By dailyContent = By.xpath("//*[@class='android.view.View' and @text='今日工作*']/../*[@class='android.widget.EditText']");
    private final By dailyReportDetail = By.xpath("//*[contains(@text,'我的日报')]");

    private final By weeklyReport = By.xpath("//*[@resource-id='com.tencent.wework:id/el8' and @text='周报']");
    private final By weeklyContent = By.xpath("//*[@class='android.view.View' and @text='本周工作*']/../*[@class='android.widget.EditText']");
    private final By weeklyReportDetail = By.xpath("//*[contains(@text,'我的周报')]");

    private final By back = By.id("h9e");

    private final By records = byText("记录");
    private final By submitedRecords = By.xpath("//*[@resource-id='com.tencent.wework:id/gwh' and @text='我提交的']");

    public 汇报Page(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public 汇报Page 新建日报(String content) {
        click(dailyReport);
        sendKeys(dailyContent, content);
        scrollFind("提交").click();
        waitElement(dailyReportDetail);
        click(back);

        return this;
    }

    public 汇报Page 新建周报(String content) {
        click(weeklyReport);
        sendKeys(weeklyContent,content);
        scrollFind("提交").click();
        waitElement(weeklyReportDetail);
        click(back);

        return this;
    }

    public List<String> 获取记录列表(String type) {
        click(records);
        click(submitedRecords);

        String keyword = null;
        if ("日报".equals(type)) {
            keyword = "今日工作：";
        } else if ("周报".equals(type)) {
            keyword = "本周工作：";
        } else {
            //其它汇报类型
        }

        By reportList = By.xpath("//*[starts-with(@text,'" +keyword+ "')]");

        return finds(reportList)
                .stream()
                .map(x -> (x.getText().split("："))[1])
                .collect(Collectors.toList());
    }
}
