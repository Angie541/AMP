package org.epam.rptaf.test.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.epam.rptaf.core.api.client.RestAssuredClient;
import org.epam.rptaf.core.api.dto.ConditionDto;
import org.epam.rptaf.core.api.dto.FilterDto;
import org.epam.rptaf.core.api.dto.FiltersDto;
import org.epam.rptaf.core.api.dto.OrderDto;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.epam.rptaf.test.api.ApiTestUtil.getMapperLambda;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class FilterTest {
    private final RestAssuredClient restClient = new RestAssuredClient("http://localhost:8080/api/v1");
    private int filterId;

    @AfterMethod
    public void after() {
        restClient.sendFilterDelete(filterId);
    }

    @Test
    public void createUserFilter() {
        createFilter(true)
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("id", notNullValue());
    }

    @Test
    public void createWrongUserFilter() {
        createFilter(false)
                .then()
                .statusCode(400);
    }

    @Test
    public void createExistedUserFilter() {
        createFilter(true);

        createFilter(true)
                .then()
                .statusCode(409);
    }

    @Test
    public void getPermittedFilters() {
        createFilter(true);

        FiltersDto dto = restClient.sendFiltersGet();

        assertThat(dto.getContent(), hasSize(1));
    }

    @Test
    public void getExistedUserFilter() {
        createFilter(true);

        FilterDto filterDto = restClient.sendFilterGet(filterId)
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .body()
                .as(FilterDto.class, getMapperLambda()); //deserialization

        assertThat(filterDto.getId(), is(filterId));
        assertThat(filterDto.getName(), is("demo"));
        assertThat(filterDto.getType(), is("Launch"));
    }

    @Test
    public void getNotExistedUserFilter() {
        restClient.sendFilterGet(225)
                .statusCode(404);
    }

    @Test
    public void deleteNotExistedUserFilter() {
        restClient.sendFilterDelete(225)
                .statusCode(404);
    }

    @Test
    public void deleteExistedUserFilter() {
        createFilter(true);
        restClient.sendFilterDelete(filterId)
                .statusCode(200);
    }

    @Test
    public void getOwnUserFilters() {
        createFilter(true);

        FiltersDto dto = restClient.sendOwnFiltersGet();

        assertThat(dto.getContent(), hasSize(1));
    }

    @Test
    public void getSharedUserFilters() {
        createFilter(true);

        FiltersDto dto = restClient.sendSharedFiltersGet();

        assertThat(dto.getContent(), hasSize(1));
    }

    private Response createFilter(boolean correct) {
        FilterDto requestDto = correct ? getFilterDto() : new FilterDto();
        Response response = restClient.sendFilterPost(requestDto);
        if (response.statusCode() == 201) {
            filterId = response.then().extract()
                    .body()
                    .path("id");
        }
        return response;
    }

    private FilterDto getFilterDto() {
        ConditionDto conditionDto = new ConditionDto()
                .setCondition("cnt")
                .setFilteringField("name")
                .setValue("name");

        OrderDto orderDto = new OrderDto()
                .setAsc(true)
                .setSortingColumn("name");

        return new FilterDto()
                .setConditions(List.of(conditionDto))
                .setName("demo")
                .setOrders(List.of(orderDto))
                .setShare(true)
                .setType("Launch");
    }
}
