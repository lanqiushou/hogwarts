package test_service.utils;

import io.restassured.builder.ResponseBuilder;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

import java.util.Base64;

public class Filters {

    /**
     * base64解密
     * @return
     */
    public static Filter base64Decode() {
        return new Filter() {
            @Override
            public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
                Response originalResponse = ctx.next(requestSpec, responseSpec);
                Response newResponse = originalResponse;

                if(requestSpec.getURI().contains("encode")) {
                    ResponseBuilder responseBuilder = new ResponseBuilder().clone(originalResponse);

                    responseBuilder.setBody(Base64.getDecoder().decode(originalResponse.getBody().asString().replace("\n","")));
                    newResponse = responseBuilder.build();
                }

                return newResponse;
            }
        };
    }
}
