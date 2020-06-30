package test_app.wework.page;

import org.junit.jupiter.api.*;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class 汇报PageTest {
    private static Wework wework;

    @BeforeAll
    static void beforeAll() {
        wework = new Wework();
    }

    @AfterAll
    static void afterAll() {

    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void 新建日报() {
        String test = "日报测试"+ new Random().nextInt();

        assertTrue(
                wework.汇报()
                    .新建日报(test)
                    .获取记录列表("日报").contains(test)
        );
    }

    @Test
    void 新建周报() {
        String test = "周报测试"+ new Random().nextInt();

        assertTrue(
                wework.汇报()
                        .新建周报(test)
                        .获取记录列表("周报").contains(test)
        );
    }

    @Test
    void 获取记录列表() {
        assertTrue(
    wework.汇报()
            .获取记录列表("日报")
            .size() > 0
        );

//        assertTrue(
//    wework.汇报()
//            .获取记录列表("周报")
//            .size() > 0
//        );
    }

}