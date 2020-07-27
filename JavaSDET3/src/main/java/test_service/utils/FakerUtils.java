package test_service.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

/**
 * @version: V1.0
 * @author: kuohai
 * @className: FakerUtils
 * @packageName: com.wecht.utils
 * @description: faker伪造工具
 * @data: 2020-07-18 4:22 下午
 **/
public class FakerUtils {
    private static final Logger logger = LoggerFactory.getLogger(FakerUtils.class);

    public static String getTimeStamp() {
        return String.valueOf(System.currentTimeMillis());
    }

    /**
     * 转换字符串为int数组，字符串格式： {1,2,3}
     * @param intArray
     * @return 如果字符串为null，则返回null。
     */
    public static int[] string2IntArray(String intArray) {
        int[] array = null;

        if(null != intArray) {
            array = Stream.of(intArray.replaceAll("[\\{\\}]", "").split(",")).mapToInt(Integer::parseInt).toArray();
        }

        return array;
    }

    /**
     * 字符串格式的数字转换为整形数值
     * @param intString
     * @return 如果字符串为null，则返回0。
     */
    public static int string2int(String intString) {
        int integer = 0;

        if (null != intString) {
            integer = Integer.parseInt(intString);
        }

        return integer;
    }

    /**
     * 转换字符串格式的布尔值为布尔类型
     * @param boolString
     * @return 如果字符串为null，则返回false
     */
    public static boolean string2bool(String boolString) {
        boolean bool = false;

        if(null != boolString) {
            bool = Boolean.parseBoolean(boolString);
        }

        return bool;
    }
}
