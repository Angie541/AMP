package org.epam.rptaf.test.api;

import org.epam.rptaf.core.api.client.HttpClient;
import org.epam.rptaf.core.api.dto.ConditionDto;
import org.epam.rptaf.core.api.dto.FilterDto;
import org.epam.rptaf.core.api.dto.FiltersDto;
import org.epam.rptaf.core.api.dto.OrderDto;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

public class FilterApacheTest {
    private final HttpClient restClient = new HttpClient("http://localhost:8080/api/v1");
    private int filterId;

    @AfterMethod
    public void after() {
        //restClient.sendFilterDelete(filterId);
    }

    @Test
    public void getPermittedFilters() {
        //createFilter(true);

        FiltersDto dto = restClient.sendFiltersGet();

        assertThat(dto.getContent(), hasSize(1));
    }

    /*private Response createFilter(boolean correct) {
        FilterDto requestDto = correct ? getFilterDto() : new FilterDto();
        Response response = restClient.sendFilterPost(requestDto);
        if (response.statusCode() == 201) {
            filterId = response.then().extract()
                    .body()
                    .path("id");
        }
        return response;
    }*/

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
