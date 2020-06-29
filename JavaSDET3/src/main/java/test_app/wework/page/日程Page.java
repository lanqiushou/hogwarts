package test_app.wework.page;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

public class 日程Page extends BasePage{
    //todo:多版本app、多平台的app 定位符通常有差别
    private final By taskName = By.id("b58");
    private final By save = byText("保存");
    private final By taskList = By.id("gr5");
    private final By add =By.id("h9p");
//    private By timeSelect = By.id("ah7");
//    private By timeConfirm = By.id("btc");
    private final By delete = By.id("bi_");
    private final By deleteConfirm = byText("删除");

    public 日程Page(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    /*
     *@time 格式要求hh:mm
     */
    public 日程Page 添加(String name, String time){
        click(add);
        sendKeys(taskName, name);

        //todo：时间控件操作
/*        //点击时间控件
        click(timeSelect);

        //选择小时
        String hourString = Arrays.asList(time.split(":")).get(0);
        By hour = By.xpath("//*[@resource-id='com.tencent.wework:id/d5l']/*[@resource-id='com.tencent.wework:id/eru' and @text='"+hourString+"']");
        click(hour);

        //选择分钟
        String miniteString = Arrays.asList(time.split(":")).get(1);
        By minute = By.xpath("//*[@resource-id='com.tencent.wework:id/eek']/*[@resource-id='com.tencent.wework:id/eru' and @text='" +miniteString+ "']");
        click(minute);

        //确认选择时间
        click(timeConfirm);*/

        click(save);
        return this;
    }

    public List<String> 获取日程(String day){
        if(day!=null){
            //done:选择日期
            click(By.xpath("//*[@resource-id='com.tencent.wework:id/gj5' and @text='"+day+"']"));
        }
        return driver.findElements(taskList).stream().map(x->x.getText()).collect(Collectors.toList());
    }

    public List<MobileElement> 获取日程对象(String day) {
        if(day!=null){
            //done:选择日期
            click(By.xpath("//*[@resource-id='com.tencent.wework:id/gj5' and @text='"+day+"']"));
        }

        return driver.findElements(taskList);
    }

    public 日程Page 清理当天日程(String day) {
        do{
            List<MobileElement> list = 获取日程对象(day);

            if(list.size() == 0) {break;}

            删除日程(list.get(0));

        } while(true);

        return this;
    }

    private void 删除日程(MobileElement element) {
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        element.click();
        click(delete);
        click(deleteConfirm);

    }
}
