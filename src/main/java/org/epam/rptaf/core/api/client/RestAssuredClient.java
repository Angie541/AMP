package org.epam.rptaf.core.api.client;

import static io.restassured.RestAssured.given;
import static org.epam.rptaf.test.api.ApiTestUtil.AUTH_BEARER;
import static org.epam.rptaf.test.api.ApiTestUtil.FILTER_PATH;
import static org.epam.rptaf.test.api.ApiTestUtil.getMapperLambda;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.epam.rptaf.core.api.dto.FilterCreateResponseDto;
import org.epam.rptaf.core.api.dto.FilterDto;
import org.epam.rptaf.core.api.dto.FilterUpdateResponseDto;
import org.epam.rptaf.core.api.dto.FiltersDto;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class RestAssuredClient implements HttpClient {

    public RestAssuredClient(String baseUrl) {
        RestAssured.baseURI = baseUrl;
    }

    @Override
    public FilterCreateResponseDto sendFilterPost(FilterDto filterDto) {
        return given()
                .auth().oauth2(AUTH_BEARER)
                .when()
                .contentType(ContentType.JSON)
                .body(filterDto, getMapperLambda()) //serialization
                .post(FILTER_PATH)
                .then()
                .statusCode(statusChecker())
                .extract()
                .body()
                .as(FilterCreateResponseDto.class, getMapperLambda()); //deserialization
    }

    @Override
    public void sendFilterDelete(int filterId) {
        given()
                .auth().oauth2(AUTH_BEARER)
                .when()
                .delete(FILTER_PATH + filterId)
                .then()
                .statusCode(statusChecker());
    }

    @Override
    public FiltersDto sendFiltersGet() {
        return given()
                .auth().oauth2(AUTH_BEARER)
                .when()
                .get(FILTER_PATH)
                .then()
                .statusCode(statusChecker())
                .contentType(ContentType.JSON)
                .extract()
                .body()
                .as(FiltersDto.class, getMapperLambda()); //deserialization
    }

    @Override
    public FilterDto sendFilterGet(int filterId) {
        return given()
                .auth().oauth2(AUTH_BEARER)
                .when()
                .get(FILTER_PATH + filterId)
                .then()
                .statusCode(statusChecker())
                .contentType(ContentType.JSON)
                .extract()
                .body()
                .as(FilterDto.class, getMapperLambda()); //deserialization
    }

    @Override
    public FilterUpdateResponseDto sendFilterPut(int filterId, FilterDto filterDto) {
        return given()
                .auth().oauth2(AUTH_BEARER)
                .when()
                .contentType(ContentType.JSON)
                .body(filterDto, getMapperLambda()) //serialization
                .put(FILTER_PATH + filterId)
                .then()
                .statusCode(statusChecker())
                .extract()
                .body()
                .as(FilterUpdateResponseDto.class, getMapperLambda()); //deserialization
    }

    private Matcher<Integer> statusChecker() {
        return new BaseMatcher<>() {
            @Override
            public void describeTo(Description description) {
            }

            @Override
            public boolean matches(Object o) {
                int statusCode = (int) o;
                if (statusCode >= 200 && statusCode <= 399) {
                    return true;
                } else {
                    throw new HttpClientException(statusCode);
                }
            }
        };
    }
}
