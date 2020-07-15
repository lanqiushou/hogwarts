package test_service.mock;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class MockTool {

    private static BrowserMobProxy proxy;

    @BeforeAll
    static void beforeAll() {
        proxy = new BrowserMobProxyServer();
        proxy.start(8083);
    }

    @AfterAll
    static void afterAll() {
        proxy.stop();
    }

    @Test
    void mockNumber() throws IOException, InterruptedException {
        proxy.addResponseFilter((httpResponse, httpMessageContents, httpMessageInfo) -> {
            JSONObject json = JSONObject.parseObject(httpMessageContents.getTextContents());
            httpMessageContents.setTextContents(modifyJsonNumber(json).toJSONString());
        });
//        String json = new String(Files.readAllBytes(Paths.get("/Users/tengjb/study/stock.json")));
//        JSONObject jsonObject = JSONObject.parseObject(json);

//        JSONObject modified = modifyJsonArray(jsonObject);
//        getJsonType(modified);

        Thread.sleep(200000);
    }

    @Test
    void mockString() throws IOException, InterruptedException {
        proxy.addResponseFilter((httpResponse, httpMessageContents, httpMessageInfo) -> {
            JSONObject json = JSONObject.parseObject(httpMessageContents.getTextContents());
            httpMessageContents.setTextContents(modifyJsonString(json).toJSONString());
        });

        Thread.sleep(200000);
    }

    @Test
    void mockObject() throws IOException, InterruptedException {
        proxy.addResponseFilter((httpResponse, httpMessageContents, httpMessageInfo) -> {
            JSONObject json = JSONObject.parseObject(httpMessageContents.getTextContents());
            String modifiedJson = JSONObject.toJSONString(modifyJsonObject(json), SerializerFeature.WriteMapNullValue);
            System.out.println(modifiedJson);
            httpMessageContents.setTextContents(modifiedJson);
//            httpMessageContents.setTextContents(httpMessageContents.getTextContents());
        });

        Thread.sleep(200000);
    }

    @Test
    void mockArray() throws IOException, InterruptedException {
        proxy.addResponseFilter((httpResponse, httpMessageContents, httpMessageInfo) -> {
            JSONObject json = JSONObject.parseObject(httpMessageContents.getTextContents());
            String modifiedJson = JSONObject.toJSONString(modifyJsonArray(json), SerializerFeature.WriteMapNullValue);
            System.out.println(modifiedJson);
            httpMessageContents.setTextContents(modifiedJson);
        });

        Thread.sleep(200000);
    }
    /*
     * 修改JSON对象中数据类型为数字（Integer, BigDecimal, Long)的内容
     */
    JSONObject modifyJsonNumber(JSONObject jsonObject) {
        List<Integer> intList = Arrays.asList(-10, -1, 0, 1, 10);
        List<BigDecimal> bigDecimalList = new ArrayList<>();
        Arrays.asList("-10", "-1", "-0.5", "0", "0.5","1", "10").forEach(e -> bigDecimalList.add(new BigDecimal((String)e)));
        List<Long> longList = Arrays.asList(-10l, -1l, 0l, 1l, 10l);

        jsonObject.forEach((key,value) -> {
            String valueType = null;
            Random random = new Random();

            if(!(null==value)) {
                valueType = value.getClass().getSimpleName();

                if(valueType.equals("Integer")) {
                    jsonObject.put(key, intList.get(random.nextInt(intList.size())));
                }

                if(valueType.equals("BigDecimal")) {
                    jsonObject.put(key, bigDecimalList.get(random.nextInt(bigDecimalList.size())));
                }

                if(valueType.equals("Long")) {
                    jsonObject.put(key, longList.get(random.nextInt(longList.size())));
                }

                //如果是JSON对象，递归调用
                if (valueType.equals("JSONObject")) {
                    jsonObject.put(key, modifyJsonNumber((JSONObject) value));
                }

                //如果是JSON 对象数组，逐个递归调用
                if (valueType.equals("JSONArray")) {
                    JSONArray jsonArray = (JSONArray) value;
                    JSONArray newArray = new JSONArray();

                    jsonArray.forEach(json -> newArray.add(modifyJsonNumber((JSONObject) json)));
                    jsonObject.put(key, newArray);
                }
            }

//            System.out.println(" " + key + ":" + valueType);
        });

        return jsonObject;
    }

    /*
     * 修改JSON对象中数据类型为String的内容
     */
    JSONObject modifyJsonString(JSONObject jsonObject) {
        List<String> stringList = Arrays.asList("", "中文","English", "*@&^$#");

        jsonObject.forEach((key,value) -> {
            String valueType = null;
            Random random = new Random();

            if(!(null==value)) {
                valueType = value.getClass().getSimpleName();

                if(valueType.equals("String")) {
                    jsonObject.put(key, stringList.get(random.nextInt(stringList.size())));
                }

                //如果是JSON对象，递归调用
                if (valueType.equals("JSONObject")) {
                    jsonObject.put(key, modifyJsonString((JSONObject) value));
                }

                //如果是JSON 对象数组，逐个递归调用
                if (valueType.equals("JSONArray")) {
                    JSONArray jsonArray = (JSONArray) value;
                    JSONArray newArray = new JSONArray();

                    jsonArray.forEach(json -> newArray.add(modifyJsonString((JSONObject) json)));
                    jsonObject.put(key, newArray);
                }
            }

//            System.out.println(" " + key + ":" + valueType);
        });

        return jsonObject;
    }

    /*
     * 修改JSON对象中数据类型为对象的内容
     */
    JSONObject modifyJsonObject(JSONObject jsonObject) {

        jsonObject.forEach((key,value) -> {
            String valueType = null;
            Random random = new Random();

            if(!(null==value)) {
                valueType = value.getClass().getSimpleName();

                //如果是非叶子JSON对象，递归调用
                if (valueType.equals("JSONObject")) {
                    JSONObject json = (JSONObject) value;

                    if(isJsonObjectLeaf(json)) {
                        jsonObject.put(key, null);
                    } else {
                        jsonObject.put(key, modifyJsonObject(json));
                    }

                }

                //如果是JSON 对象数组，逐个递归调用
                if (valueType.equals("JSONArray")) {
                    JSONArray jsonArray = (JSONArray) value;
                    JSONArray newArray = new JSONArray();

                    jsonArray.forEach(json -> newArray.add(modifyJsonObject((JSONObject) json)));
                    jsonObject.put(key, newArray);
                }
            }

//            System.out.println(" " + key + ":" + valueType);
        });

        return jsonObject;
    }

    /*
     * 修改Json 数组的长度
     */
    JSONObject modifyJsonArray(JSONObject jsonObject) {
        List<Integer> sizeList = Arrays.asList(0, 1, 10, 100);

        jsonObject.forEach((key,value) -> {
            String valueType = null;
            Random random = new Random();

            if(!(null==value)) {
                valueType = value.getClass().getSimpleName();

                //如果是JSON对象，递归调用
                if (valueType.equals("JSONObject") && !(isJsonObjectLeaf((JSONObject)value)) ) {
                    jsonObject.put(key, modifyJsonArray((JSONObject) value));
                }

                //如果是JSON 数组，扩充或者缩减
                if (valueType.equals("JSONArray")) {
                    JSONArray jsonArray = (JSONArray) value;
                    int jsonArraySize = jsonArray.size();
                    int newArraySize = sizeList.get(random.nextInt(sizeList.size()));
                    JSONArray newArray = new JSONArray();

                    //要生成的JSONArray元素比原来的JSONArray长度小，截取
                    if(newArraySize != 0 && (newArraySize < jsonArraySize || newArraySize == jsonArraySize) ) {
                        newArray.addAll(jsonArray.subList(0, newArraySize));
                    } else if (newArraySize > jsonArraySize && jsonArraySize != 0) { //要生成的JSONArray元素比原来的JSONArray长度大，扩充
                        //先按照新array的长度和旧array的倍数扩充
                        for(int i=0; i < (newArraySize / jsonArraySize); i++) {
                            newArray.addAll(jsonArray);
                        }

                        //不足倍数的，扩充到足够长度为止
                        if((newArraySize % jsonArraySize) != 0) {
                            newArray.addAll(jsonArray.subList(0, (newArraySize % jsonArraySize)));
                        }
                    }

                    jsonObject.put(key, newArray);
                    System.out.println("JSON 数组长度：" + newArraySize);
                }
            }
//            System.out.println(" " + key + ":" + valueType);
        });

        return jsonObject;
    }

    /*
     * 判断JSON对象是不是叶子对象（即下面没有Json Ojbect或Json Array类型的子元素）
     */
    boolean isJsonObjectLeaf(JSONObject jsonObject) {
        AtomicBoolean isLeaf = new AtomicBoolean(true);

        jsonObject.forEach((key,value) -> {
            String valueType = null;

            if (!(null == value)) {
                valueType = value.getClass().getSimpleName();

                if(valueType.equals("JSONObject") || valueType.equals("JSONArray")) {
                    isLeaf.set(false);
                }
            }
        });

        return isLeaf.get();
    }

    /*
     * 递归给出Json对象中每个元素的类型
     */
    void getJsonType(JSONObject jsonObject) {
        boolean isLeaf = true;

        jsonObject.forEach((key,value) -> {
            String valueType = null;

            if(!(null==value)) {
                valueType = value.getClass().getSimpleName();

                if (valueType.equals("JSONObject")) {
                    getJsonType((JSONObject) value);
                }

                if (valueType.equals("JSONArray")) {
                    ((JSONArray) value).forEach(json -> getJsonType((JSONObject) json));
                }
            }

            System.out.println(" " + key + " : " + valueType + " : " + value);
        });
    }
}
