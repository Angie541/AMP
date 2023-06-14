package org.epam.rptaf.core.api.client;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.epam.rptaf.core.api.dto.FilterDto;
import org.epam.rptaf.core.api.dto.FiltersDto;

import static io.restassured.RestAssured.given;
import static org.epam.rptaf.test.api.ApiTestUtil.*;

public class RestAssuredClient {

    public RestAssuredClient(String baseUrl) {
        RestAssured.baseURI = baseUrl;
    }

    public Response sendFilterPost(FilterDto requestDto) {
        return given()
                .auth().oauth2(AUTH_BEARER)
                .when()
                .contentType(ContentType.JSON)
                .body(requestDto, getMapperLambda()) //serialization
                .post(FILTER_PATH);
    }

    public ValidatableResponse sendFilterDelete(int filterId) {
        return given()
                .auth().oauth2(AUTH_BEARER)
                .when()
                .delete(FILTER_PATH + filterId)
                .then();
    }

    public FiltersDto sendFiltersGet() {
        return given()
                .auth().oauth2(AUTH_BEARER)
                .when()
                .get(FILTER_PATH)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .body()
                .as(FiltersDto.class, getMapperLambda()); //deserialization
    }

    public ValidatableResponse sendFilterGet(int filterId) {
        return given()
                .auth().oauth2(AUTH_BEARER)
                .when()
                .get(FILTER_PATH + filterId)
                .then();
    }

    public FiltersDto sendOwnFiltersGet() {
        return given()
                .auth().oauth2(AUTH_BEARER)
                .when()
                .get(FILTER_PATH + "own")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .body()
                .as(FiltersDto.class, getMapperLambda()); //deserialization
    }

    public FiltersDto sendSharedFiltersGet() {
        return given()
                .auth().oauth2(AUTH_BEARER)
                .when()
                .get(FILTER_PATH + "shared")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .body()
                .as(FiltersDto.class, getMapperLambda()); //deserialization
    }
}
