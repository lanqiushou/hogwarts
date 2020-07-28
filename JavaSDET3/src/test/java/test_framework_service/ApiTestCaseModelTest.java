package test_framework_service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class ApiTestCaseModelTest {

    private static BaseApi baseApi;
    private static ApiTestCaseModel apiTestCase;

    @BeforeAll
    static void beforeAll() throws IOException {
        baseApi = new BaseApi();
        baseApi.load("src/main/resources/test_framework_service/api");
        apiTestCase = ApiTestCaseModel.load("src/main/resources/test_framework_service/testcase/test_list.yaml");

    }

    @Test
    void load() throws IOException {
        assertThat(apiTestCase.name, equalTo("add"));
    }

    @Test
    void run() {
        apiTestCase.run(baseApi);
    }
}