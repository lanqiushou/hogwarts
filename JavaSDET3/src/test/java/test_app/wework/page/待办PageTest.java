package test_app.wework.page;

import org.junit.jupiter.api.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class 待办PageTest {

    private static Wework wework;

    @BeforeAll
    static void beforeAll() {
        wework = new Wework();
    }

    @AfterAll
    static void afterAll() {
//        wework.quit();
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void 添加() {
        assertTrue(
            wework.待办()
                    .添加("测试待办")
                    .获取待办列表().contains("测试待办")
        );
    }

    @Test
    void 完成() {
        wework.待办()
                .完成("测试2").回首页();

        assertFalse(
                wework.待办()
                    .获取待办列表().contains("测试2")
        );
    }

    @Test
    void 获取待办列表() {
        assertTrue(
                wework.待办()
                        .获取待办列表().containsAll(Arrays.asList("测试待办","测试2","待办测试1"))
        );
    }
}