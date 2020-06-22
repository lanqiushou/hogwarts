package test_web.wework.page;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

public class ContactPage extends BasePage {
    By addMember=By.linkText("添加成员");
    By username=By.name("username");
    By delete=By.linkText("删除");

    public ContactPage(RemoteWebDriver driver) {
        super(driver);
    }

    public ContactPage flush() {
        click(By.cssSelector("#menu_contacts"));

        return this;
    }

    public ContactPage addMember(String username, String acctid, String mobile) {
        //todo:
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        new WebDriverWait(MainPage.driver, Duration.ofSeconds(10))
//                .until(ExpectedConditions.visibilityOfElementLocated(addMember));
//        //todo: 就算可点击，仍然有一定的概率是点击不成功的
//        new WebDriverWait(MainPage.driver, Duration.ofSeconds(10))
//                .until(ExpectedConditions.elementToBeClickable(addMember));


        while(driver.findElements(this.username).size()==0){
            click(addMember);
        }

//        driver.findElement(this.username).sendKeys(username);
//        driver.findElement(By.name("acctid")).sendKeys(acctid);
//        driver.findElement(By.name("mobile")).sendKeys(mobile);
//        driver.findElement(By.cssSelector(".js_btn_save")).click();
        sendKeys(this.username, username);
        sendKeys(By.name("acctid"), acctid);
        sendKeys(By.name("mobile"), mobile);
        click(By.cssSelector(".js_btn_save"));
        return this;
    }

    public ContactPage search(String keyword){
        sendKeys(By.id("memberSearchInput"), keyword);
//        driver.findElement(By.id("memberSearchInput")).sendKeys(keyword);
//        new WebDriverWait(driver, Duration.ofSeconds(10))
//                .until(ExpectedConditions.elementToBeClickable(delete));
        return this;
    }

    public String getUserName(){
        return driver.findElement(By.cssSelector(".member_display_cover_detail_name")).getText();
    }

    public String getTagName() {
        //todo: 取出来的值不对，还需要再对元素进行定位
        return driver.findElement(By.cssSelector(".ww_commonCntHead_title_inner_text")).getText();
    }

    public ContactPage delete(){
        click(delete);
        click(By.linkText("确认"));
        click(By.id("clearMemberSearchInput"));
//        driver.findElement(delete).click();
//        driver.findElement(By.linkText("确认")).click();
//        driver.findElement(By.id("clearMemberSearchInput")).click();
        return this;

    }

    public ContactPage importFromFile(URL path){
        //todo:
        System.out.println(path.getPath());

        String path_utf="";
        try {
            path_utf=URLDecoder.decode(path.getFile(), "UTF-8");
            System.out.println(path_utf);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        click(By.cssSelector(".ww_operationBar:nth-child(1) .ww_btn_PartDropdown_left"));
        click(By.linkText("文件导入"));
//        click(By.name("file"));
//        sendKeys(By.name("file"), "C:\\fakepath\\通讯录批量导入模板.xlsx");
        upload(By.name("file"), path_utf);
//        driver.findElement(By.name("file")).sendKeys("/Users/seveniruby/projects/Java3/src/main/resources/通讯录批量导入模板.xlsx");
//        sendKeys(By.name("file"), "C:\\fakepath\\通讯录批量导入模板.xlsx");
//        click(By.linkText("导入"));
//        click(By.linkText("完成"));
        click(By.cssSelector("#submit_csv"));
        click(By.cssSelector("#reloadContact"));

        return this;
    }

    public ContactPage addDepartment(String name) {
        click(By.cssSelector(".member_colLeft_top_addBtn"));
        click(By.linkText("添加部门"));
        sendKeys(By.name("name"),name);
        click(By.linkText("选择所属部门"));
        click(By.xpath("//*[@class='js_parent_party_name']/parent::a/following-sibling::div/child::div/ul/li"));

        click(By.cssSelector(".qui_dialog_foot > .ww_btn_Blue"));

        return this;
    }

    public ContactPage deleteDepartment(String name) {
        click(By.cssSelector("#\\31 688850968926753 > .jstree-icon"));
        click(By.linkText(name));
        clickNonClickble(By.xpath("//li[2]/a/span"));
        clickNonClickble(By.xpath("(//a[contains(text(),'删除')])[3]"));
        click(By.cssSelector(".qui_dialog_foot > .ww_btn_Blue"));

        return this;
    }

    public ContactPage addTag(String tagName) {
        clickNonClickble(By.linkText("标签"));
        click(By.cssSelector(".member_colLeft_top_addBtn"));
        sendKeys(By.name("name"), tagName);
        click(By.cssSelector(".qui_dialog_foot > .ww_btn_Blue"));

        return this;
    }

    public ContactPage deleteTag(String tagName) {
        clickNonClickble(By.linkText("标签"));
        click(By.xpath("//*[contains(text(),\"我的标签\")]/../ul/li[contains(text(),'"+tagName+"')]/a"));
        click(By.xpath("//main[@id=\"main\"]//*[contains(text(),\"删除\")]"));
        click(By.linkText("确定"));
//        clickNonClickble(By.cssSelector(".member_tag_list_moreBtn"));
//        click(By.linkText("删除"));
//        click(By.cssSelector(".qui_dialog_foot > .ww_btn_Blue"));

        return this;
    }
}
