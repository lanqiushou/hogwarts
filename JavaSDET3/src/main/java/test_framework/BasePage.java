package test_framework;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.openqa.selenium.By;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

//自动化领域建模
public class BasePage {
    List<PageObjectModel> pages = new ArrayList<>();
    private HashMap<String, Object> params;


    public void click(HashMap<String, Object> map) {
        System.out.println("click");
        System.out.println(map);
//        driver.findElement(by).click
    }

    public void sendKeys(HashMap<String, Object> map, HashMap<String, Object> params) {
        System.out.println("sendKeys:    map->" + map + "  params->" + params);

    }

    public void action(HashMap<String, Object> map) {
        System.out.println("action");
        System.out.println(map);

//        如果是page级别的关键字
        if (map.containsKey("page")) {
            String action = map.get("action").toString();
            String pageName = map.get("page").toString();
            params = (HashMap<String, Object>) map.get("params");

            pages.forEach(pom-> System.out.println(pom.name));

            pages.stream()
                    .filter(pom -> pom.name.equals(pageName))
                    .findFirst()
                    .get()
                    .methods.get(action).forEach(step -> {
                action(step);
            });
        } else {
//            自动化级别
            if (map.containsKey("click")) {
                HashMap<String, Object> by = (HashMap<String, Object>) map.get("click");
                click(by);
            }

            if (map.containsKey("sendKeys")) {
                sendKeys(map, params);
            }
        }




    }

    public void find() {

    }

    public void getText() {

    }

    /*
     * 从包含元素定位信息的map中获取定位符
     */
    public By getLocator(HashMap<String, Object> map) {
        By by = null;

        if(map.containsKey("id")) {
            by = By.id((String)map.get("id"));
        } else if(map.containsKey("css")) {
            by = By.cssSelector((String)map.get("css"));
        } else if(map.containsKey("xpath")) {
            by = By.xpath((String)map.get("xpath"));
        } else if(map.containsKey("name")) {
            by = By.name((String)map.get("name"));
        } else if (map.containsKey("class")) {
            by = By.className((String)map.get("class"));
        } else if (map.containsKey("linktext")) {
            by = By.linkText((String)map.get("linktext"));
        } else {
            System.out.println("不支持的定位方式：" + map);
        }

        return by;
    }

    public String run(UIAuto uiAuto) {
        AtomicReference<String> result = new AtomicReference<>("failed");

        uiAuto.steps.stream().forEach(m -> {
//            if (m.keySet().contains("click")) {
//                click((HashMap<String, Object>) m.get("click"));
//            }

            if (m.containsKey("click")) {
                HashMap<String, Object> by = (HashMap<String, Object>) m.get("click");
                click(by);
            }

            if (m.containsKey("sendKeys")) {
                sendKeys(m,null);
            }

            if (m.containsKey("action")) {
                action(m);
            }

//            if(m.containsKey("page")){
//                page(m);
//            }

            if (m.containsKey("expect")) {
                result.set(getResult(m));
            }
        });

        return result.get();
    }

    public String getResult(HashMap<String, Object> m) {
        System.out.println("getResult");
        //子类实现具体方法

        return "failed";
    }

    public UIAuto load(String path) {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        UIAuto uiauto = null;
        try {
            uiauto = mapper.readValue(
                    BasePage.class.getResourceAsStream(path),
                    UIAuto.class
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uiauto;

    }

    public PageObjectModel loadPage(String path) {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        PageObjectModel pom = null;
        try {
            pom = mapper.readValue(
                    new File(path),
                    PageObjectModel.class
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pom;
    }

    public void loadPages(String dir) {
        Stream.of(new File(dir).list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.contains("_page");
            }
        })).forEach(path -> {
            path = dir + "/" + path;
            System.out.println(path);
            pages.add(loadPage(path));
        });
    }
}
