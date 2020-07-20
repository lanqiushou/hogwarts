package test_service.testcase;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.parallel.Execution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT;

/**
 * 线程测试
 */
//@Execution(CONCURRENT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Demo_07_1 {
    private static final Logger logger = LoggerFactory.getLogger(Demo_07_1.class);

    @DisplayName("线程测试01")
    @RepeatedTest(10)
    @Execution(CONCURRENT)
    void threadTest01() throws InterruptedException {
        Thread.sleep(3000);
        long id = Thread.currentThread().getId();
        logger.info("线程号" + id + "==>装入坚果01 " + "\n");
    }

    @DisplayName("线程测试02")
    @Execution(CONCURRENT)
    @RepeatedTest(10)
    void threadTest02() throws InterruptedException {
        Thread.sleep(3000);
        long id = Thread.currentThread().getId();
        logger.info("线程号" + id + "==>装入坚果02 " + "\n");
    }
}
